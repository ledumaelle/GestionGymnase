/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Association;
import model.GestionAssociationBdD;
import model.GestionSalleBdD;
import model.GestionSportBdD;
import model.Salle;
import model.Sport;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FXMLAfficherAssociationController implements Initializable
{
    @FXML
    private ComboBox cmbAssociation; 
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtCodePostal;
    @FXML
    private TextField txtVille;
    @FXML
    private TextField txtNomResponsable;
    @FXML
    private ListView lvSport;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {        
        ObservableList<Association> lesAssociations =  GestionAssociationBdD.getAssociation();
        cmbAssociation.setItems(lesAssociations); 
    }

    public void handleSelectionAssociation()
    {
        Association UneAssociation = (Association)(cmbAssociation.getSelectionModel().getSelectedItem());
        
        txtNom.setText(null);
        txtAdresse.setText(null);
        txtCodePostal.setText(null);
        txtVille.setText(null);
        txtNomResponsable.setText(null);
        
        txtNom.setText(UneAssociation.getNomAssociation());
        txtAdresse.setText(UneAssociation.getAdresseAssociation());
        txtCodePostal.setText(UneAssociation.getCPAssociation());
        txtVille.setText(UneAssociation.getVilleAssociation());
        txtNomResponsable.setText(UneAssociation.getNomResponsable());
        ObservableList<Sport> lesSports = GestionSportBdD.getSportAssociation(UneAssociation);
        lvSport.setItems(null);
        lvSport.setItems(lesSports);
    }  
    
    
}
