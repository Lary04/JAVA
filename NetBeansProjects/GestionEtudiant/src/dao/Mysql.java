/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hilary DJENGUE
 */
public class Mysql implements IDatabase{
    
    private Connection cnx=null;
    private PreparedStatement ps=null;

    public PreparedStatement getPs() {
        return ps;
    }
    
    

    @Override
    public void connexionBD() {
        try {
            //1-Chargement du Driver
            Class.forName("com.mysql.jdbc.Driver");
            //2-Ouvrir Connexion 
            cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_etudiant","root","");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erreur de chargement du Driver");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion a la BD");
        }
    }

    @Override
    public void fermerBD() {
       if(cnx!=null){
           cnx=null;
       }
    }

    @Override
    public ResultSet executeSelect(String sql) {
      ResultSet rs=null;
        initPreparedStatement(sql);
        System.out.println(ps);
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) {
           
        }
        return rs;
    }

    @Override
    public int executeMaj(String sql) {
       int nbreLigne=0;
        initPreparedStatement(sql);
        try {
            nbreLigne= ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nbreLigne;
    }

    @Override
    public void initPreparedStatement(String sql) {
        try {
            if(sql.toLowerCase().startsWith("insert")){
                ps=cnx.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS); 
            }else{
                 ps=cnx.prepareStatement(sql); 
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ResultSet executeSelect() {
       ResultSet rs=null;
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) {
           
        }
        return rs;
    }

    @Override
    public int executeMaj() {
        int nbreLigne=0;
        try {
            nbreLigne= ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nbreLigne;
    }
    
}
