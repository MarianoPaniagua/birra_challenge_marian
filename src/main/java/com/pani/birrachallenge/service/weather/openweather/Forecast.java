package com.pani.birrachallenge.service.weather.openweather;

import java.io.Serializable;
import java.util.List;

public class Forecast implements Serializable {

	private static final long serialVersionUID = 1L;
	private Main main;
	private Long dt;
	private List<Weather> wheatherList;
	private Clouds clouds;
	private Wind wind;
	private Long visibility;
	private Long pop;
	private Sys sys;
	private String date;

	public Forecast() {

	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Long getDt() {
		return dt;
	}

	public void setDt(Long dt) {
		this.dt = dt;
	}

	public List<Weather> getWheatherList() {
		return wheatherList;
	}

	public void setWheatherList(List<Weather> wheatherList) {
		this.wheatherList = wheatherList;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Long getVisibility() {
		return visibility;
	}

	public void setVisibility(Long visibility) {
		this.visibility = visibility;
	}

	public Long getPop() {
		return pop;
	}

	public void setPop(Long pop) {
		this.pop = pop;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
