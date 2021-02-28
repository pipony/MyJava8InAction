package indi.cindy.Java8InAction.Part6;

import indi.cindy.Java8InAction.Part4.Dish;

import java.util.*;

import static java.util.stream.Collectors.*;

/*
学习6.3节 流的收集器分组功能 groupingBy
0.普通分组
1.多级分组
2.按子组收集数据
 */
public class Demo2 {
    public static Map<Boolean, List<Dish>> fun1(List<Dish> menu){
        //普通分组，按照是否为素食来分
        return menu.stream().collect(groupingBy(Dish::isVegetarian));
    }
    public static enum sortTable {Diet, Normal, Fat};
    public static Map<sortTable, List<Dish>> fun2(List<Dish> menu){
        //普通分组，按照卡路里程度分，自定义
        return menu.stream().collect(groupingBy(
                dish->{
                    if(dish.getCalories()<=400) return sortTable.Diet;
                    else if(dish.getCalories()<=700) return sortTable.Normal;
                    else return sortTable.Fat;
                }
        ));
    }
    public static Map<Dish.Type, Map<sortTable, List<Dish>>> fun3(List<Dish> menu){
        //多级分组，先按照菜肴类别分，再按照卡路里程度分
        return menu.stream().collect(groupingBy(Dish::getType, groupingBy(
                dish->{
                    if(dish.getCalories()<=400) return sortTable.Diet;
                    else if(dish.getCalories()<=700) return sortTable.Normal;
                    else return sortTable.Fat;
                }
        )));
    }
    public static Map<Dish.Type, Long> fun4(List<Dish> menu){
        //按子组收集数据1：分别获得每类菜肴个数
        return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }
    public static Map<Dish.Type, Optional<Dish>> fun5(List<Dish> menu){
        //按子组收集数据2：分别获得每类菜肴卡路里最高的菜
        return menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories))));
    }
    public static Map<Dish.Type, Set<sortTable>> fun6(List<Dish> menu){
        //按子组收集数据3：了解对于每种类型的菜肴，共有几类卡路里程度（利用mapping）
        return menu.stream().collect(groupingBy(Dish::getType,
                mapping(dish -> {
                    if(dish.getCalories()<=400) return sortTable.Diet;
                    else if(dish.getCalories()<=700) return sortTable.Normal;
                    else return sortTable.Fat;
                }, toSet())
                ));
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
        //普通分组
        printMap1(fun1(menu));
        printMap2(fun2(menu));
        System.out.println(fun3(menu));
        System.out.println();
        System.out.println(fun4(menu));
        System.out.println();
        System.out.println(fun5(menu));
        System.out.println();
        System.out.println(fun6(menu));

    }

    public static void printMap1(Map<Boolean, List<Dish>> map){
        //输出格式1的map值
        for (Boolean x : map.keySet()){
            System.out.print(x+": ");
            for (Dish dish: map.get(x)){
                System.out.print(dish.getName()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printMap2(Map<sortTable, List<Dish>> map){
        //输出格式1的map值
        for (sortTable x : map.keySet()){
            System.out.print(x+": ");
            for (Dish dish: map.get(x)){
                System.out.print(dish.getName()+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
