package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        encrypt_radio.setToggleGroup(group);
        //encrypt_radio.setSelected(true);
        decrypt_radio.setToggleGroup(group);
        send_radio.setToggleGroup(group);

    }

    @FXML
    private Label exit_label;

    @FXML
    private RadioButton encrypt_radio;

    @FXML
    private RadioButton decrypt_radio;

    @FXML
    private RadioButton send_radio;

    @FXML
    private Button btn_exit;

    @FXML
    void handleButtonClicks(ActionEvent event) throws IOException {
        if(event.getSource()==encrypt_radio)
        {
            Parent view = FXMLLoader.load(getClass().getResource("encryption.fxml"));
            Scene scene = new Scene(view);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==decrypt_radio)
        {
            Parent view = FXMLLoader.load(getClass().getResource("decryption.fxml"));
            Scene scene = new Scene(view);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==send_radio)
        {
            Parent view = FXMLLoader.load(getClass().getResource("send.fxml"));
            Scene scene = new Scene(view);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        if(event.getSource()==btn_exit)
        {
            int r = JOptionPane.showConfirmDialog(null,"sure to exit",null,JOptionPane.YES_NO_OPTION);
            if(r==JOptionPane.YES_OPTION)
                System.exit(0);
        }
    }

    @FXML
    void handleMouseClicks(MouseEvent event) {
        if(event.getSource()==exit_label)
        {
            int r = JOptionPane.showConfirmDialog(null,"sure to exit",null,JOptionPane.YES_NO_OPTION);
            if(r==JOptionPane.YES_OPTION)
                System.exit(0);
        }
    }
}
