package edu.uic.ids517.f17g305.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "regressionBean")
@SessionScoped
public class RegressionBean {

	private double intercept = 0.0;
	private double interceptStandardError = 0.0;
	private double pValue = 0.0;
	private double tValue = 0.0;
	private double slope = 0.0;
	private double dfPredictor = 0.0;
	private double dfResidualError = 0.0;
	private double dfTotal = 0.0;
	private double sumSquareRegression = 0.0;
	private double sumSquareError = 0.0;
	private double sumSquareTotal = 0.0;
	private double meanSquare = 0.0;
	private double meanSquareError = 0.0;
	private double fValue = 0.0;
	private double slopeStandardError = 0.0;
	private double tStatisticPredictor = 0.0;
	private double pValuePredictor = 0.0;
	private double standardErrorModel = 0.0;
	private double rSquareValue = 0.0;
	private double adjustedRSquare = 0.0;
	private String regressionEquation = "";
	private String predictor = "";

	public double getIntercept() {
		return intercept;
	}

	public void setIntercept(double intercept) {
		this.intercept = intercept;
	}

	public double getInterceptStandardError() {
		return interceptStandardError;
	}

	public void setInterceptStandardError(double interceptStandardError) {
		this.interceptStandardError = interceptStandardError;
	}

	public double getpValue() {
		return pValue;
	}

	public void setpValue(double pValue) {
		this.pValue = pValue;
	}

	public double gettValue() {
		return tValue;
	}

	public void settValue(double tValue) {
		this.tValue = tValue;
	}

	public double getSlope() {
		return slope;
	}

	public void setSlope(double slope) {
		this.slope = slope;
	}

	public double getDfPredictor() {
		return dfPredictor;
	}

	public void setDfPredictor(double dfPredictor) {
		this.dfPredictor = dfPredictor;
	}

	public double getDfResidualError() {
		return dfResidualError;
	}

	public void setDfResidualError(double dfResidualError) {
		this.dfResidualError = dfResidualError;
	}

	public double getDfTotal() {
		return dfTotal;
	}

	public void setDfTotal(double dfTotal) {
		this.dfTotal = dfTotal;
	}

	public double getSumSquareRegression() {
		return sumSquareRegression;
	}

	public void setSumSquareRegression(double sumSquareRegression) {
		this.sumSquareRegression = sumSquareRegression;
	}

	public double getSumSquareError() {
		return sumSquareError;
	}

	public void setSumSquareError(double sumSquareError) {
		this.sumSquareError = sumSquareError;
	}

	public double getSumSquareTotal() {
		return sumSquareTotal;
	}

	public void setSumSquareTotal(double sumSquareTotal) {
		this.sumSquareTotal = sumSquareTotal;
	}

	public double getMeanSquare() {
		return meanSquare;
	}

	public void setMeanSquare(double meanSquare) {
		this.meanSquare = meanSquare;
	}

	public double getMeanSquareError() {
		return meanSquareError;
	}

	public void setMeanSquareError(double meanSquareError) {
		this.meanSquareError = meanSquareError;
	}

	public double getfValue() {
		return fValue;
	}

	public void setfValue(double fValue) {
		this.fValue = fValue;
	}

	public double getSlopeStandardError() {
		return slopeStandardError;
	}

	public void setSlopeStandardError(double slopeStandardError) {
		this.slopeStandardError = slopeStandardError;
	}

	public double gettStatisticPredictor() {
		return tStatisticPredictor;
	}

	public void settStatisticPredictor(double tStatisticPredictor) {
		this.tStatisticPredictor = tStatisticPredictor;
	}

	public double getpValuePredictor() {
		return pValuePredictor;
	}

	public void setpValuePredictor(double pValuePredictor) {
		this.pValuePredictor = pValuePredictor;
	}

	public double getStandardErrorModel() {
		return standardErrorModel;
	}

	public void setStandardErrorModel(double standardErrorModel) {
		this.standardErrorModel = standardErrorModel;
	}

	public double getrSquareValue() {
		return rSquareValue;
	}

	public void setrSquareValue(double rSquareValue) {
		this.rSquareValue = rSquareValue;
	}

	public double getAdjustedRSquare() {
		return adjustedRSquare;
	}

	public void setAdjustedRSquare(double adjustedRSquare) {
		this.adjustedRSquare = adjustedRSquare;
	}

	public String getRegressionEquation() {
		return regressionEquation;
	}

	public void setRegressionEquation(String regressionEquation) {
		this.regressionEquation = regressionEquation;
	}

	public String getPredictor() {
		return predictor;
	}

	public void setPredictor(String predictor) {
		this.predictor = predictor;
	}
}
