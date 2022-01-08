package com.example.tp2_.metierCRUD;

import com.example.tp2_.DBConnection.ConnectionDB;
import com.example.tp2_.metier.Imetier;
import com.example.tp2_.metier.Departement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DeparetementCRUD implements Imetier<Departement> {
    Connection cn = ConnectionDB.getConnection();
    @Override
    public ObservableList<Departement> GetAll() {
        ObservableList<Departement> ob = FXCollections.observableArrayList();
        try {
            Statement stm = cn.createStatement();
            ResultSet res =stm.executeQuery("SELECT * FROM Deparetement");
            while(res.next()){
                ob.add(new Departement(
                        res.getInt("id_dept"),
                        res.getString("nom")
                        ));
                System.out.println(res.getString("nom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ob;
    }

    @Override
    public Departement Get(int i) {
        Connection cn = ConnectionDB.getConnection();
        Departement dept=null;
        try {
            PreparedStatement pr = cn.prepareStatement("SELECT * FROM Deparetement WHERE id_dept=?");
            pr.setInt(1,i);
            ResultSet res = pr.executeQuery();

            if(res.next()){
                dept=new Departement(i,res.getString("nom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dept;
    }
    public Departement GetByname(String name) {
        Connection cn = ConnectionDB.getConnection();
        Departement dept=null;
        try {
            PreparedStatement pr = cn.prepareStatement("SELECT * FROM Deparetement WHERE nom=?");
            pr.setString(1,name);
            ResultSet res = pr.executeQuery();

            if(res.next()){
                dept=new Departement(res.getInt("id_dept"),name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dept;
    }

    @Override
    public int Add(Departement p) {
        int key=-1;
        try {
            PreparedStatement pr = cn.prepareStatement("INSERT INTO Deparetement(nom) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
            pr.setString(1,p.getNom());
            pr.executeUpdate();
            ResultSet rs = pr.getGeneratedKeys();
            rs.next();
            key=rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }



    @Override
    public void Supp(int id) {
        try {
            PreparedStatement pr = cn.prepareStatement("DELETE FROM Deparetement WHERE id_dept = ?");
            pr.setInt(1,id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Modifier(Departement p) {
        try {
            PreparedStatement pr = cn.prepareStatement("UPDATE Deparetement SET nom=? WHERE id_dept=?");
            pr.setString(1,p.getNom());
            pr.setInt(2,p.getId_depart());
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
