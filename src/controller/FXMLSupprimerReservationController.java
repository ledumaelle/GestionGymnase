/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Association;
import model.GestionAssociationBdD;
import model.GestionReservationBdD;
import model.GestionSalleBdD;
import model.GestionSportBdD;
import model.Reservation;
import model.Salle;
import model.Sport;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FXMLSupprimerReservationController implements Initializable
{
    private Stage dialogStage;
    private boolean okClick = false;  
    @FXML
    private ComboBox cmbAssociation; 
     @FXML
    private ComboBox cmbSport; 
    @FXML
    private ComboBox cmbSalle; 
    @FXML
    private DatePicker dateReservation;
    @FXML
    private TableView<Reservation> tabReservation;    
    @FXML
    private TableColumn<Reservation, Integer> colHeure;
    
    
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
    public void handleBtnAnnuler()
    {
         dialogStage.close();
    }
    
    @FXML
    public boolean isOkClick()
    {
        return okClick;
    }
    
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }
    
    public void handleSelectionAssociation()
    {
        cmbSalle.setItems(null);
        cmbSport.setItems(null);
        tabReservation.setItems(null);
        Association UneAssociation = (Association)(cmbAssociation.getSelectionModel().getSelectedItem());
        ObservableList<Sport> lesSportsAssociation =  GestionSportBdD.getSportAssociation(UneAssociation);
        cmbSport.setItems(lesSportsAssociation);
    }
    
    public void handleSelectionSport()
    {
        cmbSalle.setItems(null);
        tabReservation.setItems(null);
        Sport UnSport = (Sport)(cmbSport.getSelectionModel().getSelectedItem());
        if (UnSport != null)
        {
            ObservableList<Salle> lesSallesSport =  GestionSalleBdD.getSalleParSport(UnSport);
            cmbSalle.setItems(null); 
            cmbSalle.setItems(lesSallesSport);
        }
    }
    
    public void handleBtnSupprimerReservation()
    {
        if (isInputValid())
        {
            if (tabReservation.getSelectionModel().getSelectedItem() != null )
            {
                Reservation UneReservation =(Reservation) (tabReservation.getSelectionModel().getSelectedItem());
                int nbLignes= GestionReservationBdD.supprimerReservation(UneReservation);
                Alert alert=new Alert(Alert.AlertType.INFORMATION);  
                if (nbLignes != 1)
                {
                    alert.setTitle("Echec");
                    alert.setHeaderText("Suppression impossible");
                    alert.setContentText("La réservation n'existe pas !");
                }
                else
                {
                    alert.setTitle("Succès");
                    alert.setHeaderText("Suprresion de la réservation effectué");
                    alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes);
                    handleSelectionDate();
                    
                } 
                alert.showAndWait(); 
            }            
        }        
    }
    
     private boolean isInputValid()
    {
        String messageErreur = "";
        boolean retour = true;
           
        if (cmbAssociation.getSelectionModel().getSelectedItem() == null || cmbAssociation.getSelectionModel().getSelectedItem() == "")
        {
            messageErreur =messageErreur + "\n" + "Association invalide";
        }
        
        if (cmbSalle.getSelectionModel().getSelectedItem() == null || cmbSalle.getSelectionModel().getSelectedItem() == "")
        {
            messageErreur =messageErreur + "\n" + "Salle invalide";
          }
        
        if (dateReservation.getValue() == null || LocalDate.now().isAfter(dateReservation.getValue()))
        {
            messageErreur =messageErreur+"\n" + "Date invalide";
        }

        if (messageErreur.length() > 0)
        {
          // Affichage du message
          Alert alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Erreur");
          alert.setHeaderText("Vous avez oublié de saisir une valeur");
          alert.setContentText(messageErreur);
          alert.showAndWait(); 
          retour = false;
        }
        
        return retour;

    }
     
    public void handleSelectionDate()
    {
        if(LocalDate.now().isAfter(dateReservation.getValue()))
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);    
            alert.setTitle("Echec");
            alert.setHeaderText("Date incorrecte");
            alert.setContentText("La date ne doit pas être antérieure à la date d'aujourd'hui ! ");
            alert.showAndWait();
        }
        else
        {  
            Association UneAssociation = (Association)(cmbAssociation.getSelectionModel().getSelectedItem());
            Salle UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
            if (UneSalle != null && dateReservation.getValue() != null && (LocalDate.now().isBefore(dateReservation.getValue()) || LocalDate.now().isEqual(dateReservation.getValue())))
            {   
                // Initialise le TableView reservation
                colHeure.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("Heure"));  

                // Pour redimensionner les colonnes automatiquement
                tabReservation.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                tabReservation.setItems(null);
                tabReservation.layout();            
                Date UneDate = Date.valueOf(dateReservation.getValue());
                tabReservation.setItems(GestionReservationBdD.getReservationParAssociationEtSalle(UneAssociation, UneSalle, Date.valueOf(dateReservation.getValue())));
            }
        } 
    }
}
        
        //plusDays
        //menosDays
        //Pane paneCase = new Pane();
        //paneCase.getChildren().add(lblCase);/lblCase.setFont(Font.font("System Bold", FontWeight.BOLD , 14)); 
        //GridPane.setHalignment(lblCase, HPos.CENTER);
        //GridPane.setValignment(lblCase, VPos.CENTER);
        //lblCase.setAlignment(Pos.CENTER_RIGHT);
     
