package com.coleksii.monitoring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementUsageDto {

  private Long gas;
  private Long coldWater;
  private Long hotWater;
  private String comment;
}
