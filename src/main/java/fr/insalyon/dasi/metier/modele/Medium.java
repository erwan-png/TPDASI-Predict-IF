package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author eversmee
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public abstract class Medium implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String denomination;
    protected char genre;
    protected String presentation;

    public Medium() {
    }

    public Medium(String denomination, char genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
    }

    public String getDenomination() {
        return denomination;
    }

    public char getGenre() {
        return genre;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    @Override
    public String toString() {
        return "Medium{" + "denomination=" + denomination + ", genre=" + genre + ", presentation=" + presentation + '}';
    }
    
}
