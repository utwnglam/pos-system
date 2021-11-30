package pos.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PosMachineTest {

    @Test
    public void should_return_receipt() {
        PosMachine posMachine = new PosMachine();

        String expected = "***<store earning no money>Receipt***\n" +
                "Name: Coca-Cola, Quantity: 4, Unit price: 3 (yuan), Subtotal: 12 (yuan)\n" +
                "Name: Sprite, Quantity: 2, Unit price: 3 (yuan), Subtotal: 6 (yuan)\n" +
                "Name: Battery, Quantity: 3, Unit price: 2 (yuan), Subtotal: 6 (yuan)\n" +
                "----------------------\n" +
                "Total: 24 (yuan)\n" +
                "**********************";

        assertEquals(expected, posMachine.printReceipt(ItemDataLoader.loadBarcodes()));
    }
}
