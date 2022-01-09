package com.example.tp2_.Controller;

import com.example.tp2_.PricipalController;
import com.example.tp2_.metier.Departement;
import com.example.tp2_.metierCRUD.DeparetementCRUD;
import com.example.tp2_.metierCRUD.ProfesseurCRUD;
import com.example.tp2_.metier.Professeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProfViewController implements Initializable {

    @FXML
    private StackPane ProfPane;

    @FXML
    private TableView<Professeur> tableProf;

    @FXML
    private TableColumn<Professeur,Integer> idProf;
    @FXML
    private TableColumn<Professeur,String> cin;
    @FXML
    private TableColumn<Professeur,String> nom;
    @FXML
    private TableColumn<Professeur,String> prenom;
    @FXML
    private TableColumn<Professeur,String> telephone;
    @FXML
    private TableColumn<Professeur,String> email;
    @FXML
    private TableColumn<Professeur, Date> dateR;
    @FXML
    private TableColumn<Professeur,String> deptName;
    @FXML
    private TableColumn<Professeur,String> adresse;
    @FXML
    private Button addProff;
    @FXML
    private Button SuppProf;
    @FXML
    private TextField searchFiled;

    public static ObservableList<Professeur> prolist;

    private ObservableList<Professeur> OldData;

    private boolean flag=false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prolist = new ProfesseurCRUD().GetAll();
        tableProf.setEditable(true);
        idProf.setCellValueFactory(new PropertyValueFactory<>("id_prof"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        cin.setCellFactory(TextFieldTableCell.forTableColumn());
        cin.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Professeur, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Professeur, String> professeurStringCellEditEvent) {
                Professeur p = professeurStringCellEditEvent.getRowValue();
                p.setCin(professeurStringCellEditEvent.getNewValue());
                new ProfesseurCRUD().Modifier(p);
            }
        });
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        nom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Professeur, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Professeur, String> professeurStringCellEditEvent) {
                Professeur p = professeurStringCellEditEvent.getRowValue();
                p.setNom(professeurStringCellEditEvent.getNewValue());
                new ProfesseurCRUD().Modifier(p);
            }
        });
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        prenom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Professeur, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Professeur, String> professeurStringCellEditEvent) {
                Professeur p = professeurStringCellEditEvent.getRowValue();
                p.setPrenom(professeurStringCellEditEvent.getNewValue());
                new ProfesseurCRUD().Modifier(p);
            }
        });
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        telephone.setCellFactory(TextFieldTableCell.forTableColumn());
        telephone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Professeur, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Professeur, String> professeurStringCellEditEvent) {
                Professeur p = professeurStringCellEditEvent.getRowValue();
                p.setTelephone(professeurStringCellEditEvent.getNewValue());
                new ProfesseurCRUD().Modifier(p);
            }
        });
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        email.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Professeur, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Professeur, String> professeurStringCellEditEvent) {
                Professeur p = professeurStringCellEditEvent.getRowValue();
                p.setEmail(professeurStringCellEditEvent.getNewValue());
                new ProfesseurCRUD().Modifier(p);
            }
        });
        dateR.setCellValueFactory(new PropertyValueFactory<>("date_recrutement"));

        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        adresse.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Professeur, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Professeur, String> professeurStringCellEditEvent) {
                Professeur p = professeurStringCellEditEvent.getRowValue();
                p.setAdresse(professeurStringCellEditEvent.getNewValue());
                new ProfesseurCRUD().Modifier(p);
            }
        });
        tableProf.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                    TablePosition pos = tableProf.getFocusModel().getFocusedCell();
                    if(pos.getColumn()==7){
                        tableProf.getSelectionModel().getSelectedItem().DisableCombox(false);
                    }
            }
        });
        tableProf.setOnMouseEntered(event -> tableProf.setCursor(Cursor.HAND));
        //deptName.setCellValueFactory(new PropertyValueFactory<>("dept"));
        deptName.setCellValueFactory(new PropertyValueFactory<>("department"));

        tableProf.setItems(prolist);

        addProff.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(PricipalController.class.getResource("ProfFormulaire.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Formulair Professeur");
                    stage.setScene(new Scene(root, 450, 450));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        SuppProf.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int indice = tableProf.getSelectionModel().getSelectedIndex();
                if(indice>=0){
                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmer ur choice !");
                    alert.setHeaderText("do u want to delete this instance ?");
                    Optional<ButtonType> op = alert.showAndWait();
                    if(op.get() == ButtonType.OK) {
                        new ProfesseurCRUD().Supp(tableProf.getSelectionModel().getSelectedItem().getId_prof());
                        prolist.remove(indice);
                    }else{
                        alert.close();
                    }
                }else{
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setTitle("Supp Warning!");
                    a.setHeaderText("Select a item from the table !!");
                    a.show();
                }
            }
        });

        searchFiled.textProperty().addListener((observable, oldValue, newValue) ->{
            prolist.clear();
            prolist.addAll(OldData);
            List l = prolist.stream().filter(dept  -> !dept.getNom().contains(observable.getValue())).collect(Collectors.toList());
            prolist.removeAll(l);
        });

       searchFiled.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               if(flag){
                   prolist.clear();
                   prolist.addAll(OldData);
                   searchFiled.setText("");
               }else{
                   OldData=FXCollections.observableArrayList();
                   OldData.addAll(prolist);
               }
               flag=true;
               ((Node)mouseEvent.getTarget()).getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {
                   @Override
                   public void handle(MouseEvent mouseEvent) {
                       if(flag){
                           searchFiled.getParent().requestFocus();
                           searchFiled.setText("");
                           prolist.clear();
                           prolist.addAll(OldData);
                           OldData.clear();
                       }
                       flag=false;
                   }
               });
           }
       });


    }
}
