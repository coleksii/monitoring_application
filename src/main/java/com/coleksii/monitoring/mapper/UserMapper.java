package com.coleksii.monitoring.mapper;

import com.coleksii.monitoring.dto.UserDto;
import com.coleksii.monitoring.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
  UserDto toDto(UserEntity source);
  List<UserDto> toList(List<UserEntity> source);
}
