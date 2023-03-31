import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

;import java.util.ArrayList;


public class Participants extends JFrame {
    private JPanel panelMain;
    private JTextField textField1;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton addParticipantButton;
    private JPanel subPanel1;
    private JPanel subPanel2;
    private JList list1;

    public Participants() {
        JTextField alphabeticTextField;
        alphabeticTextField = new JTextField();
        ArrayList<ParticipantManagement> list = new ArrayList<>();

// Create a document filter that rejects non-alphabetic characters
        AbstractDocument doc = (AbstractDocument) textField1.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            private AttributeSet attrs;
            private int offset;
            private FilterBypass fb;
            private AttributeSet attr;

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                this.fb = fb;
                this.offset = offset;
                this.attr = attr;
                if (string == null) {
                    return;
                }
                // Reject non-alphabetic characters
                if (!string.matches("[a-zA-Z\\s]+")) {
                    return;
                }
                super.insertString(fb, offset, string, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                this.fb = fb;
                this.offset = offset;
                this.attrs = attrs;
                if (text == null) {
                    return;
                }
                // Reject non-alphabetic characters
                if (!text.matches("[a-zA-Z\\s]+")) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });



        // When the addParticipantButton is clicked...
        addParticipantButton.addActionListener(e -> {
            list.add(new ParticipantManagement(textField1.getText(), (int)spinner1.getValue(),(int)spinner2.getValue()));
            list1.setListData(list.toArray());
//            String participants = alphabeticTextField.getText();
//            JOptionPane.showMessageDialog(addParticipantButton, participants + " Thank You!");
        });

        // Set the minimum and maximum values for spinner1
        SpinnerNumberModel model1 = new SpinnerNumberModel(0, 0, 23, 1);
        spinner1.setModel(model1);

        // Set the minimum and maximum values for spinner2
        SpinnerNumberModel model2 = new SpinnerNumberModel(0, 0, 59, 1);
        spinner2.setModel(model2);

        // Add a change listener to spinner1 to enforce the maximum value
        spinner1.addChangeListener(e -> {
            JSpinner spinner = (JSpinner) e.getSource();
            SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
            int value = (int) model.getValue();
            if (value > 23) {
                model.setValue(23);
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
    }}


