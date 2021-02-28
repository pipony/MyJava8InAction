package indi.cindy.Java8InAction.Part7;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
复现7.3.2中代码清单7-5代码：用来在遍历 Character 流时计数的类
 */
public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    //遍历字符函数，定义了何时定义一个新的WordCounter类
    public WordCounter accumulate(Character c){
        if(Character.isWhitespace(c)){
            return lastSpace ?
                    this : new WordCounter(counter, true);
        }
        else{
            return lastSpace ?
                    new WordCounter(counter + 1, false) : this;
            //特别注意，上面的counter+1不能是++counter，因为其实并不改变this.counter
            //若++counter，则this也加，新的WordCounter也加，那么结果就会错！！！！
        }
    }

    //将两个WordCounter类结果combine起来
    public WordCounter combine(WordCounter wc){
        return new WordCounter(this.counter + wc.counter, wc.lastSpace);
    }

    //获得count
    public int getCounter() {
        return counter;
    }

    //最终的获得word数的函数
    public static int countWords(Stream<Character> stream){
        WordCounter wc = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);
        return wc.getCounter();
    }

    //测试一下
    public static void main(String[] args) {
        String s = " Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " ché la dritta via era smarrita ";
        //由于没有默认的charStream，于是需要从IntStream来转换
        Stream<Character> stream = IntStream.range(0, s.length())
                .mapToObj(s::charAt);
        System.out.println("not parallel: "+WordCounter.countWords(stream));
        //上述顺序的Stream结果是正确的。而当用并行来做时，结果是错的
        //注意，为了再来一次，需要再流化一次s，因为上面的流已经报废了
        Stream<Character> stream1 = IntStream.range(0, s.length())
                .mapToObj(s::charAt);
        System.out.println("parallel: "+WordCounter.countWords(stream1.parallel()));
    }
}
