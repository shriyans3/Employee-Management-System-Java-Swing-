import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.sql.*;
import java.io.*;
import java.util.*;

public class AddProject{
    JFrame projFrame;
    JPanel s1,s2,s3;
    JTabbedPane tab;
    JLabel id,proj_id,dept_id,proj_name,del_id;
    JTextField id_text,proj_text,dept_text,proj_name_text,del_id_text;
    JButton addbtn,clearbtn,viewbtn,delbtn;
    JTable projtable;
    JComboBox projName;
    AddProject(){
        projFrame = new JFrame();
        projFrame.setSize(500,400);
        s1 = new JPanel(new GridLayout(5,2));
        s2 = new JPanel();
        s3 = new JPanel();
        tab = new JTabbedPane(JTabbedPane.LEFT,JTabbedPane.WRAP_TAB_LAYOUT);
        id = new JLabel("Employee ID");
        dept_id = new JLabel("Department ID");
        proj_id = new JLabel("Project ID");
        proj_name = new JLabel("Project Name");
        String[] names = {"C++","Java","Python","System Design","Flutter","C#","Go Lang","DataBase","FrontEnd","Rust","Rest API"};
        projName = new JComboBox(names);
        del_id = new JLabel("Emp_ID to Delete");
        del_id.setBounds(0,2,301,59);
        id_text = new JTextField();
        dept_text = new JTextField();
        proj_text = new JTextField();
        // proj_name_text = new JTextField();
        del_id_text = new JTextField();
        del_id_text.setBounds(131,14,170,36);
        addbtn = new JButton("Add");
        clearbtn = new JButton("Clear");
        viewbtn = new JButton("View");
        delbtn = new JButton("Delete");
        delbtn.setBounds(57,71,170,47);
        viewbtn.setBounds(49,41,178,62);
        s1.add(id);
        s1.add(id_text);
        s1.add(dept_id);
        s1.add(dept_text);
        s1.add(proj_id);
        s1.add(proj_text);
        s1.add(proj_name);
        s1.add(projName);
        s1.add(addbtn);
        s1.add(clearbtn);
        s2.setLayout(null);

        s2.add(viewbtn);
        s3.setLayout(null);
        s3.add(del_id);
        s3.add(del_id_text);
        s3.add(delbtn);
        

        addbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String emp_id = id_text.getText();
                String dept_id = dept_text.getText();
                String proj_id = proj_text.getText();
                // String proj_name = proj_name_text.getText();
                Connection con = null;
                String url = "jdbc:mysql://localhost:3306/";
                String db = "sys";
                String driver = "com.mysql.cj.jdbc.Driver";
                String user = "***";
                String pass = "***";
                try{
                    Class.forName(driver);
                    con = DriverManager.getConnection(url+db, user, pass);
                    PreparedStatement st=con.prepareStatement("INSERT INTO project (id,proj_id,dept_id,proj_name) VALUES (?, ?, ?, ?)");
                    st.setString(1, emp_id);
                    st.setString(2, proj_id);
                    st.setString(3, dept_id);
                    st.setString(4, (String) projName.getSelectedItem());
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Project Added Successfully");
                    con.close();
                }
                catch(Exception ex){
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Error Occured");
                }
            }
        });
        clearbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                id_text.setText("");
                dept_text.setText("");
                proj_text.setText("");
                proj_name_text.setText("");
            }
        });
        viewbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                projtable = new JTable();
                JFrame viewProj = new JFrame("View Projects");
                viewProj.setSize(600,400);
                DefaultTableModel projModel = new DefaultTableModel();
                projModel.setColumnIdentifiers(new String[]{"ID","Project ID","Department ID","Project Name"});
                projtable.setModel(projModel);

                viewProj.getContentPane().add(new JScrollPane(projtable));
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","***","***");
                    PreparedStatement ps = con.prepareStatement("select * from project");
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        projModel.addRow(new Object[]{rs.getString("id"),rs.getString("proj_id"),rs.getString("dept_id"),rs.getString("proj_name")});
                    }
                }
                catch(Exception ex){
                    System.out.println(ex);
                }
                viewProj.setVisible(true);
            }
        });
        delbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","***","***");
                    PreparedStatement ps = con.prepareStatement("delete from project where id = ?");
                    ps.setString(1,del_id_text.getText());
                    System.out.println(del_id_text.getText());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Deleted");
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }
        });
    }
    void Dis(){
        projFrame.getContentPane().add(tab);
        tab.addTab("Add Project",s1);
        tab.addTab("View Project",s2);
        tab.addTab("Delete Project",s3);

        projFrame.setSize(400,400);
        projFrame.setVisible(true);
        projFrame.setResizable(true);
    }
    public static void main(String[] args) {
        AddProject add = new AddProject();
        add.Dis();
    }
}