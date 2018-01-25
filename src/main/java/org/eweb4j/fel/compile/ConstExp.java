package org.eweb4j.fel.compile;

import org.eweb4j.fel.Expression;
import org.eweb4j.fel.context.FelContext;

public final class ConstExp implements Expression {
	public ConstExp(Object o) {
		this.value = o;
	}

	private final Object value;

	public final Object eval(FelContext context) {
		return value;
	}
}
