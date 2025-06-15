/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2.proyecto2;

import Controladores.ColeccionJpaController;
import Controladores.InterfazController;
import Controladores.SalaJpaController;
import Persistencia.Coleccion;
import Persistencia.Sala;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Axton Urbina
 */
public class MuseoInterfazController implements Initializable {

    @FXML
    private Rectangle rec;
    @FXML
    private TextField FiltrarTf;
    @FXML
    private ComboBox<?> FiltrarCb;
    @FXML
    private TableView<Object> tvContenido;
    @FXML
    private Button SalaBtn;
    @FXML
    private Label infoLbl;
    @FXML
    private TextField infoTxt;
    @FXML
    private Button GuardarBtn;
    @FXML
    private Button insertarBtn;
    @FXML
    private Button eliminarBtn;
    @FXML
    private Button editarBtn;
    @FXML
    private Button FiltrarBtn;
    @FXML
    private Button coleccionBtn;
    @FXML
    private Pane paneContenidos;
    @FXML
    private ComboBox<Object> infoCb;
    @FXML
    private Button especiesBtn;
    @FXML
    private Button tematicasBtn;
    @FXML
    private Button preciosBtn;
    @FXML
    private Button comisionesBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoLbl.setVisible(false);
        infoTxt.setVisible(false);
        GuardarBtn.setVisible(false);
        infoCb.setVisible(false);

        InterfazController interfaz = new InterfazController();
        
        ColeccionJpaController ctrl = new ColeccionJpaController();
        
        
        interfaz.manejoInterfaz(SalaBtn, coleccionBtn, especiesBtn, tematicasBtn, preciosBtn, comisionesBtn, tvContenido, infoTxt, GuardarBtn, infoLbl, insertarBtn, FiltrarTf, FiltrarCb, FiltrarBtn, eliminarBtn, editarBtn, infoCb);

    }    
    
}
