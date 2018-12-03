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
 * @author Rabelais
 */
public class GestionReservationBdD
{
    public static ObservableList<Reservation> getReservationSalle(Salle pUneSalle,Date pUneDate)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Reservation> lesReservationsSalle= FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            jeuEnr = stmt.executeQuery("SELECT * FROM RESERVATION,SALLE,ASSOCIATION WHERE RESERVATION.NumSalle=SALLE.NumSalle AND ASSOCIATION.NumAssociation=RESERVATION.NumAssociation AND SALLE.NumSalle="+pUneSalle.getNumSalle()+" AND Date='"+pUneDate+"' ORDER BY date,heure");
            if (jeuEnr.next())
            {
                Reservation UneReservation;  
                Association UneAssociation;
                UneAssociation= new Association(jeuEnr.getInt("NumAssociation"),jeuEnr.getString("NomAssociation"), jeuEnr.getString("AdresseAssociation"),jeuEnr.getString("CPAssociation"),jeuEnr.getString("VilleAssociation"),jeuEnr.getString("NomResponsable"));
                UneReservation= new Reservation(jeuEnr.getInt("NumSalle"), jeuEnr.getDate("date"),jeuEnr.getInt("heure"),UneAssociation);
                lesReservationsSalle.add(UneReservation);
                while (jeuEnr.next())
                {
                    UneAssociation= new Association(jeuEnr.getInt("NumAssociation"),jeuEnr.getString("NomAssociation"), jeuEnr.getString("AdresseAssociation"),jeuEnr.getString("CPAssociation"),jeuEnr.getString("VilleAssociation"),jeuEnr.getString("NomResponsable"));
                    UneReservation= new Reservation(jeuEnr.getInt("NumSalle"), jeuEnr.getDate("date"),jeuEnr.getInt("heure"),UneAssociation);
                    lesReservationsSalle.add(UneReservation);
                } 
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
        return lesReservationsSalle;
    }
    
    public static int ajouterReservation(Reservation pUneReservation)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
        int nbLignes=0;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();  
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbReservations" + "  FROM RESERVATION WHERE NumSalle="+pUneReservation.getNumSalle()+" AND Date='"+pUneReservation.getDate()+"' AND Heure = "+pUneReservation.getHeure());
            jeuEnr.next();
            nbLignes=jeuEnr.getInt("NbReservations");
            if(nbLignes == 0)
            {
                nbLignes = stmt.executeUpdate("INSERT INTO RESERVATION VALUES ("+pUneReservation.getNumSalle()+",'"+pUneReservation.getDate()+"',"+pUneReservation.getHeure()+","+pUneReservation.getUneAssociation().getNumAssociation()+")");
            }
            else
            {
                nbLignes=-1;
            }
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
        return nbLignes;
    }
    
    public static int supprimerReservation(Reservation pUneReservation)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
        int nbLignes=0;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();  
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbReservations" + "  FROM RESERVATION WHERE NumSalle="+pUneReservation.getNumSalle()+" AND Date='"+pUneReservation.getDate()+"' AND Heure = "+pUneReservation.getHeure());
            jeuEnr.next();
            nbLignes=jeuEnr.getInt("NbReservations");
            if(nbLignes == 1)
            {
                nbLignes = stmt.executeUpdate("DELETE FROM RESERVATION WHERE NumSalle="+pUneReservation.getNumSalle()+" AND Date='"+pUneReservation.getDate()+"' AND Heure="+pUneReservation.getHeure());
            }
            else
            {
                nbLignes=-1;
            }
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
        return nbLignes;
    }
    
    public static ObservableList<Integer> getHeureDisponible(Salle pUneSalle,Date pUneDate)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Integer> lesheuresReservees= FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();  
            jeuEnr = stmt.executeQuery("SELECT heure FROM RESERVATION WHERE NumSalle="+pUneSalle.getNumSalle()+" AND Date='"+pUneDate+"' ORDER BY heure ASC");
            while (jeuEnr.next())
            {             
                lesheuresReservees.add(jeuEnr.getInt("heure"));
            } 
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
        return lesheuresReservees;
    }
    
    public static ObservableList<Planning> getPlanning(Salle pUneSalle,LocalDate pUneDate)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<Planning> lePlanning= FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            int NbJours;
            Association UneAssociation ; 
            if(pUneDate.getDayOfWeek().getValue() != 1)
            {
                pUneDate = pUneDate.minusDays(pUneDate.getDayOfWeek().getValue()-1);
            }
            jeuEnr = stmt.executeQuery("SELECT * FROM RESERVATION,SALLE,ASSOCIATION WHERE RESERVATION.NumSalle=SALLE.NumSalle AND ASSOCIATION.NumAssociation=RESERVATION.NumAssociation AND SALLE.NumSalle="+pUneSalle.getNumSalle()+" AND Date BETWEEN '"+pUneDate+"' AND '"+pUneDate.plusDays(6)+"' ORDER BY heure");
            if(jeuEnr.next())
            {
                String UnJour =  jeuEnr.getString("date");
                NbJours= TestDate(pUneDate,UnJour);
                Integer UnHoraire =  jeuEnr.getInt("heure");
                UneAssociation= new Association(jeuEnr.getInt("NumAssociation"),jeuEnr.getString("NomAssociation"), jeuEnr.getString("AdresseAssociation"),jeuEnr.getString("CPAssociation"),jeuEnr.getString("VilleAssociation"),jeuEnr.getString("NomResponsable"));
                Planning UnPlanning =  new Planning(UneAssociation,NbJours,UnHoraire);
                lePlanning.add(UnPlanning);
                while (jeuEnr.next())
                {   
                    UneAssociation= new Association(jeuEnr.getInt("NumAssociation"),jeuEnr.getString("NomAssociation"), jeuEnr.getString("AdresseAssociation"),jeuEnr.getString("CPAssociation"),jeuEnr.getString("VilleAssociation"),jeuEnr.getString("NomResponsable"));
                    UnJour =  jeuEnr.getString("date");
                    NbJours= TestDate(pUneDate,UnJour);
                    UnHoraire = jeuEnr.getInt("heure");
                    lePlanning.add( new Planning (UneAssociation,NbJours,UnHoraire));
                }  
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
        return lePlanning;
    }
    
    public static int TestDate(LocalDate pUneDate , String pUnJour)
    {
        int day,comparaison;
        String tab[]=pUnJour.split("-");
        day  = Integer.parseInt(tab[2]); 
        comparaison = day - pUneDate.getDayOfMonth(); 
        return comparaison+1;
    }
}
