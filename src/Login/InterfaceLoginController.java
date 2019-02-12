package Login;

import Database.ConnectionDataBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Interface_Admin.InterfaceAdminExecutor;
import Interface_User1.InterfaceUser1Executor;
import Interface_User2.InterfaceUser2Executor;
import Objects.User;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.PasswordField;

public class InterfaceLoginController implements Initializable {
    
    ConnectionDataBase connectionDataLoginUser = new ConnectionDataBase();
    List<User> dataUsers = new ArrayList<>();
    
    @FXML private Label messageLabel;
    @FXML private TextField idUserTextField;
    @FXML private PasswordField passwordUserPasswordField;

    @FXML
    void ButtonLogin(ActionEvent event) throws Exception{
        if (idUserTextField.getText().equals(dataUsers.get(0).getLogin_user()) && passwordUserPasswordField.getText().equals(dataUsers.get(0).getPassword_user())){
            ((Node) (event.getSource())).getScene().getWindow().hide();
            InterfaceUser1Executor user1Executor = new InterfaceUser1Executor(event);
        }
        else if (idUserTextField.getText().equals(dataUsers.get(1).getLogin_user()) && passwordUserPasswordField.getText().equals(dataUsers.get(1).getPassword_user())){
            ((Node) (event.getSource())).getScene().getWindow().hide();
            InterfaceUser2Executor user2Executor = new InterfaceUser2Executor(event);
        }
        else if(idUserTextField.getText().equals(dataUsers.get(2).getLogin_user()) && passwordUserPasswordField.getText().equals(dataUsers.get(2).getPassword_user())){
            ((Node) (event.getSource())).getScene().getWindow().hide();
            InterfaceAdminExecutor adminExcecutor = new InterfaceAdminExecutor(event);
        }
        else
            messageLabel.setText("Usuario o contraseña invalido");
    }
    
    public void initializeData(){
        connectionDataLoginUser.searchDataUserOfTheTurn("1");
        dataUsers.add(0,connectionDataLoginUser.dataUserFound());
        connectionDataLoginUser.searchDataUserOfTheTurn("2");
        dataUsers.add(1,connectionDataLoginUser.dataUserFound());
        connectionDataLoginUser.searchDataUserOfTheTurn("3");
        dataUsers.add(2,connectionDataLoginUser.dataUserFound());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeData();
    }    
}
