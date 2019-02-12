package Interface_User1;

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

public class InterfazUsuario1Controller implements Initializable {
    
    private final ConnectionDataBase conector = new ConnectionDataBase();
    private User firstUserData = null;
    
    // Panel data User
    @FXML private TextField nickNameFirstUserTextField;
    @FXML private TextField surnameFirstUserTextField;
    @FXML private TextField nameFirstUserTextField;
    @FXML private TextField dniFirstUserTextField;
    @FXML private TextField phoneFirstUserTextField;
    @FXML private TextField addressFirstUserTextField;
    
    // Panel Incidents
    @FXML private TextField turnFirstUserTextField;
    @FXML private TextField dateIncidentFirstUserTextField;
    @FXML private TextField cashRegisterFirstUserTextField;
    @FXML private TextField incidentFirstUserTextField; 
    @FXML private TextField costIncidentFirstUserTextField;
    @FXML private TextField commentaryFirstUserTextField;
    @FXML private TextField costFirstTurnUserTextField;
    @FXML private TextField balanceTurnFirstUserTextField;
    
    @FXML private TableView<Incident> incidentsFirstUserTable;
    @FXML private TableColumn incidentFirstUserColumn;
    @FXML private TableColumn costFirstUserColumn;
    ObservableList<Incident> incidentsFirstUserObservableList;
    
    @FXML private TableView<CommentaryTurn> commentaryFirstUserTable;
    @FXML private TableColumn commentaryFirstUserColumn;
    ObservableList<CommentaryTurn> commentaryFirstUserObservableList;
 
    // Panel Edit User
    @FXML private TextField nameEditFirstUserTextField;
    @FXML private TextField surnameEditFirstUserTextField;
    @FXML private TextField dniEditFirstUserTextField;
    @FXML private TextField phoneEditFirstUserTextField;
    @FXML private TextField addressEditFirstUserTextField;
    
    @FXML private TextField passwordUserTextField;
    @FXML private PasswordField passwordFirstUserPasswordField;
    @FXML private PasswordField newPasswordFirstUserPasswordField;
    @FXML private PasswordField confirmNewPasswordFirstUserPasswordField;
    
    @FXML private CheckBox showPasswordFirstUserCheckBox;
    
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
        TextFields.bindAutoCompletion(incidentFirstUserTextField,conector.productsList());
    }
    
    public void initializeCostBalanceFirstTurnData(){
        float cost_turn = conector.costTurnUser(firstUserData.getId_user(), setDate());
        costFirstTurnUserTextField.setText(""+cost_turn);
        float balance = 1500 - cost_turn;
        String balanceTurn = "S/"+balance;
        balanceTurnFirstUserTextField.setText(balanceTurn);
    }
    
    //Initializador Data User Connection------------------------------------------------------------------------
    private void initializeFirstUserData(){
        conector.searchDataUserOfTheTurn("1"); //crea el query 
        firstUserData = conector.dataUserFound(); // llena los datos obtenidos
    }
    //END Initizador Data User Connection-----------------------------------------------------------------------
    
    //Panel Data User -----------------------------------------------------------------------------------------
    private void initializePanelDataFirstUser(){
        nickNameFirstUserTextField.setText(firstUserData.getLogin_user());
        surnameFirstUserTextField.setText(firstUserData.getSurnames_user());
        nameFirstUserTextField.setText(firstUserData.getName_user());
        dniFirstUserTextField.setText(firstUserData.getDni_user());
        phoneFirstUserTextField.setText(firstUserData.getPhone_user());
        addressFirstUserTextField.setText(firstUserData.getAddress_user());
    }
    
    @FXML
        private void ButtonUserSingOff(ActionEvent event) throws IOException{
        InterfaceLoginExecutor llamadoLogin = new InterfaceLoginExecutor(event);
    }
    //END Panel Data User---------------------------------------------------------------------------------------
    
    //Panel Events First User ---------------------------------------------------------------------------------
    private void initializePanelFirstUserEvents(){
        
        turnFirstUserTextField.setText(firstUserData.getTurn_user());
        dateIncidentFirstUserTextField.setText(setDate());
        cashRegisterFirstUserTextField.setText("S/1500");
        
        conector.incidentUserTurn(firstUserData.getTurn_user(),setDate()); // Query incident
        
        incidentFirstUserColumn.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        costFirstUserColumn.setCellValueFactory(new PropertyValueFactory<>("cost_incident"));
        incidentsFirstUserObservableList = FXCollections.observableArrayList();
        incidentsFirstUserObservableList.setAll(conector.eventsTurnTable());
        incidentsFirstUserTable.setItems(incidentsFirstUserObservableList);
        
        conector.commentaryUser(firstUserData.getTurn_user(),setDate()); // Query comments
        
        commentaryFirstUserColumn.setCellValueFactory(new PropertyValueFactory<>("commentary_incident"));
        commentaryFirstUserObservableList = FXCollections.observableArrayList();
        commentaryFirstUserObservableList.setAll(conector.commentaryTurnTable());
        commentaryFirstUserTable.setItems(commentaryFirstUserObservableList);
    }
        
    @FXML
    private void ButtonEnterIncidentFirstUser(ActionEvent event) throws Exception{
        if(!incidentFirstUserTextField.getText().equals("") && isNumeric(costIncidentFirstUserTextField.getText())){
            String name_product = incidentFirstUserTextField.getText();
            float cost_incident = Float.parseFloat(costIncidentFirstUserTextField.getText());
            conector.insertEvent(firstUserData.getId_user(), setDate(),name_product,cost_incident);
            incidentFirstUserTextField.setText("");
            costIncidentFirstUserTextField.setText("");
            initializeData(); 
        }
        else{
            System.out.println("Datos mal ingresados");
        }
    }
    
    @FXML
    private void ButtonEnterCommentaryFirstUser(ActionEvent event) throws Exception{
        if(!commentaryFirstUserTextField.getText().equals("")){
            String commentary_incident = this.commentaryFirstUserTextField.getText();
            conector.insertComment(firstUserData.getId_user(),setDate(),commentary_incident);
            initializeData();
            this.commentaryFirstUserTextField.setText("");
        }
        else{
            System.out.println("Datos mal ingresados");
        }
    }
    //END Panel Events User-------------------------------------------------------------------------------------
    
    //Panel Edit Data User-------------------------------------------------------------------------------------
    private void initializePanelEditFirstUser(){
        surnameEditFirstUserTextField.setText(firstUserData.getSurnames_user());
        nameEditFirstUserTextField.setText(firstUserData.getName_user());
        dniEditFirstUserTextField.setText(firstUserData.getDni_user());
        phoneEditFirstUserTextField.setText(firstUserData.getPhone_user());
        addressEditFirstUserTextField.setText(firstUserData.getAddress_user());
    }
    
    @FXML
    private void ButtonEditDataFirstUser(ActionEvent event) throws Exception {
        this.nameEditFirstUserTextField.setDisable(false);
        this.surnameEditFirstUserTextField.setDisable(false);
        this.dniEditFirstUserTextField.setDisable(false);
        this.addressEditFirstUserTextField.setDisable(false);
        this.phoneEditFirstUserTextField.setDisable(false);
    }

    @FXML
    private void ButtonSaveDataFirstUser(ActionEvent event) throws Exception {
        this.nameEditFirstUserTextField.setDisable(true);
        this.surnameEditFirstUserTextField.setDisable(true);
        this.dniEditFirstUserTextField.setDisable(true);
        this.addressEditFirstUserTextField.setDisable(true);
        this.phoneEditFirstUserTextField.setDisable(true);
        
        conector.editDataUser(this.firstUserData.getId_user(), nameEditFirstUserTextField.getText(), surnameEditFirstUserTextField.getText(),
                dniEditFirstUserTextField.getText(), addressEditFirstUserTextField.getText(), phoneEditFirstUserTextField.getText());
        
        initializeData();
    }

    @FXML
    private void ButtonEditPasswordFirstUser(ActionEvent event) throws Exception {
        this.showPasswordFirstUserCheckBox.setDisable(false);
        this.passwordFirstUserPasswordField.setDisable(false);
        this.newPasswordFirstUserPasswordField.setDisable(false);
        this.confirmNewPasswordFirstUserPasswordField.setDisable(false);
    }

    @FXML
    private void ButtonSavePasswordFirstUser(ActionEvent event) throws Exception {
        if (passwordFirstUserPasswordField.getText().equals(firstUserData.getPassword_user())){
            if (newPasswordFirstUserPasswordField.getText().equals(confirmNewPasswordFirstUserPasswordField.getText())) {
                passwordFirstUserPasswordField.setDisable(true);
                passwordUserTextField.setDisable(true);
                newPasswordFirstUserPasswordField.setDisable(true);
                confirmNewPasswordFirstUserPasswordField.setDisable(true);
                messagePasswordAutenticationLabel.setText("");
                conector.editPasswordUser(firstUserData.getId_user(), newPasswordFirstUserPasswordField.getText());
            } else {
                messagePasswordAutenticationLabel.setText("La contraseña no coincide");
            }

        } else {
            messagePasswordAutenticationLabel.setText("Contraseña incorrecta");
        }
    }

    @FXML
    private void showPassword(ActionEvent event) throws Exception {
        if (showPasswordFirstUserCheckBox.isSelected()) {
            String passwordUser = passwordFirstUserPasswordField.getText();
            passwordFirstUserPasswordField.setVisible(false);
            passwordUserTextField.setText(passwordUser);
            passwordUserTextField.setVisible(true);
        } else {
            String passwordUser = passwordUserTextField.getText();
            passwordUserTextField.setVisible(false);
            passwordFirstUserPasswordField.setText(passwordUser);
            passwordFirstUserPasswordField.setVisible(true);
        }
    }
    //END Panel Edit Data User----------------------------------------------------------------------------------
    
    private void initializeData(){
        initializeFirstUserData();
        initializePanelDataFirstUser();
        initializePanelFirstUserEvents();
        initializePanelEditFirstUser();
        initializeAutocompletTextField();
        initializeCostBalanceFirstTurnData();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeData();
    }
}