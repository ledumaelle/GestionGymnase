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
public class Salle
{
    private int NumSalle;
    private String NomSalle;
    private float Surface;
    private String TypeDeRevetement;

    public Salle(String NomSalle)
    {
        this.NomSalle = NomSalle;
    }
    
    public Salle(String NomSalle, float Surface, String TypeDeRevetement)
    {
        this.NomSalle = NomSalle;
        this.Surface = Surface;
        this.TypeDeRevetement = TypeDeRevetement;
    }
    
    public Salle(int NumSalle, String NomSalle, float Surface, String TypeDeRevetement)
    {
        this.NumSalle = NumSalle;
        this.NomSalle = NomSalle;
        this.Surface = Surface;
        this.TypeDeRevetement = TypeDeRevetement;
    }

    public int getNumSalle()
    {
        return NumSalle;
    }

    public void setNumSalle(int NumSalle)
    {
        this.NumSalle = NumSalle;
    }

    public String getNomSalle()
    {
        return NomSalle;
    }

    public void setNomSalle(String NomSalle)
    {
        this.NomSalle = NomSalle;
    }

    public float getSurface()
    {
        return Surface;
    }

    public void setSurface(float Surface)
    {
        this.Surface = Surface;
    }

    public String getTypeDeRevetement()
    {
        return TypeDeRevetement;
    }

    public void setTypeDeRevetement(String TypeDeRevetement)
    {
        this.TypeDeRevetement = TypeDeRevetement;
    }

    @Override
    public String toString()
    {
        return NomSalle + " ~ " + Surface + "m²"; 
    }
    
}
