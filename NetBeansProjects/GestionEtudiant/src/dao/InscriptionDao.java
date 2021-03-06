/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Inscription;

/**
 *
 * @author Hilary DJENGUE
 */
public class InscriptionDao implements IDao<Inscription>{

      private final String SQL_INSERT="INSERT INTO `inscription` (`create_at`, `annee`, `classe_id`, `etudiant_id`) "
                                      + "VALUES (?, ?, ?, ?);";
      
    @Override
    public int insert(Inscription obj) {
       int nbreLigne=0;
       return nbreLigne;
    }

    @Override
    public int update(Inscription obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inscription> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
