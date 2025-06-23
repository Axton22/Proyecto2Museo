/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Controladores.SalaJpaController;
import Controladores.ValoracionsalaJpaController;
import Persistencia.Sala;
import Persistencia.Valoracionsala;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.persistence.criteria.Path;

/**
 *
 * @author Axton Urbina
 */
public class ManejoValoracionSalas {
    File archivoSeleccionado;
    Valoracionsala valoracion;
    ValoracionsalaJpaController valoracionCtrl;

    public ManejoValoracionSalas() {
        valoracionCtrl = new ValoracionsalaJpaController();
    }
    
    public void generarQR(String contenidoQR, Sala sala) {
        String nombreArchivo = sala.getNombreSala();
        String ruta = "src/main/resources/QRSalas/" + nombreArchivo + ".jpg";
        File archivo = new File(ruta);
        
         if (archivo.exists()) {
            System.out.println("QR ya existe para la sala: " + nombreArchivo);
            return;
        }

        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(contenidoQR, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);

            // Crea la carpeta si no existe
            archivo.getParentFile().mkdirs();

            // Guarda la imagen en JPG
            ImageIO.write(bufferedImage, "jpg", archivo);

            System.out.println("QR generado para la sala " + sala.getNombreSala() + " en: " + ruta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void generarQRSalas() {
        SalaJpaController salaController = new SalaJpaController();
        Collection<Sala> salas = salaController.findSalaEntities();

        for (Sala sala : salas) {
            String contenidoQR = "Sala ID: " + sala.getId() + "\nNombre: " + sala.getNombreSala();
            generarQR(contenidoQR, sala);
        }
    }
    
    public void validarSalas(Button validarBtn, ImageView especieTematicaImg, Label nombreSalaLbl, Label nombreColeccionLbl,
            ImageView estrella1, ImageView estrella2, ImageView estrella3, ImageView estrella4, ImageView estrella5,
            TextArea observacionTa) {
        
        ImageView [] estrellas ={estrella1,estrella2,estrella3,estrella4,estrella5};
        
        File fileEst1 = new File("src/main/resources/Imágenes/EstrellaValoraciónAmarilla.png");
        Image EstrellaAmarilla = new Image(fileEst1.toURI().toString());
        
        File fileEst2 = new File("src/main/resources/Imágenes/EstrellaValoración.png");
        Image EstrellaGris = new Image(fileEst2.toURI().toString());

        validarBtn.setOnAction(event-> {
           if (archivoSeleccionado == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Archivo no seleccionado");
                alert.setContentText("Por favor, seleccione una imagen de código QR antes de validar.");
                alert.showAndWait();
                return;
            } 
           
            String nombreArchivo = archivoSeleccionado.getName().replace(".jpg", "").trim();
           
            if (nombreArchivo.equalsIgnoreCase("Sala de Historia Natural")) {
                File file = new File("src/main/resources/Imágenes/T-rex.jpg");
                Image img = new Image(file.toURI().toString());
                
                especieTematicaImg.setImage(img);
                nombreSalaLbl.setText("Sala de Historia Natural");
                nombreColeccionLbl.setText("Coleccion de Fósiles");
                
                
                for (int i = 0; i < estrellas.length; i++) {
                    final int indice = i;
                    
                    estrellas[i].setOnMouseClicked(e ->{
                    
                        for (int j = 0; j < estrellas.length; j++) {
                            if(j<=indice){
                                estrellas[j].setImage(EstrellaAmarilla);
                            }else{
                            
                                estrellas[j].setImage(EstrellaGris);
                                
                            }
                        }
                        SalaJpaController salaController = new SalaJpaController();
                        Sala sala = salaController.findSala(10); 

                        valoracion = new Valoracionsala();
                    
                        valoracion.setIdSala(sala); 
                        valoracion.setValoracion(indice + 1);
                    
                    if (observacionTa.getText().isEmpty()) {
                        valoracion.setObservacion("No ingresaron observación");
                        
                    } else {
                        valoracion.setObservacion(observacionTa.getText());
                    }
                    
                    valoracionCtrl.create(valoracion);
                    
                    });
                    
                }
                
                
                
            } else if (nombreArchivo.equalsIgnoreCase("Sala de música")) {
                
                File file = new File("src/main/resources/Imágenes/musica(2).jpeg");
                Image img = new Image(file.toURI().toString());
                
                especieTematicaImg.setImage(img);
                nombreSalaLbl.setText("Sala de música");
                nombreColeccionLbl.setText("Coleccion de Instrumentos");
                
                for (int i = 0; i < estrellas.length; i++) {
                    final int indice = i;
                    
                    estrellas[i].setOnMouseClicked(e ->{
                    
                        for (int j = 0; j < estrellas.length; j++) {
                            if(j<=indice){
                                estrellas[j].setImage(EstrellaAmarilla);
                            }else{
                            
                                estrellas[j].setImage(EstrellaGris);
                                
                            }
                        }
                        SalaJpaController salaController = new SalaJpaController();
                        Sala sala = salaController.findSala(10); 

                        valoracion = new Valoracionsala();
                    
                        valoracion.setIdSala(sala); 
                        valoracion.setValoracion(indice + 1);
                    
                    if (observacionTa.getText().isEmpty()) {
                        valoracion.setObservacion("No ingresaron observación");
                        
                    } else {
                        valoracion.setObservacion(observacionTa.getText());
                    }
                    
                    valoracionCtrl.create(valoracion);
                    
                    });
                    
                }
                
                System.out.println("ℹ️ Info: Sala de Música");
                
            } else if (nombreArchivo.equalsIgnoreCase("Sala del Tiempo y el Clima")) {
                
                File file = new File("src/main/resources/Imágenes/tiempoYClima.jpg");
                Image img = new Image(file.toURI().toString());
                
                especieTematicaImg.setImage(img);
                nombreSalaLbl.setText("Sala del Tiempo y el Clima");
                nombreColeccionLbl.setText("Coleccion de Instrumentos de Medicion");
                
                for (int i = 0; i < estrellas.length; i++) {
                    final int indice = i;
                    
                    estrellas[i].setOnMouseClicked(e ->{
                    
                        for (int j = 0; j < estrellas.length; j++) {
                            if(j<=indice){
                                estrellas[j].setImage(EstrellaAmarilla);
                            }else{
                            
                                estrellas[j].setImage(EstrellaGris);
                                
                            }
                        }
                        SalaJpaController salaController = new SalaJpaController();
                        Sala sala = salaController.findSala(10); 

                        valoracion = new Valoracionsala();
                    
                        valoracion.setIdSala(sala); 
                        valoracion.setValoracion(indice + 1);
                    
                    if (observacionTa.getText().isEmpty()) {
                        valoracion.setObservacion("No ingresaron observación");
                        
                    } else {
                        valoracion.setObservacion(observacionTa.getText());
                    }
                    
                    valoracionCtrl.create(valoracion);
                    
                    });
                    
                }
                
                System.out.println("ℹ️ Info: Sala del Tiempo y el Clima");
                
            } else if (nombreArchivo.equalsIgnoreCase("Sala de espacio exterior")) {
                
                File file = new File("src/main/resources/Imágenes/astronauta.jpg");
                Image img = new Image(file.toURI().toString());
                
                especieTematicaImg.setImage(img);
                nombreSalaLbl.setText("Sala de espacio exterior");
                nombreColeccionLbl.setText("Coleccion de partes de Cohete");
                
                for (int i = 0; i < estrellas.length; i++) {
                    final int indice = i;
                    
                    estrellas[i].setOnMouseClicked(e ->{
                    
                        for (int j = 0; j < estrellas.length; j++) {
                            if(j<=indice){
                                estrellas[j].setImage(EstrellaAmarilla);
                            }else{
                            
                                estrellas[j].setImage(EstrellaGris);
                                
                            }
                        }
                        SalaJpaController salaController = new SalaJpaController();
                        Sala sala = salaController.findSala(10); 

                        valoracion = new Valoracionsala();
                    
                        valoracion.setIdSala(sala); 
                        valoracion.setValoracion(indice + 1);
                    
                    if (observacionTa.getText().isEmpty()) {
                        valoracion.setObservacion("No ingresaron observación");
                        
                    } else {
                        valoracion.setObservacion(observacionTa.getText());
                    }
                    
                    valoracionCtrl.create(valoracion);
                    
                    });
                    
                }
                
                System.out.println("ℹ️ Info: Sala de Espacio Exterior");
                
            } else if (nombreArchivo.equalsIgnoreCase("Sala de civilizaciones perdidas")) {
                
                File file = new File("src/main/resources/Imágenes/civilizacionesPerdidas.jpg");
                Image img = new Image(file.toURI().toString());
                
                especieTematicaImg.setImage(img);
                nombreSalaLbl.setText("Sala de civilizaciones perdidas");
                nombreColeccionLbl.setText("Coleccion de piedras");
                
                for (int i = 0; i < estrellas.length; i++) {
                    final int indice = i;
                    
                    estrellas[i].setOnMouseClicked(e ->{
                    
                        for (int j = 0; j < estrellas.length; j++) {
                            if(j<=indice){
                                estrellas[j].setImage(EstrellaAmarilla);
                            }else{
                            
                                estrellas[j].setImage(EstrellaGris);
                                
                            }
                        }
                        SalaJpaController salaController = new SalaJpaController();
                        Sala sala = salaController.findSala(10); 

                        valoracion = new Valoracionsala();
                    
                        valoracion.setIdSala(sala); 
                        valoracion.setValoracion(indice + 1);
                    
                    if (observacionTa.getText().isEmpty()) {
                        valoracion.setObservacion("No ingresaron observación");
                        
                    } else {
                        valoracion.setObservacion(observacionTa.getText());
                    }
                    
                    valoracionCtrl.create(valoracion);
                    
                    });
                    
                }
                
                System.out.println("ℹ️ Info: Sala de Civilizaciones Perdidas");
                
            } else if (nombreArchivo.equalsIgnoreCase("Sala de Inventos")) {
                
                File file = new File("src/main/resources/Imágenes/civilizacionesPerdidas.jpg");
                Image img = new Image(file.toURI().toString());
                
                especieTematicaImg.setImage(img);
                nombreSalaLbl.setText("inventos.jpeg");
                nombreColeccionLbl.setText("Expocision de prototipos");
                
                for (int i = 0; i < estrellas.length; i++) {
                    final int indice = i;
                    
                    estrellas[i].setOnMouseClicked(e ->{
                    
                        for (int j = 0; j < estrellas.length; j++) {
                            if(j<=indice){
                                estrellas[j].setImage(EstrellaAmarilla);
                            }else{
                            
                                estrellas[j].setImage(EstrellaGris);
                                
                            }
                        }
                        SalaJpaController salaController = new SalaJpaController();
                        Sala sala = salaController.findSala(10); 

                        valoracion = new Valoracionsala();
                    
                        valoracion.setIdSala(sala); 
                        valoracion.setValoracion(indice + 1);
                    
                    if (observacionTa.getText().isEmpty()) {
                        valoracion.setObservacion("No ingresaron observación");
                        
                    } else {
                        valoracion.setObservacion(observacionTa.getText());
                    }
                    
                    valoracionCtrl.create(valoracion);
                    
                    });
                    
                }
                
                System.out.println("ℹ️ Info: Sala de Inventos");
                
            } else if (nombreArchivo.equalsIgnoreCase("Sala de Especies en Extinción")) {
                
                
                File file = new File("src/main/resources/Imágenes/peligroExtincion.JPG");
                Image img = new Image(file.toURI().toString());
                
                especieTematicaImg.setImage(img);
                nombreSalaLbl.setText("Sala de Especies en Extinción");
                nombreColeccionLbl.setText("Coleccion de Especies en Peligro de exticion");
                
                for (int i = 0; i < estrellas.length; i++) {
                    final int indice = i;
                    
                    estrellas[i].setOnMouseClicked(e ->{
                    
                        for (int j = 0; j < estrellas.length; j++) {
                            if(j<=indice){
                                estrellas[j].setImage(EstrellaAmarilla);
                            }else{
                            
                                estrellas[j].setImage(EstrellaGris);
                                
                            }
                        }
                        SalaJpaController salaController = new SalaJpaController();
                        Sala sala = salaController.findSala(10); 

                        valoracion = new Valoracionsala();
                    
                        valoracion.setIdSala(sala); 
                        valoracion.setValoracion(1);
                    
                    if (observacionTa.getText().isEmpty()) {
                        valoracion.setObservacion("No ingresaron observación");
                        
                    } else {
                        valoracion.setObservacion(observacionTa.getText());
                    }
                    
                    valoracionCtrl.create(valoracion);
                    
                    });
                    
                }
                
                
            } else {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Sala no reconocida");
                alerta.setContentText("El código QR no corresponde a ninguna sala conocida.");
                alerta.showAndWait();
            }   
        });
    }
    
    public void filePicker(Button buscarQrBtn, TextField imagenTf, ImageView qrImagen) {
        buscarQrBtn.setOnAction(event-> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccione el codigo QR");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg")
            );

            archivoSeleccionado = fileChooser.showOpenDialog(null);

            if (archivoSeleccionado != null) {

                imagenTf.setText(archivoSeleccionado.getName());

                try {

                    Image image = new Image(archivoSeleccionado.toURI().toString());
                    qrImagen.setImage(image);

                } catch (Exception error) {
                        error.printStackTrace();
                    }
            }
            
        });
    }
}

