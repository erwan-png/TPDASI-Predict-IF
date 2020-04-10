/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author eversmee
 */
@Entity
public class Astrologue extends Medium {
    protected String formation;
    protected int promotion;

    public Astrologue() {
    }

    public Astrologue(String formation, int promotion, String denomination, char genre, String presentation) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getDenomination() {
        return denomination;
    }

    @Override
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @Override
    public char getGenre() {
        return genre;
    }

    @Override
    public void setGenre(char genre) {
        this.genre = genre;
    }

    @Override
    public String getPresentation() {
        return presentation;
    }

    @Override
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    @Override
    public String toString() {
        return "Astrologue{" + "formation=" + formation + ", promotion=" + promotion + '}';
    }
    
}
