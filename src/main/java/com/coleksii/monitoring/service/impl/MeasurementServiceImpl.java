package com.coleksii.monitoring.service.impl;

import com.coleksii.monitoring.config.NotFoundException;
import com.coleksii.monitoring.dao.MeasurementUsageRepository;
import com.coleksii.monitoring.dao.UserRepository;
import com.coleksii.monitoring.dto.MeasurementSubmitDto;
import com.coleksii.monitoring.dto.UserMeasurementDto;
import com.coleksii.monitoring.entity.MeasurementUsageEntity;
import com.coleksii.monitoring.entity.UserEntity;
import com.coleksii.monitoring.mapper.UserMeasurementMapper;
import com.coleksii.monitoring.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

  private final UserRepository userRepository;
  private final MeasurementUsageRepository measurementRepository;
  private final UserMeasurementMapper userMeasurementMapper;

  @Override
  public void saveMeasurement(MeasurementSubmitDto submitDto) {
    UserEntity user = userRepository.findUserEntityByPhoneNumber(submitDto.getPhoneNumber())
        .orElseThrow(() -> new NotFoundException("User with given phone number not found"));
    MeasurementUsageEntity measurementUsageEntity = userMeasurementMapper.toMeasurementEntity(submitDto, user);
    measurementRepository.save(measurementUsageEntity);
  }

  @Override
  public UserMeasurementDto getMeasurementByPhone(String phoneNumber) {
    UserEntity user = userRepository.findUserWithMeasurementByPhone(phoneNumber)
        .orElseThrow(() -> new NotFoundException("User with given phone number not found"));
    return userMeasurementMapper.toUserMeasurementDto(user);
  }
}
