package org.eweb4j.fel.interpreter;

import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.parser.FelNode;

/**
 * 代理解释器，用于保存节点
 * @author yuqingsong
 *
 */
public class ProxyInterpreter implements Interpreter{
	
	private Interpreter inte;
	
	private FelNode node;
	public ProxyInterpreter(Interpreter inte,FelNode node){
		this.inte = inte;
		this.node = node;
	}
	public Object interpret(FelContext context, FelNode node) {
		return inte.interpret(context, this.node);
	}

}
