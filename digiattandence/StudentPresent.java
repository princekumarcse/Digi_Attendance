package digiattandence;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentPresent extends JFrame implements ActionListener {

    JButton exit;

    StudentPresent() {

        setTitle("DIGI ATTENDANCE STUDENT PRESENT");
        setLayout(null);

        JLabel formno = new JLabel("SENGUNTHAR ENGINEERING COLLEGE ");
        formno.setFont(new Font("Raleway", Font.BOLD, 60));
        formno.setBounds(140, 10, 1200, 50);
        formno.setForeground(Color.RED);
        add(formno);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/present.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(290, 100, 880, 400);//600,450
        add(image);

        JLabel present = new JLabel("Marked as Present");
        present.setFont(new Font("Raleway", Font.BOLD, 40));
        present.setBounds(540, 450, 1200, 50);
        present.setForeground(Color.blue);
        add(present);

        exit = new JButton("EXIT");
        exit.setBounds(600, 570, 220, 60);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.white);
        exit.addActionListener(this);
        exit.setFont(new Font("tahome", Font.BOLD, 35));
        add(exit);
        //cancle button

        getContentPane().setBackground(Color.WHITE);
        setSize(1540, 850);
        setLocation(0, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            setVisible(false);
            new Home();
        }

    }

    public static void main(String[] args) {
        new StudentPresent();
    }
}

