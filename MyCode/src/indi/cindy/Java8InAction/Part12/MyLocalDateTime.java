package indi.cindy.Java8InAction.Part12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
学习LocalDateTime
 */
public class MyLocalDateTime {
    public static void main(String[] args) {
        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 3, 2, 11, 12, 13);
        LocalDate localDate1 = localDateTime1.toLocalDate();
        LocalTime localTime1 = localDateTime1.toLocalTime();
        System.out.println(localDate1);
        System.out.println(localTime1);
        LocalDateTime localDateTime2 = localDate1.atTime(localTime1);
        LocalDateTime localDateTime3 = localTime1.atDate(localDate1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
    }
}
