package com.fetch.model.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Item
 */

@Data
public class Item {
  @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Description is Invalid")
  private String shortDescription;

  @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price is Invalid")
  private String price;
}

