package edu.uic.ids517.f17g305.controller;

import java.awt.Color;
import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import edu.uic.ids517.f17g305.model.DescriptiveStatisticsBean;
import edu.uic.ids517.f17g305.model.PredictorResponseBean;
import edu.uic.ids517.f17g305.model.RegressionBean;

@ManagedBean(name = "statisticsBean")
@SessionScoped
public class StatisticsBean {

	private DataAccessBean dataAccessBean = new DataAccessBean();
	private DescriptiveStatisticsBean descriptiveStatisticsBean = new DescriptiveStatisticsBean();
	private RegressionBean regressionBean = new RegressionBean();
	private JFreeChart scatterPlot = null;
	private XYSeries xySeries = new XYSeries("xySeries");
	private XYSeriesCollection xySeriesCollectionVar = new XYSeriesCollection();
	private XYSeriesCollection xySeriesCollectionTime = new XYSeriesCollection();
	private XYSeries xySeriesPredictor = new XYSeries("xySeriesPredictor");
	private XYSeries xyResponseSeries = new XYSeries("xyResponseSeries");
	private static XYSeries scatterPlotSeries;
	private static XYSeriesCollection datasetCollection;
	private String chartPath;
	private ArrayList<PredictorResponseBean> predictorResponseBeanList = new ArrayList<>();
	private ArrayList<String> columnsData = new ArrayList<String>();
	private String operation;
	private String filepath;
	private String predictorVariable;
	private String responseVariable;
	private String errorMessage = "";
	private String predictorValue;
	private String responseValue;
	private String status;
	private boolean renderColumnList = false;
	private boolean renderStatisticsColumns = false;
	private boolean renderRegression = false;
	private boolean renderRegressionTable = false;
	private boolean renderScatterPlot = false;

	public DataAccessBean getDataAccessBean() {
		return dataAccessBean;
	}

	public void setDataAccessBean(DataAccessBean dataAccessBean) {
		this.dataAccessBean = dataAccessBean;
	}

	public DescriptiveStatisticsBean getDescriptiveStatisticsBean() {
		return descriptiveStatisticsBean;
	}

	public void setDescriptiveStatisticsBean(DescriptiveStatisticsBean descriptiveStatisticsBean) {
		this.descriptiveStatisticsBean = descriptiveStatisticsBean;
	}

	public RegressionBean getRegressionBean() {
		return regressionBean;
	}

	public void setRegressionBean(RegressionBean regressionBean) {
		this.regressionBean = regressionBean;
	}

	public JFreeChart getScatterPlot() {
		return scatterPlot;
	}

	public void setScatterPlot(JFreeChart scatterPlot) {
		this.scatterPlot = scatterPlot;
	}

	public XYSeries getXySeries() {
		return xySeries;
	}

	public void setXySeries(XYSeries xySeries) {
		this.xySeries = xySeries;
	}

	public XYSeriesCollection getXySeriesCollectionVar() {
		return xySeriesCollectionVar;
	}

	public void setXySeriesCollectionVar(XYSeriesCollection xySeriesCollectionVar) {
		this.xySeriesCollectionVar = xySeriesCollectionVar;
	}

	public XYSeriesCollection getXySeriesCollectionTime() {
		return xySeriesCollectionTime;
	}

	public void setXySeriesCollectionTime(XYSeriesCollection xySeriesCollectionTime) {
		this.xySeriesCollectionTime = xySeriesCollectionTime;
	}

	public XYSeries getXySeriesPredictor() {
		return xySeriesPredictor;
	}

	public void setXySeriesPredictor(XYSeries xySeriesPredictor) {
		this.xySeriesPredictor = xySeriesPredictor;
	}

	public XYSeries getXyResponseSeries() {
		return xyResponseSeries;
	}

	public void setXyResponseSeries(XYSeries xyResponseSeries) {
		this.xyResponseSeries = xyResponseSeries;
	}

	public static XYSeries getScatterPlotSeries() {
		return scatterPlotSeries;
	}

	public static void setScatterPlotSeries(XYSeries scatterPlotSeries) {
		StatisticsBean.scatterPlotSeries = scatterPlotSeries;
	}

	public static XYSeriesCollection getDatasetCollection() {
		return datasetCollection;
	}

	public static void setDatasetCollection(XYSeriesCollection datasetCollection) {
		StatisticsBean.datasetCollection = datasetCollection;
	}

	public String getChartPath() {
		return chartPath;
	}

	public void setChartPath(String chartPath) {
		this.chartPath = chartPath;
	}

	public ArrayList<PredictorResponseBean> getPredictorResponseBeanList() {
		return predictorResponseBeanList;
	}

	public void setPredictorResponseBeanList(ArrayList<PredictorResponseBean> predictorResponseBeanList) {
		this.predictorResponseBeanList = predictorResponseBeanList;
	}

	public ArrayList<String> getColumnsData() {
		return columnsData;
	}

	public void setColumnsData(ArrayList<String> columnsData) {
		this.columnsData = columnsData;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getPredictorVariable() {
		return predictorVariable;
	}

	public void setPredictorVariable(String predictorVariable) {
		this.predictorVariable = predictorVariable;
	}

	public String getResponseVariable() {
		return responseVariable;
	}

	public void setResponseVariable(String responseVariable) {
		this.responseVariable = responseVariable;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPredictorValue() {
		return predictorValue;
	}

	public void setPredictorValue(String predictorValue) {
		this.predictorValue = predictorValue;
	}

	public String getResponseValue() {
		return responseValue;
	}

	public void setResponseValue(String responseValue) {
		this.responseValue = responseValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isRenderColumnList() {
		return renderColumnList;
	}

	public void setRenderColumnList(boolean renderColumnList) {
		this.renderColumnList = renderColumnList;
	}

	public boolean isRenderStatisticsColumns() {
		return renderStatisticsColumns;
	}

	public void setRenderStatisticsColumns(boolean renderStatisticsColumns) {
		this.renderStatisticsColumns = renderStatisticsColumns;
	}

	public boolean isRenderRegression() {
		return renderRegression;
	}

	public void setRenderRegression(boolean renderRegression) {
		this.renderRegression = renderRegression;
	}

	public boolean isRenderRegressionTable() {
		return renderRegressionTable;
	}

	public void setRenderRegressionTable(boolean renderRegressionTable) {
		this.renderRegressionTable = renderRegressionTable;
	}

	public boolean isRenderScatterPlot() {
		return renderScatterPlot;
	}

	public void setRenderScatterPlot(boolean renderScatterPlot) {
		this.renderScatterPlot = renderScatterPlot;
	}

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> m = context.getExternalContext().getSessionMap();
		dataAccessBean = (DataAccessBean) m.get("dataAccessBean");
	}

	public String generateDescriptive() {
		errorMessage = "";
		renderColumnList = false;
		renderStatisticsColumns = false;
		renderRegression = false;
		renderScatterPlot = false;
		renderRegressionTable = false;
		String status = "fail";
		double dataList[] = null;
		dataAccessBean.generateColumnData();
		ArrayList<Integer> columnsType = new ArrayList<Integer>();
		columnsType = dataAccessBean.getColumnsType();
		columnsData = dataAccessBean.getColumnsData();

		if (null != columnsData && columnsData.size() != 0) {

			errorMessage = "";
			if (columnsType.contains(1) || columnsType.contains(12)) {
				renderColumnList = false;
				errorMessage = "Cannot perform Descriptive Stastistics on categorical columns.";
				status = "fail";
			} else {
				errorMessage = "";
				int dataSize = columnsData.size();
				dataList = new double[dataSize];
				if (columnsType.contains(6)) {
					for (int r = 0; r < dataSize; r++) {
						NumberFormat f = NumberFormat.getInstance();
						try {
							if (columnsData.get(r) == null) {
								columnsData.set(r, "0.0");
								dataList[r] = 0.0;
							} else {
								dataList[r] = f.parse(columnsData.get(r)).doubleValue();
							}
						} catch (ParseException e) {
							errorMessage = e.getMessage();
						} catch (Exception e) {
							errorMessage = e.getMessage();
						}
					}
				} else {

					for (int r = 0; r < dataSize; r++) {
						if (columnsData.get(r) == null) {
							columnsData.set(r, "0.0");
							dataList[r] = 0.0;
						} else {
							dataList[r] = Double.parseDouble(columnsData.get(r));
						}
					}
				}

				double mean = Math.round(StatUtils.mean(dataList) * 100) / 100.0;
				double minValue = Math.round(StatUtils.min(dataList) * 100.0) / 100.0;
				double maxValue = Math.round(StatUtils.max(dataList) * 100.0) / 100.0;
				double median = Math.round(StatUtils.percentile(dataList, 50.0) * 100.0) / 100.0;
				double variance = Math.round(StatUtils.variance(dataList, mean) * 100.0) / 100.0;
				double standardDeviation = Math.round(Math.sqrt(variance) * 100.0) / 100.0;
				double range = maxValue - minValue;
				double q1 = Math.round(StatUtils.percentile(dataList, 25.0) * 100.0) / 100.0;
				double q3 = Math.round(StatUtils.percentile(dataList, 75.0) * 100.0) / 100.0;
				double iqr = Math.round(q3 - q1) * 100.0 / 100.0;
				double cVar = Math.round((standardDeviation / mean) * 100);

				descriptiveStatisticsBean.setMean(mean);
				descriptiveStatisticsBean.setMinValue(minValue);
				descriptiveStatisticsBean.setMaxValue(maxValue);
				descriptiveStatisticsBean.setMedian(median);
				descriptiveStatisticsBean.setVariance(variance);
				descriptiveStatisticsBean.setStandardDeviation(standardDeviation);
				descriptiveStatisticsBean.setRange(range);
				descriptiveStatisticsBean.setQuartile1(q1);
				descriptiveStatisticsBean.setQuartile3(q3);
				descriptiveStatisticsBean.setIqr(iqr);
				descriptiveStatisticsBean.setCoeffOfVariation(cVar);

				renderColumnList = true;
				status = "success";
			}
		} else {
			renderColumnList = false;
			errorMessage = "No data present in the selected column.";
			status = "fail";
		}
		return status;
	}

	public JFreeChart drawScatterPlot(String title, String tableName, String predictor, String response,
			double predictorValue[], double responseValue[]) {

		errorMessage = "";
		datasetCollection = new XYSeriesCollection();

		String label = predictor + " vs " + response;
		scatterPlotSeries = new XYSeries(label);

		for (int i = 0; i < predictorValue.length; i++) {
			scatterPlotSeries.add(predictorValue[i], responseValue[i]);
		}

		datasetCollection.addSeries(scatterPlotSeries);
		JFreeChart scatterPlot = ChartFactory.createScatterPlot(title, predictor, response, datasetCollection,
				PlotOrientation.VERTICAL, true, true, false);
		XYPlot xyplot = scatterPlot.getXYPlot();
		xyplot.getRenderer().setSeriesPaint(0, Color.blue);

		double predictMinValue = StatUtils.min(predictorValue);
		double predictMaxValue = StatUtils.max(predictorValue);

		double regressionValues[] = Regression.getOLSRegression(datasetCollection, 0);
		LineFunction2D linefunction2d = new LineFunction2D(regressionValues[0], regressionValues[1]);

		XYDataset dataset = DatasetUtilities.sampleFunction2D(linefunction2d, predictMinValue, predictMaxValue,
				predictorValue.length, "Fitted Regression Line");

		xyplot.setDataset(1, dataset);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(true, false);
		xylineandshaperenderer.setSeriesPaint(0, Color.RED);
		xyplot.setRenderer(1, xylineandshaperenderer);

		return scatterPlot;

	}

	public String scatterCallPlot() {

		errorMessage = "";
		File out = null;
		String scatterDrawn = "fail";
		String columnStatus = dataAccessBean.generateColumnValues(predictorVariable, responseVariable);

		for (int i = 0; i < dataAccessBean.getResponseVariableValues().size(); i++) {

			if ((dataAccessBean.getResponseVariableValues().get(i) == null)) {
				dataAccessBean.getResponseVariableValues().set(i, 0.0);
			}
		}
		for (int i = 0; i < dataAccessBean.getPredictorVariableValues().size(); i++) {

			if ((dataAccessBean.getPredictorVariableValues().get(i) == null)) {
				dataAccessBean.getPredictorVariableValues().set(i, 0.0);
			}
		}

		double[] columnNameValueResponse = dataAccessBean.getResponseVariableValues().stream().mapToDouble(d -> d)
				.toArray();
		double[] columnNameValuePredictor = dataAccessBean.getPredictorVariableValues().stream().mapToDouble(d -> d)
				.toArray();
		String title = "Scatter Plot: " + predictorVariable + " vs " + responseVariable;
		if (columnStatus.equalsIgnoreCase("success")) {
			scatterPlot = drawScatterPlot(title, "table", predictorVariable, responseVariable, columnNameValuePredictor,
					columnNameValueResponse);
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				String path = context.getExternalContext().getRealPath("/charts");
				File tempFile = new File(path);
				if (!tempFile.exists()) {
					new File(path).mkdirs();
				}
				chartPath = "/charts/" + scatterPlot + ".png";
				filepath = path + "/" + scatterPlot + ".png";
				out = new File(filepath);

				ChartUtilities.saveChartAsPNG(out, scatterPlot, 600, 500);
				renderScatterPlot = true;
				scatterDrawn = "success";
			} catch (Exception e) {
				errorMessage = e.getMessage();
				scatterDrawn = "fail";
				renderScatterPlot = false;
			}
		}

		else {
			errorMessage = "Improper data.";
		}
		return scatterDrawn;
	}

	public String calculateRegressionVariables() {

		errorMessage = "";
		renderScatterPlot = false;
		renderRegressionTable = false;
		predictorResponseBeanList.clear();
		xySeriesPredictor.clear();
		xyResponseSeries.clear();
		xySeriesCollectionTime = new XYSeriesCollection();
		xySeriesCollectionVar = new XYSeriesCollection();

		double intercept = 0.0;
		double interceptStandardError = 0.0;
		double pValue = 0.0;
		double tValue = 0.0;
		double slope = 0.0;
		double dfPredictor = 0.0;
		double dfResidualError = 0.0;
		double dfTotal = 0.0;
		double sumSquareRegression = 0.0;
		double sumSquareError = 0.0;
		double sumSquareTotal = 0.0;
		double meanSquare = 0.0;
		double meanSquareError = 0.0;
		double fValue = 0.0;
		double slopeStandardError = 0.0;
		double tStatisticPredictor = 0.0;
		double pValuePredictor = 0.0;
		double standardErrorModel = 0.0;
		double rSquareValue = 0.0;
		double adjustedRSquare = 0.0;
		String regressionEquation = "";

		try {
			ArrayList<Double> predictorList = new ArrayList<Double>();
			ArrayList<Double> responseList = new ArrayList<Double>();
			String status = dataAccessBean.generateColumnValues(predictorValue, responseValue);

			if (status.equalsIgnoreCase("success")) {
				predictorList = dataAccessBean.getPredictorVariableValues();
				responseList = dataAccessBean.getResponseVariableValues();

				for (int i = 0; i < dataAccessBean.getPredictorVariableValues().size(); i++) {

					predictorResponseBeanList.add(
							new PredictorResponseBean(predictorList.get(i).toString(), responseList.get(i).toString()));

				}

				double[] predictorArray = new double[predictorList.size()];
				for (int i = 0; i < predictorList.size(); i++) {
					predictorArray[i] = Double.parseDouble(predictorList.get(i).toString());
					xySeriesPredictor.add(i + 1, predictorArray[i]);
				}

				double[] responseArray = new double[responseList.size()];
				for (int i = 0; i < responseList.size(); i++) {
					responseArray[i] = (double) responseList.get(i);
					xyResponseSeries.add(i + 1, (double) responseList.get(i));
				}
				xySeriesCollectionTime.addSeries(xySeriesPredictor);
				xySeriesCollectionTime.addSeries(xyResponseSeries);

				SimpleRegression sr = new SimpleRegression();
				if (responseArray.length > predictorArray.length) {
					for (int i = 0; i < predictorArray.length; i++) {
						sr.addData(predictorArray[i], responseArray[i]);
						xySeries.add(predictorArray[i], responseArray[i]);
					}
				} else {
					for (int i = 0; i < responseArray.length; i++) {
						sr.addData(predictorArray[i], responseArray[i]);
						xySeries.add(predictorArray[i], responseArray[i]);
					}
				}
				xySeriesCollectionVar.addSeries(xySeries);
				dfTotal = responseArray.length - 1;
				TDistribution tDistribution = new TDistribution(dfTotal);
				intercept = sr.getIntercept();
				interceptStandardError = sr.getInterceptStdErr();
				tValue = 0;
				dfPredictor = 1;
				dfResidualError = dfTotal - dfPredictor;
				rSquareValue = sr.getRSquare();
				adjustedRSquare = rSquareValue - (1 - rSquareValue) / (dfTotal - dfPredictor - 1);
				if (interceptStandardError != 0) {
					tValue = (double) intercept / interceptStandardError;
				} else
					tValue = Double.NaN;
				pValue = (double) 2 * tDistribution.cumulativeProbability(-Math.abs(tValue));
				slope = sr.getSlope();
				slopeStandardError = sr.getSlopeStdErr();
				double tStatisticpredict = 0;
				if (slopeStandardError != 0) {
					tStatisticpredict = (double) slope / slopeStandardError;
				}
				pValuePredictor = (double) 2 * tDistribution.cumulativeProbability(-Math.abs(tStatisticpredict));
				standardErrorModel = Math.sqrt(StatUtils.variance(responseArray)) * (Math.sqrt(1 - adjustedRSquare));
				sumSquareRegression = sr.getRegressionSumSquares();
				sumSquareError = sr.getSumSquaredErrors();
				sumSquareTotal = sr.getTotalSumSquares();
				meanSquare = 0;
				if (dfPredictor != 0) {
					meanSquare = sumSquareRegression / dfPredictor;
				}
				meanSquareError = 0;
				if (dfResidualError != 0) {
					meanSquareError = (double) (sumSquareError / dfResidualError);
				}
				fValue = 0;
				if (meanSquareError != 0) {
					fValue = meanSquare / meanSquareError;
				}
				regressionEquation = responseValue + " = " + intercept + " + (" + slope + ") " + predictorValue;

				FDistribution fDistribution = new FDistribution(dfPredictor, dfResidualError);
				pValue = (double) (1 - fDistribution.cumulativeProbability(fValue));

				regressionBean.setIntercept(intercept);
				regressionBean.setInterceptStandardError(interceptStandardError);
				regressionBean.setpValue(pValue);
				regressionBean.settValue(tValue);
				regressionBean.setSlope(slope);
				regressionBean.setDfPredictor(dfPredictor);
				regressionBean.setDfResidualError(dfResidualError);
				regressionBean.setDfTotal(dfTotal);
				regressionBean.setSumSquareRegression(sumSquareRegression);
				regressionBean.setSumSquareError(sumSquareError);
				regressionBean.setSumSquareTotal(sumSquareTotal);
				regressionBean.setMeanSquare(meanSquare);
				regressionBean.setMeanSquareError(meanSquareError);
				regressionBean.setfValue(fValue);
				regressionBean.setSlopeStandardError(slopeStandardError);
				regressionBean.settStatisticPredictor(tStatisticpredict);
				regressionBean.setpValuePredictor(pValuePredictor);
				regressionBean.setStandardErrorModel(standardErrorModel);
				regressionBean.setrSquareValue(rSquareValue);
				regressionBean.setAdjustedRSquare(adjustedRSquare);
				regressionBean.setRegressionEquation(regressionEquation);
				regressionBean.setPredictor(predictorValue);

				status = "success";
				renderRegression = true;
				renderRegressionTable = true;
				return status;

			} else {
				errorMessage = "Improper data.";
				status = "fail";
				renderRegression = false;
				renderRegressionTable = false;
				return status;
			}

		} catch (Exception e) {
			errorMessage = "Failure generating Regression.";
			status = "fail";
			renderRegression = false;
			renderRegressionTable = false;
			return status;
		}
	}

	public String runOperation() {

		errorMessage = "";
		String status = "";
		String columns[] = { "List of columns" };
		String[] cName = new String[11];
		ArrayList<String> cValue = new ArrayList<>();

		switch (operation) {

		case "excelColumns":
			renderStatisticsColumns = false;
			renderColumnList = false;
			renderScatterPlot = false;
			renderRegression = false;
			renderRegressionTable = false;
			status = new FileDownloadBean().downloadFile(columns, dataAccessBean.getColumnList(), "Column_Names.csv");
			break;

		case "descriptiveStatistics":
			renderStatisticsColumns = false;
			renderColumnList = false;
			renderScatterPlot = false;
			renderRegression = false;
			renderRegressionTable = false;
			status = generateDescriptive();
			break;

		case "excelStats":
			renderStatisticsColumns = false;
			renderScatterPlot = false;
			renderRegression = false;
			renderRegressionTable = false;
			status = generateDescriptive();
			renderColumnList = false;
			if (status.contains("success")) {
				cName = new String[] { "Mean", "Minimum Value", "Maximum Value", "Median", "Variance",
						"Standard Deviation", "Range", "Quartile 1", "Quartile 3", "Inter-quartile Range",
						"Coefficient of Variation" };

				cValue.add(String.valueOf(descriptiveStatisticsBean.getMean()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getMinValue()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getMaxValue()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getMedian()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getVariance()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getStandardDeviation()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getRange()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getQuartile1()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getQuartile3()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getIqr()));
				cValue.add(String.valueOf(descriptiveStatisticsBean.getCoeffOfVariation()));
				renderColumnList = false;
				status = new FileDownloadBean().downloadFile(cName, cValue, "Descriptive_Statistics.csv");
			} else {
				errorMessage = "Cannot perform Descriptive Stastistics on categorical columns.";
			}
			break;

		case "scatterPlot":
			renderStatisticsColumns = true;
			renderScatterPlot = true;
			renderColumnList = false;
			renderRegression = false;
			renderRegressionTable = false;
			break;

		case "regression":
			renderStatisticsColumns = false;
			renderScatterPlot = false;
			renderColumnList = false;
			renderRegression = true;
			renderRegressionTable = false;
			break;
		}
		return status;
	}
}
