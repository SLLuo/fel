package org.eweb4j.fel;

import org.eweb4j.fel.context.FelContext;

public interface Expression {
	/**
	 * 求表达式的值
	 * @param arguments
	 * @return
	 */
	Object eval(FelContext context);
	
}
