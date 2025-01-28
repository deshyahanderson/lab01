import java.util.Scanner;

public class SafeInputObj {

    Scanner pipe;

    public SafeInputObj() {
        this.pipe = new Scanner(System.in);
    }

    public SafeInputObj(Scanner scanner) {
        this.pipe = scanner;
    }
        /**
         * Get a String which contains at least one character
         * @param prompt prompt for the user
         * @return a String response that is not zero length
         */
        public String getNonZeroLenString(String prompt)
        {
            String retString = "";
            do
            {
                System.out.print("\n" + prompt + ": ");
                retString = pipe.nextLine();
            }while(retString .length() == 0); // until we have some characters

            return retString;

        }
        /**
         * Get an int value within a specified numeric range
         * @param prompt - input prompt msg should not include range info
         * @param low - low end of inclusive range
         * @param high - high end of inclusive range
         * @return - int value within the inclusive range
         */
        public int getRangedInt(String prompt, int low, int high)
        {
            int retVal = 0;
            String trash = "";
            boolean done = false;

            do
            {
                System.out.print("\n" + prompt + "[" + low + "-" + high + "]: ");
                if(pipe.hasNextInt())
                {
                    retVal = pipe.nextInt();
                    pipe.nextLine();
                    if(retVal >= low && retVal <= high)
                    {
                        done = true;
                    }
                    else
                    {
                        System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                    }
                }
                else
                {
                    trash = pipe.nextLine();
                    System.out.println("You must enter an int: " + trash);
                }
            }while(!done);

            return retVal;
        }

        /**
         * Get an int value with no constraints
         * @param prompt - input prompt msg should not include range info
         * @return - unconstrained int value
         */
        public int getInt(String prompt)
        {
            int retVal = 0;
            String trash = "";
            boolean done = false;

            do
            {
                System.out.print("\n" + prompt + ": ");
                if(pipe.hasNextInt())
                {
                    retVal = pipe.nextInt();
                    pipe.nextLine();
                    done = true;
                }
                else
                {
                    trash = pipe.nextLine();
                    System.out.println("You must enter an int: " + trash);
                }
            }while(!done);

            return retVal;
        }


        /**
         * get a double value within an inclusive range
         * @param prompt - input prompt msg should not contain range info
         * @param low - low value inclusive
         * @param high - high value inclusive
         * @return  - double value within the specified inclusive range
         */
        public double getRangedDouble(String prompt, double low, double high)
        {
            double retVal = 0;
            String trash = "";
            boolean done = false;

            do
            {
                System.out.print("\n" + prompt + "[" + low + "-" + high + "]: ");
                if(pipe.hasNextDouble())
                {
                    retVal = pipe.nextDouble();
                    pipe.nextLine();
                    if(retVal >= low && retVal <= high)
                    {
                        done = true;
                    }
                    else
                    {
                        System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                    }
                }
                else
                {
                    trash = pipe.nextLine();
                    System.out.println("You must enter a double: " + trash);
                }
            }while(!done);

            return retVal;
        }

        /**
         * Get an unconstrained double value
         * @param prompt - input prompt msg should not contain range info
         * @return  - an unconstrained double value
         */
        public double getDouble(String prompt)
        {
            double retVal = 0;
            String trash = "";
            boolean done = false;

            do
            {
                System.out.print("\n" + prompt + ": ");
                if(pipe.hasNextDouble())
                {
                    retVal = pipe.nextDouble();
                    pipe.nextLine();
                    done = true;
                }
                else
                {
                    trash = pipe.nextLine();
                    System.out.println("You must enter a double: " + trash);
                }
            }while(!done);

            return retVal;
        }

        /**
         * Get a [Y/N] confirmation from the user
         * @param prompt -input prompt msg for user does not need [Y/N]
         * @return - true for yes false for no
         */
        public boolean getYNConfirm(String prompt)
        {
            boolean retVal = true;
            String response = "";
            boolean gotAVal = false;

            do
            {
                System.out.print("\n" + prompt + " [Y/N] ");
                response = pipe.nextLine();
                if(response.equalsIgnoreCase("Y"))
                {
                    gotAVal = true;
                    retVal = true;
                }
                else if(response.equalsIgnoreCase("N"))
                {
                    gotAVal = true;
                    retVal = false;
                }
                else
                {
                    System.out.println("You must answere [Y/N]! " + response );
                }

            }while(!gotAVal);

            return retVal;
        }
        /**
         * Get a string that matches a RegEx pattern! This is a very powerful method
         * @param prompt - prompt for user
         * @param regExPattern - java style RegEx pattern to constrain the input
         * @return a String that matches the RegEx pattern supplied
         */

        public String getRegExString(String prompt, String regExPattern)
        {
            String response;
            boolean gotAVal = false;

            do
            {
                System.out.print("\n" + prompt + ": ");
                response = pipe.nextLine();
                if(response.matches(regExPattern))
                {
                    gotAVal = true;
                }
                else
                {
                    System.out.println("\n" + response + " must match the pattern " + regExPattern);
                    System.out.println("Try again!");
                }

            }while(!gotAVal);

            return response;
        }


}
