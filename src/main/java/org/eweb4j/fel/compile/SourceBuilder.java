package org.eweb4j.fel.compile;

import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.parser.FelNode;

/**
 * 创建java源码接口,此接口和SourceGenerator的区别在于，后者负责创建整个java类的源码。前者只用了创建表达式的代码片断。
 * @author yuqingsong
 *
 */
public interface SourceBuilder {
	
	/**
	 * 类型
	 * @param ctx TODO
	 * @param node TODO
	 * @return
	 */
	Class<?> returnType(FelContext ctx, FelNode node);
	/**
	 * 构建源码
	 * @param node TODO
	 * @return
	 */
	String source(FelContext ctx, FelNode node); 

}
