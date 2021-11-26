package com.test.kraken.websocket.model.factory;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

public class OperationUpdate implements Operation {
    @Override
    public void doOperation(TreeMap<BigDecimal,BigDecimal> treeMap, List<List<String>>list, BigDecimal... a) {
        //update
        treeMap.put(a[0],a[1]);
        System.out.println("update");
    }
}
