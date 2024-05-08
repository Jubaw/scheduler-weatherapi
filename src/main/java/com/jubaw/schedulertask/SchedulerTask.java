package com.jubaw.schedulertask;

import com.jubaw.domain.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SchedulerTask {
    private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${app.weather.api.key}")
    private String apiKey;

    @Scheduled(fixedRate = 10000) // her 10 saniyedeg bir bu method tetiklensin
    public void havaDurumuBilgisiniGetir(){

        String url = "https://api.openweathermap.org/data/2.5/weather?q=Istanbul&appid=" + apiKey + "&units=metric&lang=tr";
        WeatherResponse response =  restTemplate.getForObject(url, WeatherResponse.class);

        if(response != null){
            log.info("Forecast for Istanbul : {}, Temperature: {}",
                    response.getWeather().get(0).getDescription(),
                    response.getMain().getTemp());
        }
    }
    }






