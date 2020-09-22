package sample;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Receive extends JFrame implements Runnable {
    JFileChooser fileChooser;
    ServerSocket serverSocket;
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;
    byte bytes[];

    public Receive() throws Exception{
        bytes =new byte[100];
        fileChooser =new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        serverSocket =new ServerSocket(8000);
        toRun();
    }
    public void toRun() throws Exception{
        while(true){
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            String string="You have received an image,would you like to save it?";
            JOptionPane.showMessageDialog(this,string,null,JOptionPane.INFORMATION_MESSAGE);
            fileChooser.showSaveDialog(this);
            File file= this.fileChooser.getSelectedFile();
            outputStream =new FileOutputStream(file);
            Thread thread=new Thread(this);
            thread.start();
        }
    }

    public void run() {
        try{
            while (true){
                int n = inputStream.read();
                if (n==-1) break;
                outputStream.write(n);
            }
            inputStream.close();
            outputStream.close();
            socket.close();
            JOptionPane.showMessageDialog(null,"\nYour encrypted image file has been uploaded successfully\n","message",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
