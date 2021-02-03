package indi.cindy.Java8InAction.Part2;
import java.util.function.IntPredicate;

/*
3.4节，针对原始类型数据的函数式接口进行简单示例。以针对int的Predicate为例。
 */
public class Demo4 {
    public static boolean lt10(int x, IntPredicate intPre){
        return intPre.test(x);
    }
    public static void main(String[] args) {
        //利用IntPredicate作判断框架：判断x是否大于10
        int x = 5;
        boolean res = lt10(x, (int value) -> value>10);
        System.out.println(res);
    }
}
