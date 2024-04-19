package digiattandence;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class StudentList extends JFrame implements ActionListener {


    JTable table;
    Choice cemployeeId;
    JButton search, print, update, back;

    StudentList(String StudentId) {

        getContentPane().setBackground(Color.WHITE);
        setTitle("DIGI ATTENDANCE STUDENTLIST PAGE");
        setLayout(null);
        JLabel formno = new JLabel("SENGUNTHAR ENGINEERING COLLEGE ");
        formno.setFont(new Font("Raleway", Font.BOLD, 60));
        formno.setBounds(140, 10, 1200, 50);
        formno.setForeground(Color.RED);
        add(formno);
        
        JLabel searchlbl = new JLabel("SEARCH BY STUDENT REGISTRATION NUMBER:");
        searchlbl.setBounds(500, 100, 300, 50);
        add(searchlbl);

        cemployeeId = new Choice();
        cemployeeId.setBounds(800, 100, 150, 20);
        add(cemployeeId);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from  StudentDetails");
            while (rs.next()) {
                cemployeeId.add(rs.getString("StudentId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from  StudentDetails");
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(15, 200, 1500, 500);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20, 150, 140, 40);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(450, 150, 140, 40);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(880, 150, 140, 40);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(1370, 150, 140, 40);
        back.addActionListener(this);
        add(back);
        setSize(1540, 850);
        setLocation(0, 0);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == search) {

            String query = "select * from  StudentDetails where StudentId = '" + cemployeeId.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateStudent(cemployeeId.getSelectedItem());
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String args[]) {
        new StudentList("");
    }
}
