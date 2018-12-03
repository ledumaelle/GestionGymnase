/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Rabelais
 */
public class Sport
{
    private int NumSport; 
    private String NomSport;

    public Sport(String NomSport)
    {
        this.NomSport = NomSport;
    }
    
    public Sport(int NumSport, String NomSport)
    {
        this.NumSport = NumSport;
        this.NomSport = NomSport;
    }

    public int getNumSport()
    {
        return NumSport;
    }

    public void setNumSport(int NumSport)
    {
        this.NumSport = NumSport;
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
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return (this.NomSport == null ? ((Sport) obj).NomSport == null : this.NomSport.equals(((Sport) obj).NomSport));
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.NomSport);
        return hash;
    }
    
    @Override
    public String toString()
    {
        return NomSport; 
    }
    
}
