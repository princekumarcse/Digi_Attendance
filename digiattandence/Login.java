package digiattandence;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JButton login, clear, register;
    JTextField tfregno;
    JPasswordField tfpassword;
    String name, password, regno;

    Login() {

        getContentPane().setBackground(Color.WHITE);
        setTitle("DIGI ATTENDANCE LOGIN PAGE");
        setLayout(null);

        JLabel heading1 = new JLabel("Welcome! Now you are on the Digi Attendance Login Page.");

        heading1.setBounds(80, 20, 1500, 70);
        heading1.setFont(new Font("Raleway", Font.BOLD, 50));
        heading1.setForeground(new Color(0, 200, 0));
        add(heading1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/login.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 10, 880, 400);//600,450
        add(image);

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("Icons/m.png"));
        JLabel secimage = new JLabel(i2);
        secimage.setBounds(0, 0, 2200, 750);//600,450
        add(secimage);

        JLabel lblregno = new JLabel("Registration No:");
        lblregno.setFont(new Font("times new roman", Font.BOLD, 20));
        lblregno.setBounds(300, 310, 220, 20);
        add(lblregno);

        tfregno = new JTextField();
        tfregno.setBounds(460, 310, 150, 25);
        tfregno.setFont(new Font("Arial", Font.BOLD, 14));
        add(tfregno);
        // label for password

        JLabel lblpassword = new JLabel("Password :");
        lblpassword.setFont(new Font("times new roman", Font.BOLD, 20));
        lblpassword.setBounds(300, 390, 220, 20);
        add(lblpassword);
        //password textfield

        tfpassword = new JPasswordField();
        tfpassword.setBounds(460, 390, 150, 25);
        tfpassword.setFont(new Font("Arial", Font.BOLD, 14));
        add(tfpassword);
        //Login Button

        login = new JButton("Login");
        login.setBounds(300, 470, 120, 30);
        login.setBackground(Color.blue);
        login.setForeground(Color.white);
        login.addActionListener(this);
        login.setFont(new Font("tahome", Font.BOLD, 15));
        add(login);
        //cancle button

        clear = new JButton("Clear");
        clear.setBounds(480, 470, 120, 30);
        clear.setBackground(Color.blue);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        clear.setFont(new Font("tahome", Font.BOLD, 15));
        add(clear);

        //dont have account
        JLabel lblrandom = new JLabel("Don't Have Account? ");
        lblrandom.setBounds(380, 500, 200, 40);
        lblrandom.setFont(new Font("Viner Hand ITC", Font.BOLD, 15));
        lblrandom.setForeground(Color.red);
        add(lblrandom);

        register = new JButton("Register");
        register.setBounds(330, 550, 240, 30);
        register.setBackground(Color.blue);
        register.setForeground(Color.white);
        register.addActionListener(this);
        register.setFont(new Font("tahome", Font.BOLD, 15));
        add(register);

        setSize(1540, 850);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            tfregno.setText("");
            tfpassword.setText("");
        } else if (ae.getSource() == register) {
            setVisible(false);
            new registration(name); // Make sure you have a class named 'Registration'
        } else if (ae.getSource() == login) {
            setVisible(false);
            new Home();
            Conn conn = new Conn();
            String registerno = tfregno.getText();
            String password = tfpassword.getText();

            if (registerno.isEmpty() && password.isEmpty()) {
                new Login();
                JOptionPane.showMessageDialog(null, "Please enter both Registration Number and Password");
                
                //setVisible(true);

                // Optionally, set visibility of Login window to true if needed
            } else {
                String query = "Select* from Registration where Regno ='" + registerno + "' and Password='" + password
                        + "'";
                try {
                    ResultSet rs = conn.s.executeQuery(query);
                    if (rs.next()) {
                        setVisible(false);
                        new Home();
                    } else {
                        new Login();
                        JOptionPane.showMessageDialog(null, "Incorrect Registration Number or Password");

                        //setVisible(true);
                    }

                } catch (HeadlessException | SQLException e) {
                    System.out.println(e);
                }
            }
        }

    }

    public static void main(String args[]) {
        new Login();
    }
}
