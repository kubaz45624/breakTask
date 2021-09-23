import breaks.PayoutBreak;
import employee.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BreakManagement {
    private final List<PayoutBreak> breakStorage;
    private final List<Employee> employeeStorage;

    public BreakManagement(List<PayoutBreak> breakStorage, List<Employee> employeeStorage) {
        this.breakStorage = breakStorage;
        this.employeeStorage = employeeStorage;
    }

    public void addEmployee(Employee employee) {
        employeeStorage.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employeeStorage.remove(employee);
    }

    public boolean addBreak(PayoutBreak payoutBreak) {
        if (employeePresent(payoutBreak)) return false;

        if (validDateRange(payoutBreak)) return false;
        breakStorage.add(payoutBreak);
        
        return true;
    }

    public boolean removeBreak(PayoutBreak payoutBreak) {
        if (employeePresent(payoutBreak)) {
            return false;
        }
        breakStorage.remove(payoutBreak);
        return true;
    }

    public boolean changePayoutBreakTime(LocalTime newTime, int breakIndex) {
        if (employeePresent(breakStorage.get(breakIndex))) {
            return false;
        }
        breakStorage.get(breakIndex).setPayoutBreakTime(newTime);
        return true;
    }

    public boolean changeRequiredWorkTime(LocalTime newTime, int breakIndex) {
        if (employeePresent(breakStorage.get(breakIndex))) {
            return false;
        }
        breakStorage.get(breakIndex).setRequiredWorkTime(newTime);
        return true;
    }

    public boolean breakTimeReduction(LocalDate newStart, LocalDate newEnd, int breakIndex) {
        if (employeePresent(breakStorage.get(breakIndex))) {
            return false;
        }
        breakStorage.get(breakIndex).setValidFrom(newStart);
        breakStorage.get(breakIndex).setValidTo(newEnd);
        return true;
    }

    private boolean validDateRange(PayoutBreak payoutBreak) {
        for (PayoutBreak payoutBreaks : breakStorage) {
            boolean checkStarDateRange = (payoutBreaks.getValidFrom().isEqual(payoutBreak.getValidFrom())
                    || payoutBreaks.getValidFrom().isBefore(payoutBreak.getValidFrom()))
                    && (payoutBreaks.getValidTo().isEqual(payoutBreak.getValidFrom())
                    || payoutBreaks.getValidTo().isAfter(payoutBreak.getValidFrom()));

            boolean checkEndDateRange = (payoutBreaks.getValidFrom().isEqual(payoutBreak.getValidTo())
                    || payoutBreaks.getValidFrom().isBefore(payoutBreak.getValidTo())) &&
                    (payoutBreaks.getValidTo().isEqual(payoutBreak.getValidTo())
                            || payoutBreaks.getValidTo().isAfter(payoutBreak.getValidTo()));

            boolean checkDateRange = payoutBreak.getValidFrom().isBefore(payoutBreaks.getValidFrom())
                    && payoutBreak.getValidTo().isAfter(payoutBreaks.getValidTo());
            if (checkStarDateRange || checkEndDateRange || checkDateRange) {
                return true;
            }
        }
        return false;
    }

    private boolean employeePresent(PayoutBreak payoutBreak) {
        for (Employee employee : employeeStorage) {
            for (LocalDate localDate : employee.getPresents()) {
                if ((localDate.isAfter(payoutBreak.getValidFrom()) || localDate.isEqual(payoutBreak.getValidFrom()))
                        && localDate.isBefore(payoutBreak.getValidTo()) || localDate.isEqual(payoutBreak.getValidTo())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<PayoutBreak> getBreakStorage() {
        return breakStorage;
    }

    public List<Employee> getEmployeeStorage() {
        return employeeStorage;
    }

}
