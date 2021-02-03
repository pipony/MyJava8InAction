package indi.cindy.Java8InAction.Part2And3;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor()) ? true: false;
    }
}
