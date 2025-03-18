package UI;

import dataModels.Polynomial;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.PolynomialOperations;

public class Controller {

    @FXML private TextField textFieldFX1;
    @FXML private TextField textFieldFX2;
    @FXML private TextField textFieldResult;
    @FXML private TextField textFieldResult2;

    private void setTextField(TextField textField, String text) {
        textField.setEditable(true);
        textField.setText(text);
        textField.setEditable(false);
    }
    private void initialize() {
        textFieldFX1.setVisible(true);
        textFieldFX2.setVisible(true);
        textFieldFX1.setEditable(true);
        textFieldFX2.setEditable(true);
        textFieldResult.setEditable(false);
        textFieldResult2.setEditable(false);
        textFieldResult.clear();
        textFieldResult2.clear();
        textFieldResult2.setVisible(false);
    }
    @FXML
    public void onButtonPlusClick() {
        initialize();

        String polynomial1Text = textFieldFX1.getText();
        String polynomial2Text = textFieldFX2.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);
        Polynomial polynomial2 = new Polynomial(polynomial2Text);

        Polynomial result = PolynomialOperations.add(polynomial, polynomial2);
        setTextField(textFieldResult, result.toString());
    }

    @FXML
    protected void onButtonMinusClick() {
        initialize();

        String polynomial1Text = textFieldFX1.getText();
        String polynomial2Text = textFieldFX2.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);
        Polynomial polynomial2 = new Polynomial(polynomial2Text);

        Polynomial result = PolynomialOperations.subtract(polynomial, polynomial2);
        setTextField(textFieldResult, result.toString());
    }

    @FXML
    protected void onButtonMultiplyClick() {
        initialize();

        String polynomial1Text = textFieldFX1.getText();
        String polynomial2Text = textFieldFX2.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);
        Polynomial polynomial2 = new Polynomial(polynomial2Text);

        Polynomial result = PolynomialOperations.multiply(polynomial, polynomial2);
        setTextField(textFieldResult, result.toString());
    }

    @FXML
    protected void onButtonDerivationClick() {
        initialize();
        textFieldFX2.setVisible(false);

        String polynomial1Text = textFieldFX1.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);

        Polynomial result = PolynomialOperations.differentiate(polynomial);
        setTextField(textFieldResult, result.toString());
    }

    @FXML void onButtonIntegrationClick() {
        initialize();
        textFieldFX2.setVisible(false);

        String polynomial1Text = textFieldFX1.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);

        Polynomial result = PolynomialOperations.integrate(polynomial);
        setTextField(textFieldResult, result.toString());
    }

    @FXML
    protected void onButtonDivideClick() {
        initialize();
        textFieldResult2.setVisible(true);

        String polynomial1Text = textFieldFX1.getText();
        String polynomial2Text = textFieldFX2.getText();

        Polynomial polynomial = new Polynomial(polynomial1Text);
        Polynomial polynomial2 = new Polynomial(polynomial2Text);

        Polynomial result = PolynomialOperations.divide(polynomial, polynomial2);

        setTextField(textFieldResult, result.toString());
        setTextField(textFieldResult2, polynomial.toString());
    }

    @FXML
    protected void onButtonClearClick() {
        textFieldFX1.clear();
        textFieldFX2.clear();
        textFieldResult.clear();
        textFieldResult2.clear();
    }
}