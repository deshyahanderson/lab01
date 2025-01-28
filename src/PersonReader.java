import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class PersonReader {
    public static void main(String[] args) {
        // Initialize JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Person File");

        // Show the file chooser and get the result
        int result = fileChooser.showOpenDialog(null);

        // If the user selects a file
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            System.out.println("File selected: " + fileName);

            // Create an ArrayList to store Person objects
            ArrayList<Person> people = new ArrayList<>();

            // Step 2: Read the File Back and Display Contents Using NIO
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                System.out.printf("%-10s %-15s %-15s %-10s %-5s%n", "ID", "First Name", "Last Name", "Title", "YOB");
                System.out.println("------------------------------------------------------------");

                while ((line = reader.readLine()) != null) {
                    // Split using comma instead of spaces
                    String[] parts = line.split(",");

                    if (parts.length >= 5) {  // Ensure correct data format
                        String id = parts[0].trim();
                        String firstName = parts[1].trim();
                        String lastName = parts[2].trim();
                        String title = parts[3].trim();
                        int yob = Integer.parseInt(parts[4].trim());

                        // Create a new Person object
                        Person person = new Person(id, firstName, lastName, title, yob);
                        people.add(person);

                        // Print the formatted output
                        System.out.printf("%-10s %-15s %-15s %-10s %-5d%n", id, firstName, lastName, title, yob);
                    } else {
                        System.out.println("Invalid record format: " + line);
                    }
                }

                // Convert the list to JSON and XML
                System.out.println("\nJSON Output:");
                System.out.println(toJSON(people));

                System.out.println("\nXML Output:");
                System.out.println(toXML(people));

            } catch (IOException e) {
                System.out.println("❌ Error reading file: " + e.getMessage());
            }
        }
    }

    // Convert the list of Person objects to JSON
    public static String toJSON(ArrayList<Person> people) {
        StringBuilder json = new StringBuilder("[\n");
        for (Person p : people) {
            json.append(String.format(
                    "  {\"ID\": \"%s\", \"FirstName\": \"%s\", \"LastName\": \"%s\", \"Title\": \"%s\", \"YOB\": %d},\n",
                    p.getID(), p.getFirstName(), p.getLastName(), p.getTitle(), p.getYOB()
            ));
        }
        if (!people.isEmpty()) json.setLength(json.length() - 2); // Remove last comma
        json.append("\n]");
        return json.toString();
    }

    // Convert the list of Person objects to XML
    public static String toXML(ArrayList<Person> people) {
        StringBuilder xml = new StringBuilder("<People>\n");
        for (Person p : people) {
            xml.append(String.format(
                    "  <Person>\n    <ID>%s</ID>\n    <FirstName>%s</FirstName>\n    <LastName>%s</LastName>\n    <Title>%s</Title>\n    <YOB>%d</YOB>\n  </Person>\n",
                    p.getID(), p.getFirstName(), p.getLastName(), p.getTitle(), p.getYOB()
            ));
        }
        xml.append("</People>");
        return xml.toString();
    }
}