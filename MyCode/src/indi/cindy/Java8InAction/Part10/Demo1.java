package indi.cindy.Java8InAction.Part10;

import java.util.Optional;

/*
分别复现书中几种函数：
复现10.3.3的代码清单10-5：使用 Optional 获取 car 的 Insurance 名称
复现10.3.5的测验10.1代码：组合两个 Optional 对象实现找到最便宜的保险公司功能（给定Person和Car）
复现10.3.6的：检查保险公司的名称是否为“xxx”
 */
public class Demo1 {
    //注意，输入参数person也可能是null，所以也用Optional<Person>类型表示
    public static String getCarInsuranceName(Optional<Person> person){
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
    public static Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person,
                                                                    Optional<Car> car){
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }
    public static Insurance findCheapestInsurance(Person person, Car car){
        //功能暂不实现，因为不重要，直接返回某个Insurance
        return new Insurance("cheapestInsuranceName");
    }
    public static void checkInsuranceName(Optional<Insurance> insurance){
        //检查保险公司的名称是否为“xxx”
        insurance.filter(i -> i.getName().equals("xxx"))
                .ifPresent(x -> System.out.println("ok"));
    }


    //测试一下
    public static void main(String[] args) {
        //新建一个完整的例子
        Optional<Insurance> insurance = Optional.ofNullable(new Insurance("insuranceName"));
        Optional<Car> car = Optional.ofNullable(new Car(insurance));
        Optional<Person> person = Optional.ofNullable(new Person(car));
        //开始执行getCarInsuranceName
        System.out.println(getCarInsuranceName(person));
        //开始执行nullSafeFindCheapestInsurance
        Optional<Insurance> insurance1 = nullSafeFindCheapestInsurance(person, car);
        String cheapestInsuranceName = insurance1.map(Insurance::getName).orElse("Unknown");
        System.out.println(cheapestInsuranceName);
        //开始执行checkInsuranceName
        Optional<Insurance> insurance2 = Optional.ofNullable(new Insurance("xxx"));
        checkInsuranceName(insurance2);
        Optional<Insurance> insurance3 = Optional.ofNullable(new Insurance("xxx1"));
        checkInsuranceName(insurance3); //没输出
        Optional<Insurance> insurance4 = Optional.ofNullable(null);
        checkInsuranceName(insurance4); //没输出
    }
}
