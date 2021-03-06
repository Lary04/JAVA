/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionetudiant;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Classe;
import services.GestionEtudiantImpl;

/**
 *
 * @author Hilary DJENGUE
 */
public class C_Classe implements Initializable {

    private GestionEtudiantImpl service=new GestionEtudiantImpl();
    private ObservableList<String>obvAnneeScolaire;
    private ObservableList<Classe>obvClasse;
    
    
    @FXML
    private ComboBox<String> cbo_AnneeScolaire;
    @FXML
    private TableView<Classe> tblv_Classe;
    @FXML
    private TableColumn<Classe, String> tblc_Id;
    @FXML
    private TableColumn<Classe, String> tblc_Libelle;
    @FXML
    private TextField txt_Libelle;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> lAnneeScolaire=service.getAnneeScolaire();
        //Convertir un ArrayList en Observable
        obvAnneeScolaire=FXCollections.observableArrayList(lAnneeScolaire);
        //Souscription 
        cbo_AnneeScolaire.setItems(obvAnneeScolaire);
        //Selectionner le Premier Element
        cbo_AnneeScolaire.getSelectionModel().selectFirst();
        
        obvClasse=FXCollections.observableArrayList(service.listerClasse());
        //Construction des Cellule de la Table
        tblc_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblc_Libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
         //Souscription 
         tblv_Classe.setItems(obvClasse);
        
        
        
    }

    @FXML
    private void handleInsertClasse(ActionEvent event) {
       String libelle=txt_Libelle.getText().trim();
       if(libelle.compareTo("")==0){
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setContentText("Libelle Obligatoire");
           alert.showAndWait();
       }else{
           if(service.rechercherClasseByLibelle(libelle)){
               Alert alert=new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Erreur");
               alert.setContentText("La Classe Existe");
               alert.showAndWait();
           }
       }
       
    }
    
   
    
       
    
}
