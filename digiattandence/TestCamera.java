package digiattandence;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TestCamera extends JFrame {

    private Webcam webcam;
    private CascadeClassifier faceDetector;

    public TestCamera() {
        super("Webcam Frame");

        webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setFPSDisplayed(true);
        panel.setFillArea(true);

        add(panel);

        JButton captureButton = new JButton("Capture");
        JButton closeButton = new JButton("Close");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(captureButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                captureImageAndMarkAttendance();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                webcam.close();
                new Home();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                webcam.close();
            }
        });

        // Load face cascade classifier
        faceDetector = new CascadeClassifier("haarcascade_frontalface_default.xml");
    }

    private void captureImageAndMarkAttendance() {
        BufferedImage image = webcam.getImage();
        Mat matImage = bufferedImageToMat(image);

        // Detect faces
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(matImage, faceDetections);

        // Mark attendance for each detected face
        for (Rect rect : faceDetections.toArray()) {
            // Draw a rectangle around each detected face
            Imgproc.rectangle(matImage, new org.opencv.core.Point(rect.x, rect.y),
                    new org.opencv.core.Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0), 2);

            // Get student ID from user input (assuming you have a UI for this)
            String studentID = JOptionPane.showInputDialog(this, "Enter student ID:");

            // Save image with student ID as filename
            saveImage(matImage, studentID);

            // Mark attendance in database (example)
            markAttendanceInDatabase(studentID);
        }

        // Convert the Mat back to BufferedImage and display it in a panel
        BufferedImage detectedImage = matToBufferedImage(matImage);
        ImageIcon icon = new ImageIcon(detectedImage);
        JLabel label = new JLabel(icon);
        JOptionPane.showMessageDialog(this, label, "Detected Faces", JOptionPane.PLAIN_MESSAGE);
    }

    private void saveImage(Mat mat, String studentID) {
        String folderPath = "attendance_images";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }

        String filename = folderPath + File.separator + studentID + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".jpg";
        File file = new File(filename);
        BufferedImage bi = matToBufferedImage(mat);
        try {
            ImageIO.write(bi, "jpg", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void markAttendanceInDatabase(String studentID) {
        // Your logic to mark attendance in the database goes here
        // Connect to your database and update attendance for the given studentID

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");

            // Define SQL query to update attendance
            String sql = "UPDATE attendance_table SET attendance = 1 WHERE student_id = ?";

            // Create prepared statement with student ID parameter
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentID);

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Attendance marked for student: " + studentID);
            } else {
                System.out.println("Failed to mark attendance for student: " + studentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close PreparedStatement and Connection
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Mat bufferedImageToMat(BufferedImage bi) {
        // Convert BufferedImage to Mat
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
        // return null;
    }

    private BufferedImage matToBufferedImage(Mat matrix) {
        // Convert Mat to BufferedImage
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, matOfByte);
        byte[] byteArray = matOfByte.toArray();

        BufferedImage bufferedImage = null;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedImage;
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        SwingUtilities.invokeLater(Webcamera::new);
        SwingUtilities.invokeLater(TestCamera::new);
    }

}
