package com.reservoir.datareservoir.client.controller.drone;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.service.DroneDataServiceClient;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/drone/position-gps-y")
public class DronePositionGpsYController {

	private final DroneDataServiceClient droneDataService;
	
    @GetMapping
    public ModelAndView dronePositionGpsY() {
        return new ModelAndView("drone/tabs/positionGpsY/positionGpsY");
    }

    @GetMapping("/table")
    public ModelAndView getDronePositionGpsYTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/positionGpsY/table");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postDronePositionGpsYTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/positionGpsY/table");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getDronePositionGpsYGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/positionGpsY/graph");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postDronePositionGpsYGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/positionGpsY/graph");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }
}
