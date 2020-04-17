package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.util.Message;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author eversmee
 */
public class ServiceOutils {
    
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
    
    public void genererNotificationEmploye(Client client, Medium medium, Employe employe){
        String numeroTelEmploye = employe.getNumeroTel();
        
        StringWriter message = new StringWriter();
        PrintWriter notificationWriter = new PrintWriter(message);
        
        notificationWriter.println("Bonjour "+employe.getPrenom()+". Consultation requise pour Mme "+client.getPrenom()+" "+client.getNom()+". Médium à incarner : "+medium.getDenomination());
        
        Message.envoyerNotification(numeroTelEmploye,message.toString());
    }
}
