/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author camsiro
 */
@Entity
public class Employe implements Serializable {
    
//Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private char genre;
    @Column(unique=true)
    private String mail;
    private String motDePasse;
    private String numeroTel;
    private boolean disponibilite;
    private int nbConsultations;
    
//Methodes

    public Employe() {
    }

    public Employe(String nom, String prenom, char genre, String mail, String motDePasse, String numeroTel, int nbConsultations) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.numeroTel = numeroTel;
        this.nbConsultations = nbConsultations;
        this.disponibilite = true;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public char getGenre() {
        return genre;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public int getNbConsultations() {
        return nbConsultations;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String mdp) {
        this.motDePasse = mdp;
    }

    public void setNbConsultations(int nbConsult) {
        this.nbConsultations = nbConsult;
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", mail=" + mail + ", motDePasse=" + motDePasse + ", numeroTel=" + numeroTel + ", disponibilite=" + disponibilite + ", nbConsultations=" + nbConsultations + '}';
    }
    
}
