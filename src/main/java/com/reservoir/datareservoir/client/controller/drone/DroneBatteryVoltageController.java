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
@RequestMapping("/drone/battery-voltage")
public class DroneBatteryVoltageController {

	private final DroneDataServiceClient droneDataService;
	
    @GetMapping
    public ModelAndView droneBatteryVoltage() {
        return new ModelAndView("drone/tabs/batteryVoltage/batteryVoltage");
    }

    @GetMapping("/table")
    public ModelAndView getDroneBatteryVoltageTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/batteryVoltage/table");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postDroneBatteryVoltageTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/batteryVoltage/table");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getDroneBatteryVoltageGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/batteryVoltage/graph");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postDroneBatteryVoltageGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/tabs/batteryVoltage/graph");
        modelAndView.addObject("droneData", droneDataService.getDroneData(propertiesFilter));
        return modelAndView;
    }
}
