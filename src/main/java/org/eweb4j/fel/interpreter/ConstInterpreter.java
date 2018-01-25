package org.eweb4j.fel.interpreter;

import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.parser.FelNode;

public class ConstInterpreter implements Interpreter {

	private Object value;

	public ConstInterpreter(FelContext context, FelNode node) {
		this.value = node.eval(context);
	}

	public Object interpret(FelContext context, FelNode node) {
		return value;
	}

}
