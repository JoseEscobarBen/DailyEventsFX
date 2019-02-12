package Interface_Admin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceAdminExecutor  {
    public InterfaceAdminExecutor (ActionEvent event){
        try {
            Parent main_parent = FXMLLoader.load(getClass().getResource("InterfazAdministrador.fxml"));
            Scene main_scene = new Scene(main_parent);
            Stage main_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            main_stage.setScene(main_scene);
            main_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceAdminExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
