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
    private int NumAssociation;
    private String NomAssociation;
    private String AdresseAssociation;
    private String CPAssociation;
    private String VilleAssociation;
    private String NomResponsable;

    public Association()
    {
        
    }

    public Association(int NumAssociation, String NomAssociation, String AdresseAssociation, String CPAssociation, String VilleAssociation, String NomResponsable)
    {
        this.NumAssociation = NumAssociation;
        this.NomAssociation = NomAssociation;
        this.AdresseAssociation = AdresseAssociation;
        this.CPAssociation = CPAssociation;
        this.VilleAssociation = VilleAssociation;
        this.NomResponsable = NomResponsable;
    }

    public int getNumAssociation()
    {
        return NumAssociation;
    }
    

    public void setNumAssociation(int NumAssociation)
    {
        this.NumAssociation = NumAssociation;
    }

    public String getNomAssociation()
    {
        return NomAssociation;
    }

    public void setNomAssociation(String NomAssociation)
    {
        this.NomAssociation = NomAssociation;
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
        return NomAssociation + " à " + VilleAssociation + " / " + NomResponsable ; 
    }
    
}
