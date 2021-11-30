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

    
}
