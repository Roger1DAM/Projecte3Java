import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import cursos.Curs;
import cursos.CursColectiu;
import cursos.CursCompeticio;
import cursos.CursIndividual;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LloguerCursosApp extends Application {

    //Declaració dels camps dels formularis centrals.
    Tab tab1;
    Tab tab2;
    Tab tab3;

    Label lblDni;
    TextField txtDni;
    Label lblNom;
    TextField txtNom;
    Label lblCognom;
    TextField txtCognom;
    Label lblUsuari;
    TextField txtUsuari;
    Label lblSexe;
    TextField txtSexe;
    Label lblEmail;
    TextField txtEmail;
    Label lblNumFN;
    TextField txtNumFN;
    Label lblDataFN;
    TextField txtDataFN;
    Label lblNumFed;
    TextField txtNumFed;
    Label lblDataFed;
    TextField txtDataFed;
    Label lblNivell;
    TextField txtNivell;

    Label lblIdCurs;
    TextField txtIdCurs;
    Label lblNomCurs;
    TextField txtNomCurs;
    Label lblDataCurs;
    TextField txtDataCurs;
    Label lblPreuCurs;
    TextField txtPreuCurs;

    Label lblDataFi;
    TextField txtDataFi;
    Label lblNivellCurs;
    TextField txtNivellCurs;
    Label lblHoresCurs;
    ChoiceBox cbHoresCurs;
    Label lblHoraInici;
    ChoiceBox cbHoraInici;
    DatePicker dpCursIndividual;
    Label lblPreuFinal;
    TextField txtPreuFinal;

    Stage stage;

    TableView<Client> tblClients;
    TableView<Curs> tblCursosIndividuals;
    TableView<Curs> tblCursosColectius;
    TableView<Curs> tblCursosCompeticio;

    GridPane gpClient;
    GridPane gpCurs;

    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Curs> cursos = new ArrayList<>();

    Connection c = ConnexioBD.getConnection();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane contenidor = new BorderPane();

        Pane superior = partSuperior();
        Pane inferior = partInferior();
        Pane left = partLateralEsquerra();
        Pane right = partLateralDreta();
        Pane center = formulariCentral();

        contenidor.setTop(superior);
        contenidor.setBottom(inferior);
        contenidor.setLeft(left);
        contenidor.setRight(right);
        contenidor.setCenter(center);

        //Afegim marges a tots els costats.
        BorderPane.setMargin(superior, new Insets(20, 20, 20, 20));
        BorderPane.setMargin(inferior, new Insets(20, 20, 20, 20));
        BorderPane.setMargin(left, new Insets(20, 20, 20, 20));
        BorderPane.setMargin(right, new Insets(20, 20, 20, 20));
        BorderPane.setMargin(center, new Insets(20, 20, 20, 20));

        Scene scene = new Scene(contenidor);
        
        stage.setScene(scene);
        stage.setMinHeight(300);
        stage.setMinWidth(300);
        stage.show();
    }

    public Pane partLateralDreta() throws SQLException {
        VBox vb = new VBox();
        Label lblCursos = new Label("· CURSOS ·");
        vb.setSpacing(10.0);

        vb.setAlignment(Pos.CENTER);

        TabPane tp = new TabPane();
        tab1 = new Tab("Individuals", cursosIndividuals());
        tab2 = new Tab("Col·lectius", cursosColectius());
        tab3 = new Tab("Competició", cursosCompeticio());

        tp.getTabs().addAll(tab1, tab2, tab3);

        vb.getChildren().addAll(lblCursos, tp);

        tab1.setOnSelectionChanged(e -> {
            //Si detecta que hi ha seleccionada la pestanya de cursos individuals es mostraràn els camps dels cursos individuals i d'amagaran els de la resta de cursos.
            if (tab1.isSelected()) {
                txtIdCurs.setText("");
                //Si detecta que té els camps dels cursos de competició els elimina.
                if (gpCurs.getChildren().contains(txtDataFi)); {
                    lblDataCurs.setText("Data: ");
                    gpCurs.getChildren().removeAll(lblDataFi, txtDataFi, lblNivellCurs, txtNivellCurs);
                }
                //Si detecta que no té els camps dels cursos individuals elimina els que no són necessaris i afegeix els que necesita.
                if (!gpCurs.getChildren().contains(cbHoresCurs)) {
                    gpCurs.getChildren().removeAll(lblIdCurs, txtIdCurs, lblNomCurs, txtNomCurs, txtDataCurs);
                    txtNomCurs.setText("");
                    txtDataCurs.setText("");
                    lblPreuCurs.setText("Preu/hora: ");
                    txtPreuCurs.setText("20");
                    

                    dpCursIndividual = new DatePicker();
                    dpCursIndividual.setPromptText("AAAA-MM-DD");
                    gpCurs.add(dpCursIndividual, 1, 3);
                    gpCurs.add(lblHoraInici, 0, 4);
                    gpCurs.add(cbHoraInici, 1, 4);
                    gpCurs.add(lblHoresCurs, 0, 5);
                    gpCurs.add(cbHoresCurs, 1, 5);
                    gpCurs.add(lblPreuFinal, 0, 6);
                    gpCurs.add(txtPreuFinal, 1, 6);

                    cbHoresCurs.setOnAction(ev-> { 
                        if (txtNom.getText().equals("")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setTitle("Avís");
                            alert.setContentText("Selecciona un client");
                            alert.showAndWait();
                            cbHoresCurs.setValue(null);
                            cbHoraInici.setValue(null);
                        } else {
                            if (!cbHoresCurs.getValue().equals("")) {
                            Connection c = ConnexioBD.getConnection();
                            try {
                                CallableStatement query = c.prepareCall("{? = call calcularPreuFinal(?,?,?)}");
                                query.registerOutParameter(1, Types.INTEGER);
                                query.setInt(2, Integer.parseInt(txtPreuCurs.getText()));
                                query.setInt(3, Integer.parseInt(cbHoresCurs.getValue().toString()));
                                query.setString(4, txtNom.getText());
                                query.execute();
                                txtPreuFinal.setText(String.valueOf(query.getInt(1)));
                                
                            } catch (SQLException ex) {
                                System.out.println("Hi ha hagut un error a la consulta SQL");
                            }
                        }
                    }  
                    });
                }
            }
        });
        return vb;
    }

    public Pane formulariCentral() {
        VBox vb = new VBox();
        VBox vb2 = new VBox();
        gpClient = new GridPane();

        Label lblTitolClient = new Label("DADES CLIENT");
        lblTitolClient.setPadding(new Insets(20));   
        lblTitolClient.setTextFill(Color.web("#0076a3")); 
        lblTitolClient.setFont(new Font(20));

        lblDni = new Label("Dni: ");
        txtDni = new TextField();
        txtDni.setDisable(true);
        
        lblNom = new Label("Nom: ");
        txtNom = new TextField();
        txtNom.setDisable(true);

        lblCognom = new Label("Cognom: ");
        txtCognom = new TextField();
        txtCognom.setDisable(true);

        lblUsuari = new Label("Usuari: ");
        txtUsuari = new TextField();
        txtUsuari.setDisable(true);

        lblSexe = new Label("Sexe: ");
        txtSexe = new TextField();
        txtSexe.setDisable(true);

        lblEmail = new Label("Email: ");
        txtEmail = new TextField();
        txtEmail.setDisable(true);

        lblNumFN = new Label("Nº Familia Nombrosa: ");
        txtNumFN = new TextField();
        txtNumFN.setDisable(true);

        lblDataFN = new Label("Data caducitat FN: ");
        txtDataFN = new TextField();
        txtDataFN.setDisable(true);

        lblNumFed = new Label("Nº Federació: ");
        txtNumFed = new TextField();
        txtNumFed.setDisable(true);

        lblDataFed = new Label("Data caducitat federació: ");
        txtDataFed = new TextField();
        txtDataFed.setDisable(true);

        lblNivell = new Label("Nivell: ");
        txtNivell = new TextField();
        txtNivell.setDisable(true);

        lblPreuFinal = new Label("Preu final: ");
        txtPreuFinal = new TextField();
        txtPreuFinal.setDisable(true);
        
        gpClient.add(lblDni, 0, 0);
        gpClient.add(txtDni, 1, 0);
        gpClient.add(lblNom, 0, 1);
        gpClient.add(txtNom, 1, 1);
        gpClient.add(lblCognom, 0, 2);
        gpClient.add(txtCognom, 1, 2);
        gpClient.add(lblUsuari, 0, 3);
        gpClient.add(txtUsuari, 1, 3);
        gpClient.add(lblSexe, 0, 4);
        gpClient.add(txtSexe, 1, 4);
        gpClient.add(lblEmail, 0, 5);
        gpClient.add(txtEmail, 1, 5);
        gpClient.add(lblNumFN, 0, 6);
        gpClient.add(txtNumFN, 1, 6);
        gpClient.add(lblDataFN, 0, 7);
        gpClient.add(txtDataFN, 1, 7);
        gpClient.add(lblNumFed, 0, 8);
        gpClient.add(txtNumFed, 1, 8);
        gpClient.add(lblDataFed, 0, 9);
        gpClient.add(txtDataFed, 1, 9);
        gpClient.add(lblNivell, 0, 10);
        gpClient.add(txtNivell, 1, 10);
        vb2.getChildren().addAll(lblTitolClient, gpClient);

        gpCurs = new GridPane();
        VBox vb3 = new VBox();

        Label lblTitolCurs = new Label("DADES CURS");
        lblTitolCurs.setPadding(new Insets(20));   
        lblTitolCurs.setTextFill(Color.web("#0076a3")); 
        lblTitolCurs.setFont(new Font(20));

        lblIdCurs = new Label("ID: ");
        txtIdCurs = new TextField();
        txtIdCurs.setDisable(true);
        lblNomCurs = new Label("Nom: ");
        txtNomCurs = new TextField();
        txtNomCurs.setDisable(true);
        txtNomCurs.setPrefWidth(200);
        lblPreuCurs = new Label("Preu: ");
        txtPreuCurs = new TextField();
        txtPreuCurs.setDisable(true);
        lblDataCurs = new Label("Data: ");
        txtDataCurs = new TextField();
        txtDataCurs.setDisable(true);

        lblDataFi = new Label("Data fi: ");
        txtDataFi = new TextField();
        txtDataFi.setDisable(true);
        lblNivellCurs = new Label("Nivell: ");
        txtNivellCurs = new TextField();
        txtNivellCurs.setDisable(true);

        lblHoresCurs = new Label("Hores a reservar");
        cbHoresCurs = new ChoiceBox<>();
        cbHoresCurs.getItems().add("1");
        cbHoresCurs.getItems().add("2");
        cbHoresCurs.getItems().add("3");
        cbHoresCurs.getItems().add("6");


        lblHoraInici = new Label("Hora inici: ");

        cbHoraInici = new ChoiceBox<>();
        cbHoraInici.getItems().add("11:00:00");
        cbHoraInici.getItems().add("12:00:00");
        cbHoraInici.getItems().add("13:00:00");
        cbHoraInici.getItems().add("16:00:00");
        cbHoraInici.getItems().add("17:00:00");
        cbHoraInici.getItems().add("18:00:00");
        cbHoraInici.getItems().add("19:00:00");        

        gpCurs.add(lblIdCurs, 0, 0);
        gpCurs.add(txtIdCurs, 1, 0);
        gpCurs.add(lblNomCurs, 0, 1);
        gpCurs.add(txtNomCurs, 1, 1);
        gpCurs.add(lblPreuCurs, 0, 2);
        gpCurs.add(txtPreuCurs, 1, 2);
        gpCurs.add(lblDataCurs, 0, 3);
        gpCurs.add(txtDataCurs, 1, 3);
        
        gpClient.setAlignment(Pos.CENTER);
        gpClient.setVgap(10);
        gpClient.setHgap(10);
        vb2.setAlignment(Pos.CENTER);
        vb2.setMinHeight(400);
        gpCurs.setAlignment(Pos.CENTER);
        gpCurs.setVgap(10);
        gpCurs.setHgap(10);
        vb3.setAlignment(Pos.TOP_CENTER);

        vb.setAlignment(Pos.TOP_CENTER);

        vb3.getChildren().addAll(lblTitolCurs, gpCurs);
        vb.getChildren().addAll(vb2, vb3);

        return vb;
    }

    public Pane partSuperior() {
        HBox hb = new HBox();
        Label lblTitol = new Label("Lloguer de cursos d'esquí");
        lblTitol.setPadding(new Insets(20));   
        lblTitol.setTextFill(Color.web("#0076a3")); 
        lblTitol.setFont(new Font(30));
        hb.getChildren().add(lblTitol);
        
        hb.setAlignment(Pos.CENTER);
        hb.setMinHeight(100);
        // hb.setStyle("-fx-border-color: black");
        return hb;
    } 

    public Pane partInferior() {
        Button btn1 = new Button("Llogar");
        Button btn2 = new Button("Netejar");
        Button btn3 = new Button("Sortir");
        btn1.setPadding(new Insets(20));   
        btn2.setPadding(new Insets(20));   
        btn3.setPadding(new Insets(20));   

        btn1.setOnAction(e-> {
            llogarCurs();
        });
        btn2.setOnAction(e-> netejarDades());

        btn3.setOnAction(e-> {
            javafx.application.Platform.exit();
        });
        
        HBox hInferior = new HBox();
        hInferior.setAlignment(Pos.CENTER);
        hInferior.getChildren().addAll(btn1, btn2, btn3);
        return hInferior;
    }

    private void llogarCurs() {
        Connection c = ConnexioBD.getConnection();
        if (!tab1.isSelected()) {
            try {
                CallableStatement cs = c.prepareCall("call llogarCursos(?,?)");
                cs.setString(1, txtDni.getText());
                cs.setInt(2, Integer.parseInt(txtIdCurs.getText()));
                
                if (cs.execute()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Curs llogat");
                    alert.setContentText("Curs llogat correctament!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("No s'ha pogut llogar el curs.");
                    alert.showAndWait();
                }
                
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");  
                alert.setContentText("No s'ha pogut llogar el curs.");
                if (txtDni.getText().equals("")) {
                    alert.setContentText("Has de seleccionar algun client.");
                } else if (txtIdCurs.getText().equals("")) {
                    alert.setContentText("Has de seleccionar algun curs.");
                }
                alert.showAndWait();
            }
        } else {
            try {
                CallableStatement cs = c.prepareCall("call llogarCursIndividual(?,?,?,?,?,?)");
                cs.setString(1, txtDni.getText());
                cs.setDate(2, Date.valueOf(dpCursIndividual.getValue()));
                cs.setInt(3, Integer.parseInt(txtPreuCurs.getText()));
                cs.setTime(4, Time.valueOf(String.valueOf(cbHoraInici.getValue())));
                cs.setInt(5, Integer.parseInt(String.valueOf(cbHoresCurs.getValue())));
                cs.setInt(6, Integer.parseInt(txtPreuFinal.getText()));
                cs.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Curs llogat");
                alert.setContentText("Curs llogat correctament");
                alert.showAndWait();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");  
                alert.setContentText("No s'ha pogut llogar el curs.");
            }
        }
    }

    private void netejarDades() {
        txtDni.setText("");
        txtNom.setText("");
        txtCognom.setText("");
        txtUsuari.setText("");
        txtSexe.setText("");
        txtEmail.setText("");
        txtNumFN.setText("");
        txtDataFN.setText("");
        txtNumFed.setText("");
        txtDataFed.setText("");
        txtNivell.setText("");

        txtIdCurs.setText("");
        txtNomCurs.setText("");
        txtDataCurs.setText("");
        if (!gpCurs.getChildren().contains(cbHoresCurs)) {
            txtPreuCurs.setText("");
        }
        txtDataFi.setText("");
        txtNivellCurs.setText("");
        cbHoraInici.setValue(null);
        cbHoresCurs.setValue(null);
        txtPreuFinal.setText("");
        
        tblClients.getSelectionModel().select(null);
        tblCursosIndividuals.getSelectionModel().select(null);
        tblCursosColectius.getSelectionModel().select(null);
        tblCursosCompeticio.getSelectionModel().select(null);
    }

    public Pane partLateralEsquerra() throws SQLException {
        VBox vLateral = new VBox();
        vLateral.setAlignment(Pos.CENTER);
        // vLateral.setPadding(new Insets(10));

        tblClients = new TableView<>();
        TableColumn<Client, String> colDni = new TableColumn<>("DNI"); 
        TableColumn<Client, String> colNom = new TableColumn<>("Nom"); 
        TableColumn<Client, String> colCognom = new TableColumn<>("Cognom");
        tblClients.getColumns().addAll(colDni,colNom,colCognom);

        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCognom.setCellValueFactory(new PropertyValueFactory<>("cognom"));

        clients = obtenirClientsBD();
        for (Client c : clients) {
            tblClients.getItems().add(c);
        }
        
        vLateral.getChildren().addAll(new Label("CLIENTS"), tblClients);

        tblClients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Client client = (Client) newValue;
                
                if (client != null) {
                    txtDni.setText(client.getDni());
                    txtNom.setText(client.getNom());
                    txtCognom.setText(client.getCognom());
                    txtUsuari.setText(client.getUsuari());
                    txtSexe.setText(client.getSexe());
                    txtEmail.setText(client.getEmail());
                    
                    if (client.getFamilia_nombrosa() != null) {
                        txtNumFN.setText(client.getFamilia_nombrosa());
                        txtDataFN.setText(String.valueOf(client.getData_caducitatFN()));
                    } else {
                        txtNumFN.setText("");
                        txtDataFN.setText("");
                    }
                    
                    if (client.getNumFederat() != null) {
                        txtNumFed.setText(client.getNumFederat());
                        txtDataFed.setText(String.valueOf(client.getData_caducitatFed()));
                        txtNivell.setText(String.valueOf(client.getNivell()));
                    } else {
                        txtNumFed.setText("");
                        txtDataFed.setText("");
                        txtNivell.setText("");
                    }
                }
        }}); 

        return vLateral;
    }

    private Pane cursosIndividuals() throws SQLException {
        VBox vb = new VBox();
        tblCursosIndividuals = new TableView<>();
        TableColumn<Curs, String> colId = new TableColumn<>("ID");
        TableColumn<Curs, String> colData = new TableColumn<>("DATA");
        TableColumn<Curs, String> colHores = new TableColumn<>("HORES");
        TableColumn<Curs, String> colPreu = new TableColumn<>("PREU");

        tblCursosIndividuals.getColumns().addAll(colId, colData, colHores, colPreu);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colHores.setCellValueFactory(new PropertyValueFactory<>("Hores"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu"));

        // cursos = obtenirCursosIndividualsBD();
        // for (Curs c1 : cursos) {
        //     if( c1 instanceof CursIndividual) {
        //         tblCursosIndividuals.getItems().add(c1);
        //     }
        // }

        tblCursosIndividuals.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CursIndividual curs = (CursIndividual) newValue;
                
                if (curs != null) {
                    txtNomCurs.setText(curs.getNom());
                    txtDataCurs.setText(String.valueOf(curs.getData()));
                }
        }});
        
        vb.getChildren().add(tblCursosIndividuals);
        
        return vb;
    }

    private Pane cursosColectius() throws SQLException {
        VBox vb = new VBox();
        tblCursosColectius = new TableView<>();
        TableColumn<Curs, String> colId = new TableColumn<>("ID");
        TableColumn<Curs, String> colNom = new TableColumn<>("NOM");
        TableColumn<Curs, String> colData = new TableColumn<>("DATA");
        TableColumn<Curs, String> colPreu = new TableColumn<>("PREU");

        tblCursosColectius.getColumns().addAll(colId, colNom, colData, colPreu);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu"));

        cursos = obtenirCursosColectiusBD();
        for (Curs c1 : cursos) {
            if( c1 instanceof CursColectiu) {
                tblCursosColectius.getItems().add(c1);
            }
        }

        tblCursosColectius.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CursColectiu curs = (CursColectiu) newValue;
                
                if (curs != null) {
                    txtIdCurs.setText(String.valueOf(curs.getId()));
                    txtNomCurs.setText(curs.getNom());
                    txtDataCurs.setText(String.valueOf(curs.getData()));
                    txtPreuCurs.setText(String.valueOf(curs.getPreu()));
                    if (gpCurs.getChildren().contains(txtDataFi)); {
                        lblDataCurs.setText("Data: ");
                        gpCurs.getChildren().removeAll(lblDataFi, txtDataFi, lblNivellCurs, txtNivellCurs);
                    }
                    if (gpCurs.getChildren().contains(cbHoresCurs)) {
                        lblPreuCurs.setText("Preu: ");
                        gpCurs.getChildren().addAll(lblIdCurs, txtIdCurs, lblNomCurs, txtNomCurs, txtDataCurs);
                        gpCurs.getChildren().removeAll(cbHoresCurs, lblHoresCurs, dpCursIndividual, lblPreuFinal, txtPreuFinal, cbHoraInici, lblHoraInici);
                    }
                }
        }});
        
        vb.getChildren().add(tblCursosColectius);
        
        return vb;
    }

    private Pane cursosCompeticio() throws SQLException {
        VBox vb = new VBox();
        tblCursosCompeticio = new TableView<>();
        TableColumn<Curs, Integer> colId = new TableColumn<>("ID");
        TableColumn<Curs, Integer> colNivell = new TableColumn<>("NIVELL");
        TableColumn<Curs, String> colNom = new TableColumn<>("NOM");
        TableColumn<Curs, LocalDate> colDataInici = new TableColumn<>("DATA INICI");
        TableColumn<Curs, LocalDate> colDataFi = new TableColumn<>("DATA FI");
        TableColumn<Curs, Double> colPreu = new TableColumn<>("PREU");

        tblCursosCompeticio.getColumns().addAll(colId, colNivell, colNom, colDataInici, colDataFi, colPreu);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNivell.setCellValueFactory(new PropertyValueFactory<>("nivell"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDataInici.setCellValueFactory(new PropertyValueFactory<>("data"));
        colDataFi.setCellValueFactory(new PropertyValueFactory<>("data_fi"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu"));

        cursos = obtenirCursosCompeticioBD();
        for (Curs c1 : cursos) {
            if( c1 instanceof CursCompeticio) {
                tblCursosCompeticio.getItems().add(c1);
                // gpCurs.getChildren().addAll(lblDataFi, txtDataFi, lblNivellCurs, txtNivellCurs);
            }
        }

        tblCursosCompeticio.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CursCompeticio curs = (CursCompeticio) newValue;
                
                if (curs != null) {
                    txtIdCurs.setText(String.valueOf(curs.getId()));
                    txtNomCurs.setText(curs.getNom());
                    txtDataCurs.setText(String.valueOf(curs.getData()));
                    txtPreuCurs.setText(String.valueOf(curs.getPreu()));
                    if (!gpCurs.getChildren().contains(txtDataFi)); {
                        lblDataCurs.setText("Data inici: ");
                        gpCurs.add(lblDataFi, 0, 4);
                        gpCurs.add(txtDataFi, 1, 4);
                        gpCurs.add(lblNivellCurs, 0, 5);
                        gpCurs.add(txtNivellCurs, 1, 5);
                        txtDataFi.setText(String.valueOf(curs.getData_fi()));
                        txtNivellCurs.setText(String.valueOf(curs.getNivell()));
                    }
                    if (gpCurs.getChildren().contains(cbHoresCurs)) {
                        lblPreuCurs.setText("Preu: ");
                        gpCurs.getChildren().addAll(lblIdCurs, txtIdCurs, lblNomCurs, txtNomCurs, txtDataCurs);
                        gpCurs.getChildren().removeAll(lblHoresCurs, cbHoresCurs, dpCursIndividual, lblPreuFinal, txtPreuFinal, cbHoraInici, lblHoraInici);
                    }
                }
        }});
        vb.getChildren().add(tblCursosCompeticio);
        return vb;
    }

    private ArrayList<Client> obtenirClientsBD() throws SQLException {
        String consulta = "SELECT a.*, b.num_familia_nombrosa, b.data_caducitat AS data_fn , c.num_federacio, c.data_caducitat AS data_fed, c.nivell  FROM client a LEFT JOIN familia_nombrosa b ON a.dni = b.dni LEFT JOIN federat c ON a.dni = c.dni";
        Connection c = ConnexioBD.getConnection();
        PreparedStatement sentencia = c.prepareStatement(consulta);
        sentencia.executeQuery();
        ResultSet rs = sentencia.executeQuery();

        while (rs.next()) {
            Client c1 = new Client();
            c1.setDni(rs.getString("dni"));
            c1.setNom(rs.getString("nom"));
            c1.setCognom(rs.getString("cognom"));
            c1.setEmail(rs.getString("email"));
            c1.setUsuari(rs.getString("usuari"));
            c1.setSexe(rs.getString("sexe"));
            c1.setNivell(rs.getInt("nivell"));
            c1.setFamilia_nombrosa(rs.getString("num_familia_nombrosa"));
            try {
                c1.setData_caducitatFN(LocalDate.parse(rs.getString("data_fn")));
            } catch (Exception e) {
            }
            c1.setNumFederat(rs.getString("num_federacio"));
            try {
                c1.setData_caducitatFed(Date.valueOf(rs.getString("data_fed")).toLocalDate());   
            } catch (Exception e) {
            }
            c1.setNivell(rs.getInt("nivell"));
            clients.add(c1);            
        }  
        return clients;     
    }

    // private ArrayList<Curs> obtenirCursosIndividualsBD() throws SQLException {
    //     String consulta = "SELECT * FROM lloga_curs_individual WHERE dni = " + txtDni.getText();
    //     Connection c = ConnexioBD.getConnection();
    //     PreparedStatement sentencia = c.prepareStatement(consulta);
    //     sentencia.executeQuery();
    //     ResultSet rs = sentencia.executeQuery();

    //     while (rs.next()) {
    //         CursIndividual c1 = new CursIndividual();
    //         c1.setId(rs.getInt("id"));
    //         c1.setData(LocalDate.parse(rs.getString("data")));
    //         c1.setHores(rs.getInt("hores"));
    //         c1.setHoraInici(rs.getString("hora_inici"));
    //         c1.setPreuHora(rs.getDouble("preu"));
    //         cursos.add(c1);            
    //     }  
    //     return cursos;
    // }

    private ArrayList<Curs> obtenirCursosColectiusBD() throws SQLException {
        String consulta = "SELECT * FROM colectiu a, curs b WHERE a.id = b.id";
        Connection c = ConnexioBD.getConnection();
        PreparedStatement sentencia = c.prepareStatement(consulta);
        sentencia.executeQuery();
        ResultSet rs = sentencia.executeQuery();

        while (rs.next()) {
            CursColectiu c1 = new CursColectiu();
            c1.setId(rs.getInt("id"));
            c1.setNom(rs.getString("nom"));
            c1.setData(LocalDate.parse(rs.getString("data")));
            c1.setPreu(rs.getDouble("preu"));
            cursos.add(c1);            
        }  
        return cursos;     
    }

    private ArrayList<Curs> obtenirCursosCompeticioBD() throws SQLException {
        String consulta = "SELECT * FROM competicio a, curs b WHERE a.id = b.id";
        Connection c = ConnexioBD.getConnection();
        PreparedStatement sentencia = c.prepareStatement(consulta);
        sentencia.executeQuery();
        ResultSet rs = sentencia.executeQuery();

        while (rs.next()) {
            CursCompeticio c1 = new CursCompeticio();
            c1.setId(rs.getInt("id"));
            c1.setNivell(rs.getInt("nivell"));
            c1.setNom(rs.getString("nom"));
            c1.setData(LocalDate.parse(rs.getString("data")));
            c1.setData_fi(LocalDate.parse(rs.getString("data_fi")));
            c1.setPreu(rs.getDouble("preu"));
            cursos.add(c1);            
        }  
        return cursos;     
    }
}
