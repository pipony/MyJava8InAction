package indi.cindy.Java8InAction.Part10;

import java.util.Optional;

/*
10.2节的例子，Person类
 */
public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public Person(Optional<Car> car) {
        this.car = car;
    }
}
