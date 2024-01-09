package com.coleksii.monitoring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserMeasurementDto {

  private String firstName;
  private String lastName;
  private String location;
  private String phoneNumber;
  private List<MeasurementUsageDto> measurements;
}
