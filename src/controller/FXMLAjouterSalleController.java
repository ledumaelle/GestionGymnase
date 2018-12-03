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
public class FXMLAjouterSalleController implements Initializable
{

    private Stage dialogStage;
    private boolean okClick = false;  
    //liste de droite
    ObservableList<Sport> lesSportsSalles = FXCollections.observableArrayList();
    
    @FXML
    private ListView lvSalle; 
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
        lvSalle.setItems(lesSalles);
        ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
        lvSport.setItems(lesSports); 
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
    
    @FXML
    public boolean isOkClick()
    {
        return okClick;
    }
    
    private boolean isInputValid()
    {
        String messageErreur = "";
        boolean retour = true;
           
        if (txtNom.getText() == null || txtNom.getText().length()<=0)
        {
            messageErreur =messageErreur + "\n" + "Nom invalide";
        }
        
        if (txtSurface.getText() == null || txtSurface.getText().length()<=0)
        {
            messageErreur =messageErreur + "\n" + "Surface invalide";
        }

        if (txtTypeRevetement.getText() == null || txtTypeRevetement.getText().length()<=0)
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
        if(lvSport.getSelectionModel().getSelectedItem() != null && (txtNom.getText() != null || txtNom.getText().length() >0))
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
        if(txtNom.getText() != null || txtNom.getText().length() >0)
        {
            Sport UnSport = (Sport) (lvSportSalle.getSelectionModel().getSelectedItem());
            lesSportsSalles.remove(UnSport);
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
    
    @FXML
    public void handleBtnAjouter()
    {
        if (isInputValid())
        {
            boolean valide=false;
            //AJOUT SALLE
            int nbLignes;
            //Salle temporaire
            Salle TempSalle = new Salle (txtNom.getText(),Float.parseFloat(txtSurface.getText()),txtTypeRevetement.getText());
            //Valide ?
            nbLignes=GestionSalleBdD.ajouterSalle(TempSalle);
            Alert alert=new Alert(Alert.AlertType.INFORMATION); 
            if (nbLignes == -1)
            {
                alert.setTitle("Echec");
                alert.setHeaderText("Ajout impossible");
                alert.setContentText("La salle n'est pas valide !");
                valide=false;
            }
            else
            {
                alert.setTitle("Succès");
                alert.setHeaderText("Ajout de la salle effectuée");
                alert.setContentText("Nb de ligne(s) affectée(s) : " + nbLignes); 
                txtNom.setText(null);
                txtSurface.setText(null);
                txtTypeRevetement.setText(null);              
                valide=true;
            }
            alert.showAndWait();   
            
            if (valide==true)
            {
               //AJOUT ACCUEILLIR
                nbLignes=0;
                int i,j;
                for(i=0;i<lesSportsSalles.size();i++)
                {                
                    //BUUUUUUUUUUUUUUG
                    Salle UneSalle = GestionSalleBdD.getSalle(txtNom.getText(),Float.parseFloat(txtSurface.getText()), txtTypeRevetement.getText());
                    Sport UnSport = new Sport (lesSportsSalles.get(i).getNumSport(),lesSportsSalles.get(i).getNomSport());
                    //si le sport n'existe pas dans la BdD
                    if(!(GestionSportBdD.existe(UnSport)))
                    {                    
                        //ajout
                        GestionSportBdD.ajouterSport(UnSport);
                    }
                    nbLignes=nbLignes + GestionSalleBdD.ajouterSport(UneSalle,UnSport);
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
                    ObservableList<Sport> lesSports =  GestionSportBdD.getSport();
                    lvSport.setItems(null);
                    lvSport.setItems(lesSports); 
                    lvSportSalle.setItems(null);
                } 
                alert.showAndWait(); 
            } 
            okClick=true;
        }
    } 
}
