package org.eweb4j.fel.function;

import java.lang.reflect.Method;
import java.util.List;

import org.eweb4j.fel.compile.FelMethod;
import org.eweb4j.fel.compile.SourceBuilder;
import org.eweb4j.fel.compile.VarBuffer;
import org.eweb4j.fel.context.FelContext;
import org.eweb4j.fel.function.operator.Dot;
import org.eweb4j.fel.parser.FelNode;

/**
 * 
 * 此类用于保存用户注册的java method。
 * 
 * @author Administrator
 * 
 */
public class JavaMethod implements Function {

	/**
	 * 方法名称
	 */
	private String name;

	/**
	 * 对象，如果是静态方法，对象为null
	 */
	private Object obj;

	/**
	 * java method
	 */
	private Method method;
	
	public JavaMethod(String name,Method method,Object obj) {
		this.name = name;
		this.method = method;
		this.obj = obj;
	}

	
	public Object call(FelNode node, FelContext context) {
//		Dot.clearNullNode(node);
		Object[] args = CommonFunction.evalArgs(node, context);
		return Dot.invoke(obj, method, args);
	}

	
	public String getName() {
		return name;
	}

	
	public SourceBuilder toMethod(FelNode node, FelContext context) {
		StringBuilder code = new StringBuilder();
		String classOrObjCode = getClassOrObjCode();
		code.append(classOrObjCode);
		code.append(".");
		String methodName = method.getName();
		code.append(methodName);
		StringBuilder methodParams = getParamsCode(node, context);
		code.append("(").append(methodParams).append(")");
		FelMethod returnMe = new FelMethod(method
				.getReturnType(), code.toString());
		return returnMe;
	}

	private String getClassOrObjCode() {
		if(obj == null){
			Class<?> cls = method.getDeclaringClass();
			return cls.getCanonicalName();
		}else{
			String varName = VarBuffer.push(obj);
			return varName;
		}
	}

	private StringBuilder getParamsCode(FelNode node, FelContext context) {
		StringBuilder methodParams = new StringBuilder();
		List<FelNode> params = node.getChildren();
		boolean hasParam = params != null && !params.isEmpty();
		Class<?>[] paramTypes = method.getParameterTypes();
		if (hasParam) {
			// 有参数
			for (int i = 0; i < paramTypes.length; i++) {
				Class<?> paramType = paramTypes[i];
				FelNode p = params.get(i);
				String paramCode = Dot.getParamCode(paramType, p, context);
				methodParams.append(paramCode);
				if(i<paramTypes.length-1){
					methodParams.append(",");
				}
			}
		}
		return methodParams;
	}

	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException {

	}


}
