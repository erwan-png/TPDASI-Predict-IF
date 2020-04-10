/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author eversmee
 */
@Embeddable
public class ProfilAstrologique implements Serializable {
    private String signeZodiaque;
    private String signeChinois;
    private String couleur;
    private String animal;

    public ProfilAstrologique() {
    }

    public ProfilAstrologique(String signeZodiaque, String signeChinois, String couleur, String animal) {
        this.signeZodiaque = signeZodiaque;
        this.signeChinois = signeChinois;
        this.couleur = couleur;
        this.animal = animal;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
    
    
}
