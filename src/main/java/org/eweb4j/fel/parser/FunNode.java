package org.eweb4j.fel.parser;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

import org.eweb4j.fel.compile.FelMethod;
import org.eweb4j.fel.compile.InterpreterSourceBuilder;
import org.eweb4j.fel.compile.SourceBuilder;
import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.exception.EvalException;
import org.eweb4j.fel.function.Function;
import org.eweb4j.fel.function.FunMgr;

/**
 * 函数节点
 * 
 * @author yqs
 * 
 */
public  class FunNode extends AbstFelNode {
	private Function fun;

	public Function getFun() {
		return fun;
	}

	private static final Function NOT_FOUND_FUN = new Function() {

		
		public String getName() {
			return "未知函数";
		}

		
		public Object call(FelNode node, FelContext context) {
			throw new EvalException("找不到函数[" + node.getText() + "]", null);
		}

		
		public FelMethod toMethod(FelNode node, FelContext ctx) {
			return null;
		}
	};
	
	public FunNode(CommonTree node) {
		super(node);
	}

	public FunNode(Token token) {
		super(token);

	}

//	{
//		initFun();
//	}

	
	public Object interpret(FelContext context, FelNode node) {
		return fun.call(this, context);
	}


	public void initFun(FunMgr funMgr) {
		fun = funMgr.getFun(getText());
		if (fun == null) {
			fun = NOT_FOUND_FUN;
		}
	}
	
	
	public SourceBuilder toMethod(FelContext ctx) {
		if(this.builder!=null){
			return builder;
		}
		if(!this.isDefaultInterpreter()){
			return InterpreterSourceBuilder.getInstance();
		}
		return this.fun.toMethod(this,ctx);
	}
	
	
	public boolean stable() {
		if(this.fun instanceof Stable){
			// 函数是稳定的，并且参数是稳定的
			return ((Stable)fun).stable()&&this.isChildrenStable();
		}
		return false;
	}
}
