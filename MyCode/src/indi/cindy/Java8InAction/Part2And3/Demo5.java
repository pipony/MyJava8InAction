package indi.cindy.Java8InAction.Part2And3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
3.7节，利用传递代码、方法引用、lambda最大程度的简化代码
例子：对苹果列表进行排序（按照重量升序排序）
 */
public class Demo5 {
    public static void main(String[] args) {
        //构造苹果列表
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple(50, "green"));
        appleList.add(new Apple(10, "red"));
        appleList.add(new Apple(30, "black"));
        //进行排序,只需要简单的一行！
        appleList.sort(Comparator.comparing(Apple::getWeight));
        //输出看看
        for(Apple apple: appleList){
            System.out.println(apple.getWeight());
        }
    }
}
