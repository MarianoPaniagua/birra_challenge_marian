package com.pani.birrachallenge.service.weather.openweather;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "temp", "temp_min", "grnd_level", "temp_kf", "humidity", "pressure", "sea_level", "feels_like",
		"temp_max" })
public class Main {

	@JsonProperty("temp")
	private Double temp;
	@JsonProperty("temp_min")
	private Double tempMin;
	@JsonProperty("grnd_level")
	private Integer grndLevel;
	@JsonProperty("temp_kf")
	private Double tempKf;
	@JsonProperty("humidity")
	private Integer humidity;
	@JsonProperty("pressure")
	private Integer pressure;
	@JsonProperty("sea_level")
	private Integer seaLevel;
	@JsonProperty("feels_like")
	private Double feelsLike;
	@JsonProperty("temp_max")
	private Double tempMax;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("temp")
	public Double getTemp() {
		return temp;
	}

	@JsonProperty("temp")
	public void setTemp(Double temp) {
		this.temp = temp;
	}

	@JsonProperty("temp_min")
	public Double getTempMin() {
		return tempMin;
	}

	@JsonProperty("temp_min")
	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	@JsonProperty("grnd_level")
	public Integer getGrndLevel() {
		return grndLevel;
	}

	@JsonProperty("grnd_level")
	public void setGrndLevel(Integer grndLevel) {
		this.grndLevel = grndLevel;
	}

	@JsonProperty("temp_kf")
	public Double getTempKf() {
		return tempKf;
	}

	@JsonProperty("temp_kf")
	public void setTempKf(Double tempKf) {
		this.tempKf = tempKf;
	}

	@JsonProperty("humidity")
	public Integer getHumidity() {
		return humidity;
	}

	@JsonProperty("humidity")
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	@JsonProperty("pressure")
	public Integer getPressure() {
		return pressure;
	}

	@JsonProperty("pressure")
	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}

	@JsonProperty("sea_level")
	public Integer getSeaLevel() {
		return seaLevel;
	}

	@JsonProperty("sea_level")
	public void setSeaLevel(Integer seaLevel) {
		this.seaLevel = seaLevel;
	}

	@JsonProperty("feels_like")
	public Double getFeelsLike() {
		return feelsLike;
	}

	@JsonProperty("feels_like")
	public void setFeelsLike(Double feelsLike) {
		this.feelsLike = feelsLike;
	}

	@JsonProperty("temp_max")
	public Double getTempMax() {
		return tempMax;
	}

	@JsonProperty("temp_max")
	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}