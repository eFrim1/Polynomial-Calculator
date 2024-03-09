package com.example.UI;

import dataModels.Polynomial;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.PolynomialOperations;

public class Controller {
    @FXML private Button buttonPlus;
    @FXML private Button buttonMinus;
    @FXML private Button buttonDivide;
    @FXML private Button buttonMultiply;
    @FXML private Button buttonDerivation;
    @FXML private Button buttonIntegration;
    @FXML private Button buttonClear;
    @FXML private TextField textFieldFX1;
    @FXML private TextField textFieldFX2;
    @FXML private TextField textFieldResult;
    @FXML private TextField textFieldResult2;

    @FXML
    protected void onButtonPlusClick() {
        textFieldResult2.setVisible(false);

        String polynomial1Text = textFieldFX1.getText();
        String polynomial2Text = textFieldFX2.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);
        Polynomial polynomial2 = new Polynomial(polynomial2Text);

        Polynomial result = PolynomialOperations.add(polynomial, polynomial2);
        textFieldResult.setText(result.toString());
    }

    @FXML
    protected void onButtonMinusClick() {
        textFieldResult2.setVisible(false);

        String polynomial1Text = textFieldFX1.getText();
        String polynomial2Text = textFieldFX2.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);
        Polynomial polynomial2 = new Polynomial(polynomial2Text);

        Polynomial result = PolynomialOperations.subtract(polynomial, polynomial2);
        textFieldResult.setText(result.toString());
    }

    @FXML
    protected void onButtonMultiplyClick() {
        textFieldResult2.setVisible(false);

        String polynomial1Text = textFieldFX1.getText();
        String polynomial2Text = textFieldFX2.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);
        Polynomial polynomial2 = new Polynomial(polynomial2Text);

        Polynomial result = PolynomialOperations.multiply(polynomial, polynomial2);
        textFieldResult.setText(result.toString());
    }

    @FXML
    protected void onButtonDerivationClick() {
        textFieldResult2.setVisible(false);

        String polynomial1Text = textFieldFX1.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);

        Polynomial result = PolynomialOperations.differentiate(polynomial);
        textFieldResult.setText(result.toString());
    }

    @FXML
    protected void onButtonIntegrationClick() {
        textFieldResult2.setVisible(false);

        String polynomial1Text = textFieldFX1.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);

        Polynomial result = PolynomialOperations.integrate(polynomial);
        textFieldResult.setText(result.toString());
    }

    @FXML
    protected void onButtonDivideClick() {
        textFieldResult2.setVisible(true);

        String polynomial1Text = textFieldFX1.getText();
        String polynomial2Text = textFieldFX2.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);
        Polynomial polynomial2 = new Polynomial(polynomial2Text);

        Polynomial result = PolynomialOperations.divide(polynomial, polynomial2);
        textFieldResult.setText(result.toString());
        textFieldResult2.setText(polynomial.toString());
    }

    @FXML
    protected void onButtonClearClick() {
        textFieldFX1.clear();
        textFieldFX2.clear();
        textFieldResult.clear();
        textFieldResult2.clear();
    }
}