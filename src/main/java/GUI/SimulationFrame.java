package GUI;

import BusinessLogic.Scheduler;
import BusinessLogic.SimulationManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JScrollPane;

import static java.lang.Integer.parseInt;

public class SimulationFrame {

    private JFrame frame;
    private JTextField textFieldNrClienti;
    private JTextField textFieldNrServicii;
    private JTextField textFieldMinSosire;
    private JTextField textFieldMaxSosire;
    private JTextField textFieldMinServire;
    private JTextField textFieldMaxServire;
    private JTextField textTimpSimulare;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SimulationFrame window = new SimulationFrame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SimulationFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // frame-ul
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(252, 237, 205));
        frame.setBounds(100, 100, 1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // JLabel-uri
        JLabel nrClient = new JLabel("Numar de clienti:");
        nrClient.setFont(new Font("Times New Roman", Font.BOLD, 18));
        nrClient.setBounds(30, 122, 192, 47);
        frame.getContentPane().add(nrClient);

        JLabel nrServer = new JLabel("Numar de servicii:");
        nrServer.setFont(new Font("Times New Roman", Font.BOLD, 18));
        nrServer.setBounds(30, 198, 189, 47);
        frame.getContentPane().add(nrServer);

        JLabel minTimpServire = new JLabel("Timpul minim de servire:");
        minTimpServire.setFont(new Font("Times New Roman", Font.BOLD, 18));
        minTimpServire.setBounds(30, 427, 209, 47);
        frame.getContentPane().add(minTimpServire);

        JLabel lblTimpulMaximDe = new JLabel("Timpul maxim de servire:");
        lblTimpulMaximDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblTimpulMaximDe.setBounds(30, 497, 209, 47);
        frame.getContentPane().add(lblTimpulMaximDe);

        JLabel lblTimpSimulare = new JLabel("Timpul de simulare:");
        lblTimpSimulare.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblTimpSimulare.setBounds(30, 567, 209, 47);
        frame.getContentPane().add(lblTimpSimulare);

        JLabel lblTimpulMinimDeSosire = new JLabel("Timpul minim de sosire:");
        lblTimpulMinimDeSosire.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTimpulMinimDeSosire.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblTimpulMinimDeSosire.setBounds(30, 279, 192, 47);
        frame.getContentPane().add(lblTimpulMinimDeSosire);

        JLabel lblTimpulMaximDeSosire = new JLabel("Timpul maxim de sosire:");
        lblTimpulMaximDeSosire.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblTimpulMaximDeSosire.setBounds(30, 351, 209, 47);
        frame.getContentPane().add(lblTimpulMaximDeSosire);

        JLabel titlu = new JLabel("Gestionarea cozilor");
        titlu.setHorizontalAlignment(SwingConstants.CENTER);
        titlu.setFont(new Font("Papyrus", Font.BOLD, 35));
        titlu.setBounds(232, 22, 515, 47);
        frame.getContentPane().add(titlu);

        JLabel lblRezultatCozi = new JLabel("Rezultat cozi:");
        lblRezultatCozi.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblRezultatCozi.setBounds(654, 108, 189, 38);
        frame.getContentPane().add(lblRezultatCozi);

        // TextField-uri
        textFieldNrClienti = new JTextField();
        textFieldNrClienti.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldNrClienti.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textFieldNrClienti.setColumns(10);
        textFieldNrClienti.setBounds(232, 124, 184, 47);
        frame.getContentPane().add(textFieldNrClienti);

        textFieldNrServicii = new JTextField();
        textFieldNrServicii.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldNrServicii.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textFieldNrServicii.setColumns(10);
        textFieldNrServicii.setBounds(232, 198, 184, 47);
        frame.getContentPane().add(textFieldNrServicii);

        textFieldMinSosire = new JTextField();
        textFieldMinSosire.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldMinSosire.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textFieldMinSosire.setColumns(10);
        textFieldMinSosire.setBounds(232, 279, 184, 47);
        frame.getContentPane().add(textFieldMinSosire);

        textFieldMaxSosire = new JTextField();
        textFieldMaxSosire.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldMaxSosire.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textFieldMaxSosire.setColumns(10);
        textFieldMaxSosire.setBounds(232, 351, 184, 47);
        frame.getContentPane().add(textFieldMaxSosire);

        textFieldMinServire = new JTextField();
        textFieldMinServire.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldMinServire.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textFieldMinServire.setColumns(10);
        textFieldMinServire.setBounds(232, 427, 184, 47);
        frame.getContentPane().add(textFieldMinServire);

        textFieldMaxServire = new JTextField();
        textFieldMaxServire.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldMaxServire.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textFieldMaxServire.setColumns(10);
        textFieldMaxServire.setBounds(232, 497, 184, 47);
        frame.getContentPane().add(textFieldMaxServire);

        textTimpSimulare = new JTextField();
        textTimpSimulare.setHorizontalAlignment(SwingConstants.CENTER);
        textTimpSimulare.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textTimpSimulare.setColumns(10);
        textTimpSimulare.setBounds(232, 567, 184, 47);
        frame.getContentPane().add(textTimpSimulare);

        // textArea in care se vor afisa datele dorite
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // scrollPane-ul pentru a naviga in textArea
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(460, 147, 487, 397);
        frame.getContentPane().add(scrollPane);
        scrollPane.setViewportView(textArea);

        // butonul de start
        JButton butonStart = new JButton("START");
        butonStart.setBorderPainted(false);
        butonStart.setBackground(new Color(255, 146, 41));
        butonStart.setFont(new Font("Times New Roman", Font.BOLD, 20));
        butonStart.setBounds(470, 567, 200, 67);
        frame.getContentPane().add(butonStart);
        butonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input1 = textFieldNrClienti.getText().trim(); // scoate spatiile care sunt in plus
                int n = Integer.parseInt(input1);
                String input2 = textFieldNrServicii.getText().trim();
                int q = Integer.parseInt(input2);
                String input3 = textFieldMinSosire.getText().trim();
                int minArrivalTime = Integer.parseInt(input3);
                String input4 = textFieldMaxSosire.getText().trim();
                int maxArrivalTime = Integer.parseInt(input4);
                String input5 = textFieldMinServire.getText().trim();
                int minServiceTime = Integer.parseInt(input5);
                String input6 = textFieldMaxServire.getText().trim();
                int maxServiceTime = Integer.parseInt(input6);
                String input7 = textTimpSimulare.getText().trim();
                int timpSimulare = Integer.parseInt(input7);

                SimulationManager s = new SimulationManager(n, q, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, timpSimulare, textArea);
                Thread t = new Thread(s);
                t.start();

            }
        });

        JButton deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        deleteButton.setBorderPainted(false);
        deleteButton.setBackground(new Color(70, 180, 206));
        deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        deleteButton.setBounds(740, 567, 200, 67);
        frame.getContentPane().add(deleteButton);
    }
}