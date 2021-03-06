/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.Classe;
import models.Etudiant;
import models.Professeur;
import models.User;

/**
 *
 * @author Hilary DJENGUE
 */
public interface IGestionEtudiant {
    
    public List<Classe> listerClasseUnEnseignant(Professeur prof);
    public boolean inscrireEtudiant(Etudiant etu,Classe cl,String annee);
    public boolean addClasse(Classe cl);
    public List<Classe> listerClasse();
    public List<String> listerModuleParClasseUnProf(Professeur prof,Classe cl);
    public boolean affecterProfesseur(Professeur prof,Classe cl);
    public boolean modifierClasse(Classe cl);
    public User seConnecter(User user);
    public boolean rechercherClasseByLibelle(String libelle);
    public boolean rechercherEtudiantParMatricule(String matricule);
    public List<Etudiant> listerEtudiantParClasse(Classe cl,String annee);
    public List<String> getAnneeScolaire();
    
}
