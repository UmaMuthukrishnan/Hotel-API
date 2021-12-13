package com.booking.recruitment.hotel.controller;

import com.booking.testing.SlowTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@SlowTest
class CityHotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When hotels in a city are requested along with distance sort then sorted results are returned")
    void getHotelsByCityIdAndDistance() throws Exception {
        mockMvc.perform(
                get("/search/{cityId}?sort=distance", 1)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$.[0].name", equalTo("The Thornton Council Hotel")))
                .andExpect(jsonPath("$.[0].rating", equalTo(6.3)));
    }

    @Test
    @DisplayName("When hotels in a city are requested then results are returned without sorting")
    void getHotelByCityIdWithoutDistanceSort() throws Exception {
        mockMvc.perform(
                get("/search/{cityId}", 1)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$.[0].name", equalTo("Monaghan Hotel")))
                .andExpect(jsonPath("$.[0].rating", equalTo(9.2)));
    }
}
