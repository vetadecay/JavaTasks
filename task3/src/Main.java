import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeController controller = new EmployeeController();
        EmployeeView view = new EmployeeView();

        controller.addEmployee(new Employee("Alice", 30, 50000));
        controller.addEmployee(new Employee("Bob", 25, 45000));
        controller.addEmployee(new Employee("Charlie", 35, 70000));
        controller.addEmployee(new Employee("Diana", 28, 52000));
        controller.addEmployee(new Employee("Edward", 40, 65000));
        controller.addEmployee(new Employee("Fiona", 32, 48000));
        controller.addEmployee(new Employee("George", 29, 51000));
        controller.addEmployee(new Employee("Helen", 27, 47000));
        controller.addEmployee(new Employee("Ian", 31, 49000));
        controller.addEmployee(new Employee("Jack", 26, 46000));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            view.displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    view.displayEmployees(controller.getEmployees());
                    break;
                case 2:
                    controller.sortByName();
                    System.out.println("Sorted by name.");
                    break;
                case 3:
                    controller.sortByAge();
                    System.out.println("Sorted by age.");
                    break;
                case 4:
                    controller.sortBySalary();
                    System.out.println("Sorted by salary.");
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
