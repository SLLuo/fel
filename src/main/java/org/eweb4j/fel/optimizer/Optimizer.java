package org.eweb4j.fel.optimizer;

import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.parser.FelNode;

/**
 * 优化器
 * @author yuqingsong
 *
 */
public interface Optimizer  {
	
	
	FelNode call(FelContext ctx, FelNode node);
}
