package indi.cindy.Java8InAction.Part7;
/*
复现7.3.2中代码清单7-4代码：一个迭代式（而非递归）字数统计方法
给定一个包含若干次的字符串，返回其中包含的单词数
 */
public class Demo1 {
    public static int wordCount(String string){
        //我自己写的，比较冗余。。。
        int len = string.length();
        int count = 0;
        boolean flag = true; //表示前面的char是空格
        for(int i = 0; i < len; i++){
            char c = string.charAt(i);
            if(Character.isWhitespace(c)&&flag) continue;
            else if(Character.isWhitespace(c)){
                flag = true;
            }
            else if(!Character.isWhitespace(c)&&flag){
                count++;
                flag = false;
            }
            else continue;
        }
        return count;
    }
    public static int wordCount1(String string){
        //书中给出的
        int count = 0;
        boolean flag = true;
        for(char c: string.toCharArray()){
            if(Character.isWhitespace(c)) flag = true;
            else{
                if(flag == true) count++;
                flag = false;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = " Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " ché la dritta via era smarrita ";
        System.out.println(wordCount(s));
        System.out.println(wordCount1(s));
    }
}
