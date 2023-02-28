import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.io.*;
import java.util.*;

public class AddDept implements ActionListener {
    JFrame frame;
    JLabel emp_id,department,dept_id,del_id;
    JTextField emp_id_text,department_text,dept_id_text,del_id_text;
    JTable table;
    JButton del;
    JComboBox combo1 ;
    AddDept(){
        frame = new JFrame();
        frame.setSize(400,400);
        frame.setLayout(null);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        emp_id = new JLabel("Employee ID");
        emp_id.setBounds(50,50,100,30);
        frame.add(emp_id);
        emp_id_text = new JTextField();
        emp_id_text.setBounds(150,50,100,30);
        frame.add(emp_id_text);
        dept_id = new JLabel("Department ID");
        dept_id.setBounds(50,100,100,30);
        frame.add(dept_id);
        dept_id_text = new JTextField();
        dept_id_text.setBounds(150,100,100,30);
        frame.add(dept_id_text);
        department = new JLabel("Department");
        department.setBounds(50,150,100,30);
        frame.add(department);
        String[] dept = {"Sales","Marketing","Finance","IT","Developer","Testing","Operations","Architecture"};
        combo1 = new JComboBox(dept);
        combo1.setBounds(150,150,100,30);
        frame.add(combo1);
        JButton remove = new JButton("Remove");
        remove.setBounds(200, 250, 100, 30);
        frame.add(remove);
        JButton view = new JButton("View");
        view.setBounds(50,250,100,30);
        frame.add(view);
        JButton save = new JButton("Save");
        save.setBounds(50,200,100,30);
        frame.add(save);
        JButton clear = new JButton("Clear");
        clear.setBounds(200,200,100,30);
        frame.add(clear);
        frame.setVisible(true);
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                combo1.getItemAt(combo1.getSelectedIndex());
                // System.out.println(combo1.getItemAt(combo1.getSelectedIndex()));
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","***","***");
                    PreparedStatement ps = con.prepareStatement("insert into department (id,dept_id,dept_name) values(?,?,?)");
                    ps.setString(1,emp_id_text.getText());
                    ps.setString(2,dept_id_text.getText());
                    ps.setString(3,combo1.getItemAt(combo1.getSelectedIndex()).toString());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Saved");
                    
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        remove.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","***","***");
                    PreparedStatement ps = con.prepareStatement("delete from department where id=?");
                    ps.setString(1,emp_id_text.getText());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Deleted");
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }

        });
        view.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                    table = new JTable();
                    JFrame viewDepartment = new JFrame("View Departments");
                    viewDepartment.setSize(600,400);
                    viewDepartment.setLocationRelativeTo(null);

                    DefaultTableModel tabModel = new DefaultTableModel();
                    tabModel.setColumnIdentifiers(new String[]{"Employee ID","Dept ID","Dept Name"});
                    table.setModel(tabModel);

                    viewDepartment.add(new JScrollPane(table));
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","***","***");
                        PreparedStatement ps = con.prepareStatement("select * from department");
                        ResultSet rs = ps.executeQuery();
                        while(rs.next()){
                            tabModel.addRow(new Object[]{rs.getString("id"),rs.getString("dept_id"),rs.getString("dept_name")});
                        }
                    }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                viewDepartment.setVisible(true);
            }

        });
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                emp_id_text.setText("");
                dept_id_text.setText("");
                combo1.setSelectedIndex(0);
            }
        });
    }
    public static void main(String[] args) {
        new AddDept();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
}
