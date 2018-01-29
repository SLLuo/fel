package org.eweb4j.fel.function;

import org.eweb4j.fel.parser.Stable;

public abstract class StableFunction implements Function, Stable {
	/*
	 * 
	 * 求函数值是否固定不变
	 * 
	 * 当函数的参数相同时，多次执行函数，返回值都相同表示此函数是稳定的
	 * 
	 * @see org.eweb4j.fel.parser.Optimizable#stable()
	 */
	
	public boolean stable() {
		return true;
	}

}
