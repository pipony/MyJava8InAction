package indi.cindy.Java8InAction.Part7;

import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/*
复现7.2.1的分支/合并代码，即创建RecursiveTask<R>的子类
功能：对long[]数组求和
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private long[] numbers;
    private int start;
    private int end;
    private int threshold = 10000;

    public ForkJoinSumCalculator(long[] numbers){
        //构造函数1
        this.numbers = numbers;
        start = 0;
        end = numbers.length - 1;
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end){
        //构造函数2
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        //分情况讨论，若大小够小，顺序执行；若够大，拆分成两个任务执行
        int length = end - start;
        //顺序执行
        if(length <= threshold) return SequentiallyRun(numbers, start, end);
        //拆分并行执行
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2 + 1, end);
        Long rightRes = rightTask.compute();
        Long leftRes = leftTask.join();
        return leftRes +  rightRes;
    }

    public static Long SequentiallyRun(long[] numbers, int start, int end){
        long res = 0L;
        for (int i = start; i <= end; i++){
            res += numbers[i];
        }
        return res;
    }

    public static void main(String[] args) {
        //构建一个long[]数组
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
        Long res = new ForkJoinSumCalculator(numbers).compute();
        System.out.println(res);
        //来个普通模式验证一下算法的正确性
        Long res1 = 0L;
        for(int i = 0; i < numbers.length; i++){
            res1 += numbers[i];
        }
        System.out.println(res1);
    }
}
