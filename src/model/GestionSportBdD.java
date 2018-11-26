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
public class GestionSportBdD
{    
    public static ObservableList<Sport> getSport()
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Sport> lesSports = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT * FROM SPORT ORDER BY NomSport ASC");
            Sport UnSport;
            while (jeuEnr.next())
            {
                UnSport= new Sport(jeuEnr.getString("NomSport"));
                lesSports.add(UnSport);
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
        return lesSports;
    }
    
    public static ObservableList<Sport> getSportAssociation(Association pUneAssociation)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Sport> lesSportsAssociation = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT * FROM SPORT,PRATIQUER WHERE SPORT.NomSport=PRATIQUER.NomSport AND RefAssociation='" + pUneAssociation.getRefAssociation() + "'");
            Sport UnSport;
            while (jeuEnr.next())
            {
                UnSport= new Sport(jeuEnr.getString("NomSport"));
                lesSportsAssociation.add(UnSport);
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
        return lesSportsAssociation;
    }
    
    public static ObservableList<Sport> getSportParSalle(Salle pUneSalle)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Sport> lesSportsParSalle = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT NomSportAutorise FROM SALLE,ACCUEILLIR WHERE SALLE.RefSalle=ACCUEILLIR.RefSalle AND SALLE.RefSalle='"+pUneSalle.getRefSalle()+"'");
            Sport UnSport;
            while (jeuEnr.next())
            {
                UnSport= new Sport(jeuEnr.getString("NomSportAutorise"));
                lesSportsParSalle.add(UnSport);
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
        return lesSportsParSalle;
    }
    
    public static int ajouterSport(Object pUnSport)
    {
        Connection conn; //connexion
        int NbLignes=0;
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSports" + "  FROM SPORT WHERE NomSport = '"+pUnSport+"'");
            jeuEnr.next();
            NbLignes=jeuEnr.getInt("NbSports");
            if(NbLignes == 0)
            {
                NbLignes = stmt.executeUpdate("INSERT INTO SPORT VALUES ('"+pUnSport+"')");
            }
            else
            {
                NbLignes=-1;
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
        return NbLignes;
    }
}
