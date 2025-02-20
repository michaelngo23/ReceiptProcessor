package com.fetch.controller.impl;

import com.fetch.controller.ReceiptProcessorController;
import com.fetch.model.request.ReceiptRequest;
import com.fetch.model.response.ReceiptResponse;
import com.fetch.service.ReceiptProcessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ReceiptProcessorControllerImpl implements ReceiptProcessorController {


    @Autowired
    ReceiptProcessorService receiptProcessorService;

    /**
     * GET /receipts/{id}/points : Returns the points awarded for the receipt.
     * Returns the points awarded for the receipt.
     *
     * @paramId The ID of the receipt. (required)
     * @return The number of points awarded. (status code 200)
     *         or No receipt found for that ID. (status code 404)
     */
    @GetMapping("/{id}/points")
    public ResponseEntity<ReceiptResponse> receiptsIdPointsGet(@PathVariable String id) {
        return ResponseEntity.ok(receiptProcessorService.getPointsById(id));

    }

    @PostMapping("/process")
    public ResponseEntity<ReceiptResponse> receiptsProcessPost(@Valid @RequestBody ReceiptRequest receiptRequest) {
        return ResponseEntity.ok(receiptProcessorService.postReceiptsProcess(receiptRequest));
    }
}