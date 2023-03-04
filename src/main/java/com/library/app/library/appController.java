package com.library.app.library;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class appController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Library management!");
    }
}