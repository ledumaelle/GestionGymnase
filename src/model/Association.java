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
public class Association
{
    private String RefAssociation;
    private String AdresseAssociation;
    private String VilleAssociation;
    private String CPAssociation;
    private String NomResponsable;

    public Association()
    {
        
    }  

    public Association(String RefAssociation, String AdresseAssociation, String VilleAssociation, String CPAssociation, String NomResponsable)
    {
        this.RefAssociation = RefAssociation;
        this.AdresseAssociation = AdresseAssociation;
        this.VilleAssociation = VilleAssociation;
        this.CPAssociation = CPAssociation;
        this.NomResponsable = NomResponsable;
    }

    public String getRefAssociation()
    {
        return RefAssociation;
    }

    public void setRefAssociation(String RefAssociation)
    {
        this.RefAssociation = RefAssociation;
    }

    public String getAdresseAssociation()
    {
        return AdresseAssociation;
    }

    public void setAdresseAssociation(String AdresseAssociation)
    {
        this.AdresseAssociation = AdresseAssociation;
    }

    public String getVilleAssociation()
    {
        return VilleAssociation;
    }

    public void setVilleAssociation(String VilleAssociation)
    {
        this.VilleAssociation = VilleAssociation;
    }

    public String getCPAssociation()
    {
        return CPAssociation;
    }

    public void setCPAssociation(String CPAssociation)
    {
        this.CPAssociation = CPAssociation;
    }

    public String getNomResponsable()
    {
        return NomResponsable;
    }

    public void setNomResponsable(String NomResponsable)
    {
        this.NomResponsable = NomResponsable;
    }

    
    
    @Override
    public String toString()
    {
        return RefAssociation + " à " + VilleAssociation + " / " + NomResponsable ; 
    }
    
}
