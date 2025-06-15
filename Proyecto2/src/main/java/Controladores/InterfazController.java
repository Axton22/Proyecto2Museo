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
import Persistencia.ComisionTarjetas;
import Persistencia.Especies;
import Persistencia.Museo;
import Persistencia.Precio;
import Persistencia.Tematica;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.layout.Pane;

/**
 *
 * @author Axton Urbina
 */
public class InterfazController {
    private Sala sala;
    private SalaJpaController salaCtrl;
    private int cont;
    private String bandera;
    private Coleccion coleccion;
    private ColeccionJpaController colecCtrl;
    private Especies especies;
    private EspeciesJpaController especiesCtrl;
    private Tematica tematica;
    private TematicaJpaController tematicaCtrl;
    private Precio precio;
    private PrecioJpaController precioCtrl;
    private ComisionTarjetas comision;
    private ComisiontarjetasJpaController comisionCtrl;
    
            
    
    public InterfazController() {
        salaCtrl = new SalaJpaController();
        colecCtrl = new ColeccionJpaController();
        especiesCtrl = new EspeciesJpaController();
        cont = 1;
        bandera = null;
        tematicaCtrl = new TematicaJpaController();
        precioCtrl = new PrecioJpaController();
        comisionCtrl = new ComisiontarjetasJpaController();
    }
    
    //SETTERS Y GETTERS
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
    
    public void manejoInterfaz(Button salaBtn,Button coleccionBtn, Button especiesBtn, Button tematicaBtn, Button precioBtn, Button comisionBtn, TableView<Object> tabla, TextField datosTF,
            Button guardarBtn,Label datosLb, Button insertarBtn, TextField filtroTF, ComboBox filtroCB, Button filtBtn, Button eliminarBtn, 
            Button editarBtn,  ComboBox<Object> salasCb, Tab tab1, TabPane tpContenidos) {
    
        salaBtn.setOnAction(event -> {
            tpContenidos.getSelectionModel().select(tab1);
            sala = null;
            coleccion = null;
            especies = null;
            tematica = null;
            precio = null;
            comision = null;

            cargarSalaDatos(tabla);

            tabla.refresh();


            tabla.setRowFactory(tv -> {
                TableRow<Object> row = new TableRow<>();
                row.setOnMouseClicked(ev -> {
                    if (ev.getClickCount() == 2 && !row.isEmpty()) {
                        Object item = row.getItem();
                        if (item instanceof Sala) {
                            sala = (Sala) item;
                            System.out.println("Sala seleccionada con ID: " + sala.getId());
                        }
                    }
                });
                return row;
            });

            InsertarSala(datosTF, guardarBtn, datosLb, insertarBtn, filtroTF, filtroCB, filtBtn, 
                    eliminarBtn, editarBtn);

            editarSala(editarBtn, filtBtn, eliminarBtn, guardarBtn, insertarBtn, filtroTF, filtroCB, datosTF, datosLb);

            eliminarSala(eliminarBtn);
        });
        
        coleccionBtn.setOnAction(event -> {
            tpContenidos.getSelectionModel().select(tab1);
            sala = null;
            coleccion = null;
            especies = null;
            tematica = null;
            precio = null;
            comision = null;

            cargarColeccionDatos(tabla);

            tabla.refresh();

            tabla.setRowFactory(tv -> {
                TableRow<Object> row = new TableRow<>();
                row.setOnMouseClicked(ev -> {
                    if (ev.getClickCount() == 2 && !row.isEmpty()) {
                        Object item = row.getItem();
                        if (item instanceof Coleccion) {
                            coleccion = (Coleccion) item;
                            System.out.println("Coleccion seleccionada con ID: " + coleccion.getId());
                        }
                    }
                });
                return row;
            });
            
        insertarColeccion(datosTF, guardarBtn, datosLb, insertarBtn, filtroTF, filtroCB, filtBtn, eliminarBtn, editarBtn, salasCb);
        
        editarColeccion(editarBtn, filtBtn, eliminarBtn, guardarBtn, insertarBtn, filtroTF, filtroCB, datosTF, datosLb, salasCb);
        
        eliminarColeccion(eliminarBtn);
        });
        
        especiesBtn.setOnAction(event -> {
            tpContenidos.getSelectionModel().select(tab1);
            sala = null;
            coleccion = null;
            especies = null;
            tematica = null;
            precio = null;
            comision = null;

            cargarEspeciesDatos(tabla);

            tabla.refresh();


            tabla.setRowFactory(tv -> {
                TableRow<Object> row = new TableRow<>();
                row.setOnMouseClicked(ev -> {
                    if (ev.getClickCount() == 2 && !row.isEmpty()) {
                        Object item = row.getItem();
                        if (item instanceof Especies) {
                            especies = (Especies) item;
                            System.out.println("Especie seleccionada con ID: " + especies.getId());
                        }
                    }
                });
                return row;
            });

            insertarEspecies(datosTF, guardarBtn, datosLb, insertarBtn, filtroTF, filtroCB, filtBtn, eliminarBtn, editarBtn, salasCb);
            
            editarEspecies(editarBtn, filtBtn, eliminarBtn, guardarBtn, insertarBtn, filtroTF, filtroCB, datosTF, datosLb, salasCb);
            
            eliminarEspecies(eliminarBtn);
        });
        
        tematicaBtn.setOnAction(event -> {
            tpContenidos.getSelectionModel().select(tab1);
            sala = null;
            coleccion = null;
            especies = null;
            tematica = null;
            precio = null;
            comision = null;

            cargarTematicaDatos(tabla);

            tabla.refresh();


            tabla.setRowFactory(tv -> {
                TableRow<Object> row = new TableRow<>();
                row.setOnMouseClicked(ev -> {
                    if (ev.getClickCount() == 2 && !row.isEmpty()) {
                        Object item = row.getItem();
                        if (item instanceof Tematica) {
                            tematica = (Tematica) item;
                            System.out.println("Tematica seleccionada con ID: " + tematica.getId());
                        }
                    }
                });
                return row;
            });

            insertarTematica(datosTF, guardarBtn, datosLb, insertarBtn, filtroTF, filtroCB, filtBtn, eliminarBtn, editarBtn, salasCb);
            
            editarTematica(editarBtn, filtBtn, eliminarBtn, guardarBtn, insertarBtn, filtroTF, filtroCB, datosTF, datosLb, salasCb);
            
            eliminarTematica(eliminarBtn);
 
        });
        
        precioBtn.setOnAction(event -> {
            tpContenidos.getSelectionModel().select(tab1);
            sala = null;
            coleccion = null;
            especies = null;
            tematica = null;
            precio = null;
            comision = null;

            cargarPrecioDatos(tabla);

            tabla.refresh();


            tabla.setRowFactory(tv -> {
                tpContenidos.getSelectionModel().select(tab1);
                TableRow<Object> row = new TableRow<>();
                row.setOnMouseClicked(ev -> {
                    if (ev.getClickCount() == 2 && !row.isEmpty()) {
                        Object item = row.getItem();
                            if (item instanceof Precio) {
                            precio = (Precio) item;
                            System.out.println("Precio seleccionado con ID: " + precio.getId());
                        }
                    }
                });
                return row;
            });

            InsertarPrecio(datosTF, guardarBtn, datosLb, insertarBtn, filtroTF, filtroCB, filtBtn, eliminarBtn, editarBtn, salasCb);

            editarPerecio(editarBtn, filtBtn, eliminarBtn, guardarBtn, insertarBtn, filtroTF, filtroCB, datosTF, datosLb, salasCb);
            
            eliminarPrecio(eliminarBtn);
        });
        
        comisionBtn.setOnAction(event -> {
            tpContenidos.getSelectionModel().select(tab1);
            sala = null;
            coleccion = null;
            especies = null;
            tematica = null;
            precio = null;
            comision = null;

            cargarComisionDatos(tabla);

            tabla.refresh();


            tabla.setRowFactory(tv -> {
                TableRow<Object> row = new TableRow<>();
                row.setOnMouseClicked(ev -> {
                    if (ev.getClickCount() == 2 && !row.isEmpty()) {
                        Object item = row.getItem();
                            if (item instanceof ComisionTarjetas) {
                            comision = (ComisionTarjetas) item;
                            System.out.println("Comision seleccionada con tipo de tarjeta: " + comision.getTipoTarjeta());
                        }
                    }
                });
                return row;
            });

            InsertarComision(datosTF, guardarBtn, datosLb, insertarBtn, filtroTF, filtroCB, filtBtn, eliminarBtn, editarBtn);
            
            editarComision(editarBtn, filtBtn, eliminarBtn, guardarBtn, insertarBtn, filtroTF, filtroCB, datosTF, datosLb);
            
            eliminarComision(eliminarBtn);
        });
    }
    
    
    
    //Sala 
    public void cargarSalaDatos(TableView tabla) {
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
        
        Collection salas = salaCtrl.findSalaEntities();
        ObservableList<Object> salasJFX = FXCollections.observableArrayList(salas);
 
        tabla.setItems(salasJFX);  
    }
    
    public void InsertarSala(TextField infoTxt, Button guardarBtn, Label infoLbl, Button insertarBtn, TextField filt1, 
            ComboBox filt2, Button filtBtn, Button elim, Button edit) {
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


                guardarBtn.setDisable(true);
                
                Museo museo = new Museo();
                museo.setId(1);
                sala.setIdMuseo(museo);

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
                        infoLbl.setText("Información digitada, presione insertar nuevamente para guardar la información");
                        insertarBtn.setDisable(false);
                    }
                });
            } else if ("INSERTANDO".equals(getBandera())) {
                salaCtrl.create(sala);
                setCont(1);
                sala = new Sala();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información se ha insertado con éxito");
                alert.showAndWait();


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
            }
        });
    }
    
    public void editarSala(Button editarBtn, Button filtBtn, Button eliminarBtn, Button guardarBtn, Button insertarBtn, 
            TextField filtTf, ComboBox filtCb, TextField txtDatos, Label lblDatos) {
        editarBtn.setOnAction(event -> {
            if (!"EDITANDO".equals(getBandera())) {
                if (sala != null) {
                    filtBtn.setDisable(true);
                    eliminarBtn.setDisable(true);
                    insertarBtn.setDisable(true);
                    filtTf.setDisable(true);
                    filtCb.setDisable(true);
                    editarBtn.setDisable(true);

                    txtDatos.setVisible(true);
                    lblDatos.setVisible(true);
                    guardarBtn.setVisible(true);
                    guardarBtn.setDisable(true);

                    setBandera("EDITANDO");

                    txtDatos.textProperty().addListener((observable, oldValue, newValue) -> {
                        guardarBtn.setDisable(newValue.trim().isEmpty());
                    });

                    lblDatos.setText("Ingrese el nombre de la sala");
                    guardarBtn.setOnAction(ev -> {
                        if (getCont() == 1) {
                            sala.setNombreSala(txtDatos.getText());
                            txtDatos.setText("");
                            setCont(2);
                            lblDatos.setText("Ingrese la descripción de la sala");
                        } else if (getCont() == 2) {
                            sala.setDescripcion(txtDatos.getText());
                            txtDatos.setText("");
                            editarBtn.setDisable(false);
                            lblDatos.setText("Información guardada, presione nuevamente Editar para guardar la sala");
                        }
                        });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cuidado");
                    alert.setContentText("No has seleccionado ninguna fila para editar aún");
                    alert.showAndWait();
                }
            } else if ("EDITANDO".equals(getBandera())) {
                salaCtrl.edit(sala);
                setCont(1);
                sala = null;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información de la sala se ha actualizado con éxito");
                alert.showAndWait();

                filtBtn.setDisable(false);
                eliminarBtn.setDisable(false);
                insertarBtn.setDisable(false);
                filtTf.setDisable(false);
                filtCb.setDisable(false);
                editarBtn.setDisable(false);

                txtDatos.setVisible(false);
                lblDatos.setVisible(false);
                guardarBtn.setVisible(false);
                txtDatos.setText("");

                setBandera(null);
            }
        });
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
                        salaCtrl.delete(sala);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("EXITO");
                        alert.setContentText("Usuario eliminado exitosamente");
                        alert.showAndWait();
                    } 
                } 
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Precaución");
                alert.setContentText("No hay ninguna sala seleccionada");
                alert.showAndWait();
            }
        });
    }
    
    //COLECCIÓN
  
    
    public void cargarColeccionDatos(TableView tabla) {
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
        ObservableList<Object> coleccionJFX = FXCollections.observableArrayList(colec);
            
        tabla.setItems(coleccionJFX);  
    }
    
    public void insertarColeccion(TextField infoTxt, Button guardarBtn, Label infoLbl, Button insertarBtn, TextField filt1, 
            ComboBox filt2, Button filtBtn, Button elim, Button edit, ComboBox<Object> salasCb) {
        insertarBtn.setOnAction(event -> {
            if (!"INSERTANDO".equals(getBandera())) {
                setBandera("INSERTANDO");
                setCont(1);
                coleccion = new Coleccion();

                filt1.setDisable(true);
                filt2.setDisable(true);
                filtBtn.setDisable(true);
                elim.setDisable(true);
                edit.setDisable(true);
                insertarBtn.setDisable(true);

                infoTxt.setVisible(true);
                infoLbl.setVisible(true);
                guardarBtn.setVisible(true);

                guardarBtn.setDisable(true);

                infoTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                    guardarBtn.setDisable(newValue.trim().isEmpty());
                });

                infoLbl.setText("Ingrese el nombre de la coleccion");

                guardarBtn.setOnAction(e -> {
                    if (getCont() == 1) {
                    coleccion.setNombreColeccion(infoTxt.getText());
                    infoTxt.setText("");
                    setCont(2);
                    infoLbl.setText("Seleccione la sala a la que pertenece la coleccion");

                    Collection<Sala> salasActualizadas = salaCtrl.findSalaEntities();
                    salasCb.setItems(FXCollections.observableArrayList(salasActualizadas));
                    salasCb.setVisible(true);
                    
                    infoTxt.setVisible(false);
                    guardarBtn.setDisable(true);

                    salasCb.valueProperty().addListener((obs, oldVal, newVal) -> {
                        guardarBtn.setDisable(!(newVal instanceof Sala));
                    });

                } else if (getCont() == 2) {
                    Object salaSeleccionada = salasCb.getValue();

                    if (salaSeleccionada instanceof Sala) {
                        Sala sala = (Sala) salaSeleccionada;
                        coleccion.setIdSala(sala);
                        salasCb.setVisible(false);
                        setCont(3);
                        infoLbl.setText("Ingrese el siglo de la colección");

                        infoTxt.setVisible(true);
                        infoTxt.setText("");
                        guardarBtn.setDisable(true);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Advertencia");
                        alert.setContentText("Debe seleccionar una sala válida.");
                        alert.showAndWait();
                    }

                } else if (getCont() == 3) {
                    coleccion.setSiglo(infoTxt.getText());
                    infoTxt.setText("");
                    setCont(4);
                    infoLbl.setText("Ingrese la descripción de la colección");

                } else if (getCont() == 4) {
                    coleccion.setDescripcionColeccion(infoTxt.getText());
                    infoLbl.setText("Información digitada, presione insertar nuevamente para guardar la información");
                    guardarBtn.setDisable(true); 
                    insertarBtn.setDisable(false);
                }
                });
            } else if ("INSERTANDO".equals(getBandera())) {
                colecCtrl.create(coleccion);
                setCont(1);
                coleccion = new Coleccion();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información se ha insertado con éxito");
                alert.showAndWait();


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
            }
        });
    }
    
    public void editarColeccion(Button editarBtn, Button filtBtn, Button eliminarBtn, Button guardarBtn, Button insertarBtn, 
            TextField filtTf, ComboBox filtCb, TextField txtDatos, Label lblDatos, ComboBox salasCb) {
        editarBtn.setOnAction(event -> {
            if (!"EDITANDO".equals(getBandera())) {
                if (coleccion != null) {
                    filtBtn.setDisable(true);
                    eliminarBtn.setDisable(true);
                    insertarBtn.setDisable(true);
                    filtTf.setDisable(true);
                    filtCb.setDisable(true);
                    editarBtn.setDisable(true);

                    txtDatos.setVisible(true);
                    lblDatos.setVisible(true);
                    guardarBtn.setVisible(true);
                    guardarBtn.setDisable(true);

                    setBandera("EDITANDO");

                    txtDatos.textProperty().addListener((observable, oldValue, newValue) -> {
                        guardarBtn.setDisable(newValue.trim().isEmpty());
                    });

                    lblDatos.setText("Ingrese el nombre de la colección");
                    guardarBtn.setOnAction(ev -> {
                        if (getCont() == 1) {
                            coleccion.setNombreColeccion(txtDatos.getText());
                            txtDatos.setText("");
                            setCont(2);
                            lblDatos.setText("Seleccione la sala a la que pertenece la colección");
                            
                            Collection<Sala> salasActualizadas = salaCtrl.findSalaEntities();
                            salasCb.setItems(FXCollections.observableArrayList(salasActualizadas));
                            salasCb.setVisible(true);

                            txtDatos.setVisible(false);
                            guardarBtn.setDisable(true);

                            salasCb.valueProperty().addListener((obs, oldVal, newVal) -> {
                                guardarBtn.setDisable(!(newVal instanceof Sala));
                            });
                        } else if (getCont() == 2) {
                            Object salaSeleccionada = salasCb.getValue();

                            if (salaSeleccionada instanceof Sala) {
                                Sala sala = (Sala) salaSeleccionada;
                                coleccion.setIdSala(sala);
                                salasCb.setVisible(false);
                                setCont(3);
                                lblDatos.setText("Ingrese el siglo de la colección");

                                txtDatos.setVisible(true);
                                txtDatos.setText("");
                                guardarBtn.setDisable(true);

                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Advertencia");
                                alert.setContentText("Debe seleccionar una sala válida.");
                                alert.showAndWait();
                            }     
                        } else if (getCont() == 3) {
                            coleccion.setSiglo(txtDatos.getText());
                            txtDatos.setText("");
                            setCont(4);
                            lblDatos.setText("Ingrese la descripción de la colección");

                        } else if (getCont() == 4) {
                            coleccion.setDescripcionColeccion(txtDatos.getText());
                            lblDatos.setText("Información digitada, presione editar nuevamente para guardar la información");
                            guardarBtn.setDisable(true); 
                            editarBtn.setDisable(false);
                        }
                        });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cuidado");
                    alert.setContentText("No has seleccionado ninguna fila para editar aún");
                    alert.showAndWait();
                }
            } else if ("EDITANDO".equals(getBandera())) {
                colecCtrl.edit(coleccion);
                setCont(1);
                coleccion = null;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información de la colección se ha actualizado con éxito");
                alert.showAndWait();

                filtBtn.setDisable(false);
                eliminarBtn.setDisable(false);
                insertarBtn.setDisable(false);
                filtTf.setDisable(false);
                filtCb.setDisable(false);
                editarBtn.setDisable(false);

                txtDatos.setVisible(false);
                lblDatos.setVisible(false);
                guardarBtn.setVisible(false);
                txtDatos.setText("");

                setBandera(null);
            }
        });
    }
    
    public void eliminarColeccion (Button eliminarBtn) {
        eliminarBtn.setOnAction(event-> {
            if(coleccion != null) {
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Solicitud de confirmación");
                mensaje.setContentText("Estas seguro de que deseas eliminar la información de la Coleccion: "
                        + coleccion .getId() + " " + coleccion.getNombreColeccion());

                ButtonType si = new ButtonType("SI");
                ButtonType no = new ButtonType("NO");

                mensaje.getButtonTypes().setAll(si, no);

                Optional<ButtonType> resultado = mensaje.showAndWait();

                if(resultado.isPresent()) {
                    if(resultado.get() == si) {
                        colecCtrl.delete(coleccion);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("EXITO");
                        alert.setContentText("Usuario eliminado exitosamente");
                        alert.showAndWait();
                    } 
                } 
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Precaución");
                alert.setContentText("No hay ninguna sala seleccionada");
                alert.showAndWait();
            }
        });
    }
    
    //ESPECIES
    
    public void cargarEspeciesDatos(TableView tabla) {
        tabla.getColumns().clear();
        
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<Especies, Integer>("id"));
        
        TableColumn<Especies, Integer> idColeccion = new TableColumn<>("ID Coleccion");
        idColeccion.setCellValueFactory(cellData -> {
            Coleccion colecicon = cellData.getValue().getIdColeccion();
            return new SimpleIntegerProperty(colecicon != null ? colecicon.getId() : 0).asObject();
        });
        
        TableColumn nombreCientifico = new TableColumn("Nombre Científico ");
        nombreCientifico.setCellValueFactory(new PropertyValueFactory<Especies, String>("nombreCientifico"));
        
        TableColumn nombreComun = new TableColumn("Nombre Común");
        nombreComun.setCellValueFactory(new PropertyValueFactory<Especies, String>("nombreComun"));
        
         TableColumn fechaExtincion = new TableColumn("Fecha Extincion");
         fechaExtincion.setCellValueFactory(new PropertyValueFactory<Especies, Date>("fechaExtincion"));

        
        fechaExtincion.setCellFactory(column -> {
            return new TableCell<Especies, Date>() {
                private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                protected void updateItem(Date fecha, boolean empty) {
                    super.updateItem(fecha, empty);
                    if (empty || fecha == null) {
                        setText(null);
                    } else {
                        setText(sdf.format(fecha));
                    }
                }
            };
        });
        
        TableColumn epocaVivio = new TableColumn("Epoca en la que vivió");
        epocaVivio.setCellValueFactory(new PropertyValueFactory<Especies, String>("epocaVivio"));
        
        TableColumn peso = new TableColumn("Peso");
        peso.setCellValueFactory(new PropertyValueFactory<Especies, BigDecimal>("peso"));
        
        TableColumn tamanio = new TableColumn("Tamaño");
        tamanio.setCellValueFactory(new PropertyValueFactory<Especies, BigDecimal>("tamanio"));
        
        TableColumn caracteristicas = new TableColumn("Caracteristicas");
        caracteristicas.setCellValueFactory(new PropertyValueFactory<Especies, String>("caracteristicas"));
        
        
        
        tabla.getColumns().addAll(id, idColeccion, nombreCientifico, nombreComun, fechaExtincion, epocaVivio, peso, tamanio, caracteristicas);
        
        Collection especies = especiesCtrl.findEspeciesEntities();
        ObservableList<Object> especiesJFX = FXCollections.observableArrayList(especies);
            
        tabla.setItems(especiesJFX);  
    }
    
    
    public void insertarEspecies(TextField infoTxt, Button guardarBtn, Label infoLbl, Button insertarBtn, TextField filt1, 
            ComboBox filt2, Button filtBtn, Button elim, Button edit, ComboBox<Object> coleccionCB) {
        insertarBtn.setOnAction(event -> {
            if (!"INSERTANDO".equals(getBandera())) {
                setBandera("INSERTANDO");
                setCont(1);
                especies = new Especies();

                filt1.setDisable(true);
                filt2.setDisable(true);
                filtBtn.setDisable(true);
                elim.setDisable(true);
                edit.setDisable(true);
                insertarBtn.setDisable(true);

                infoTxt.setVisible(true);
                infoLbl.setVisible(true);
                guardarBtn.setVisible(true);

                guardarBtn.setDisable(true);

                infoTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                    guardarBtn.setDisable(newValue.trim().isEmpty());
                });

                infoLbl.setText("Ingrese el nombre (común) de la Especie");

                guardarBtn.setOnAction(e -> {
                    if (getCont() == 1) {
                    especies.setNombreComun(infoTxt.getText());
                    infoTxt.setText("");
                    setCont(2);
                    infoLbl.setText("Seleccione la sala a la que pertenece la especie");

                    Collection<Coleccion> coleccionesActualizadas = colecCtrl.findColeccionEntities();
                    coleccionCB.setItems(FXCollections.observableArrayList(coleccionesActualizadas));
                    coleccionCB.setVisible(true);
                    
                    infoTxt.setVisible(false);
                    guardarBtn.setDisable(true);

                    coleccionCB.valueProperty().addListener((obs, oldVal, newVal) -> {
                        guardarBtn.setDisable(!(newVal instanceof Coleccion));
                    });

                    } else if (getCont() == 2) {
                        Object coleccionSeleccionada = coleccionCB.getValue();

                    if (coleccionSeleccionada instanceof Coleccion) {
                        Coleccion coleccion = (Coleccion) coleccionSeleccionada;
                        especies.setIdColeccion(coleccion);
                        coleccionCB.setVisible(false);
                        setCont(3);
                        infoLbl.setText("Ingrese el nombre científico de la especie");

                        infoTxt.setVisible(true);
                        infoTxt.setText("");
                        guardarBtn.setDisable(true);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Advertencia");
                        alert.setContentText("Debe seleccionar una sala válida.");
                        alert.showAndWait();
                    }

                    } else if (getCont() == 3) {
                        especies.setNombreCientifico(infoTxt.getText());
                        infoTxt.setText("");
                        setCont(4);
                        infoLbl.setText("Ingrese la fecha de extinción de la especie (ejemplo: Día/Mes/Año)");

                    } else if (getCont() == 4) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            String fechaTexto = infoTxt.getText();
                            Date fechaConvertida = sdf.parse(fechaTexto);
                            especies.setFechaExtincion(fechaConvertida);
                            infoTxt.setText("");
                            setCont(5);
                            infoLbl.setText("Ingrese la época en que vivió la especie");
                            
                        } catch (Exception er) {
                            System.err.println("Error al parsear la fecha: " + er.getMessage()); 

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error de Formato de Fecha");
                            alert.setHeaderText("Formato de fecha inválido");
                            alert.setContentText("Por favor, ingrese la fecha en el formato correcto (ej. dd/MM/yyyy).");
                            alert.showAndWait();
                        }
                    } else if (getCont() == 5) {
                        especies.setEpocaVivio(infoTxt.getText());
                        infoTxt.setText("");
                        setCont(6);
                        infoLbl.setText("Ingrese el peso de la especie");
                        
                    } else if(getCont() == 6) {
                        String convertirString = infoTxt.getText();
                        BigDecimal peso = new BigDecimal(convertirString);
                        especies.setPeso(peso);
                        infoTxt.setText("");
                        setCont(7);
                        infoLbl.setText("Ingrese el tamaño de la especie");
                    
                    } else if (getCont() == 7) {
                        String convertirString = infoTxt.getText();
                        BigDecimal tamanio = new BigDecimal(convertirString);
                        especies.setTamanio(tamanio);
                        infoTxt.setText("");
                        setCont(8);
                        infoLbl.setText("Ingrese las caracteristicas de la especie");
                        
                    } else if (getCont() == 8) {
                        especies.setCaracteristicas(infoTxt.getText());
                        infoTxt.setText("");
                        guardarBtn.setDisable(false);
                        insertarBtn.setDisable(false);
                        infoLbl.setText("Información digitada, presione insertar nuevamente para guardar la información");
                    }
                });
            } else if ("INSERTANDO".equals(getBandera())) {
                especiesCtrl.create(especies);
                setCont(1);
                especies = new Especies();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información se ha insertado con éxito");
                alert.showAndWait();


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
            }
        });
    }
    
    public void editarEspecies(Button editarBtn, Button filtBtn, Button eliminarBtn, Button guardarBtn, Button insertarBtn, 
            TextField filtTf, ComboBox filtCb, TextField txtDatos, Label lblDatos, ComboBox coleccionCB) {
        editarBtn.setOnAction(event -> {
            if (!"EDITANDO".equals(getBandera())) {
                if (especies != null) {
                    filtBtn.setDisable(true);
                    eliminarBtn.setDisable(true);
                    insertarBtn.setDisable(true);
                    filtTf.setDisable(true);
                    filtCb.setDisable(true);
                    editarBtn.setDisable(true);

                    txtDatos.setVisible(true);
                    lblDatos.setVisible(true);
                    guardarBtn.setVisible(true);
                    guardarBtn.setDisable(true);

                    setBandera("EDITANDO");

                    txtDatos.textProperty().addListener((observable, oldValue, newValue) -> {
                        guardarBtn.setDisable(newValue.trim().isEmpty());
                    });

                    lblDatos.setText("Ingrese el nombre de la Especie");
                    guardarBtn.setOnAction(ev -> {
                        if (getCont() == 1) {
                            especies.setNombreComun(txtDatos.getText());
                            txtDatos.setText("");
                            setCont(2);
                            lblDatos.setText("Seleccione la sala a la que pertenece la especie");

                            Collection<Coleccion> coleccionesActualizadas = colecCtrl.findColeccionEntities();
                            coleccionCB.setItems(FXCollections.observableArrayList(coleccionesActualizadas));
                            coleccionCB.setVisible(true);

                            txtDatos.setVisible(false);
                            guardarBtn.setDisable(true);

                            coleccionCB.valueProperty().addListener((obs, oldVal, newVal) -> {
                                guardarBtn.setDisable(!(newVal instanceof Coleccion));
                            });

                            } else if (getCont() == 2) {
                                Object coleccionSeleccionada = coleccionCB.getValue();

                            if (coleccionSeleccionada instanceof Coleccion) {
                                Coleccion coleccion = (Coleccion) coleccionSeleccionada;
                                especies.setIdColeccion(coleccion);
                                coleccionCB.setVisible(false);
                                setCont(3);
                                lblDatos.setText("Ingrese el nombre científico de la especie");

                                txtDatos.setVisible(true);
                                txtDatos.setText("");
                                guardarBtn.setDisable(true);

                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Advertencia");
                                alert.setContentText("Debe seleccionar una sala válida.");
                                alert.showAndWait();
                            }

                            } else if (getCont() == 3) {
                                especies.setNombreCientifico(txtDatos.getText());
                                txtDatos.setText("");
                                setCont(4);
                                lblDatos.setText("Ingrese la fecha de extinción de la especie (ejemplo: Día/Mes/Año)");

                            } else if (getCont() == 4) {
                                try {
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                    String fechaTexto = txtDatos.getText();
                                    Date fechaConvertida = sdf.parse(fechaTexto);
                                    especies.setFechaExtincion(fechaConvertida);
                                    txtDatos.setText("");
                                    setCont(5);
                                    lblDatos.setText("Ingrese la época en que vivió la especie");

                                } catch (Exception er) {
                                    System.err.println("Error al parsear la fecha: " + er.getMessage()); 

                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Error de Formato de Fecha");
                                    alert.setHeaderText("Formato de fecha inválido");
                                    alert.setContentText("Por favor, ingrese la fecha en el formato correcto (ej. dd/MM/yyyy).");
                                    alert.showAndWait();
                                }
                            } else if (getCont() == 5) {
                                especies.setEpocaVivio(txtDatos.getText());
                                txtDatos.setText("");
                                setCont(6);
                                lblDatos.setText("Ingrese el peso de la especie");

                            } else if(getCont() == 6) {
                                String convertirString = txtDatos.getText();
                                BigDecimal peso = new BigDecimal(convertirString);
                                especies.setPeso(peso);
                                txtDatos.setText("");
                                setCont(7);
                                lblDatos.setText("Ingrese el tamaño de la especie");

                            } else if (getCont() == 7) {
                                String convertirString = txtDatos.getText();
                                BigDecimal tamanio = new BigDecimal(convertirString);
                                especies.setTamanio(tamanio);
                                txtDatos.setText("");
                                setCont(8);
                                lblDatos.setText("Ingrese las caracteristicas de la especie");

                            } else if (getCont() == 8) {
                                especies.setCaracteristicas(txtDatos.getText());
                                txtDatos.setText("");
                                guardarBtn.setDisable(false);
                                editarBtn.setDisable(false);
                                lblDatos.setText("Información digitada, presione insertar nuevamente para guardar la información");
                            }
                        });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cuidado");
                    alert.setContentText("No has seleccionado ninguna fila para editar aún");
                    alert.showAndWait();
                }
            } else if ("EDITANDO".equals(getBandera())) {
                especiesCtrl.edit(especies);
                setCont(1);
                especies = null;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información de la colección se ha actualizado con éxito");
                alert.showAndWait();

                filtBtn.setDisable(false);
                eliminarBtn.setDisable(false);
                insertarBtn.setDisable(false);
                filtTf.setDisable(false);
                filtCb.setDisable(false);
                editarBtn.setDisable(false);

                txtDatos.setVisible(false);
                lblDatos.setVisible(false);
                guardarBtn.setVisible(false);
                txtDatos.setText("");

                setBandera(null);
            }
        });
    }
    
    public void eliminarEspecies (Button eliminarBtn) {
        eliminarBtn.setOnAction(event-> {
            if(especies != null) {
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Solicitud de confirmación");
                mensaje.setContentText("Estas seguro de que deseas eliminar la información de la Especie: "
                        + especies .getId() + " " + especies.getNombreComun());

                ButtonType si = new ButtonType("SI");
                ButtonType no = new ButtonType("NO");

                mensaje.getButtonTypes().setAll(si, no);

                Optional<ButtonType> resultado = mensaje.showAndWait();

                if(resultado.isPresent()) {
                    if(resultado.get() == si) {
                        especiesCtrl.delete(especies);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("EXITO");
                        alert.setContentText("Usuario eliminado exitosamente");
                        alert.showAndWait();
                    } 
                } 
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Precaución");
                alert.setContentText("No hay ninguna sala seleccionada");
                alert.showAndWait();
            }
        });
    }
    
    //Temáticas
    
    public void cargarTematicaDatos(TableView tabla) {
        tabla.getColumns().clear();
        
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<Tematica, Integer>("id"));
        
        TableColumn<Tematica, Integer> idSala = new TableColumn<>("ID Sala");
        idSala.setCellValueFactory(cellData -> {
            Sala sala = cellData.getValue().getIdSala();
            return new SimpleIntegerProperty(sala != null ? sala.getId() : 0).asObject();
        });
        
        TableColumn nombreTematica = new TableColumn("Nombre Tematica");
        nombreTematica.setCellValueFactory(new PropertyValueFactory<Tematica, String>("nombreTematica"));
        
        TableColumn caracteristicas = new TableColumn("Caracteristicas");
        caracteristicas.setCellValueFactory(new PropertyValueFactory<Tematica, String>("caracteristicas"));
        
        TableColumn epocaTematica = new TableColumn("EpocaTematica");
        epocaTematica.setCellValueFactory(new PropertyValueFactory<Tematica, String>("epocaTematica"));
        
        tabla.getColumns().addAll(id, idSala, nombreTematica, caracteristicas, epocaTematica);
        
        Collection tematica = tematicaCtrl.findTematicaEntities();
        ObservableList<Object> tematicaJFX = FXCollections.observableArrayList(tematica);
            
        tabla.setItems(tematicaJFX);  
    }
    
    public void insertarTematica(TextField infoTxt, Button guardarBtn, Label infoLbl, Button insertarBtn, TextField filt1, 
            ComboBox filt2, Button filtBtn, Button elim, Button edit, ComboBox<Object> salasCb){
        insertarBtn.setOnAction(event -> {
            if (!"INSERTANDO".equals(getBandera())) {
                setBandera("INSERTANDO");
                setCont(1);
                tematica = new Tematica();

                filt1.setDisable(true);
                filt2.setDisable(true);
                filtBtn.setDisable(true);
                elim.setDisable(true);
                edit.setDisable(true);
                insertarBtn.setDisable(true);

                infoTxt.setVisible(true);
                infoLbl.setVisible(true);
                guardarBtn.setVisible(true);

                guardarBtn.setDisable(true);

                infoTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                    guardarBtn.setDisable(newValue.trim().isEmpty());
                });

                infoLbl.setText("Ingrese el nombre de la temática");

                guardarBtn.setOnAction(e -> {
                    if (getCont() == 1) {
                    tematica.setNombreTematica(infoTxt.getText());
                    infoTxt.setText("");
                    setCont(2);
                    infoLbl.setText("Seleccione la sala a la que pertenece la temática");

                    Collection<Sala> salasActualizadas = salaCtrl.findSalaEntities();
                    salasCb.setItems(FXCollections.observableArrayList(salasActualizadas));
                    salasCb.setVisible(true);
                    
                    infoTxt.setVisible(false);
                    guardarBtn.setDisable(true);

                    salasCb.valueProperty().addListener((obs, oldVal, newVal) -> {
                        guardarBtn.setDisable(!(newVal instanceof Sala));
                    });

                    } else if (getCont() == 2) {
                    Object salaSeleccionada = salasCb.getValue();

                    if (salaSeleccionada instanceof Sala) {
                        Sala sala = (Sala) salaSeleccionada;
                        tematica.setIdSala(sala);
                        salasCb.setVisible(false);
                        setCont(3);
                        infoLbl.setText("Ingrese las caracteristicas de la temática");

                        infoTxt.setVisible(true);
                        infoTxt.setText("");
                        guardarBtn.setDisable(true);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Advertencia");
                        alert.setContentText("Debe seleccionar una sala válida.");
                        alert.showAndWait();
                    }

                    } else if (getCont() == 3) {
                        tematica.setCaracteristicas(infoTxt.getText());
                        infoTxt.setText("");
                        setCont(4);
                        infoLbl.setText("Ingrese la época de la temática");

                    } else if (getCont() == 4) {
                        tematica.setEpocaTematica(infoTxt.getText());
                        infoLbl.setText("Información digitada, presione insertar nuevamente para guardar la información");
                        guardarBtn.setDisable(true); 
                        insertarBtn.setDisable(false);
                    }
                });
            } else if ("INSERTANDO".equals(getBandera())) {
                tematicaCtrl.create(tematica);
                setCont(1);
                tematica = new Tematica();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información se ha insertado con éxito");
                alert.showAndWait();


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
            }
        });
        
    }
    
    public void editarTematica(Button editarBtn, Button filtBtn, Button eliminarBtn, Button guardarBtn, Button insertarBtn, 
            TextField filtTf, ComboBox filtCb, TextField txtDatos, Label lblDatos, ComboBox salasCb) {
        editarBtn.setOnAction(event -> {
            if (!"EDITANDO".equals(getBandera())) {
                if (tematica != null) {
                    filtBtn.setDisable(true);
                    eliminarBtn.setDisable(true);
                    insertarBtn.setDisable(true);
                    filtTf.setDisable(true);
                    filtCb.setDisable(true);
                    editarBtn.setDisable(true);

                    txtDatos.setVisible(true);
                    lblDatos.setVisible(true);
                    guardarBtn.setVisible(true);
                    guardarBtn.setDisable(true);

                    setBandera("EDITANDO");

                    txtDatos.textProperty().addListener((observable, oldValue, newValue) -> {
                        guardarBtn.setDisable(newValue.trim().isEmpty());
                    });

                    lblDatos.setText("Ingrese el nombre de la colección");
                    guardarBtn.setOnAction(ev -> {
                        if (getCont() == 1) {
                    tematica.setNombreTematica(txtDatos.getText());
                    txtDatos.setText("");
                    setCont(2);
                    lblDatos.setText("Seleccione la sala a la que pertenece la temática");

                    Collection<Sala> salasActualizadas = salaCtrl.findSalaEntities();
                    salasCb.setItems(FXCollections.observableArrayList(salasActualizadas));
                    salasCb.setVisible(true);
                    
                    txtDatos.setVisible(false);
                    guardarBtn.setDisable(true);

                    salasCb.valueProperty().addListener((obs, oldVal, newVal) -> {
                        guardarBtn.setDisable(!(newVal instanceof Sala));
                    });

                    } else if (getCont() == 2) {
                    Object salaSeleccionada = salasCb.getValue();

                    if (salaSeleccionada instanceof Sala) {
                        Sala sala = (Sala) salaSeleccionada;
                        tematica.setIdSala(sala);
                        salasCb.setVisible(false);
                        setCont(3);
                        lblDatos.setText("Ingrese las caracteristicas de la temática");

                        txtDatos.setVisible(true);
                        txtDatos.setText("");
                        guardarBtn.setDisable(true);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Advertencia");
                        alert.setContentText("Debe seleccionar una sala válida.");
                        alert.showAndWait();
                    }

                    } else if (getCont() == 3) {
                        tematica.setCaracteristicas(txtDatos.getText());
                        txtDatos.setText("");
                        setCont(4);
                        lblDatos.setText("Ingrese la época de la temática");

                    } else if (getCont() == 4) {
                        tematica.setEpocaTematica(txtDatos.getText());
                        lblDatos.setText("Información digitada, presione insertar nuevamente para guardar la información");
                        guardarBtn.setDisable(true); 
                        editarBtn.setDisable(false);
                    }
                        });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cuidado");
                    alert.setContentText("No has seleccionado ninguna fila para editar aún");
                    alert.showAndWait();
                }
            } else if ("EDITANDO".equals(getBandera())) {
                tematicaCtrl.edit(tematica);
                setCont(1);
                tematica = null;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información de la colección se ha actualizado con éxito");
                alert.showAndWait();

                filtBtn.setDisable(false);
                eliminarBtn.setDisable(false);
                insertarBtn.setDisable(false);
                filtTf.setDisable(false);
                filtCb.setDisable(false);
                editarBtn.setDisable(false);

                txtDatos.setVisible(false);
                lblDatos.setVisible(false);
                guardarBtn.setVisible(false);
                txtDatos.setText("");

                setBandera(null);
            }
        });
    }
    
    public void eliminarTematica (Button eliminarBtn) {
        eliminarBtn.setOnAction(event-> {
            if(tematica != null) {
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Solicitud de confirmación");
                mensaje.setContentText("Estas seguro de que deseas eliminar la información de la Tematica: "
                        + tematica .getId() + " " + tematica.getNombreTematica());

                ButtonType si = new ButtonType("SI");
                ButtonType no = new ButtonType("NO");

                mensaje.getButtonTypes().setAll(si, no);

                Optional<ButtonType> resultado = mensaje.showAndWait();

                if(resultado.isPresent()) {
                    if(resultado.get() == si) {
                        tematicaCtrl.delete(tematica);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("EXITO");
                        alert.setContentText("Usuario eliminado exitosamente");
                        alert.showAndWait();
                    } 
                } 
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Precaución");
                alert.setContentText("No hay ninguna sala seleccionada");
                alert.showAndWait();
            }
        });
    }
    
    //PRECIO
    
    public void cargarPrecioDatos(TableView tabla) {
        tabla.getColumns().clear();
        
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<Precio, Integer>("id"));
        
        TableColumn<Precio, Integer> idSala = new TableColumn<>("ID Sala");
        idSala.setCellValueFactory(cellData -> {
            Sala sala = cellData.getValue().getIdSala();
            return new SimpleIntegerProperty(sala != null ? sala.getId() : 0).asObject();
        });
        
        TableColumn precioLunesSabado = new TableColumn("Precio de lunes a sabado ");
        precioLunesSabado.setCellValueFactory(new PropertyValueFactory<Precio, BigDecimal>("precioLunesSabado"));
        
        TableColumn precioDomingo = new TableColumn("Precio domingos");
        precioDomingo.setCellValueFactory(new PropertyValueFactory<Precio, BigDecimal>("precioDomingo"));
        
        
        tabla.getColumns().addAll(id, idSala, precioLunesSabado, precioDomingo);
        
        Collection precio = precioCtrl.findPrecioEntities();
        ObservableList<Object> precioJFX = FXCollections.observableArrayList(precio);
            
        tabla.setItems(precioJFX);  
    }
    
    public void InsertarPrecio(TextField infoTxt, Button guardarBtn, Label infoLbl, Button insertarBtn, TextField filt1, 
            ComboBox filt2, Button filtBtn, Button elim, Button edit, ComboBox salasCb) {
        insertarBtn.setOnAction(event -> {
            if (!"INSERTANDO".equals(getBandera())) {
                setBandera("INSERTANDO");
                setCont(1);
                precio = new Precio();

                filt1.setDisable(true);
                filt2.setDisable(true);
                filtBtn.setDisable(true);
                elim.setDisable(true);
                edit.setDisable(true);
                insertarBtn.setDisable(true);

                infoTxt.setVisible(true);
                infoLbl.setVisible(true);
                guardarBtn.setVisible(true);


                guardarBtn.setDisable(true);

                infoTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                    guardarBtn.setDisable(newValue.trim().isEmpty());
                });

                infoLbl.setText("Seleccione la sala a la que se le pondrá el precio");
                
                Collection<Sala> salasActualizadas = salaCtrl.findSalaEntities();
                        salasCb.setItems(FXCollections.observableArrayList(salasActualizadas));
                        salasCb.setVisible(true);

                        infoTxt.setVisible(false);
                        guardarBtn.setDisable(true);

                        salasCb.valueProperty().addListener((obs, oldVal, newVal) -> {
                            guardarBtn.setDisable(!(newVal instanceof Sala));
                        });
                        
                guardarBtn.setOnAction(e -> {

                    if (getCont() == 1) {
                        Object salaSeleccionada = salasCb.getValue();

                        if (salaSeleccionada instanceof Sala) {
                            Sala sala = (Sala) salaSeleccionada;
                            precio.setIdSala(sala);
                            salasCb.setVisible(false);
                            setCont(2);
                            infoLbl.setText("Ingrese el precio de lunes a sabados");

                            infoTxt.setVisible(true);
                            infoTxt.setText("");
                            guardarBtn.setDisable(true);

                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Advertencia");
                            alert.setContentText("Debe seleccionar una sala válida.");
                            alert.showAndWait();
                        }

                    }else if (getCont() == 2) {
                        String convertirString = infoTxt.getText();
                        BigDecimal tamanio = new BigDecimal(convertirString);
                        precio.setPrecioLunesSabado(tamanio);
                        infoTxt.setText("");
                        setCont(3);
                        infoLbl.setText("Ingrese el precio los domingos");

                    } else if (getCont() == 3) {
                        String convertirString = infoTxt.getText();
                        BigDecimal tamanio = new BigDecimal(convertirString);
                        precio.setPrecioDomingo(tamanio);
                        infoTxt.setText("");
                        infoLbl.setText("Información digitada, presione insertar nuevamente para guardar la información");
                        guardarBtn.setDisable(false);
                        insertarBtn.setDisable(false);
                    }
                });
            } else if ("INSERTANDO".equals(getBandera())) {
                precioCtrl.create(precio);
                setCont(1);
                precio = new Precio();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información se ha insertado con éxito");
                alert.showAndWait();


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
            }
        });
    }
    
    public void editarPerecio(Button editarBtn, Button filtBtn, Button eliminarBtn, Button guardarBtn, Button insertarBtn, 
            TextField filtTf, ComboBox filtCb, TextField txtDatos, Label lblDatos, ComboBox salasCb) {
        editarBtn.setOnAction(event -> {
            if (!"EDITANDO".equals(getBandera())) {
                if (precio != null) {
                    filtBtn.setDisable(true);
                    eliminarBtn.setDisable(true);
                    insertarBtn.setDisable(true);
                    filtTf.setDisable(true);
                    filtCb.setDisable(true);
                    editarBtn.setDisable(true);

                    txtDatos.setVisible(true);
                    lblDatos.setVisible(true);
                    guardarBtn.setVisible(true);
                    guardarBtn.setDisable(true);

                    setBandera("EDITANDO");

                    txtDatos.textProperty().addListener((observable, oldValue, newValue) -> {
                        guardarBtn.setDisable(newValue.trim().isEmpty());
                    });

                    lblDatos.setText("Seleccione la sala a la que se le pondrá el precio");
                
                    Collection<Sala> salasActualizadas = salaCtrl.findSalaEntities();
                    salasCb.setItems(FXCollections.observableArrayList(salasActualizadas));
                    salasCb.setVisible(true);

                    txtDatos.setVisible(false);
                    guardarBtn.setDisable(true);

                    salasCb.valueProperty().addListener((obs, oldVal, newVal) -> {
                        guardarBtn.setDisable(!(newVal instanceof Sala));
                    });
                    
                    guardarBtn.setOnAction(ev -> {
                        if (getCont() == 1) {
                        Object salaSeleccionada = salasCb.getValue();

                        if (salaSeleccionada instanceof Sala) {
                            Sala sala = (Sala) salaSeleccionada;
                            precio.setIdSala(sala);
                            salasCb.setVisible(false);
                            setCont(2);
                            lblDatos.setText("Ingrese el precio de lunes a sabados");

                            txtDatos.setVisible(true);
                            txtDatos.setText("");
                            guardarBtn.setDisable(true);

                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Advertencia");
                            alert.setContentText("Debe seleccionar una sala válida.");
                            alert.showAndWait();
                        }

                    }else if (getCont() == 2) {
                        String convertirString = txtDatos.getText();
                        BigDecimal tamanio = new BigDecimal(convertirString);
                        precio.setPrecioLunesSabado(tamanio);
                        txtDatos.setText("");
                        setCont(3);
                        lblDatos.setText("Ingrese el precio los domingos");

                    } else if (getCont() == 3) {
                        String convertirString = txtDatos.getText();
                        BigDecimal tamanio = new BigDecimal(convertirString);
                        precio.setPrecioDomingo(tamanio);
                        txtDatos.setText("");
                        lblDatos.setText("Información digitada, presione editar nuevamente para guardar la información");
                        guardarBtn.setDisable(false);
                        editarBtn.setDisable(false);
                    }
                        });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cuidado");
                    alert.setContentText("No has seleccionado ninguna fila para editar aún");
                    alert.showAndWait();
                }
            } else if ("EDITANDO".equals(getBandera())) {
                precioCtrl.edit(precio);
                setCont(1);
                precio = null;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información de la colección se ha actualizado con éxito");
                alert.showAndWait();

                filtBtn.setDisable(false);
                eliminarBtn.setDisable(false);
                insertarBtn.setDisable(false);
                filtTf.setDisable(false);
                filtCb.setDisable(false);
                editarBtn.setDisable(false);

                txtDatos.setVisible(false);
                lblDatos.setVisible(false);
                guardarBtn.setVisible(false);
                txtDatos.setText("");

                setBandera(null);
            }
        });
    }
    
    public void eliminarPrecio (Button eliminarBtn) {
        eliminarBtn.setOnAction(event-> {
            if(precio != null) {
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Solicitud de confirmación");
                mensaje.setContentText("Estas seguro de que deseas eliminar el precio con la ID: " + precio .getId());

                ButtonType si = new ButtonType("SI");
                ButtonType no = new ButtonType("NO");

                mensaje.getButtonTypes().setAll(si, no);

                Optional<ButtonType> resultado = mensaje.showAndWait();

                if(resultado.isPresent()) {
                    if(resultado.get() == si) {
                        precioCtrl.delete(precio);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("EXITO");
                        alert.setContentText("Usuario eliminado exitosamente");
                        alert.showAndWait();
                    } 
                } 
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Precaución");
                alert.setContentText("No hay ninguna sala seleccionada");
                alert.showAndWait();
            }
        });
    }
    
    //Comisiones
    public void cargarComisionDatos(TableView tabla) {
        tabla.getColumns().clear();


        TableColumn tipoTarjeta = new TableColumn("Tipo de tarjeta ");
        tipoTarjeta.setCellValueFactory(new PropertyValueFactory<ComisionTarjetas, String>("tipoTarjeta"));
        
        TableColumn comisionT = new TableColumn("Comision");
        comisionT.setCellValueFactory(new PropertyValueFactory<ComisionTarjetas, BigDecimal>("comision"));
        
        
        tabla.getColumns().addAll(tipoTarjeta, comisionT);
        
        Collection comisionTarjeta = comisionCtrl.findComisionTarjetasEntities();
        ObservableList<Object> comisionJFX = FXCollections.observableArrayList(comisionTarjeta);
            
        tabla.setItems(comisionJFX);  
    }
    
    public void InsertarComision(TextField infoTxt, Button guardarBtn, Label infoLbl, Button insertarBtn, TextField filt1, 
            ComboBox filt2, Button filtBtn, Button elim, Button edit) {
        insertarBtn.setOnAction(event -> {
            if (!"INSERTANDO".equals(getBandera())) {
                setBandera("INSERTANDO");
                setCont(1);
                comision = new ComisionTarjetas();

                filt1.setDisable(true);
                filt2.setDisable(true);
                filtBtn.setDisable(true);
                elim.setDisable(true);
                edit.setDisable(true);
                insertarBtn.setDisable(true);

                infoTxt.setVisible(true);
                infoLbl.setVisible(true);
                guardarBtn.setVisible(true);


                guardarBtn.setDisable(true);

                infoTxt.textProperty().addListener((observable, oldValue, newValue) -> {
                    guardarBtn.setDisable(newValue.trim().isEmpty());
                });
                
                infoLbl.setText("Ingrese el tipo de tarjeta");

                guardarBtn.setOnAction(e -> {
                    if (getCont() == 1) {
                        comision.setTipoTarjeta(infoTxt.getText());
                        infoTxt.setText("");
                        setCont(2);
                        infoLbl.setText("Ingrese la comision");

                    } else if (getCont() == 2) {
                        String convertirString = infoTxt.getText();
                        BigDecimal comisionT = new BigDecimal(convertirString);
                        comision.setComision(comisionT);
                        infoLbl.setText("Información digitada, presione insertar nuevamente para guardar la información");
                        guardarBtn.setDisable(false);
                        insertarBtn.setDisable(false);
                    }
                });
            } else if ("INSERTANDO".equals(getBandera())) {
                comisionCtrl.create(comision);
                setCont(1);
                comision = new ComisionTarjetas();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información se ha insertado con éxito");
                alert.showAndWait();


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
            }
        });
    }
    
    public void editarComision(Button editarBtn, Button filtBtn, Button eliminarBtn, Button guardarBtn, Button insertarBtn, 
            TextField filtTf, ComboBox filtCb, TextField txtDatos, Label lblDatos) {
        editarBtn.setOnAction(event -> {
            if (!"EDITANDO".equals(getBandera())) {
                if (comision != null) {
                    filtBtn.setDisable(true);
                    eliminarBtn.setDisable(true);
                    insertarBtn.setDisable(true);
                    filtTf.setDisable(true);
                    filtCb.setDisable(true);
                    editarBtn.setDisable(true);

                    txtDatos.setVisible(true);
                    lblDatos.setVisible(true);
                    guardarBtn.setVisible(true);
                    guardarBtn.setDisable(true);

                    setBandera("EDITANDO");

                    txtDatos.textProperty().addListener((observable, oldValue, newValue) -> {
                        guardarBtn.setDisable(newValue.trim().isEmpty());
                    });

                    lblDatos.setText("Ingrese la comision");
                    guardarBtn.setOnAction(ev -> {
                        if (getCont() == 1) {
                        String convertirString = txtDatos.getText();
                        BigDecimal comisionT = new BigDecimal(convertirString);
                        comision.setComision(comisionT);
                        lblDatos.setText("Información digitada, presione editar nuevamente para guardar la información");
                        guardarBtn.setDisable(false);
                        editarBtn.setDisable(false);
                        } 
                    });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cuidado");
                    alert.setContentText("No has seleccionado ninguna fila para editar aún");
                    alert.showAndWait();
                }
            } else if ("EDITANDO".equals(getBandera())) {
                comisionCtrl.edit(comision);
                setCont(1);
                comision = null;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Éxito");
                alert.setContentText("La información de la sala se ha actualizado con éxito");
                alert.showAndWait();

                filtBtn.setDisable(false);
                eliminarBtn.setDisable(false);
                insertarBtn.setDisable(false);
                filtTf.setDisable(false);
                filtCb.setDisable(false);
                editarBtn.setDisable(false);

                txtDatos.setVisible(false);
                lblDatos.setVisible(false);
                guardarBtn.setVisible(false);
                txtDatos.setText("");

                setBandera(null);
            }
        });
    }
    
    public void eliminarComision (Button eliminarBtn) {
        eliminarBtn.setOnAction(event-> {
            if(comision != null) {
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Solicitud de confirmación");
                mensaje.setContentText("Estas seguro de que deseas eliminar la comision con tipo de tarjeta: " + comision.getTipoTarjeta());

                ButtonType si = new ButtonType("SI");
                ButtonType no = new ButtonType("NO");

                mensaje.getButtonTypes().setAll(si, no);

                Optional<ButtonType> resultado = mensaje.showAndWait();

                if(resultado.isPresent()) {
                    if(resultado.get() == si) {
                        comisionCtrl.delete(comision);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("EXITO");
                        alert.setContentText("Usuario eliminado exitosamente");
                        alert.showAndWait();
                    } 
                } 
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Precaución");
                alert.setContentText("No hay ninguna sala seleccionada");
                alert.showAndWait();
            }
        });
    }
    
    /*public void probar(Pane pane) {
        for (Node nodo : pane.getChildren()) {
            nodo.setVisible(false);
        }
    }*/
}
