package indi.cindy.Java8InAction.Part2;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight()>150 ? true: false;
    }
}
