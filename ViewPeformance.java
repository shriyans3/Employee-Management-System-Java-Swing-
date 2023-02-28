import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.io.*;
import java.util.*;


public class ViewPeformance {
    JFrame f;
    JButton addperbtn,viewbtn,deletebtn;
    JTable pertable;
    JLabel id,task,delete;
    JTextField id_text,task_text,dele_text;


    ViewPeformance(){
        f = new JFrame("View Performance");
        f.setSize(400,300);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        id = new JLabel("Emp_id");
        id.setBounds(180,10,100,30);
        id_text = new JTextField();
        id_text.setBounds(250,10,100,30);
        task = new JLabel("task_comp");
        task.setBounds(180,80,100,30);
        task_text = new JTextField();
        task_text.setBounds(250,80,100,30);
        delete = new JLabel("Enter id to delete");
        delete.setBounds(170,200,100,30);
        dele_text = new JTextField();
        dele_text.setBounds(280,200,100,30);
        f.add(id);
        f.add(id_text);
        f.add(task);
        f.add(task_text);
        f.add(delete);
        f.add(dele_text);
        viewbtn = new JButton("View Performance");
        addperbtn = new JButton("Add Performance");
        addperbtn.setBounds(10,50,150,30);
        viewbtn.setBounds(10,130,150,30);
        deletebtn = new JButton("Delete Performance");
        deletebtn.setBounds(10,200,150,30);
        f.add(addperbtn);
        f.add(viewbtn);
        f.add(deletebtn);
        f.setVisible(true);
        addperbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String emp_id = id_text.getText();
                String task_comp = task_text.getText();
                Connection con = null;
                String url = "jdbc:mysql://localhost:3306/";
                String db = "sys";
                String driver = "com.mysql.cj.jdbc.Driver";
                String user = "***";
                String pass = "***";
                try{
                    Class.forName(driver);
                    con = DriverManager.getConnection(url+db,user,pass);
                    PreparedStatement ps = con.prepareStatement("insert into performance(id,tasks_comp) values(?,?)");
                    ps.setString(1,emp_id);
                    ps.setString(2,task_comp);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Performance Added");
                }
                catch(Exception ex1){
                    System.out.println(ex1);
                    JOptionPane.showMessageDialog(null, "Error Occured");
                }
            }
        });
        viewbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e1){
                pertable = new JTable();
                JFrame viewPer = new JFrame("Performance Table");
                viewPer.setSize(400,300);
                DefaultTableModel perftable = new DefaultTableModel();
                perftable.setColumnIdentifiers(new String[]{"id","tasks_comp"});
                pertable.setModel(perftable);

                viewPer.getContentPane().add(new JScrollPane(pertable));
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","***","***");
                    PreparedStatement ps = con.prepareStatement("select * from performance");
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        perftable.addRow(new Object[]{rs.getString("id"),rs.getString("tasks_comp")});
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                viewPer.setVisible(true);
            }
        });
        deletebtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e2){
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","***","***");
                    PreparedStatement ps = con.prepareStatement("delete from performance where id = ?");
                    ps.setString(1,dele_text.getText());
                    System.out.println(dele_text.getText());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Deleted");
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                
                
        }
        });
    }
    public static void main(String[] args) {
        new ViewPeformance();
    }
}
