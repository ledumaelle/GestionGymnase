/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rabelais
 */
public class GestionAssociationBdD
{
    public static ObservableList<Association> getAssociation()
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Association> lesAssociations = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("select * from association");
            Association UneAssociation;
            while (jeuEnr.next())
            {
                UneAssociation= new Association(jeuEnr.getString("refAssociation"), jeuEnr.getString("AdresseAssociation"),jeuEnr.getString("VilleAssociation"),jeuEnr.getString("CPAssociation"),jeuEnr.getString("NomResponsable"));
                lesAssociations.add(UneAssociation);
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL " + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesAssociations;
    }
}
