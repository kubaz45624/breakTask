import breaks.PayoutBreak;
import employee.Employee;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args)  {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Employee employee = new Employee(LocalDate.MIN);
        Employee employeeOne = new Employee(LocalDate.of(1990, 12, 16));
        Employee employeeTwo = new Employee(LocalDate.of(1990, 12, 12));
        Employee employeeThree = new Employee(LocalDate.of(2000, 12, 11));
        Employee employeeFour = new Employee(LocalDate.of(1990, 12, 17));
        Employee employeeFive = new Employee(LocalDate.of(2000, 12, 17));

        EmployeeStorage employeeStorage = new EmployeeStorage(new LinkedList<>());
        BreakManagement breakManagement = new BreakManagement(new LinkedList<>(), new LinkedList<>());

        breakManagement.addEmployee(employeeOne);
        breakManagement.addEmployee(employeeTwo);
        breakManagement.addEmployee(employeeThree);
        breakManagement.addEmployee(employeeFour);



        PayoutBreak payoutBreak = new PayoutBreak(LocalTime.of(0, 11), LocalTime.MIN, LocalDate.MIN, LocalDate.MAX);
        PayoutBreak payoutBreakOne = new PayoutBreak(LocalTime.of(0, 12), LocalTime.MIN, LocalDate.of(2001, 12, 17), LocalDate.of(2002, 12, 17));
        PayoutBreak payoutBreakTwo = new PayoutBreak(LocalTime.of(0, 14), LocalTime.MIN, LocalDate.of(2000, 12, 17), LocalDate.of(2001, 11, 17));
        breakManagement.addBreak(payoutBreak);
        breakManagement.addBreak(payoutBreakOne);
        breakManagement.addBreak(payoutBreakOne);
        breakManagement.addBreak(payoutBreakTwo);
        breakManagement.getBreakStorage().forEach(i -> System.out.println(i.getValidFrom()));
        System.out.println("---");
        breakManagement.addEmployee(employeeFive);
        breakManagement.breakTimeReduction(LocalDate.of(2001, 1, 17), LocalDate.of(2001, 10, 17), 1);
        breakManagement.getBreakStorage().forEach(i -> System.out.println(i.getValidFrom()));

    }
}
