package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.EmployeDAO;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fr.insalyon.dasi.util.AstroTest;
import fr.insalyon.dasi.util.Message;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author DASI Team
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDAO employeDao = new EmployeDAO();
    protected ConsultationDao consultationDao = new ConsultationDao();
    protected MediumDao mediumDao = new MediumDao();


    public Long inscrireClient(Client client) throws IOException {
        Long resultat = null;
        AstroTest profilAstro = new AstroTest();
        
        List<String> astroClient = profilAstro.getProfil(client.getPrenom(),client.getNaissance());
        client.getProfilAstro().setSigneZodiaque(astroClient.get(0));
        client.getProfilAstro().setSigneChinois(astroClient.get(1));
        client.getProfilAstro().setCouleur(astroClient.get(2));
        client.getProfilAstro().setAnimal(astroClient.get(3));
 
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.creerClient(client);
            JpaUtil.validerTransaction();
            resultat = client.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Employe rechercherEmployeParId(Long id) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = employeDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Medium rechercherMediumParId(Long id) {
        Medium resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public Consultation rechercherConsultationParId(Long id) {
        Consultation resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = consultationDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client authentifierClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public boolean estInscrit(String mail) {
        boolean resultat = false;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                resultat = true;
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<String> obtenirPredictions(Client client, int[] notes) {
        List<String> resultat = null;
        AstroTest testProfilAstro = new AstroTest();
        
        String couleur = client.getProfilAstro().getCouleur();
        String animal = client.getProfilAstro().getAnimal();
        
        int sante = notes[0];
        int amour = notes[1];
        int travail = notes[2];
        
        JpaUtil.creerContextePersistance();
        
        try {
            
            resultat = testProfilAstro.getPredictions(couleur, animal, amour, sante, travail);
            
        } catch (IOException ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service obtenirPredictions(Client client, int[] notes)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    

    public boolean initialiserEmploye() throws IOException {
        boolean flag = false; // 0 si tout ok
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            Employe[] tab = new Employe[15];
            tab[0] = new Employe("Sierra", "Camilo", 'M', "csr@predictif.com", "123456"," 06 55 44 77 88", 0);
            tab[1] = new Employe("Versmee", "Erwan", 'M', "ev@predictif.com", "654321"," 06 55 44 77 89", 0);
            tab[2] = new Employe("Dupont", "François", 'M', "fd@predictif.com", "hjvdfk"," 06 55 44 77 90", 0);
            //empDao.creerEmploye(camilo);
            for (int i=0;i<3;i++)
            {
                employeDao.creerEmploye(tab[i]);
            }
            JpaUtil.validerTransaction();
            
            flag = true;

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserEmploye()", ex);
            JpaUtil.annulerTransaction();
            flag = false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return flag;
    }
    
    public Long commencerConsultation(Consultation consultation) throws IOException {
        Long id;
        consultation.getEmploye().setDisponibilite(false);
        
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.creerConsultation(consultation);
            JpaUtil.validerTransaction();
            id = consultation.getId_consultation();
            consultation.getEmploye().setNbConsultations(consultation.getEmploye().getNbConsultations()+1);
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service commencerConsultation(consultation)", ex);
            JpaUtil.annulerTransaction();
            consultation.getEmploye().setDisponibilite(true);
            id = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return id;
    }
    
    public Long terminerConsultation(Consultation consultation, Date heureFin ) throws IOException {
        Long id;
        consultation.getEmploye().setDisponibilite(true);
        
        JpaUtil.creerContextePersistance();

        consultation.setDateFin(heureFin);
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.modifierConsultation(consultation);
            JpaUtil.validerTransaction();
            id = consultation.getId_consultation();
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service terminerConsultation(consultation, heureFin)", ex);
            JpaUtil.annulerTransaction();
            consultation.getEmploye().setDisponibilite(false);
            id = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return id;
    }
    
    public boolean initialiserMediums() throws IOException {
        boolean success = false;
        
        JpaUtil.creerContextePersistance();
        
        List<String> supportUn = new ArrayList<>();
        List<String> supportDeux = new ArrayList<>();
        supportUn.add("Boule de cristal");
        supportDeux.add("Marc de café");
        supportDeux.add("Boule de crista");
        supportDeux.add("Oreilles de lapin ");
            
        Spirite spiriteUn = new Spirite(supportUn, "Gwenaëlle ",'F', "Spécialiste des grandes conversations au-delà de TOUTES les frontières.");
        Spirite spiriteDeux = new Spirite(supportDeux, " Professeur Tran  ",'H', "Votre avenir est devant vous : regardons-le ensemble !");
            
        Cartomancien cartomancienUn = new Cartomancien("Mme Irma",'F', "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.");
        Cartomancien cartomancienDeux = new Cartomancien("Endora",'F', "Mes cartes répondront à toutes vos questions personnelles.");
            
        Astrologue astrologueUn = new Astrologue("École Normale Supérieure d’Astrologie (ENS-Astro)" , 2006,"Serena", 'F',"Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé." );
        Astrologue astrologueDeux = new Astrologue(" Institut des Nouveaux Savoirs Astrologiques" , 2010," Mr M ", 'H',"Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!" );
        
        try {
            
            JpaUtil.ouvrirTransaction();
            mediumDao.creerMedium(spiriteUn);
            mediumDao.creerMedium(spiriteDeux);
            mediumDao.creerMedium(cartomancienUn);
            mediumDao.creerMedium(cartomancienDeux);
            mediumDao.creerMedium(astrologueUn);
            mediumDao.creerMedium(astrologueDeux);
            JpaUtil.validerTransaction();
            
            success = true;
            
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service initialiserMediums()", ex);
            JpaUtil.annulerTransaction();
            
            success = false;
            
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return success;
    }
    
    public List<Client> listerClients() {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Medium> listerMediums() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = mediumDao.listerMediums();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediums()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    
    public List<Employe> listerEmployeParPriorite(char genre) {
        List<Employe> employesOK = null;
        JpaUtil.creerContextePersistance();
        
        try {
            employesOK = employeDao.listerEmployes();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediums()", ex);
            employesOK = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        if(employesOK!=null) {  
            
            int nbConsultMini=employesOK.get(0).getNbConsultations();
            
            for (int i=0;i<employesOK.size();i++) {
                if (employesOK.get(i).getGenre()==genre && employesOK.get(i).getNbConsultations()<nbConsultMini) {
                    nbConsultMini=employesOK.get(i).getNbConsultations();
                }
            }
            
            for (int i=0;i<employesOK.size();i++) {
                if (employesOK.get(i).getNbConsultations()!=nbConsultMini || employesOK.get(i).getGenre()!=genre) {
                    employesOK.remove(i);
                }
            }
        }
        
        return employesOK;
    }
    
    public void genererMailClientSuccesInscription(Client client){
        String mailExpediteur = "admin@predict.if";
        String mailDestinataire = client.getMail();
        String objet ="Bienvenue chez PREDICT’IF";
        
        StringWriter corps = new StringWriter();
        PrintWriter mailWriter = new PrintWriter(corps);
        
        mailWriter.println("Bonjour "+ client.getPrenom() +", nous vous confirmons votre inscription au service PREDICT’IF.");
        mailWriter.println("Rendez vous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos mediums");
        
        Message.envoyerMail(mailExpediteur,mailDestinataire,objet,corps.toString());
    }
    
    public void genererMailClientEchecInscription(Client client){
        String mailExpediteur = "admin@predict.if";
        String mailDestinataire = client.getMail();
        String objet ="Bienvenue chez PREDICT’IF";
        
        StringWriter corps = new StringWriter();
        PrintWriter mailWriter = new PrintWriter(corps);
        
        mailWriter.println("Bonjour "+ client.getPrenom() +" votre inscription au service PREDICT’IF a malencontreusement échoué...");
        mailWriter.println("Merci de recommencer ultérieurement");
        
        Message.envoyerMail(mailExpediteur,mailDestinataire,objet,corps.toString());
    }
    
    public void genererNotificationClient(Client client, Medium medium, Employe employe){
        String numeroTelClient = client.getNumeroTel();
        
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        
        Date actuelle = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(actuelle);
        
        SimpleDateFormat heureFormat = new SimpleDateFormat("HH");
        String heure = heureFormat.format(actuelle);
        
        SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
        String minute = minuteFormat.format(actuelle);
        
        notificationWriter.println("Bonjour "+ client.getPrenom()+". J’ai bien reçu votre demande de consultation du " + date + " à " + heure +"h"+minute+".");
        notificationWriter.println("Vous pouvez dès à présent me contacter au "+ employe.getNumeroTel() +". A tout de suite ! Médiumiquement vôtre, " + medium.getDenomination());
        
        Message.envoyerNotification(numeroTelClient,message.toString());
    }
}
