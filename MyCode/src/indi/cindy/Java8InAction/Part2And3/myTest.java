package indi.cindy.Java8InAction.Part2And3;

import java.util.ArrayList;
import java.util.List;

public class myTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        for(String s: list){
            s = "123";
        }
        Demo3.printList(list);
        String tmp = "abc";
        tmp =  tmp.concat("aaa");
        System.out.println(tmp);
    }
}
