package indi.cindy.Java8InAction.Part10;

import java.util.Optional;

/*
10.2节的例子，Car类
 */
public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public Car(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
