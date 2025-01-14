import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeController {
    private List<Employee> employees;

    public EmployeeController() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void sortByName() {
        employees.sort(Comparator.comparing(Employee::getName));
    }

    public void sortByAge() {
        employees.sort(Comparator.comparingInt(Employee::getAge));
    }

    public void sortBySalary() {
        employees.sort(Comparator.comparingDouble(Employee::getSalary));
    }
}
