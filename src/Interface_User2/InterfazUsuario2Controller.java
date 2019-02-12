package Interface_User2;

import Database.ConnectionDataBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.Calendar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Login.InterfaceLoginExecutor;
import Objects.CommentaryTurn;
import Objects.Incident;
import Objects.User;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;

public class InterfazUsuario2Controller implements Initializable {
    
    private final ConnectionDataBase conector = new ConnectionDataBase();
    private User secondUserData = null;
    
    // Panel data User
    @FXML private TextField nickNameSecondUserTextField;
    @FXML private TextField surnameSecondUserTextField;
    @FXML private TextField nameSecondUserTextField;
    @FXML private TextField dniSecondUserTextField;
    @FXML private TextField phoneSecondUserTextField;
    @FXML private TextField addressSecondUserTextField;
    
    // Panel Incidents
    @FXML private TextField turnSecondUserTextField;
    @FXML private TextField dateIncidentSecondUserTextField;
    @FXML private TextField cashRegisterSecondUserTextField;
    @FXML private TextField incidentSecondUserTextField; 
    @FXML private TextField costIncidentSecondUserTextField;
    @FXML private TextField commentarySecondUserTextField;
    @FXML private TextField costSecondTurnUserTextField;
    @FXML private TextField balanceTurnSecondUserTextField;
    
    @FXML private TableView<Incident> incidentsSecondUserTable;
    @FXML private TableColumn incidentSecondUserColumn;
    @FXML private TableColumn costSecondUserColumn;
    ObservableList<Incident> incidentsSecondUserObservableList;
    
    @FXML private TableView<CommentaryTurn> commentarySecondUserTable;
    @FXML private TableColumn commentarySecondUserColumn;
    ObservableList<CommentaryTurn> commentarySecondUserObservableList;
 
    // Panel Edit User
    @FXML private TextField nameEditSecondUserTextField;
    @FXML private TextField surnameEditSecondUserTextField;
    @FXML private TextField dniEditSecondUserTextField;
    @FXML private TextField phoneEditSecondUserTextField;
    @FXML private TextField addressEditSecondUserTextField;
    
    @FXML private TextField passwordUserTextField;
    @FXML private PasswordField passwordSecondUserPasswordField;
    @FXML private PasswordField newPasswordSecondUserPasswordField;
    @FXML private PasswordField confirmNewPasswordSecondUserPasswordField;
    
    @FXML private CheckBox showPasswordSecondUserCheckBox;
    
    @FXML private Label messagePasswordAutenticationLabel;
    
    private String setDate(){
        Calendar calendario = Calendar.getInstance();
        
        int day = calendario.get(Calendar.DAY_OF_MONTH);
        int month = 1+calendario.get(Calendar.MONTH);
        int year = calendario.get(Calendar.YEAR);
        
        return (year+"-"+month+"-"+day);
    }
    
    public static boolean isNumeric(String string){  
        try{  
            double number = Double.parseDouble(string);  
        }  
        catch(NumberFormatException nfe){  
            return false;  
        }  
        return true;  
    }
    
    public void initializeAutocompletTextField(){
        TextFields.bindAutoCompletion(incidentSecondUserTextField,conector.productsList());
    }
    
    public void initializeCostBalanceSecondTurnData(){
        ConnectionDataBase conectorAux = new ConnectionDataBase();
        conectorAux.searchDataUserOfTheTurn("1");
        User firstTurnUser = conectorAux.dataUserFound();
        float cost_first_turn = conector.costTurnUser(firstTurnUser.getId_user(), setDate());
        
        
        float cost_second_turn = conector.costTurnUser(this.secondUserData.getId_user(), setDate());
        this.costSecondTurnUserTextField.setText(""+cost_second_turn);
        float balance = 1500 - cost_first_turn - cost_second_turn;
        String balanceTurn = "S/"+balance;
        this.balanceTurnSecondUserTextField.setText(balanceTurn);
    }
    
    //Initializador Data User Connection------------------------------------------------------------------------
    private void initializeSecondUserData(){
        conector.searchDataUserOfTheTurn("2"); //crea el query 
        secondUserData = conector.dataUserFound(); // llena los datos obtenidos
    }
    //END Initizador Data User Connection-----------------------------------------------------------------------
    
    //Panel Data User -----------------------------------------------------------------------------------------
    private void initializePanelDataSecondUser(){
        nickNameSecondUserTextField.setText(secondUserData.getLogin_user());
        surnameSecondUserTextField.setText(secondUserData.getSurnames_user());
        nameSecondUserTextField.setText(secondUserData.getName_user());
        dniSecondUserTextField.setText(secondUserData.getDni_user());
        phoneSecondUserTextField.setText(secondUserData.getPhone_user());
        addressSecondUserTextField.setText(secondUserData.getAddress_user());
    }
    
    @FXML
    private void btnCerrarSesion(ActionEvent event) throws IOException{
        InterfaceLoginExecutor llamadoLogin = new InterfaceLoginExecutor(event);
    }
    //END Panel Data User---------------------------------------------------------------------------------------
    
    //Panel Events First User ---------------------------------------------------------------------------------
    private void initializePanelSecondUserEvents(){
        
        turnSecondUserTextField.setText(secondUserData.getTurn_user());
        dateIncidentSecondUserTextField.setText(setDate());
        
        ConnectionDataBase conectorAux = new ConnectionDataBase();
        conectorAux.searchDataUserOfTheTurn("1");
        User firstTurnUser = conectorAux.dataUserFound();
        float cost_first_turn = conector.costTurnUser(firstTurnUser.getId_user(), setDate());
        Float balance_first_turn = 1500 - cost_first_turn;
        cashRegisterSecondUserTextField.setText("S/"+balance_first_turn);
        
        conector.incidentUserTurn(secondUserData.getTurn_user(),setDate()); // Query incident
        
        incidentSecondUserColumn.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        costSecondUserColumn.setCellValueFactory(new PropertyValueFactory<>("cost_incident"));
        incidentsSecondUserObservableList = FXCollections.observableArrayList();
        incidentsSecondUserObservableList.setAll(conector.eventsTurnTable());
        incidentsSecondUserTable.setItems(incidentsSecondUserObservableList);
        
        conector.commentaryUser(secondUserData.getTurn_user(),setDate()); // Query comments
        
        commentarySecondUserColumn.setCellValueFactory(new PropertyValueFactory<>("commentary_incident"));
        commentarySecondUserObservableList = FXCollections.observableArrayList();
        commentarySecondUserObservableList.setAll(conector.commentaryTurnTable());
        commentarySecondUserTable.setItems(commentarySecondUserObservableList);
    }
        
    @FXML
    private void ButtonEnterIncidentSecondUser(ActionEvent event) throws Exception{
        if(!incidentSecondUserTextField.getText().equals("") && isNumeric(costIncidentSecondUserTextField.getText())){
            String name_product = incidentSecondUserTextField.getText();
            float cost_incident = Float.parseFloat(costIncidentSecondUserTextField.getText());
            conector.insertEvent(secondUserData.getId_user(), setDate(),name_product,cost_incident);
            incidentSecondUserTextField.setText("");
            costIncidentSecondUserTextField.setText("");
            initializeData(); 
        }
        else{
            System.out.println("Datos mal ingresados");
        }
    }
    
    @FXML
    private void ButtonEnterCommentarySecondUser(ActionEvent event) throws Exception{
        if(!commentarySecondUserTextField.getText().equals("")){
            String commentary_incident = this.commentarySecondUserTextField.getText();
            conector.insertComment(secondUserData.getId_user(),setDate(),commentary_incident);
            initializeData();
            this.commentarySecondUserTextField.setText("");
        }
        else{
            System.out.println("Datos mal ingresados");
        }
    }
    //END Panel Events User-------------------------------------------------------------------------------------
    
    //Panel Edit Data User-------------------------------------------------------------------------------------
    private void initializePanelEditSecondUser(){
        surnameEditSecondUserTextField.setText(secondUserData.getSurnames_user());
        nameEditSecondUserTextField.setText(secondUserData.getName_user());
        dniEditSecondUserTextField.setText(secondUserData.getDni_user());
        phoneEditSecondUserTextField.setText(secondUserData.getPhone_user());
        addressEditSecondUserTextField.setText(secondUserData.getAddress_user());
    }
    
    @FXML
    private void ButtonEditDataSecondUser(ActionEvent event) throws Exception {
        this.nameEditSecondUserTextField.setDisable(false);
        this.surnameEditSecondUserTextField.setDisable(false);
        this.dniEditSecondUserTextField.setDisable(false);
        this.addressEditSecondUserTextField.setDisable(false);
        this.phoneEditSecondUserTextField.setDisable(false);
    }

    @FXML
    private void ButtonSaveDataSecondUser(ActionEvent event) throws Exception {
        this.nameEditSecondUserTextField.setDisable(true);
        this.surnameEditSecondUserTextField.setDisable(true);
        this.dniEditSecondUserTextField.setDisable(true);
        this.addressEditSecondUserTextField.setDisable(true);
        this.phoneEditSecondUserTextField.setDisable(true);
        
        conector.editDataUser(this.secondUserData.getId_user(), nameEditSecondUserTextField.getText(), surnameEditSecondUserTextField.getText(),
                dniEditSecondUserTextField.getText(), addressEditSecondUserTextField.getText(), phoneEditSecondUserTextField.getText());
        
        initializeData();
    }

    @FXML
    private void ButtonEditPasswordSecondUser(ActionEvent event) throws Exception {
        this.showPasswordSecondUserCheckBox.setDisable(false);
        this.passwordSecondUserPasswordField.setDisable(false);
        this.newPasswordSecondUserPasswordField.setDisable(false);
        this.confirmNewPasswordSecondUserPasswordField.setDisable(false);
    }

    @FXML
    private void ButtonSavePasswordSecondUser(ActionEvent event) throws Exception {
        if (passwordSecondUserPasswordField.getText().equals(secondUserData.getPassword_user())){
            if (newPasswordSecondUserPasswordField.getText().equals(confirmNewPasswordSecondUserPasswordField.getText())) {
                passwordSecondUserPasswordField.setDisable(true);
                passwordUserTextField.setDisable(true);
                newPasswordSecondUserPasswordField.setDisable(true);
                confirmNewPasswordSecondUserPasswordField.setDisable(true);
                messagePasswordAutenticationLabel.setText("");
                conector.editPasswordUser(secondUserData.getId_user(), newPasswordSecondUserPasswordField.getText());
            } else {
                messagePasswordAutenticationLabel.setText("La contraseña no coincide");
            }

        } else {
            messagePasswordAutenticationLabel.setText("Contraseña incorrecta");
        }
    }

    @FXML
    private void showPassword(ActionEvent event) throws Exception {
        if (showPasswordSecondUserCheckBox.isSelected()) {
            String passwordUser = passwordSecondUserPasswordField.getText();
            passwordSecondUserPasswordField.setVisible(false);
            passwordUserTextField.setText(passwordUser);
            passwordUserTextField.setVisible(true);
        } else {
            String passwordUser = passwordUserTextField.getText();
            passwordUserTextField.setVisible(false);
            passwordSecondUserPasswordField.setText(passwordUser);
            passwordSecondUserPasswordField.setVisible(true);
        }
    }
    //END Panel Edit Data User----------------------------------------------------------------------------------
    
    private void initializeData(){
        initializeSecondUserData();
        initializePanelDataSecondUser();
        initializePanelSecondUserEvents();
        initializePanelEditSecondUser();
        initializeAutocompletTextField();
        initializeCostBalanceSecondTurnData();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeData();
    }
}