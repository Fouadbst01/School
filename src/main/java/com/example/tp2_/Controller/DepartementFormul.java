package com.example.tp2_.Controller;

import com.example.tp2_.metier.Departement;
import com.example.tp2_.metierCRUD.DeparetementCRUD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartementFormul implements Initializable {
    @FXML
    private Button Cancel;
    @FXML
    private Button Confirmer;
    @FXML
    private TextField Nomdept;
    @FXML
    private AnchorPane DeptPane;
    public void Cancel(){
        ((Stage)DeptPane.getScene().getWindow()).close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cancel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Cancel.setCursor(Cursor.HAND);
            }
        });
        Confirmer.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Confirmer.setCursor(Cursor.HAND);
            }
        });
        Confirmer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String Nom = Nomdept.getText();
                if(Nom ==""){
                    Nomdept.requestFocus();
                    Nomdept.setStyle("-fx-border-color: red;-fx-prompt-text-fill: red;");
                }else{
                    Departement d= new Departement(Nom);
                    int n=new DeparetementCRUD().Add(d);
                    d.setId_depart(n);
                    DeptViewController.deptList.add(d);
                    Cancel();
                }
            }
        });
    }
}
