package indi.cindy.Java8InAction.Part2And3;

import java.io.*;

/*
3.3节的例子：利用lambda和行为参数化更简单的实现文件处理功能，支持不同的文件操作（如读文件前一行和读前两行）
需要：1.函数式接口，里面包含仅一个抽象方法
2.实现文件处理的类，接收FileProcessInterface为输入参数
 */
public class Demo2 {
    public static String fileProcess(FileProcessInterface fpi) throws Exception {  //实现类
        try {
            BufferedReader br = new BufferedReader(new FileReader("d://1.txt"));
            //执行输入的行为fpi的操作
            return fpi.work(br);
        } finally {

        }
    }
    public static void main(String[] args) throws Exception {
        //功能一：读取文件前一行
        String res1 = Demo2.fileProcess((BufferedReader br) -> br.readLine());
        //功能二：读取文件前两行
        String res2 = Demo2.fileProcess((BufferedReader br) -> br.readLine() + br.readLine());
        //输出看看
        System.out.println(res1);
        System.out.println("---------");
        System.out.println(res2);
    }
}
