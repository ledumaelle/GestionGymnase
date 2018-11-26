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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.GestionReservationBdD;
import model.GestionSportBdD;
import model.Reservation;
import model.Sport;

/**
 * FXML Controller class
 *
 * @author LE DU Maëlle
 */
public class FXMLAjouterSportController implements Initializable 
{
    private Stage dialogStage;
    private boolean okClick = false;   
    
    @FXML
    private ListView lvSport; 
    @FXML
    private TextField txtSport; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
        lvSport.setItems(lesSports);  
    } 
    
    public void handleAjouterSport()
    {
        int nbLignes = 0;
        Object UnSport = (Object) txtSport.getText();
        
        if (txtSport.getText() != null && txtSport.getText().length() > 0 )
        {
            nbLignes=GestionSportBdD.ajouterSport(UnSport);
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);  
        if (nbLignes == -1)
        {
            alert.setTitle("Echec");
            alert.setHeaderText("Ajout impossible");
            alert.setContentText("Le sport existe déjà !");
        }
        else
        {
            alert.setTitle("Succès");
            alert.setHeaderText("Ajout du sport effectué");
            alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes); 
            txtSport.setText(null);
            lvSport.setItems(null);
            ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
            lvSport.setItems(lesSports); 
        } 
        alert.showAndWait();
        okClick=true;
    }
    
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    private void handleBtnAnnuler()
    {
        dialogStage.close();
    }
    
    public boolean isOkClick()
    {
        return okClick;
    }
}
