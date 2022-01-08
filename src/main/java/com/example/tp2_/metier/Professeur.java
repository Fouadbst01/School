package com.example.tp2_.metier;

import com.example.tp2_.metier.Departement;
import com.example.tp2_.metierCRUD.DeparetementCRUD;
import com.example.tp2_.metierCRUD.ProfesseurCRUD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.util.Date;

public class Professeur {
    private int id_prof;
    private String nom;
    private String prenom;
    private String cin;
    private String adresse;
    private String telephone;
    private String email;
    private Date date_recrutement;
    private Departement dept;
    private ComboBox department;

    public ComboBox getDepartment() {
        return department;
    }

    public void setDepartment(ComboBox department) {
        this.department = department;
    }

    public Professeur() {
        intComboBox();
    }

    public Professeur(int id_prof, String nom, String prenom, String cin, String adresse, String telephone, String email, Date date_recrutement) {
        this.id_prof = id_prof;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.date_recrutement = date_recrutement;
        intComboBox();
    }
    public Professeur( String nom, String prenom, String cin, String adresse, String telephone, String email, Date date_recrutement) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.date_recrutement = date_recrutement;
    }
    public Professeur(int id_prof,String nom, String prenom, String cin, String adresse, String telephone, String email, Date date_recrutement,Departement dept) {
        this.id_prof = id_prof;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.date_recrutement = date_recrutement;
        this.dept=dept;
        intComboBox();
    }
    public void  intComboBox(){
        this.department=new ComboBox();
        this.department.getItems().setAll(new DeparetementCRUD().GetAll());
        if(dept !=null)
            this.department.setValue(dept);
        this.department.setDisable(true);
        department.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                dept = ((Departement)observableValue.getValue());
                Update();
                department.setDisable(true);
            }
        });
    }
    public void Update(){
        System.out.println(this.dept.getId_depart());
        new ProfesseurCRUD().Modifier(this);
    }
    public Departement getDept() {
        return dept;
    }

    public void setDept(Departement dept) {
        this.dept = dept;
        this.department.setValue(dept);
        this.department.setDisable(true);
    }
    public void DisableCombox(boolean f){
        this.department.setDisable(f);
    }
    public int getId_prof() {
        return id_prof;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_recrutement() {
        return date_recrutement;
    }

    public void setDate_recrutement(Date date_recrutement) {
        this.date_recrutement = date_recrutement;
    }
}
