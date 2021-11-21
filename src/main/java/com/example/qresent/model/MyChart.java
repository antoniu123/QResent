package com.example.qresent.model;

import java.util.Objects;

public class MyChart {

	private String x;

	private Integer y;


	public MyChart(String x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MyChart)) {
			return false;
		}
		MyChart myChart = (MyChart) o;
		return Objects.equals(x, myChart.x) && Objects.equals(y, myChart.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
