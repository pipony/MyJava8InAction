package indi.cindy.Java8InAction.Part6;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/*
复现6.6节自定义的质数划分收集器 PrimeNumbersCollector
功能：给定整数n，分别划分出n以内质数和非质数（两个List）
分别实现接口包含的五个函数
 */
public class PrimeNumbersCollector implements Collector<Integer,
        Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    //takeWhile函数，用以实现给定一组有序列表和一个条件判断谓词，返回满足谓词的最长前缀
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i = 0;
        for (A a: list){
            if(!p.test(a)) return list.subList(0, i);
            i++;
        }
        return list;
    }
    //优化实现isPrime函数
    public static Boolean isPrime(List<Integer> list, Integer candidate){
        //仅考虑小于sqrt(candidate)的质数作为除数
        int x = (int) Math.sqrt(candidate);
        return takeWhile(list, a -> a <= x)
                .stream()
                .noneMatch(s -> candidate % s == 0);
    }

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        //暂时不清楚这里的语法，，，，
        return () -> new HashMap<Boolean, List<Integer>>(){{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(isPrime(acc.get(true), candidate))
                    .add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        //注意，因为本算法本身是顺序的，因此无法使用到并行，也无法使用到combiner
        //但是为了完整，还是蛮实现在这
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) ->
               {map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
               return map1;};
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
    }
}
