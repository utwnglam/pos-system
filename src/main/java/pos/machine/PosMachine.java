package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        Map<String, Integer> quantityList = countItem(barcodes);
        return generateReceipt(quantityList);
    }

    private Map<String, Integer> countItem(List<String> barcodeList) {
        Map<String, Integer> result = new HashMap<>();
        for(String item : barcodeList) {
            result.merge(item, 1, Integer::sum);
        }
        return result;
    }

    private String generateReceipt(Map<String, Integer> quantityList) {
        List<ItemInfo> databaseList = loadAllItemsInfo();

        ArrayList<Integer> subtotalList = new ArrayList<>();

        StringBuilder finalContent = new StringBuilder("***<store earning no money>Receipt***\n");
        for(ItemInfo item : databaseList) {
            if (quantityList.get(item.getBarcode()) != null) {
                int subtotal = calculateSubtotal(item.getPrice(), quantityList.get(item.getBarcode()));
                subtotalList.add(subtotal);
                finalContent.append(generateItem(item, quantityList.get(item.getBarcode()), subtotal));
            }
        }
        finalContent.append(String.format("----------------------\nTotal: %d (yuan)\n**********************", calculateTotal(subtotalList)));

        return finalContent.toString();
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
