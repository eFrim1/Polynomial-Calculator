package dataModels;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private TreeMap<Integer, Double> polynomialTerms;
    public Polynomial(){
        polynomialTerms = new TreeMap<>(Comparator.reverseOrder());
    }

    public Polynomial(Polynomial polynomial){
        polynomialTerms = new TreeMap<>(Comparator.reverseOrder());
        polynomialTerms.putAll(polynomial.getPolynomialTerms());

    }
    public Polynomial(String polynomialString) {
        polynomialTerms = new TreeMap<>(Comparator.reverseOrder());

        polynomialString = polynomialString.replaceAll("\\s", "");

        for(String term : splitIntoTerms(polynomialString)) {
            Map.Entry<Integer, Double> termEntry = parseTerm(term);

            int exponent = termEntry.getKey();
            double coefficient = termEntry.getValue();

            if (coefficient == 0 && exponent != 0) continue;
            if (polynomialTerms.containsKey(exponent)) {
                polynomialTerms.put(exponent, polynomialTerms.get(exponent) + coefficient);
            } else {
                polynomialTerms.put(exponent, coefficient);
            }
        }
    }

    private ArrayList<String> splitIntoTerms(String polynomialString){
        ArrayList<String> terms = new ArrayList<>();
        if(polynomialString.isEmpty()){
            terms.add("0");
            return terms;
        }
        Pattern pattern = Pattern.compile("([+-]?(?:(?:\\d+\\.?\\d*\\*?[xX]\\^\\d+)|(?:\\d+\\.?\\d*\\*?[xX])|(?:[xX]\\^\\d+)|(?:[xX])|(?:\\d+\\.?\\d*)))");
        Matcher matcher = pattern.matcher(polynomialString);
        while (matcher.find()) {
            terms.add(matcher.group());
        }
        return terms;
    }

    private Map.Entry<Integer,Double> parseTerm(String term){
        double coefficient;
        int exponent;
        if (term.contains("x") || term.contains("X")) {
            term = term.replace("x", "").replace("X", "").replace("*", "");

            if (term.contains("^")) {
                String[] parts = term.split("\\^");
                exponent = Integer.parseInt(parts[1]);
                coefficient = parts[0].isEmpty() ? 1 : parts[0].equals("+") ? 1 : parts[0].equals("-") ? -1 : Double.parseDouble(parts[0]);
            } else {
                exponent = 1;
                coefficient = term.isEmpty() ? 1 : term.equals("+") ? 1 : term.equals("-") ? -1 : Double.parseDouble(term);
            }
        }else{
            exponent = 0;
            coefficient = Double.parseDouble(term);
        }
        return new AbstractMap.SimpleEntry<>(exponent, coefficient);
    }

    public TreeMap<Integer, Double> getPolynomialTerms(){
        return polynomialTerms;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        double coefficient;
        for (Integer exponent : polynomialTerms.keySet()){
            coefficient = polynomialTerms.get(exponent);
            coefficient = Math.round(coefficient * 100.0) / 100.0;
            if (coefficient < 0) {
                stringBuilder.append(" - ");
            } else {
                stringBuilder.append(" + ");
            }
            if(exponent == 0){
                stringBuilder.append(Math.abs(coefficient));
            }else {
                if(coefficient != 1 && coefficient != -1){
                    stringBuilder.append(Math.abs(coefficient));
                }
                if (exponent == 1) {
                    stringBuilder.append("x");
                } else {
                    stringBuilder.append("x^").append(exponent);
                }
            }
        }
        if(!stringBuilder.isEmpty()) {
            if (stringBuilder.charAt(1) == '+') {
                stringBuilder.delete(0, 3);
            } else {
                stringBuilder.delete(0, 1);
                stringBuilder.delete(1, 2);
            }
        }
        if(stringBuilder.isEmpty()){
            stringBuilder.append("0.0");
        }
        return stringBuilder.toString();
    }
}
