package org.eweb4j.fel.interpreter;

import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.parser.FelNode;

/**
 * 解析器,用于解析AstNode的值
 * @author yqs
 *
 */
public interface Interpreter {

	/**
	 * @param context
	 * @return
	 */
	Object interpret(FelContext context, FelNode node);

}
