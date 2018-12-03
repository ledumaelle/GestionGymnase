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
import javafx.scene.control.ComboBox;
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
public class FXMLModifierAssociationController implements Initializable
{
    private Stage dialogStage;
    private boolean okClick = false;  
    //liste de droite
    ObservableList<Sport> lesSportsAssociations = FXCollections.observableArrayList();
    
    @FXML
    private ComboBox cmbAssociation; 
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
    private ListView lvSport; 
    @FXML
    private ListView lvSportAssociation; 
    @FXML
    private Label lblNomNouveauSport; 
    @FXML
    private TextField txtNomNouveauSport; 
    @FXML
    private Button btnNomNouveauSport; 

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
        ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
        lvSport.setItems(lesSports); 
    } 
    
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void handleSelectionAssociation()
    {
        if (cmbAssociation.getSelectionModel().getSelectedItem() != null)
        {
            int i;
            Association UneAssociation = (Association)(cmbAssociation.getSelectionModel().getSelectedItem());
            txtNom.setText(UneAssociation.getNomAssociation());
            txtAdresse.setText(UneAssociation.getAdresseAssociation());
            txtCodePostal.setText(UneAssociation.getCPAssociation());
            txtVille.setText(UneAssociation.getVilleAssociation());
            txtNomResponsable.setText(UneAssociation.getNomResponsable());
            lesSportsAssociations=GestionSportBdD.getSportAssociation(UneAssociation);
            lvSportAssociation.setItems(null);
            lvSportAssociation.setItems(lesSportsAssociations);            
            
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
    
    private boolean isInputValid()
    {
        String messageErreur = "";
        boolean retour = true;
           
        if (cmbAssociation.getSelectionModel().getSelectedItem() == null || cmbAssociation.getSelectionModel().getSelectedItem() == "")
        {
            messageErreur =messageErreur + "\n" + "Association invalide";
        }
        
        if (txtNom.getText() == null || txtNom.getText().length()<0)
        {
            messageErreur =messageErreur + "\n" + "Référence invalide";
          }
        
        if (txtAdresse.getText() == null || txtAdresse.getText().length()<0)
        {
            messageErreur =messageErreur + "\n" + "Adresse invalide";
         }

        if (txtVille.getText() == null || txtVille.getText().length()<0)
        {
            messageErreur =messageErreur+"\n" + "Ville invalide";
        }

        if (txtCodePostal.getText() == null || txtCodePostal.getText().length()<0)
        {
            messageErreur =messageErreur+"\n" + "Code postal invalide";
        }

        if (txtNomResponsable.getText() == null || txtNomResponsable.getText().length()<0)
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
        if(cmbAssociation.getSelectionModel().getSelectedItem() != null && lvSport.getSelectionModel().getSelectedItem() != null)
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
        if(cmbAssociation.getSelectionModel().getSelectedItem() != null)
        {
            Sport UnSport = (Sport) (lvSportAssociation.getSelectionModel().getSelectedItem());
            lesSportsAssociations.remove(UnSport);
        }
        
    }
    
    public void handleBtnAjoutSport()
    {
        if(cmbAssociation.getSelectionModel().getSelectedItem() != null)
        {
            lblNomNouveauSport.setVisible(true);
            txtNomNouveauSport.setVisible(true);
            btnNomNouveauSport.setVisible(true);
        }
    }
    
    public void handleBtnAjoutNouveauSport()
    {
        if(cmbAssociation.getSelectionModel().getSelectedItem() != null)
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
    }
    
    @FXML
    public void handleBtnModifier()
    {
        if (isInputValid())
        {
            boolean valide=false;
            //MODIFIER ASSOCIATION
            int nbLignes;
            Association UneAssociation = (Association)(cmbAssociation.getSelectionModel().getSelectedItem());
            int NbMaxSport = GestionAssociationBdD.getNbMax()+1 ;
            //Association temporaire
            Association UneAssociation2 = new Association (NbMaxSport,txtNom.getText(),txtAdresse.getText(),txtVille.getText(),txtCodePostal.getText(),txtNomResponsable.getText());
            //Valide ?
            nbLignes=GestionAssociationBdD.modifierAssociation(UneAssociation,UneAssociation2);
            Alert alert=new Alert(Alert.AlertType.INFORMATION); 
            if (nbLignes == -1)
            {
                alert.setTitle("Echec");
                alert.setHeaderText("Modification impossible");
                alert.setContentText("L'association n'est pas valide !");
            }
            else
            {
                alert.setTitle("Succès");
                alert.setHeaderText("Modification de l'association effectuée");
                alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes); 
                txtNom.setText(null);
                txtAdresse.setText(null);
                txtCodePostal.setText(null);
                txtVille.setText(null);
                txtNomResponsable.setText(null);                
                valide=true;
            }
            alert.showAndWait();   
            
            if (valide==true)
            {
                
            }
            //MODIFIER PRATIQUER
            nbLignes=0;
            int i,j;
            for(i=0;i<lesSportsAssociations.size();i++)
            {                     
                ObservableList<Sport> lesSportsAssociationsAvantModification = GestionSportBdD.getSportAssociation(UneAssociation);
                UneAssociation = (Association)(cmbAssociation.getSelectionModel().getSelectedItem());
                Sport UnSport = new Sport (lesSportsAssociations.get(i).getNumSport(),lesSportsAssociations.get(i).getNomSport());
                
                //si le sport n'existe pas dans la BdD
                if(!(GestionSportBdD.existe(UnSport)))
                {                    
                    //ajout
                    GestionSportBdD.ajouterSport(UnSport);
                }
                if(!(lesSportsAssociationsAvantModification.contains(UnSport)))
                {                    
                    //ajout dans pratiquer si besoin
                    nbLignes=nbLignes + GestionAssociationBdD.ajouterSport(UneAssociation,UnSport);
                }
                //parcourir la liste de base des sports de l'association
                for(j=0;j<lesSportsAssociationsAvantModification.size();j++)
                {       
                    // si le sport de la nouvelle liste n'est pas dans la liste auparavent alors on le supprime 
                    if (!(lesSportsAssociations.contains(lesSportsAssociationsAvantModification.get(j))))
                    {
                        Sport UnSport2 = new Sport (lesSportsAssociationsAvantModification.get(j).getNumSport(),lesSportsAssociationsAvantModification.get(j).getNomSport());
                        //supprimer dans pratiquer
                        nbLignes=nbLignes + GestionAssociationBdD.supprimerSport(UneAssociation,UnSport2);
                    }
                }
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
                cmbAssociation.setItems(null);
                ObservableList<Association> lesAssociations =  GestionAssociationBdD.getAssociation();
                cmbAssociation.setItems(lesAssociations);                
                ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
                lvSport.setItems(null);
                lvSport.setItems(lesSports); 
                lvSportAssociation.setItems(null);
            } 
            alert.showAndWait();
            
            
            okClick=true;
        }
    } 
    
}
