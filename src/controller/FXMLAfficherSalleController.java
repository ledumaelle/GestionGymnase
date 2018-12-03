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
import model.GestionSalleBdD;
import model.GestionSportBdD;
import model.Salle;
import model.Sport;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FXMLAfficherSalleController implements Initializable
{
    @FXML
    private ComboBox cmbSalle; 
    @FXML
    private TextField txtNumSalle;
    @FXML
    private TextField txtSurfaceSalle;
    @FXML
    private TextField txtTypeRevetement;
    @FXML
    private ListView lvSalleSport;
    
    public FXMLAfficherSalleController()
    {
        
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {        
        ObservableList<Salle> lesSalles =  GestionSalleBdD.getSalle();
        cmbSalle.setItems(lesSalles); 
    }

    public void handleAfficherUneSalle()
    {
        Salle UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
        txtNumSalle.setText(UneSalle.getNomSalle());
        txtSurfaceSalle.setText(String.valueOf(UneSalle.getSurface()));
        txtTypeRevetement.setText(UneSalle.getTypeDeRevetement());
        ObservableList<Sport> lesSports = GestionSportBdD.getSportParSalle(UneSalle);
        lvSalleSport.setItems(lesSports);
    }  
    
    
}
