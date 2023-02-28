
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class EmployeeInformation {
    JFrame f1;
    JPanel p1,p2,p3,p4;
    JTabbedPane tp;
    JScrollPane Spane;
    JLabel l1, l2, l3, l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10,tf11,tf12,tf13,tf14;
    JScrollPane sp1;
    JButton savebtn,resetbtn,editbtn1,editbtn2,deletebtn,viewbtn;
    JTable tableEmployees;
    EmployeeInformation(){
    f1=new JFrame("Form");
    int w = Toolkit.getDefaultToolkit().getScreenSize().width;
    int h = Toolkit.getDefaultToolkit().getScreenSize().height;
    f1.setLocation(w/2-250, h/2-200);
    f1.setSize(500,400);
    p1=new JPanel(new GridLayout(7,2));
    p2=new JPanel(new GridLayout(7,2));
    p3=new JPanel();
    p4=new JPanel();
    tp=new JTabbedPane();
    l1=new JLabel("Employee ID:");
    l2=new JLabel("Employee Name:");
    l3=new JLabel("Employee Email:");
    l11=new JLabel("Gender:");
    l14=new JLabel("Phone Number:");
    l4=new JLabel("Designation:");
    l5=new JLabel("Enter Employee ID to delete:");
    l5.setBounds(0, 2, 481, 72);

    l7=new JLabel("Employee ID:");
    l8=new JLabel("Employee Name:");
    l9=new JLabel("Employee Email:");
    l10=new JLabel("Gender:");
    l12=new JLabel("Phone Number:");
    l13=new JLabel("Designation:");

    tf1=new JTextField(12);
    tf2=new JTextField(12);
    tf3=new JTextField(12);
    tf4=new JTextField(12);
    tf5=new JTextField(12);
    tf5.setBounds(175, 12, 265, 54);
    tf6=new JTextField(12);
    tf7=new JTextField(12);
    tf8=new JTextField(12);
    tf9=new JTextField(12);
    tf10=new JTextField(12);
    tf11=new JTextField(12);
    tf12=new JTextField(12);
    tf13=new JTextField(12);
    tf14=new JTextField(12);
    savebtn=new JButton(" Add ");
    resetbtn=new JButton(" Reset");
    editbtn1=new JButton(" Edit ");
    editbtn2=new JButton(" Save");
    deletebtn=new JButton("Delete");
    deletebtn.setBounds(132, 84, 160, 44);
    viewbtn=new JButton("View");
    viewbtn.setLocation(142, 35);
    
    p1.add(l1);
    p1.add(tf1);
    p1.add(l2);
    p1.add(tf2);
    p1.add(l3);
    p1.add(tf3);
    p1.add(l14);
    p1.add(tf14);
    p1.add(l11);
    p1.add(tf11);
    p1.add(l4);
    p1.add(tf4);
    p1.add(savebtn);
    p1.add(resetbtn);

    p2.add(l7);
    p2.add(tf7);
    p2.add(l8);
    p2.add(tf8);
    p2.add(l9);
    p2.add(tf9);
    p2.add(l10);
    p2.add(tf10);
    p2.add(l12);
    p2.add(tf12);
    p2.add(tf12);
    p2.add(l13);
    p2.add(tf13);
    p2.add(editbtn1);
    p2.add(editbtn2);
    p3.setLayout(null);

    p3.add(l5);
    p3.add(tf5);
    p3.add(deletebtn);
    resetbtn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae){
    tf1.setText("");
    tf2.setText("");
    tf3.setText("");
    tf4.setText("");
    tf11.setText("");
    tf14.setText("");
    }
    });
    p4.setLayout(null);
    viewbtn.setSize(183,55);
    p4.add(viewbtn);
    viewbtn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent a1){
            tableEmployees = new JTable();
            JFrame viewEmployeesFrame = new JFrame("View Employees");
                viewEmployeesFrame.setSize(600, 400);
                viewEmployeesFrame.setLocationRelativeTo(null);
                // viewEmployeesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                // Create a table model and set it as the model for the table
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.setColumnIdentifiers(new String[] {"ID", "Name", "Email", "Gender", "Phone Number", "Designation"});
                tableEmployees.setModel(tableModel);

                // Add the table to the frame
                viewEmployeesFrame.getContentPane().add(new JScrollPane(tableEmployees));

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/***", "***", "***");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
                    while (rs.next()) {
                        tableModel.addRow(new Object[] {rs.getInt("id"), rs.getString("name"), rs.getString("email"), 
                                                       rs.getString("gender"), rs.getString("phoneNum"), rs.getString("designation") });
                    }
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // Display the frame
                viewEmployeesFrame.setVisible(true);
    }
    });
    savebtn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae){
    String value1=tf1.getText();
    String value2=tf2.getText();
    String value3=tf3.getText();
    String value4=tf4.getText();
    String value14=tf14.getText();
    String value11=tf11.getText();
    String value5=tf5.getText();
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/";
    String db = "sys";
    String driver = "com.mysql.cj.jdbc.Driver";
    String user = "***";
    String pass = "***";
    System.out.println(value1+value2+value3+value4+value11+value14+value5);
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url+db, user, pass);
    PreparedStatement st=con.prepareStatement("INSERT INTO employees (id, name, email, gender, phoneNum, designation) VALUES (?, ?, ?, ?, ?, ?)");
    st.setString(1,value1);
    st.setString(2,value2);
    st.setString(3,value3);
    st.setString(4,value11);
    st.setString(5,value14);
    st.setString(6,value4);
    st.executeUpdate();
    JOptionPane.showMessageDialog(p1,"Data is successfully inserted into database.");
    con.close();
    }
    catch(Exception e){
    JOptionPane.showMessageDialog(p1,"Error in submitting data!");
    }
    }
    });

    deletebtn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae){
    String value1=tf5.getText();
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/";
    String db = "sys";
    String driver = "com.mysql.cj.jdbc.Driver";
    String user = "***";
    String pass = "***";
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url+db, user, pass);
    PreparedStatement st=con.prepareStatement("DELETE FROM employees WHERE id = ?");
    st.setString(1,value1);
    st.executeUpdate();
    JOptionPane.showMessageDialog(p3,"Record is deleted successfully.");
    con.close();
    }
    catch(Exception exp3)
    {
        exp3.printStackTrace();
    JOptionPane.showMessageDialog(p3,"Error in deleting record.");
    }
    }
    });
    editbtn1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae){

    String value=tf7.getText();
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/";
    String db = "sys";
    String driver = "com.mysql.cj.jdbc.Driver";
    String user = "***";
    String pass = "***";
    try{
    Class.forName(driver);
    con = DriverManager.getConnection(url+db, user, pass);
    PreparedStatement st=con.prepareStatement("select * from employees where id=?");
    st.setString(1,value);
    ResultSet res=st.executeQuery();
    res.next();
    tf7.setText(Integer.toString(res.getInt(1)));
    tf8.setText(res.getString(2));
    tf9.setText(res.getString(3));
    tf10.setText(res.getString(4));
    tf12.setText(res.getString(5));
    tf13.setText(res.getString(6));
    con.close();
    }
    catch(Exception e)
    {
    JOptionPane.showMessageDialog(p2,"Can not edit data");
    }
    }
    });
    editbtn2.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae){
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/";
    String db = "sys";
    String driver = "com.mysql.cj.jdbc.Driver";
    String user = "***";
    String pass = "***";
    try
    {
    int x=JOptionPane.showConfirmDialog(p2,"Confirm edit? All data will be replaced");
    if(x==0){
    try{
    String value1=tf7.getText();
    String value2=tf8.getText();
    String value3=tf9.getText();
    String value4=tf10.getText();
    String value5=tf12.getText();
    String value6=tf13.getText();

    Class.forName(driver);
    con = DriverManager.getConnection(url+db, user, pass);;
    Statement st=con.createStatement();
    st.executeUpdate("update employees set name='"+value2+"', email='"+value3+"',gender='"+value4+"',phoneNum = '"+value5+"',designation = '"+value6+"' where id='"+value1+"'");
    JOptionPane.showMessageDialog(p2,"Updated successfully");
    con.close();
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    JOptionPane.showMessageDialog(p2,"Error in updating edit fields");
    }
    }
    }
    catch(Exception ex)
    {
    JOptionPane.showMessageDialog(p2,"Error");
    }
    }
    });
    }
    void dis()
    {
    f1.getContentPane().add(tp);
    tp.addTab("Add Record",p1);
    tp.addTab("Update Record",p2);
    tp.addTab("Delete Record",p3);
    tp.addTab("View Record",p4);

    f1.setSize(500,500);
    f1.setVisible(true);
    f1.setResizable(true);
    }
    public static void main(String z[]){
        EmployeeInformation pro=new EmployeeInformation();
                pro.dis();   
        }
    }

