package indi.cindy.Java8InAction.Part7;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/*
复现7.3.2中代码清单7-6代码 WordCounterSpliterator
功能：用于正确拆分WordCounter的流，以支持并行计算的正确性
 */
public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        if (string.length() - currentChar > 0) return true;
        else return false;
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) return null;
        //开始拆分，从当前的中点出发，往后寻找第一个可拆分的点
        for (int splitPos = currentChar + currentSize / 2; splitPos < string.length(); splitPos++){
            //测试当前点是否满足拆分条件，即让拆分点前进直到遇到第一个空格而后开始拆分
            if (Character.isWhitespace(string.charAt(splitPos))){
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

    //测试一下
    public static void main(String[] args) {
        //此时就可以利用拆分器WordCounterSpliterator来指定流的断开方式了
        String s = " Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " ché la dritta via era smarrita ";
        Spliterator<Character> spliterator = new WordCounterSpliterator(s);
        Stream<Character> stream = StreamSupport.stream(spliterator, true); //第二个参数表示开启并行
        //调用之前构造的WordCounter的countWords函数
        int count = WordCounter.countWords(stream);
        System.out.println("parallel: "+count);
    }
}
