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
    private String RefSalle;
    private float Surface;
    private String TypeDeRevetement;

    public Salle()
    {
        
    }  

    public Salle(String RefSalle, float Surface, String TypeDeRevetement)
    {
        this.RefSalle = RefSalle;
        this.Surface = Surface;
        this.TypeDeRevetement = TypeDeRevetement;
    }

    public String getRefSalle()
    {
        return RefSalle;
    }

    public void setRefSalle(String RefSalle)
    {
        this.RefSalle = RefSalle;
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
        return RefSalle + " ~ " + Surface + "m²"; 
    }
    
}
