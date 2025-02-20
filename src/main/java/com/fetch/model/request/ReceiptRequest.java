package com.fetch.model.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Receipt
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptRequest {

  @NotBlank
  @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Retailer is Invalid")
  private String retailer;

  @NotBlank
  @Pattern(regexp = "^(\\d{4})-(0[1-9]|1[0-2]|[1-9])-([1-9]|0[1-9]|[1-2]\\d|3[0-1])$", message = "Date is Invalid")
  private String purchaseDate;

  @NotBlank
  @Pattern(regexp = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Time is Invalid")
  private String purchaseTime;

  @Valid
  private List<Item> items;

  @NotBlank
  @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Total is Invalid")
  private String total;

}

