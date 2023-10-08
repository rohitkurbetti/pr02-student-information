package com.example.workaholic.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mini_project_details")
public class MiniProjectDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mini_project_details_id")
	private Integer miniProjectDetailsId;

	@Column(name = "grp_prj_id")
	private Long grpPrjId;

	@Column(name = "project_title")
	private String prjTitle;

	@Column(name = "is_project_leader")
	private Boolean isProjectLeader=Boolean.FALSE;

	@Column(name = "project_member_name")
	private String projectMemberName;

	@Column(name = "project_subject")
	private String projectSubject;

	@Column(name = "semester")
	private String semester;
	
	@Column(name = "rollno")
	private Integer rollno;
	
	@Transient
	private Long projLeaderRollno;

	@Transient
	private List<Long> projectMemberRollNosList;

//	@Column(name = "marks_json", columnDefinition = "TEXT", length = 2048)
//	private String marksJson;

	@Lob
	@Column(name = "mini_project_files")
	private byte[] miniProjectFiles;


	public Integer getRollno() {
		return rollno;
	}

	public void setRollno(Integer rollno) {
		this.rollno = rollno;
	}

	public Long getProjLeaderRollno() {
		return projLeaderRollno;
	}

	public void setProjLeaderRollno(Long projLeaderRollno) {
		this.projLeaderRollno = projLeaderRollno;
	}

	public List<Long> getProjectMemberRollNosList() {
		return projectMemberRollNosList;
	}

	public void setProjectMemberRollNosList(List<Long> projectMemberRollNosList) {
		this.projectMemberRollNosList = projectMemberRollNosList;
	}

	public Integer getMiniProjectDetailsId() {
		return miniProjectDetailsId;
	}

	public void setMiniProjectDetailsId(Integer miniProjectDetailsId) {
		this.miniProjectDetailsId = miniProjectDetailsId;
	}

	public Long getGrpPrjId() {
		return grpPrjId;
	}

	public void setGrpPrjId(Long grpPrjId) {
		this.grpPrjId = grpPrjId;
	}

	public String getPrjTitle() {
		return prjTitle;
	}

	public void setPrjTitle(String prjTitle) {
		this.prjTitle = prjTitle;
	}

	public Boolean getIsProjectLeader() {
		return isProjectLeader;
	}

	public void setIsProjectLeader(Boolean isProjectLeader) {
		this.isProjectLeader = isProjectLeader;
	}

	public String getProjectMemberName() {
		return projectMemberName;
	}

	public void setProjectMemberName(String projectMemberName) {
		this.projectMemberName = projectMemberName;
	}

	public String getProjectSubject() {
		return projectSubject;
	}

	public void setProjectSubject(String projectSubject) {
		this.projectSubject = projectSubject;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public byte[] getMiniProjectFiles() {
		return miniProjectFiles;
	}

	public void setMiniProjectFiles(byte[] miniProjectFiles) {
		this.miniProjectFiles = miniProjectFiles;
	}

	
	
}
