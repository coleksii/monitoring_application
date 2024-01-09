package com.coleksii.monitoring.dao;

import com.coleksii.monitoring.entity.MeasurementUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementUsageRepository extends JpaRepository<MeasurementUsageEntity, Long> {

}
