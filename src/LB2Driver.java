import java.util.Scanner;

/**
 * Runs an practice setting or machine learning algorithm to crack an LB2
 * key
 *
 * @author Austin Herring
 * @version 1.0
 */
public class LB2Driver {

    public static void main(String args[]) {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.println("What do you want to do? Press the a " +
                    "number.");
            System.out.println("1 Generate a password for a website");
            System.out.println("2 Let me try to break your schema");
            System.out.println("3 Quit\n");
            int choice;

            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            if (choice == 1) {
                practice();
            } else if (choice == 2) {
                hack();
            } else if (choice == 3) {
                running = false;
            } else {
                System.out.println("\nPLEASE ENTER 1, 2 OR 3\n");
            }
        }
    }

    private static void practice() {
        boolean practicing = true;
        String challenge;
        while (practicing) {
            boolean website = true;
            Scanner input = new Scanner(System.in);
            System.out.println("What is the first word of your key?");
            String word1 = input.nextLine();
            System.out.println("What is the second word of your key?");
            String word2 = input.nextLine();
            System.out.println("What is the third word of your key?");
            String word3 = input.nextLine();
            PasswordGenerator g = new PasswordGenerator(word1, word2, word3);
            System.out.println("Ok, enter a website like " +
                    "'Yahoo' or 'WellsFargo'");
            while (website) {
                System.out.println("What website do you want a password for?");
                challenge = input.nextLine();
                System.out.println("This should you be your password: "
                        + g.generate(challenge));
                boolean answer = true;
                while (answer) {
                    System.out.println("\n\nWould you like to try an new " +
                            "website?");
                    System.out.println("1 Yes!");
                    System.out.println("2 No thank you.");
                    int choice;

                    try {
                        choice = Integer.parseInt(input.nextLine());
                    } catch (NumberFormatException e) {
                        choice = -1;
                    }
                    if (choice == 1) {
                        answer = false;
                    } else if (choice == 2) {
                        answer = false;
                        website = false;
                        practicing = false;
                    } else {
                        System.out.println("\nPlease enter 1 or 2\n");
                    }
                }
            }
        }
    }

    private static void hack() {
        System.out.println("You will enter a website and I will guess " +
                "what your password would be. If I am wrong, then inform me " +
                "of the correct password. Lets see how many tries it will " +
                "take me");
        boolean running = true;
        SchemaBreaker breaker = new SchemaBreaker();
        Scanner input = new Scanner(System.in);
        int tries = 1;
        String info;
        while (running) {
            System.out.println("Enter your website.");
            System.out.println("You can quit by entering 'quit'");
            info = input.nextLine();
            if (info.equals("quit")) {
                running = false;
            } else {
                breaker.takeChallenge(info);
                boolean answering = true;
                while (answering) {
                    System.out.println("Is your password " + breaker.guess(info)
                            + "?\nEnter 'yes' or 'no'");
                    info = input.nextLine();
                    if (info.equals("no")) {
                        System.out.println("Darn, ok what is the correct " +
                                "password then?");
                        breaker.takeFeedback(input.nextLine());
                        System.out.println("Lets try again then.");
                        answering = false;
                    } else if (info.equals("yes")) {
                        System.out.println("Hooray!! It took me " + tries +
                                " tries to guess");
                        answering = false;
                        running = false;
                    } else {
                        System.out.println("That was not a yes or no answer.");
                    }
                }
            }
            tries++;
        }
    }
}
