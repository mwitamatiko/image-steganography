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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class Decryption extends JFrame implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openedComponent = 0;
        decryptComponent = 0;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        byte b[];
        int len;

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    JFileChooser fileChooser;
    File temporaryFilename, outputFilename;
    int openedComponent, decryptComponent;
    String decryptionKey;

    @FXML
    private Label exit_label;

    @FXML
    private TextField decrypt_text;

    @FXML
    private Button btn_load;

    @FXML
    private TextArea text_area2;

    @FXML
    private Button btn_decrypt;

    @FXML
    private Button btn_back;

    @FXML
    void handleButtonClicks(ActionEvent event){
       try
       {
           if(event.getSource()==btn_back)
           {
               Parent view = FXMLLoader.load(getClass().getResource("home.fxml"));
               Scene scene = new Scene(view);
               Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
               stage.setScene(scene);
               stage.show();
           }
           if(event.getSource()==btn_load)
           {
               int r = fileChooser.showOpenDialog(this);
               temporaryFilename = fileChooser.getSelectedFile(); //File type
               if (r == JFileChooser.CANCEL_OPTION)
                   JOptionPane.showMessageDialog(this,"file has not been selected","ERROR",JOptionPane.ERROR_MESSAGE);
               else {
                   String name = temporaryFilename.getName();

                   if((!name.endsWith(".jpg")) && (!name.endsWith(".gif")) && (!name.endsWith(".bmp")) && (!name.endsWith(".jpeg")) && (!name.endsWith(".png")))
                       JOptionPane.showMessageDialog(this,"Only image files are allowed","ERROR",JOptionPane.ERROR_MESSAGE);

                   else {
                       openedComponent = 1;
                       outputFilename = temporaryFilename;
                       decrypt_text.setEditable(true);
                       decrypt_text.setText(temporaryFilename.getPath());
                   }

               }
           }
           if(event.getSource()==btn_decrypt)
           {
               if (openedComponent == 1) {
                   decryptionKey = JOptionPane.showInputDialog("please enter 4 digit key to decrypt");
                   //String type
                   if (decryptionKey.trim().equals(""))
                       JOptionPane.showMessageDialog(this, "input is invalid", "ERROR", JOptionPane.ERROR_MESSAGE);
                   else {
                       // decrypt the message
                       int key = Integer.parseInt(decryptionKey);
                       decryptImage(outputFilename, key);
                       decryptComponent = 1;
                   }
               } else {
                   JOptionPane.showMessageDialog(this, "file is not opened", "ERROR", JOptionPane.ERROR_MESSAGE);
               }
           }
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE);
       }

    }

    @FXML
    void handleMouseClicks(MouseEvent event) throws IOException {
        if(event.getSource()==exit_label)
        {
            Parent view = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(view);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
}
