/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Controladores.ComisiontarjetasJpaController;
import Controladores.EntradaJpaController;
import Controladores.PrecioJpaController;
import Controladores.SalaJpaController;
import Persistencia.ComisionTarjetas;
import Persistencia.Entrada;
import Persistencia.Especies;
import Persistencia.Precio;
import Persistencia.Sala;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.Permission;
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
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.io.File;
import java.security.GeneralSecurityException;
import javafx.scene.image.ImageView;

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
    private ArrayList<LocalDate> diaSeleccionado;
    private LocalDate diaSelect;
    private ObservableList<ManejoEntradas> listaEntradas = FXCollections.observableArrayList();
    private ValidacionEntradas qrCode;
    private ArrayList<ComisionTarjetas> tipoTarjeta;
    private EntradaJpaController entradaCtrl;
    private Entrada entrada;
    
    public ManejoEntradas(){
        comisionCtrl = new ComisiontarjetasJpaController();
        precioCtrl = new PrecioJpaController();
        listaSalas = new ArrayList();
        diaSeleccionado = new ArrayList();
        qrCode = new ValidacionEntradas();
        tipoTarjeta = new ArrayList();
        entradaCtrl = new EntradaJpaController();
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

    public ArrayList<LocalDate> getDiaSeleccionado() {
        return diaSeleccionado;
    }

    public void setDiaSeleccionado(ArrayList<LocalDate> diaSeleccionado) {
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

    public LocalDate getDiaSelect() {
        return diaSelect;
    }

    public void setDiaSelect(LocalDate diaSelect) {
        this.diaSelect = diaSelect;
    }

    public ArrayList<ComisionTarjetas> getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(ArrayList<ComisionTarjetas> tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public Entrada getEntradaActual() {
        return entrada;
    }

    public void setEntradaActual(Entrada entradaActual) {
        this.entrada = entradaActual;
    }
    
    //Funcionamiento para la venta de entradas
    public void ventaEntradas(TextField nombreVisitanteTf, ComboBox <ComisionTarjetas> tipoTarjetaCb, 
            ComboBox<Precio> salasCb, DatePicker diaDp, Button agregarBtn,Button btnVender, Label subTotalLbl, 
            Label ivaLbl, Label totalLbl,TableView<ManejoEntradas> tabla, Button validarBtn, TableView validarTv,
            Button buscarImgBtn, ImageView qr, TextField imgTf, Button nuevaEntradaBtn) {
        
        //Muestra las Salas disponibles en el ComboBox de Salas
        Collection<Precio> preciosActualizadas = precioCtrl.findPrecioEntities();
        ObservableList<Precio> listaPrecios = FXCollections.observableArrayList(preciosActualizadas);
        salasCb.setItems(listaPrecios);
        
        //Muestra las Tarjetas disponibles en el ComboBox de Tipo de Tarjetas
        Collection<ComisionTarjetas> comisionesActualizadas = comisionCtrl.findComisionTarjetasEntities();
        ObservableList<ComisionTarjetas> listaComisiones= FXCollections.observableArrayList(comisionesActualizadas);
        tipoTarjetaCb.setItems(listaComisiones);
        
        nuevaEntradaBtn.setOnAction(event-> {
            nombreVisitanteTf.setDisable(false);
            nombreVisitanteTf.clear();
            tipoTarjetaCb.setValue(null);
            salasCb.setValue(null);
            diaDp.setValue(null);
            subTotalLbl.setText("");
            ivaLbl.setText("");
            totalLbl.setText("");
            tabla.getItems().clear();
            tabla.getColumns().clear();
            validarTv.getItems().clear();
            validarTv.getColumns().clear();
            imgTf.clear();
            qr.setImage(null);
        });
 
        agregarBtn.setOnAction(event -> {
            btnVender.setDisable(false);
            
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
            
            listaSalas.add(salasCb.getValue());
            diaSeleccionado.add(diaDp.getValue());
            tipoTarjeta.add(tipoTarjetaCb.getValue());

            //IMPORTANTE: Crear una instancia para que todo se maneje en esa misma instancia
            ManejoEntradas entradas = new ManejoEntradas();
            
            entradas.setNombreVisitante(nombreVisitanteTf.getText());
            entradas.setTipoTarjeta(new ArrayList<>(tipoTarjeta));
            entradas.setSalaSeleccionada(salasCb.getValue());
            entradas.setListaSalas(new ArrayList<>(listaSalas));
            entradas.setNombreSala(salasCb.getValue().toString());
            entradas.setDiaSeleccionado(new ArrayList<>(diaSeleccionado));
            entradas.setDiaSelect(diaDp.getValue());


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            entradas.setDia(diaDp.getValue().format(formatter));

            entradas.calcularPrecioEntrada();
            entradas.calcularSubTotal();
            entradas.calcularIVA();
            entradas.calcularTotal();
            entradas.calcularComision();
            
            subTotalLbl.setText(String.format("%.2f", entradas.getSubTotal()));
            ivaLbl.setText(String.format("%.2f", entradas.getIva()));
            totalLbl.setText(String.format("%.2f", entradas.getTotalPagar()));
            System.out.println(String.format("%.2f", entradas.getComision()));
            
            
            entrada = new Entrada();
            
            entrada.setIdSala(salasCb.getValue().getIdSala());
            entrada.setNombreVisitante(nombreVisitanteTf.getText());
            nombreVisitanteTf.setDisable(true);
            LocalDate localDate = diaDp.getValue();
            Date date = java.sql.Date.valueOf(localDate);
            entrada.setFechaVisita(date);
            entrada.setPrecio(entradas.getSubTotal());
            entrada.setComision(entradas.getComision());
            entrada.setIva(entradas.getIva());
            entrada.setTotalPagar(entradas.getTotalPagar());
            entrada.setAutorizada(false);
            
            listaEntradas.add(entradas);
            tabla.setItems(listaEntradas);
            tabla.refresh();
            setEntradaActual(entrada);

            tipoTarjetaCb.setValue(null);
            salasCb.setValue(null);
            diaDp.setValue(null);
        });

        btnVender.setDisable(true);
        
        // Configurar columnas de la tabla
        cargarDatos(tabla);
        
        
        //crea el qr con los datos necesarios de la entrada
        btnVender.setOnAction(e -> {
            nombreVisitanteTf.setDisable(false);
            JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
            String carpeta = "archivos_generados";
            new File(carpeta).mkdirs();

            try {
                InputStream in = ManejoEntradas.class.getResourceAsStream("/.json/credenciales.json");
                if (in == null) throw new FileNotFoundException("No se encontró credenciales.json");

                GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
                ArrayList<Entrada> entradasFinales = new ArrayList<>();

                for (int i = 0; i < listaSalas.size(); i++) {
                    Entrada entradaInd = new Entrada();
                    entradaInd.setNombreVisitante(nombreVisitanteTf.getText());
                    entradaInd.setIdSala(listaSalas.get(i).getIdSala());
                    entradaInd.setFechaVisita(java.sql.Date.valueOf(diaSeleccionado.get(i)));
                    entradaInd.setPrecio(listaEntradas.get(0).getSubTotal());
                    entradaInd.setComision(listaEntradas.get(0).getComision());
                    entradaInd.setIva(listaEntradas.get(0).getIva());
                    entradaInd.setTotalPagar(listaEntradas.get(0).getTotalPagar());
                    entradaInd.setAutorizada(false);

                    entradaCtrl.create(entradaInd);

                    String contenido = generarContenidoPDF(entradaInd);
                    String ruta = carpeta + "/entrada_" + entradaInd.getId() + ".pdf";
                    crearPDF(ruta, contenido);
                    String link = subirPDFAGoogleDrive(new File(ruta), clientSecrets, JSON_FACTORY);

                    qrCode.generarQR(link, entradaInd);
                    entradasFinales.add(entradaInd);
                }

                qrCode.configurarColumnas(validarTv);
                qrCode.validarEntrada(validarBtn, validarTv, entradasFinales);
                qrCode.filePicker(buscarImgBtn, imgTf, qr); 

            } catch (IOException | GeneralSecurityException ex) {
                ex.printStackTrace();
            }
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

        if (getDiaSelect().getDayOfWeek() == DayOfWeek.SUNDAY) {
            setPrecioEntrada(precioDomingo);
        } else {
            setPrecioEntrada(precioLunSab);
        }
    }
    
    //Calcula el precio de la entrada con la comisión de la tarjeta
    public void calcularSubTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < listaSalas.size(); i++) {
            Precio sala = listaSalas.get(i);
            LocalDate tipoDia = diaSeleccionado.get(i);

            if (tipoDia.getDayOfWeek() == DayOfWeek.SUNDAY) {
                total = total.add(sala.getPrecioDomingo());
            } else {
                total = total.add(sala.getPrecioLunesSabado());
            }
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
    
    //Calcula el totalComision
    public void calcularTotal () {
        BigDecimal total = getSubTotal().add(getIva());
        setTotalPagar(total);
    }
    
    public void calcularComision() {
        BigDecimal ivaPorcentaje = BigDecimal.valueOf(1.13);
        BigDecimal totalComision = BigDecimal.ZERO;

        for (int i = 0; i < listaSalas.size(); i++) {
            Precio sala = listaSalas.get(i);
            LocalDate tipoDia = diaSeleccionado.get(i);
            BigDecimal comision = getTipoTarjeta().get(i).getComision();

            BigDecimal precioBase;
            if (tipoDia.getDayOfWeek() == DayOfWeek.SUNDAY) {
                precioBase = sala.getPrecioDomingo();
            } else {
                precioBase = sala.getPrecioLunesSabado();
            }

            BigDecimal precioConIVA = precioBase.multiply(ivaPorcentaje);
            BigDecimal comisionIndividual = precioConIVA.multiply(comision);

            totalComision = totalComision.add(comisionIndividual);
        }

        setComision(totalComision);
    }
    
    public static void crearPDF(String ruta, String contenido) {
        try {
            PdfWriter writer = new PdfWriter(ruta);
            PdfDocument pdf = new PdfDocument(writer);
            Document documento = new Document(pdf);

            documento.add(new Paragraph(contenido));

            documento.close();
            System.out.println("PDF creado en: " + ruta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String generarContenidoPDF(Entrada entrada) {
        return "Sala: " + entrada.getIdSala() + "\n" + "Fecha de visita: " + entrada.getFechaVisita();
    }
    
    public static String subirPDFAGoogleDrive(File archivoPdf, GoogleClientSecrets clientSecrets, JsonFactory JSON_FACTORY) 
            throws IOException, GeneralSecurityException {

        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets,
                Collections.singleton(DriveScopes.DRIVE_FILE))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        Drive service = new Drive.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName("Proyecto2")
                .build();

        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setName(archivoPdf.getName());

        FileContent mediaContent = new FileContent("application/pdf", archivoPdf);

        com.google.api.services.drive.model.File uploadedFile = service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();

        Permission permiso = new Permission()
                .setType("anyone")
                .setRole("reader");

        service.permissions().create(uploadedFile.getId(), permiso).execute();

        return "https://drive.google.com/file/d/" + uploadedFile.getId() + "/view?usp=sharing";
    }
}

