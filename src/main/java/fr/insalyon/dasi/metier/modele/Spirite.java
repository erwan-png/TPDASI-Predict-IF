package fr.insalyon.dasi.metier.modele;

import java.util.List;
import javax.persistence.Entity;
/**
 *
 * @author eversmee
 */
@Entity
public class Spirite extends Medium {
    protected List<String> support;

    public Spirite() {
    }

    public Spirite(List<String> support, String denomination, char genre, String presentation) {
        super(denomination, genre, presentation);
        this.support = support;
    }

    public List<String> getSupport() {
        return support;
    }

    public void setSupport(List<String> support) {
        this.support = support;
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
        return "Spirite{" + "support=" + support + '}';
    }
    
}
