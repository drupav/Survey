package com.survey.app.config;

import java.util.List;

import com.survey.app.job.dto.RespondentDTO;
import com.survey.app.model.Respondent;

public class CSVContext {
	
private List<RespondentDTO> respondentDTOs;
private List<Respondent> respondents;

public List<RespondentDTO> getRespondentDTOs() {
	return respondentDTOs;
}

public void setRespondentDTOs(List<RespondentDTO> respondentDTOs) {
	this.respondentDTOs = respondentDTOs;
}

public List<Respondent> getRespondentDatas() {
	return respondents;
}

public void setRespondentDatas(List<Respondent> respondents) {
	this.respondents = respondents;
}



}
