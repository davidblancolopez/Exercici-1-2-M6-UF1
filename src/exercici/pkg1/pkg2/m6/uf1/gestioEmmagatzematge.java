package exercici.pkg1.pkg2.m6.uf1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class gestioEmmagatzematge {

    File fitxer = new File("C:\\proves\\Execici2");

    /**
     * Metode que s'encarrega d'insertar dades a un fitxer d'emmagatzematge. El
     * metode utilitza el metode anomenat c
     *
     * @param nom
     * @param preu
     * @param unitat
     */
    public void insertarDades(String nom, double preu, int unitat) throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile(fitxer, "rw");

        int codi = cercarCodiRepetit();

        //Es realitza la inserció de dades.
        raf.seek(fitxer.length());

        raf.writeInt(codi);
        raf.writeUTF(nom);
        raf.writeDouble(preu);
        raf.writeInt(unitat);
    }

    /**
     * Metode que llegeix totes les dades del fitxer i va guardant el numero de
     * codi de cada producte en una variable anomenada codi, aixi quan arribi al
     * final el numero que estigui en la variable sera el ultim codi introduit.
     *
     * @param codi
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int cercarCodiRepetit() throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile(fitxer, "r");
        int codi = 0;
        raf.seek(0);

        do {
            codi = raf.readInt();
            raf.readUTF();
            raf.readDouble();
            raf.readInt();
        } while (raf.getFilePointer() < fitxer.length());

        return codi++;
    }

    public void cercarProductesNom(String nom) throws FileNotFoundException, IOException {
        RandomAccessFile raf = new RandomAccessFile(fitxer, "r");
        raf.seek(0);
        
        do {
            raf.readInt();
            
        } while (raf.getFilePointer() < fitxer.length());
        
    }

    /**
     * Metode que rep un codi per a realitzar la cerca del producte que conté aquest codi
     * Recorre tot el fitxer agafant la informació i introduint-la en les variables,
     * si troba el producte amb el codi llavors l'imprimeix per pantalla sino no mostra rés.
     * @param codi
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void cercarProductesCodi(int codi) throws FileNotFoundException, IOException{
        RandomAccessFile raf = new RandomAccessFile(fitxer, "r");
        raf.seek(0);
        int codi2, unitat;
        String nom;
        double preu;
        
        do {
            codi2 = raf.readInt();
            nom = raf.readUTF();
            preu = raf.readDouble();
            unitat = raf.readInt();
            
            if (codi == codi2) {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("PRODUCTE");
                System.out.println("Nom: " + nom);
                System.out.println("Preu: " + preu);
                System.out.println("Numero d'unitats: " + unitat);
                System.out.println("-----------------------------------------------------------------");
            }
        } while (raf.getFilePointer() < fitxer.length());
        
    }
    
}