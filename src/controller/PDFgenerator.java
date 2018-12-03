/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javax.swing.filechooser.FileSystemView;
import model.Planning;
import model.Salle;
/**
 *
 * @author Rabelais
 */
public class PDFgenerator
{
    public static String chemin="C:/Users/Rabelais/Documents/PDFtest/ReservationSalle.pdf";
    
    public static String getPDF(Salle pUneSalle, LocalDate pUneDate, ObservableList<Planning> pLePlanning)
    {  
        Document document = new Document(PageSize.A4.rotate());
        try
        {             
            FileSystemView fsv = FileSystemView.getFileSystemView();
            File f = fsv.getDefaultDirectory();
            
            chemin=f+"/"+pUneSalle.getNomSalle()+"Du"+DateFrancaise(pUneDate)+"Au"+DateFrancaise(pUneDate.plusDays(6))+".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(chemin));
            document.open();                    
            //ajout de contenu
            document.add(new Paragraph ("Planning de la réservation de la salle : " + pUneSalle.getNomSalle()));
            document.add(new Paragraph ("Du : " +DateFrancaise(pUneDate)+ " au : " +DateFrancaise(pUneDate.plusDays(6))));
            document.add(new Paragraph ("\n"));
            document.add(tabReservation(pLePlanning));
        }
        catch(DocumentException de)
        {
            de.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        document.close();
        return chemin;
    }
    
    public static PdfPTable tabReservation(ObservableList<Planning> pLePlanning) throws DocumentException
    {
        //On créer un objet table dans lequel on intialise ça taille.
        PdfPTable table = new PdfPTable(8);
        table.setWidths(new int[]{50,50,50,50,50,50,50,50});

        //On créer l'objet cellule.
        PdfPCell cell = new PdfPCell(new Phrase(" "));
        cell.setColspan(1);
        table.addCell(cell);
        int i,j,k,l;
        int UnHoraire=0;
        String LAssoc="";
        int UnJour=0;
        //créer l'entête
        for(i=1;i<8;i++)
        {
            switch (i)
            {
                case 1 :                    
                    cell = new PdfPCell(new Phrase("Lundi"));
                    break;
                case 2 :                    
                    cell = new PdfPCell(new Phrase("Mardi"));
                    break;
                case 3 :                    
                    cell = new PdfPCell(new Phrase("Mercredi"));
                    break;
                case 4 :                    
                    cell = new PdfPCell(new Phrase("Jeudi"));
                    break;
                case 5 :                    
                    cell = new PdfPCell(new Phrase("Vendredi"));
                    break;
                case 6 :                    
                    cell = new PdfPCell(new Phrase("Samedi"));
                    break;
                case 7 :                    
                    cell = new PdfPCell(new Phrase("Dimanche"));
                    break;
            }
            cell.setColspan(1);
            table.addCell(cell);
        }
        j=8; // numéro de cellule pour les horaires
        l=8; // horaires
        int HoraireActuel=8;
        int JourActuel;
        i=8;
        while(i != 113)
        {
            //créer les horaires
            if (i==j)
            {                
                cell = new PdfPCell(new Phrase(l+"h - "+(l+1)+"h"));
                cell.setColspan(1);
                table.addCell(cell);
                j=j+8;
                l++;
                i++;
            }
            else
            {
                for(JourActuel=1;JourActuel<8;JourActuel++)
                {
                    for (k=0;k<pLePlanning.size();k++)
                    {
                        UnHoraire = pLePlanning.get(k).getUnHoraire();
                        UnJour = pLePlanning.get(k).getUnJour();
                        LAssoc = pLePlanning.get(k).getUneAssociation().getNomAssociation();

                        if(UnHoraire==HoraireActuel && UnJour == JourActuel)
                        {
                            break;
                        }
                    }
                    if (UnHoraire==HoraireActuel && UnJour == JourActuel)
                    {
                        cell = new PdfPCell(new Phrase(LAssoc));
                        cell.setColspan(1);
                        table.addCell(cell);
                        i++;
                    }
                    else
                    {                    
                        cell = new PdfPCell(new Phrase(" "));
                        cell.setColspan(1);
                        table.addCell(cell);
                        i++;
                    }
                }
                HoraireActuel++;
            } 
        }
        return table;  
    }
    
    public static String DateFrancaise(LocalDate pUneDate)
    {
        String annee,mois,jour,NouvelleDate;
        String tab[]=String.valueOf(pUneDate).split("-");
        annee=tab[0];
        mois=tab[1];
        jour=tab[2];
        NouvelleDate=jour+"-"+mois+"-"+annee;
        return NouvelleDate;
    }
}
