package com.reservoir.datareservoir.client.controller.cube;

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

import com.reservoir.datareservoir.client.domain.model.CubeData;
import com.reservoir.datareservoir.client.domain.model.PropertiesFilter;
import com.reservoir.datareservoir.client.domain.service.CubeDataServiceClient;

@Controller
@RequestMapping("/cube")
public class CubeController {

	@Autowired
	private CubeDataServiceClient cubeDataService;
	
	private CubeData[] cubeDataList;

    @GetMapping
    public ModelAndView getCube(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/cubeHome");
        cubeDataList = cubeDataService.getCubeData(propertiesFilter);
        modelAndView.addObject("cubeData", cubeDataList);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView postCube(PropertiesFilter propertiesFilter) {
        ModelAndView modelAndView = new ModelAndView("cube/cubeHome");
        cubeDataList = cubeDataService.getCubeData(propertiesFilter);
        modelAndView.addObject("cubeData", cubeDataList);
        return modelAndView;
    }
    
    @GetMapping("/download")
    public void downloadCsv(HttpServletResponse response) throws IOException {
    	response.setContentType("application/octet-stream");
    	String headerKey = "Content-Disposition";
    	String headerValue = "attachment; filename=cubeData.csv";
    	
    	response.setHeader(headerKey, headerValue);
    	
    	ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		cubeDataService.downloadCsv(csvBeanWriter, cubeDataList);
		csvBeanWriter.close();
    	
    }
}
