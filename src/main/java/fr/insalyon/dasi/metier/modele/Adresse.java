package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Embeddable;
/**
 *
 * @author eversmee
 */
@Embeddable
public class Adresse implements Serializable {
    private String lieuResidence;
    private String codePostale;
    private String ville;

    public Adresse() {
    }

    public Adresse(String lieuResidence, String codePostale, String ville) {
        this.lieuResidence = lieuResidence;
        this.codePostale = codePostale;
        this.ville = ville;
    }

    public String getLieuResidence() {
        return lieuResidence;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public String getVille() {
        return ville;
    }
}
