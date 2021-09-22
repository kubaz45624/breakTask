package breaks;

import java.time.LocalDate;
import java.time.LocalTime;

public class PayoutBreak implements Comparable<PayoutBreak> {
    private LocalTime payoutBreakTime;
    private LocalTime requiredWorkTime;
    private LocalDate validFrom;
    private LocalDate validTo;

    public PayoutBreak(LocalTime payoutBreakTime, LocalTime requiredWorkTime, LocalDate validFrom, LocalDate validTo) {
        this.payoutBreakTime = payoutBreakTime;
        this.requiredWorkTime = requiredWorkTime;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public LocalTime getPayoutBreakTime() {
        return payoutBreakTime;
    }

    public void setPayoutBreakTime(LocalTime payoutBreakTime) {
        this.payoutBreakTime = payoutBreakTime;
    }

    public LocalTime getRequiredWorkTime() {
        return requiredWorkTime;
    }

    public void setRequiredWorkTime(LocalTime requiredWorkTime) {
        this.requiredWorkTime = requiredWorkTime;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    @Override
    public int compareTo(PayoutBreak o) {
        if (this.validFrom.isAfter(o.getValidFrom())) {
            return 1;
        }
        return -1;
    }
}
