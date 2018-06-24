package com.pj.bedazzled.data.model;

import java.math.BigDecimal;

public class TotalDebt {
    private BigDecimal earlierDebt;
    private BigDecimal lastFullSeasonCost;
    private BigDecimal currentSeasonCost;
    private BigDecimal paymentReceived;

    public TotalDebt(String initial) {
        earlierDebt = new BigDecimal(initial);
        currentSeasonCost = new BigDecimal(0);
        paymentReceived = new BigDecimal(0);
        lastFullSeasonCost = new BigDecimal(0);
    }

    public void addDebt(String debtToAdd) {
        earlierDebt = earlierDebt.add(new BigDecimal(debtToAdd));
    }

    public void addDebt(BigDecimal debtToAdd) {
        earlierDebt = earlierDebt.add(debtToAdd);
    }

    public void addCurrentSeasonCost(BigDecimal debtToAdd) {
        currentSeasonCost = currentSeasonCost.add(debtToAdd);
    }

    public void addLastFullSeasonCost(BigDecimal debtToAdd) {
        lastFullSeasonCost = lastFullSeasonCost.add(debtToAdd);
    }

    public void paymentReceived(String payment) {
        paymentReceived = paymentReceived.add(new BigDecimal(payment));
    }

    public String getEarlierDebt() {
        return earlierDebt.toPlainString();
    }

    public String getDebtAsEndOfLastSeason() {
        return earlierDebt.add(lastFullSeasonCost).subtract(paymentReceived).toPlainString();
    }

    public String getLastFullSeasonCost() {
        return lastFullSeasonCost.toPlainString();
    }

    public String getThisSeasonCost() {
        return currentSeasonCost.toPlainString();
    }

    public String getLiveDebt() {
        return earlierDebt.add(lastFullSeasonCost).add(currentSeasonCost).subtract(paymentReceived).toPlainString();
    }
}
