package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import util.AstroTest;

/**
 *
 * @author DASI Team
 */
@Entity
public class Client implements Serializable {

        @Id
    private String mail;
    private String nom;
    private String prenom;
    private Date naissance;
    @Embedded
    private Adresse adresse;
    @Column(unique = true)
    private String motDePasse;
    private AstroTest profilAstrologique;

    protected Client() {
    }

    public Client(String mail, String nom, String prenom, Date naissance, Adresse adresse, String motDePasse) {
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.adresse = adresse;
        this.motDePasse = motDePasse;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getNaissance() {
        return naissance;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Client{" + "mail=" + mail + ", nom=" + nom + ", prenom=" + prenom + ", naissance=" + naissance + ", adresse=" + adresse + ", motDePasse=" + motDePasse + ", profilAstrologique=" + profilAstrologique + '}';
    }
    

}
