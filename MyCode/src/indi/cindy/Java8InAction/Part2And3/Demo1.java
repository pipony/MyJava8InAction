package indi.cindy.Java8InAction.Part2And3;

import java.util.ArrayList;
import java.util.List;

/*
2.3代码，比较啰嗦地实现行为参数化：用谓词筛选苹果
 */
public class Demo1 {
    public static List<Apple> appleFilter(List<Apple> list, ApplePredicate ap){  //筛选类
        List<Apple> res = new ArrayList<>();  //存储筛选出的结果列表
        for(Apple apple: list){
            if(ap.test(apple)) res.add(apple);
        }
        return res;
    }
    public static void main(String[] args) {
        //构造苹果列表
        List<Apple> list = new ArrayList<>();
        list.add(new Apple(200, "green"));
        list.add(new Apple(100, "green"));
        list.add(new Apple(200, "red"));
        //功能一：筛选出heavy的苹果列表，并输出
        Demo1.printList(Demo1.appleFilter(list, new AppleHeavyWeightPredicate()));
        System.out.println("----------");
        //功能二：筛选出绿苹果列表，并输出
        Demo1.printList(Demo1.appleFilter(list, new AppleGreenColorPredicate()));
    }
    public static void printList(List<Apple> list){
        for(Apple apple: list){
            System.out.println(apple.getWeight()+" "+apple.getColor());
        }
    }
}
