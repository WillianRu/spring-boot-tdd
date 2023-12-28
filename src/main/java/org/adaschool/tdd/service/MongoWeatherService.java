package org.adaschool.tdd.service;

import org.adaschool.tdd.controller.weather.dto.WeatherReportDto;
import org.adaschool.tdd.exception.WeatherReportNotFoundException;
import org.adaschool.tdd.repository.WeatherReportRepository;
import org.adaschool.tdd.repository.document.GeoLocation;
import org.adaschool.tdd.repository.document.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoWeatherService
    implements WeatherService
{

    private final WeatherReportRepository repository;

    public MongoWeatherService( @Autowired WeatherReportRepository repository )
    {
        this.repository = repository;
    }

    @Override
    public WeatherReport report( WeatherReportDto weatherReportDto )
    {
        GeoLocation location = weatherReportDto.getGeoLocation();
        WeatherReport weatherReport = new WeatherReport(
                location,
                weatherReportDto.getTemperature(),
                weatherReportDto.getHumidity(),
                weatherReportDto.getReporter(),
                weatherReportDto.getCreated()
        );
        return repository.save(weatherReport);
    }

    @Override
    public WeatherReport findById( String id )
    {
        Optional<WeatherReport> optionalWeatherReport = repository.findById(id);
        return optionalWeatherReport.orElseThrow(WeatherReportNotFoundException::new);
    }


    @Override
    public List<WeatherReport> findNearLocation( GeoLocation geoLocation, float distanceRangeInMeters )
    {
        return null;
    }

    @Override
    public List<WeatherReport> findWeatherReportsByName( String reporter )
    {
        return null;
    }
}
