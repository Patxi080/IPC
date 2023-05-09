/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafxmlapplication.model.Blog;

/**
 * FXML Controller class
 *
 * @author sovacu
 */
public class MainViewController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button Amazon;
    @FXML
    private Button Blogger;
    @FXML
    private Button Ebay;
    @FXML
    private Button FaceBook;
    @FXML
    private Button GooglePlus;

    //Direcciones web
    String amazon = "https://www.amazon.es/";
    String blogger = "https://www.blogger.com/about/";
    String ebay = "https://www.ebay.es/";
    String facebook = "https://www.facebook.com/";
    String googlePlus = "https://es.wikipedia.org/wiki/Google+";
    
    @FXML
    private ToggleGroup opcionesCompra;
    @FXML
    private MenuItem salirButton;
    @FXML
    private Label mensajeInferior;
    @FXML
    private RadioMenuItem amazonSelected;
    @FXML
    private Menu lanzarButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void onAmazon(ActionEvent event) {   
        if(amazonSelected.isSelected()){
            motorWeb(amazon);
        }else{
            mensError("Amazon");
        }
    }

    /**
     Abre un dialogo de seleccion para preguntar al usuario
     * qué blog desea ver
     * @param Event event
     */
    @FXML
    private void onBlogger(ActionEvent event) {
       
        List<String> choices = new ArrayList<>();
        choices.add("El blog de " + Blog.ATHOS.getName());
        choices.add("El blog de " +Blog.PORTHOS.getName());
        choices.add("El blog de " + Blog.ARAMIS.getName());
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>("El blog de " + Blog.ATHOS.getName(), choices);
        dialog.setTitle("Selecciona un blog");
        dialog.setHeaderText("¿Qué blog quieres visitar?");
        dialog.setContentText("Elige: ");
        Optional<String> result = dialog.showAndWait();
        // Pre Java 8
        if (result.isPresent()) {
        mensajeInferior.setText("Visitando: "+ result.get());
        }
        
    }

    @FXML
    private void onEbay(ActionEvent event) {
        if(!amazonSelected.isSelected()){
            motorWeb(ebay);
        }else{
            mensError("Ebay");
        }
        
    }
    /**
     Abre un dialogo de entrada de texto para enviar un menaaje
     * @param Event event
     */
    @FXML
    private void onFaceBook(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(""); // Por defecto
        dialog.setTitle("Introduce tu nombre");
        dialog.setHeaderText("¿Con qué usuario quires escribir en Facebook?");
        dialog.setContentText("Introduce tu nombre:");
        Optional<String> result = dialog.showAndWait();
        // Obteniendo el resultado (pre Java 8)
        if (result.isPresent()){
            mensajeInferior.setText("Mensaje enviado como " + result.get());
        }

    }

    @FXML
    private void onGooglePlus(ActionEvent event) {
        motorWeb(googlePlus);
    }
    
    private WebView motorWeb(String direccion){
        WebView web = new WebView();
        web.getEngine().load(direccion);
        borderPane.setCenter(web);
        return web;
    }

    /**
     Abre un dialogo de confirmación para preguntar al usuario
     * si desea salir de la aplicación 
     * @param Event event
     */
    @FXML
    private void onSalir(ActionEvent event) {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Dialogo de confirmación");
        alerta.setHeaderText("Vas a salir del programa");
        alerta.setContentText("¿Seguro que quieres salir?");
        
        Optional<ButtonType> result = alerta.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK");
            Platform.exit();
            
        }else{
            System.out.println("CANCEL");
        }
    }
    
    public void mensError(String web){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error en la selección");
        alert.setHeaderText("No puede comprar en " + web);
        alert.setContentText("Por favor, cambie la selección actual en el menú Opciones.");
        alert.showAndWait();
    }

    @FXML
    private void onLanzar(ActionEvent event) {
        if(amazonSelected.isSelected()){
            motorWeb(amazon);
        }else{
            motorWeb(ebay);
        }
    }

}
