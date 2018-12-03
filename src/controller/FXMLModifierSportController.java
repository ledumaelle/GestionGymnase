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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GestionSportBdD;
import model.Sport;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FXMLModifierSportController implements Initializable
{
    private Stage dialogStage;
    private boolean okClick = false;   
    
    @FXML
    private ComboBox cmbSport; 
    @FXML
    private TextField txtSport; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
        cmbSport.setItems(lesSports);
    } 
    
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void handleSelectionSport()
    {
        if (cmbSport.getSelectionModel().getSelectedItem() != null)
        {
            Sport UnSport = (Sport)(cmbSport.getSelectionModel().getSelectedItem());
            txtSport.setText(UnSport.getNomSport());
        }
        
    }
    
    @FXML
    private void handleBtnAnnuler()
    {
        dialogStage.close();
    }
    
    @FXML
    public boolean isOkClick()
    {
        return okClick;
    }
    
    @FXML
    public void handleBtnModifier()
    {
        int nbLignes = 0;
        Sport UnSport = (Sport)(cmbSport.getSelectionModel().getSelectedItem());
        String Nom = txtSport.getText();
        
        if (txtSport.getText() != null && txtSport.getText().length() > 0 )
        {
            nbLignes=GestionSportBdD.modifierSport(UnSport,Nom);
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);  
        if (nbLignes == -1)
        {
            alert.setTitle("Echec");
            alert.setHeaderText("Modification impossible");
            alert.setContentText("Le sport n'est pas valide !");
        }
        else
        {
            alert.setTitle("Succès");
            alert.setHeaderText("Modification du sport effectuée");
            alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes); 
            txtSport.setText(null); 
            ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
            cmbSport.setItems(null);
            cmbSport.setItems(lesSports);
        } 
        alert.showAndWait();
        okClick=true;
    }
}
