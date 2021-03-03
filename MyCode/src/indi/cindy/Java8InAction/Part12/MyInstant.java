package indi.cindy.Java8InAction.Part12;

import java.time.Instant;

/*
学习Instant
 */
public class MyInstant {
    public static void main(String[] args) {
        //表示从1970-01-01 00:00:00开始计时后的时间
        Instant instant = Instant.ofEpochSecond(3);
        Instant instant1 = Instant.ofEpochSecond(4, -1000000000);
        System.out.println(instant);
        System.out.println(instant1);
    }
}
