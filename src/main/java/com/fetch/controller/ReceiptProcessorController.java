package com.fetch.controller;

import com.fetch.model.request.ReceiptRequest;
import com.fetch.model.response.ReceiptResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public interface ReceiptProcessorController {



    /**
     * GET /receipts/{id}/points : Returns the points awarded for the receipt.
     * Returns the points awarded for the receipt.
     *
     * @paramId The ID of the receipt. (required)
     * @return The number of points awarded. (status code 200)
     *         or No receipt found for that ID. (status code 404)
     */
    @GetMapping("/{id}/points")
    public ResponseEntity<ReceiptResponse> receiptsIdPointsGet(@PathVariable String id);

    @PostMapping("/process")
    public ResponseEntity<ReceiptResponse> receiptsProcessPost(@Valid @RequestBody ReceiptRequest receiptRequest);
}
