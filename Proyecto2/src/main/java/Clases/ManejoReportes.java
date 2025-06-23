
package Clases;

import Controladores.EntradaJpaController;
import Controladores.ValoracionsalaJpaController;
import Persistencia.Entrada;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;


public class ManejoReportes {
    
    EntradaJpaController EntradaController = new EntradaJpaController();
    ValoracionsalaJpaController ValoracionController = new ValoracionsalaJpaController();
    String bandera = "";
    boolean mejor = true;
    

    public ManejoReportes() {}

    public void totalComisiones(Button validarYGenerarPDF,DatePicker desde,DatePicker hasta){
        
        String rutaCarpeta = "Reportes";
        String rutaArchivo = rutaCarpeta + "/reporte.pdf";
        
        validarYGenerarPDF.setOnAction(e -> {
            
            LocalDate fechaInicio = desde.getValue();
            LocalDate fechaFinal = hasta.getValue();
            
            List<Object[]> datos = EntradaController.obtenerTotalesPorTarjeta(fechaInicio, fechaFinal);
            List<String> contenido = new ArrayList<>();
             for (Object[] fila : datos) {
                String nombre = (String) fila[0];
                BigDecimal totalBD =(BigDecimal)fila[1];
                Double total = totalBD.doubleValue();
                
                System.out.println(nombre + ": "+ String.format("%.2f", total));
                
                String linea = nombre + ": "+String.format("%.2f", total);
                
                contenido.add(linea);
            }
             
             crearPDF(contenido,rutaArchivo);
        });
    }
    
    public void mejoresYPeores(Button btnMejoresSalas, Button btnPeoresSalas,Button generarPDF){
        
        String rutaCarpeta = "ReportesValoracion";
        String rutaArchivo = rutaCarpeta + "/reporte.pdf";
        
        generarPDF.setDisable(true);
        
        btnMejoresSalas.setOnAction(event -> {
        
            mejor = true;
            bandera = "MEJOR";
            
            btnMejoresSalas.setDisable(true);
            btnPeoresSalas.setDisable(false);
            
            generarPDF.setDisable(false);
            
        });
        
        btnPeoresSalas.setOnAction(event -> {
        
            mejor = false;
            bandera = "PEOR";
            
            btnMejoresSalas.setDisable(false);
            btnPeoresSalas.setDisable(true);
            
            generarPDF.setDisable(false);
            
        });
        
        generarPDF.setOnAction(event ->{
        
            List<Object[]>  datos =  ValoracionController.obtenerTopCinco(mejor);
            List<String> contenido = new ArrayList<>();
            if(datos == null || datos.isEmpty()){
                System.out.println("Vacio");
            }else{
                for (Object[] fila :datos) {
                    String nombreSala = (String) fila[0];
                    Double promedio = ((Number) fila[1]).doubleValue();
                    System.out.println(nombreSala + ": " + promedio);
                    
                    String linea = nombreSala + String.format("%.2f", promedio);
                    
                    contenido.add(linea);
                }
                crearPDF(contenido,rutaArchivo);
            }
        });
        
    }
    
    public void crearPDF(List<String> contenido,String ruta) {
    

    try {
        File carpeta = new File(ruta);
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // crea la carpeta si no existe
        }

        PdfWriter writer = new PdfWriter(ruta);
        PdfDocument pdf = new PdfDocument(writer);
        Document documento = new Document(pdf);

        documento.add(new Paragraph("Reporte generado\n"));

        for (String linea : contenido) {
            documento.add(new Paragraph(linea));
        }

        documento.close();
        System.out.println("PDF creado en: " + ruta);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
