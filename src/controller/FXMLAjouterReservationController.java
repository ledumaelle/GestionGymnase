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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
 * @author Gaara
 */
public class FXMLAjouterReservationController implements Initializable
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
    private TableView<Reservation> tabReservation;    
    @FXML
    private TableColumn<Reservation, String> colAssociation;
    @FXML
    private TableColumn<Reservation, Integer> colHeure;
    
    @FXML
    private ListView lvHeureReservation;    
    @FXML
    private DatePicker dateReservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {        
        ObservableList<Association> lesAssociations =  GestionAssociationBdD.getAssociation();
        cmbAssociation.setItems(lesAssociations);         
        
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
            cmbSalle.setItems(lesSallesSport);
        }
        
    }
    
    public void handleDateReservation()
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
            int i,j;
            boolean same; 
            tabReservation.setItems(null);
            Salle UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
            if (UneSalle != null && dateReservation.getValue() != null && (LocalDate.now().isBefore(dateReservation.getValue()) || LocalDate.now().isEqual(dateReservation.getValue())))
            {
                // Initialise le TableView reservation
                colAssociation.setCellValueFactory(new PropertyValueFactory<Reservation, String>("RefAssociation"));
                colHeure.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("Heure"));  

                // Pour redimensionner les colonnes automatiquement
                tabReservation.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                tabReservation.setItems(null);
                tabReservation.layout();            
                Date UneDate = Date.valueOf(dateReservation.getValue());
                tabReservation.setItems(GestionReservationBdD.getReservationSalle(UneSalle,UneDate));
                
                
                lvHeureReservation.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                ObservableList<Integer> lesHeuresOccupees = GestionReservationBdD.getHeureDisponible(UneSalle,Date.valueOf(dateReservation.getValue()));
                ObservableList<String> lesHeuresDisponibles = FXCollections.observableArrayList();
                if(!(lesHeuresOccupees.isEmpty()))
                {
                    for (i=8; i<20; i++)
                    {
                        same =false;
                        for(j=0;j<lesHeuresOccupees.size();j++)
                        {
                            if(lesHeuresOccupees.get(j) ==i)
                            {
                                same = true; 
                            }
                            if( same == false && (j==lesHeuresOccupees.size()-1))
                            {
                                lesHeuresDisponibles.add(i + "-" + (i+1));
                            }
                        }                        
                    }
                }
                else
                {
                    for (i=8; i<20; i++)
                    {
                        lesHeuresDisponibles.add(i + "-" + (i+1));
                    }
                }
                lvHeureReservation.setItems(lesHeuresDisponibles);                
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
        
        if (cmbSport.getSelectionModel().getSelectedItem() == null || cmbSport.getSelectionModel().getSelectedItem() == "")
        {
            messageErreur =messageErreur + "\n" + "Sport invalide";
         }

        if (cmbSalle.getSelectionModel().getSelectedItem() == null || cmbSalle.getSelectionModel().getSelectedItem() == "")
        {
            messageErreur =messageErreur+"\n" + "Salle invalide";
        }

        if (dateReservation.getValue() == null || LocalDate.now().isAfter(dateReservation.getValue()))
        {
            messageErreur =messageErreur+"\n" + "Date invalide";
        }

        if (lvHeureReservation.getSelectionModel().getSelectedItem() == null || lvHeureReservation.getSelectionModel().getSelectedItem() == "")
        {
            messageErreur =messageErreur+"\n" + "Heure invalide";
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
    
    @FXML
    public void handleBtnAjouter()
    {
        if(isInputValid())
        {
            int nbLignes=0,i;
            Association UneAssociation = (Association)(cmbAssociation.getSelectionModel().getSelectedItem());
            Salle UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
            Date UneDate = Date.valueOf(dateReservation.getValue());
            ObservableList<String> lesHeuresReservees = lvHeureReservation.getSelectionModel().getSelectedItems();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);  
            for(i=0;i<lesHeuresReservees.size();i++)
            {
                String PlageHorraire= lesHeuresReservees.get(i);
                String[] tab = PlageHorraire.split("-");
                Integer UneHeure = Integer.parseInt(tab[0]);
                Reservation UneReservation= new Reservation(UneSalle.getRefSalle(),UneDate,UneHeure,UneAssociation.getRefAssociation());                
                          
                nbLignes=nbLignes+GestionReservationBdD.ajouterReservation(UneReservation);
                if (nbLignes == -1)
                {
                    alert.setTitle("Echec");
                    alert.setHeaderText("Ajout impossible");
                    alert.setContentText("La réservation existe déjà !");
                    break;
                }
                else
                {
                    alert.setTitle("Succès");
                    alert.setHeaderText("Ajout de la réservation effectué");
                    alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes);
                }  
            }
            if (nbLignes != -1)
            {
                handleDateReservation();                
            }    
            alert.showAndWait();
            okClick=true;
        }
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
    
    public void handleSelectionSalle()
    {
        Salle UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
        if (UneSalle != null && dateReservation.getValue() != null && (LocalDate.now().isBefore(dateReservation.getValue()) || LocalDate.now().isEqual(dateReservation.getValue())))
        {
            // Initialise le TableView reservation
            colAssociation.setCellValueFactory(new PropertyValueFactory<Reservation, String>("RefAssociation"));
            colHeure.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("Heure"));  
            
            // Pour redimensionner les colonnes automatiquement
            tabReservation.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            tabReservation.setItems(null);
            tabReservation.layout();            
            Date UneDate = Date.valueOf(dateReservation.getValue());
            tabReservation.setItems(GestionReservationBdD.getReservationSalle(UneSalle,UneDate));
        }
    }    
    
    public void handleSupprimerReservation(KeyEvent pKeyEvent)
    {        
        if(pKeyEvent.getCode() == KeyCode.DELETE)
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
                handleDateReservation();  
            } 
            alert.showAndWait();
        }        
    }
}
