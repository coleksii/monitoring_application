package com.coleksii.monitoring.dao;

import com.coleksii.monitoring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query("select u from UserEntity u "
      + "left join fetch u.measurementUsage "
      + "where u.phoneNumber = :phoneNumber ")
  Optional<UserEntity> findUserWithMeasurementByPhone(@Param("phoneNumber") String phoneNumber);

  Optional<UserEntity> findUserEntityByPhoneNumber(String phoneNumber);
}
