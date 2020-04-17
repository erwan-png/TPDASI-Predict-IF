package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Adresse;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.service.Service;
import fr.insalyon.dasi.metier.service.ServiceOutils;
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
        
        testerInscriptionClient();
        creerMediums();
        testEmp();
        
        //testerRechercheClient();
        //testerListeClients();
        //testerAuthentificationClient();
        
        //saisirInscriptionClient();
        //saisirRechercheClient();
        //testerObtenirPredictions();

        testerAjouterConsultation();
        
        testerTerminerConsultation();
        
        //testerEnvoieMail();
        //testerEnvoieNotification();
        
        //testerDemanderMedium();
        
        //testerObtenirHistoriqueConsultationsClient();
        testerobtenirStatistique();

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
        SimpleDateFormat formatHeure = new SimpleDateFormat("hh-mm");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date dateDebut = formatHeure.parse("17-05");
        Date dateDebut2 = formatHeure.parse("13-28");
        Date dateDebut3 = formatHeure.parse("14-00");
        Date dateDebut4 = formatHeure.parse("08-54");
        
        Date date1 = formatDate.parse("31-12-2009");
        Date date2 = formatDate.parse("08-12-2011");
        Date date3 = formatDate.parse("08-04-2012");
        Date date4 = formatDate.parse("08-12-2012");
        
        Client client = service.rechercherClientParId(id);
        Employe employe = service.rechercherEmployeParId(id);
        Medium medium = service.rechercherMediumParId(id);
        
        Employe employe2 = service.rechercherEmployeParId(id2);
        Medium medium2 = service.rechercherMediumParId(id2);
        
        Employe employe3 = service.rechercherEmployeParId(id3);
        Medium medium3 = service.rechercherMediumParId(id3);
        
        Employe employe4 = service.rechercherEmployeParId(id4);
        Medium medium4 = service.rechercherMediumParId(id4);
        
        Consultation c1 = new Consultation(dateDebut, null,date1, medium, employe, client,null);
        Consultation c2 = new Consultation(dateDebut2, null,date2, medium, employe2, client,null);
        Consultation c3 = new Consultation(dateDebut3, null,date3, medium, employe3, client,null);
        Consultation c4 = new Consultation(dateDebut4, null,date4, medium4, employe4, client,null);
        
        Long idC1 = service.commencerConsultation(c1);
        Long idC2 = service.commencerConsultation(c2);
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
        long id4=4;
        
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-hh-mm");
        Date dateFin = format.parse("31-12-2009-17-25");
        Date dateFin2 = format.parse("08-12-2011-13-34");
        Date dateFin3 = format.parse("08-04-2012-14-55");
        Date dateFin4= format.parse("08-12-2012-09-24");
        Consultation consultation = service.rechercherConsultationParId(id);
        Consultation consultation2 = service.rechercherConsultationParId(id2);
        Consultation consultation3 = service.rechercherConsultationParId(id3);
        Consultation consultation4 = service.rechercherConsultationParId(id4);
        
        if (consultation != null){
        
            Long id_test = service.terminerConsultation(consultation, dateFin);
            Long id_test2 = service.terminerConsultation(consultation2, dateFin2);
            Long id_test3 = service.terminerConsultation(consultation3, dateFin3);
            Long id_test4 = service.terminerConsultation(consultation4, dateFin4);
            
            Long id_1 = service.laisserCommentaire(consultation,"Client malpoli");
            Long id_2 = service.laisserCommentaire(consultation,"Client malpoli");
            Long id_3 = service.laisserCommentaire(consultation,"Client malpoli");
            Long id_4 = service.laisserCommentaire(consultation,"Client malpoli");
        
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
        
        ServiceOutils serviceOutils = new ServiceOutils();
        Service service = new Service();
        long id;
        id = 1;
        Client client = service.rechercherClientParId(id);
        
        System.out.println();
        System.out.println("**** Envoie Mail réussite Inscription ****");
        System.out.println();
        serviceOutils.genererMailClientSuccesInscription(client);
        
        System.out.println();
        System.out.println("**** Envoie Mail échec Inscription ****");
        System.out.println();
        serviceOutils.genererMailClientEchecInscription(client);
        
        System.out.println();
        System.out.println("**** Fin ****");
        System.out.println();
    }
    
    public static void testerEnvoieNotification() {
        System.out.println();
        System.out.println("**** testerEnvoieMail() ****");
        System.out.println();
        
        Service service = new Service();
        ServiceOutils serviceOutils = new ServiceOutils();
        long id;
        id = 1;
        Client client = service.rechercherClientParId(id);
        Medium medium = service.rechercherMediumParId(id);
        Employe employe = service.rechercherEmployeParId(id);
        
        System.out.println();
        System.out.println("**** Envoie Notification consultation ****");
        System.out.println();
        
        serviceOutils.genererNotificationClient(client, medium, employe);
        
        System.out.println();
        System.out.println("**** Fin ****");
        System.out.println();
    }
    
    public static void testerDemanderMedium() throws IOException, ParseException {
        System.out.println();
        System.out.println("**** testerEnvoieMail() ****");
        System.out.println();
        
        Service service = new Service();
        
        creerMediums();
        testerInscriptionClient();
        testEmp();
        
        testerAjouterConsultation();
        
        testerTerminerConsultation();
        long id=1;
        
        Medium medium = service.rechercherMediumParId(id);
        Client client = service.rechercherClientParId(id);
        
        Long id_employeChoisi = service.demanderMedium(client, medium);
        
        System.out.println();
        System.out.println("**** liste des Employes avec la note maximale****");
        System.out.println();
        
        if(id_employeChoisi !=null) {
            /*employesDispo.forEach((employe) -> {
                afficherEmploye(employe);
            });*/
            afficherEmploye(service.rechercherEmployeParId(id_employeChoisi));
        } else {
            System.out.println("Aucun employé dispo");
        }
        
        
        System.out.println();
        System.out.println("**** Fin ****");
        System.out.println();
    }
    
    public static void testerObtenirHistoriqueConsultationsClient() throws ParseException, IOException {
        System.out.println();
        System.out.println("**** testerEnvoieMail() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        id = 1;
        
        creerMediums();
        testerInscriptionClient();
        testEmp();
        Client client = service.rechercherClientParId(id);
        
        testerAjouterConsultation();
        testerTerminerConsultation();
        
        List<Consultation> res = service.consulterHistoriqueConsultationClient(client);
        
        if(res != null) {
            System.out.println();
            System.out.println("**** Consultations client : ****");
            System.out.println();
            res.forEach((consultation) -> {
                afficherConsultation(consultation);
            });
            System.out.println();
            System.out.println("**** Fin Consultations client ****");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("**** Aucun résultat ****");
            System.out.println();
        }
    }
    
    public static void testerobtenirStatistique() {
        System.out.println();
        System.out.println("**** testerobtenirStatistique() ****");
        System.out.println();
        
        Service service = new Service();
        long id;
        id = 1;
        
        service.obtenirStatistique();
        
        System.out.println();
        System.out.println("**** Fin testerobtenirStatistique ****");
        System.out.println();
    }
}
