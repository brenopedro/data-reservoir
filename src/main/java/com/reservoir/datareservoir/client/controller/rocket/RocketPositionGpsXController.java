package com.reservoir.datareservoir.client.controller.rocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.service.RocketDataServiceClient;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/rocket/position-gps-x")
public class RocketPositionGpsXController {

	private final RocketDataServiceClient rocketDataService;
	
    @GetMapping
    public ModelAndView rocketPositionGpsX() {
        return new ModelAndView("rocket/tabs/positionGpsX/positionGpsX");
    }

    @GetMapping("/table")
    public ModelAndView getRocketPositionGpsXTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/positionGpsX/table");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postRocketPositionGpsXTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/positionGpsX/table");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getRocketPositionGpsXGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/positionGpsX/graph");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postRocketPositionGpsXGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/positionGpsX/graph");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }
}
