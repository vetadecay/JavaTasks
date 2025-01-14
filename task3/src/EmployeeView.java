import java.util.List;

public class EmployeeView {
    public void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    public void displayMenu() {
        System.out.println("\\nMenu:");
        System.out.println("1. Display employees");
        System.out.println("2. Sort by name");
        System.out.println("3. Sort by age");
        System.out.println("4. Sort by salary");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
}
