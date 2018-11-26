/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Rabelais
 */
public class FXMainApplicationGestionGymnase extends Application
{    
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) 
    {
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Gestion du gymnase");
        
        try
        {
            //chargement du layout racine à partir du fichier fxml file
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLNoeudRacine.fxml"));
            rootLayout=(BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();            
        }
        catch (IOException e)
        {
            //Exception qui intervient si le fichier fxml file n'a pas pu être chargé
            e.printStackTrace();
        }
        
        AfficherAccueil();
    }
    
    public void AfficherAccueil()
    {
        try
        {
            // Charge le fichier fxml (FenFXML_Coordo) et le place au centre du layout principal
            FXMLLoader loader = new FXMLLoader(FXMainApplicationGestionGymnase.class.getResource("/view/FXMLAccueil.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();
            rootLayout.setCenter(overviewPage);
        }

        catch (IOException e)
        {
            //Exception qui intervient si le fichier fxml n'a pas pu être chargé
            e.printStackTrace();
        }
    }
       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
    
    
}
