/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ClasseDao;
import dao.InscriptionDao;
import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import models.Classe;
import models.Etudiant;
import models.Inscription;
import models.Professeur;
import models.User;

/**
 *
 * @author Hilary DJENGUE
 */
public class GestionEtudiantImpl implements IGestionEtudiant{

    
    private ClasseDao daoClasse=new ClasseDao();
    private UserDao   daoUser=new UserDao();
    private InscriptionDao daoIns=new InscriptionDao();
    
    @Override
    public boolean rechercherClasseByLibelle(String libelle) {
       return  daoClasse.selectClasseByLibelle(libelle)!=null;
    }
    
    @Override
    public List<Classe> listerClasseUnEnseignant(Professeur prof) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

    @Override
    public boolean addClasse(Classe cl) {
       return daoClasse.insert(cl)!=0;
    }

    @Override
    public List<Classe> listerClasse() {
        return daoClasse.selectAll();
    }
    
    @Override
    public User seConnecter(User user) {
       return daoUser.selectUserByLoginAndPwd(user);
    }
    
     @Override
    public boolean inscrireEtudiant(Etudiant etu, Classe cl,String annee) {
        if(etu.getId()==0){
            String mat=generateMatricule();
            etu.setMatricule(mat);
            int id=daoUser.insert(etu);
            etu.setId(id);
        }
        Inscription ins=new Inscription(annee, etu, cl);
        return daoIns.insert(ins)!=0;
    }
    
    private String generateMatricule(){
        String matricule="";
        
        return matricule;
    }
    

    @Override
    public List<String> listerModuleParClasseUnProf(Professeur prof, Classe cl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean affecterProfesseur(Professeur prof, Classe cl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifierClasse(Classe cl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean rechercherEtudiantParMatricule(String matricule) {
      return  daoUser.selectEtudiant(matricule)!=null;
    }

    @Override
    public List<Etudiant> listerEtudiantParClasse(Classe cl, String annee) {
       return daoUser.selectEtudiantByClasseByAnnee(cl, annee);
    }

    @Override
    public List<String> getAnneeScolaire() {
       List<String> lAnneeScolaire=new ArrayList<>();
       lAnneeScolaire.add("2020-2021");
       lAnneeScolaire.add("2019-2020");
       lAnneeScolaire.add("2018-2019");
       return lAnneeScolaire;
    }

    

    
    
}
