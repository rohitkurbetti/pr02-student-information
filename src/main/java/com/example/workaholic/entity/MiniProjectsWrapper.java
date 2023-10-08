package com.example.workaholic.entity;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class MiniProjectsWrapper {

	
	List<MiniProjectDetails> miniProjectDetails;

	public List<MiniProjectDetails> getMiniProjectDetails() {
		return miniProjectDetails;
	}

	public void setMiniProjectDetails(List<MiniProjectDetails> miniProjectDetails) {
		this.miniProjectDetails = miniProjectDetails;
	}
	
	
	
}
