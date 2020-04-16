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
    private Date heureDebut;
    @Temporal(TemporalType.TIME)
    private Date heureFin;
    @Temporal(TemporalType.DATE)
    private Date dateConsultation;
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

    public Consultation(Date heureDebut, Date heureFin, Date dateConsultation, Medium medium, Employe employe, Client client, String commentaire) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.dateConsultation = dateConsultation;
        this.medium = medium;
        this.employe = employe;
        this.client = client;
        this.commentaire = commentaire;
    }

    public Long getId_consultation() {
        return id_consultation;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public Date getDateConsultation() {
        return dateConsultation;
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

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
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
        return "Consultation{" + "id_consultation=" + id_consultation + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", dateConsultation=" + dateConsultation + ", medium=" + medium + ", employe=" + employe + ", client=" + client + ", commentaire=" + commentaire + '}';
    }
    
}
