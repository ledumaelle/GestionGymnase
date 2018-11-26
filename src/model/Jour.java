/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Rabelais
 */
public class Jour
{
    private String leJour;
    private ArrayList<Horaire> lesHoraires = new ArrayList(); 

    public Jour(String leJour)
    {
        this.leJour = leJour;
    }

    
    public Jour(String leJour, ArrayList<Horaire> pLesHoraires)
    {
        this.leJour = leJour;
        lesHoraires= pLesHoraires;
    }

    public String getLeJour()
    {
        return leJour;
    }

    public void setLeJour(String leJour)
    {
        this.leJour = leJour;
    }

    public ArrayList<Horaire> getLesHoraires()
    {
        return lesHoraires;
    }

    public void setLesHoraires(ArrayList<Horaire> lesHoraires)
    {
        this.lesHoraires = lesHoraires;
    }      
    
}
