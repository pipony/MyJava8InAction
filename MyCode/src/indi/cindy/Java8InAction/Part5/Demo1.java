package indi.cindy.Java8InAction.Part5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
5.5节的题目做一下（给定一些交易员的资料，做相关查询操作）
 */
public class Demo1 {
    //1.找出2011年发生的所有交易，并按交易额排序（从低到高）
    public static List<Transaction> fun1(List<Transaction> transactions){
        return transactions.stream()
                .filter(transaction -> transaction.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }
    //2.交易员都在哪些不同的城市工作过？
    public static List<String> fun2(List<Transaction> transactions){
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }
    //3.查找所有来自于剑桥的交易员，并按姓名排序
    public static List<Trader> fun3(List<Transaction> transactions){
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }
    //4.返回所有交易员的姓名字符串，按字母顺序排序
    public static String fun4(List<Transaction> transactions){
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                //.reduce("",(n1, n2) -> n1 + n2);   //这个还比较效率低
                .collect(Collectors.joining());   //joining内部会用到StringBuilder
    }
    //5.有没有交易员是在米兰工作的？
    public static boolean fun5(List<Transaction> transactions){
        return transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    }
    //6.打印生活在剑桥的交易员的所有交易额，注意是直接打印
    public static void fun6(List<Transaction> transactions){
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .forEach(System.out::println);
    }
    //7.所有交易中，最高的交易额是多少？
    public static Optional<Integer> fun7(List<Transaction> transactions){
        return transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(Integer::max);
    }
    //8.找到交易额最小的交易
    public static Optional<Transaction> fun8(List<Transaction> transactions){
        return transactions.stream()
//                .reduce((t1, t2) ->
////                        t1.getValue() < t2.getValue() ? t1 : t2);
                 .min(Comparator.comparing(Transaction::getValue));  //更好
    }

    public static void main(String[] args) {
        //初始化交易员和交易列表
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //开始工作
        //List<Transaction> transactions1 = Demo1.fun1(transactions);
        //List<String> string1 = Demo1.fun2(transactions);
        //List<Trader> traders1 = Demo1.fun3(transactions);
        //String string1 = Demo1.fun4(transactions);
        //boolean string1 = Demo1.fun5(transactions);
        //Demo1.fun6(transactions);
        //Optional<Integer> string1 = Demo1.fun7(transactions);
        //System.out.println(Demo1.fun8(transactions).toString());

//      Demo1.printTransaction(transactions1);
//      Demo1.printTrader(traders1);
//      Demo1.printString(string1);
//        System.out.println(string1);
    }
    public static void printTrader(List<Trader> traders){
        for(Trader trader: traders) System.out.println(trader.toString());
    }
    public static void printTransaction(List<Transaction> transactions){
        for(Transaction transaction: transactions) System.out.println(transaction.toString());
    }
    public static void printString(List<String> strings){
        for(String string: strings) System.out.println(string);
    }
}
