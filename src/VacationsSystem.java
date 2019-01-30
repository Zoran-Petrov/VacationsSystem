import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class VacationsSystem {
    public static final int FIRST_NAME_WIDTH = 15;
    public static final int LAST_NAME_WIDTH = 20;
    public static final int EMAIL_WIDTH = 35;
    public static final int ID_WIDTH = 20;
    public static final int VACATION_PERIOD_WIDTH = 35;
    public static final int VACATION_TYPE_WIDTH = 150;

    public static void main(String[] args) {
        printUserOptions();
    }

    public static void printUserOptions() {
        Scanner input = new Scanner(System.in);
        printApplicationOptions();
        System.out.println("Въведи избор:");
        String userChoice = input.nextLine();
        while (!userChoice.matches("[1-5]")) {
            System.out.println("Моля въведете число между 1 и 5:");
            userChoice = input.nextLine();
        }
        switch (Integer.parseInt(userChoice)) {
            case 1:
                writeVacationToFile();
                break;
            case 2:
                showAllVacations();
                break;
            case 3:
                //showUserVacations();
                break;
            case 4:
                changeVacationStatus();
                break;
            case 5:
                exitProgram();
                break;
            default:
                System.out.println("Въведи избор между 1 и 5!");
                printUserOptions();
        }
    }

    public static void printApplicationOptions() {
        System.out.println("-------------------------------------------");
        System.out.println("  1. Заяви отпуска.");
        System.out.println("  2. Виж всички отпуски.");
        System.out.println("  3. Виж отпуска за служител.");
        System.out.println("  4. Промени статус на отпуска.");
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
        String fetchedVacations = fetchAllVacationsFromFile();
        formatVacationsAndDisplay(fetchedVacations);
        printUserOptions();
    }

    public static String fetchAllVacationsFromFile() {
        Path patToFile = Paths.get("vacations.txt");
        if (Files.notExists(patToFile) || patToFile.toFile().length() == 0) {
            System.out.println("Все още няма заявени отпуски.");
            printUserOptions();
            return "";
        } else {
            File vacations = new File("vacations.txt");
            StringBuilder vacationsSB = new StringBuilder();
            Scanner scanner = null;
            try {
                scanner = new Scanner(vacations);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                vacationsSB.append(line + "\n");
            }
            String allVacations = vacationsSB.toString();
            return allVacations;
        }
    }

    public static void formatVacationsAndDisplay(String vacationsString) {
        String[] vacationsArray;
        vacationsArray = vacationsString.split("\n");
        printVacationsTableHeader();
        for (int i = 0; i < vacationsArray.length; i++) {
            String[] row = vacationsArray[i].split("\t");
            printVacations(row);
        }
    }

    public static void printVacationsTableHeader() {
        String firstName = "Име";
        String lastName = "Фамилия";
        String email = "Имейл";
        String id = "ЕГН";
        String vacationPeriod = "Начална дата - крайна дата";
        String vacationType = "Тип отпуска";
        System.out.print(firstName);
        for (int j = 0; j < FIRST_NAME_WIDTH - firstName.length(); j++) {
            System.out.print(" ");
        }
        System.out.print(lastName);
        for (int j = 0; j < LAST_NAME_WIDTH - lastName.length(); j++) {
            System.out.print(" ");
        }
        System.out.print(email);
        for (int j = 0; j < EMAIL_WIDTH - email.length(); j++) {
            System.out.print(" ");
        }
        System.out.print(id);
        for (int j = 0; j < ID_WIDTH - id.length(); j++) {
            System.out.print(" ");
        }
        System.out.print(vacationPeriod);
        for (int j = 0; j < VACATION_PERIOD_WIDTH - vacationPeriod.length(); j++) {
            System.out.print(" ");
        }
        System.out.println(vacationType);
        for (int j = 0; j < VACATION_TYPE_WIDTH; j++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void showUserVacations() {
        String allVacations = fetchAllVacationsFromFile();
        String[] userFirstAndLastNames = new String[2];
        userFirstAndLastNames[0] = returnFirstName();
        userFirstAndLastNames[1] = returnLastName();
        String[] vacationsArray = allVacations.split("\n");
        int indexFirstName;
        int indexLastName;
        int occurrenceCounter = 0;
        boolean isTableHeaderPrinted = false;
        for (int i = 0; i < vacationsArray.length; i++) {
            indexFirstName = vacationsArray[i].toLowerCase().indexOf(userFirstAndLastNames[0].toLowerCase());
            indexLastName = vacationsArray[i].toLowerCase().indexOf(userFirstAndLastNames[1].toLowerCase());
            if ((indexFirstName == -1) || (indexLastName == -1)) {
                continue;
            }
            {
                if (!isTableHeaderPrinted) {
                    printVacationsTableHeader();
                }
                String[] row = vacationsArray[i].split("\t");
                printVacations(row);
                occurrenceCounter++;
            }
            isTableHeaderPrinted = true;
        }
        if (occurrenceCounter != 0) {
            printUserOptions();
        } else {
            System.out.println();
            System.out.println("Този служител все още няма заявени отпуски.");
            printUserOptions();
        }
    }

    public static void printVacations(String[] vacations) {
        String[] row = vacations;
        System.out.print(row[0]);
        for (int j = 0; j < FIRST_NAME_WIDTH - row[0].length(); j++) {
            System.out.print(" ");
        }
        System.out.print(row[1]);
        for (int j = 0; j < LAST_NAME_WIDTH - row[1].length(); j++) {
            System.out.print(" ");
        }
        System.out.print(row[2]);
        for (int j = 0; j < EMAIL_WIDTH - row[2].length(); j++) {
            System.out.print(" ");
        }
        System.out.print(row[3]);
        for (int j = 0; j < ID_WIDTH - row[3].length(); j++) {
            System.out.print(" ");
        }
        System.out.print(row[4]);
        for (int j = 0; j < VACATION_PERIOD_WIDTH - row[4].length(); j++) {
            System.out.print(" ");
        }
        System.out.println(row[5]);

    }

    public static void changeVacationStatus() {
        System.out.println("Тази опция все още не е реализирана. Моля изберете друга!");
        printUserOptions();
    }

    public static String returnFirstName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Моля въведете собствено име:");
        String firstName = input.nextLine();
        if (firstName == null) {
            System.out.println("Моля въведете име!");
            firstName = input.nextLine();
        } else {
            firstName = firstName.trim();
            while (firstName.isEmpty() || !firstName.matches("^[А-ЩЮ-Я][а-я]{0,10}[а-щю-я]$")) {
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
            lastName = lastName.trim();
            while (lastName.isEmpty() || !lastName.matches("^[А-ЩЮ-Я][а-я]{0,10}[а-щю-я]$")) {
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
            email = email.trim();
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
            id = id.trim();
            while (!id.matches("[0-9]{10}")) {
                System.out.println("Моля въведете валиден ЕГН!");
                id = input.nextLine();
            }
        }
        return id;
    }

    public static String returnVacationPeriod() {
        Scanner input = new Scanner(System.in);
        System.out.println("Моля въведете начална дата в формат DD/MM/2019");
        String startDate = input.nextLine();
        if (startDate == null || startDate.isEmpty()) {
            System.out.println("Моля въведете валидна начална дата в указаният формат!");
            startDate = input.nextLine();
        } else {
            String dateRegex = "[0-9]{2}/[0-9]{2}/(2019)";
            while (!startDate.matches(dateRegex) || !validateDate(startDate)) {
                System.out.println("Моля въведете валидна начална дата в указаният формат!");
                startDate = input.nextLine();
            }
        }
        System.out.println("Моля въведете крайна дата в формат DD/MM/2019");
        String endDate = input.nextLine();
        if (endDate == null || endDate.isEmpty()) {
            System.out.println("Моля въведете валидна крайна дата в указаният формат!");
            endDate = input.nextLine();
        } else {
            String dateRegex = "[0-9]{2}/[0-9]{2}/(2019)";
            while (!endDate.matches(dateRegex) || !validateDate(endDate)) {
                System.out.println("Моля въведете валидна крайна дата в указаният формат!");
                endDate = input.nextLine();
            }

        }
        String vacationPeriod = startDate.concat(" - ").concat(endDate);

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
            vacationType = vacationType.trim();
            while (!vacationType.equals(payed) && !vacationType.equals(notPayed)) {
                System.out.println("Моля въведете думата \"платена\" или думата \"неплатена\"!");
                vacationType = input.nextLine();
            }
        }
        return vacationType;
    }

    public static boolean validateDate(String date) {
        String stringDate = date;
        String[] dateArray = stringDate.split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        boolean isDateValid;
        if (day == 0 || month > 12) {
            isDateValid = false;
        } else if (month == 0) {
            isDateValid = false;
        } else if (month == 1 && day > 31) {
            isDateValid = false;
        } else if (month == 2 && day > 28) {
            isDateValid = false;
        } else if (month == 3 && day > 31) {
            isDateValid = false;
        } else if (month == 4 && day > 30) {
            isDateValid = false;
        } else if (month == 5 && day > 31) {
            isDateValid = false;
        } else if (month == 6 && day > 30) {
            isDateValid = false;
        } else if (month == 7 && day > 31) {
            isDateValid = false;
        } else if (month == 8 && day > 31) {
            isDateValid = false;
        } else if (month == 9 && day > 30) {
            isDateValid = false;
        } else if (month == 10 && day > 31) {
            isDateValid = false;
        } else if (month == 11 && day > 30) {
            isDateValid = false;
        } else if (month == 12 && day > 31) {
            isDateValid = false;
        } else {
            isDateValid = true;
        }
        return isDateValid;
    }

    public static void exitProgram() {
        System.out.println("Излязохте от програмата.");
        System.exit(0);
    }
}

