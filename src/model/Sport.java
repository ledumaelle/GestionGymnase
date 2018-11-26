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
public class Sport
{
    private String NomSport;

    public Sport()
    {
        
    }  

    public Sport(String NomSport)
    {
        this.NomSport = NomSport;
    }

    public String getNomSport()
    {
        return NomSport;
    }

    public void setNomSport(String NomSport)
    {
        this.NomSport = NomSport;
    }
    
    @Override
    public String toString()
    {
        return NomSport; 
    }
    
}
