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

        if(result.getPolynomialTerms().isEmpty()){
            result.getPolynomialTerms().put(0, 0.0);
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

        if(result.getPolynomialTerms().isEmpty()){
            result.getPolynomialTerms().put(0, 0.0);
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

        if(result.getPolynomialTerms().isEmpty()){
            result.getPolynomialTerms().put(0, 0.0);
        }

        return result;
    }

    public static Polynomial divide(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial result = new Polynomial();
        Polynomial remainder = new Polynomial(polynomial1);


        while(remainder.getPolynomialTerms().firstEntry().getKey() >= polynomial2.getPolynomialTerms().firstEntry().getKey()){

            Map.Entry<Integer, Double> term1 = remainder.getPolynomialTerms().firstEntry();
            Map.Entry<Integer, Double> term2 = polynomial2.getPolynomialTerms().firstEntry();

            int exponent = term1.getKey() - term2.getKey();
            double coefficient = term1.getValue() / term2.getValue();

            if(term2.getValue() == 0){
                result.getPolynomialTerms().clear();
                result.getPolynomialTerms().put(0, 0.0);
                break;
            }

            result.getPolynomialTerms().put(exponent, coefficient);

            remainder = subtract(polynomial1, multiply(result, polynomial2));

        }
        polynomial1.getPolynomialTerms().clear();
        polynomial1.getPolynomialTerms().putAll(remainder.getPolynomialTerms());

        if(result.getPolynomialTerms().isEmpty()){
            result.getPolynomialTerms().put(0, 0.0);
        }

        return result;
    }

    public static Polynomial differentiate(Polynomial polynomial){
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Double> term : polynomial.getPolynomialTerms().entrySet()){
            if(term.getKey() != 0){
                int exponent = term.getKey() - 1;
                double coefficient = term.getKey() * term.getValue();

                if (result.getPolynomialTerms().containsKey(exponent)) {
                    result.getPolynomialTerms().put(exponent, result.getPolynomialTerms().get(exponent) + coefficient);

                }else {
                    result.getPolynomialTerms().put(exponent, coefficient);
                }
            }
        }

        if(result.getPolynomialTerms().isEmpty()){
            result.getPolynomialTerms().put(0, 0.0);
        }

        return result;
    }

    public static Polynomial integrate(Polynomial polynomial){
        Polynomial result = new Polynomial();

        for(Map.Entry<Integer, Double> term : polynomial.getPolynomialTerms().entrySet()){
            int exponent = term.getKey() + 1;
            double coefficient = term.getValue() / exponent;
            if(exponent == 0){
                continue;
            }
            if(coefficient != 0)
                result.getPolynomialTerms().put(exponent, coefficient);

        }

        if(result.getPolynomialTerms().isEmpty()){
            result.getPolynomialTerms().put(0, 0.0);
        }

        return result;
    }

}
