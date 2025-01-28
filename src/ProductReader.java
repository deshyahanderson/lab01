import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProductReader {
    public static void main(String[] args) {
        // Initialize JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a Product File");

        // Show the file chooser and get the result
        int result = fileChooser.showOpenDialog(null);

        // If the user selects a file
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            System.out.println("File selected: " + fileName);

            // Create an ArrayList to store Product objects
            ArrayList<Product> products = new ArrayList<>();

            // Step 2: Read the File and Process the Data
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                System.out.printf("%-10s %-20s %-30s %-10s%n", "ID", "Name", "Description", "Cost");
                System.out.println("--------------------------------------------------------------");

                while ((line = reader.readLine()) != null) {
                    // Split using comma
                    String[] parts = line.split(",");

                    if (parts.length >= 4) {  // Ensure correct data format
                        String id = parts[0].trim();
                        String name = parts[1].trim();
                        String description = parts[2].trim(); // Fixed: Initialize description
                        double cost = Double.parseDouble(parts[3].trim());

                        // Create a new Product object
                        Product product = new Product(id, name, description, cost);
                        products.add(product);

                        // Print the formatted output
                        System.out.printf("%-10s %-20s %-30s %-10.2f%n", id, name, description, cost);
                    } else {
                        System.out.println("Invalid record format: " + line);
                    }
                }

                // Convert the list to JSON and XML
                System.out.println("\nJSON Output:");
                System.out.println(toJSON(products));

                System.out.println("\nXML Output:");
                System.out.println(toXML(products));

            } catch (IOException e) {
                System.out.println("❌ Error reading file: " + e.getMessage());
            }
        }
    }

    // Convert the list of Product objects to JSON
    public static String toJSON(ArrayList<Product> products) {
        StringBuilder json = new StringBuilder("[\n");
        for (Product p : products) {
            json.append(String.format(
                    "  {\"ID\": \"%s\", \"Name\": \"%s\", \"Description\": \"%s\", \"Cost\": %.2f},\n",
                    p.getID(), p.getName(), p.getDescription(), p.getCost() // Fixed: Correct JSON format
            ));
        }
        if (!products.isEmpty()) json.setLength(json.length() - 2); // Remove last comma
        json.append("\n]");
        return json.toString();
    }

    // Convert the list of Product objects to XML
    public static String toXML(ArrayList<Product> products) {
        StringBuilder xml = new StringBuilder("<Products>\n");
        for (Product p : products) {
            xml.append(String.format(
                    "  <Product>\n    <ID>%s</ID>\n    <Name>%s</Name>\n    <Description>%s</Description>\n    <Cost>%.2f</Cost>\n  </Product>\n",
                    p.getID(), p.getName(), p.getDescription(), p.getCost() // Fixed: Correct XML format
            ));
        }
        xml.append("</Products>");
        return xml.toString();
    }
}
