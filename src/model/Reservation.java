/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Rabelais
 */
public class Reservation
{
    private String RefSalle;   
    private Date Date;
    private Integer Heure;
    private String RefAssociation;

    public Reservation()
    {
        
    }  

    public Reservation(String RefSalle, Date Date, Integer Heure, String RefAssociation)
    {
        this.RefSalle = RefSalle;
        this.Date = Date;
        this.Heure = Heure;
        this.RefAssociation = RefAssociation;
    }
    
    public String getRefSalle()
    {
        return RefSalle;
    }

    public void setRefSalle(String RefSalle)
    {
        this.RefSalle = RefSalle;
    }

    public Date getDate()
    {
        return Date;
    }

    public void setDate(Date Date)
    {
        this.Date = Date;
    }

    public Integer getHeure()
    {
        return Heure;
    }

    public void setHeure(Integer Heure)
    {
        this.Heure = Heure;
    }

    public String getRefAssociation()
    {
        return RefAssociation;
    }

    public void setRefAssociation(String RefAssociation)
    {
        this.RefAssociation = RefAssociation;
    }

    @Override
    public String toString()
    {
        return RefSalle + " le " + Date + " à " + Heure + " par " + RefAssociation; 
    }
    
}
