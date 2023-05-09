package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class FXMLCalculatorController implements Initializable {

    @FXML
    private TextField textField;
    @FXML
    private Button borrar;
    @FXML
    private Button division;
    @FXML
    private Button multiplicacion;
    @FXML
    private Button resta;
    @FXML
    private Button siete;
    @FXML
    private Button ocho;
    @FXML
    private Button nueve;
    @FXML
    private Button suma;
    @FXML
    private Button cuatro;
    @FXML
    private Button cinco;
    @FXML
    private Button seis;
    @FXML
    private Button igual;
    @FXML
    private Button uno;
    @FXML
    private Button dos;
    @FXML
    private Button tres;
    @FXML
    private Button cero;
    @FXML
    private Button coma;
    @FXML
    private GridPane numberPad;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        String css = this.getClass().getResource("/CSS/style - ejemplo.css").toExternalForm();
        numberPad.getStylesheets().add(css);
        textField.getStylesheets().add(css);
       
    }    
    
}
