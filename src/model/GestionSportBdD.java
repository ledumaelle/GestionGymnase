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
                UnSport= new Sport(jeuEnr.getInt("NumSport"),jeuEnr.getString("NomSport"));
                lesSports.add(UnSport);
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionSportBdD.getSport()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesSports;
    }
    
    public static int getNbMax()
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
        int NbMax=0;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT MAX(NumSport)  AS " + "NbMaxSports" + " FROM SPORT");
            if (jeuEnr.next())
            {                
                NbMax=jeuEnr.getInt("NbMaxSports");
            }
                			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionSportBdD.getNbMax()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbMax;
    }
    
    public static ObservableList<String> getNomSportAssociation(Association pUneAssociation)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        ObservableList<String> lesSportsAssociation = FXCollections.observableArrayList();
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT * FROM SPORT,PRATIQUER WHERE SPORT.NumSport=PRATIQUER.NumSport AND NumAssociation=" + pUneAssociation.getNumAssociation());
            Sport UnSport;
            while (jeuEnr.next())
            {
                UnSport= new Sport(jeuEnr.getInt("NumSport"),jeuEnr.getString("NomSport"));
                lesSportsAssociation.add(UnSport.getNomSport());
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionSportBdD.getSportAssociation()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesSportsAssociation;
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
            jeuEnr = stmt.executeQuery("SELECT * FROM SPORT,PRATIQUER WHERE SPORT.NumSport=PRATIQUER.NumSport AND NumAssociation=" + pUneAssociation.getNumAssociation());
            Sport UnSport;
            while (jeuEnr.next())
            {
                UnSport= new Sport(jeuEnr.getInt("NumSport"),jeuEnr.getString("NomSport"));
                lesSportsAssociation.add(UnSport);
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionSportBdD.getSportAssociation()" + sqle.getMessage());
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
            jeuEnr = stmt.executeQuery("SELECT Sport.NumSport,NomSport FROM SALLE,ACCUEILLIR,SPORT WHERE SALLE.NumSalle=ACCUEILLIR.NumSalle AND SPORT.NumSport=ACCUEILLIR.NumSport AND SALLE.NumSalle="+pUneSalle.getNumSalle());
            Sport UnSport;
            while (jeuEnr.next())
            {
                UnSport= new Sport(jeuEnr.getInt("NumSport"),jeuEnr.getString("NomSport"));
                lesSportsParSalle.add(UnSport);
            }           			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionSportBdD.getSportParSalle()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesSportsParSalle;
    }
    
    public static int ajouterSport(Sport pUnSport)
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
            //test si le nom du sport existe déjà car on sait déjà que l'ID n'est pas dupliqué
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSports" + "  FROM SPORT WHERE NomSport = '"+pUnSport.getNomSport()+"'");
            if (jeuEnr.next())
            {
                NbLignes=jeuEnr.getInt("NbSports");
                if(NbLignes == 0)
                {
                    NbLignes = stmt.executeUpdate("INSERT INTO SPORT(nomSport) VALUES ('"+pUnSport.getNomSport()+"')");
                }
                else
                {
                    NbLignes=-1;
                } 
            }                      			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionSportBdD.ajouterSport()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbLignes;
    }
    
    public static boolean existe(Sport pUnSport)
    {
        Connection conn; //connexion
        boolean existe=false;
        int NbLignes;
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSports" + "  FROM SPORT WHERE NumSport = "+pUnSport.getNumSport());
            if(jeuEnr.next())
            {
                NbLignes=jeuEnr.getInt("NbSports"); 
                if (NbLignes==1)    
                {
                    existe=true;
                }
            }            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionSportBdD.existe()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return existe;
    }
    
    public static int modifierSport(Sport pUnSport, String pNom)
    {
        Connection conn; //connexion
        int NbLignes=-1;
	Statement stmt;
	ResultSet jeuEnr;
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSports" + "  FROM SPORT WHERE NomSport = '"+pNom+"'");
            if(jeuEnr.next())
            {
               NbLignes=jeuEnr.getInt("NbSports");
                if(NbLignes == 0)
                {
                    NbLignes = stmt.executeUpdate("UPDATE Sport SET NomSport ='"+pNom+"' WHERE NomSport='"+pUnSport.getNomSport()+"'");
                }
                else
                {
                    NbLignes=-1;
                }  
            }                     			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionSportBdD.modifierSport()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbLignes;
    }
}
