package dataModels;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial implements Cloneable {
    private TreeMap<Integer, Double> polynomialTerms;
    public Polynomial(){
        polynomialTerms = new TreeMap<>(Comparator.reverseOrder());
    }
    public Polynomial(String polynomialString){
        polynomialTerms = new TreeMap<>(Comparator.reverseOrder());
        polynomialString = polynomialString.replaceAll("\\s","");
        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+\\.?\\d*\\*?[xX]\\^\\d+)|(?:\\d+\\.?\\d*\\*?[xX])|(?:[xX]\\^\\d+)|(?:[xX])|(?:\\d+\\.?\\d*)))");
        Matcher matcher = pattern.matcher(polynomialString);
        while(matcher.find()){
            String term = matcher.group();
            if(term.contains("x") || term.contains("X")){

                term = term.replace("x", "");
                term = term.replace("X", "");
                term = term.replace("*", "");

                if(term.contains("^")){
                    String[] parts = term.split("\\^");

                    int exponent = Integer.parseInt(parts[1]);
                    double coefficient = Double.parseDouble(parts[0]);
                    polynomialTerms.put(exponent, coefficient);
                }else{
                    double coefficient = Double.parseDouble(term);
                    polynomialTerms.put(1, coefficient);
                }
            }else{
                double coefficient = Double.parseDouble(term);
                polynomialTerms.put(0, coefficient);
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Polynomial polynomial = (Polynomial) super.clone();
        polynomial.polynomialTerms = (TreeMap<Integer, Double>) polynomialTerms.clone();
        return polynomial;
    }

    public TreeMap<Integer, Double> getPolynomialTerms(){
        return polynomialTerms;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer exponent : polynomialTerms.keySet()){
            if (polynomialTerms.get(exponent) < 0) {
                stringBuilder.append(" - ");
            } else {
                stringBuilder.append(" + ");
            }
            if(exponent == 0){
                stringBuilder.append(Math.abs(polynomialTerms.get(exponent)));
            }else if(exponent == 1){
                stringBuilder.append(Math.abs(polynomialTerms.get(exponent))).append("x");
            }else{
                stringBuilder.append(Math.abs(polynomialTerms.get(exponent))).append("x^").append(exponent);
            }
        }
        stringBuilder.delete(0, 3);
        return stringBuilder.toString();
    }
}
