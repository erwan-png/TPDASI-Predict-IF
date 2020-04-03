package fr.insalyon.dasi.metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author eversmee
 */
@Entity
public class Cartomancien extends Medium{

    public Cartomancien() {
    }

    public Cartomancien(String denomination, char genre, String presentation) {
        super(denomination, genre, presentation);
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
        return "Cartomancien{" + '}';
    }
    
}
