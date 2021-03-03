package indi.cindy.Java8InAction.Part10;

import org.junit.Test;

import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/*
复现10.4.3节的测试10.3：从Properties中读取正数，非正数都转换为0
 */
public class Demo2 {
    public static int readDuration(Properties pro, String name){
        return Optional.ofNullable(pro.getProperty(name))
                .flatMap(Demo2::stringToInt)
                .filter(i -> i>0)
                .orElse(0);
    }
    public static Optional<Integer> stringToInt(String s){
        //假设s必须为非null
        Optional<Integer> res;
        try{
            res = Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e){
            res = Optional.empty();
        }
        return res;
    }

    @Test
    public void testFun(){
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");
        assertEquals(5, readDuration(props, "a"));
        assertEquals(0, readDuration(props, "b"));
        assertEquals(0, readDuration(props, "c"));
        assertEquals(0, readDuration(props, "d"));
    }

}
