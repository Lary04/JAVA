/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;
import models.AC;
import models.Classe;
import models.Etudiant;
import models.Professeur;
import models.RP;
import models.User;

/**
 *
 * @author Hilary DJENGUE
 */
public class UserDao implements IDao<User>{

    private final String SQL_SELECT_USER_LOGIN_PWD="select * from user where login=? and pwd=?";
    private final String SQL_SELECT_ETUDIANT_MATRICULE="select * from user where matricule=?";
    private final String SQL_INSERT="INSERT INTO `user` (`nomComplet`, `login`, `pwd`, `grade`, `matieres`, `tuteur`, `matricule`, `type`)"
                                    + "  VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
     private final String SQL_SELECT_ETUDIANT_CLASSE=" select * from user u,inscription i "
                                                   + " where u.id=i.etudiant_id"
                                                   + " and i.classe_id=? and i.annee=?"
                                                   + " and u.type='Etudiant'  ";
     
      private Mysql mysql=new Mysql();
    @Override
    public int insert(User obj) {
        int id=0;
        //1-Connexion BD
          mysql.connexionBD();
       try {
           //2-Preparer la requete
            mysql.getPs().setString(1, obj.getNomComplet());
            mysql.getPs().setString(8, obj.getType());
           if (obj.getType().compareTo("Etudiant")==0){
               mysql.getPs().setString(2, null);
               mysql.getPs().setString(3, null);
               mysql.getPs().setString(4, null);
               mysql.getPs().setString(5, null);
               mysql.getPs().setString(6, ((Etudiant)obj).getTuteur());
               mysql.getPs().setString(7, ((Etudiant)obj).getMatricule());
              
           }else{
               if (obj.getType().compareTo("Professeur")==0){
                    mysql.getPs().setString(2, null);
                    mysql.getPs().setString(3, null);
                    mysql.getPs().setString(4, ((Professeur)obj).getGrade());
                 // mysql.getPs().setString(5, null);
                    mysql.getPs().setString(6, null);
                    mysql.getPs().setString(7, null);
               
               } else{
                   //RP ou AC
                   mysql.getPs().setString(2, obj.getLogin());
                    mysql.getPs().setString(3, obj.getPwd());
                    mysql.getPs().setString(4,null);
                    mysql.getPs().setString(5, null);
                    mysql.getPs().setString(6, null);
                    mysql.getPs().setString(7, null);
               }
           }
            
           //3-Executer la requete   //4-Recuperation des Resultat
           mysql.executeMaj(SQL_INSERT);
            ResultSet rs= mysql.getPs().getGeneratedKeys();
            rs.next();
          id=rs.getInt("id");
           //5-Fermer la Connexion
           mysql.fermerBD();
       } catch (SQLException ex) {
           Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return id;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(User obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   public User selectUserByLoginAndPwd(User user){
       User userConnect=null;
        //1-Connexion BD
          mysql.connexionBD();
       try {
           //2-Preparer la requete
           mysql.getPs().setString(1, user.getLogin());
           mysql.getPs().setString(2, user.getPwd());
           
       } catch (SQLException ex) {
           Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
       }
           //3-Executer la requete   
           ResultSet rs=mysql.executeSelect(SQL_SELECT_USER_LOGIN_PWD);
       try {
           //4-Recuperation des Resultat
          
           if(rs.next()){
               String type=rs.getString("type");
               if(type.compareTo("AC")==0){
                   userConnect=new AC();
               }
               if(type.compareTo("RP")==0){
                    userConnect=new RP();
               }
                 
                 userConnect.setId(rs.getInt("id"));
                 userConnect.setNomComplet(rs.getString("nomComplet"));
                 userConnect.setLogin(rs.getString("login"));
                 userConnect.setPwd(rs.getString("pwd"));
                         
           }
       } catch (SQLException ex) {
           Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
       }
           //5-Fermer la Connexion
           mysql.fermerBD();
       return userConnect;
   }
   
   public Etudiant selectEtudiant(String matricule){
         Etudiant etu=null;
        //1-Connexion BD
          mysql.connexionBD();
       try {
           //2-Preparer la requete
           mysql.getPs().setString(1, matricule);
       } catch (SQLException ex) {
           Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
       }
           //3-Executer la requete   
           ResultSet rs=mysql.executeSelect(SQL_SELECT_ETUDIANT_MATRICULE);
       try {
           //4-Recuperation des Resultat
          
           if(rs.next()){
                 etu=new Etudiant();
                 etu.setId(rs.getInt("id"));
                 etu.setMatricule(rs.getString("matricule"));  
                 etu.setNomComplet(rs.getString("nomComplet"));
                 etu.setTuteur(rs.getString("tuteur"));
                         
           }
       } catch (SQLException ex) {
           Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
       }
           //5-Fermer la Connexion
           mysql.fermerBD();
       return etu;
   }
    public List<Etudiant> selectEtudiantByClasseByAnnee(Classe cl,String annee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
