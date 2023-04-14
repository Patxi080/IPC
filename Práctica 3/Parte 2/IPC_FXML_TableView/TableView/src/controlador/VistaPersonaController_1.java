/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.awt.Menu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsole
 */
public class VistaPersonaController_1 implements Initializable {

    @FXML
    private Stage stage;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private ComboBox<String> imagenCombo;
    @FXML
    private Button aceptarButton2;
    @FXML
    private Button cancelarButton2;
    private MenuButton menuButton = new MenuButton();
    
    private boolean okPressed = false;
    private Persona persona;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imagenCombo.getItems().addAll("/resources/images/Lloroso.png", "/resources/images/Pregunta.png", "/resources/images/Sonriente.png");
        imagenCombo.setCellFactory(c -> new ImagenTabCell());
        imagenCombo.setButtonCell(new ImagenTabCell()); // para mostrar en el combo el elemento seleccionado
       
    }      
    public void initPersona(Persona p){
        persona = p;
        nombreTextField.setText(p.getNombre());
        apellidosTextField.setText(p.getApellidos());
        String imagenPath = p.getImagenPath();
        if (imagenPath != null && !imagenPath.isEmpty()) {
            imagenCombo.getSelectionModel().select(imagenPath);
        }
       
    }
    public boolean isOkPressed(){
        return okPressed;
    }
    public Persona getPersona(){
        return persona;
    }
    @FXML
    private void aceptar2(ActionEvent event) {
        String nombre = nombreTextField.getText();
    String apellidos = apellidosTextField.getText();
    String imagen = imagenCombo.getValue(); // Obtener la imagen seleccionada del ComboBox

    if (!nombre.isEmpty() && !apellidos.isEmpty() && imagen != null && !imagen.isEmpty()) {
        persona = new Persona(nombre, apellidos, imagen);
        okPressed = true;
        nombreTextField.getScene().getWindow().hide();
    }
        
    }

    @FXML
    private void cancelar2(ActionEvent event ) {
        nombreTextField.getScene().getWindow().hide();
    }
class ImagenTabCell extends ComboBoxListCell<String> {
        private ImageView view = new ImageView();
        private Image imagen;

        @Override
        public void updateItem(String t, boolean bln) {
            super.updateItem(t, bln); 
            if (t == null || bln) {
                setText(null);
                setGraphic(null);
            } else {
                imagen = new Image(t,25,25,true,true);
                view.setImage(imagen);
                setGraphic(view);
                setText(null);
            }
        }
    }
    
    
}
