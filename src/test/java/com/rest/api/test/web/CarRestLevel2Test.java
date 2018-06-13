package com.rest.api.test.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rest.api.model.Car;
import com.rest.api.service.CarService;
import com.rest.api.web.ApiRest;
import com.rest.api.web.CarRest;

@RunWith(SpringRunner.class)
@WebMvcTest(CarRest.class)
public class CarRestLevel2Test {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CarService carDao;
	
	@Test
	public void listCars() throws Exception {
		given(carDao.listCars())
				.willReturn(Arrays.asList(new Car(1, "BMW", "320d", 0),
										  new Car(1, "Audi", "A3 2.0 TDI", 0)));
		
		mockMvc.perform(get(ApiRest.API_PATH + "/cars"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
}
