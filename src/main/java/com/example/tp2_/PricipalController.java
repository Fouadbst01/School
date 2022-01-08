package com.example.tp2_;

import com.example.tp2_.Controller.ProfViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PricipalController extends Application implements Initializable {
    @FXML
    private AnchorPane PaneP;
    @FXML
    private Rectangle rectShadow1;
    @FXML
    private Rectangle rectShadow2;
    @FXML
    private Button GestProf;
    @FXML
    private Button GestDept;
    @FXML
    private StackPane rectMove;
    @FXML
    private Button Close;
    @FXML
    private Button Reduce;
    @FXML
    private Button Maximise;

    private int selectedB=1;

    private double PosX = 0;
    private double PosY = 0;
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PrincipalView.fxml"));
        scene = new Scene(fxmlLoader.load(), 1120, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void goToProfView() throws IOException {
        StackPane scenProf = FXMLLoader.load(getClass().getResource("profView.fxml"));
        PaneP.getChildren().removeAll();
        PaneP.getChildren().setAll(scenProf);
        AnchorPane.setLeftAnchor(scenProf,0d);
        AnchorPane.setRightAnchor(scenProf,0d);
        AnchorPane.setBottomAnchor(scenProf,0d);
        AnchorPane.setTopAnchor(scenProf,0d);
    }

    public void goToDeptView() throws IOException {
        StackPane scenProd = FXMLLoader.load(getClass().getResource("deptView.fxml"));
        PaneP.getChildren().removeAll();
        PaneP.getChildren().setAll(scenProd);
        AnchorPane.setLeftAnchor(scenProd,0d);
        AnchorPane.setRightAnchor(scenProd,0d);
        AnchorPane.setBottomAnchor(scenProd,0d);
        AnchorPane.setTopAnchor(scenProd,0d);
    }

    public void Close(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rectShadow2.setVisible(false);
        rectShadow1.setVisible(true);
        StackPane scenProf = null;
        try {
            scenProf = FXMLLoader.load(getClass().getResource("profView.fxml"));
            AnchorPane.setLeftAnchor(scenProf,0d);
            AnchorPane.setRightAnchor(scenProf,0d);
            AnchorPane.setBottomAnchor(scenProf,0d);
            AnchorPane.setTopAnchor(scenProf,0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PaneP.getChildren().removeAll();
        PaneP.getChildren().setAll(scenProf);

        GestDept.addEventHandler(MouseEvent.MOUSE_ENTERED,
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    rectShadow2.setVisible(true);
                    rectShadow1.setVisible(false);
                }
            });
        Close.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                /*Stage s = (Stage)PaneP.getScene().getWindow();
                //s.setIconified(true);
                s.setMaximized(true);*/
                Close.setCursor(Cursor.HAND);
            }
        });
        Reduce.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Close.setCursor(Cursor.HAND);
            }
        });
        Reduce.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage s = (Stage)PaneP.getScene().getWindow();
                s.setIconified(true);
            }
        });
        Maximise.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Close.setCursor(Cursor.HAND);
            }
        });
        Maximise.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage s = (Stage)PaneP.getScene().getWindow();
                if(s.isMaximized())
                    s.setMaximized(false);
                else
                    s.setMaximized(true);
            }
        });
        GestDept.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        rectShadow2.setVisible(false);
                        if(selectedB == 1)
                            rectShadow1.setVisible(true);
                        else
                            rectShadow2.setVisible(true);
                    }
                });
        GestProf.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        rectShadow1.setVisible(true);
                        rectShadow2.setVisible(false);
                    }
                });
        GestProf.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        rectShadow1.setVisible(false);
                        if(selectedB == 1)
                            rectShadow1.setVisible(true);
                        else
                            rectShadow2.setVisible(true);
                    }
                });

        GestProf.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        selectedB=1;
                        rectShadow1.setVisible(true);
                        rectShadow2.setVisible(false);
                    }
                });
        GestDept.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        selectedB=2;
                        rectShadow1.setVisible(false);
                        rectShadow2.setVisible(true);
                    }
                });
        rectMove.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PosX = mouseEvent.getX();
                PosY = mouseEvent.getY();
                rectMove.setCursor(Cursor.CLOSED_HAND);
            }
        });
        rectMove.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Window w = ((Node)(mouseEvent.getSource())).getScene().getWindow();
                ((Node)(mouseEvent.getSource())).setCache(false);
                rectMove.setCursor(Cursor.CLOSED_HAND);
                w.setX(w.getX()+mouseEvent.getX()-PosX);
                w.setY(w.getY()+mouseEvent.getY()-PosY);
            }
        });
        rectMove.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectMove.setCursor(Cursor.OPEN_HAND);
            }
        });
        rectMove.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectMove.setCursor(Cursor.OPEN_HAND);
            }
        });

    }

}
