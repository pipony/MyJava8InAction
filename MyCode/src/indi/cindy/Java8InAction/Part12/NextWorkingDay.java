package indi.cindy.Java8InAction.Part12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/*
复现测验12.2 实现一个定制的 TemporalAdjuster
实现功能：给定一个时间，选择下一个工作日（若明天是周六/日，则到周一）
 */
public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        //获得输入日期是星期几
        DayOfWeek day = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        //初始化稍后加的时间
        int addDay = 1;
        //考虑两种特殊情况以修改addDay
        if (day == DayOfWeek.FRIDAY) addDay = 3;
        else if(day == DayOfWeek.SATURDAY) addDay = 2;
        //得到下一个工作日
        return temporal.plus(addDay, ChronoUnit.DAYS);
    }

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2021, 3, 2);
        //使用自定义的TemporalAdjuster
        LocalDate newLocalDate = localDate.with(new NextWorkingDay());
        System.out.println(newLocalDate);
    }
}
