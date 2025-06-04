/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2.proyecto2;

import Controladores.ColeccionJpaController;
import Controladores.InterfazController;
import Controladores.SalaJpaController;
import Persistencia.Coleccion;
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
    private TableView<?> tvContenido;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SalaJpaController sala = new SalaJpaController();
        InterfazController interfaz = new InterfazController();
        
        ColeccionJpaController ctrl = new ColeccionJpaController();
        
        interfaz.probar(paneContenidos);
        
        interfaz.mouseClick(tvContenido);
        
        interfaz.presionarSalaBtn(SalaBtn, tvContenido, sala);
        interfaz.InsertarSala(infoTxt, GuardarBtn, infoLbl, insertarBtn, FiltrarTf, FiltrarCb, FiltrarBtn, eliminarBtn, editarBtn);
        interfaz.editarSala(editarBtn, FiltrarBtn, eliminarBtn, GuardarBtn, insertarBtn, FiltrarTf, FiltrarCb, infoTxt, infoLbl);
        interfaz.eliminarSala(eliminarBtn);
        
        interfaz.presionarColeccionBtn(coleccionBtn, tvContenido, ctrl);
        
        
    }    
    
}
