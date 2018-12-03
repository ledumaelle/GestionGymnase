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
import model.GestionSalleBdD;
import model.GestionSportBdD;
import model.Salle;
import model.Sport;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FXMLModifierSalleController implements Initializable
{
    private Stage dialogStage;
    private boolean okClick = false;  
    //liste de droite
    ObservableList<Sport> lesSportsSalles = FXCollections.observableArrayList();
    
    @FXML
    private ComboBox cmbSalle; 
    @FXML
    private TextField txtNom; 
    @FXML
    private TextField txtSurface; 
    @FXML
    private TextField txtTypeRevetement; 
    @FXML
    private ListView lvSport; 
    @FXML
    private ListView lvSportSalle; 
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
        ObservableList<Salle> lesSalles =  GestionSalleBdD.getSalle();
        cmbSalle.setItems(lesSalles);
        ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
        lvSport.setItems(lesSports); 
    } 
    
    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void handleSelectionSalle()
    {
        if (cmbSalle.getSelectionModel().getSelectedItem() != null)
        {
            Salle UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
            txtNom.setText(UneSalle.getNomSalle());
            txtSurface.setText(String.valueOf(UneSalle.getSurface()));
            txtTypeRevetement.setText(UneSalle.getTypeDeRevetement());
            lesSportsSalles=GestionSportBdD.getSportParSalle(UneSalle);
            lvSportSalle.setItems(null);
            lvSportSalle.setItems(lesSportsSalles);            
            
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
           
        if (cmbSalle.getSelectionModel().getSelectedItem() == null || cmbSalle.getSelectionModel().getSelectedItem() == "")
        {
            messageErreur =messageErreur + "\n" + "Salle invalide";
        }
        
        if (txtNom.getText() == null || txtNom.getText().length()<0)
        {
            messageErreur =messageErreur + "\n" + "Nom invalide";
          }
        
        if (txtSurface.getText() == null || txtSurface.getText().length()<0)
        {
            messageErreur =messageErreur + "\n" + "Surface invalide";
         }

        if (txtTypeRevetement.getText() == null || txtTypeRevetement.getText().length()<0)
        {
            messageErreur =messageErreur+"\n" + "Type de revêtement invalide";
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
        if(cmbSalle.getSelectionModel().getSelectedItem() != null && lvSport.getSelectionModel().getSelectedItem() != null)
        {
            Sport UnSport = (Sport)(lvSport.getSelectionModel().getSelectedItem());
            if (!(lesSportsSalles.contains(UnSport)))
            {
                lesSportsSalles.add(UnSport);
                lvSportSalle.setItems(null);
                lvSportSalle.setItems(lesSportsSalles);
            }        
        }        
    }
    
    public void handleBtnEnleveSport()
    {
        if(cmbSalle.getSelectionModel().getSelectedItem() != null)
        {
            Sport UnSport = (Sport) (lvSportSalle.getSelectionModel().getSelectedItem());
            lesSportsSalles.remove(UnSport);
        }
        
    }
    
    public void handleBtnAjoutSport()
    {
        if(cmbSalle.getSelectionModel().getSelectedItem() != null)
        {
            lblNomNouveauSport.setVisible(true);
            txtNomNouveauSport.setVisible(true);
            btnNomNouveauSport.setVisible(true);
        }
    }
    
    public void handleBtnAjoutNouveauSport()
    {
        if(cmbSalle.getSelectionModel().getSelectedItem() != null)
        {
            if (!(txtNomNouveauSport.getText() == null || txtNomNouveauSport.getText().length() <= 0 ))
            {            
                int NbMaxSport = GestionSportBdD.getNbMax()+1 ;
                Sport UnSport = new Sport (NbMaxSport,txtNomNouveauSport.getText());
                if (!(lesSportsSalles.contains(UnSport)))
                {
                    lesSportsSalles.add(UnSport);
                    lvSportSalle.setItems(null);
                    lvSportSalle.setItems(lesSportsSalles);
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
            Salle UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
            int NbMaxSalle = GestionSalleBdD.getNbMax()+1 ;
            //Association temporaire
            Salle UneSalle2 = new Salle (NbMaxSalle,txtNom.getText(),Float.parseFloat(txtSurface.getText()),txtTypeRevetement.getText());
            //Valide ?
            nbLignes=GestionSalleBdD.modifierSalle(UneSalle,UneSalle2);
            Alert alert=new Alert(Alert.AlertType.INFORMATION); 
            if (nbLignes == -1)
            {
                alert.setTitle("Echec");
                alert.setHeaderText("Modification impossible");
                alert.setContentText("La salle n'est pas valide !");
            }
            else
            {
                alert.setTitle("Succès");
                alert.setHeaderText("Modification de la samme effectuée");
                alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes); 
                txtNom.setText(null);
                txtSurface.setText(null);
                txtTypeRevetement.setText(null);              
                valide=true;
            }
            alert.showAndWait();   
            
            if (valide==true)
            {
                
            }
            //MODIFIER PRATIQUER
            nbLignes=0;
            int i,j;
            for(i=0;i<lesSportsSalles.size();i++)
            {                     
                ObservableList<Sport> lesSportsAssociationsAvantModification = GestionSportBdD.getSportParSalle(UneSalle);
                UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
                Sport UnSport = new Sport (lesSportsSalles.get(i).getNumSport(),lesSportsSalles.get(i).getNomSport());
                
                //si le sport n'existe pas dans la BdD
                if(!(GestionSportBdD.existe(UnSport)))
                {                    
                    //ajout
                    GestionSportBdD.ajouterSport(UnSport);
                }
                if(!(lesSportsAssociationsAvantModification.contains(UnSport)))
                {                    
                    //ajout dans pratiquer si besoin
                    nbLignes=nbLignes + GestionSalleBdD.ajouterSport(UneSalle,UnSport);
                }
                //parcourir la liste de base des sports de l'association
                for(j=0;j<lesSportsAssociationsAvantModification.size();j++)
                {       
                    // si le sport de la nouvelle liste n'est pas dans la liste auparavent alors on le supprime 
                    if (!(lesSportsSalles.contains(lesSportsAssociationsAvantModification.get(j))))
                    {
                        Sport UnSport2 = new Sport (lesSportsAssociationsAvantModification.get(j).getNumSport(),lesSportsAssociationsAvantModification.get(j).getNomSport());
                        //supprimer dans pratiquer
                        nbLignes=nbLignes + GestionSalleBdD.supprimerSport(UneSalle,UnSport2);
                    }
                }
            }        
            if (nbLignes == -1)
            {
                alert.setTitle("Echec");
                alert.setHeaderText("Ajout impossible");
                alert.setContentText("Impossible d'ajouter un (des) sports pour cette salle !");
            }
            else
            {
                alert.setTitle("Succès");
                alert.setHeaderText("Ajout des sports pour la salle effectué");
                alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes); 
                txtNom.setText(null);
                txtSurface.setText(null);
                txtTypeRevetement.setText(null);
                cmbSalle.setItems(null);
                ObservableList<Salle> lesSalles =  GestionSalleBdD.getSalle();
                cmbSalle.setItems(lesSalles);                
                ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
                lvSport.setItems(null);
                lvSport.setItems(lesSports); 
                lvSportSalle.setItems(null);
            } 
            alert.showAndWait();
            
            
            okClick=true;
        }
    }   
    
}
