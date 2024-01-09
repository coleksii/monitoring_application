package com.coleksii.monitoring.service.impl;

import com.coleksii.monitoring.dao.UserRepository;
import com.coleksii.monitoring.dto.UserDto;
import com.coleksii.monitoring.entity.UserEntity;
import com.coleksii.monitoring.mapper.UserMapper;
import com.coleksii.monitoring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public List<UserDto> getAllUsers() {
    List<UserEntity> all = userRepository.findAll();
    return userMapper.toList(all);
  }
}
