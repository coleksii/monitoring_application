package com.coleksii.monitoring.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class MeasurementSubmitDto {

  @NotNull(message = "gas usage value is mandatory")
  @Positive(message = "gas usage should be positive")
  private Long gas;
  @NotNull(message = "hotWater usage value is mandatory")
  @Positive(message = "hotWater usage should be positive")
  private Long hotWater;
  @NotNull(message = "coldWater usage value is mandatory")
  @Positive(message = "coldWater usage should be positive")
  private Long coldWater;
  @NotBlank(message = "phoneNumber is mandatory")
  private String phoneNumber;
  private String comment;
}
