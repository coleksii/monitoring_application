package com.coleksii.monitoring.service;

import com.coleksii.monitoring.dto.UserDto;

import java.util.List;

public interface UserService {

  List<UserDto> getAllUsers();
}
