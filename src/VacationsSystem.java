import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class VacationsSystem {
    public static void main(String[] args) {
        printUserOptions();
    }

    public static void printUserOptions() {
        Scanner input = new Scanner(System.in);
        printApplicationOptions();
        System.out.println("Въведи избор:");
        int userChoice = input.nextInt();
        switch (userChoice) {
            case 1:
                writeVacationToFile();
                break;
            case 2:
                showAllVacations();
                break;
        }

    }

    public static void printApplicationOptions() {
        System.out.println("-------------------------------------------");
        System.out.println("  1. Заяви отпуска.");
        System.out.println("  2. Виж всички отпуски.");
        System.out.println("  3. Виж отпуска за служител.");
        System.out.println("  4. Промени статус на отпуска");
        System.out.println("  5. Изход.");
        System.out.println("-------------------------------------------");
    }

    public static String[] returnUserDetails() {
        String[] vacationDetails = new String[6];
        vacationDetails[0] = returnFirstName();
        vacationDetails[1] = returnLastName();
        vacationDetails[2] = returnEmail();
        vacationDetails[3] = returnUserId();
        vacationDetails[4] = returnVacationPeriod();
        vacationDetails[5] = returnVacationType();
        return vacationDetails;
    }

    public static void writeVacationToFile() {
        String[] vacation = returnUserDetails();
        try (
                PrintWriter writer = new PrintWriter(
                        new BufferedWriter(
                                new FileWriter("vacations.txt", true)))) {
            for (int i = 0; i < vacation.length - 1; i++) {
                writer.print(vacation[i] + "\t");
            }
            writer.println(vacation[5]);
            System.out.println("Заявката ви беше успешно записана.");
            writer.close();
            printUserOptions();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void showAllVacations() {
        Path patToFile = Paths.get("vacations.txt");
        if (Files.notExists(patToFile)) {
            System.out.println("Все още няма направени заявки за отпуска.");
            printUserOptions();
        } else {
            File vacations = new File("vacations.txt");
            String msg = "";
            try {
                Scanner scanner = new Scanner(vacations);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    msg += line + "\n";
                }
                System.out.println(msg);
            }
            catch (IOException e) {
                System.out.println(e);
            }

        }
    }


    public static String returnFirstName() {
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
        return firstName;
    }

    public static String returnLastName() {
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
        return lastName;
    }

    public static String returnEmail() {
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
        return email;
    }

    public static String returnUserId() {
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
        return id;
    }

    public static String returnVacationPeriod() {
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
        return vacationPeriod;
    }

    public static String returnVacationType() {
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
        return vacationType;
    }
}

