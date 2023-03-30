import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.Vector;


public class Participants extends JFrame {
    private JPanel panelMain;
    private JTextField textField1;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton addParticipantButton;
    private JList<Participant> list1;
    private JPanel subPanel2;
    private JPanel subPanel1;

    public Participants(JList<Participant> list1) {
        JTextField alphabeticTextField;
        alphabeticTextField = new JTextField();
        Participants participants = new Participants();
        JFrame frame = new JFrame("Participant List");
        frame.setContentPane(participants.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

// Create a document filter that rejects non-alphabetic characters
        AbstractDocument doc = (AbstractDocument) textField1.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            private final ThreadLocal<FilterBypass> fb = new ThreadLocal<>();

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                this.fb.set(fb);
                if (string == null) {
                    return;
                }
                // Reject non-alphabetic characters and non-whitespace characters
                if (!string.matches("[a-zA-Z\\s]+")) {
                    return;
                }
                super.insertString(fb, offset, string, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                this.fb.set(fb);
                if (text == null) {
                    return;
                }
                // Reject non-alphabetic characters and non-whitespace characters
                if (!text.matches("[a-zA-Z\\s]+")) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        // When the addParticipantButton is clicked...
        addParticipantButton.addActionListener(e -> {
            String name = alphabeticTextField.getText();
            int hours = (int) spinner1.getValue();
            int minutes = (int) spinner2.getValue();
            String participant = name + " (" + hours + ":" + String.format("%02d", minutes) + ")";
            DefaultListModel<Participant> listModel = (DefaultListModel<Participant>) list1.getModel();
            listModel.addElement(new Participant(name, hours, minutes));
            JOptionPane.showMessageDialog(addParticipantButton, name + " Thank You!");
        });

        // Set the minimum and maximum values for spinner1
        SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, 24, 1);
        spinner1.setModel(model1);

        // Set the minimum and maximum values for spinner2
        SpinnerNumberModel model2 = new SpinnerNumberModel(0, 0, 59, 1);
        spinner2.setModel(model2);

        // Add a change listener to spinner1 to enforce the maximum value
        spinner1.addChangeListener(e -> {
            JSpinner spinner = (JSpinner) e.getSource();
            SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
            int value = (int) model.getValue();
            if (value > 24) {
                model.setValue(24);
            } else if (value < 0) {
                model.setValue(0);
            }
        });

        // Add a change listener to spinner2 to enforce the maximum value
        spinner2.addChangeListener(e -> {
            JSpinner spinner = (JSpinner) e.getSource();
            SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
            int value = (int) model.getValue();
            if (value > 59) {
                model.setValue(59);
            } else if (value < 0) {
                model.setValue(0);
            }
        });
        // Initialize the list model
        DefaultListModel<Participant> listModel = new DefaultListModel<>();

         }

    public Participants() {

    }

    private void removeNonAlphabeticCharacters(JTextField textField) {
        try {
            String text = textField.getText(0, textField.getDocument().getLength());
            StringBuilder filteredText = new StringBuilder();
            for (char c : text.toCharArray()) {
                if (Character.isLetter(c)) {
                    filteredText.append(c);
                }
            }
            if (!filteredText.toString().equals(text)) {
                textField.setText(filteredText.toString());
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Participants h = new Participants();
        h.setContentPane(h.panelMain);
        h.setTitle("Participant List"); // Provide a title for the window
        h.setSize(400, 400);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultListModel<Participant> listModel = new DefaultListModel<>();
        h.list1.setModel(listModel); // set the model for the JList

            }


    public record Participant(String name, int hours, int minutes) {

        public Participant(String name) {
            this(name, 0, 0);
        }

        public Participant(String name, int hours, int minutes) {
            this.name = name;
            this.hours = hours;
            this.minutes = minutes;
        }

    @Override
    public String toString() {
        String time = hours + ":" + minutes;
        return name + " - " + time;
    }
}}

