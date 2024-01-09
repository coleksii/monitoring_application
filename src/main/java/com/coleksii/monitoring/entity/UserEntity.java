package com.coleksii.monitoring.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity  extends BaseEntity {
  private String firstName;
  private String lastName;
  @Column(unique = true)
  private String location;
  @Column(unique = true, nullable = false)
  private String phoneNumber;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
  private Set<MeasurementUsageEntity> measurementUsage;
}
