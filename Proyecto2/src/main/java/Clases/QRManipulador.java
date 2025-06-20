/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

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
import java.util.Random;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 *
 * @author aleja
 */
public class QRManipulador {
    
    private File selectedFile;

    public QRManipulador() {
    }

    public QRManipulador(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }
     
    public void generarQR(String contenidoQR){
        Random random = new Random();
        
        int numeroRandom = random.nextInt(100) +  1;
        
        String nombreArchivo = String.valueOf(numeroRandom);
        
        String ruta = "src/main/resources/QR/"+ nombreArchivo+".jpg";
        
        File archivo = new File(ruta);
        
        try {
            //genera el codigo QR
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(contenidoQR, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
            
            //mete el QR en un archivo jpg y lo guarda en la carpeta QR de el proyecto, aunque no este creada este la crea
            archivo.getParentFile().mkdirs();
            
            ImageIO.write(bufferedImage, "jpg", archivo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public String leerQR(File codigoQR){
        try {
            
            BufferedImage bufferedImage = ImageIO.read(codigoQR);
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            
            Result result = new MultiFormatReader().decode(bitmap);
            
            return result.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;
    }
}
