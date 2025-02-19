package com.fetch.service;

import com.fetch.exception.ResourceNotFoundException;
import com.fetch.model.request.ReceiptRequest;
import com.fetch.model.response.ReceiptResponse;
import com.fetch.repository.ReceiptRepository;
import com.fetch.rule.PointsRuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReceiptProcessorService {

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    PointsRuleEngine pointsRuleEngine;


    public ReceiptResponse getPointsById(String id) {
        //Ideally we go to DB and fetch for the data here instead of caching in a Map
        Long points = receiptRepository.getPoint(id);
        if(points != null) {
            return new ReceiptResponse(points);
        }else{
            throw new ResourceNotFoundException(List.of("No receipt found for that ID."));
        }

    }

    public ReceiptResponse postReceiptsProcess(ReceiptRequest receiptRequest) {
        //Could generate a UUID base on receipt info + score to reverse this uuid later on
        ReceiptResponse receiptResponse = null;
        Long points = pointsRuleEngine.calculatePoints(receiptRequest);
        if(points == null){
            throw new RuntimeException();
        }
        String receiptUUID = UUID.randomUUID().toString();
        receiptRepository.saveReceiptPoints(receiptUUID, points, receiptRequest);


        return new ReceiptResponse(receiptUUID);

    }
}
