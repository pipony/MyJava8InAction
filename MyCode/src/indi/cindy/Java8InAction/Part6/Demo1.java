package indi.cindy.Java8InAction.Part6;

import indi.cindy.Java8InAction.Part4.Dish;

import java.util.*;

import static java.util.stream.Collectors.*;

/*
学习6.2节，流收集器的规约和汇总功能
1.查找最大/最小值
2.汇总
 2.1 求和
 2.2 平均值
 2.3 汇总功能
3.连接字符串
 */
public class Demo1 {
    public static Optional<Dish> fun1(List<Dish> menu){
        //返回卡路里最大的菜
        //首先是要建立一个Comparator，指欲比较的指标
        Comparator<Dish> comparator = Comparator.comparing(Dish::getCalories);
        //调用自建的Comparator比较
        return menu.stream().collect(maxBy(comparator));
    }
    public static int fun2(List<Dish> menu){
        //返回所有菜的卡路里之和
        return menu.stream().collect(summingInt(Dish::getCalories));
    }
    public static double fun3(List<Dish> menu){
        //返回所有菜的卡路里平均值，注意返回类型是double
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }
    public static IntSummaryStatistics fun4(List<Dish> menu){
        //返回所有菜的汇总（分别是个数，热量总和，平均值，最大，最小值）
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }
    public static String fun5(List<Dish> menu){
        //返回所有菜名的连接字符串
        //直接连接
        //return menu.stream().map(Dish::getName).collect(joining());
        //添加一些分割符的链接
        return menu.stream().map(Dish::getName).collect(joining(","));
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
        //maxBy
        System.out.println(fun1(menu).get().getName());
        //summingInt
        System.out.println(fun2(menu));
        //averagingInt
        System.out.println(fun3(menu));
        //summarizingInt
        System.out.println(fun4(menu));
        //joining
        System.out.println(fun5(menu));
    }
}
