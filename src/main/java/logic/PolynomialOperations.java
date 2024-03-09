package logic;

import dataModels.Polynomial;
import java.util.Map;

public class PolynomialOperations {
    
    public static Polynomial add( Polynomial polynomial1, Polynomial polynomial2){
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Double> term : polynomial1.getPolynomialTerms().entrySet()){
            result.getPolynomialTerms().put(term.getKey(), term.getValue());
        }

        for (Map.Entry<Integer, Double> term : polynomial2.getPolynomialTerms().entrySet()){
            if(result.getPolynomialTerms().containsKey(term.getKey())){
                result.getPolynomialTerms().put(term.getKey(), result.getPolynomialTerms().get(term.getKey()) + term.getValue());
            }else{
                result.getPolynomialTerms().put(term.getKey(), term.getValue());
            }
        }

        return result;
    }

    public static Polynomial subtract(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Double> term : polynomial1.getPolynomialTerms().entrySet()){
            result.getPolynomialTerms().put(term.getKey(), term.getValue());
        }

        for (Map.Entry<Integer, Double> term : polynomial2.getPolynomialTerms().entrySet()){
            if(result.getPolynomialTerms().containsKey(term.getKey())){
                double coefficient = result.getPolynomialTerms().get(term.getKey()) - term.getValue();
                if(coefficient != 0) {
                    result.getPolynomialTerms().put(term.getKey(), coefficient);
                }else{
                    result.getPolynomialTerms().remove(term.getKey());
                }
            }else{
                result.getPolynomialTerms().put(term.getKey(), -term.getValue());
            }
        }

        return result;
    }

    public static Polynomial multiply(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Double> term1 : polynomial1.getPolynomialTerms().entrySet()){
            for(Map.Entry<Integer, Double> term2 : polynomial2.getPolynomialTerms().entrySet()){
                int exponent = term1.getKey() + term2.getKey();
                double coefficient = term1.getValue() * term2.getValue();
                if(result.getPolynomialTerms().containsKey(exponent)){
                    result.getPolynomialTerms().put(exponent, result.getPolynomialTerms().get(exponent) + coefficient);
                }else{
                    result.getPolynomialTerms().put(exponent, coefficient);
                }
            }
        }

        return result;
    }

    public static Polynomial divide(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial result = new Polynomial();

        while(polynomial1.getPolynomialTerms().size() >= polynomial2.getPolynomialTerms().size()){

            Map.Entry<Integer, Double> term1 = polynomial1.getPolynomialTerms().firstEntry();
            Map.Entry<Integer, Double> term2 = polynomial2.getPolynomialTerms().firstEntry();

            int exponent = term1.getKey() - term2.getKey();
            double coefficient = term1.getValue() / term2.getValue();

            result.getPolynomialTerms().put(exponent, coefficient);

            polynomial1 = subtract(polynomial1, multiply(result, polynomial2));

        }

        return result;
    }

    public static Polynomial differentiate(Polynomial polynomial){
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Double> term : polynomial.getPolynomialTerms().entrySet()){
            if(term.getKey() != 0){
                int exponent = term.getKey() - 1;

                if (result.getPolynomialTerms().containsKey(exponent)) {
                    result.getPolynomialTerms().put(exponent, result.getPolynomialTerms().get(exponent) + term.getKey() * term.getValue());

                }else {
                    result.getPolynomialTerms().put(exponent, term.getKey() * term.getValue());
                }
            }else {
                result.getPolynomialTerms().remove(term.getKey());
            }
        }

        return result;
    }

    public static Polynomial integrate(Polynomial polynomial){
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Double> term : polynomial.getPolynomialTerms().entrySet()){
            int exponent = term.getKey() + 1;

            if(result.getPolynomialTerms().containsKey(exponent)) {
                result.getPolynomialTerms().put(exponent, result.getPolynomialTerms().get(exponent) + term.getValue() / (exponent));
            }else {
                result.getPolynomialTerms().put(exponent, term.getValue() / (exponent));
            }
        }

        return result;
    }

}
