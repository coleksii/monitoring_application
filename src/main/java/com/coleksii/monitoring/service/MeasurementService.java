package com.coleksii.monitoring.service;

import com.coleksii.monitoring.dto.MeasurementSubmitDto;
import com.coleksii.monitoring.dto.UserMeasurementDto;

public interface MeasurementService {

  void saveMeasurement(MeasurementSubmitDto submitDto);

  UserMeasurementDto getMeasurementByPhone(String phoneNumber);
}
