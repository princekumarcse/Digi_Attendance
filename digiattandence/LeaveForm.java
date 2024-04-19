package digiattandence;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;

public class LeaveForm extends JFrame implements ActionListener {

    long random;
    JTextField nametext, registertext, yeartext, citytext, reasontext, pintext, pdftext, daystext, parentsphonetext;
    JButton next, open, save, back;
    JRadioButton cse, mech, ece, eee, civil, others;
    JDateChooser dateChooser;
    private JButton chooseButton;
    private JLabel selectedFileLabel;

    LeaveForm() {
        //this.formno=formno;
        // getContentPane().setBackground(Color.YELLOW);
        setTitle("DIGI ATTENDANCE LEAVE FORM PAGE");
        setLayout(null);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);
        String first = "" + Math.abs(random);

        //label for application number
        JLabel formno = new JLabel("SENGUNTHAR ENGINEERING COLLEGE ");
        formno.setFont(new Font("Raleway", Font.BOLD, 60));
        formno.setBounds(140, 10, 1200, 50);
        formno.setForeground(Color.RED);
        add(formno);

        JLabel personalDetails = new JLabel("STUDENT LEAVE FORM NO: " + first);
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 35));
        personalDetails.setBounds(560, 70, 600, 40);
        personalDetails.setForeground(Color.blue);
        add(personalDetails);

        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(200, 130, 100, 40);
        add(name);
        nametext = new JTextField();
        nametext.setFont(new Font("Raleway", Font.BOLD, 14));
        nametext.setBounds(300, 130, 400, 30);
        add(nametext);

        JLabel register = new JLabel("Register Number: ");
        register.setFont(new Font("Raleway", Font.BOLD, 20));
        register.setBounds(750, 120, 200, 40);
        add(register);
        registertext = new JTextField();
        registertext.setFont(new Font("Raleway", Font.BOLD, 14));
        registertext.setBounds(950, 125, 400, 30);
        add(registertext);

        JLabel dob = new JLabel("Leave Date:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(180, 210, 150, 40);
        add(dob);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 210, 400, 30);
        dateChooser.setForeground(new Color(105, 105, 105));
        add(dateChooser);

        JLabel year = new JLabel("Year/Sem:");
        year.setFont(new Font("Raleway", Font.BOLD, 20));
        year.setBounds(750, 210, 150, 40);
        add(year);
        yeartext = new JTextField();
        yeartext.setFont(new Font("Raleway", Font.BOLD, 14));
        yeartext.setBounds(950, 210, 400, 30);
        add(yeartext);

        JLabel branch = new JLabel("Branch: ");
        branch.setFont(new Font("Raleway", Font.BOLD, 20));
        branch.setBounds(180, 270, 150, 40);
        add(branch);
        //radio button for male and female

        cse = new JRadioButton("CSE");
        cse.setBounds(300, 270, 100, 40);
        cse.setBackground(Color.WHITE);
        add(cse);

        mech = new JRadioButton("MECH");
        mech.setBounds(440, 270, 100, 40);
        mech.setBackground(Color.WHITE);
        add(mech);

        ece = new JRadioButton("ECE");
        ece.setBounds(580, 270, 100, 40);
        ece.setBackground(Color.WHITE);
        add(ece);

        eee = new JRadioButton("EEE");
        eee.setBounds(720, 270, 100, 40);
        eee.setBackground(Color.WHITE);
        add(eee);

        civil = new JRadioButton("CIVIL");
        civil.setBounds(860, 270, 100, 40);
        civil.setBackground(Color.WHITE);
        add(civil);

        others = new JRadioButton("OTHERS");
        others.setBounds(1000, 270, 100, 40);
        others.setBackground(Color.WHITE);
        add(others);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(cse);
        gendergroup.add(mech);
        gendergroup.add(ece);
        gendergroup.add(eee);
        gendergroup.add(civil);
        gendergroup.add(others);

        JLabel days = new JLabel("No.Of Days :");
        days.setFont(new Font("Raleway", Font.BOLD, 20));
        days.setBounds(180, 350, 150, 40);
        add(days);
        daystext = new JTextField();
        daystext.setFont(new Font("Raleway", Font.BOLD, 14));
        daystext.setBounds(300, 350, 400, 30);
        add(daystext);

        JLabel parentsphone = new JLabel("Parents Phone: ");
        parentsphone.setFont(new Font("Raleway", Font.BOLD, 20));
        parentsphone.setBounds(750, 350, 200, 40);
        add(parentsphone);
        parentsphonetext = new JTextField();
        parentsphonetext.setFont(new Font("Raleway", Font.BOLD, 14));
        parentsphonetext.setBounds(950, 350, 400, 30);
        add(parentsphonetext);

        JLabel reason = new JLabel("REASON:");
        reason.setFont(new Font("Raleway", Font.BOLD, 20));
        reason.setBounds(180, 430, 150, 40);
        add(reason);
        reasontext = new JTextField();
        reasontext.setFont(new Font("Raleway", Font.BOLD, 14));
        reasontext.setBounds(300, 410, 1050, 80);
        add(reasontext);

        JLabel pdf = new JLabel("Upload Pdf:");
        pdf.setFont(new Font("Raleway", Font.BOLD, 20));
        pdf.setBounds(180, 530, 150, 40);
        add(pdf);
        pdftext = new JTextField();
        pdftext.setFont(new Font("Raleway", Font.BOLD, 14));
        pdftext.setBounds(300, 530, 400, 30);
        add(pdftext);

        open = new JButton("Open");
        open.setForeground(Color.WHITE);
        open.setBackground(Color.BLACK);
        open.setFont(new Font("Raleway", Font.BOLD, 14));
        open.setBounds(720, 530, 80, 30);
        open.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    pintext.setText(selectedFile.getAbsolutePath());
                    // Perform file upload or processing here
                }
            }
        }
        );
        add(open);

        save = new JButton("Save");
        save.setForeground(Color.WHITE);
        save.setBackground(Color.BLACK);
        save.setFont(new Font("Raleway", Font.BOLD, 14));
        save.setBounds(820, 530, 80, 30);
        add(save);

        next = new JButton("Submit");
        next.setForeground(Color.GREEN);
        next.setBackground(Color.BLACK);
        next.setFont(new Font("Raleway", Font.BOLD, 20));
        next.setBounds(640, 630, 150, 50);
        next.addActionListener(this); // Initialize ActionListener here
        add(next);

        back = new JButton("BACK");
        back.setForeground(Color.GREEN);
        back.setBackground(Color.BLACK);
        back.setFont(new Font("Raleway", Font.BOLD, 20));
        back.setBounds(810, 630, 150, 50);
        back.addActionListener(this);
        add(back);

        //frame 
        getContentPane().setBackground(Color.WHITE);
        setSize(1540, 850);
        setLocation(0, 0);
        setVisible(true);
    }

    /*
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            // Handle button click event

            String name = nametext.getText();
            String register = registertext.getText();
            String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
            String yearsem = yeartext.getText();
            String branch = null;
            if (cse.isSelected()) {
                branch = "CSE";
            } else if (mech.isSelected()) {
                branch = "MECH";
            } else if (ece.isSelected()) {
                branch = "ECE";
            } else if (eee.isSelected()) {
                branch = "EEE";
            } else if (civil.isSelected()) {
                branch = "CIVIL";
            } else if (others.isSelected()) {
                branch = "OTHERS";
            }
            String days = daystext.getText();
            String parentscall = parentsphonetext.getText();
            String reason = reasontext.getText();
            String filePath = pdftext.getText();

            try {
                Conn conn = new Conn();

                String query = "insert into  LeaveForm values('" + name + "', '" + register + "', '" + dob + "', '" + yearsem + "', '" + branch + "', '" + days + "', '" + parentscall + "', '" + reason + "', '" + filePath + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Home();

            } catch (Exception ae) {
                ae.printStackTrace();
            }

            System.out.println("Button clicked!");
        } else {
            setVisible(false);
            new Home();
        }

    }


     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                pdftext.setText(selectedFile.getAbsolutePath());
                // Perform file upload or processing here
            }
        } else if (e.getSource() == save) {
            // Handle save button click event
            // Add code to save data if needed
        } else if (e.getSource() == next) {
            // Handle submit button click event
            String name = nametext.getText();
            String register = registertext.getText();
            String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
            String yearsem = yeartext.getText();
            String branch = null;
            if (cse.isSelected()) {
                branch = "CSE";
            } else if (mech.isSelected()) {
                branch = "MECH";
            } else if (ece.isSelected()) {
                branch = "ECE";
            } else if (eee.isSelected()) {
                branch = "EEE";
            } else if (civil.isSelected()) {
                branch = "CIVIL";
            } else if (others.isSelected()) {
                branch = "OTHERS";
            }
            String days = daystext.getText();
            String parentscall = parentsphonetext.getText();
            String reason = reasontext.getText();
            String filePath = pdftext.getText();

            try {
                Conn conn = new Conn(); // Assuming Conn class handles database connections

                String query = "insert into LeaveForm values('" + name + "', '" + register + "', '" + dob + "', '" + yearsem + "', '" + branch + "', '" + days + "', '" + parentscall + "', '" + reason + "', '" + filePath + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Home();

            } catch (SQLException ae) {
                ae.printStackTrace();
            }
        } else if (e.getSource() == back) {
            // Handle back button click event
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new LeaveForm();
    }
}
