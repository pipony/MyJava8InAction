package indi.cindy.Java8InAction.Part4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/*
4.2节的例子，初见Stream，复现一下书中给的代码
基于的是一个菜单（List<Dish>），实现对菜单内容的一些筛选，最终返回筛选后的菜单内容
 */
public class Demo1 {
    public static List<String> filterMenu(List<Dish> menu){
        //筛选出菜单中1.高热量(calories>300)2.获取菜名3.只选择头三个菜4.组成新菜单
        return menu.stream().filter(d->d.getCalories()>0)
                .map(Dish::getName).limit(3).collect(toList());
    }

    public static void main(String[] args) {
        //直接copy书中代码
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
        List<String> res = Demo1.filterMenu(menu);
        Demo1.printList(res);

    }
    public static void printList(List<String> list){
        for (String str:list) System.out.println(str);
    }
}
