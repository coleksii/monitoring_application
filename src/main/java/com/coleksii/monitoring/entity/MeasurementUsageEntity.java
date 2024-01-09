package com.coleksii.monitoring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEASUREMENT_USAGE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementUsageEntity extends BaseEntity {
  private Long hotWater;
  private Long coldWater;
  private Long gas;
  @Column(length = 5000)
  private String comment;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
  private UserEntity user;
  private LocalDateTime receivedDate;


}
