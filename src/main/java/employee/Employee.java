package employee;

import java.time.LocalDate;

public class Employee implements Comparable<Employee>{
    private final LocalDate employeePresent;

    public Employee(LocalDate employeePresent) {
        this.employeePresent = employeePresent;
    }

    public LocalDate getEmployeePresent() {
        return employeePresent;
    }

    @Override
    public int compareTo(Employee o) {
        if (this.employeePresent.isAfter(o.employeePresent)) {
            return 1;
        }
        return -1;
    }
}
