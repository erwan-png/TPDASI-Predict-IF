/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author camsiro
 */
public class Consultation {
    
//Attributs
    
    private Date dateDebut;
    private Date dateFin;
    //private Medium medium;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Client client;
    private String commentaire;
    
//Methodes

    public Consultation() {
    }

    public Consultation(Date dateDebut, Date dateFin, Employe employe, Client client, String commentaire) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.employe = employe;
        this.client = client;
        this.commentaire = commentaire;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Client getClient() {
        return client;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setDateDebut(Date dateDeb) {
        this.dateDebut = dateDeb;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Consultation{" + "dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", employe=" + employe + ", client=" + client + ", commentaire=" + commentaire + '}';
    }
    
    
}
