//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public PersonGenerator() {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean doneInput = false;
        ArrayList<Person> people = new ArrayList();

        do {
            String ID = SafeInput.getNonZeroLenString(in, "Enter your ID");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter your first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter your last name");
            String title = SafeInput.getNonZeroLenString(in, "Enter your title");
            int YOB = SafeInput.getRangedInt(in, "Enter your year of birth", 1000, 9999);
            Person person = new Person(ID, firstName, lastName, title, YOB);
            people.add(person);
            System.out.println("✅ Record added: " + person.toCSV());
            doneInput = SafeInput.getYNConfirm(in, "Are you done entering persons? [Y/N]");
        } while(!doneInput);

        String fileName = SafeInput.getNonZeroLenString(in, "Enter the name of the file to save (e.g., PersonTestData.txt)");
        Path filePath = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for(Person person : people) {
                writer.write(person.toCSV());
                writer.newLine();
            }

            System.out.println("✅ Data successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }

        in.close();
    }
}
