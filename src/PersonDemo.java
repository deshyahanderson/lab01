import java.util.Scanner;

public class PersonDemo
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        SafeInputObj sio = new SafeInputObj(in);

        Person boy = new Person("00001", "Bill", "Bailey","Dr",1942);
        Person girl = new Person("00001", "Sally", "Smith","Dr",1952);

        System.out.println(boy.getFullName());
        System.out.println(girl.getFormalName());
        System.out.println(boy.getAge());
        System.out.println(boy.getAge(2005));
        System.out.println(girl.toCSV());

        String ID = sio.getNonZeroLenString("Enter your ID");
        System.out.println("ID is " + ID);
    }

}
