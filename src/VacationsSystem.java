import java.util.Scanner;

public class VacationsSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printUserOptions();
        System.out.println("Въведи избор:");
        int userChoice = input.nextInt();
        switch (userChoice) {
            case 1:
                getUserDetails();
                break;
        }
    }

    public static void printUserOptions() {
        System.out.println("-------------------------------------------");
        System.out.println("  1. Заяви отпуска.");
        System.out.println("  2. Виж всички отпуски.");
        System.out.println("  3. Виж отпуска за служител.");
        System.out.println("  4. Промени статус на отпуска");
        System.out.println("  5. Изход.");
        System.out.println("-------------------------------------------");
    }

    public static void getUserDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("Моля въведете собствено име:");
        String firstName = input.nextLine();
        if (firstName == null) {
            System.out.println("Моля въведете име!");
            firstName = input.nextLine();
        } else {
            while (firstName.isEmpty() || firstName.length() < 2) {
                System.out.println("Моля въведете коректно собствено име!");
                firstName = input.nextLine();
            }
        }

        System.out.println("Моля въведете фамилно име:");
        String lastName = input.nextLine();
        if (lastName == null) {
            System.out.println("Моля въведете име!");
            lastName = input.nextLine();
        } else {
            while (lastName.isEmpty() || lastName.length() < 2) {
                System.out.println("Моля въведете коректно фамилно име!");
                lastName = input.nextLine();
            }
        }

        

    }


}

