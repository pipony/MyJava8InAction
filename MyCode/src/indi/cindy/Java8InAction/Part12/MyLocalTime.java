package indi.cindy.Java8InAction.Part12;

import java.time.LocalTime;

/*
学习LocalTime
 */
public class MyLocalTime {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.of(11, 12, 13);
        System.out.println("hour: "+localTime.getHour());
        System.out.println("minite: "+localTime.getMinute());
        System.out.println("second: "+localTime.getSecond());
        System.out.println();
        LocalTime localTime1 = LocalTime.parse("11:12:13");
        System.out.println("hour: "+localTime.getHour());
        System.out.println("minite: "+localTime.getMinute());
        System.out.println("second: "+localTime.getSecond());

    }
}
