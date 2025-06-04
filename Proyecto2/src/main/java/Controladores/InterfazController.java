/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Persistencia.Sala;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Controladores.SalaJpaController;
import Controladores.ColeccionJpaController;
import Persistencia.Coleccion;
import Persistencia.Museo;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableRow;
import javafx.scene.layout.Pane;

/**
 *
 * @author Axton Urbina
 */
public class InterfazController {
    Sala sala;
    boolean guardado;
    SalaJpaController salaContr;
    int cont;
    String bandera;
    boolean guardarEditando;
    boolean guardarInsertando;
    Coleccion coleccion;
    ColeccionJpaController ctrl;
    
    public InterfazController() {
        guardado = false;
        salaContr = new SalaJpaController();
        cont = 1;
        bandera = null;
        guardarEditando = false;
        guardarInsertando = false;
    }
    
    //SETTERS Y GETTERS
    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public boolean isGuardarEditando() {
        return guardarEditando;
    }

    public void setGuardarEditando(boolean guardarEditando) {
        this.guardarEditando = guardarEditando;
    }

    public boolean isGuardarInsertando() {
        return guardarInsertando;
    }

    public void setGuardarInsertando(boolean guardarInsertando) {
        this.guardarInsertando = guardarInsertando;
    }
    
    //SALA
    
    //Método para seleccionar una fila (tipo Sala)
    public void mouseClick(TableView tabla) {
        tabla.setRowFactory(tv -> {
            TableRow<Sala> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    sala = row.getItem();
                    System.out.println(sala.getId());
                }
            });
            return row;
        });
    }
    
    public void cargarSalaDatos(TableView tabla, SalaJpaController sala) {
        tabla.getColumns().clear();
        
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("id"));
        
        TableColumn<Sala, Integer> idMuseo = new TableColumn<>("ID Museo");
        idMuseo.setCellValueFactory(cellData -> {
            Museo museo = cellData.getValue().getIdMuseo();
            return new SimpleIntegerProperty(museo != null ? museo.getId() : 0).asObject();
        });
        
        TableColumn nombre = new TableColumn("Nombre");
        nombre.setCellValueFactory(new PropertyValueFactory<Sala, String>("nombreSala"));
        
        TableColumn descripcion = new TableColumn("Descripcion");
        descripcion.setCellValueFactory(new PropertyValueFactory<Sala, String>("descripcion"));
        
        tabla.getColumns().addAll(id, idMuseo, nombre, descripcion);
        
        Collection salas = sala.findSalaEntities();
        ObservableList<Sala> salaJFX = FXCollections.observableArrayList(salas);
            
        tabla.setItems(salaJFX);  
    }
    
    public void presionarSalaBtn(Button btn, TableView tabla, SalaJpaController sala) {
        btn.setOnAction(event-> {
            cargarSalaDatos(tabla, sala);
        });
    }
    
    public void InsertarSala(TextField infoTxt, Button guardarBtn, Label infoLbl, Button insertarBtn, TextField filt1, ComboBox filt2, Button filtBtn, Button elim, Button edit) {
        infoTxt.setVisible(false);
        infoLbl.setVisible(false);
        guardarBtn.setVisible(false);
        insertarBtn.setOnAction(event -> {
            if (!"INSERTANDO".equals(getBandera())) {
                setBandera("INSERTANDO");
                setCont(1);
                sala = new Sala();

                filt1.setDisable(true);
                filt2.setDisable(true);
                filtBtn.setDisable(true);
                elim.setDisable(true);
                edit.setDisable(true);
                insertarBtn.setDisable(true);

                infoTxt.setVisible(true);
                infoLbl.setVisible(true);
                guardarBtn.setVisible(true);

                Museo museo = new Museo();
                museo.setId(1);
                sala.setIdMuseo(museo);

                guardarBtn.setDisable(true);

                if (!isGuardarInsertando()) {
                    infoTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                        guardarBtn.setDisable(newValue.trim().isEmpty());
                    });

                    infoLbl.setText("Ingrese el nombre de la sala");

                    guardarBtn.setOnAction(e -> {
                        if (infoTxt.getText().isEmpty()) return;

                        if (getCont() == 1) {
                            sala.setNombreSala(infoTxt.getText());
                            infoTxt.setText("");
                            setCont(2);
                            infoLbl.setText("Ingrese la descripción de la sala");
                        } else if (getCont() == 2) {
                            sala.setDescripcion(infoTxt.getText());
                            infoTxt.setText("");
                            setGuardado(true);
                            insertarBtn.setDisable(false);
                            infoLbl.setText("Información guardada, presione Insertar para guardar la sala");
                        }
                    });
                    setGuardarInsertando(true);
                }
            } else if ("INSERTANDO".equals(getBandera()) && isGuardado()) {
                salaContr.create(sala);
                setGuardado(false);
                setCont(1);
                sala = new Sala();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información se ha insertado con éxito");
                alert.showAndWait();

                infoLbl.setText("Ingrese el nombre de la sala");

                filt1.setDisable(false);
                filt2.setDisable(false);
                filtBtn.setDisable(false);
                elim.setDisable(false);
                edit.setDisable(false);
                insertarBtn.setDisable(false);

                infoTxt.setVisible(false);
                infoLbl.setVisible(false);
                guardarBtn.setVisible(false);

                infoTxt.setText("");

                setBandera(null);
                setGuardarInsertando(false);
            }
        });
    }
    
    public void editarSala(Button editar, Button filt, Button eliminar, Button guardar, Button insertar, TextField filt1, ComboBox filt2, TextField txtDatos, Label lblDatos) {
        editar.setOnAction(event -> {
            if (!"EDITANDO".equals(getBandera())) {
                if (sala != null) {
                    filt.setDisable(true);
                    eliminar.setDisable(true);
                    insertar.setDisable(true);
                    filt1.setDisable(true);
                    filt2.setDisable(true);

                    txtDatos.setVisible(true);
                    lblDatos.setVisible(true);
                    guardar.setVisible(true);
                    guardar.setDisable(true);

                    setBandera("EDITANDO");

                    if (getCont() == 1) {
                        lblDatos.setText("Ingrese el nombre de la sala");
                    } else if (getCont() == 2) {
                        lblDatos.setText("Ingrese la descripción de la sala");
                    }

                    if (!isGuardarEditando()) {
                        txtDatos.textProperty().addListener((observable, oldValue, newValue) -> {
                            guardar.setDisable(newValue.trim().isEmpty());
                        });

                        guardar.setOnAction(ev -> {
                            if (getCont() == 1) {
                                sala.setNombreSala(txtDatos.getText());
                                txtDatos.setText("");
                                setCont(2);
                                lblDatos.setText("Ingrese la descripción de la sala");
                            } else if (getCont() == 2) {
                                sala.setDescripcion(txtDatos.getText());
                                txtDatos.setText("");
                                setGuardado(true);
                                editar.setDisable(false);
                                lblDatos.setText("Información guardada, presione nuevamente Editar para guardar la sala");
                            }
                        });
                        setGuardarEditando(true);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cuidado");
                    alert.setContentText("No has seleccionado ninguna fila para editar aún");
                    alert.showAndWait();
                }
            } else if ("EDITANDO".equals(getBandera()) && isGuardado()) {
                salaContr.edit(sala);
                setGuardado(false);
                setCont(1);
                sala = null;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información de la sala se ha actualizado con éxito");
                alert.showAndWait();

                filt.setDisable(false);
                eliminar.setDisable(false);
                insertar.setDisable(false);
                filt1.setDisable(false);
                filt2.setDisable(false);
                editar.setDisable(false);

                txtDatos.setVisible(false);
                lblDatos.setVisible(false);
                guardar.setVisible(false);
                txtDatos.setText("");

                setBandera(null);
                setGuardarEditando(false);
            }
    });

    txtDatos.setVisible(false);
    lblDatos.setVisible(false);
    guardar.setVisible(false);
    }
    
    public void eliminarSala(Button eliminarBtn) {
        eliminarBtn.setOnAction(event-> {
            if(sala != null) {
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Solicitud de confirmación");
            mensaje.setContentText("Estas seguro de que deseas eliminar la información de la sala: "
                    + sala.getId() + " " + sala.getNombreSala());
        
            ButtonType si = new ButtonType("SI");
            ButtonType no = new ButtonType("NO");
            
            mensaje.getButtonTypes().setAll(si, no);
            
            Optional<ButtonType> resultado = mensaje.showAndWait();
            
            if(resultado.isPresent()) {
                if(resultado.get() == si) {
                    salaContr.delete(sala);
                    
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("EXITO");
                    alert.setContentText("Usuario eliminado EXITADAMENTE");
                    alert.showAndWait();
                } 
            } 
        }
        });
    }
    
    //COLECCIÓN
    
    public void cargarColeccionDatos(TableView tabla, ColeccionJpaController colecCtrl) {
        tabla.getColumns().clear();
        
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<Coleccion, Integer>("id"));
        
        TableColumn<Coleccion, Integer> idSala = new TableColumn<>("ID Sala");
        idSala.setCellValueFactory(cellData -> {
            Sala sala = cellData.getValue().getIdSala();
            return new SimpleIntegerProperty(sala != null ? sala.getId() : 0).asObject();
        });
        
        TableColumn nombre = new TableColumn("Nombre ");
        nombre.setCellValueFactory(new PropertyValueFactory<Coleccion, String>("nombreColeccion"));
        
        TableColumn siglo = new TableColumn("Siglo");
        siglo.setCellValueFactory(new PropertyValueFactory<Coleccion, String>("siglo"));
        
        TableColumn descripcion = new TableColumn("Descripcion");
        descripcion.setCellValueFactory(new PropertyValueFactory<Coleccion, String>("descripcionColeccion"));
        
        tabla.getColumns().addAll(id, idSala, nombre, siglo, descripcion);
        
        Collection colec = colecCtrl.findColeccionEntities();
        ObservableList<Coleccion> coleccionJFX = FXCollections.observableArrayList(colec);
            
        tabla.setItems(coleccionJFX);  
    }
    
    public void presionarColeccionBtn(Button btn, TableView tabla, ColeccionJpaController ctrl) {
        btn.setOnAction(event-> {
            cargarColeccionDatos(tabla, ctrl);
        });
    }
    
    public void probar(Pane pane) {
        for (Node nodo : pane.getChildren()) {
            nodo.setVisible(false);
        }
    }
}
