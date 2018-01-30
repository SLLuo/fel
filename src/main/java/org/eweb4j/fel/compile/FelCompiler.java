package org.eweb4j.fel.compile;

import org.eweb4j.fel.Expression;

public interface FelCompiler {
	
	/**
	 * 
	 * 编译代码，并创建Expression
	 * @param src
	 * @return
	 */
	Expression compile(JavaSource src);

}
