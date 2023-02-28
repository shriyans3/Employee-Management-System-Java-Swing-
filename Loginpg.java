import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.io.*;
import java.util.*;

public class Loginpg implements ActionListener{
    JFrame f;
    JPanel p;
    JLabel username;
    JLabel password,message;
    JTextField usertxt;
    JPasswordField passtxt;
    JButton login,cancel;
    public Loginpg(){
        f = new JFrame("Login");
        f.setLayout(null);
        username = new JLabel("Username : ");
        password = new JLabel("Password : ");
        usertxt = new JTextField();
        passtxt = new JPasswordField();
        message = new JLabel();
        login = new JButton("Submit");
        cancel = new JButton("Cancel");
        username.setBounds(20, 40, 150, 30);
        password.setBounds(20, 80, 150, 30);
        usertxt.setBounds(120, 40, 200, 30);
        passtxt.setBounds(120, 80, 200, 30);
        login.setBounds(120, 120, 90, 30);
        cancel.setBounds(220, 120, 90, 30);
        message.setBounds(150, 160, 190, 30);
        f.add(username);
        f.add(password);
        f.add(usertxt);
        f.add(passtxt);
        f.add(message);
        f.add(login);
        f.add(cancel);
        login.addActionListener(this);
        cancel.addActionListener(this);
        login.setActionCommand("Login");
        cancel.setActionCommand("Cancel");
        f.setSize(450,250);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand() == "Login"){
            if(usertxt.getText().equals("admin") && passtxt.getText().equals("admin@123")){
                message.setText("Login " + usertxt.getText() + " Successful");
                // System.out.println("yes");
                new Mainpg(); // This is the next page
            }
            else{
                message.setText("Login " + usertxt.getText() + " Failed");
                System.out.println("No");
            }
        }
        if(e.getActionCommand() == "Cancel"){
            usertxt.setText("");
            passtxt.setText("");
        }
        
    }
    public static void main(String[] args) {
        new Loginpg();
    }
}
