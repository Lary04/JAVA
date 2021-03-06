/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Hilary DJENGUE
 */
public class Affectation {
    private String annee;
    private LocalDate createAt;
    private List<String> matiereEnseignees;
    //Navigabilite
     private Professeur prof;
     private Classe classe;

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public List<String> getMatiereEnseignees() {
        return matiereEnseignees;
    }

    public void setMatiereEnseignees(List<String> matiereEnseignees) {
        this.matiereEnseignees = matiereEnseignees;
    }

    public Professeur getProf() {
        return prof;
    }

    public void setProf(Professeur prof) {
        this.prof = prof;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
     
     
   
}
