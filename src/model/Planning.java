/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LE DU Maëlle
 */
public class Planning
{
    private Association UneAssociation;
    private int UnJour;
    private int UnHoraire;

    public Planning(Association UneAssociation, int UnJour, int UnHoraire)
    {
        this.UneAssociation = UneAssociation;
        this.UnJour = UnJour;
        this.UnHoraire = UnHoraire;
    }

    public Association getUneAssociation()
    {
        return UneAssociation;
    }

    public void setUneAssociation(Association UneAssociation)
    {
        this.UneAssociation = UneAssociation;
    }

    public int getUnJour()
    {
        return UnJour;
    }

    public void setUnJour(int UnJour)
    {
        this.UnJour = UnJour;
    }

    public int getUnHoraire()
    {
        return UnHoraire;
    }

    public void setUnHoraire(int UnHoraire)
    {
        this.UnHoraire = UnHoraire;
    }

    
    
}
