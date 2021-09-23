package employee;

import java.time.LocalDate;
import java.util.List;

public class Employee {
    private final List<LocalDate> presents;

    public Employee(List<LocalDate> presents) {
        this.presents = presents;
    }

    public List<LocalDate> getPresents() {
        return presents;
    }

}
