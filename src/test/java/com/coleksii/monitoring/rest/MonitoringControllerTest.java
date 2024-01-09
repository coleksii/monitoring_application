package com.coleksii.monitoring.rest;

import com.coleksii.monitoring.config.NotFoundException;
import com.coleksii.monitoring.dto.MeasurementSubmitDto;
import com.coleksii.monitoring.dto.MeasurementUsageDto;
import com.coleksii.monitoring.dto.UserDto;
import com.coleksii.monitoring.dto.UserMeasurementDto;
import com.coleksii.monitoring.rest.util.DefaultExceptionHandler;
import com.coleksii.monitoring.service.MeasurementService;
import com.coleksii.monitoring.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
public class MonitoringControllerTest {

  @Mock
  UserService userService;
  @Mock
  MeasurementService measurementService;

  private final ObjectWriter objectWriter = new ObjectMapper().writer();

  private MockMvc mvc;

  /**
   * setup mockMvc.
   */
  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(new MonitoringController(
            measurementService,
            userService
        ))
        .setControllerAdvice(new DefaultExceptionHandler())
        .build();
  }

  @Test
  public void statusCodeOk() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void equalUser() throws Exception {
    UserDto userDto = new UserDto();
    userDto.setFirstName("first");

    Mockito.when(userService.getAllUsers()).thenReturn(Collections.singletonList(userDto));

    mvc.perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Is.is(userDto.getFirstName())));
  }

  @Test
  public void getMeasurement() throws Exception {
    UserMeasurementDto userMeasurement = new UserMeasurementDto();
    userMeasurement.setFirstName("second");
    MeasurementUsageDto usage = new MeasurementUsageDto();
    usage.setGas(999L);
    userMeasurement.setMeasurements(Collections.singletonList(usage));

    Mockito.when(measurementService.getMeasurementByPhone("000")).thenReturn(userMeasurement);

    mvc.perform(MockMvcRequestBuilders.get("/measurement?phoneNumber=000"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.is(userMeasurement.getFirstName())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.measurements[0].gas", Is.is(999)));
  }

  @Test
  public void getBadRequest() throws Exception {
    UserMeasurementDto userMeasurement = new UserMeasurementDto();
    userMeasurement.setFirstName("second");
    MeasurementUsageDto usage = new MeasurementUsageDto();
    usage.setGas(999L);
    userMeasurement.setMeasurements(Collections.singletonList(usage));

    Mockito.when(measurementService.getMeasurementByPhone("000")).thenThrow(NotFoundException.class);

    mvc.perform(MockMvcRequestBuilders.get("/measurement?phoneNumber=000"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void submitUsage() throws Exception {

    MeasurementSubmitDto submitDto = new MeasurementSubmitDto();
    submitDto.setGas(999L);
    submitDto.setColdWater(999L);
    submitDto.setHotWater(999L);
    submitDto.setPhoneNumber("321");

    Mockito.when(measurementService.getMeasurementByPhone("000")).thenThrow(NotFoundException.class);

    mvc.perform(MockMvcRequestBuilders.post("/measurement")
            .contentType(APPLICATION_JSON_VALUE)
            .content(objectWriter.writeValueAsString(submitDto)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void submitUsageBadRequest() throws Exception {

    MeasurementSubmitDto submitDto = new MeasurementSubmitDto();
    submitDto.setGas(999L);
    submitDto.setColdWater(999L);
    submitDto.setHotWater(999L);

    Mockito.when(measurementService.getMeasurementByPhone("000")).thenThrow(NotFoundException.class);

    mvc.perform(MockMvcRequestBuilders.post("/measurement")
            .contentType(APPLICATION_JSON_VALUE)
            .content(objectWriter.writeValueAsString(submitDto)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
}
