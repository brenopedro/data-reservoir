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
@RequestMapping("/rocket/magnetic-field-y")
public class RocketMagneticFieldYController {

	private final RocketDataServiceClient rocketDataService;
	
    @GetMapping
    public ModelAndView magneticFieldY() {
        return new ModelAndView("rocket/tabs/magneticFieldY/magneticFieldY");
    }

    @GetMapping("/table")
    public ModelAndView getMagneticFieldYTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/magneticFieldY/table");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postMagneticFieldYTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/magneticFieldY/table");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getMagneticFieldYGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/magneticFieldY/graph");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postMagneticFieldYGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/tabs/magneticFieldY/graph");
        modelAndView.addObject("rocketData", rocketDataService.getRocketData(propertiesFilter));
        return modelAndView;
    }
}
