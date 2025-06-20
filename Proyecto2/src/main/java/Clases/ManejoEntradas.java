/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Controladores.ComisiontarjetasJpaController;
import Controladores.PrecioJpaController;
import Controladores.SalaJpaController;
import Persistencia.ComisionTarjetas;
import Persistencia.Especies;
import Persistencia.Precio;
import Persistencia.Sala;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Axton Urbina
 */
public class ManejoEntradas {
    private String nombreVisitante;
    private String nombreSala;
    private String dia;
    private Precio salaSeleccionada;
    private ArrayList <Precio> listaSalas;
    private BigDecimal precioEntrada;
    private BigDecimal subTotal;
    private BigDecimal iva;
    private BigDecimal totalPagar;
    private ComisiontarjetasJpaController comisionCtrl;
    private BigDecimal comision;
    private PrecioJpaController precioCtrl;
    private LocalDate diaSeleccionado;
    private ObservableList<ManejoEntradas> listaEntradas = FXCollections.observableArrayList();
    private QRManipulador qrCode = new QRManipulador();
    
    public ManejoEntradas(){
        comisionCtrl = new ComisiontarjetasJpaController();
        precioCtrl = new PrecioJpaController();
        listaSalas = new ArrayList();
    }
    
    //Setters y Getters
    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public void setNombreVisitante(String nombreVisitante) {
        this.nombreVisitante = nombreVisitante;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String salaSeleccionada) {
        this.nombreSala = salaSeleccionada;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String fecha) {
        this.dia = fecha;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(BigDecimal totalPagar) {
        this.totalPagar = totalPagar;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }
    
    public LocalDate getDiaSeleccionado() {
        return diaSeleccionado;
    }

    public void setDiaSeleccionado(LocalDate diaSeleccionado) {
        this.diaSeleccionado = diaSeleccionado;
    }

    public BigDecimal getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(BigDecimal precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public Precio getSalaSeleccionada() {
        return salaSeleccionada;
    }

    public void setSalaSeleccionada(Precio salaSeleccionada) {
        this.salaSeleccionada = salaSeleccionada;
    }

    public ArrayList<Precio> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(ArrayList<Precio> listaSalas) {
        this.listaSalas = listaSalas;
    }
    
    

    //Funcionamiento para la venta de entradas
    public void ventaEntradas(TextField nombreVisitanteTf, ComboBox <ComisionTarjetas> tipoTarjetaCb, 
            ComboBox<Precio> salasCb, DatePicker diaDp, Button agregarBtn,Button btnVender, Label subTotalLbl, Label ivaLbl, Label totalLbl,
            TableView<ManejoEntradas> tabla) {
        
        //Muestra las Salas disponibles en el ComboBox de Salas
        Collection<Precio> preciosActualizadas = precioCtrl.findPrecioEntities();
        ObservableList<Precio> listaPrecios = FXCollections.observableArrayList(preciosActualizadas);
        salasCb.setItems(listaPrecios);
        
        //Muestra las Tarjetas disponibles en el ComboBox de Tipo de Tarjetas
        Collection<ComisionTarjetas> comisionesActualizadas = comisionCtrl.findComisionTarjetasEntities();
        ObservableList<ComisionTarjetas> listaComisiones= FXCollections.observableArrayList(comisionesActualizadas);
        tipoTarjetaCb.setItems(listaComisiones);
 
        agregarBtn.setOnAction(event -> {
            
            //Si alguno de los campos está vacío
            if (nombreVisitanteTf.getText().isEmpty() || tipoTarjetaCb.getValue() == null ||
                salasCb.getValue() == null || diaDp.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("Todos los campos deben estar completos.");
                alert.showAndWait();
                return;
            }

            //IMPORTANTE: Crear una instancia para que todo se maneje en esa misma instancia
            ManejoEntradas nuevaEntrada = new ManejoEntradas();
            
            nuevaEntrada.setNombreVisitante(nombreVisitanteTf.getText());
            nuevaEntrada.setComision(tipoTarjetaCb.getValue().getComision());
            nuevaEntrada.setSalaSeleccionada(salasCb.getValue());
            listaSalas.add(salasCb.getValue());
            nuevaEntrada.setListaSalas(listaSalas);
            nuevaEntrada.setNombreSala(salasCb.getValue().toString());
            nuevaEntrada.setDiaSeleccionado(diaDp.getValue());


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            nuevaEntrada.setDia(diaDp.getValue().format(formatter));

            nuevaEntrada.calcularPrecioEntrada();
            nuevaEntrada.calcularSubTotal();
            nuevaEntrada.calcularIVA();
            nuevaEntrada.calcularTotal();
            
            subTotalLbl.setText(String.format("%.2f", nuevaEntrada.getSubTotal()));
            ivaLbl.setText(String.format("%.2f", nuevaEntrada.getIva()));
            totalLbl.setText(String.format("%.2f", nuevaEntrada.getTotalPagar()));
            
            listaEntradas.add(nuevaEntrada);
            tabla.setItems(listaEntradas);
            tabla.refresh();

            nombreVisitanteTf.clear();
            tipoTarjetaCb.setValue(null);
            salasCb.setValue(null);
            diaDp.setValue(null);
        });

        // Configurar columnas de la tabla
        cargarDatos(tabla);
        
        
        //crea el qr con los datos que hay en la tabla (string)
        btnVender.setOnAction(e -> {
            
            List<String> union = new ArrayList<>();
            
            List<String> salas = new ArrayList<>();
            List<String> dias = new ArrayList<>();
            
            salas.addAll(
                    tabla.getItems()
                    .stream()
                    .map(obj -> ((ManejoEntradas) obj).getNombreSala())
                    .collect(Collectors.toList())
            );
            
            dias.addAll(
                    tabla.getItems()
                    .stream()
                    .map(obj -> ((ManejoEntradas) obj).getDia())
                    .collect(Collectors.toList())
            );
            
            String sala = String.join(",", salas);
            String dia = String.join(",", dias);
            
            union.add(sala);
            union.add(dia);
            
            String contenidoQR = String.join("|", union);
            
            System.out.println(contenidoQR);
            
            qrCode.generarQR(contenidoQR);  
            
        });
    }
    
    public void cargarDatos(TableView tabla) {

        tabla.getColumns().clear();
        
        TableColumn<ManejoEntradas, String> sala = new TableColumn<>("Sala");
        sala.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getNombreSala()));

        TableColumn<ManejoEntradas, String> dia = new TableColumn<>("Día");
        dia.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getDia()));

        TableColumn<ManejoEntradas, String> precio = new TableColumn<>("Precio");
        precio.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getPrecioEntrada().toString()));
        
        tabla.getColumns().addAll(sala, dia, precio);
    }
    
    //Calcula el precio de la entrada sin comisión de tarjeta
    public void calcularPrecioEntrada() {
        BigDecimal precioLunSab = getSalaSeleccionada().getPrecioLunesSabado();
        BigDecimal precioDomingo = getSalaSeleccionada().getPrecioDomingo();

        
        if (getDiaSeleccionado().getDayOfWeek() == DayOfWeek.SUNDAY) {
            setPrecioEntrada(precioDomingo);
        } else {
            setPrecioEntrada(precioLunSab);
        }
    }
    
    //Calcula el precio de la entrada con la comisión de la tarjeta
   public void calcularSubTotal() {
    BigDecimal total = BigDecimal.ZERO;        
    BigDecimal comisionTarjeta = getComision().add(BigDecimal.valueOf(1));
    
    for (Precio precio : getListaSalas()) {
        BigDecimal precioBase;

        if (getDiaSeleccionado().getDayOfWeek() == DayOfWeek.SUNDAY) {
            precioBase = precio.getPrecioDomingo();
        } else {
            precioBase = precio.getPrecioLunesSabado();
        }

        BigDecimal precioConComision = precioBase.multiply(comisionTarjeta);
        total = total.add(precioConComision);
    }

    setSubTotal(total);
}
    
   //Calcula el precio de la entrada con la comisión de la tarjeta + IVA
    public void calcularIVA() {
        BigDecimal ivaPorcentaje = BigDecimal.valueOf(0.13);
        BigDecimal subtotal = getSubTotal(); 
        BigDecimal ivaTotal = subtotal.multiply(ivaPorcentaje);
        setIva(ivaTotal);
    }
    
    //Calcula el total
    public void calcularTotal () {
        BigDecimal total = getSubTotal().add(getIva());
        setTotalPagar(total);
    }
            
}

