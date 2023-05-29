import java.io.*;
import javax.swing.*;

public class TelephoneDirectory {
    private static final String FILENAME = "directory.txt";
    private static String[][] directory = new String[100][2];
    private static int count = 0;

    public static void main(String[] args) {
        loadDirectory();

        while (true) {
            String[] options = {"Add Info", "Search Info", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", "Telephone Directory", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    addInfo();
                    break;
                case 1:
                    searchInfo();
                    break;
                case 2:
                    saveDirectory();
                    System.exit(0);
                    break;
            }
        }
    }

    private static void addInfo() {
        String name = JOptionPane.showInputDialog("Enter name:");
        String number = JOptionPane.showInputDialog("Enter number:");
        directory[count][0] = name;
        directory[count][1] = number;
        count++;
        JOptionPane.showMessageDialog(null, "Information added successfully.");
    }

    private static void searchInfo() {
        String name = JOptionPane.showInputDialog("Enter name to search:");
        String result = "";
        for (int i = 0; i < count; i++) {
            if (directory[i][0].equalsIgnoreCase(name)) {
                result += directory[i][0] + ": " + directory[i][1] + "\n";
            }
        }
        if (result.equals("")) {
            JOptionPane.showMessageDialog(null, "No information found.");
        } else {
            JOptionPane.showMessageDialog(null, result);
        }
    }

    private static void loadDirectory() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILENAME));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                directory[count][0] = parts[0];
                directory[count][1] = parts[1];
                count++;
            }
            br.close();
        } catch (IOException e) {
            // do nothing if file doesn't exist
        }
    }

    private static void saveDirectory() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
            for (int i = 0; i < count; i++) {
                bw.write(directory[i][0] + "," + directory[i][1] + "\n");
            }
            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving directory.");
        }
    }
}
