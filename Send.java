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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Send extends Component implements Initializable,Runnable {
    @Override
    public void initialize(URL location, ResourceBundle resources){
        thread =new Thread(this);
        thread.start();

        fileChooser=new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }
    @FXML
    private Label exit_label;

    @FXML
    private TextField text_send;

    @FXML
    private Button btn_load;

    @FXML
    private Button btn_share;

    @FXML
    private Button btn_back;

    JFileChooser fileChooser;
    File f, temporaryFile, outputFilename, saveFilename;
    InetAddress ip;
    String address;
    int openedComponent;
    Thread thread;

    public void run() {
        try{
            new Receive();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e,null,JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    void handleButtonClicks(ActionEvent event) throws IOException{
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
               int r=fileChooser.showOpenDialog(this);
               temporaryFile =fileChooser.getSelectedFile(); //File type

               if(r==JFileChooser.CANCEL_OPTION)
                   JOptionPane.showMessageDialog(this,"you have not selected a file","ERROR",JOptionPane.ERROR_MESSAGE);
               else{
                   String name= temporaryFile.getName();

                   if((!name.endsWith(".jpg")) && (!name.endsWith(".gif")) && (!name.endsWith(".bmp")) && (!name.endsWith(".jpeg")) && (!name.endsWith(".png")))
                       JOptionPane.showMessageDialog(this,"Only image files are allowed","ERROR",JOptionPane.ERROR_MESSAGE);

                   else{
                       openedComponent =1;
                       outputFilename = temporaryFile;
                       text_send.setText(name);
                       text_send.setText(temporaryFile.getPath());
                   }
               }
           }
           if(event.getSource()==btn_share)
           {
               address=JOptionPane.showInputDialog("please enter an ip for receiver");
               ip =InetAddress.getByName(address);
               Socket socket=new Socket(ip,8000);
               DataOutputStream out=new DataOutputStream(socket.getOutputStream());
               FileInputStream in=new FileInputStream(outputFilename);
               while(true) {
                   int i=in.read();
                   if(i==-1) break;
                   out.write(i);
               }
               in.close();
               out.close();
           }
       }
       catch (Exception ex)
       {
           JOptionPane.showMessageDialog(this,ex,"ERRORr",JOptionPane.ERROR_MESSAGE);
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
