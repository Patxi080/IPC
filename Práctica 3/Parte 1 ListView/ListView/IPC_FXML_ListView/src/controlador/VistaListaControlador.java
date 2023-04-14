package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;

import modelo.Persona;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class VistaListaControlador implements Initializable {
    //=========================================================
    @FXML
    private ListView<Persona> personasListView;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button borrarButton;
    
    private ObservableList<Persona> listaObservable;

    //=========================================================
    // DEBEN conincidir los tipo del ListView y de la lista observable
    private ObservableList<?> datos = null; // Coleccion vinculada a la vista.

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // en el código de inicialización del controlador
        ArrayList<Persona> misdatos = new ArrayList<Persona>();
        misdatos.add(new Persona("Pepe", "García"));
        misdatos.add(new Persona("María", "Pérez"));
        
        //=======================================================
        // creamos la lista observable mediante un metodo de FXCollections
        listaObservable = FXCollections.observableList(misdatos);
        
        //=======================================================
        //=======================================================
        // vinculamos la lista observables de personas con el ListView
        listaObservable = personasListView.getItems();
        
        //=======================================================
        //=======================================================
        // Hay que modificar CellFactory para mostrar el objeto Persona
        
        
        //=======================================================
        // inhabilitar botones al arrancar.
        addButton.setDisable(true);
        // si no hay selección el boton Borrar esta disabled
        borrarButton.disableProperty().bind(Bindings.equal(personasListView.getSelectionModel().selectedIndexProperty(), -1));

        // oyente de foco para el textfield.
        nombreTextField.focusedProperty().addListener((a, b, c) -> {
            // TODO Auto-generated method stub
            if (nombreTextField.isFocused()) {
                addButton.setDisable(false);
                personasListView.getSelectionModel().select(-1);
            }
        });

        // oyente de foco para el listView
        personasListView.focusedProperty().addListener((a, b, c) -> {
            if (personasListView.isFocused()) {
                addButton.setDisable(true);
            }
        });
        
    }
    class PersonaListCell extends ListCell<Persona>{
            @Override
            protected void updateItem(Persona t, boolean bln){
                super.updateItem(t, bln);
            }
        }
    @FXML
    void addAccion(ActionEvent event) {
        // añade a la colección si los campos no son vacíos y no contienen únicamente blancos
        if ((!nombreTextField.getText().isEmpty())
                && (nombreTextField.getText().trim().length() != 0)
                && (!apellidosTextField.getText().isEmpty())
                && (apellidosTextField.getText().trim().length() != 0)) {
            //============================================
            // añadimos a la lista
            Persona persona = new Persona(nombreTextField.getText().trim(),apellidosTextField.getText().trim());
            listaObservable.addAll(persona);
            
            //============================================
            // vaciamos los campos despues de añadir a la lista
            nombreTextField.clear();
            apellidosTextField.clear();
            //cambio del foco al textfield inicial
            nombreTextField.requestFocus();

        }
    }

    @FXML
    void borrarAccion(ActionEvent event) {
        //================================================
        // borramos de la lista
        //listaObservable.removeAll();
        int indice = personasListView.getSelectionModel().getSelectedIndex();
        if(indice != -1){
            personasListView.getItems().remove(indice);
            
        }
        //================================================
    }

}
