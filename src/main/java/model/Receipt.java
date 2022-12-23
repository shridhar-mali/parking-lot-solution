package model;

import java.time.LocalDateTime;

public class Receipt {

    private static String RECEIPT_PREFIX = "R-%s";

    private String receiptNumber;
    private LocalDateTime entryDateTime;
    private LocalDateTime exitDateTime;
    private Integer fees;

    public Receipt(Long receiptNumber, LocalDateTime entryDateTime, LocalDateTime exitDateTime, Integer fees) {
        this.receiptNumber = String.format(RECEIPT_PREFIX, receiptNumber);
        this.entryDateTime = entryDateTime;
        this.exitDateTime = exitDateTime;
        this.fees = fees;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    public LocalDateTime getExitDateTime() {
        return exitDateTime;
    }

    public Integer getFees() {
        return fees;
    }

    @Override
    public String toString() {
        return "Parking Receipt:" +
                "\n\tReceipt Number:" + receiptNumber +
                "\n\tEntry Date-time:" + entryDateTime +
                "\n\tExit Date-time:" + exitDateTime +
                "\n\tFees:" + fees;
    }
}
