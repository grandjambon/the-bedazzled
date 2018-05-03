package com.pj.bedazzled.data.model;

import java.math.BigDecimal;

public class TotalDebt {
    private BigDecimal debt;

    public TotalDebt(String initial) {
        debt = new BigDecimal(initial);
    }

    public void addDebt(String debtToAdd) {
        debt = debt.add(new BigDecimal(debtToAdd));
    }

    public void addDebt(BigDecimal debtToAdd) {
        debt = debt.add(debtToAdd);
    }

    public void paymentReceived(String payment) {
        debt = debt.subtract(new BigDecimal(payment));
    }

    public String getDebt() {
        return debt.toPlainString();
    }
}
