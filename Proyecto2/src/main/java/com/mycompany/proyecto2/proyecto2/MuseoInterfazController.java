/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto2.proyecto2;

import Clases.ManejoEntradas;
import Controladores.ColeccionJpaController;
import Clases.ManejoMantenimiento;
import Clases.ManejoReportes;
import Clases.ManejoValoracionSalas;
import Clases.ValidacionEntradas;
import Controladores.SalaJpaController;
import Persistencia.Coleccion;
import Persistencia.ComisionTarjetas;
import Persistencia.Precio;
import Persistencia.Sala;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

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
    private Button btnVender;
    @FXML
    private Button btnValidar;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblIVA;
    @FXML
    private Button btnValoraciones;
    @FXML
    private TableView<ManejoEntradas> tvContenidoVender;
    @FXML
    private Label lblSubtotal;
    @FXML
    private TableView<String[]> tvContenidoValidar;
    @FXML
    private Label lblTipoValoracion;
    @FXML
    private Label lblDetalleValoracion;
    @FXML
    private Tab tabMantenimiento;
    @FXML
    private Tab tabVender;
    @FXML
    private Tab tabValidar;
    @FXML
    private Tab tabValoracion;
    @FXML
    private Tab tabReportes;
    @FXML
    private Button btnReportes;
    @FXML
    private TextField nombreVisitanteTf;
    @FXML
    private ComboBox<Precio> salasCb;
    @FXML
    private DatePicker diasDp;
    @FXML
    private Button agregarBtn;
    
    @FXML
    private Button venderBtn;
    
    @FXML
    private ComboBox<ComisionTarjetas> tipoTarjetaCb;
    @FXML
    private ImageView imgQR;
    @FXML
    private Label lblFecha;
    @FXML
    private TextField txtImageName;
    @FXML
    private Button btnSearchQR;
    @FXML
    private Button NuevaEntradaBtn;
    @FXML
    private ImageView estrella1;
    @FXML
    private ImageView estrella2;
    @FXML
    private ImageView estrella3;
    @FXML
    private ImageView estrella4;
    @FXML
    private ImageView estrella5;
    @FXML
    private Button buscarQrBtn;
    @FXML
    private ImageView QrImagen;
    @FXML
    private TextField QrTf;
    @FXML
    private Label nombreSalaLbl;
    @FXML
    private Label nombreColeccionLbl;
    @FXML
    private Button validarQrBtn;
    @FXML
    private ImageView EspecieTematicaImg;
    @FXML
    private TextArea observacionTa;
    @FXML
    private DatePicker dateHasta;
    @FXML
    private DatePicker dateDesde;
    @FXML
    private Button btnGenerarTotalComisiones;
    @FXML
    private Button btnMejoresSalas;
    @FXML
    private Button btnPeoresSalas;
    @FXML
    private Button btnGenerarTopCinco;
    @FXML
    private ImageView imgMuseo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvContenido.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvContenidoValidar.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvContenidoVender.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        infoLbl.setVisible(false);
        infoTxt.setVisible(false);
        GuardarBtn.setVisible(false);
        infoCb.setVisible(false);
        
        Image image = new Image("/Imágenes/museum-line-icon-logo-free-vector.jpg/");
        
        
        imgMuseo.setImage(image);
        
        ManejoMantenimiento interfaz = new ManejoMantenimiento();
        
        ColeccionJpaController ctrl = new ColeccionJpaController();
        
        ManejoEntradas entradas = new ManejoEntradas();
        
        ManejoValoracionSalas valoracionSalas = new ManejoValoracionSalas();
        
        ManejoReportes manejoReportes = new ManejoReportes();

        
        
        interfaz.manejoInterfaz(SalaBtn, coleccionBtn, especiesBtn, tematicasBtn, preciosBtn, comisionesBtn, tvContenido, 
                infoTxt, GuardarBtn, infoLbl, insertarBtn, FiltrarTf, FiltrarCb, FiltrarBtn, eliminarBtn, editarBtn, infoCb, 
                tabMantenimiento, tabVender, tabValidar, tabValoracion, tabReportes, tpContenidos, btnValoraciones, btnVender, 
                btnValidar, btnReportes);
        
        entradas.ventaEntradas(nombreVisitanteTf, tipoTarjetaCb, salasCb, diasDp, agregarBtn,venderBtn, lblSubtotal, lblIVA,
                lblTotal, tvContenidoVender, btnValidar, tvContenidoValidar, btnSearchQR, imgQR, txtImageName, NuevaEntradaBtn);
        
        valoracionSalas.generarQRSalas();
        valoracionSalas.filePicker(buscarQrBtn, QrTf, QrImagen);
        valoracionSalas.validarSalas(validarQrBtn, EspecieTematicaImg, nombreSalaLbl, nombreColeccionLbl, estrella1, 
                estrella2, estrella3, estrella4, estrella5, observacionTa);
        
        manejoReportes.totalComisiones(btnGenerarTotalComisiones, dateDesde, dateHasta);
        manejoReportes.mejoresYPeores(btnMejoresSalas, btnPeoresSalas, btnGenerarTopCinco);
        
    }    
    
    
    //Cambios de escena con Tap Pane
    @FXML
    private void cambiarVender(ActionEvent event) {
        tpContenidos.getSelectionModel().select(tabVender);
    }

    @FXML
    private void cambiarValidar(ActionEvent event) {
        tpContenidos.getSelectionModel().select(tabValidar);
    }
    @FXML
    private void cambiarValoracion(ActionEvent event){
        tpContenidos.getSelectionModel().select(tabValoracion);
    }
    @FXML
    private void cambiarReportes(ActionEvent event) {
        tpContenidos.getSelectionModel().select(tabReportes);
    }

}
