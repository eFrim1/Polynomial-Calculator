package dataModels;

import java.util.HashMap;

public class Polynomial {
    private HashMap<Integer, Double> polynomialTerms;
    public Polynomial(){};

    public HashMap<Integer, Double> getPolynomialTerms(){
        return polynomialTerms;
    }
}
