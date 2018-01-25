package org.eweb4j.fel.optimizer;

import org.eweb4j.fel.common.Null;
import org.eweb4j.fel.common.ReflectUtil;
import org.eweb4j.fel.compile.SourceBuilder;
import org.eweb4j.fel.compile.VarBuffer;
import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.parser.ConstNode;
import org.eweb4j.fel.parser.FelNode;

/**
 * 当表达式是常量表达式，对表达式进行优化。
 * 
 * @author yuqingsong
 * 
 */
public class ConstExpOpti implements Optimizer {

	
	public FelNode call(FelContext ctx, FelNode node) {
		if (node instanceof ConstNode) {
			final Object value = node.eval(ctx);

			// 重新构建常量节点的java源码
			node.setSourcebuilder(new SourceBuilder() {

				
				public String source(FelContext ctx, FelNode node) {
					// Class<?> type = returnType(ctx, node);
					return VarBuffer.push(value, Object.class);
				}

				
				public Class<?> returnType(FelContext ctx, FelNode node) {
					if (value != null) {
						Class<?> cls = value.getClass();
						if (cls.isPrimitive()) {
							return ReflectUtil.toWrapperClass(cls);
						}
						return cls;
					}
					return Null.class;
				}
			});
		}
		return node;
	}

}
