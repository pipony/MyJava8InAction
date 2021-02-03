package indi.cindy.Java8InAction.Part2And3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/*
3.4节，测试Java8 API已有的三个函数式接口的使用
1.Predicate{boolean test(T t);}
2.Consumer{void accept(T t);}
3.Function{R apply(T t)}
 */
public class Demo3 {

    public static <T> List<Boolean> filter(List<T> list, Predicate<T> pr){
        //1.利用Predicate实现的“框架”
        List<Boolean> res = new ArrayList<>();
        for(T t: list){
            if(pr.test(t)) res.add(true);
            else res.add(false);
        }
        return res;
    }

    public static <T> void forEach(List<T> list, Consumer<T> cs){
        //2.利用Consumer实现的“框架”
        for(T t: list){
            cs.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T,R> fun){
        //3.利用Function实现的“框架”
        List<R> res = new ArrayList<R>();
        for(T t: list){
            res.add(fun.apply(t));
        }
        return res;
    }

    public static void main(String[] args) {
        //初始化T为String的List
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("ghi");
        //1.测试Predicate：返回输入的字符串是否为空的结果
        List<Boolean> res1 = new ArrayList<>();
        res1 = Demo3.filter(list, (String str) -> !str.isEmpty());
        //2.测试Consumer:输出每个字符串
        Demo3.forEach(list, (String str) -> { System.out.print(str+" ");});
        System.out.println();
        //3.测试Function：获得每个字符串的长度
        List<Integer> res3 = new ArrayList<>();
        res3 = Demo3.map(list, (String str) -> str.length());
        //输出看看，第2个测试的输出已在上面实现...
        printList(res1);
        System.out.println("");
        printList(res3);
    }
    public static <T> void printList(List<T> list){
        for (T t: list){
            System.out.print(t+" ");
        }
    }

}
