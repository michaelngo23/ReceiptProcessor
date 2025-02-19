package com.fetch.repository;

import com.fetch.model.request.ReceiptRequest;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Data
public class ReceiptRepository {
    //representing databases
    Map<String, Long> idPointMap = new HashMap<>();

    //representing databases Receipt object, Id
    Map<String, ReceiptRequest> idReceiptRequestMap = new HashMap<>();
    public void saveReceiptPoints(String id, Long points, ReceiptRequest receipt){
        idReceiptRequestMap.put(id, receipt);
        idPointMap.put(id, points);
    }

    public Long getPoint(String id) {
        return idPointMap.getOrDefault(id,null);
    }

    public void deleteReceipt(String id) {
        idPointMap.remove(id);
        idReceiptRequestMap.remove(id);
    }
}
