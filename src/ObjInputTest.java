import java.util.Scanner;

public class ObjInputTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SafeInputObj inputHelper = new SafeInputObj(scanner);


        // Test getNonZeroLenString
        String name = inputHelper.getNonZeroLenString("Enter your name");
        System.out.println("You entered: " + name);

        // Test getRangedInt
        int age = inputHelper.getRangedInt("Enter your age", 1, 100);
        System.out.println("You entered: " + age);

        // Test getInt
        int number = inputHelper.getInt("Enter any number");
        System.out.println("You entered: " + number);

        // Test getRangedDouble
        double temperature = inputHelper.getRangedDouble("Enter a temperature", -50, 50);
        System.out.println("You entered: " + temperature);

        // Test getDouble
        double weight = inputHelper.getDouble("Enter your weight in kg");
        System.out.println("You entered: " + weight);

        // Test getYNConfirm
        boolean confirm = inputHelper.getYNConfirm("Do you like to code?");
        System.out.println("You answered: " + (confirm ? "Yes" : "No"));

        // Test getRegExString
        String zipCode = inputHelper.getRegExString("Enter a 5-digit ZIP code", "\\d{5}");
        System.out.println("You entered: " + zipCode);

        scanner.close();

    }
}
