/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours;

import dao.ClasseDao;
import dao.Mysql;
import java.util.ArrayList;
import java.util.List;
import models.Etudiant;
import models.Professeur;
import models.User;

/**
 *
 * @author Hilary DJENGUE
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      // User u=new User(); => User est abstract
      //Polymorphisme d Objet
     /*   User u =new Etudiant();
        
       ((Etudiant)u).getTuteur();
       
       List<String> lString=new ArrayList();
           lString.add("Bonjour");
            lString.add("a");
           lString.add("Tous");
           
           //Afficher la Liste 
           for(String s:lString){
               System.out.println(s);
           }
           
           
        List<User> lUser=new ArrayList();
        lUser.add(new Etudiant());
        lUser.add(new Professeur());
        
         for(User user:lUser){
             System.out.println(user);
         }*/
     
        Mysql mysql=new Mysql();
        mysql.connexionBD();
        ClasseDao dao=new ClasseDao();
        dao.selectAll().forEach((cl)->{
            System.out.println(cl);
        });
        
    }
    
}
