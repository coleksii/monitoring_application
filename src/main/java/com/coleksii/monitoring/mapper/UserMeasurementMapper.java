package com.coleksii.monitoring.mapper;

import com.coleksii.monitoring.dto.MeasurementSubmitDto;
import com.coleksii.monitoring.dto.MeasurementUsageDto;
import com.coleksii.monitoring.dto.UserMeasurementDto;
import com.coleksii.monitoring.entity.MeasurementUsageEntity;
import com.coleksii.monitoring.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMeasurementMapper {

  @Mapping(target = "measurements", source = "measurementUsage")
  UserMeasurementDto toUserMeasurementDto(UserEntity userEntity);

  @Mapping(target = "user", source = "user")
  @Mapping(target = "id", ignore = true)
  MeasurementUsageEntity toMeasurementEntity(MeasurementSubmitDto submitDto, UserEntity user);

  MeasurementUsageDto measurementUsageToDto(MeasurementUsageEntity employees);
}
