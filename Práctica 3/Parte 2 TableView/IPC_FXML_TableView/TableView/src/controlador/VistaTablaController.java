/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsoler
 */
public class VistaTablaController implements Initializable {

    private ObservableList<Persona> datos = null; // Colección vinculada a la vista.
    //private ObservableList<Persona> misPersonas;
    @FXML
    private Button addButton;
    @FXML
    private Button modificarButton;
    @FXML
    private Button borrarButton;
    @FXML
    private TableColumn<Persona, String> nombreColumn;
    @FXML
    private TableColumn<Persona, String> apellidosColumn;
    @FXML
    private TableView<Persona> personasTableV;
    @FXML
    private TableColumn<Persona, String> imagenPathColumn;

    private void inicializaModelo() {
       
        //Ajustar las columnas al modelo
        nombreColumn.setCellValueFactory((personaFila)->{
            return personaFila.getValue().NombreProperty();
        });
        apellidosColumn.setCellValueFactory(personaFila -> personaFila.getValue().ApellidosProperty());
        //imagenPathColumn.setCellValueFactory(personaFila -> new SimpleStringProperty(personaFila.getValue().getImagenPath()));
        imagenPathColumn.setCellFactory(c -> new ImagenTabCell());
        ArrayList<Persona> misdatos = new ArrayList<Persona>();
        misdatos.add(new Persona("Pepe", "García", "/resources/images/Sonriente.png"));
        misdatos.add(new Persona("María", "Pérez", "/resources/images/Lloroso.png"));
        
        
        
        imagenPathColumn.setCellValueFactory(
        personaFila ->new SimpleStringProperty(
        personaFila.getValue().getImagenPath()));
        datos = FXCollections.observableList(misdatos);
        personasTableV.setItems(datos);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        inicializaModelo();
       
    }
 
    @FXML
    private void añadir(ActionEvent event) throws IOException {
        //mostramos la ventana
        //FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona.fxml"));
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona_1.fxml"));
        Stage stage = miCargador.load();  // el fxml contiene como raíz a un stage, lo puedes ver en el Scene Builder
        
        VistaPersonaController_1 controlPersona = miCargador.getController();
        
        stage.setTitle("Añadir persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        if(controlPersona.isOkPressed()){
            Persona p = controlPersona.getPersona();
            datos.add(p);
            
        }
        
    }

    @FXML
    private void modificar(ActionEvent event) throws IOException {
        //mostramos la ventana
        //FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona.fxml"));
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/vista/VistaPersona_1.fxml"));
        Stage stage = miCargador.load();  
        
        VistaPersonaController_1 controlPersona = miCargador.getController();
        Persona persona = personasTableV.getSelectionModel().getSelectedItem();
        controlPersona.initPersona(persona);
        
        stage.setTitle("Modificar persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        if(controlPersona.isOkPressed()){
            int indice = datos.indexOf(persona);
             Persona p = controlPersona.getPersona();
             datos.set(indice, p);
        }
    }

    @FXML
    private void borrar(ActionEvent event){
        datos.remove(personasTableV.getSelectionModel().getSelectedIndex());
    }

class ImagenTabCell extends TableCell<Persona, String> {
    private ImageView view = new ImageView();
    private Image imagen;
    @Override
    protected void updateItem(String t, boolean bln) {
        super.updateItem(t, bln);
        if (t == null || bln) {
            setText(null);
            setGraphic(null);
        } else {
            imagen = new Image(t, 25, 25, true, true);
            view.setImage(imagen);
            setGraphic(view);
            }
    }
    }

}
