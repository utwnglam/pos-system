package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        Map<String, Integer> quantityList = countItem(barcodes);
        return null;
    }

    private Map<String, Integer> countItem(List<String> barcodeList) {
        Map<String, Integer> result = new HashMap<>();
        for(String item : barcodeList) {
            result.merge(item, 1, Integer::sum);
        }
        return result;
    }

    private List<ItemInfo> loadAllItemsInfo() {
        return ItemDataLoader.loadAllItemInfos();
    }

    private int calculateSubtotal(int unitPrice, int quantity) {
        return unitPrice * quantity;
    }

    private String generateItem(ItemInfo item, int quantity, int subtotal) {
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", item.getName(), quantity, item.getPrice(), subtotal);
    }

    private int calculateTotal(ArrayList<Integer> subtotalList) {
        int total = 0;
        for (int subtotal : subtotalList) {
            total += subtotal;
        }
        return total;
    }
}
