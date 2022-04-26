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
@RequestMapping("/drone/position-gps-z")
public class DronePositionGpsZController {

	private final DroneDataServiceClient droneDataService;
	
    @GetMapping
    public ModelAndView dronePositionGpsZ() {
        return new ModelAndView("drone/tabs/positionGpsZ/positionGpsZ");
    }

    @GetMapping("/table")
    public ModelAndView getDronePositionGpsZTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/positionGpsZ/table");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postDronePositionGpsZTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/positionGpsZ/table");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getDronePositionGpsZGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/positionGpsZ/graph");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postDronePositionGpsZGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/positionGpsZ/graph");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }
}
