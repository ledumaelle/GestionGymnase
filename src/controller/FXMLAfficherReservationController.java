/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.GestionReservationBdD;
import model.GestionSalleBdD;
import model.Planning;
import model.Salle;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FXMLAfficherReservationController implements Initializable
{
    @FXML
    private ComboBox cmbSalle; 
    @FXML
    private DatePicker dateReservation;
    @FXML
    private GridPane gridReservation; 
    
    
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
    } 
    
    public void vider()
    {
        int i,j,k;
        for(i=1;i<14;i++)
        {
            for(j=1;j<8;j++)
            {
                for(k=0;k<gridReservation.getChildren().size();k++)                  
                {  
                    Node child = gridReservation.getChildren().get(k);
                    if(GridPane.getColumnIndex(child) != null && GridPane.getRowIndex(child) != null)
                    {
                        if(GridPane.getColumnIndex(child) == j && GridPane.getRowIndex(child)== i)
                        {
                            gridReservation.getChildren().remove(k);
                        }
                    }    
                }
            } 
        }
    }
    
    public void handleSelectionSalle()
    {
        dateReservation.setValue(null);
        vider();
    }
    
    public void handleSelectionDate()
    {
        vider();
        if (cmbSalle.getSelectionModel().getSelectedItem() != null && dateReservation.getValue() != null ) 
        {
            Salle UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
            LocalDate UneDate = dateReservation.getValue();
            int i,j,k;
            int l=1;
            int UnHoraire=0;
            int UnJour=0;
            String LAssoc="";
            ObservableList<Planning> lePlanning= GestionReservationBdD.getPlanning(UneSalle,UneDate);
            ArrayList<String> lesAssociations = new ArrayList();
            if (!(lePlanning.isEmpty()))
            {
                for(i=8;i<21;i++)
                {
                    for (j=1;j<8;j++)
                    {
                        for (k=0;k<lePlanning.size();k++)
                        {
                            UnHoraire = lePlanning.get(k).getUnHoraire();
                            UnJour = lePlanning.get(k).getUnJour();
                            LAssoc = lePlanning.get(k).getRefAssociation();
                            
                            if(UnHoraire == i && UnJour == j)
                            {
                                break;
                            }
                        }
                        if (UnHoraire == i && UnJour == j)
                        {
                            if (LAssoc != null)
                            {
                                if(!(lesAssociations.contains(LAssoc)))
                                {                                    
                                    lesAssociations.add(lesAssociations.size(),LAssoc);
                                }
                                Label lblCase = new Label();
                                lblCase.setText(LAssoc);
                                lblCase.setStyle("-fx-text-fill: red;");
                                GridPane.setConstraints(lblCase,j,l,1,1,HPos.CENTER,VPos.CENTER);
                                gridReservation.getChildren().addAll(lblCase);
                            }
                        }
                    }
                    l++;
                }  
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
     
