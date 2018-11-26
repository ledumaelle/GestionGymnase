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
public class GestionSalleBdD
{
    public static ObservableList<Salle> getSalle()
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Salle> lesSalles = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT * FROM SALLE");
            Salle UneSalle;
            while (jeuEnr.next())
            {
                UneSalle= new Salle(jeuEnr.getString("refSalle"), jeuEnr.getFloat("surface"),jeuEnr.getString("TypeRevetement"));
                lesSalles.add(UneSalle);
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
        return lesSalles;
    }
    
    public static ObservableList<Salle> getSalleParSport(Sport pUnSport)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Salle> lesSallesSport = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT * FROM SALLE,ACCUEILLIR WHERE SALLE.refSalle=ACCUEILLIR.refSalle AND NomSportAutorise='" + pUnSport.getNomSport()+ "'");
            Salle UneSalle;
            while (jeuEnr.next())
            {
                UneSalle= new Salle(jeuEnr.getString("refSalle"), jeuEnr.getFloat("surface"),jeuEnr.getString("TypeRevetement"));
                lesSallesSport.add(UneSalle);
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
        return lesSallesSport;
    }
        
    
}
