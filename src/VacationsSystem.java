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
        System.out.println("Моля въведете собствено име");
        String firstName = input.nextLine();
        System.out.println(firstName);


    }
}
