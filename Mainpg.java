import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.io.*;
import java.util.*;

public class Mainpg implements ActionListener{
    JFrame f ;
    JMenuBar menuBar; 
    JMenu Employee,Department,Project,Performance,Queries;
    JRadioButtonMenuItem i1;
    JRadioButtonMenuItem i3;
    JRadioButtonMenuItem i4;
    JRadioButtonMenuItem i5,i6;
    JRadioButtonMenuItem i2,i7;

   
    Connection con;
    
    Mainpg(Connection con){
        this.con = con;
    }
    JLabel background;
    JTable tableEmployees;
    JMenu btnAdd, btnDelete, btnUpdate, btnView, btnAddSalary, btnlogout, btnNewReg, btnApproveLeave;
    JMenuBar mb;

    public 
    Mainpg(){
        tableEmployees = new JTable();
        f =  new JFrame("Emploee Management System");
        f.setSize(600,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new JMenuBar();
        Employee = new JMenu("Employee");
        Department = new JMenu("Department");
        Project = new JMenu("Project");
        Performance = new JMenu("Performance");
        Queries = new JMenu("Queries");
        i1 = new JRadioButtonMenuItem("View Functionalities");
        i2 = new JRadioButtonMenuItem("Edit Performance");
        i3 = new JRadioButtonMenuItem("Add Department to Employee");
        i4 = new JRadioButtonMenuItem("Assign to Employee");
        i5 = new JRadioButtonMenuItem("Performance Details");
        i7 = new JRadioButtonMenuItem("Your Queries");
        Employee.add(i1);
        Department.add(i3);
        Project.add(i4);
        Performance.add(i5);
        Performance.add(i2);
        Queries.add(i7);
        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
        i5.addActionListener(this);
        i7.addActionListener(this);
        i1.setActionCommand("view");
        i2.setActionCommand("Edit Performance");
        i3.setActionCommand("Add");
        i4.setActionCommand("Assign");
        i5.setActionCommand("performance");
        i7.setActionCommand("Queries");
        menuBar.add(Employee);
        menuBar.add(Department);
        menuBar.add(Project);
        menuBar.add(Performance);
        menuBar.add(Queries);
        f.setJMenuBar(menuBar);
        f.setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="view"){
            // JOptionPane.showMessageDialog(f,"hello","Alert",JOptionPane.WARNING_MESSAGE);
            EmployeeInformation pro=new EmployeeInformation();
            pro.dis();  

        }
        if(e.getActionCommand()=="Add"){
            System.out.println("0");
            new AddDept();
        }
        if(e.getActionCommand()=="Assign"){
            AddProject add = new AddProject();
            add.Dis();
        }
        if(e.getActionCommand()=="performance"){
            new ViewPeformance();
        }
        if(e.getActionCommand()=="Edit Performance"){
            new EditPerformance();
        }
        if(e.getActionCommand()=="Queries"){
            new YourQueries();
        }

    }
        
    public static void main(String args[]) throws Exception{
        // String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
        // UIManager.setLookAndFeel(str);
        new 
        Mainpg(); 
    }
}