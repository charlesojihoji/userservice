package com.softel.user.response;

import java.util.List;

public class UserServiceResponse {

    private String id;
    private String name;
    private String emailId;
    private String about;
    private List<RateServiceResponse> rateServiceResponse;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public List<RateServiceResponse> getRateServiceResponse() {
		return rateServiceResponse;
	}
	public void setRateServiceResponse(List<RateServiceResponse> rateServiceResponse) {
		this.rateServiceResponse = rateServiceResponse;
	}

}
