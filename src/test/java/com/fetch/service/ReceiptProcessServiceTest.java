package com.fetch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch.exception.ResourceNotFoundException;
import com.fetch.model.request.ReceiptRequest;
import com.fetch.model.response.ReceiptResponse;
import com.fetch.repository.ReceiptRepository;
import com.fetch.rule.PointsRuleEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReceiptProcessServiceTest {

    @InjectMocks
    private ReceiptProcessorService receiptProcessorService;

    @Mock
    private ReceiptRepository receiptRepository;

    @Mock
    private PointsRuleEngine pointsRuleEngine;

    private ReceiptRequest mockReceiptRequest;

    @BeforeEach
    void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/receipt.json")));
        mockReceiptRequest = objectMapper.readValue(json, ReceiptRequest.class);
    }

    @Test
    public void getPointsByIdTest_shouldReturnReceiptResponse_whenValidId(){
        Long expectedPoints = 123L;
        String mockReceiptId = "test-receipt-id";
        doReturn(expectedPoints).when(receiptRepository).getPoint(mockReceiptId);

        ReceiptResponse receiptResponse = receiptProcessorService.getPointsById(mockReceiptId);

        verify(receiptRepository, times(1)).getPoint(anyString());
        assert(receiptResponse.getPoints()).equals(expectedPoints);
    }

    @Test
    public void getPointsById_ShouldThrowException_WhenPointsAreNull(){
        String mockReceiptId = "test-receipt-id";
        doReturn(null).when(receiptRepository).getPoint(mockReceiptId);

        assertThrows(ResourceNotFoundException.class, () -> receiptProcessorService.getPointsById(mockReceiptId));

        verify(receiptRepository, times(1)).getPoint(mockReceiptId);
    }

    @Test
    void postReceiptsProcess_shouldReturnReceiptResponse_whenValidPoints() {
        Long mockPoints = 100L;
        // Mock points calculation
        when(pointsRuleEngine.calculatePoints(mockReceiptRequest)).thenReturn(mockPoints);
        doNothing().when(receiptRepository).saveReceiptPoints(anyString(), eq(mockPoints), eq(mockReceiptRequest));

        ReceiptResponse response = receiptProcessorService.postReceiptsProcess(mockReceiptRequest);

        assertNotNull(response);
        assertNotNull(response.getId());
        verify(pointsRuleEngine, times(1)).calculatePoints(mockReceiptRequest);
        verify(receiptRepository, times(1)).saveReceiptPoints(anyString(), eq(mockPoints), eq(mockReceiptRequest));
    }

    @Test
    void postReceiptsProcess_shouldThrowException_whenPointsIsNull() {
        when(pointsRuleEngine.calculatePoints(mockReceiptRequest)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> receiptProcessorService.postReceiptsProcess(mockReceiptRequest));
        verify(pointsRuleEngine, times(1)).calculatePoints(mockReceiptRequest);
        verify(receiptRepository, never()).saveReceiptPoints(anyString(), anyLong(), any());
    }

}
