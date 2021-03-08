package com.pani.birrachallenge.service.weather;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pani.birrachallenge.service.weather.openweather.Forecast;
import com.pani.birrachallenge.service.weather.openweather.ForecastList;

@Service
public class OpenWeatherMapApiImpl implements WeatherRestClient {

	private static final Logger logger = LoggerFactory.getLogger(OpenWeatherMapApiImpl.class);
	private final RestTemplate restTemplate;
	private String apiKey;
	private String apiUrl;
	private String apiHost;

	@Autowired
	public OpenWeatherMapApiImpl(RestTemplateBuilder builder, @Value("${x-rapidapi-key}") String apiKey,
			@Value("${weather.api.url}") String apiUrl, @Value("${x-rapidapi-host}") String apiHost) {
		this.restTemplate = builder.build();
		this.apiKey = apiKey;
		this.apiUrl = apiUrl;
		logger.info("OpenWeatherMapApiImpl started");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Forecast> getForecast5Days(String location) {
		HttpHeaders headers = new HttpHeaders();
		ForecastList listForecast = new ForecastList();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + "/forecast").queryParam("q", location)
				.queryParam("units", "metric");

		headers.put("x-rapidapi-key", Collections.singletonList(apiKey));
		headers.put("x-rapidapi-host", Collections.singletonList(apiHost));
		ResponseEntity<String> res = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				new HttpEntity<>(null, headers), new ParameterizedTypeReference<String>() {
				});
		Gson gson = new Gson();
		Object obj;
		JsonParser parser = new JsonParser();
		obj = parser.parse(res.getBody());
		JsonObject jsonObject = (JsonObject) obj;
		listForecast = gson.fromJson(jsonObject, ForecastList.class);
		return listForecast.getList();
	}

}
