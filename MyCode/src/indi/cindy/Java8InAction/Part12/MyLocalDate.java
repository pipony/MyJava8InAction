package indi.cindy.Java8InAction.Part12;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

/*
学习LocalData
 */
public class MyLocalDate {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2021, 3, 2);
        System.out.println("year: "+localDate.getYear());
        System.out.println("month: "+localDate.getMonth());
        System.out.println("day: "+localDate.getDayOfMonth());
        System.out.println("xingqi: "+localDate.getDayOfWeek());
        System.out.println("now "+LocalDate.now());
        System.out.println("year1: "+ localDate.get(ChronoField.YEAR));
        System.out.println();
        LocalDate localDate1 = LocalDate.parse("2021-03-02");
        System.out.println("year: "+localDate.getYear());
        System.out.println("month: "+localDate.getMonth());
        System.out.println("day: "+localDate.getDayOfMonth());


    }
}
