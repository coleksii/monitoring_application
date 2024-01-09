package com.coleksii.monitoring.rest;

import com.coleksii.monitoring.dto.MeasurementSubmitDto;
import com.coleksii.monitoring.dto.UserDto;
import com.coleksii.monitoring.dto.UserMeasurementDto;
import com.coleksii.monitoring.rest.util.RestResponse;
import com.coleksii.monitoring.service.MeasurementService;
import com.coleksii.monitoring.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Log4j2
public class MonitoringController {

  private final MeasurementService measurementService;
  private final UserService userService;

  /**
   * GET /users endpoint for getting all users.
   *
   */
  @ApiOperation("Endpoint for getting all users")
  @GetMapping("/users")
  @RestResponse
  public ResponseEntity<List<UserDto>> getAllUsers() {
    log.info("request /users");
    return ResponseEntity.ok(userService.getAllUsers());
  }

  /**
   * GET /measurement endpoint for getting exact measurement.
   *
   * @param phoneNumber phone number to find user
   */
  @ApiOperation("Endpoint for getting exact measurement")
  @GetMapping("/measurement")
  @RestResponse
  public ResponseEntity<UserMeasurementDto> getMeasurementByPhone(@RequestParam String phoneNumber) {
    log.info("request get /measurement");
    UserMeasurementDto measurementByLocation = measurementService.getMeasurementByPhone(phoneNumber);
    return ResponseEntity.ok(measurementByLocation);
  }

  /**
   * POST /measurement endpoint.
   *
   * @param submitDto DTO with field details to submit
   */
  @ApiOperation("Submit gas, cold and hot water usage measurement")
  @PostMapping("/measurement")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 201, message = "Measurements were submitted"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  })
  public ResponseEntity<Void> submitMeasurement(@Valid @RequestBody MeasurementSubmitDto submitDto) {
    log.info("request post /measurement");
    measurementService.saveMeasurement(submitDto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
