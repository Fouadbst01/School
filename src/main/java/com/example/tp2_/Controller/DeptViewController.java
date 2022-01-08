package com.example.tp2_.Controller;

import com.example.tp2_.PricipalController;
import com.example.tp2_.metierCRUD.DeparetementCRUD;
import com.example.tp2_.metier.Departement;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeptViewController implements Initializable {
    @FXML
    private StackPane DeptPane;

    @FXML
    private TableView<Departement> TableDept;
    @FXML
    private TableColumn<Departement,Integer> idDept;
    @FXML
    private TableColumn<Departement,String> nomDept;
    @FXML
    private Button AjouterButton;
    @FXML
    private Button SupprimerButton;


    public static ObservableList<Departement> deptList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DeparetementCRUD dc = new DeparetementCRUD();
        TableDept.setEditable(true);
        idDept.setCellValueFactory(new PropertyValueFactory<>("id_depart"));
        nomDept.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomDept.setCellFactory(TextFieldTableCell.forTableColumn());
        nomDept.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Departement, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Departement, String> departementStringCellEditEvent) {
                Departement dept = departementStringCellEditEvent.getRowValue();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation !!");
                alert.setHeaderText("dou want to save changes !!");
                Optional<ButtonType> op =alert.showAndWait();
                if(op.get()==ButtonType.OK){
                    dept.setNom(departementStringCellEditEvent.getNewValue());
                    new DeparetementCRUD().Modifier(dept);
                }else {
                    alert.close();
                }
            }
        });
        deptList=dc.GetAll();
        TableDept.setItems(deptList);
        AjouterButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AjouterButton.setCursor(Cursor.HAND);
            }
        });
        SupprimerButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SupprimerButton.setCursor(Cursor.HAND);
            }
        });
        AjouterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Parent root;
                try {
                    root = FXMLLoader.load(PricipalController.class.getResource("departementForm.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Formulair Departement");
                    stage.setScene(new Scene(root));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    //((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        SupprimerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int indice = TableDept.getSelectionModel().getSelectedIndex();
                if(indice>=0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmer ur choice !");
                    alert.setHeaderText("do u want to delete this instance ?");
                    Optional<ButtonType> op = alert.showAndWait();
                    if (op.get() == ButtonType.OK) {
                        Departement dept = TableDept.getSelectionModel().getSelectedItem();
                        new DeparetementCRUD().Supp(dept.getId_depart());
                        deptList.remove(TableDept.getSelectionModel().getSelectedIndex());
                    }
                    alert.close();
                }else{
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setTitle("Supp Warning!");
                    a.setHeaderText("Select a item from the table !!");
                    a.show();
                }
            }
        });

    }


}
