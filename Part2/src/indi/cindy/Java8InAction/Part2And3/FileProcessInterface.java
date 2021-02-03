package indi.cindy.Java8InAction.Part2;

import java.io.BufferedReader;
import java.io.IOException;

/*
函数式接口，使用@FunctionalInterface注解
 */
@FunctionalInterface
public interface FileProcessInterface{
    public String work(BufferedReader br) throws IOException;
}
