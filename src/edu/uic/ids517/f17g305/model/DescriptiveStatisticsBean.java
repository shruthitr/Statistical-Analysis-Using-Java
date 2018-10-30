package edu.uic.ids517.f17g305.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "descriptiveStatisticsBean")
@SessionScoped
public class DescriptiveStatisticsBean {

	private double mean = 0.0;
	private double minValue = 0.0;
	private double maxValue = 0.0;
	private double median = 0.0;
	private double variance = 0.0;
	private double standardDeviation = 0.0;
	private double range = 0.0;
	private double quartile1 = 0.0;
	private double quartile3 = 0.0;
	private double iqr = 0.0;
	private double coeffOfVariation = 0.0;

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getMedian() {
		return median;
	}

	public void setMedian(double median) {
		this.median = median;
	}

	public double getVariance() {
		return variance;
	}

	public void setVariance(double variance) {
		this.variance = variance;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public double getQuartile1() {
		return quartile1;
	}

	public void setQuartile1(double quartile1) {
		this.quartile1 = quartile1;
	}

	public double getQuartile3() {
		return quartile3;
	}

	public void setQuartile3(double quartile3) {
		this.quartile3 = quartile3;
	}

	public double getIqr() {
		return iqr;
	}

	public void setIqr(double iqr) {
		this.iqr = iqr;
	}

	public double getCoeffOfVariation() {
		return coeffOfVariation;
	}

	public void setCoeffOfVariation(double coeffOfVariation) {
		this.coeffOfVariation = coeffOfVariation;
	}
}
