/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Association;
import model.GestionAssociationBdD;
import model.GestionSportBdD;
import model.Sport;

/**
 * FXML Controller class
 *
 * @author LE DU Maëlle
 */
public class FXMLAjouterAssociationController implements Initializable
{
    
    private Stage dialogStage;
    private boolean okClick = false;   
    ObservableList<Sport> lesSportsAssociations = FXCollections.observableArrayList();
    
    @FXML
    private ListView lvAssociation; 
    @FXML
    private ListView lvSport; 
    @FXML
    private ListView lvSportAssociation; 
    @FXML
    private TextField txtNom; 
    @FXML
    private TextField txtAdresse; 
    @FXML
    private TextField txtVille; 
    @FXML
    private TextField txtCodePostal; 
    @FXML
    private TextField txtNomResponsable; 
    @FXML
    private Label lblNomNouveauSport; 
    @FXML
    private TextField txtNomNouveauSport; 
    @FXML
    private Button btnNomNouveauSport; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<Association> lesAssociations =  GestionAssociationBdD.getAssociation();
        lvAssociation.setItems(lesAssociations);  
        ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
        lvSport.setItems(lesSports);  
    } 
    
    private boolean isInputValid()
    {
        String messageErreur = "";
        boolean retour = true;
            
        if (txtNom.getText() == null || txtNom.getText().length()<=0)
        {
            messageErreur =messageErreur + "\n" + "Référence invalide";
          }
        
        if (txtAdresse.getText() == null || txtAdresse.getText().length()<=0)
        {
            messageErreur =messageErreur + "\n" + "Adresse invalide";
         }

        if (txtVille.getText() == null || txtVille.getText().length()<=0)
        {
            messageErreur =messageErreur+"\n" + "Ville invalide";
        }

        if (txtCodePostal.getText() == null || txtCodePostal.getText().length()<=0)
        {
            messageErreur =messageErreur+"\n" + "Code postal invalide";
        }

        if (txtNomResponsable.getText() == null || txtNomResponsable.getText().length()<=0)
        {
            messageErreur =messageErreur+"\n" + "Nom du responsable invalide";
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
    
    public void handleBtnEnvoiSport()
    {
        if(lvSport.getSelectionModel().getSelectedItem() != null && (txtNom.getText() != null || txtNom.getText().length() >0))
        {
            Sport UnSport = (Sport)(lvSport.getSelectionModel().getSelectedItem());
            if (!(lesSportsAssociations.contains(UnSport)))
            {
                lesSportsAssociations.add(UnSport);
                lvSportAssociation.setItems(null);
                lvSportAssociation.setItems(lesSportsAssociations);
            }
        }
        
    }
    
    public void handleBtnEnleveSport()
    {
        if(txtNom.getText() != null || txtNom.getText().length() >0)
        {
            Sport UnSport = (Sport)(lvSportAssociation.getSelectionModel().getSelectedItem());
            lesSportsAssociations.remove(UnSport);            
        }
    }
    
    public void handleBtnAjoutSport()
    {
        if(txtNom.getText() != null || txtNom.getText().length() >0)
        {
            lblNomNouveauSport.setVisible(true);
            txtNomNouveauSport.setVisible(true);
            btnNomNouveauSport.setVisible(true);
        }
    }
    
    public void handleBtnAjoutNouveauSport()
    {
        if (!(txtNomNouveauSport.getText() == null || txtNomNouveauSport.getText().length() <= 0 ))
        {            
            int NbMaxSport = GestionSportBdD.getNbMax()+1 ;
            Sport UnSport = new Sport (NbMaxSport,txtNomNouveauSport.getText());
            if (!(lesSportsAssociations.contains(UnSport)))
            {
                lesSportsAssociations.add(UnSport);
                lvSportAssociation.setItems(null);
                lvSportAssociation.setItems(lesSportsAssociations);
                txtNomNouveauSport.setText(null);
                lblNomNouveauSport.setVisible(false);
                txtNomNouveauSport.setVisible(false);
                btnNomNouveauSport.setVisible(false);
            }
            
        }
    }
    
    public void handleBtnAjouter()
    {
        if (isInputValid())
        {
            //AJOUT ASSOCIATION
            int nbLignes;
            int NbMaxSport = GestionAssociationBdD.getNbMax()+1 ;
            Association UneAssociation = new Association (NbMaxSport,txtNom.getText(),txtAdresse.getText(),txtVille.getText(),txtCodePostal.getText(),txtNomResponsable.getText());
            nbLignes=GestionAssociationBdD.ajouterAssociation(UneAssociation);
            
            Alert alert=new Alert(Alert.AlertType.INFORMATION);  
            if (nbLignes == -1)
            {
                alert.setTitle("Echec");
                alert.setHeaderText("Ajout impossible");
                alert.setContentText("L'association existe déjà !");
            }
            else
            {
                alert.setTitle("Succès");
                alert.setHeaderText("Ajout de l'association effectué");
                alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes); 
                txtNom.setText(null);
                txtAdresse.setText(null);
                txtVille.setText(null);
                txtCodePostal.setText(null);
                txtNomResponsable.setText(null);
                lvAssociation.setItems(null);
                ObservableList<Association> lesAssociations =  GestionAssociationBdD.getAssociation();
                lvAssociation.setItems(lesAssociations); 
            } 
            alert.showAndWait();
            
            //AJOUT PRATIQUER
            nbLignes=0;
            int i;
            for(i=0;i<lesSportsAssociations.size();i++)
            {                
                Sport UnSport = new Sport (lesSportsAssociations.get(i).getNumSport(),lesSportsAssociations.get(i).getNomSport());
                if(!(GestionSportBdD.existe(UnSport)))
                {                    
                    GestionSportBdD.ajouterSport(UnSport);
                }
                nbLignes=nbLignes + GestionAssociationBdD.ajouterSport(UneAssociation,UnSport);
            }         
            if (nbLignes == -1)
            {
                alert.setTitle("Echec");
                alert.setHeaderText("Ajout impossible");
                alert.setContentText("Impossible d'ajouter un (des) sports pour cette association !");
            }
            else
            {
                alert.setTitle("Succès");
                alert.setHeaderText("Ajout des sports pour l'association effectué");
                alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes); 
                txtNom.setText(null);
                txtAdresse.setText(null);
                txtVille.setText(null);
                txtCodePostal.setText(null);
                txtNomResponsable.setText(null);
                lvAssociation.setItems(null);
                ObservableList<Association> lesAssociations =  GestionAssociationBdD.getAssociation();
                lvAssociation.setItems(lesAssociations); 
                ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
                lvSport.setItems(null);
                lvSport.setItems(lesSports); 
                lvSportAssociation.setItems(null);
                lesSportsAssociations.clear();
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
    
}
