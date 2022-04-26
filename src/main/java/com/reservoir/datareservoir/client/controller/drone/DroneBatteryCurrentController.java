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
@RequestMapping("/drone/battery-current")
public class DroneBatteryCurrentController {

	private final DroneDataServiceClient droneDataService;
	
    @GetMapping
    public ModelAndView droneBatteryCurrent() {
        return new ModelAndView("drone/tabs/batteryCurrent/batteryCurrent");
    }

    @GetMapping("/table")
    public ModelAndView getDroneBatteryCurrentTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/batteryCurrent/table");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postDroneBatteryCurrentTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/batteryCurrent/table");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getDroneBatteryCurrentGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/batteryCurrent/graph");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postDroneBatteryCurrentGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/batteryCurrent/graph");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }
}
