package indi.cindy.Java8InAction.Part12;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

/*
学习Duration和Period
 */
public class MyDurationAndPeriod {

    public static void main(String[] args) {
        LocalTime localTime1 = LocalTime.of(11, 37, 50);
        LocalTime localTime2 = LocalTime.of(11, 37, 1);
        Duration duration = Duration.between(localTime1, localTime2);
        System.out.println(duration);

        LocalDate localDate1 = LocalDate.of(2021, 3, 2);
        LocalDate localDate2 = LocalDate.of(2021, 3, 1);
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period);

        Duration duration1 = Duration.ofSeconds(3);
        Period period1 = Period.ofMonths(3);


    }
}
