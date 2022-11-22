package com.example.test;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DiffieController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Here comes Primenumber");
    }
}