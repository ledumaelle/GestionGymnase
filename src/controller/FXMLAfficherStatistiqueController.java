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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.GestionSalleBdD;
import model.Salle;
import model.GestionStatistiqueBdD;
import model.Association;


/**
 * FXML Controller class
 *
 * @author LE DU Maëlle
 */
public class FXMLAfficherStatistiqueController implements Initializable
{

    @FXML
    private ComboBox cmbSalle; 
    @FXML
    private Label lblAssociationMax;
    @FXML
    private Label lblAssociationMin;
    @FXML
    private Label lblDateMax;
    @FXML
    private Label lblDateMin;
    @FXML
    private Label lblHeureMax;
    @FXML
    private Label lblHeureMin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ObservableList<Salle> lesSalles =  GestionSalleBdD.getSalle();
        cmbSalle.setItems(lesSalles); 
    }    
    
    public void handleSelectionSalle()
    {
        //MAX ASSOCIATION
        Salle UneSalle = (Salle)(cmbSalle.getSelectionModel().getSelectedItem());
        ObservableList<Association> lesAssociationsMax =  GestionStatistiqueBdD.getAssociationMax(UneSalle);
        int i ;
        String CdC="";
        for (i=0;i<lesAssociationsMax.size();i++)
        {       
            if (i==0)
            {
                CdC = lesAssociationsMax.get(i).getNomAssociation();
            }
            else
            {
                 CdC=CdC + " / " + lesAssociationsMax.get(i).getNomAssociation(); 
            }
           
        }     
            lblAssociationMax.setText(CdC);
            
        //MIN ASSOCIATION
        ObservableList<Association> lesAssociationsMin =  GestionStatistiqueBdD.getAssociationMin(UneSalle);
        CdC="";
        for (i=0;i<lesAssociationsMin.size();i++)
        {       
            if (i==0)
            {
                CdC = lesAssociationsMin.get(i).getNomAssociation();
            }
            else
            {
                 CdC=CdC + " / " + lesAssociationsMin.get(i).getNomAssociation(); 
            }
           
        }
            lblAssociationMin.setText(CdC);
            
        //MAX DATE
        ObservableList<String> lesDatesMax =  GestionStatistiqueBdD.getDateMax(UneSalle);
        CdC="";
        for (i=0;i<lesDatesMax.size();i++)
        {       
            if (i==0)
            {
                CdC = lesDatesMax.get(i);
            }
            else
            {
                 CdC=CdC + " / " + lesDatesMax.get(i); 
            }
           
        }     
            lblDateMax.setText(CdC);
            
        //MIN DATE
        ObservableList<String> lesDatesMin =  GestionStatistiqueBdD.getDateMin(UneSalle);
        CdC="";
        for (i=0;i<lesDatesMin.size();i++)
        {       
            if (i==0)
            {
                CdC = lesDatesMin.get(i);
            }
            else
            {
                 CdC=CdC + " / " + lesDatesMin.get(i); 
            }
           
        }
            lblDateMin.setText(CdC);
            
        //MAX HEURE
        ObservableList<Integer> lesHeuresMax =  GestionStatistiqueBdD.getHeureMax(UneSalle);
        CdC="";
        for (i=0;i<lesHeuresMax.size();i++)
        {       
            if (i==0)
            {
                CdC = lesHeuresMax.get(i).toString();
            }
            else
            {
                 CdC=CdC + " / " + lesHeuresMax.get(i); 
            }
           
        }     
            lblHeureMax.setText(CdC);
            
        //MIN HEURE
        ObservableList<Integer> lesHeuresMin =  GestionStatistiqueBdD.getHeureMin(UneSalle);
        CdC="";
        for (i=0;i<lesHeuresMin.size();i++)
        {       
            if (i==0)
            {
                CdC = lesHeuresMin.get(i).toString();
            }
            else
            {
                 CdC=CdC + " / " + lesHeuresMin.get(i); 
            }
           
        }
            lblHeureMin.setText(CdC);
    }
    
    
}
