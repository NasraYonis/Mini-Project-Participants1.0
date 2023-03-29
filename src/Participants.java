import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class Participants extends JFrame {
    private JPanel panelMain;
    private JTextField textField1;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JButton addParticipantButton;

    public Participants() {
        JPanel panelMain = new JPanel();
        new JTextField();

        // Add key listener to the text field
        JTextField textField1 = new JTextField();

// Create a document filter that rejects non-alphabetic characters
        AbstractDocument doc = (AbstractDocument) textField1.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            public FilterBypass fb;
            public int offset;
            public AttributeSet attrs;
            public AttributeSet attr;

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                this.fb = fb;
                this.offset = offset;
                this.attr = attr;
                if (string == null) {
                    return;
                }
                // Reject non-alphabetic characters
                if (!string.matches("[a-zA-Z]+")) {
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
                if (!text.matches("[a-zA-Z]+")) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });



        // When the addParticipantButton is clicked...
        addParticipantButton.addActionListener(e -> {
            String participants = textField1.getText();
            JOptionPane.showMessageDialog(addParticipantButton, participants + " Thank You!");
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
    }

    public static void main(String[] args) {
        Participants h = new Participants();
        h.setContentPane(h.panelMain);
        h.setTitle("Participant List"); // Provide a title for the window
        h.setSize(400, 400);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
