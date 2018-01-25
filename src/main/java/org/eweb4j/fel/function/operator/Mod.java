package org.eweb4j.fel.function.operator;

import org.eweb4j.fel.common.NumberUtil;

public class Mod extends Mul {

	@Override
	Object calc(double l, double r) {
		return NumberUtil.parseNumber(l % r);
	}
	
	@Override
	public String getName() {
		return "%";
	}
}
