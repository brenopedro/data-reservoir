package com.reservoir.datareservoir.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.service.CubeDataServiceClient;
import com.reservoir.datareservoir.client.domain.service.DroneDataServiceClient;
import com.reservoir.datareservoir.client.domain.service.RocketDataServiceClient;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {
	
	private final CubeDataServiceClient cubeDataService;
	private final DroneDataServiceClient droneDataService;
	private final RocketDataServiceClient rocketDataService;

    @GetMapping
    public ModelAndView getHome(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        
        return modelAndView;
    }
    
    @PostMapping
    public ModelAndView postHome(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        
        return modelAndView;
    }

    @GetMapping("error")
    public ModelAndView handleErrorGet() {
        ModelAndView modelAndView = new ModelAndView("error/error");
        return modelAndView;
    }
    
    @PostMapping("error")
    public ModelAndView handleErrorPost() {
        ModelAndView modelAndView = new ModelAndView("error/error");
        return modelAndView;
    }

}
