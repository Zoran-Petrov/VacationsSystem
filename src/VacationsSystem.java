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

        System.out.println("Моля въведете имейл:");
        String email = input.nextLine();
        if (email == null) {
            System.out.println("Моля въведете имейл!");
            email = input.nextLine();
        } else {
            while (!email.matches("^[A-Za-z0-9_\\-.]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-.]{2,}$")) {
                System.out.println("Моля въведете валиден имейл!");
                email = input.nextLine();
            }
        }

        System.out.println("Моля въведете ЕГН:");
        String id = input.nextLine();
        if (id == null || id.isEmpty()) {
            System.out.println("Моля въведете ЕГН!");
            id = input.nextLine();
        } else {
            while (!id.matches("[0-9]{10}")) {
                System.out.println("Моля въведете валиден ЕГН!");
                id = input.nextLine();
            }
        }

        System.out.println("Моля въведете начална и крайна дата в формат DD/MM/2019-DD/MM/2019:");
        String vacationPeriod = input.nextLine();
        if (vacationPeriod == null || vacationPeriod.isEmpty()) {
            System.out.println("Моля въведете начална и крайна дата!");
            vacationPeriod = input.nextLine();

        } else {
            String dateRegex = "[0-9]{2}/[0-9]{2}/(2019)-[0-9]{2}/[0-9]{2}/(2019)";
            while (!vacationPeriod.matches(dateRegex)) {
                System.out.println("Моля въведете начална и крайна дата в указаният формат!");
                vacationPeriod = input.nextLine();
            }
        }

        System.out.println("Моля въведете тип отпуска: платена/неплатена");
        String payed = "платена";
        String notPayed = "неплатена";
        String vacationType = input.nextLine();
        if (vacationType == null || vacationType.isEmpty()) {
            System.out.println("Моля въведете тип на отпуската!!");
            vacationType = input.nextLine();
        } else {
            while (!vacationType.equals(payed) && !vacationType.equals(notPayed)) {
                System.out.println("Моля въведете думата \"платена\" или думата \"неплатена\"!");
                vacationType = input.nextLine();

            }
        }

    }
    public static void getFirstName() {
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
    }

    public static void getLastName() {
        Scanner input = new Scanner(System.in);
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

    public static void getEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Моля въведете имейл:");
        String email = input.nextLine();
        if (email == null) {
            System.out.println("Моля въведете имейл!");
            email = input.nextLine();
        } else {
            while (!email.matches("^[A-Za-z0-9_\\-.]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-.]{2,}$")) {
                System.out.println("Моля въведете валиден имейл!");
                email = input.nextLine();
            }
        }
    }

    public static void getUserId() {
        Scanner input = new Scanner(System.in);
        System.out.println("Моля въведете ЕГН:");
        String id = input.nextLine();
        if (id == null || id.isEmpty()) {
            System.out.println("Моля въведете ЕГН!");
            id = input.nextLine();
        } else {
            while (!id.matches("[0-9]{10}")) {
                System.out.println("Моля въведете валиден ЕГН!");
                id = input.nextLine();
            }
        }
    }

    public static void getVacationPeriod() {
        Scanner input = new Scanner(System.in);
        System.out.println("Моля въведете начална и крайна дата в формат DD/MM/2019-DD/MM/2019:");
        String vacationPeriod = input.nextLine();
        if (vacationPeriod == null || vacationPeriod.isEmpty()) {
            System.out.println("Моля въведете начална и крайна дата!");
            vacationPeriod = input.nextLine();

        } else {
            String dateRegex = "[0-9]{2}/[0-9]{2}/(2019)-[0-9]{2}/[0-9]{2}/(2019)";
            while (!vacationPeriod.matches(dateRegex)) {
                System.out.println("Моля въведете начална и крайна дата в указаният формат!");
                vacationPeriod = input.nextLine();
            }
        }
    }

    public static void getVacationType() {
        Scanner input = new Scanner(System.in);
        System.out.println("Моля въведете тип отпуска: платена/неплатена");
        String payed = "платена";
        String notPayed = "неплатена";
        String vacationType = input.nextLine();
        if (vacationType == null || vacationType.isEmpty()) {
            System.out.println("Моля въведете тип на отпуската!!");
            vacationType = input.nextLine();
        } else {
            while (!vacationType.equals(payed) && !vacationType.equals(notPayed)) {
                System.out.println("Моля въведете думата \"платена\" или думата \"неплатена\"!");
                vacationType = input.nextLine();

            }
        }

    }

}

