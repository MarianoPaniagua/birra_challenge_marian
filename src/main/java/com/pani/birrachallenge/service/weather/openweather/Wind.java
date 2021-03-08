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
@JsonPropertyOrder({ "deg", "speed" })
public class Wind {

	@JsonProperty("deg")
	private Integer deg;
	@JsonProperty("speed")
	private Double speed;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("deg")
	public Integer getDeg() {
		return deg;
	}

	@JsonProperty("deg")
	public void setDeg(Integer deg) {
		this.deg = deg;
	}

	@JsonProperty("speed")
	public Double getSpeed() {
		return speed;
	}

	@JsonProperty("speed")
	public void setSpeed(Double speed) {
		this.speed = speed;
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