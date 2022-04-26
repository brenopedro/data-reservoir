package com.reservoir.datareservoir.client.controller.rocket;

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

import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.model.RocketData;
import com.reservoir.datareservoir.client.domain.service.RocketDataServiceClient;

@Controller
@RequestMapping("/rocket")
public class RocketController {
	
	@Autowired
	private RocketDataServiceClient rocketDataService;
	
	private RocketData[] rocketDataList;

    @GetMapping
    public ModelAndView getRocket(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/rocketHome");
        rocketDataList = rocketDataService.getRocketData(propertiesFilter);
        modelAndView.addObject("rocketData", rocketDataList);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView postRocket(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("rocket/rocketHome");
        rocketDataList = rocketDataService.getRocketData(propertiesFilter);
        modelAndView.addObject("rocketData", rocketDataList);
        return modelAndView;
    }
    
    @GetMapping("/download")
    public void downloadCsv(HttpServletResponse response) throws IOException {
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=rocketData.csv";
    	
    	response.setHeader(headerKey, headerValue);
    	
    	ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		rocketDataService.downloadCsv(csvBeanWriter, rocketDataList);
		csvBeanWriter.close();
    	
    }
}
