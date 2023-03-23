import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class Participants extends JFrame {
    private JPanel panelMain;
    private JTextField textField1;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton addParticipantButton;
public Participants() {
    addParticipantButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(addParticipantButton, txtName.getText()+"Thank You!");
        }
    });

    public static void main(String[] args) {
        Participants h = new Participants();
                h.setContentPane(h.panelMain);
        h.setTitle();
        h.setSize( 300,  400);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
}
