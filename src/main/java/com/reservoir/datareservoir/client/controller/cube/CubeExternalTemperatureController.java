package com.reservoir.datareservoir.client.controller.cube;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.service.CubeDataServiceClient;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/cube/external-temperature")
public class CubeExternalTemperatureController {

	private final CubeDataServiceClient cubeDataService;
	
    @GetMapping
    public ModelAndView externalTemperature() {
        return new ModelAndView("cube/tabs/externalTemperature/externalTemperature");
    }

    @GetMapping("/table")
    public ModelAndView getExternalTemperatureTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/externalTemperature/table");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postExternalTemperatureTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/externalTemperature/table");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getExternalTemperatureGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/externalTemperature/graph");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postExternalTemperatureGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/externalTemperature/graph");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }
}
