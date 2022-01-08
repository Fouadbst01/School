package com.example.tp2_.Controller;

import com.example.tp2_.metier.Departement;
import com.example.tp2_.metier.Professeur;
import com.example.tp2_.metierCRUD.DeparetementCRUD;
import com.example.tp2_.metierCRUD.ProfesseurCRUD;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FomeProfController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField tele;
    @FXML
    private TextField emeil;
    @FXML
    private ChoiceBox deptSelect;
    @FXML
    private TextArea adressTextarea;
    @FXML
    private Button Confirme;
    @FXML
    private Button Cancel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private AnchorPane FormPane;

    public void cancel(){
        ((Stage)FormPane.getScene().getWindow()).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DeparetementCRUD deparetement = new DeparetementCRUD();
        ProfesseurCRUD professeur = new ProfesseurCRUD();
        ObservableList<Departement> listdept = deparetement.GetAll();

        deptSelect.setItems(listdept);
        Confirme.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Confirme.setCursor(Cursor.HAND);
            }
        });
        Cancel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Cancel.setCursor(Cursor.HAND);
            }
        });
        nom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nom.setStyle("-fx-border-color:none;");
            }
        });
        prenom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nom.setStyle("-fx-border-color:none;");
            }
        });
        cin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nom.setStyle("-fx-border-color:none;");
            }
        });
        emeil.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nom.setStyle("-fx-border-color:none;");
            }
        });
        tele.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nom.setStyle("-fx-border-color:none;");
            }
        });
        datePicker.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nom.setStyle("-fx-border-color:none;-fx-border-style: none;");
            }
        });
        deptSelect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nom.setStyle("-fx-border-color:none;");
            }
        });
        Confirme.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Professeur p = new Professeur();
                String nomv=nom.getText();
                System.out.println("*"+nomv+"*");
                if(nomv==""){
                    nom.requestFocus();
                    nom.setStyle("-fx-border-color: red;-fx-prompt-text-fill: red;");
                    nom.setPromptText("name must not be null");
                }else{
                    p.setNom(nomv);
                    String Preno=prenom.getText();
                    if(Preno==""){
                        prenom.requestFocus();
                        prenom.setStyle("-fx-border-color: red;-fx-prompt-text-fill: red;");
                        prenom.setPromptText("prenom must not be null");
                    }else{
                        p.setPrenom(Preno);
                        String cinv=cin.getText();
                        if(cinv==""){
                            cin.requestFocus();
                            cin.setStyle("-fx-border-color: red;-fx-prompt-text-fill: red;");
                            cin.setPromptText("cin must not be null");
                        }else{
                            p.setCin(cinv);
                            String telev = tele.getText();
                            if(telev==""){
                                tele.requestFocus();
                                tele.setStyle("-fx-border-color: red;-fx-prompt-text-fill: red;");
                                tele.setPromptText("tele must not be null");
                            }else{
                                p.setTelephone(telev);
                                String emailv =emeil.getText();
                                if(emailv==""){
                                    emeil.requestFocus();
                                    emeil.setStyle("-fx-border-color: red;-fx-prompt-text-fill: red;");
                                    emeil.setPromptText("emeil must not be null");
                                }else{
                                    p.setEmail(emailv);
                                    LocalDate ld = datePicker.getValue();
                                    if(ld==null){
                                        datePicker.requestFocus();
                                        datePicker.setStyle("-fx-border-color: red;");
                                        datePicker.setPromptText("date must not be null");
                                    }else{
                                        p.setDate_recrutement(Date.valueOf(ld));
                                        Object deptv = deptSelect.getValue();
                                        if(deptv==null){
                                            deptSelect.requestFocus();
                                            deptSelect.setStyle("-fx-border-color: red;");
                                        }else{
                                            p.setDept(((Departement)deptSelect.getSelectionModel().getSelectedItem()));
                                            p.setAdresse(adressTextarea.getText());
                                            int key = new ProfesseurCRUD().Add(p);
                                            p.setId_prof(key);
                                            ProfViewController.prolist.add(p);
                                            cancel();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            }
        });
    }
}
