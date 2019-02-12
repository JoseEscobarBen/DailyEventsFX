package Interface_User1;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceUser1Executor  {
    public InterfaceUser1Executor (ActionEvent event) throws IOException{
        Parent main_parent = 
                FXMLLoader.load(getClass().getResource("InterfazUsuario1.fxml"));
        Scene main_scene = new Scene(main_parent);
        
        Stage main_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
}
