package edu.uic.ids517.f17g305.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "predictorResponseBean")
@SessionScoped
public class PredictorResponseBean {

	private String predictor = "";
	private String response = "";

	public String getPredictor() {
		return predictor;
	}

	public void setPredictor(String predictor) {
		this.predictor = predictor;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public PredictorResponseBean(String predictor, String response) {

		this.predictor = predictor;
		this.response = response;
	}

}
