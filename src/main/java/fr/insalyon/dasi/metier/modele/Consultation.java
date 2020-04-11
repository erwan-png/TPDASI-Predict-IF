/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author camsiro
 */
@Entity
public class Consultation implements Serializable {
    
//Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_consultation;
    @Temporal(TemporalType.TIME)
    private Date dateDebut;
    @Temporal(TemporalType.TIME)
    private Date dateFin;
    @ManyToOne
    private Medium medium;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Client client;
    private String commentaire;
    
//Methodes
    public Consultation() {
    }

    public Consultation(Date dateDebut, Date dateFin, Medium medium, Employe employe, Client client, String commentaire) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.medium = medium;
        this.employe = employe;
        this.client = client;
        this.commentaire = commentaire;
    }

    public Long getId_consultation() {
        return id_consultation;
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

    public Medium getMedium() {
        return medium;
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

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id_consultation=" + id_consultation + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", medium=" + medium + ", employe=" + employe + ", client=" + client + ", commentaire=" + commentaire + '}';
    }
    
}
