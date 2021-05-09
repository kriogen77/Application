import javax.swing.*;

public class Main extends JPanel {
    private JPanel panel;
    private JTextField inputField;
    private JButton addButton;
    private JButton runButton;
    private JButton clearButton;
    private JButton deleteButton;
    private JList<String> leftList;
    private JList<String> rightList;

    public Main() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        leftList.setModel(listModel);

        addButton.addActionListener(e -> {
            String line = inputField.getText();
            listModel.addElement(line);
            inputField.setText("");
        });

        deleteButton.addActionListener(e -> {
            DefaultListModel model = (DefaultListModel) leftList.getModel();
            if (leftList.getSelectedIndices().length > 0) {
                int[] selectedIndices = leftList.getSelectedIndices();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    model.removeElementAt(selectedIndices[i]);
                }
            }
        });

        DefaultListModel<String> listModel2 = new DefaultListModel<>();
        runButton.addActionListener(e -> {
            int count;
            StringBuilder newLine = new StringBuilder();
            for (int i = 0; i < listModel.size(); i++) {
                String[] words = listModel.elementAt(i).split(" ");
                for (int j = 0; j < words.length; j++) {
                    count = 1;
                    for (int k = j + 1; k < words.length; k++) {
                        if (words[j].equals(words[k])) {
                            count++;
                            words[k] = "0";
                        }
                    }
                    if (count > 1 && !words[j].equals("0")) {
                        newLine.append(words[j]).append(" ");
                    }
                }
                listModel2.addElement(newLine.toString());
                newLine = new StringBuilder();
            }
            listModel.removeAllElements();
            rightList.setModel(listModel2);
        });

        clearButton.addActionListener(e -> listModel2.removeAllElements());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Application");
        frame.setContentPane(new Main().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}