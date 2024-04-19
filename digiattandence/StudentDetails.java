package digiattandence;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class StudentDetails extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);

    JTextField tfname, tffname, tfaddress, tfphone, tfaadhar, tfemail, tfregistration, tfdesignation;
    JDateChooser dcdob;
    JComboBox cbeducation;
    JLabel lblStudentId;
    JButton add, back;

    StudentDetails() {

        setTitle("DIGI ATTENDANCE");
        setLayout(null);
        JLabel formno = new JLabel("SENGUNTHAR ENGINEERING COLLEGE ");
        formno.setFont(new Font("Raleway", Font.BOLD, 60));
        formno.setBounds(140, 10, 1200, 50);
        formno.setForeground(Color.GREEN);
        add(formno);

        JLabel personalDetails = new JLabel("ADD STUDENT DETAILS ");
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 25));
        personalDetails.setBounds(560, 60, 600, 50);
        personalDetails.setForeground(Color.GREEN);
        add(personalDetails);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(150, 120, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        tfname = new JTextField();
        tfname.setBounds(400, 120, 300, 30);
        add(tfname);

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

        dcdob = new JDateChooser();
        dcdob.setBounds(400, 200, 300, 30);
        add(dcdob);

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

        String courses[] = {"10th", "12th", "BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD"};
        cbeducation = new JComboBox(courses);
        cbeducation.setBackground(Color.WHITE);
        cbeducation.setBounds(1100, 400, 300, 30);
        add(cbeducation);

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

        tfaadhar = new JTextField();
        tfaadhar.setBounds(1100, 500, 300, 30);
        add(tfaadhar);

        JLabel labelempId = new JLabel("STUDENT ID:-");
        labelempId.setBounds(550, 580, 350, 30);
        labelempId.setFont(new Font("serif", Font.BOLD, 30));
        add(labelempId);

        lblStudentId = new JLabel("" + number);
        lblStudentId.setBounds(760, 580, 150, 30);
        lblStudentId.setFont(new Font("serif", Font.BOLD, 30));
        add(lblStudentId);

        add = new JButton("ADD DETAILS");
        add.setBounds(500, 650, 150, 40);
        add.setBackground(Color.RED);
        add.setForeground(Color.WHITE);
        add.addActionListener(this); // Add action listener only once
        add(add);

        /*
        // Create the "ADD DETAILS" button
        add = new JButton("ADD DETAILS");
        add.setBounds(500, 650, 150, 40);
        add.setBackground(Color.RED);
        add.setForeground(Color.WHITE);

// Disable the button initially
        add.setEnabled(false);

// Add action listener to the button
        add.addActionListener(this);
        add(add);

// Create the phone number text field
        tfphone = new JTextField();
        tfphone.setBounds(1100, 300, 300, 30);
        tfphone.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String phoneNumber = tfphone.getText();
                if (phoneNumber.length() != 10) {
                    // If phone number is not 10 digits, show error message and keep the button disabled
                    JOptionPane.showMessageDialog(null, "Phone number must be 10 digits long", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
                    add.setEnabled(false);
                } else {
                    // If phone number is 10 digits, enable the button
                    add.setEnabled(true);
                }
            }
        });
        add(tfphone);
         */
        back = new JButton("BACK");
        back.setBounds(800, 650, 150, 40);
        back.setBackground(Color.GREEN);
        back.setForeground(Color.WHITE);
        back.addActionListener(this); // Add action listener only once
        add(back);

        getContentPane().setBackground(Color.WHITE);
        setSize(1600, 850); // Set a default size
        setLocationRelativeTo(null);  // Center the JFrame
        setResizable(false);  // Disable resizing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == add) {
            add.setEnabled(false);
            String name = tfname.getText();
            String fname = tffname.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String registration = tfregistration.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = (String) cbeducation.getSelectedItem();
            String designation = tfdesignation.getText();
            String aadhar = tfaadhar.getText();
            String StudentId = lblStudentId.getText();

            try {
                Conn conn = new Conn();

                String query = "insert into  StudentDetails values('" + name + "', '" + fname + "', '" + dob + "', '" + registration + "', '" + address + "', '" + phone + "', '" + email + "', '" + education + "', '" + designation + "', '" + aadhar + "', '" + StudentId + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Home();

            } catch (Exception e) {
                e.printStackTrace();
            }
            // add.setEnabled(true);
        } else {
            setVisible(false);
            new Home();

        }

    }
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            add.setEnabled(false);
            String name = tfname.getText();
            String fname = tffname.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String registration = tfregistration.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = (String) cbeducation.getSelectedItem();
            String designation = tfdesignation.getText();
            String aadhar = tfaadhar.getText();
            String StudentId = lblStudentId.getText();

            try {
                Conn conn = new Conn();

                String query = "insert into  StudentDetails values('" + name + "', '" + fname + "', '" + dob + "', '" + registration + "', '" + address + "', '" + phone + "', '" + email + "', '" + education + "', '" + designation + "', '" + aadhar + "', '" + StudentId + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                dispose(); // Disposes of the current window
                new Home();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                e.printStackTrace();
            } finally {
                add.setEnabled(true); // Ensure the button is enabled even if an exception occurs
            }
        } else {
            dispose(); // Disposes of the current window
            new Home();
        }
    }

    public static void main(String args[]) {
        new StudentDetails();
    }

}
