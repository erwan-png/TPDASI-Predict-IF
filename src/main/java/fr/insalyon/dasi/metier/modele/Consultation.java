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

/**
 *
 * @author camsiro
 */
public class Consultation {
    
//Attributs
    
    private Date dateDeb;
    private Date dateFin;
    //private Medium medium;
    private Employe employe;
    private Client client;
    private String commentaire;
    
//Methodes
    
    
}
