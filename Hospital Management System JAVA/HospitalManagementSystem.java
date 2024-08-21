import java.util.Scanner;

class Patient {
    long ID;
    String firstname;
    String lastname;
    int age;
    String blood;
    char gender;
    Patient next;
}

class LinkedQueue {
    Patient head, last;
    String departmentName;

    LinkedQueue() {
        head = null;
        last = null;
    }

    Patient input() {
        Scanner scanner = new Scanner(System.in);
        Patient p = new Patient();
        int flag = 0;

        System.out.println("\n   Please enter data for patient");
        System.out.print("   First name     : ");
        p.firstname = scanner.nextLine();
        System.out.print("   Last name      : ");
        p.lastname = scanner.nextLine();

        do {
            System.out.print("   Blood Group    : ");
            p.blood = scanner.nextLine();
            if (p.blood.equalsIgnoreCase("A+") || p.blood.equalsIgnoreCase("A-") ||
                p.blood.equalsIgnoreCase("B+") || p.blood.equalsIgnoreCase("B-") ||
                p.blood.equalsIgnoreCase("O+") || p.blood.equalsIgnoreCase("O-") ||
                p.blood.equalsIgnoreCase("AB+") || p.blood.equalsIgnoreCase("AB-")) {
                flag = 1;
            } else {
                System.out.println("\n   Invalid Blood Group. Try Again..\n");
            }
        } while (flag == 0);

        System.out.print("   Gender(m/f)    : ");
        p.gender = scanner.next().charAt(0);
        System.out.print("   Age            : ");
        p.age = scanner.nextInt();
        System.out.print("   Mobile number  : ");
        p.ID = scanner.nextLong();

        if (search(p.ID)) {
            p.ID = 0;
            System.out.println("\n   Data not valid. Operation cancelled.");
        }
        return p;
    }

    boolean search(long item) {
        if (head == null) {
            return false;
        } else {
            Patient p = head;
            while (p.next != null && p.ID != item) {
                p = p.next;
            }
            return p.ID == item;
        }
    }

    void insertAtBeg() {
        Patient p = input();
        if (p.ID == 0)
            return;

        if (head == null) {
            head = p;
            last = p;
            p.next = null;
        } else {
            p.next = head;
            head = p;
        }
        System.out.println("\n\tPatient added:");
        output(p);
    }

    void insertAtEnd() {
        Patient p = input();
        if (p.ID == 0)
            return;

        if (head == null) {
            head = p;
            last = p;
            p.next = null;
        } else {
            p.next = null;
            last.next = p;
            last = p;
        }
        System.out.println("\n  ----------PATIENT ADDED-----------");
        output(p);
    }

    void getPatientOut() {
        if (head == null) {
            System.out.println("\n  No Patient to operate");
        } else {
            Patient p = head;
            head = head.next;
            System.out.println("\n  Patient to operate:");
            output(p);
        }
    }

    void listOfPatients() {
        if (head == null) {
            System.out.println("\n  No patient");
            return;
        }
        Patient p = head;
        while (p != null) {
            System.out.println("\n   Patient data:");
            System.out.println("   First Name       : " + p.firstname);
            System.out.println("   Last Name        : " + p.lastname);
            System.out.println("   Gender           : " + p.gender);
            System.out.println("   Age              : " + p.age);
            System.out.println("   Blood Group      : " + p.blood);
            System.out.println("   Mobile Number    : " + p.ID);
            p = p.next;
        }
    }

    void output(Patient p) {
        System.out.println("\n   Patient data:");
        System.out.println("   First Name       : " + p.firstname);
        System.out.println("   Last Name        : " + p.lastname);
        System.out.println("   Gender           : " + p.gender);
        System.out.println("   Age              : " + p.age);
        System.out.println("   Blood Group      : " + p.blood);
        System.out.println("   Mobile Number    : " + p.ID);
    }
}

public class HospitalManagementSystem {
    static int readNumber() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    static void departmentMenu(LinkedQueue q) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n  |-- HOSPITAL MANAGEMENT SYSTEM --|");
            System.out.println("\n   " + q.departmentName);
            System.out.println("   [1] Add normal patient");
            System.out.println("   [2] Add critically ill patient");
            System.out.println("   [3] Take patient to Doctor");
            System.out.println("   [4] Display list");
            System.out.println("   [5] Change department or exit");
            System.out.print("   Please enter your choice : ");
            choice = readNumber();

            switch (choice) {
                case 1:
                    q.insertAtEnd();
                    System.out.println("\n   Press any key");
                    new Scanner(System.in).nextLine();
                    break;
                case 2:
                    q.insertAtBeg();
                    System.out.println("\n   Press any key");
                    new Scanner(System.in).nextLine();
                    break;
                case 3:
                    q.getPatientOut();
                    System.out.println("\n   Press any key");
                    new Scanner(System.in).nextLine();
                    break;
                case 4:
                    q.listOfPatients();
                    System.out.println("\n   Press any key");
                    new Scanner(System.in).nextLine();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        LinkedQueue[] departments = new LinkedQueue[4];
        for (int i = 0; i < 4; i++) {
            departments[i] = new LinkedQueue();
        }

        departments[0].departmentName = "GENERAL CLINIC";
        departments[1].departmentName = "HEART CLINIC";
        departments[2].departmentName = "LUNG CLINIC";
        departments[3].departmentName = "PLASTIC SURGERY";

        int choice = 0;
        while (choice != 5) {
            System.out.println("\n  |-- HOSPITAL MANAGEMENT SYSTEM --|");
            System.out.println("   Main Menu");
            for (int i = 0; i < 4; i++) {
                System.out.println("   " + (i + 1) + ": " + departments[i].departmentName);
            }
            System.out.println("   5: Exit");
            System.out.print("   Please enter your choice : ");
            choice = readNumber();

            if (choice >= 1 && choice <= 4) {
                departmentMenu(departments[choice - 1]);
            }
        }
        if (choice == 5) {
            System.out.println("\n\t\tThank you! ");
            System.exit(0);
        }
    }
}
