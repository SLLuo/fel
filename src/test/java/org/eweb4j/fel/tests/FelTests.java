package org.eweb4j.fel.tests;

import org.eweb4j.fel.FelEngine;
import org.eweb4j.fel.FelEngineImpl;
import org.eweb4j.fel.context.FelContext;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

/**
 * Created by sheng on 2018/1/24.
 */
public class FelTests {

    private static FelEngine engine;

    @BeforeClass
    public static void before() {
        engine = new FelEngineImpl();
    }

    @Test
    public void test1() {
        FelContext ctx = engine.getContext();
        ctx.set("单价", 5000);
        ctx.set("数量", 12);
        ctx.set("运费", 7500);
        Object result = engine.eval("单价*数量+运费");
        System.out.println(result);
    }

    @Test
    public void test2() {
        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();
        Foo foo = new Foo();
        ctx.set("foo", foo);
        Map<String, String> m = new HashMap<String, String>();
        m.put("ElName", "fel");
        ctx.set("m", m);

        //调用foo.getSize()方法。
        Object result = fel.eval("foo.size");
        System.out.println(result);

        //调用foo.isSample()方法。
        result = fel.eval("foo.sample");
        System.out.println(result);

        //foo没有name、getName、isName方法
        //foo.name会调用foo.get("name")方法。
        result = fel.eval("foo.name");
        System.out.println(result);

        //m.ElName会调用m.get("ElName");
        result = fel.eval("m.ElName");
        System.out.println(result);
    }

    @Test
    public void test3() {
        FelContext ctx = engine.getContext();

        //数组
        int[] intArray = {1, 2, 3};
        ctx.set("intArray", intArray);
        //获取intArray[0]
        String exp = "intArray[0]";
        System.out.println(exp + "->" + engine.eval(exp));

        //List
        List<Integer> list = Arrays.asList(1, 2, 3);
        ctx.set("list", list);
        //获取list.get(0)
        exp = "list[0]";
        System.out.println(exp + "->" + engine.eval(exp));

        //集合
        Collection<String> coll = Arrays.asList("a", "b", "c");
        ctx.set("coll", coll);
        //获取集合最前面的元素。执行结果为"a"
        exp = "coll[0]";
        System.out.println(exp + "->" + engine.eval(exp));

        //迭代器
        Iterator<String> iterator = coll.iterator();
        ctx.set("iterator", iterator);
        //获取迭代器最前面的元素。执行结果为"a"
        exp = "iterator[0]";
        System.out.println(exp + "->" + engine.eval(exp));

        //Map
        Map<String, String> m = new HashMap<String, String>();
        m.put("name", "HashMap");
        ctx.set("map", m);
        exp = "map.name";
        System.out.println(exp + "->" + engine.eval(exp));

        //多维数组
        int[][] intArrays = {{11, 12}, {21, 22}};
        ctx.set("intArrays", intArrays);
        exp = "intArrays[0][0]";
        System.out.println(exp + "->" + engine.eval(exp));

        //多维综合体，支持数组、集合的任意组合。
        List<int[]> listArray = new ArrayList<int[]>();
        listArray.add(new int[]{1, 2, 3});
        listArray.add(new int[]{4, 5, 6});
        ctx.set("listArray", listArray);
        exp = "listArray[0][0]";
        System.out.println(exp + "->" + engine.eval(exp));
    }

    private long incr = 0;

    public long incr() {
        return incr++;
    }

    public String str(int num, int len) {
        return "" + num;
    }

    public String str(long num, int len) {
        return "" + num;
    }

    @Test
    public void test4() {
        FelContext ctx = engine.getContext();
        ctx.set("out", System.out);
        engine.eval("out.println('Hello Everybody'.substring(6))");
        ctx.set("x", new FelTests());
        System.out.println(engine.eval("'test_' + x.incr()"));
        System.out.println(engine.eval("'test_' + x.incr()"));
        System.out.println(engine.eval("'test_' + x.incr()"));
        System.out.println(engine.eval("'test_' + x.incr()"));
        System.out.println(engine.eval("'test_' + x.incr()"));
        System.out.println(engine.eval("'test_' + x.str(1,10)"));
        System.out.println(engine.eval("'test_' + x.str(x.incr(),10)"));
    }

}
