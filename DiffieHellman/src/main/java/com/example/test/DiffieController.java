package com.example.test;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DiffieController {
    @FXML
    private Label PrimeAText;
    @FXML
    private Label PrimeBText;

    @FXML
    protected void onGenAButtonClick() {
        PrimeAText.setText("Here comes Primenumber");
    }
    @FXML
    protected void onGenBButtonClick() {
        PrimeBText.setText("Here comes Primenumber");
    }
}