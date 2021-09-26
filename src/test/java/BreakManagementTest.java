import breaks.PayoutBreak;
import employee.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BreakManagementTest {

    @Test
    void addBreak_NoEmployeePresent_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());
        PayoutBreak payoutBreakToAdd = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN, LocalDate.MIN,
                LocalDate.of(1880, 1, 1));

        assertTrue(breakManagement.addBreak(payoutBreakToAdd));
    }

    @Test
    void addBreak_EmployeePresent_True() {
        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(1990, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);

        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());
        breakManagement.addEmployee(employeeToAdd);
        PayoutBreak payoutBreakToAdd = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(1880, 1, 1));

        assertTrue(breakManagement.addBreak(payoutBreakToAdd));
    }

    @Test
    void addBreak_EmployeePresent_False() {
        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(1990, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);

        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());
        breakManagement.addEmployee(employeeToAdd);
        PayoutBreak payoutBreakToAdd = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));

        assertFalse(breakManagement.addBreak(payoutBreakToAdd));
    }

    @Test
    void addBreak_OthersBreakExist_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        PayoutBreak payoutBreakToAdd = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToAdd);

        PayoutBreak payoutBreakToTest= new PayoutBreak(LocalTime.of(0, 12), LocalTime.MIN,
                LocalDate.of(2001, 12, 17), LocalDate.of(2002, 12, 17));

        assertTrue(breakManagement.addBreak(payoutBreakToTest));
    }

    @Test
    void addBreak_OthersBreakExist_False() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        PayoutBreak payoutBreakToAdd = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToAdd);

        PayoutBreak payoutBreakToTest= new PayoutBreak(LocalTime.of(0, 12), LocalTime.MIN,
                LocalDate.of(1990, 12, 17), LocalDate.of(2002, 12, 17));

        assertFalse(breakManagement.addBreak(payoutBreakToTest));
    }

    @Test
    void removeBreak_NoEmployeePresent_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        assertTrue(breakManagement.removeBreak(payoutBreakToTest));
    }

    @Test
    void removeBreak_EmployeePresent_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(2005, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);
        breakManagement.addEmployee(employeeToAdd);

        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        assertTrue(breakManagement.removeBreak(payoutBreakToTest));
    }

    @Test
    void removeBreak_EmployeePresent_False() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(1990, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);

        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        breakManagement.addEmployee(employeeToAdd);

        assertFalse(breakManagement.removeBreak(payoutBreakToTest));
    }

    @Test
    void changePayoutBreakTime_NoEmployeePresent_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        assertAll(
                () -> assertTrue(breakManagement.changePayoutBreakTime(LocalTime.of(1, 10), 0)),
                () -> assertEquals(LocalTime.of(1, 10), breakManagement.getBreakStorage().get(0).getPayoutBreakTime())
        );
    }

    @Test
    void changePayoutBreakTime_EmployeePresent_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(2005, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);
        breakManagement.addEmployee(employeeToAdd);

        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        assertAll(
                () -> assertTrue(breakManagement.changePayoutBreakTime(LocalTime.of(1, 10), 0)),
                () -> assertEquals(LocalTime.of(1, 10), breakManagement.getBreakStorage().get(0).getPayoutBreakTime())
        );
    }

    @Test
    void changePayoutBreakTime_EmployeePresent_False() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(1990, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);

        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        breakManagement.addEmployee(employeeToAdd);

        assertFalse(breakManagement.changePayoutBreakTime(LocalTime.of(1, 10), 0));
    }

    @Test
    void changeRequiredWorkTime_NoEmployeePresent_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        assertAll(
                () -> assertTrue(breakManagement.changeRequiredWorkTime(LocalTime.of(7, 0), 0)),
                () -> assertEquals(LocalTime.of(7, 0), breakManagement.getBreakStorage().get(0).getRequiredWorkTime())
        );
    }

    @Test
    void changeRequiredWorkTime_EmployeePresent_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(2005, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);
        breakManagement.addEmployee(employeeToAdd);

        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        assertAll(
                () -> assertTrue(breakManagement.changeRequiredWorkTime(LocalTime.of(7, 0), 0)),
                () -> assertEquals(LocalTime.of(7, 0), breakManagement.getBreakStorage().get(0).getRequiredWorkTime())
        );
    }

    @Test
    void changeRequiredWorkTime_EmployeePresent_False() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());

        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(1990, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);

        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.MIN, LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        breakManagement.addEmployee(employeeToAdd);

        assertFalse(breakManagement.changeRequiredWorkTime(LocalTime.of(1, 0), 0));
    }

    @Test
    void breakTimeReduction_NoEmployeePresent_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());
        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.of(1990, 1, 1), LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        assertAll(
                () -> assertTrue(breakManagement.breakTimeReduction(LocalDate.of(1990, 1, 1),
                        LocalDate.of(1999, 1, 1), 0)),
                () -> assertEquals(LocalDate.of(1990, 1, 1), breakManagement.getBreakStorage().get(0).getValidFrom()),
                () -> assertEquals(LocalDate.of(1999, 1, 1), breakManagement.getBreakStorage().get(0).getValidTo())
        );
    }

    @Test
    void breakTimeReduction_EmployeePresent_True() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());
        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.of(1990, 1, 1), LocalDate.of(2000, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(2015, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);
        breakManagement.addEmployee(employeeToAdd);

        assertAll(
                () -> assertTrue(breakManagement.breakTimeReduction(LocalDate.of(1990, 1, 1),
                        LocalDate.of(1999, 1, 1), 0)),
                () -> assertEquals(LocalDate.of(1990, 1, 1), breakManagement.getBreakStorage().get(0).getValidFrom()),
                () -> assertEquals(LocalDate.of(1999, 1, 1), breakManagement.getBreakStorage().get(0).getValidTo())
        );
    }

    @Test
    void breakTimeReduction_EmployeePresent_False() {
        BreakManagement breakManagement = new BreakManagement(new ArrayList<>(), new ArrayList<>());
        PayoutBreak payoutBreakToTest = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN,
                LocalDate.of(1990, 1, 1), LocalDate.of(2001, 1, 1));
        breakManagement.addBreak(payoutBreakToTest);

        List<LocalDate> employeePresentList = new ArrayList<>();
        employeePresentList.add(LocalDate.of(2000, 12, 16));
        Employee employeeToAdd = new Employee(employeePresentList);
        breakManagement.addEmployee(employeeToAdd);


        assertFalse(breakManagement.breakTimeReduction(LocalDate.of(1990, 1, 1),
                LocalDate.of(1999, 1, 1), 0));
    }
}