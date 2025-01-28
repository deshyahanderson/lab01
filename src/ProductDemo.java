import java.util.Scanner;

public class ProductDemo {

        public static void main(String[] args)
        {
            Scanner in = new Scanner(System.in);
            SafeInputObj sio = new SafeInputObj(in);

            Product one = new Product("00001", "The first product", "One",18.99);

            System.out.println(one.toCSV());

            String ID = sio.getNonZeroLenString("Enter your ID");
            System.out.println("ID is " + ID);
        }


}
