import dataModels.Polynomial;

import static org.junit.jupiter.api.Assertions.*;

import logic.PolynomialOperations;
import org.junit.jupiter.api.Test;

public class PolynomialOperationsTest {
    @Test
    void testAdditionPolynomials() {
        String[][] tests = new String[][]{
                {"2.0x^2 + 3.0x + 4.0", "3.0x^2 + 2.0x + 1.0", "5.0x^2 + 5.0x + 5.0"},
                {"x^3 + 2.0x^2 + 3.0x + 4.0", "2.0x^3 + 3.0x^2 + 2.0x + 1.0", "3.0x^3 + 5.0x^2 + 5.0x + 5.0"},
                {"4.0x^4 + 3.0x^3 + 2.0x^2 + x + 1", "3.0x^4 + 2.0x^3 + x^2 + 1", "7.0x^4 + 5.0x^3 + 3.0x^2 + x + 2.0"},
                {"x^5 + x^4 + x^3 + x^2 + x + 1", "x^5 + x^4 + x^3 + x^2 + x + 1", "2.0x^5 + 2.0x^4 + 2.0x^3 + 2.0x^2 + 2.0x + 2.0"}
        };
        for (String[] test : tests) {
            Polynomial polynomial1 = new Polynomial(test[0]);
            Polynomial polynomial2 = new Polynomial(test[1]);
            Polynomial result = PolynomialOperations.add(polynomial1, polynomial2);
            assertEquals(test[2], result.toString());
        }
    }

    @Test
    void testSubtractionPolynomials() {
        String[][] tests = {
                {"2.0x^2 + 3.0x + 4.0", "3.0x^2 + 2.0x + 1.0", "-x^2 + x + 3.0"},
                {"-x^3 + 2.0x^2 + 3.0x + 4.0", "2.0x^3 + 3.0x^2 + 2.0x + 1.0", "-3.0x^3 - x^2 + x + 3.0"},
                {"4.0x^4 + 3.0x^3 + 2.0x^2 + x + 1", "3.0x^4 + 2.0x^3 + x^2 + 1", "x^4 + x^3 + x^2 + x"},
                {"x^5 + x^4 + x^3 + x^2 + x + 1", "x^5 + x^4 + x^3 + x^2 + x + 1", "0.0"}
        };
        for (String[] test : tests) {
            Polynomial polynomial1 = new Polynomial(test[0]);
            Polynomial polynomial2 = new Polynomial(test[1]);
            Polynomial result = PolynomialOperations.subtract(polynomial1, polynomial2);
            assertEquals(test[2], result.toString());
        }
    }

    @Test
    void testMultiplicationPolynomials() {
        String[][] tests = {
                {"2.0x^2 + 3.0x + 4.0", "3.0x^2 + 2.0x + 1.0", "6.0x^4 + 13.0x^3 + 20.0x^2 + 11.0x + 4.0"},
                {"x^3 + 2.0x^2 + 3.0x + 4.0", "2.0x^3 + 3.0x^2 + 2.0x + 1.0", "2.0x^6 + 7.0x^5 + 14.0x^4 + 22.0x^3 + 20.0x^2 + 11.0x + 4.0"},
                {"4.0x^4 + 3.0x^3 + 2.0x^2 + x + 1", "3.0x^4 + 2.0x^3 + x^2 + 1", "12.0x^8 + 17.0x^7 + 16.0x^6 + 10.0x^5 + 11.0x^4 + 6.0x^3 + 3.0x^2 + x + 1.0"},
                {"x^5 + x^4 + x^3 + x^2 + x + 1", "x^5 + x^4 + x^3 + x^2 + x + 1", "x^10 + 2.0x^9 + 3.0x^8 + 4.0x^7 + 5.0x^6 + 6.0x^5 + 5.0x^4 + 4.0x^3 + 3.0x^2 + 2.0x + 1.0"}
        };
        for (String[] test : tests) {
            Polynomial polynomial1 = new Polynomial(test[0]);
            Polynomial polynomial2 = new Polynomial(test[1]);
            Polynomial result = PolynomialOperations.multiply(polynomial1, polynomial2);
            assertEquals(test[2], result.toString());
        }
    }

    @Test
    void testDivisionPolynomials() {
        String[][] tests = {
                {"2.0x^2 + 3.0x + 4.0", "3.0x^2 + 2.0x + 1.0", "0.67", "1.67x + 3.33"},
                {"x^3 - 2x^2 + 6x - 5", "x^2 - 1", "x - 2.0", "7.0x - 7.0"},
                {"x^5", "x^2", "x^3", "0.0"},
                {"x^3 + 5x + 2", "x^2 + 2", "x", "3.0x + 2.0"}

        };
        for (String[] test : tests) {
            Polynomial polynomial1 = new Polynomial(test[0]);
            Polynomial polynomial2 = new Polynomial(test[1]);
            Polynomial result = PolynomialOperations.divide(polynomial1, polynomial2);
            assertEquals(test[2], result.toString());
            assertEquals(test[3], polynomial1.toString());
        }
    }

    @Test
    void testIntegrationPolynomials() {
        String[][] tests = {
                {"2.0x^2 + 3.0x + 4.0", "0.67x^3 + 1.5x^2 + 4.0x"},
                {"x^3 - 2x^2 + 6x - 5", "0.25x^4 - 0.67x^3 + 3.0x^2 - 5.0x"},
                {"x^5", "0.17x^6"},
                {"x^3 + 5x + 2", "0.25x^4 + 2.5x^2 + 2.0x"}
        };
        for (String[] test : tests) {
            Polynomial polynomial = new Polynomial(test[0]);
            Polynomial result = PolynomialOperations.integrate(polynomial);
            assertEquals(test[1], result.toString());
        }
    }

    @Test
    void testDifferentiationPolynomials() {
        String[][] tests = {
                {"2.0x^2 + 3.0x + 4.0", "4.0x + 3.0"},
                {"x^3 - 2x^2 + 6x - 5", "3.0x^2 - 4.0x + 6.0"},
                {"x^5", "5.0x^4"},
                {"x^3 + 5x + 2", "3.0x^2 + 5.0"}
        };
        for (String[] test : tests) {
            Polynomial polynomial = new Polynomial(test[0]);
            Polynomial result = PolynomialOperations.differentiate(polynomial);
            assertEquals(test[1], result.toString());
        }
    }
}
