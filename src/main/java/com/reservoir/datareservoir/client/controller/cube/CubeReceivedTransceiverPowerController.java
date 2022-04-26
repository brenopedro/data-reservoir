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
@RequestMapping("/cube/received-transceiver-power")
public class CubeReceivedTransceiverPowerController {

	private final CubeDataServiceClient cubeDataService;
	
    @GetMapping
    public ModelAndView receivedTransceiverPower() {
        return new ModelAndView("cube/tabs/receivedTransceiverPower/receivedTransceiverPower");
    }

    @GetMapping("/table")
    public ModelAndView getReceivedTranceiverPowerTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/receivedTransceiverPower/table");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/table")
    public ModelAndView postReceivedTranceiverPowerTable(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/receivedTransceiverPower/table");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }

    @GetMapping("/graph")
    public ModelAndView getReceivedTranceiverPowerGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/receivedTransceiverPower/graph");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }

    @PostMapping("/graph")
    public ModelAndView postReceivedTranceiverPowerGraph(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/tabs/receivedTransceiverPower/graph");
        modelAndView.addObject("cubeData", cubeDataService.getCubeData(propertiesFilter));
        return modelAndView;
    }
}
