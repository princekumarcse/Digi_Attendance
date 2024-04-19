package digiattandence;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Home extends JFrame implements ActionListener {

    JButton entry, picclick, exit, edit, delete, train, pic, attendance;

    Home() {

        setTitle("DIGI ATTENDANCE HOME PAGE");
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/backgr.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1540, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1540, 850);
        add(image);

        // JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        JLabel heading = new JLabel("Welcome,"/* + name + */ + " Now you are on Digi Attendance Home Page ");
        heading.setBounds(80, 20, 1500, 70);
        heading.setFont(new Font("serif", Font.PLAIN, 60));
        heading.setForeground(Color.RED);
        image.add(heading);

        ImageIcon iconAboveButton = new ImageIcon(ClassLoader.getSystemResource("Icons/ss.png"));
        entry = new JButton("<html><center>STUDENT DETAILS<br/><img src='" + iconAboveButton + "'></center></html>");
        entry.setBounds(60, 200, 350, 220);  // Increase the height to accommodate the icon
        entry.setBackground(Color.yellow);
        entry.setForeground(Color.BLUE);
        entry.setFont(new Font("Times New Roman", Font.BOLD, 25));
        entry.setVerticalTextPosition(SwingConstants.BOTTOM);
        entry.setHorizontalTextPosition(SwingConstants.CENTER);
        entry.addActionListener(this);
        image.add(entry);

        ImageIcon searchiconAboveButton = new ImageIcon(ClassLoader.getSystemResource("Icons/cmara.png"));
        picclick = new JButton("<html><center>FACE DETECTOR<br/><img src='" + searchiconAboveButton + "'></center></html>");
        picclick.setBounds(600, 200, 350, 220);  // Increase the height to accommodate the icon
        picclick.setBackground(Color.yellow);
        picclick.setForeground(Color.BLUE);
        picclick.setFont(new Font("Times New Roman", Font.BOLD, 25));
        picclick.setVerticalTextPosition(SwingConstants.BOTTOM);
        picclick.setHorizontalTextPosition(SwingConstants.CENTER);
        picclick.addActionListener(this);
        image.add(picclick);

        ImageIcon attendanceiconAboveButton = new ImageIcon(ClassLoader.getSystemResource("Icons/_leave_.png"));
        attendance = new JButton("<html><center>LEAVE FORM<br/><img src='" + attendanceiconAboveButton + "'></center></html>");
        attendance.setBounds(1100, 200, 350, 220);  // Increase the height to accommodate the icon
        attendance.setBackground(Color.yellow);
        attendance.setForeground(Color.BLUE);
        attendance.setFont(new Font("Times New Roman", Font.BOLD, 25));
        attendance.setVerticalTextPosition(SwingConstants.BOTTOM);
        attendance.setHorizontalTextPosition(SwingConstants.CENTER);
        attendance.addActionListener(this);
        image.add(attendance);

        ImageIcon trainiconAboveButton = new ImageIcon(ClassLoader.getSystemResource("Icons/Lstttt.png"));
        train = new JButton("<html><center>STUDENT LIST<br/><img src='" + trainiconAboveButton + "'></center></html>");
        train.setBounds(60, 530, 350, 220);  // Increase the height to accommodate the icon
        train.setBackground(Color.YELLOW);
        train.setForeground(Color.BLUE);
        train.setFont(new Font("Times New Roman", Font.BOLD, 25));
        train.setVerticalTextPosition(SwingConstants.BOTTOM);
        train.setHorizontalTextPosition(SwingConstants.CENTER);
        train.addActionListener(this);
        image.add(train);

        ImageIcon piciconAboveButton = new ImageIcon(ClassLoader.getSystemResource("Icons/face.jpg"));
        pic = new JButton("<html><center>REMOVE STUDENT<br/><img src='" + piciconAboveButton + "'></center></html>");
        pic.setBounds(600, 530, 350, 220);  // Increase the height to accommodate the icon
        pic.setBackground(Color.YELLOW);
        pic.setForeground(Color.BLUE);
        pic.setFont(new Font("Times New Roman", Font.BOLD, 25));
        pic.setVerticalTextPosition(SwingConstants.BOTTOM);
        pic.setHorizontalTextPosition(SwingConstants.CENTER);
        pic.addActionListener(this);
        image.add(pic);

        ImageIcon exiticonAboveButton = new ImageIcon(ClassLoader.getSystemResource("Icons/exit.jpg"));
        exit = new JButton("<html><center>EXIT<br/><img src='" + exiticonAboveButton + "'></center></html>");
        exit.setBounds(1100, 530, 350, 220);  // Increase the height to accommodate the icon
        exit.setBackground(Color.yellow);
        exit.setForeground(Color.BLUE);
        exit.setFont(new Font("Times New Roman", Font.BOLD, 25));
        exit.setVerticalTextPosition(SwingConstants.BOTTOM);
        exit.setHorizontalTextPosition(SwingConstants.CENTER);
        exit.addActionListener(this);
        image.add(exit);

        getContentPane().setBackground(Color.WHITE);
        setSize(1600, 850); // Set a default size
        setLocationRelativeTo(null);  // Center the JFrame
        setResizable(false);  // Disable resizing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == entry) {
            // Handle button click event
            new StudentDetails();
            System.out.println("Button clicked!");
        } else if (e.getSource() ==  picclick ) {
            setVisible(false);
            new Webcamera();
        } else if (e.getSource() == attendance) {
            setVisible(false);
            new LeaveForm();
        } else if (e.getSource() == train) {
            setVisible(false);
            new StudentList("");
        } else if (e.getSource() == pic) {
            setVisible(false);
            new RemoveStudent();
        } else {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String args[]) {
        new Home();
    }

}
