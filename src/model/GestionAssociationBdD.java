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
            jeuEnr = stmt.executeQuery("SELECT * from ASSOCIATION ORDER BY NomAssociation");
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
            System.out.println("ERREUR SQL GestionAssociationBdD.getAssociation()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return lesAssociations;
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
            jeuEnr = stmt.executeQuery("SELECT MAX(NumAssociation)  AS " + "NbMaxAssociations" + " FROM ASSOCIATION");
            if (jeuEnr.next())
            {                
                NbMax=jeuEnr.getInt("NbMaxAssociations");
            }
                			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionAssociationBdD.getNbMax()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbMax;
    }
    
    public static int ajouterAssociation(Association pUneAssociation)
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbAssociations" + "  FROM ASSOCIATION WHERE NumAssociation = "+pUneAssociation.getNumAssociation());
            if (jeuEnr.next())
            {
                NbLignes=jeuEnr.getInt("NbAssociations");
                if(NbLignes == 0)
                {
                    NbLignes = stmt.executeUpdate("INSERT INTO ASSOCIATION(nomAssociation,AdresseAssociation,CPAssociation,VilleAssociation,NomResponsable) VALUES ('"+pUneAssociation.getNomAssociation()+"' , '"+pUneAssociation.getAdresseAssociation()+"' , '"+pUneAssociation.getVilleAssociation()+"', '"+pUneAssociation.getCPAssociation()+"', '"+pUneAssociation.getNomResponsable()+"')");
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
            System.out.println("ERREUR SQL GestionAssociationBdD.ajouterAssociation()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbLignes;
    }
    
    public static int modifierAssociation(Association pUneAssociation1, Association pUneAssociation2)
    {
        Connection conn; //connexion
        int NbLignesAssociation1=0;
        int NbLignesAssociation2=0;
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
            
            //MODIFIER ASSOCIATION
            //si l'association existe
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbAssociations" + "  FROM ASSOCIATION WHERE NumAssociation = "+pUneAssociation1.getNumAssociation());
            if (jeuEnr.next())
            {
                NbLignesAssociation1=jeuEnr.getInt("NbAssociations");
                jeuEnr.close();
            }
            
            //si la nouvelle ref n'existe pas déjà à part si c le mm nom que la 1 
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbAssociations" + "  FROM ASSOCIATION WHERE NomAssociation = '"+pUneAssociation2.getNomAssociation()+"'");
            if (jeuEnr.next())
            {
                NbLignesAssociation2=jeuEnr.getInt("NbAssociations");
                jeuEnr.close();
            }
            if(NbLignesAssociation1 == 1 && (NbLignesAssociation2 == 0 || pUneAssociation1.getNomAssociation().equals(pUneAssociation2.getNomAssociation())))
            {
                NbLignes = stmt.executeUpdate("UPDATE ASSOCIATION SET NomAssociation ='"+pUneAssociation2.getNomAssociation()+"', AdresseAssociation ='"+pUneAssociation2.getAdresseAssociation()+"',CPAssociation ='"+pUneAssociation2.getCPAssociation()+"',VilleAssociation ='"+pUneAssociation2.getVilleAssociation()+"',NomResponsable ='"+pUneAssociation2.getNomResponsable()+"' WHERE NumAssociation="+pUneAssociation1.getNumAssociation());
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
            System.out.println("ERREUR SQL GestionAssociationBdD.ModifierAssociation() " + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbLignes;
    }
    
    public static int ajouterSport(Association pUneAssociation,Sport pUnSport)
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSports" + "  FROM PRATIQUER WHERE NumAssociation ="+pUneAssociation.getNumAssociation()+" AND NumSport="+pUnSport.getNumSport());
            if (jeuEnr.next())
            {
                NbLignes=jeuEnr.getInt("NbSports");
                if(NbLignes == 0)
                {
                    NbLignes = stmt.executeUpdate("INSERT INTO PRATIQUER VALUES ("+pUneAssociation.getNumAssociation()+" , "+pUnSport.getNumSport()+")");
                }
                else
                {
                    NbLignes=0;
                } 
            }                      			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionAssociationBdD.AjouterSport()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbLignes;
    }
    
    public static int supprimerSport(Association pUneAssociation,Sport pUnSport)
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSports" + "  FROM PRATIQUER WHERE NumAssociation ="+pUneAssociation.getNumAssociation()+" AND NumSport="+pUnSport.getNumSport());
            if (jeuEnr.next())
            {
                NbLignes=jeuEnr.getInt("NbSports");
                if(NbLignes == 1)
                {
                    NbLignes = stmt.executeUpdate("DELETE FROM PRATIQUER WHERE NumAssociation="+pUneAssociation.getNumAssociation()+" AND NumSport="+pUnSport.getNumSport());
                }
                else
                {
                    NbLignes=0;
                } 
            }                      			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionAssociationBdD.supprimerSport() " + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbLignes;
    }
}
