import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.io.*;
import java.util.*;

public class YourQueries {
    JFrame fr;
    JLabel emp_id;
    JTextField emp_id_text;
    JTextArea fillForm;
    JScrollPane SPane;
    public YourQueries(){
        fr = new JFrame("Query");
        fr.setLayout(null);
        emp_id = new JLabel("Enter your employee ID : ");
        emp_id.setBounds(50,30,200,30);
        fr.add(emp_id);
        emp_id_text = new JTextField();
        emp_id_text.setBounds(200,30,100,30);
        fr.add(emp_id_text);
        fillForm = new JTextArea("Please Write Your Query Here");
        SPane = new JScrollPane(fillForm);
        SPane.setBounds(50,100,300,200);
        fr.add(SPane);
        fr.setSize(400,400);
        fr.setVisible(true);
    }
    public static void main(String[] args) {
        new YourQueries();
    }
}
