package service;

import model.Receipt;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ReceiptServiceTest {

    @Test
    public void shouldGenerateIncrementalReceipts() {
        ReceiptService receiptService = new ReceiptService();

        Receipt receipt1 = receiptService.generateReceipt(LocalDateTime.now(), LocalDateTime.now(), 12);
        Receipt receipt2 = receiptService.generateReceipt(LocalDateTime.now(), LocalDateTime.now(), 12);

        assertEquals("Receipt one", "R-1", receipt1.getReceiptNumber());
        assertEquals("Receipt two", "R-2", receipt2.getReceiptNumber());
    }
}