package org.eweb4j.fel.compile;

import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.optimizer.Optimizer;
import org.eweb4j.fel.parser.FelNode;

public interface SourceGenerator {
	
	/**
	 * 获取表达式JAVA源代码
	 * @param node TODO
	 * @return 
	 */
	JavaSource getSource(FelContext ctx, FelNode node);
	
	void addOpti(Optimizer opti);
	
	
}
