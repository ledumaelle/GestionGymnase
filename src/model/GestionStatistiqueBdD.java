/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LE DU Maëlle
 */
public class GestionStatistiqueBdD
{
    public static ObservableList<Association> getAssociationMax(Salle pUneSalle)
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbReservations" +" ,ASSOCIATION.NumAssociation,NomAssociation,AdresseAssociation,CPAssociation,VilleAssociation,NomResponsable FROM SALLE,ASSOCIATION,RESERVATION WHERE RESERVATION.numSalle=SALLE.numSalle AND ASSOCIATION.numAssociation=RESERVATION.numAssociation AND SALLE.numSalle=" + pUneSalle.getNumSalle() + " GROUP BY ASSOCIATION.NumAssociation HAVING COUNT(*) >= ALL ( SELECT COUNT(*) AS " +"NbReservations" + " FROM SALLE,ASSOCIATION,RESERVATION WHERE RESERVATION.numSalle=SALLE.numSalle AND ASSOCIATION.numAssociation=RESERVATION.numAssociation AND SALLE.numSalle="+pUneSalle.getNumSalle()+" GROUP BY ASSOCIATION.NumAssociation)");
            Association UneAssociation;
            while (jeuEnr.next())
            {
                UneAssociation= new Association(jeuEnr.getInt("NumAssociation"),jeuEnr.getString("NomAssociation"), jeuEnr.getString("AdresseAssociation"),jeuEnr.getString("CPAssociation"),jeuEnr.getString("VilleAssociation"),jeuEnr.getString("NomResponsable"));
                lesAssociations.add(UneAssociation);
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionStatistique.getAssociationMax() : " + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesAssociations;
    }
    
    public static ObservableList<Association> getAssociationMin(Salle pUneSalle)
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbReservations" +" ,ASSOCIATION.NumAssociation,NomAssociation,AdresseAssociation,CPAssociation,VilleAssociation,NomResponsable FROM SALLE,ASSOCIATION,RESERVATION WHERE RESERVATION.numSalle=SALLE.numSalle AND ASSOCIATION.numAssociation=RESERVATION.numAssociation AND SALLE.numSalle=" + pUneSalle.getNumSalle() + " GROUP BY ASSOCIATION.NumAssociation HAVING COUNT(*) <= ALL ( SELECT COUNT(*) AS " +"NbReservations" + " FROM SALLE,ASSOCIATION,RESERVATION WHERE RESERVATION.numSalle=SALLE.numSalle AND ASSOCIATION.numAssociation=RESERVATION.numAssociation AND SALLE.numSalle="+pUneSalle.getNumSalle()+" GROUP BY ASSOCIATION.NumAssociation)");
            Association UneAssociation;
            while (jeuEnr.next())
            {
                UneAssociation= new Association(jeuEnr.getInt("NumAssociation"),jeuEnr.getString("NomAssociation"), jeuEnr.getString("AdresseAssociation"),jeuEnr.getString("CPAssociation"),jeuEnr.getString("VilleAssociation"),jeuEnr.getString("NomResponsable"));
                lesAssociations.add(UneAssociation);
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionStatistique.getAssociationMin() : " + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesAssociations;
    }
    
    public static ObservableList<String> getDateMax(Salle pUneSalle)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<String> lesDatesMax = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT COUNT(*), DATE FROM RESERVATION WHERE NumSalle = "+pUneSalle.getNumSalle()+" GROUP BY DATE HAVING count(*) >= ALL ( SELECT COUNT(*) FROM RESERVATION  WHERE NumSalle ="+pUneSalle.getNumSalle()+" GROUP BY DATE )");
            while (jeuEnr.next())
            {
                lesDatesMax.add(DateFrancaise(jeuEnr.getString("date")));
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionStatistique.getDateMax() : " + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesDatesMax;
    }
    
    public static ObservableList<String> getDateMin(Salle pUneSalle)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<String> lesDatesMin = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT COUNT(*), DATE FROM RESERVATION WHERE NumSalle = "+pUneSalle.getNumSalle()+" GROUP BY DATE HAVING count(*) <= ALL ( SELECT COUNT(*) FROM RESERVATION  WHERE NumSalle ="+pUneSalle.getNumSalle()+" GROUP BY DATE )");
            while (jeuEnr.next())
            {
                lesDatesMin.add(DateFrancaise(jeuEnr.getString("date")));
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionStatistique.getDateMin() : " + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesDatesMin;
    }
    
    public static ObservableList<Integer> getHeureMax(Salle pUneSalle)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Integer> lesHeuresMax = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
                jeuEnr = stmt.executeQuery("SELECT COUNT(*), Heure FROM RESERVATION WHERE NumSalle = "+pUneSalle.getNumSalle()+" GROUP BY Heure HAVING count(*) >= ALL ( SELECT COUNT(*) FROM RESERVATION  WHERE NumSalle ="+pUneSalle.getNumSalle()+" GROUP BY Heure )");
            while (jeuEnr.next())
            {
                lesHeuresMax.add(jeuEnr.getInt("heure"));
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionStatistique.getHeureMax() : " + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesHeuresMax;
    }
    
    public static ObservableList<Integer> getHeureMin(Salle pUneSalle)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Integer> lesHeuresMin = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
                jeuEnr = stmt.executeQuery("SELECT COUNT(*), Heure FROM RESERVATION WHERE NumSalle = "+pUneSalle.getNumSalle()+" GROUP BY Heure HAVING count(*) <= ALL ( SELECT COUNT(*) FROM RESERVATION  WHERE NumSalle ="+pUneSalle.getNumSalle()+" GROUP BY Heure )");
            while (jeuEnr.next())
            {
                lesHeuresMin.add(jeuEnr.getInt("heure"));
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionStatistique.getHeureMin() : " + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesHeuresMin;
    }
    
    public static String DateFrancaise(String pUneDate)
    {
        String annee,mois,jour,NouvelleDate;
        String tab[]=String.valueOf(pUneDate).split("-");
        annee=tab[0];
        mois=tab[1];
        jour=tab[2];
        NouvelleDate=jour+"-"+mois+"-"+annee;
        return NouvelleDate;
    }
}
