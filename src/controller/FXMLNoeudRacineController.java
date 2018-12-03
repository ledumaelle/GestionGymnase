/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FXMLNoeudRacineController implements Initializable
{
    private Stage primaryStage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }              
    
    @FXML
    public void handleAfficherSalle()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLAfficherSalle.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Afficher les salles");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Affiche la boite de dialogue et attend que l'utilisateur la ferme
            dialogStage.showAndWait();
        }

        catch(IOException ioe)
        {
          System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
        }
    }  
    
    @FXML
    public boolean handleAjouterReservation()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLAjouterReservation.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter une réservation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            FXMLAjouterReservationController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return controleur.isOkClick();
        }

        catch(IOException ioe)
        {
          System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
          return false;
        }
    }
    
    @FXML
    public boolean handleAjouterSport()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLAjouterSport.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter un sport");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            FXMLAjouterSportController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return controleur.isOkClick();
        }

        catch(IOException ioe)
        {
          System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
          return false;
        }
    }  
    
    @FXML
    public void handleAfficherReservation()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLAfficherReservation.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Afficher le planning");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Affiche la boite de dialogue et attend que l'utilisateur la ferme
            dialogStage.showAndWait();
        }

        catch(IOException ioe)
        {
          System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
        }
    }  
    
    @FXML
    public void handleAfficherAssociation()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLAfficherAssociation.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Afficher une association");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Affiche la boite de dialogue et attend que l'utilisateur la ferme
            dialogStage.showAndWait();
        }

        catch(IOException ioe)
        {
          System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
        }
    }  
    
    @FXML
    public boolean handleModifierSport()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLModifierSport.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier un sport");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            FXMLModifierSportController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return controleur.isOkClick();
        }

        catch(IOException ioe)
        {
          System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
          return false;
        }
    }
    
    @FXML 
    public boolean handleAjouterAssociation()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLAjouterAssociation.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter une association");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            FXMLAjouterAssociationController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return controleur.isOkClick();
        }

        catch(IOException ioe)
        {
          System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
          return false;
        }
    }
    
    @FXML
    public boolean handleModifierAssociation()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLModifierAssociation.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier une association");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            FXMLModifierAssociationController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return controleur.isOkClick();
        }

        catch(IOException ioe)
        {
          System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
          return false;
        }
    }
    
    @FXML
    public boolean handleModifierSalle()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLModifierSalle.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modifier une salle");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            FXMLModifierSalleController controleur = loader.getController();
            controleur.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            return controleur.isOkClick();
        }

        catch(IOException ioe)
        {
          System.out.println("ERREUR chargement boite dialogue:" + ioe.getMessage());
          return false;
        }
    }
        
}
