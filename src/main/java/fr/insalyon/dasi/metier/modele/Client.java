package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author DASI Team
 */
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String mail;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date naissance;
    @Embedded
    private Adresse adresse;
    private String motDePasse;
    private String numeroTel;
    @Embedded
    private ProfilAstrologique profilAstro;

    protected Client() {
    }

    public Client(String mail, String nom, String prenom, Date naissance, Adresse adresse, String motDePasse, String numeroTel) {
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.adresse = adresse;
        this.motDePasse = motDePasse;
        this.numeroTel = numeroTel;
        this.profilAstro = new ProfilAstrologique();
    }

    public Long getId() {
        return id;
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

    public ProfilAstrologique getProfilAstro() {
        return profilAstro;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
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

    public void setProfilAstro(ProfilAstrologique profilAstro) {
        this.profilAstro = profilAstro;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", mail=" + mail + ", nom=" + nom + ", prenom=" + prenom + ", naissance=" + naissance + ", adresse=" + adresse + ", motDePasse=" + motDePasse + ", numeroTel=" + numeroTel + ", profilAstro=" + profilAstro + '}';
    }

}
