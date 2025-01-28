import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductGenerator {
    public ProductGenerator() {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean doneInput = false;
        ArrayList<Product> products = new ArrayList<>();

        do {
            String ID = SafeInput.getNonZeroLenString(in, "Enter the product ID"); // Should never change
            String name = SafeInput.getNonZeroLenString(in, "Enter the product name");
            String description = SafeInput.getNonZeroLenString(in, "Enter the product description");
            double cost = SafeInput.getRangedDouble(in, "Enter the product cost", 0, 1000000);

            Product product = new Product(ID, name, description, cost);
            products.add(product);

            System.out.println("✅ Record added: " + product.toCSV());
            doneInput = SafeInput.getYNConfirm(in, "Are you done entering products? [Y/N]");
        } while (!doneInput);

        String fileName = SafeInput.getNonZeroLenString(in, "Enter the name of the file to save (e.g., ProductData.txt)");
        Path filePath = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Product product : products) {
                writer.write(product.toCSV());
                writer.newLine();
            }

            System.out.println("✅ Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }

        in.close();
    }
}
