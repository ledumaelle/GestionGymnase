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
                UneSalle= new Salle(jeuEnr.getInt("NumSalle"),jeuEnr.getString("NomSalle"), jeuEnr.getFloat("surface"),jeuEnr.getString("TypeRevetement"));
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
    
    public static Salle getSalle(String pNom, float pSurface, String pTypeRevetement)
    {
        Connection conn; //connexion
	Statement stmt;
	ResultSet jeuEnr;
        Salle UneSalle=new Salle ("Erreur");
	String pilote = "org.gjt.mm.mysql.Driver";
	String url = new String("jdbc:mysql://localhost/gymnase");
        try
	{
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();			            
            jeuEnr = stmt.executeQuery("SELECT * FROM SALLE WHERE NomSalle = '" +pNom+"' AND Surface =" +pSurface+ " AND TypeRevetement='"+pTypeRevetement+"'" );
            if(jeuEnr.next())
            {
                UneSalle= new Salle(jeuEnr.getInt("NumSalle"),jeuEnr.getString("NomSalle"), jeuEnr.getFloat("surface"),jeuEnr.getString("TypeRevetement"));                
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
        return UneSalle;
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
            jeuEnr = stmt.executeQuery("SELECT MAX(NuMSalle)  AS " + "NbMaxSalles" + " FROM SALLE");
            jeuEnr.next();
            NbMax=jeuEnr.getInt("NbMaxSalles");
                			            
            jeuEnr.close();
            stmt.close();
            conn.close();
	}			        
	catch (SQLException sqle)
	{
            System.out.println("ERREUR SQL GestionSalleBdD.getNbMax()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbMax;
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
            jeuEnr = stmt.executeQuery("SELECT * FROM SALLE,ACCUEILLIR WHERE SALLE.NumSalle=ACCUEILLIR.NumSalle AND NumSport=" + pUnSport.getNumSport());
            Salle UneSalle;
            while (jeuEnr.next())
            {
                UneSalle= new Salle(jeuEnr.getInt("NumSalle"),jeuEnr.getString("NomSalle"), jeuEnr.getFloat("surface"),jeuEnr.getString("TypeRevetement"));
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
    
    public static int ajouterSalle(Salle pUneSalle)
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSalles" + "  FROM SALLE WHERE NomSalle = '"+pUneSalle.getNomSalle()+"'");
            jeuEnr.next();
            NbLignes=jeuEnr.getInt("NbSalles");
            if(NbLignes == 0)
            {
                NbLignes = stmt.executeUpdate("INSERT INTO SALLE(NomSalle,Surface,TypeRevetement) VALUES ('"+pUneSalle.getNomSalle()+"' , "+pUneSalle.getSurface()+" , '"+pUneSalle.getTypeDeRevetement()+"')");
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
            System.out.println("ERREUR SQL GestionSalleBdD.ajouterSalle()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbLignes;
    }
    
    public static int modifierSalle(Salle pUneSalle1, Salle pUneSalle2)
    {
        Connection conn; //connexion
        int NbLignesSalle1=0;
        int NbLignesSalle2=0;
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
            
            //MODIFIER SALLE
            //si la salle existe
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSalles" + "  FROM SALLE WHERE NumSalle= "+pUneSalle1.getNumSalle());
            if (jeuEnr.next())
            {
                NbLignesSalle1=jeuEnr.getInt("NbSalles");
                jeuEnr.close();
            }
            
            //si la nouvelle ref n'existe pas déjà à part si c le mm nom que la 1 
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSalles" + "  FROM SALLE WHERE NomSalle = '"+pUneSalle2.getNomSalle()+"'");
            if (jeuEnr.next())
            {
                NbLignesSalle2=jeuEnr.getInt("NbSalles");
                jeuEnr.close();
            }
            if(NbLignesSalle1 == 1 && (NbLignesSalle2 == 0 || pUneSalle1.getNomSalle().equals(pUneSalle2.getNomSalle())))
            {
                NbLignes = stmt.executeUpdate("UPDATE SALLE SET NomSalle ='"+pUneSalle2.getNomSalle()+"', Surface ="+pUneSalle2.getSurface()+",TypeRevetement ='"+pUneSalle2.getTypeDeRevetement()+"' WHERE NumSalle="+pUneSalle1.getNumSalle());
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
    public static int ajouterSport(Salle pUneSalle,Sport pUnSport)
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSports" + "  FROM ACCUEILLIR WHERE NumSalle ="+pUneSalle.getNumSalle()+" AND NumSport="+pUnSport.getNumSport());
            if (jeuEnr.next())
            {
                NbLignes=jeuEnr.getInt("NbSports");
                if(NbLignes == 0)
                {
                    NbLignes = stmt.executeUpdate("INSERT INTO ACCUEILLIR VALUES ("+pUneSalle.getNumSalle()+" , "+pUnSport.getNumSport()+")");
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
            System.out.println("ERREUR SQL GestionSalleBdD.AjouterSport()" + sqle.getMessage());
	}
	catch (ClassNotFoundException cnfe)
	{
            System.out.println("ERREUR Driver " + cnfe.getMessage());
	} 
        return NbLignes;
    }
    
    public static int supprimerSport(Salle pUneSalle,Sport pUnSport)
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
            jeuEnr = stmt.executeQuery("SELECT COUNT(*) AS " + "NbSports" + "  FROM ACCUEILLIR WHERE NumSalle ="+pUneSalle.getNumSalle()+" AND NumSport="+pUnSport.getNumSport());
            if (jeuEnr.next())
            {
                NbLignes=jeuEnr.getInt("NbSports");
                if(NbLignes == 1)
                {
                    NbLignes = stmt.executeUpdate("DELETE FROM ACCUEILLIR WHERE NumSalle="+pUneSalle.getNumSalle()+" AND NumSport="+pUnSport.getNumSport());
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
