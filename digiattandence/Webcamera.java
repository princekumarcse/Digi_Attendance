package digiattandence;

/*
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Webcamera extends JFrame {

    private Webcam webcam;

    public Webcamera() {
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
                captureImage();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                webcam.close();
               // System.exit(0);
               new Home();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                webcam.close();
            }
        });
    }

    private void captureImage() {
        BufferedImage image = webcam.getImage();
        try {
            ImageIO.write(image, "JPG", new File("capturedImage.jpg"));
            JOptionPane.showMessageDialog(this, "Image captured and saved as capturedImage.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Webcamera::new);
    }
}


 */
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.opencv.core.CvType;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class Webcamera extends JFrame {

    private Webcam webcam;
    private CascadeClassifier faceDetector;

    public Webcamera() {
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
               // captureImageAndMarkAttendance();
                new StudentPresent();
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
            // You can implement attendance marking logic here
            // For example, save the image to a folder with student ID as filename
            // and mark attendance in your database
        }

        // Convert the Mat back to BufferedImage and display it in a panel
        BufferedImage detectedImage = matToBufferedImage(matImage);
        ImageIcon icon = new ImageIcon(detectedImage);
        JLabel label = new JLabel(icon);
        JOptionPane.showMessageDialog(this, label, "Detected Faces", JOptionPane.PLAIN_MESSAGE);
    }

    private Mat bufferedImageToMat(BufferedImage bi) {

        // Convert BufferedImage to Mat
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
        // Convert BufferedImage to Mat
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
        SwingUtilities.invokeLater(Webcamera::new);
    }
}
