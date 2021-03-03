package indi.cindy.Java8InAction.Part12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/*
复现12.2.2中java.time.format学习内容
 */
public class MyTimeFormat {
    //创建并使用format
    public static void fun1(){
        //创建format
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //新建一个LocalDate
        LocalDate localDate = LocalDate.of(2021, 3, 2);
        //利用format来按格式转换为字符串
        String dateString = localDate.format(f1);
        //再根据format以及转换后的字符串新建LocalDate对象
        LocalDate localDate1 = LocalDate.parse(dateString, f1);
        //输出看看
        System.out.println(dateString);
        System.out.println(localDate1);
    }
    public static void fun2(){
        //在fun1()基础上添加本地化（意大利）功能
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate localDate = LocalDate.of(2021, 3, 2);
        String dateString = localDate.format(f1);
        LocalDate localDate1 = LocalDate.parse(dateString, f1);
        System.out.println(dateString);
        System.out.println(localDate1);
    }
    public static void fun3(){
        //通过DateTimeFormatterBuiler来自定义格式器
        DateTimeFormatter f = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        //使用一下
        LocalDate localDate = LocalDate.of(2021, 3, 2);
        String dateString = localDate.format(f);
        System.out.println(dateString);
    }

    public static void main(String[] args) {
        fun1();
        System.out.println();
        fun2();
        System.out.println();
        fun3();
    }
}
