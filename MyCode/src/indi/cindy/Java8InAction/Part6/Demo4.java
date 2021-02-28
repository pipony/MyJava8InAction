package indi.cindy.Java8InAction.Part6;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/*
6.6节，调用自定义的PrimeNumbersCollector收集器来实现质数划分
 */
public class Demo4 {
    public static void main(String[] args) {
        int x = 6;
        Map<Boolean, List<Integer>> res = IntStream.rangeClosed(2,x)
                .boxed().collect(new PrimeNumbersCollector());
        System.out.println(res);
    }
}
