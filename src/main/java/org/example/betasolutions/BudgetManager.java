package org.example.betasolutions;

public class BudgetManager {


    public int calculateCost(int hours){
        int pricePerHour = 200;
        int cost = hours * pricePerHour;
        return cost;
    }
}
