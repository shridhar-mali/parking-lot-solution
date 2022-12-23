package service;

import model.Receipt;

import java.time.LocalDateTime;

public class ReceiptService {

    private Long currentReceiptNumber = 1l;

    public Receipt generateReceipt(LocalDateTime entryDateTime, LocalDateTime exitDateTime, Integer fees) {
        return new Receipt(currentReceiptNumber++, entryDateTime, exitDateTime, fees);
    }

    public void printReceipt(Receipt receipt) {
        System.out.println(receipt.toString());
    }
}
