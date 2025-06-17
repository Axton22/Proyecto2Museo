/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2.proyecto2;

import Clases.ManejoEntradas;
import Controladores.ColeccionJpaController;
import Clases.ManejoMantenimiento;
import Controladores.SalaJpaController;
import Persistencia.Coleccion;
import Persistencia.ComisionTarjetas;
import Persistencia.Precio;
import Persistencia.Sala;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    private TabPane tpContenidos;
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
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Button btnVender;
    @FXML
    private Button btnValidar;
    @FXML
    private Tab tab3;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblIVA;
    @FXML
    private TextField nombreVisitanteTf;
    @FXML
    private ComboBox<Precio> salasCb;
    @FXML
    private DatePicker diasDp;
    @FXML
    private Button agregarBtn;
    @FXML
    private TableView<?> tvContenidoVender;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Button venderBtn;
    @FXML
    private TableView<?> tvContenidoValidar;
    @FXML
    private ComboBox<ComisionTarjetas> tipoTarjetaCb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoLbl.setVisible(false);
        infoTxt.setVisible(false);
        GuardarBtn.setVisible(false);
        infoCb.setVisible(false);

        ManejoEntradas entradas = new ManejoEntradas();
        ManejoMantenimiento interfaz = new ManejoMantenimiento();
        
        ColeccionJpaController ctrl = new ColeccionJpaController();
        
        
        interfaz.manejoInterfaz(SalaBtn, coleccionBtn, especiesBtn, tematicasBtn, preciosBtn, comisionesBtn, tvContenido, 
                infoTxt, GuardarBtn, infoLbl, insertarBtn, FiltrarTf, FiltrarCb, FiltrarBtn, eliminarBtn, editarBtn, 
                infoCb, tab1,tpContenidos);
        
        entradas.ventaEntradas(nombreVisitanteTf, tipoTarjetaCb, salasCb, diasDp, agregarBtn, lblSubtotal, lblIVA,
                lblTotal, tvContenidoVender);

    }    
    
    @FXML
    private void cambiarTab1(ActionEvent event){
        tpContenidos.getSelectionModel().select(tab1);
    }
    @FXML
    private void cambiarTab2(ActionEvent event) {
        tpContenidos.getSelectionModel().select(tab2);
    }

    @FXML
    private void cambiarTab3(ActionEvent event) {
        tpContenidos.getSelectionModel().select(tab3);
    }
    
}
