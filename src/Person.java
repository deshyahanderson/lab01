import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Person {

    private String ID= "";
    private String firstName= "";
    private String lastName= "";
    private String title= "";
    private int YOB= 0;

    public Person(String ID, String firstName, String lastName, String title, int YOB) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.YOB = YOB;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYOB() {
        return YOB;
    }

    public void setYOB(int YOB) {
        this.YOB = YOB;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public String getFormalName() {
        return title + ". " + getFullName();
    }

    public String getAge(){
        int currentYear= Year.now().getValue();
        int currentAge= currentYear - YOB;
        return String.valueOf(currentAge);}

    public String getAge(int targetYear){
        if (targetYear < YOB){
            return "Specified year is before year of birth";
        }

        Calendar birthdate= new GregorianCalendar(YOB, 0, 1);
        Calendar targetDate= new GregorianCalendar(targetYear, 0,1);
        int age= targetDate.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
        return String.valueOf(age);
    }

    public String toCSV() {
        return ID + "," + firstName + "," + lastName + "," + title + "," + YOB;
    }

    public void writeToCSV(String filename) throws IOException {
        ArrayList<String> people = new ArrayList<>();
        people.add(toCSV());

        Path csvPath = Paths.get(filename);

        try (BufferedWriter writer = Files.newBufferedWriter(csvPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String person : people) {
                writer.write(person);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Error writing to CSV file: " + e.getMessage());
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return YOB == person.YOB && Objects.equals(ID, person.ID) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(title, person.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, firstName, lastName, title, YOB);
    }
}