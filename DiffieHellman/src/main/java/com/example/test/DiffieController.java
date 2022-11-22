package com.example.test;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class DiffieController {
    private int min = 17;
    private int max = 200;
    @FXML
    private Label PrimeAText;
    @FXML
    private Label PrimeBText;

    @FXML
    protected void onGenAButtonClick() {
        PrimeAText.setText("Here comes KeyA: "+ Math.floor(Math.random()*(max-min+1)+min));
    }
    @FXML
    protected void onGenBButtonClick() {
        PrimeBText.setText("Here comes KeyB: "+ Math.floor(Math.random()*(max-min+1)+min) );
    }
}