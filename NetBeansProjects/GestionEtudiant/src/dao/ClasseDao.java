/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Classe;

/**
 *
 * @author Hilary DJENGUE
 */
public class ClasseDao implements IDao<Classe>{

   private final String SQL_SELECT_ALL="SELECT * FROM `classe`";
   private final String SQL_SELECT_CLASSE_BY_LIBELLE="select * from classe where libelle=?";
   private final String SQL_INSERT="INSERT INTO `classe` (`libelle`) VALUES (?);";
   
   private Mysql mysql=new Mysql();
   
    @Override
    public int insert(Classe obj) {
        int id=0;
        //1-Connexion BD
          mysql.connexionBD();
       try {
           //2-Preparer la requete
           mysql.getPs().setString(1,obj.getLibelle() );
           //3-Executer la requete   //4-Recuperation des Resultat
           id=mysql.executeMaj(SQL_INSERT);
           //5-Fermer la Connexion
           mysql.fermerBD();
       } catch (SQLException ex) {
           Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return id;
    }

    @Override
    public int update(Classe obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Classe> selectAll() {
        List<Classe> lClasses=null;
        //1-Connexion BD
          mysql.connexionBD();

           //3-Executer la requete   
           ResultSet rs=mysql.executeSelect(SQL_SELECT_ALL);
       try {
           //4-Recuperation des Resultat
           lClasses=new ArrayList<>();
           while(rs.next()){
                 Classe cl=new Classe();
                 cl.setId(rs.getInt("id"));
                 cl.setLibelle(rs.getString("libelle"));
                lClasses.add(cl);
                                     
           }
       } catch (SQLException ex) {
           Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
       }
           //5-Fermer la Connexion
           mysql.fermerBD();
     
       
       return lClasses;
    }
    
    public Classe selectClasseByLibelle(String libelle){
        Classe cl=null;
        //1-Connexion BD
          mysql.connexionBD();
       try {
           //2-Preparer la requete
           mysql.initPreparedStatement(SQL_SELECT_CLASSE_BY_LIBELLE);
           mysql.getPs().setString(1, libelle);
       } catch (SQLException ex) {
           Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
       }
           //3-Executer la requete   
           ResultSet rs=mysql.executeSelect();
       try {
           //4-Recuperation des Resultat
          
           if(rs.next()){
                 cl=new Classe();
                 cl.setId(rs.getInt("id"));
                 cl.setLibelle(rs.getString("libelle"));                      
           }
       } catch (SQLException ex) {
           Logger.getLogger(ClasseDao.class.getName()).log(Level.SEVERE, null, ex);
       }
           //5-Fermer la Connexion
           mysql.fermerBD();
       return cl;
    }
    
}
