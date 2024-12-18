package org.example.betasolutions;

public class BudgetManager {


    int calculateCost(int hours){
        int pricePerHour = 150;
        int cost = hours * pricePerHour;
        return cost;
    }
}
