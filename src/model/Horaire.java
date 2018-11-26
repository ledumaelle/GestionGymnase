/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rabelais
 */
public class Horaire
{
    private int heure ;
    private String association;
    private String salle; 

    public Horaire(int heure, String association, String salle)
    {
        this.heure = heure;
        this.association = association;
        this.salle = salle;
    }

    public int getHeure()
    {
        return heure;
    }

    public void setHeure(int heure)
    {
        this.heure = heure;
    }

    public String getAssociation()
    {
        return association;
    }

    public void setAssociation(String association)
    {
        this.association = association;
    }

    public String getSalle()
    {
        return salle;
    }

    public void setSalle(String salle)
    {
        this.salle = salle;
    }

    

    
}
