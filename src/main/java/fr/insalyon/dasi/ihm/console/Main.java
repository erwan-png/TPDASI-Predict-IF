package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Adresse;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) throws ParseException, IOException {

        JpaUtil.init();
        
        //testerInscriptionClient();
        //creerMediums();
        //testEmp();
        
        //testerRechercheClient();
        //testerListeClients();
        //testerAuthentificationClient();
        
        //saisirInscriptionClient();
        //saisirRechercheClient();
        //testerObtenirPredictions();

        //testerAjouterConsultation();
        
        //testerTerminerConsultation();
        
        //testerEnvoieMail();
        //testerEnvoieNotification();
        
        testerTrouverEmployerNoteMaxi();

        JpaUtil.destroy();
    }

    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }
    
    public static void afficherEmploye(Employe employe) {
        System.out.println("-> " + employe);
    }
    
    public static void afficherConsultation(Consultation consultation) {
        System.out.println("-> " + consultation);
    }

    public static void testerInscriptionClient() throws ParseException, IOException {
        
        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse("31-12-2009");
        
        Adresse adresse = new Adresse("15 rue de la république","69000","Lyon");
        
        Service service = new Service();
        Client claude = new Client("claude.chappe@insa-lyon.fr","Chappe", "Claude",date,adresse, "HaCKeR","05 04 65 88 29");
        Long idClaude = service.inscrireClient(claude);
        if (idClaude != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(claude);
    }

    public static void testEmp() throws IOException {
        
        System.out.println();
        System.out.println("**** testEmp() ****");
        System.out.println();
        
        Service service = new Service();
        boolean flag = service.initialiserEmploye();
        if (flag == true) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
    }
    
    public static void testerObtenirPredictions(){
        
        System.out.println();
        System.out.println("**** testerObtenirPredictions() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Client client;

        id = 1;
        int[] notes = {1,3,4};
        
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
            List<String> predictions = service.obtenirPredictions(client, notes);
            System.out.println(predictions.get(0));
            System.out.println(predictions.get(1));
            System.out.println(predictions.get(2));
        } else {
            System.out.println("=> Client non-trouvé");
        }
    }
    
    public static void testerAjouterConsultation() throws ParseException, IOException {
        
        System.out.println();
        System.out.println("**** testerAjouterConsultation() ****");
        System.out.println();
        
        Service service = new Service();
        long id = 1;
        long id2 = 2;
        long id3 = 3;
        long id4 = 4;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-hh-mm");
        Date dateDebut = format.parse("31-12-2009-17-05");
        Client client = service.rechercherClientParId(id);
        Employe employe = service.rechercherEmployeParId(id);
        Medium medium = service.rechercherMediumParId(id);
        
        Employe employe2 = service.rechercherEmployeParId(id2);
        Medium medium2 = service.rechercherMediumParId(id2);
        
        Employe employe3 = service.rechercherEmployeParId(id3);
        Medium medium3 = service.rechercherMediumParId(id3);
        
        Employe employe4 = service.rechercherEmployeParId(id4);
        Medium medium4 = service.rechercherMediumParId(id4);
        
        Consultation c1 = new Consultation(dateDebut, null, medium, employe, client,"Client très désagréable et malpoli");
        Consultation c2 = new Consultation(dateDebut, null, medium, employe2, client,"Client très désagréable et malpoli");
        Consultation c3 = new Consultation(dateDebut, null, medium, employe3, client,"Client très désagréable et malpoli");
        Consultation c4 = new Consultation(dateDebut, null, medium4, employe4, client,"Client très désagréable et malpoli");
        
        Long idC1 = service.commencerConsultation(c1);
        //Long idC2 = service.commencerConsultation(c2);
        Long idC3 = service.commencerConsultation(c3);
        Long idC4 = service.commencerConsultation(c4);
        
        if (idC1 != null) {
            System.out.println("> Succès ajout");
        } else {
            System.out.println("> Échec ajout");
        }
        afficherConsultation(c1);
    }
    
    public static void testerTerminerConsultation() throws ParseException, IOException {
        
        System.out.println();
        System.out.println("**** testerTerminerConsultation() ****");
        System.out.println();
        
        Service service = new Service();
        long id=1;
        long id2=2;
        long id3=3;
        
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-hh-mm");
        Date dateFin = format.parse("31-12-2009-17-31");
        Consultation consultation = service.rechercherConsultationParId(id);
        Consultation consultation2 = service.rechercherConsultationParId(id2);
        Consultation consultation3 = service.rechercherConsultationParId(id3);
        
        if (consultation != null){
        
            Long id_test = service.terminerConsultation(consultation, dateFin);
            Long id_test2 = service.terminerConsultation(consultation2, dateFin);
            Long id_test3 = service.terminerConsultation(consultation3, dateFin);
        
            if (id_test != null) {
                System.out.println("> Succès fin de consultation");
            } else {
                System.out.println("> Échec fin de consultation");
            }
        } else {
            System.out.println("> Échec fin de consultation");
        }
    }

    
    public static void creerMediums() throws IOException{
        
        System.out.println();
        System.out.println("**** CreerMediums() ****");
        System.out.println();
        
        Service service = new Service();
        
        boolean flag = service.initialiserMediums();
        
        if (flag == true) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
    }

    public static void testerRechercheClient() {
        
        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        Client client;

        id = 1;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client #" + id + " non-trouvé");
        }
    }

    public static void testerListeClients() {
        
        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();
        
        Service service = new Service();
        List<Client> listeClients = service.listerClients();
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            listeClients.forEach((client) -> {
                afficherClient(client);
            });
        }
        else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerAuthentificationClient() {
        
        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();
        
        Service service = new Service();
        Client client;
        String mail;
        String motDePasse;

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada1012";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada2020";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "etudiant.fictif@insa-lyon.fr";
        motDePasse = "********";
        client = service.authentifierClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }
    
    public static void testerEnvoieMail() {
        
        System.out.println();
        System.out.println("**** testerEnvoieMail() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        id = 1;
        Client client = service.rechercherClientParId(id);
        
        System.out.println();
        System.out.println("**** Envoie Mail réussite Inscription ****");
        System.out.println();
        service.genererMailClientSuccesInscription(client);
        
        System.out.println();
        System.out.println("**** Envoie Mail échec Inscription ****");
        System.out.println();
        service.genererMailClientEchecInscription(client);
        
        System.out.println();
        System.out.println("**** Fin ****");
        System.out.println();
    }
    
    public static void testerEnvoieNotification() {
        System.out.println();
        System.out.println("**** testerEnvoieMail() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        id = 1;
        Client client = service.rechercherClientParId(id);
        Medium medium = service.rechercherMediumParId(id);
        Employe employe = service.rechercherEmployeParId(id);
        
        System.out.println();
        System.out.println("**** Envoie Notification consultation ****");
        System.out.println();
        
        service.genererNotificationClient(client, medium, employe);
        
        System.out.println();
        System.out.println("**** Fin ****");
        System.out.println();
    }
    
    public static void testerTrouverEmployerNoteMaxi() throws IOException, ParseException {
        System.out.println();
        System.out.println("**** testerEnvoieMail() ****");
        System.out.println();
        
        Service service = new Service();
        
        creerMediums();
        testerInscriptionClient();
        testEmp();
        
        testerAjouterConsultation();
        
        testerTerminerConsultation();
        
        List<Employe> employesDispo = service.listerEmployeParPriorite('F');
        
        System.out.println();
        System.out.println("**** liste des Employes avec lanote maximale****");
        System.out.println();
        
        if(employesDispo !=null) {
            employesDispo.forEach((employe) -> {
                afficherEmploye(employe);
            });
        } else {
            System.out.println("Aucun employé dispo");
        }
        
        
        System.out.println();
        System.out.println("**** Fin ****");
        System.out.println();
    }
}
