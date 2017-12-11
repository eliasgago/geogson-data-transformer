package com.eliasgago.geogson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eliasgago.geogson.service.GetGalicianMunicipalityPointsService;
import com.eliasgago.geogson.service.GetGalicianPopulationService;

@SpringBootApplication
public class Application {

    @Autowired
    private GetGalicianPopulationService getGalicianPopulationService;

    @Autowired
    private GetGalicianMunicipalityPointsService getGalicianMunicipalityPointsService;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    /*@Override
    public void run(String... args) throws Exception {
    	getGalicianPopulationService.execute();
    	getGalicianMunicipalityPointsService.execute();
        System.exit(0);
    }*/
}