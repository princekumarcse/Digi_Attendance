package digiattandence;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class UpdateStudent extends JFrame implements ActionListener {

    JTextField tfeducation, tffname, tfaddress, tfphone, tfaadhar, tfemail, tfregistration, tfdesignation;
    JLabel lblStudentId;
    JButton add, back;
    JDateChooser dcdob;
    String StudentId;

    UpdateStudent(String StudentId) {
        this.StudentId = StudentId;
        getContentPane().setBackground(Color.WHITE);
        setTitle("DIGI ATTENDANCE");
        setLayout(null);
        JLabel formno = new JLabel("SENGUNTHAR ENGINEERING COLLEGE ");
        formno.setFont(new Font("Raleway", Font.BOLD, 60));
        formno.setBounds(140, 10, 1200, 50);
        formno.setForeground(Color.BLUE);
        add(formno);

        JLabel heading = new JLabel("UPDATE STUDENT DETAILS");
        heading.setBounds(560, 60, 600, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(150, 120, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        JLabel lblname = new JLabel();
        lblname.setBounds(300, 120, 150, 30);
        add(lblname);

        JLabel labelfname = new JLabel("Father's Name");
        labelfname.setBounds(800, 120, 150, 30);
        labelfname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(1100, 120, 300, 30);
        add(tffname);

        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(150, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);

        JLabel lbldob = new JLabel();
        lbldob.setBounds(300, 200, 150, 30);
        add(lbldob);

        JLabel labelsalary = new JLabel("Registration No ");
        labelsalary.setBounds(800, 200, 300, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsalary);

        tfregistration = new JTextField();
        tfregistration.setBounds(1100, 200, 300, 30);
        add(tfregistration);

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(150, 300, 300, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(400, 300, 300, 30);
        add(tfaddress);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(800, 300, 300, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(1100, 300, 300, 30);
        add(tfphone);

        JLabel labelemail = new JLabel("Email:-");
        labelemail.setBounds(150, 400, 150, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(400, 400, 300, 30);
        add(tfemail);

        JLabel labeleducation = new JLabel("Higest Education:-");
        labeleducation.setBounds(800, 400, 150, 30);
        labeleducation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeleducation);

        tfeducation = new JTextField();
        tfeducation.setBounds(600, 300, 150, 30);
        add(tfeducation);
        JLabel labeldesignation = new JLabel("Designation:-");
        labeldesignation.setBounds(150, 500, 300, 30);
        labeldesignation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldesignation);

        tfdesignation = new JTextField();
        tfdesignation.setBounds(400, 500, 300, 30);
        add(tfdesignation);

        JLabel labelaadhar = new JLabel("Aadhar Number:-");
        labelaadhar.setBounds(800, 500, 150, 30);
        labelaadhar.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelaadhar);

        JLabel lblaadhar = new JLabel();
        lblaadhar.setBounds(600, 350, 150, 30);
        add(lblaadhar);

        JLabel labelempId = new JLabel("STUDENT ID:-");
        labelempId.setBounds(550, 580, 350, 30);
        labelempId.setFont(new Font("serif", Font.BOLD, 30));
        add(labelempId);

        lblStudentId = new JLabel();
        lblStudentId.setBounds(800, 580, 350, 30);
        lblStudentId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblStudentId);

        try {
            Conn c = new Conn();
            String query = "select * from StudentDetails where StudentId = '" + StudentId + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                lblname.setText(rs.getString("name"));
                tffname.setText(rs.getString("fname"));
                lbldob.setText(rs.getString("dob"));
                // tfaddress.setText(rs.getString("address"));
                tfregistration.setText(rs.getString("registration"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfeducation.setText(rs.getString("education"));
                lblaadhar.setText(rs.getString("aadhar"));
                lblStudentId.setText(rs.getString("StudentId"));
                tfdesignation.setText(rs.getString("designation"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add = new JButton("ADD DETAILS");
        add.setBounds(500, 650, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.RED);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(800, 650, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.ORANGE);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setSize(1540, 850);
        setLocation(0, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == add) {
            String fname = tffname.getText();
            String registration = tfregistration.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = tfeducation.getText();
            String designation = tfdesignation.getText();

            try {
                Conn conn = new Conn();
                String query = "update StudentDetails set fname = '" + fname + "', registration  = '" + registration + "', address = '" + address + "', phone = '" + phone + "', email =  '" + email + "', education = '" + education + "', designation = '" + designation + "' where StudentId = '" + lblStudentId + "'";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateStudent("");
    }
}
