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
@RequestMapping("/cube/euler-angle-z")
public class CubeEulerAngleZController {

	private final CubeDataServiceClient cubeDataService;
	
    @GetMapping
    public ModelAndView cubeEulerAngleZ() {
        return new ModelAndView("cube/tabs/eulerAngleZ/eulerAngleZ");
    }

    @GetMapping("/table")
    public ModelAndView getCubeEulerAngleZTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/eulerAngleZ/table");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postCubeEulerAngleZTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/eulerAngleZ/table");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getCubeEulerAngleZGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/eulerAngleZ/graph");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postCubeEulerAngleZGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/eulerAngleZ/graph");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }
}
