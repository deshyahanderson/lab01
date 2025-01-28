import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean doneInput = false;
        String ID, name, description, rec;
        double cost;
        ArrayList<String> people = new ArrayList<>();

        // Step 1: Collect User Input
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter your ID");
            name = SafeInput.getNonZeroLenString(in, "Enter your name");
            description = SafeInput.getNonZeroLenString(in, "Enter a short description");
            cost = SafeInput.getRangedDouble(in, "Enter the cost", 0, 9999);

            rec = ID + ", " + name + ", " + description + ", " + cost;
            people.add(rec);
            System.out.println("Record added: " + rec);

            doneInput = SafeInput.getYNConfirm(in, "Are you done entering persons? [Y/N]");
        } while (!doneInput);

        // Step 2: Use JFileChooser to get the file path from the user
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file to save");
        int result = fileChooser.showSaveDialog(new JFrame());  // Create a JFrame to hold the dialog
        if (result == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            Path filePath = Paths.get(fileName);

            // Step 3: Write Data to File Using NIO
            try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                for (String person : people) {
                    writer.write(person);
                    writer.newLine();
                }
                System.out.println("✅ Data successfully saved to " + fileName);
            } catch (IOException e) {
                System.out.println("❌ Error writing to file: " + e.getMessage());
            }

            // Step 4: Read the File Back and Display Contents Using NIO
            System.out.println("\n🔍 Verifying written data:");
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                int lineNum = 1;
                while ((line = reader.readLine()) != null) {
                    System.out.printf("Line %d: %s%n", lineNum++, line);
                }
            } catch (IOException e) {
                System.out.println("❌ Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("❌ No file selected. Exiting...");
        }

        in.close();
    }
}
