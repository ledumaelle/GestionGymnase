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
    private String refAssociation;
    private int UnJour;
    private int UnHoraire;

    public Planning(String refAssociation, int UnJour, int UnHoraire)
    {
        this.refAssociation = refAssociation;
        this.UnJour = UnJour;
        this.UnHoraire = UnHoraire;
    }

    public String getRefAssociation()
    {
        return refAssociation;
    }

    public void setRefAssociation(String refAssociation)
    {
        this.refAssociation = refAssociation;
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
