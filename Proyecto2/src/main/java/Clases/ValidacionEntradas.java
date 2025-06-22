/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Persistencia.Entrada;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author aleja
 */
public class ValidacionEntradas {
    private File selectedFile;
    private ValidacionEntradas qrCode; 


    public ValidacionEntradas() {
    }

    public ValidacionEntradas(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
     
    public void generarQR(String contenidoQR, Entrada entrada) {
    int idEntrada = entrada.getId();
    String nombreArchivo = String.valueOf(idEntrada);
    String ruta = "src/main/resources/QR/" + nombreArchivo + ".jpg";
    File archivo = new File(ruta);

    try {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(contenidoQR, BarcodeFormat.QR_CODE, 300, 300);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);

        archivo.getParentFile().mkdirs();
        ImageIO.write(bufferedImage, "jpg", archivo);

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    public Entrada validarEntradaPorQR(File codigoQR, ArrayList<Entrada> entradas) {
        try {
            BufferedImage bufferedImage = ImageIO.read(codigoQR);
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = new MultiFormatReader().decode(bitmap);
            String contenidoQR = result.getText();

            String nombreArchivo = codigoQR.getName();
            String nombreSinExtension = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));
            int idDesdeQR = Integer.parseInt(nombreSinExtension);

            for (Entrada entrada : entradas) {
                if (entrada.getId() == idDesdeQR && !entrada.getAutorizada()) {
                    System.out.println(" QR valido para la entrada con ID: " + idDesdeQR);
                    System.out.println("Contenido del QR: " + contenidoQR);
                    return entrada; 
                }
            }

            System.out.println("El QR no corresponde a ninguna entrada válida en la lista.");
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void filePicker(Button buscarImgBtn, TextField txtImageName, ImageView imgQR) {
        buscarImgBtn.setOnAction(event-> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccione el codigo QR");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg")
            );

            selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {

                txtImageName.setText(selectedFile.getName());

                try {

                    Image image = new Image(selectedFile.toURI().toString());
                    imgQR.setImage(image);

                } catch (Exception error) {
                        error.printStackTrace();
                    }
            }
        });
    }
    
    public void validarEntrada(Button validarEntrada, TableView tvContenidoValidar, ArrayList<Entrada> entradas) {
        qrCode = new ValidacionEntradas();

        validarEntrada.setOnAction(event -> {
            if (selectedFile == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Archivo no seleccionado");
                alert.setContentText("Por favor, seleccione una imagen de código QR antes de validar.");
                alert.showAndWait();
                return;
            }

            Entrada entradaValidada = validarEntradaPorQR(selectedFile, entradas);

            if (entradaValidada != null) {
                entradaValidada.setAutorizada(true);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Éxito");
                alert.setContentText("La entrada ha sido validada con éxito");
                alert.showAndWait();

                 // Crear objeto para mostrar en tabla
                ManejoEntradas m = new ManejoEntradas();
                m.setEntradaActual(entradaValidada);

                // Obtener lista actual de la tabla
                ObservableList<ManejoEntradas> listaActual = tvContenidoValidar.getItems();

                if (listaActual == null) {
                    listaActual = FXCollections.observableArrayList();
                    tvContenidoValidar.setItems(listaActual);
                }

                // Verifica si ya está la entrada en la tabla (por ID)
                boolean yaExiste = listaActual.stream().anyMatch(e -> 
                    e.getEntradaActual().getId() == entradaValidada.getId());

                if (!yaExiste) {
                    listaActual.add(m);
                    tvContenidoValidar.refresh();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Desaprobada");
                alert.setContentText("La entrada no ha sido aprobada");
                alert.showAndWait();
            }
        });
    }
    
    public void configurarColumnas(TableView<ManejoEntradas> tabla) {
        tabla.getItems().clear();
        tabla.getColumns().clear();
        TableColumn<ManejoEntradas, String> sala = new TableColumn<>("Sala");
        sala.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getEntradaActual().getIdSala().toString()));

        TableColumn<ManejoEntradas, String> dia = new TableColumn<>("Día");
        dia.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().getEntradaActual().getFechaVisita().toString()));

        tabla.getColumns().addAll(sala, dia);
    }
    
}
