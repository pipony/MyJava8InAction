package indi.cindy.Java8InAction.Part6;

import indi.cindy.Java8InAction.Part4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/*
学习6.4节 流的收集器分区功能 partitioningBy
与分组相比，特点是只有true和false两个分区，因此需要一个谓词作为分类函数
另外，实现一个功能：给定n，返回n以内的质数和非质数
 */
public class Demo3 {

    public static Map<Boolean, List<Dish>> fun1(List<Dish> menu){
        //分区简单示例，以是否是素食来区分
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }
    public static boolean isPrime(int x){
        //判断x是否为质数
        return IntStream.rangeClosed(2, (int) Math.sqrt(x))
                .noneMatch(s -> x % s == 0);
    }
    public static Map<Boolean, List<Integer>> filterPrime(int n){
        //将n以内的整数按照是否为质数区分开来
        return IntStream.rangeClosed(2,n).boxed()
        .collect(partitioningBy(s -> isPrime(s)));
    }

    public static void main(String[] args) {
        //直接copy前面章节的例子menu
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        //System.out.println(fun1(menu));
        System.out.println(filterPrime(6));
    }
}
