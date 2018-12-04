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
    private Salle UneSalle;   
    private Date Date;
    private Integer Heure;
    private Association UneAssociation;

    public Reservation(Salle UneSalle, Date Date, Integer Heure, Association UneAssociation)
    {
        this.UneSalle = UneSalle;
        this.Date = Date;
        this.Heure = Heure;
        this.UneAssociation = UneAssociation;
    }

    public Salle getUneSalle()
    {
        return UneSalle;
    }

    public void setUneSalle(Salle UneSalle)
    {
        this.UneSalle = UneSalle;
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

    public Association getUneAssociation()
    {
        return UneAssociation;
    }

    public void setUneAssociation(Association UneAssociation)
    {
        this.UneAssociation = UneAssociation;
    }

    public String getNomAssociation()
    {
        return UneAssociation.getNomAssociation();
    }
    
    @Override
    public String toString()
    {
        return UneSalle.getNomSalle() + " le " + Date + " à " + Heure + " par " + UneAssociation.getNomAssociation(); 
    }
    
}
