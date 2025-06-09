package com.example.demo;

import java.beans.BeanProperty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Converts.BusConverter;
import com.example.demo.Converts.DriverConvert;
import com.example.demo.Converts.LineConvert;
import com.example.demo.Converts.StationConvert;
import com.example.demo.Converts.StationLineConvert;
import com.example.demo.Converts.TravelConvert;
import com.example.demo.Services.Station_LineService;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public BusConverter getBusConverter() {
        return new BusConverter();
    }

    @Bean
    public DriverConvert getDriverConvert() {
        return new DriverConvert();
    }
    @Bean
    public LineConvert getLineConvert() {
        return new LineConvert();
    }

    @Bean
    public StationConvert getStationConvert() {
        return new StationConvert();
    }
    @Bean
    public TravelConvert getTravelConvert() {
        return new TravelConvert();
    }
	@Bean
	public StationLineConvert getStationLineConvert() {
		return new StationLineConvert();
	}
}



