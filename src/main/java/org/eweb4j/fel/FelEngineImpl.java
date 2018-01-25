package org.eweb4j.fel;

import org.eweb4j.fel.common.FelBuilder;
import org.eweb4j.fel.compile.CompileService;
import org.eweb4j.fel.context.ArrayCtxImpl;
import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.context.Var;
import org.eweb4j.fel.function.FunMgr;
import org.eweb4j.fel.function.Function;
import org.eweb4j.fel.optimizer.Optimizer;
import org.eweb4j.fel.optimizer.VarVisitOpti;
import org.eweb4j.fel.parser.AntlrParser;
import org.eweb4j.fel.parser.FelNode;
import org.eweb4j.fel.parser.Parser;
import org.eweb4j.fel.security.SecurityMgr;

/**
 * 执行引擎
 * 
 * @author yqs
 * 
 */
public class FelEngineImpl implements FelEngine {

	private FelContext context;

	private CompileService compiler;

	private Parser parser;
	
	private FunMgr funMgr;
	
	private SecurityMgr securityMgr;


	public SecurityMgr getSecurityMgr() {
		return securityMgr;
	}

	public void setSecurityMgr(SecurityMgr securityMgr) {
		this.securityMgr = securityMgr;
	}

	public FelEngineImpl(FelContext context) {
		this.context = context;
		compiler = new CompileService();
		parser = new AntlrParser(this);
		this.funMgr=new FunMgr();
	}
	
	{
		this.securityMgr = FelBuilder.newSecurityMgr();
	}

	public FelEngineImpl() {
		this(new ArrayCtxImpl());
		// this(new MapContext());
	}

	
	public FelNode parse(String exp) {
		return parser.parse(exp);
	}

	
	public Object eval(String exp) {
		return this.eval(exp, this.context);
	}

	public Object eval(String exp, Var... vars) {
		FelNode node = parse(exp);
		Optimizer opt = new VarVisitOpti(vars);
		node = opt.call(context, node);
		return node.eval(context);
	}

	public Object eval(String exp, FelContext ctx) {
		return parse(exp).eval(ctx);
	}

	public Expression compile(String exp, Var... vars) {
		return compile(exp, null, new VarVisitOpti(vars));
	}
	
	public Expression compile(String exp, FelContext ctx, Optimizer... opts) {
		if (ctx == null) {
			ctx = this.context;
		}
		FelNode node = parse(exp);
		if (opts != null) {
			for (Optimizer opt : opts) {
				if (opt != null) {
					node = opt.call(ctx, node);
				}
			}
		}
		return compiler.compile(ctx, node, exp);
	}
	
	public String toString() {
		return "FelEngine";
	}
	
	public void addFun(Function fun) {
		this.funMgr.add(fun);
	}
	
	public FelContext getContext() {
		return this.context;
	}
	
	public CompileService getCompiler() {
		return compiler;
	}
	
	public void setCompiler(CompileService compiler) {
		this.compiler = compiler;
	}
	
	public Parser getParser() {
		return parser;
	}
	
	public void setParser(Parser parser) {
		this.parser = parser;
	}

	public FunMgr getFunMgr() {
		return funMgr;
	}
	
	public void setFunMgr(FunMgr funMgr) {
		this.funMgr = funMgr;
	}

	public void setContext(FelContext context) {
		this.context = context;
	}

}
