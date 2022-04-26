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
@RequestMapping("/rocket/external-temperature")
public class RocketExternalTemperatureController {

	private final RocketDataServiceClient rocketDataService;
	
    @GetMapping
    public ModelAndView externalTemperature() {
        return new ModelAndView("rocket/tabs/externalTemperature/externalTemperature");
    }

    @GetMapping("/table")
    public ModelAndView getExternalTemperatureTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/externalTemperature/table");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postExternalTemperatureTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/externalTemperature/table");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getExternalTemperatureGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/externalTemperature/graph");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postExternalTemperatureGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/externalTemperature/graph");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }
}
