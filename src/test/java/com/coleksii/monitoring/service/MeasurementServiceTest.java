package com.coleksii.monitoring.service;

import com.coleksii.monitoring.config.NotFoundException;
import com.coleksii.monitoring.dao.MeasurementUsageRepository;
import com.coleksii.monitoring.dao.UserRepository;
import com.coleksii.monitoring.dto.MeasurementSubmitDto;
import com.coleksii.monitoring.dto.UserMeasurementDto;
import com.coleksii.monitoring.entity.MeasurementUsageEntity;
import com.coleksii.monitoring.entity.UserEntity;
import com.coleksii.monitoring.mapper.UserMeasurementMapper;
import com.coleksii.monitoring.service.impl.MeasurementServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class MeasurementServiceTest {

  @Mock
  UserRepository userRepository;
  @Mock
  MeasurementUsageRepository measurementRepository;
  @Mock
  UserMeasurementMapper userMeasurementMapper;

  @InjectMocks
  MeasurementServiceImpl measurementService;

  @Test
  public void getMeasurementByPhone() {
    UserEntity entity = new UserEntity();
    UserMeasurementDto userMeasurementDto = new UserMeasurementDto();

    Mockito.when(userRepository.findUserWithMeasurementByPhone(ArgumentMatchers.any())).thenReturn(Optional.of(entity));
    Mockito.when(userMeasurementMapper.toUserMeasurementDto(ArgumentMatchers.any())).thenReturn(userMeasurementDto);
    measurementService.getMeasurementByPhone("000");
  }

  @Test(expected = NotFoundException.class)
  public void notFoundMeasurement() {
    Mockito.when(userRepository.findUserWithMeasurementByPhone(ArgumentMatchers.any())).thenReturn(Optional.empty());
    measurementService.getMeasurementByPhone("000");
  }

  @Test
  public void saveMeasurement() {
    UserEntity entity = new UserEntity();
    MeasurementSubmitDto dto = new MeasurementSubmitDto();
    MeasurementUsageEntity measurementUsageEntity = new MeasurementUsageEntity();

    Mockito.when(userRepository.findUserEntityByPhoneNumber(ArgumentMatchers.any()))
        .thenReturn(Optional.of(entity));
    Mockito.when(userMeasurementMapper.toMeasurementEntity(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(measurementUsageEntity);
    measurementService.saveMeasurement(dto);

    Mockito.verify(measurementRepository, Mockito.times(1)).save(ArgumentMatchers.any());
  }

  @Test(expected = NotFoundException.class)
  public void notFoundWithSaveMeasurement() {
    MeasurementSubmitDto dto = new MeasurementSubmitDto();

    Mockito.when(userRepository.findUserEntityByPhoneNumber(ArgumentMatchers.any()))
        .thenReturn(Optional.empty());
    measurementService.saveMeasurement(dto);
  }
}
