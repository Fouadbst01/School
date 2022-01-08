package com.example.tp2_.metierCRUD;

import com.example.tp2_.DBConnection.ConnectionDB;
import com.example.tp2_.metier.Departement;
import com.example.tp2_.metier.Imetier;
import com.example.tp2_.metier.Professeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProfesseurCRUD implements Imetier<Professeur> {
    Connection cn = ConnectionDB.getConnection();
    @Override
    public ObservableList<Professeur> GetAll() {
        ObservableList<Professeur> ob = FXCollections.observableArrayList();
        DeparetementCRUD deptcrud = new DeparetementCRUD();
        try {
            Statement stm = cn.createStatement();
            ResultSet res =stm.executeQuery("SELECT * FROM professeur");
            while(res.next()){
                ob.add(new Professeur(
                        res.getInt("id_prof"),
                        res.getString("nom"),
                        res.getString("prenom"),
                        res.getString("cin"),
                        res.getString("adresse"),
                        res.getString("telephone"),
                        res.getString("email"),
                        res.getDate("date_recrutement"),
                        deptcrud.Get(res.getInt("id_dept"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ob;
    }

    @Override
    public int Add(Professeur p) {
        int key=-1;
        try {
            PreparedStatement pr = cn.prepareStatement("INSERT INTO Professeur(nom,prenom,cin,adresse,telephone,email,date_recrutement,id_dept) VALUES(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            pr.setString(1,p.getNom());
            pr.setString(2,p.getPrenom());
            pr.setString(3,p.getCin());
            pr.setString(4,p.getAdresse());
            pr.setString(5,p.getTelephone());
            pr.setString(6,p.getEmail());
            pr.setDate(7, new java.sql.Date(p.getDate_recrutement().getTime()));
            pr.setInt(8,p.getDept().getId_depart());
            pr.executeUpdate();
            ResultSet rs=pr.getGeneratedKeys();
            rs.next();
            key=rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    @Override
    public Professeur Get(int i) {
        return null;
    }

    @Override
    public void Supp(int id) {
        try {
            PreparedStatement pr = cn.prepareStatement("DELETE FROM Professeur WHERE id_prof = ?");
            pr.setInt(1,id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Modifier(Professeur p) {
        try {
            PreparedStatement pr = cn.prepareStatement("UPDATE Professeur SET nom=?,prenom=?,cin=?,adresse=?,telephone=?,email=?,date_recrutement=?,id_dept=? WHERE id_prof = ?");
            pr.setString(1,p.getNom());
            pr.setString(2,p.getPrenom());
            pr.setString(3,p.getCin());
            pr.setString(4,p.getAdresse());
            pr.setString(5,p.getTelephone());
            pr.setString(6,p.getEmail());
            pr.setDate(7, new java.sql.Date(p.getDate_recrutement().getTime()));
            pr.setInt(8,p.getDept().getId_depart());
            pr.setInt(9,p.getId_prof());
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
