package indi.cindy.Java8InAction.Part5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
复现5.6.3节，生成勾股数三元组(a,b,c)，满足a^2+b^2=c^2
我们只找a=1~100的情况
 */
public class Demo2 {
    public static void main(String[] args) {
        //方法一
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1,100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt( a*a + b*b ) % 1 == 0)
                        .mapToObj(b -> new int[] {a, b, (int)Math.sqrt( a*a + b*b )})
                );
        //方法二
        Stream<double[]> pythagoreanTriples1 =
                IntStream.rangeClosed(1,100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(b -> new double[] {a, b, Math.sqrt( a*a + b*b )})
                                        .filter(t -> t[2] % 1 == 0)
                        );
        //分别输出结果
        pythagoreanTriples.limit(5)
                .forEach(t -> System.out.println("("+t[0]+","+t[1]+","+t[2]+")"));
        System.out.println("-----------");
        pythagoreanTriples1.limit(5)
                .forEach(t -> System.out.println("("+t[0]+","+t[1]+","+t[2]+")"));


    }
}
