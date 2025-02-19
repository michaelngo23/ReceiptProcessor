package com.fetch.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptResponse {

    private Long points;
    private String id;

    public ReceiptResponse(Long points) {
        this.points = points;
    }

    public ReceiptResponse(String id) {
        this.id = id;
    }
}
