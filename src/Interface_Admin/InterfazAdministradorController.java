package Interface_Admin;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Objects.CommentaryTurn;
import Database.ConnectionDataBase;
import Login.InterfaceLoginExecutor;
import Objects.SummaryDay;
import Objects.Incident;
import Objects.SummaryMonth;
import Objects.SemanaBalance;
import Objects.SummaryYear;
import Objects.User;
import javafx.scene.control.Button;

public class InterfazAdministradorController implements Initializable {
    
    private ConnectionDataBase conector;
    private User adminUserData;
    
    @FXML private TextField nickNameAdminTextField;
    @FXML private TextField nameAdminTextField;
    @FXML private TextField dniAdminTextField;
    @FXML private TextField phoneAdminTextField;
    @FXML private TextField addressAdminTextField;
    @FXML private TextField surnameAdminTextField;
    
    @FXML private TextField nameUserFirstTurnAdminTextField;
    @FXML private TextField nameUserSecondTurnAdminTextField;
    @FXML private TextField cashRegisterFirstUserAdminTextField;
    @FXML private TextField cashRegisterSecondUserAdminTextField;
    @FXML private TextField commentFirstTurnAdminTextField;
    @FXML private TextField commentSecondTurnAdminTextField;
    @FXML private TextField balanceDateAdminTextField;
    
    @FXML private TableView<Incident> incidentsFirstUserAdminTable;
    @FXML private TableColumn incidentFirstUserAdminColumn;
    @FXML private TableColumn costFirstUserAdminColumn;
    ObservableList<Incident> incidentsFirstUserAdminObservableList;
    
    @FXML private TableView<Incident> incidentsSecondUserAdminTable;
    @FXML private TableColumn incidentSecondUserAdminColumn;
    @FXML private TableColumn costSecondUserAdminColumn;
    ObservableList<Incident> incidentsSecondUserAdminObservableList;
    
    @FXML private TableView<CommentaryTurn> commentaryFirstUserAdminTable;
    @FXML private TableColumn commentaryFirstUserAdminColumn;
    ObservableList<CommentaryTurn> commentaryFirstUserAdminObservableList;
    
    @FXML private TableView<CommentaryTurn> commentarySecondUserAdminTable;
    @FXML private TableColumn commentarySecondUserAdminColumn;
    ObservableList<CommentaryTurn> commentarySecondUserAdminObservableList; 
    
    @FXML private TextField datePickedStatisticAdminTextField;
    @FXML private TextField nameFirstTurnAdminTextField;
    @FXML private TextField cashRegisterFirstTurnAdminTextField;
    @FXML private TextField surnameFirstTurnAdminTextField;
    @FXML private TextField phoneFirstTurnAdminTextField;
    @FXML private TextField addressFirstTurnAdminTextField;
    @FXML private TextField dniFirstTurnAdminTextField;
    @FXML private TextField nameSecondTurnAdminTextField;
    @FXML private TextField balanceWithDateAdminTextField;
    @FXML private TextField surnameSecondTurnAdminTextField;
    @FXML private TextField phoneSecondTurnAdminTextField;
    @FXML private TextField addressSecondTurnAdminTextField;
    @FXML private TextField dniSecondTurnAdminTextField;
    @FXML private TextField cashRegisterSecondTurnAdminTextField;
    
    @FXML private TableView<SummaryYear> summaryYearAdminTable;
    @FXML private TableColumn nameYearBalanceAdminColumn;
    @FXML private TableColumn summaryBalanceAdminColumn;
    ObservableList<SummaryYear> summaryYearAdminObservableList;
    private int yearPicked;
    
    @FXML private TableView<SummaryMonth> summaryMonthAdminTable;
    @FXML private TableColumn nombreMesCL;
    @FXML private TableColumn montoMesCL;
    ObservableList<SummaryMonth> summaryMonthAdminObservableList;
    private int monthPicked;
    
    @FXML private TableView<SemanaBalance> summaryWeekAdminTable;
    @FXML private TableColumn nombreSemanaCL;
    @FXML private TableColumn montoSemanaCL;
    ObservableList<SemanaBalance> summaryWeekAdminObservableList;
    private int posicionSemana;
    
    @FXML private TableView<SummaryDay> summaryDayAdminTable;
    @FXML private TableColumn nombreDiaCL;
    @FXML private TableColumn montoDiaCL;
    ObservableList<SummaryDay> summaryDayAdminObservableList;
    private int dayPicked;
    
    @FXML private TableView<Incident> incidentsFirstTurnAdminTable;
    @FXML private TableColumn incidentFirstTurnAdminColumn;
    @FXML private TableColumn costFirstTurnAdminColumn;
    ObservableList<Incident> incidentsFirstTurnAdminObservableList;
    
    @FXML private TableView<Incident> incidentsSecondTurnAdminTable;
    @FXML private TableColumn incidentSecondTurnAdminColumn;
    @FXML private TableColumn costSecondTurnAdminColumn;
    ObservableList<Incident> incidentsSecondTurnAdminObservableList;
    
    @FXML private TableView<CommentaryTurn> commentaryFirstTurnAdminTable;
    @FXML private TableColumn commentaryFirstTurnAdminColumn;
    ObservableList<CommentaryTurn> commentaryFirstTurnAdminObservableList;
    
    @FXML private TableView<CommentaryTurn> commentarySecondTurnAdminTable;
    @FXML private TableColumn commentarySecondTurnAdminColumn;
    ObservableList<CommentaryTurn> commentarySecondTurnAdminObservableList;
    
    @FXML private TextField nameEditAdminTextField;
    @FXML private TextField surnameEditAdminTextField;
    @FXML private TextField dniEditAdminTextField;
    @FXML private TextField phoneEditAdminTextField;
    @FXML private TextField addressEditAdminTextField;
    @FXML private Button editAdminDataButton;
    @FXML private Button upgradeAdminDataButton;

    @FXML private CheckBox showPasswordCheckBox;
    @FXML private PasswordField txtContrasena;
    @FXML private TextField txtFieldContrasena1;
    @FXML private PasswordField txtNuevaContrasena1;
    @FXML private PasswordField txtNuevaContrasena2;
    @FXML private Label lblMensaje;
    @FXML private Button cancelEditAdminPasswordButton;
    @FXML private Button saveAdminPasswordButton;
    @FXML private Button editAdminPasswordButton;
    
    @FXML private TextField txtNombres1;
    @FXML private TextField txtApellidos1;
    @FXML private TextField txtDNI1;
    @FXML private TextField txtDireccion1;
    @FXML private TextField txtTelefono1;
    
    @FXML private TextField txtNombres2;
    @FXML private TextField txtApellidos2;
    @FXML private TextField txtDNI2;
    @FXML private TextField txtDireccion2;
    @FXML private TextField txtTelefono2;
    
    @FXML private PasswordField txtVerificarContrasena;
    @FXML private Label lblMensajeVerificar;

    private String dateOfToday(){
        Calendar calendario = Calendar.getInstance();
        
        int day = calendario.get(Calendar.DAY_OF_MONTH);
        int month = 1+calendario.get(Calendar.MONTH);
        int year = calendario.get(Calendar.YEAR);
        
        return (year+"-"+month+"-"+day);
    }
    
    private void initializeAdminData(){
        conector = new ConnectionDataBase();
        conector.searchDataUserOfTheTurn("3"); 
        adminUserData = conector.dataUserFound(); 
    }
    
    private void initializePanelAdminData(){
        nickNameAdminTextField.setText(adminUserData.getLogin_user());
        surnameAdminTextField.setText(adminUserData.getSurnames_user());
        nameAdminTextField.setText(adminUserData.getName_user());
        dniAdminTextField.setText(adminUserData.getDni_user());
        phoneAdminTextField.setText(adminUserData.getPhone_user());
        addressAdminTextField.setText(adminUserData.getAddress_user());
    }
    
    @FXML
    private void signOffFirstAdminButton(ActionEvent event){
        conector.close();
        InterfaceLoginExecutor llamadoLogin = new InterfaceLoginExecutor(event);
    }
    
    private void initializePanelDataOfToday(){
        User userFirstTurn = dataUserFirstTurn();
        User userSecondTurn = dataUserSecondTurn();
        
        nameUserFirstTurnAdminTextField.setText(userFirstTurn.getName_user());
        nameUserSecondTurnAdminTextField.setText(userSecondTurn.getName_user());
        
        initializeCashRegisterOfToday(userFirstTurn,userSecondTurn);
        
        initializeTheIncidentsFirstTableOfTodayOfA(userFirstTurn);
        initializeTheIncidentsSecondTableOfTodayOfA(userSecondTurn);
        
        initializeTheCommentsFirstTableOfTodayOfA(userFirstTurn);
        initializeTheCommentsSecondTableOfTodayOfA(userSecondTurn);
    }
    
    private User dataUserFirstTurn(){
        conector.searchDataUserOfTheTurn("1");
        return conector.dataUserFound();
    }
    
    private User dataUserSecondTurn(){
        conector.searchDataUserOfTheTurn("2");
        return conector.dataUserFound();
    }
    
    private void initializeCashRegisterOfToday(User userFirstTurn,User userSecondTurn){
        cashRegisterFirstUserAdminTextField.setText("S/1500.00");
        float costFirstTurn = conector.costTurnUser(userFirstTurn.getId_user(), dateOfToday());
        float balance = 1500-costFirstTurn;
        cashRegisterSecondUserAdminTextField.setText("S/"+balance);
        float costSecondTurn = conector.costTurnUser(userSecondTurn.getId_user(), dateOfToday());
        balance = 1500 - costFirstTurn - costSecondTurn;
        balanceDateAdminTextField.setText("S/"+balance);
    }
    
    private void initializeTheIncidentsFirstTableOfTodayOfA(User userFirstTurn){
        conector.incidentUserTurn(userFirstTurn.getTurn_user(),dateOfToday());
        
        incidentFirstUserAdminColumn.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        costFirstUserAdminColumn.setCellValueFactory(new PropertyValueFactory<>("cost_incident"));
        incidentsFirstUserAdminObservableList = FXCollections.observableArrayList();
        incidentsFirstUserAdminObservableList.setAll(conector.eventsTurnTable());
        incidentsFirstUserAdminTable.setItems(incidentsFirstUserAdminObservableList);
    }
    
    private void initializeTheIncidentsSecondTableOfTodayOfA(User userSecondTurn){
        conector.incidentUserTurn(userSecondTurn.getTurn_user(),dateOfToday());
        
        incidentSecondUserAdminColumn.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        costSecondUserAdminColumn.setCellValueFactory(new PropertyValueFactory<>("cost_incident"));
        incidentsSecondUserAdminObservableList = FXCollections.observableArrayList();
        incidentsSecondUserAdminObservableList.setAll(conector.eventsTurnTable());
        incidentsSecondUserAdminTable.setItems(incidentsSecondUserAdminObservableList);
    }
    
    private void initializeTheCommentsFirstTableOfTodayOfA(User userFirstTurn){
        conector.commentaryUser(userFirstTurn.getTurn_user(),dateOfToday());
        
        commentaryFirstUserAdminColumn.setCellValueFactory(new PropertyValueFactory<>("commentary_incident"));
        commentaryFirstUserAdminObservableList = FXCollections.observableArrayList();
        commentaryFirstUserAdminObservableList.setAll(conector.commentaryTurnTable());
        commentaryFirstUserAdminTable.setItems(commentaryFirstUserAdminObservableList);
    }
    
    private void initializeTheCommentsSecondTableOfTodayOfA(User userSecondTurn){
        conector.commentaryUser(userSecondTurn.getTurn_user(),dateOfToday());
        
        commentarySecondUserAdminColumn.setCellValueFactory(new PropertyValueFactory<>("commentary_incident"));
        commentarySecondUserAdminObservableList = FXCollections.observableArrayList();
        commentarySecondUserAdminObservableList.setAll(conector.commentaryTurnTable());
        commentarySecondUserAdminTable.setItems(commentarySecondUserAdminObservableList);
    }
    
    @FXML
    private void ButtonInsertCommentFirstTurnAdmin(){
        conector.searchDataUserOfTheTurn("1");
        User firstUser = conector.dataUserFound();
        
        if(!commentFirstTurnAdminTextField.getText().equals("")){
            String commentary_incident = "Admin "+ adminUserData.getName_user()+": "+ commentFirstTurnAdminTextField.getText();
            conector.insertComment(firstUser.getId_user(),dateOfToday(),commentary_incident);
            this.commentFirstTurnAdminTextField.setText("");
        }
        initializeData();
    }
    
    @FXML
    private void ButtonInsertCommentSecondTurnAdmin(){
        conector.searchDataUserOfTheTurn("2");
        User secondUser = conector.dataUserFound();
        
        if(!commentSecondTurnAdminTextField.getText().equals("")){
            String commentary_incident = "Admin "+ adminUserData.getName_user()+": "+ commentSecondTurnAdminTextField.getText();
            conector.insertComment(secondUser.getId_user(),dateOfToday(),commentary_incident);
            this.commentSecondTurnAdminTextField.setText("");
        }
        initializeData();
    }
    
    public void initializarYearTable(){
        conector.setSummaryYear();
        nameYearBalanceAdminColumn.setCellValueFactory(new PropertyValueFactory<>("number_year"));
        summaryBalanceAdminColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));
        summaryYearAdminObservableList = FXCollections.observableArrayList();
        summaryYearAdminObservableList.setAll(conector.summaryYearTable());
        summaryYearAdminTable.setItems(summaryYearAdminObservableList);
    }
    
    private final ListChangeListener<SummaryYear> pickedYearTable =
        (ListChangeListener.Change<? extends SummaryYear>c) -> {
            yearPicked();
    };
    
    public void yearPicked(){
        if(summaryYearAdminTable != null){
            List<SummaryYear> tabla = summaryYearAdminTable.getSelectionModel().getSelectedItems();
            if(tabla.size() == 1 ){
                final SummaryYear yearSelect = tabla.get(0);
                yearPicked = yearSelect.getNumber_year();
                summaryDayAdminTable.setItems(null);
                initializarMonthTable();
            }
        }
    }
    
    public void initializarMonthTable(){
        conector.setSummaryMonth(yearPicked);
        nombreMesCL.setCellValueFactory(new PropertyValueFactory<>("month"));
        montoMesCL.setCellValueFactory(new PropertyValueFactory<>("summary"));
        summaryMonthAdminObservableList = FXCollections.observableArrayList();
        summaryMonthAdminObservableList.setAll(conector.summaryMonthTable());
        summaryMonthAdminTable.setItems(summaryMonthAdminObservableList); // muestra los resultados en la tabla
    }
    
    private final ListChangeListener<SummaryMonth> pickedMonthTable =
        (ListChangeListener.Change<? extends SummaryMonth>c) -> {
            posicionTablaMesSeleccionada();
    };
    
    public void posicionTablaMesSeleccionada(){
        if(summaryMonthAdminTable != null){
            List<SummaryMonth> tabla = summaryMonthAdminTable.getSelectionModel().getSelectedItems();
            if(tabla.size() == 1 ){
                final SummaryMonth mesSeleccionado = tabla.get(0);
                monthPicked = mesSeleccionado.getNumeroMes();
                inicializarTablaDia();
                //inicializarTablaSemana();
            }
        }
    }
    
    private void inicializarTablaSemana(){
        nombreSemanaCL.setCellValueFactory(new PropertyValueFactory<>("Semana"));
        montoSemanaCL.setCellValueFactory(new PropertyValueFactory<>("Balance"));
        summaryWeekAdminObservableList = FXCollections.observableArrayList();
        summaryWeekAdminTable.setItems(summaryWeekAdminObservableList); // muestra los resultados en la tabla
        //semanaBalances.setAll(conector2.llenadoBalanceSemana());
    }
    
    private final ListChangeListener<SemanaBalance> pickedWeekTable =
        (ListChangeListener.Change<? extends SemanaBalance>c) -> {
        // posicionTablaSemanaSeleccionada();
    };
    
    public void posicionTablaSemanaSeleccionada(){
        if(summaryWeekAdminTable != null){
            List<SemanaBalance> tabla = summaryWeekAdminTable.getSelectionModel().getSelectedItems();
            if(tabla.size() == 1 ){
                final SemanaBalance semanaSeleccionado = tabla.get(0);
                posicionSemana = semanaSeleccionado.getNumeroSemana();
                //inicializarTablaDia(yearPicked,monthPicked);
            }
        }
    }
    
    private void inicializarTablaDia(){
        conector.setSummaryDay(yearPicked,monthPicked);
        nombreDiaCL.setCellValueFactory(new PropertyValueFactory<>("day"));
        montoDiaCL.setCellValueFactory(new PropertyValueFactory<>("summary"));
        summaryDayAdminObservableList = FXCollections.observableArrayList();
        summaryDayAdminObservableList.setAll(conector.summaryDayTable());
        summaryDayAdminTable.setItems(summaryDayAdminObservableList); // muestra los resultados en la tabla
    }
    
    private final ListChangeListener<SummaryDay> pickedDayTable =
        (ListChangeListener.Change<? extends SummaryDay>c) -> {
            posicionTablaDiaSeleccionada();
    };
    
    public void posicionTablaDiaSeleccionada(){
        if(summaryDayAdminTable != null){
            List<SummaryDay> tabla = summaryDayAdminTable.getSelectionModel().getSelectedItems();
            if(tabla.size() == 1 ){
                final SummaryDay diaSeleccionado = tabla.get(0);
                dayPicked = diaSeleccionado.getNumDia(); 
                initializePanelDataOfADate();
            }
        }
    }
    
    public void initializePanelDataOfADate(){
        datePickedStatisticAdminTextField.setText(datePicked());

        cashRegisterWhitDate();
        
        dataFirstTurnPickedWhitDate();
        incidentsFirstTurnPickedWhitDate();
        commentsFirstTurnPickedWhitDate();
        
        dataSecondTurnPickedWhitDate();
        incidentsSecondTurnPickedWhitDate();
        commentsSecondTurnPickedWhitDate();
    }
    
    private String datePicked(){
        return yearPicked+"-"+monthPicked+"-"+dayPicked;
    }
    
    private void cashRegisterWhitDate(){
        ConnectionDataBase auxiliarConector = new ConnectionDataBase();
        auxiliarConector.incidentStatisticUserTurn("1",datePicked());
        User userFirstTurn = auxiliarConector.dataUserFound();
        cashRegisterFirstTurnAdminTextField.setText("1500");
        float costFirstTurn = conector.costTurnUser(userFirstTurn.getId_user(), datePicked());
        float balance = 1500-costFirstTurn;
        cashRegisterSecondTurnAdminTextField.setText("S/"+balance);
        
        auxiliarConector.incidentStatisticUserTurn("2",datePicked());
        User userSecondTurn = auxiliarConector.dataUserFound();
        float costSecondTurn = conector.costTurnUser(userSecondTurn.getId_user(), datePicked());
        float newbalance = 1500 - costFirstTurn -costSecondTurn;
        balanceWithDateAdminTextField.setText("S/"+balance);
    }
    
    private void dataFirstTurnPickedWhitDate(){
        ConnectionDataBase auxiliarConector = new ConnectionDataBase();
        auxiliarConector.incidentStatisticUserTurn("1",datePicked());
        User userFirstTurn = auxiliarConector.dataUserFound();
        nameFirstTurnAdminTextField.setText(userFirstTurn.getName_user());
        surnameFirstTurnAdminTextField.setText(userFirstTurn.getSurnames_user());
        phoneFirstTurnAdminTextField.setText(userFirstTurn.getPhone_user());
        addressFirstTurnAdminTextField.setText(userFirstTurn.getAddress_user());
        dniFirstTurnAdminTextField.setText(userFirstTurn.getDni_user());        
        //balance1TF.setText(" "+turnoBalances.get(0).getBalance());
        //cashRegisterFirstTurnAdminTextField.setText("S/1500.00");
    }
    
    private void incidentsFirstTurnPickedWhitDate(){
        ConnectionDataBase auxiliarConector = new ConnectionDataBase();
        auxiliarConector.incidentStatisticEventTurn("1",datePicked());
        incidentFirstTurnAdminColumn.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        costFirstTurnAdminColumn.setCellValueFactory(new PropertyValueFactory<>("cost_incident"));
        incidentsFirstTurnAdminObservableList = FXCollections.observableArrayList();
        incidentsFirstTurnAdminObservableList.setAll(auxiliarConector.eventsTurnTable());
        incidentsFirstTurnAdminTable.setItems(incidentsFirstTurnAdminObservableList);
    }
    
    private void commentsFirstTurnPickedWhitDate(){
        ConnectionDataBase auxiliarConector = new ConnectionDataBase();
        auxiliarConector.commentStatisticEventTurn("1", datePicked());
        commentaryFirstTurnAdminColumn.setCellValueFactory(new PropertyValueFactory<>("commentary_incident"));
        commentaryFirstTurnAdminObservableList = FXCollections.observableArrayList();
        commentaryFirstTurnAdminObservableList.setAll(auxiliarConector.commentaryTurnTable());
        commentaryFirstTurnAdminTable.setItems(commentaryFirstTurnAdminObservableList);
    }
    
    private void dataSecondTurnPickedWhitDate(){
        ConnectionDataBase auxiliarConector = new ConnectionDataBase();
        auxiliarConector.incidentStatisticUserTurn("2", datePicked());
        User userSecondTurn = auxiliarConector.dataUserFound();
        
        nameSecondTurnAdminTextField.setText(userSecondTurn.getName_user());
        surnameSecondTurnAdminTextField.setText(userSecondTurn.getSurnames_user());
        phoneSecondTurnAdminTextField.setText(userSecondTurn.getPhone_user());
        addressSecondTurnAdminTextField.setText(userSecondTurn.getAddress_user());
        dniSecondTurnAdminTextField.setText(userSecondTurn.getDni_user()); 
        //balance2TF.setText("1500.0");
        //balance2TF.setText(" "+turnoBalances.get(1).getBalance());
        //cashRegisterSecondUserAdminTextField.setText("S/1500.00");
    }
    
    private void incidentsSecondTurnPickedWhitDate(){
        ConnectionDataBase auxiliarConector = new ConnectionDataBase();
        auxiliarConector.incidentStatisticEventTurn("2", datePicked());
        incidentSecondTurnAdminColumn.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        costSecondTurnAdminColumn.setCellValueFactory(new PropertyValueFactory<>("cost_incident"));
        incidentsSecondTurnAdminObservableList = FXCollections.observableArrayList();
        incidentsSecondTurnAdminObservableList.setAll(auxiliarConector.eventsTurnTable());
        incidentsSecondTurnAdminTable.setItems(incidentsSecondTurnAdminObservableList);
    }
    
    private void commentsSecondTurnPickedWhitDate(){
        ConnectionDataBase auxiliarConector = new ConnectionDataBase();
        auxiliarConector.commentStatisticEventTurn("2", datePicked());
        commentarySecondTurnAdminColumn.setCellValueFactory(new PropertyValueFactory<>("commentary_incident"));
        commentarySecondTurnAdminObservableList = FXCollections.observableArrayList();
        commentarySecondTurnAdminObservableList.setAll(auxiliarConector.commentaryTurnTable());
        commentarySecondTurnAdminTable.setItems(commentarySecondTurnAdminObservableList);
    }
  
    private void initializeEditDataAdmin(){
        dataAdmin();
        dataFirstTurnUser();
        dataSecondTurnUser();
    }
    
    private void dataAdmin(){
        nameEditAdminTextField.setText(adminUserData.getName_user());
        surnameEditAdminTextField.setText(adminUserData.getSurnames_user());
        dniEditAdminTextField.setText(adminUserData.getDni_user());
        addressEditAdminTextField.setText(adminUserData.getAddress_user());
        phoneEditAdminTextField.setText(adminUserData.getPhone_user());
    }
    
    private void dataFirstTurnUser(){
        conector.searchDataUserOfTheTurn("1");
        User userFirstTurn = conector.dataUserFound();

        txtNombres1.setText(userFirstTurn.getName_user());
        txtApellidos1.setText(userFirstTurn.getSurnames_user());
        txtDNI1.setText(userFirstTurn.getDni_user());
        txtDireccion1.setText(userFirstTurn.getAddress_user());
        txtTelefono1.setText(userFirstTurn.getPhone_user());
    }
    
    private void dataSecondTurnUser(){
        conector.searchDataUserOfTheTurn("2");
        User userSecondTurn = conector.dataUserFound();
        
        txtNombres2.setText(userSecondTurn.getName_user());
        txtApellidos2.setText(userSecondTurn.getSurnames_user());
        txtDNI2.setText(userSecondTurn.getDni_user());
        txtDireccion2.setText(userSecondTurn.getAddress_user());
        txtTelefono2.setText(userSecondTurn.getPhone_user());
    }
    
    private void editAdminTextFieldsIsDisable(boolean bool){
        nameEditAdminTextField.setDisable(bool);
        surnameEditAdminTextField.setDisable(bool);
        dniEditAdminTextField.setDisable(bool);
        phoneEditAdminTextField.setDisable(bool);
        addressEditAdminTextField.setDisable(bool);
    }
    
    private void editAdminButtonsIsDisable(boolean bool){
        editAdminDataButton.setDisable(bool);
        upgradeAdminDataButton.setDisable(!bool);
    }
    
    @FXML
    private void ButtonEditAdminData(){
        editAdminTextFieldsIsDisable(false);
        editAdminButtonsIsDisable(true);
    }

    @FXML
    private void ButtonUpgradeAdminData(){
        editAdminTextFieldsIsDisable(true);
        conector.editDataUser(adminUserData.getId_user(),nameEditAdminTextField.getText(), 
                surnameEditAdminTextField.getText(), dniEditAdminTextField.getText(), 
                addressEditAdminTextField.getText(), phoneEditAdminTextField.getText());
        editAdminButtonsIsDisable(false);
        initializeData();            
    }
    
    @FXML
    private void ButtonEditAdminPassword(){
        elementsPasswordEditAdminIsDisable(false);
        clearDataPasswordEditAdmin();
    }
    
    private void elementsPasswordEditAdminIsDisable(boolean bool){
        showPasswordCheckBox.setDisable(bool);
        txtContrasena.setDisable(bool);
        txtFieldContrasena1.setDisable(bool);
        txtNuevaContrasena1.setDisable(bool);
        txtNuevaContrasena2.setDisable(bool);
        cancelEditAdminPasswordButton.setDisable(bool);
        saveAdminPasswordButton.setDisable(bool);
        editAdminPasswordButton.setDisable(!bool);
    }
    
    private void clearDataPasswordEditAdmin(){
        txtContrasena.setText("");
        txtFieldContrasena1.setText("");
        txtNuevaContrasena1.setText("");
        txtNuevaContrasena2.setText("");
        lblMensaje.setText("");
    }
    
    @FXML
    private void ButtonCancelEditAdminPassword(){
        elementsPasswordEditAdminIsDisable(true);
        clearDataPasswordEditAdmin();
    }
    
    @FXML
    private void ButtonSaveAdminPassword() {
        if(isPasswordCorrect()){
            if (isConfirmPasswordEquals()){
                conector.editPasswordUser(adminUserData.getId_user(), txtNuevaContrasena1.getText());
                elementsPasswordEditAdminIsDisable(true);
                clearDataPasswordEditAdmin();
            } else {
                lblMensaje.setText("La contraseña no coincide");
            }
        } else {
            lblMensaje.setText("Contraseña incorrecta");
        }
    }
    
    private boolean isPasswordCorrect(){
        return (!showPasswordCheckBox.isSelected() && txtContrasena.getText().equals(adminUserData.getPassword_user()))
                || (showPasswordCheckBox.isSelected() && txtFieldContrasena1.getText().equals(adminUserData.getPassword_user()));
    }
    
    private boolean isConfirmPasswordEquals(){
        return txtNuevaContrasena1.getText().equals(txtNuevaContrasena2.getText());
    }

    @FXML
    private void showAdminPasswordCheckBox(){
        if (showPasswordCheckBox.isSelected()) {
            showPassword(true);
        } else {
            showPassword(false);
        }
    }
    
    private void showPassword(boolean bool){
        txtFieldContrasena1.setVisible(bool);
        txtFieldContrasena1.setText(txtContrasena.getText());
        txtContrasena.setVisible(!bool);
    }

    @FXML
    private void btnModificarDato1(){
        txtNombres1.setDisable(false);
        txtApellidos1.setDisable(false);
        txtDNI1.setDisable(false);
        txtTelefono1.setDisable(false);
        txtDireccion1.setDisable(false);
    }
    
    @FXML
    private void btnModificarDato2(){
        txtNombres2.setDisable(false);
        txtApellidos2.setDisable(false);
        txtDNI2.setDisable(false);
        txtTelefono2.setDisable(false);
        txtDireccion2.setDisable(false);
    }
    
    @FXML
    private void btnGuardarDatoUsuario(){
        /*if(txtVerificarContrasena.getText().equals(u.getContrasena())){
           
            txtNombres1.setDisable(true);
            txtApellidos1.setDisable(true);
            txtDNI1.setDisable(true);
            txtDireccion1.setDisable(true);
            txtTelefono1.setDisable(true);
            user1.ModificarUsuario(1, txtApellidos1.getText(), txtNombres1.getText(),
                    txtDNI1.getText(), txtDireccion1.getText(), txtTelefono1.getText());

            txtNombres2.setDisable(true);
            txtApellidos2.setDisable(true);
            txtDNI2.setDisable(true);
            txtDireccion2.setDisable(true);
            txtTelefono2.setDisable(true);
            user2.ModificarUsuario(2, txtApellidos2.getText(), txtNombres2.getText(),
            txtDNI2.getText(), txtDireccion2.getText(), txtTelefono2.getText());
            
            lblMensajeVerificar.setText("");
            txtVerificarContrasena.setText("");
        }
        else
            lblMensajeVerificar.setText("Contraseña incorrecta");*/
    }   
    
    private void initializeObservableLists(){
        summaryYearAdminObservableList = summaryYearAdminTable.getSelectionModel().getSelectedItems();
        summaryMonthAdminObservableList = summaryMonthAdminTable.getSelectionModel().getSelectedItems();
        summaryWeekAdminObservableList = summaryWeekAdminTable.getSelectionModel().getSelectedItems();
        summaryDayAdminObservableList = summaryDayAdminTable.getSelectionModel().getSelectedItems();
    }
    
    private void initalizePointsObservableList(){
        summaryYearAdminObservableList.addListener(pickedYearTable);
        summaryMonthAdminObservableList.addListener(pickedMonthTable);
        summaryWeekAdminObservableList.addListener(pickedWeekTable);
        summaryDayAdminObservableList.addListener(pickedDayTable);
    }
    
    private void initializeData(){
        initializeAdminData();
        initializePanelAdminData();
        initializePanelDataOfToday();
        initializeEditDataAdmin();
    }

    private void initalizeStatisticsTable(){
        initializarYearTable();
        initializeObservableLists();
        initalizePointsObservableList();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeData();
        initalizeStatisticsTable();
    }
}