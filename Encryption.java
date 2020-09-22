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
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class Encryption extends JFrame implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openedComponent=0;
        encryptComponent=0;

        fileChooser =new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    JFileChooser fileChooser;
    File temporaryFilename, outputFilename, saveFilename;
    int openedComponent, encryptComponent;
    String encryptionKey;

    @FXML
    private Label exit_label;

    @FXML
    private TextField encrypt_text;

    @FXML
    private Button btn_load;
    @FXML
    private TextArea text_area;

    @FXML
    private Button btn_encrypt;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_back;

    @FXML
    void handleButtonClicks(ActionEvent event) throws IOException {
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
            if(event.getSource()==btn_save)
            {
                if(openedComponent ==1 && encryptComponent ==1) {
                    fileChooser.showSaveDialog(this);
                    saveFilename = fileChooser.getSelectedFile();
                    FileInputStream in=new FileInputStream("java.jpg");
                    FileOutputStream out=new FileOutputStream(saveFilename);

                    outputFilename = saveFilename;
                    encrypt_text.setEditable(true);
                    encrypt_text.setText(saveFilename.getPath());

                    while(true) {
                        int i=in.read();
                        if(i==-1) break;
                        out.write(i);
                    }

                    in.close();
                    out.close();
                    JOptionPane.showMessageDialog(null,"\nimage file has been encrypted and saved successfully\n",null,JOptionPane.INFORMATION_MESSAGE);

                } else{
                    String message;
                    if(openedComponent ==0)
                        message="file has not been opened";
                    else if(encryptComponent ==0)
                        message="file has not been encrypted";
                    else
                        message="file has not been decrypted";

                    JOptionPane.showMessageDialog(this,message,"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                encrypt_text.clear();
            }
            if(event.getSource()==btn_encrypt)
            {
                if(openedComponent ==1) {

                    encryptionKey =JOptionPane.showInputDialog("please enter a 4 digit key to encrypt");

                    //String type
                    if(encryptionKey ==null) {
                        JOptionPane.showMessageDialog(this,"only a 4 digit key is allowed","ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                    if(encryptionKey.trim().length()<4) {
                        JOptionPane.showMessageDialog(this,"only a 4 digit key is allowed","ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                    if(encryptionKey.trim().length()>4)
                        JOptionPane.showMessageDialog(this,"only a 4 digit key is allowed","ERROR",JOptionPane.ERROR_MESSAGE);

                    else
                    {
                        // encrypt the message

                        int key=Integer.parseInt(encryptionKey);
                        encryptImage(text_area.getText(), outputFilename,key);
                        encryptComponent =1;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"file is not opened","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                text_area.clear();
            }
            if(event.getSource()==btn_load)
            {
                int r= fileChooser.showOpenDialog(this);
                temporaryFilename = fileChooser.getSelectedFile(); //File type

                if(r==JFileChooser.CANCEL_OPTION)
                    JOptionPane.showMessageDialog(this,"file has not been selected","ERROR",JOptionPane.ERROR_MESSAGE);
                else
                {
                    String name= temporaryFilename.getName();

                    if((!name.endsWith(".jpg")) && (!name.endsWith(".gif")) && (!name.endsWith(".bmp")) && (!name.endsWith(".jpeg")) && (!name.endsWith(".png")))
                        JOptionPane.showMessageDialog(this,"Only image files are allowed","ERROR",JOptionPane.ERROR_MESSAGE);

                    else
                    {
                        openedComponent =1;
                        outputFilename = temporaryFilename;
                        encrypt_text.setText(name);
                        encrypt_text.setText(temporaryFilename.getPath());
                    }
                }
            }

        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void handleMouseClicks(MouseEvent event) throws IOException{
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
