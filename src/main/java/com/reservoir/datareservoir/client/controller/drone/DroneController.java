package com.reservoir.datareservoir.client.controller.drone;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.reservoir.datareservoir.client.domain.model.DroneData;
import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.service.DroneDataServiceClient;

@Controller
@RequestMapping("/drone")
public class DroneController {

	@Autowired
	private DroneDataServiceClient droneDataService;
	
	private DroneData[] droneDataList;

    @GetMapping
    public ModelAndView getDrone(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/droneHome");
        droneDataList = droneDataService.getDroneData(propertiesFilter);
        modelAndView.addObject("droneData", droneDataList);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView postDrone(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("drone/droneHome");
        droneDataList = droneDataService.getDroneData(propertiesFilter);
        modelAndView.addObject("droneData", droneDataList);
        return modelAndView;
    }
    
    @GetMapping("/download")
    public void downloadCsv(HttpServletResponse response) throws IOException {
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=droneData.csv";
    	
    	response.setHeader(headerKey, headerValue);
    	
    	ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		droneDataService.downloadCsv(csvBeanWriter, droneDataList);
		csvBeanWriter.close();
    	
    }
}
