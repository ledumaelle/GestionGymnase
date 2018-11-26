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
            jeuEnr = stmt.executeQuery("SELECT * FROM RESERVATION,SALLE WHERE RESERVATION.RefSalle=SALLE.RefSalle AND SALLE.RefSalle=RESERVATION.RefSalle AND SALLE.RefSalle='"+pUneSalle.getRefSalle()+"' AND Date='"+pUneDate+"' ORDER BY date,heure");
            if (jeuEnr != null)
            {
                Reservation UneReservation;  
                while (jeuEnr.next())
                {
                    UneReservation= new Reservation(jeuEnr.getString("refSalle"), jeuEnr.getDate("date"),jeuEnr.getInt("heure"),jeuEnr.getString("refAssociation"));
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbReservations" + "  FROM RESERVATION WHERE RefSalle='"+pUneReservation.getRefSalle()+"' AND Date='"+pUneReservation.getDate()+"' AND Heure = "+pUneReservation.getHeure());
            jeuEnr.next();
            nbLignes=jeuEnr.getInt("NbReservations");
            if(nbLignes == 0)
            {
                nbLignes = stmt.executeUpdate("INSERT INTO RESERVATION VALUES ('"+pUneReservation.getRefSalle()+"','"+pUneReservation.getDate()+"',"+pUneReservation.getHeure()+",'"+pUneReservation.getRefAssociation()+"')");
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbReservations" + "  FROM RESERVATION WHERE RefSalle='"+pUneReservation.getRefSalle()+"' AND Date='"+pUneReservation.getDate()+"' AND Heure = "+pUneReservation.getHeure());
            jeuEnr.next();
            nbLignes=jeuEnr.getInt("NbReservations");
            if(nbLignes == 1)
            {
                nbLignes = stmt.executeUpdate("DELETE FROM RESERVATION WHERE RefSalle='"+pUneReservation.getRefSalle()+"' AND Date='"+pUneReservation.getDate()+"' AND Heure="+pUneReservation.getHeure());
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
            jeuEnr = stmt.executeQuery("SELECT heure FROM RESERVATION WHERE RefSalle='"+pUneSalle.getRefSalle()+"' AND Date='"+pUneDate+"' ORDER BY heure ASC");
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
            if(pUneDate.getDayOfWeek().getValue() != 0)
            {
                pUneDate = pUneDate.minusDays(pUneDate.getDayOfWeek().getValue());
            }
            jeuEnr = stmt.executeQuery("SELECT * FROM RESERVATION,SALLE WHERE RESERVATION.RefSalle=SALLE.RefSalle AND SALLE.RefSalle=RESERVATION.RefSalle AND SALLE.RefSalle='"+pUneSalle.getRefSalle()+"' AND Date BETWEEN '"+pUneDate+"' AND '"+pUneDate.plusDays(6)+"' ORDER BY heure");
            if(jeuEnr.next())
            {
                String UnJour =  jeuEnr.getString("date");
                NbJours= TestDate(pUneDate,UnJour);
                Integer UnHoraire =  jeuEnr.getInt("heure");
                Planning UnPlanning =  new Planning(jeuEnr.getString("refAssociation"),NbJours,UnHoraire);
                lePlanning.add(UnPlanning);
                while (jeuEnr.next())
                {   
                    UnJour =  jeuEnr.getString("date");
                    NbJours= TestDate(pUneDate,UnJour);
                    UnHoraire = jeuEnr.getInt("heure");
                    lePlanning.add( new Planning (jeuEnr.getString("refAssociation"),NbJours,UnHoraire));
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
