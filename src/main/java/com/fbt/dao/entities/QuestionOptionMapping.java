package com.fbt.dao.entities;

// Generated Mar 31, 2015 1:18:31 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * QuestionOptionMapping generated by hbm2java
 */
@Entity
@Table(name = "exam_option_master", catalog = "talentex_sme")
public class QuestionOptionMapping implements java.io.Serializable {

	private Integer id;
	private QuestionMaster questionMaster;
	private String optionImage;
	private String optionText;
	private String answer;
	private int optionType;

	public QuestionOptionMapping() {
	}

	public QuestionOptionMapping(QuestionMaster questionMaster,
			String optionText, String answer, int optionType) {
		this.questionMaster = questionMaster;
		this.optionText = optionText;
		this.answer = answer;
		this.optionType = optionType;
	}

	public QuestionOptionMapping(QuestionMaster questionMaster,
			String optionImage, String optionText, String answer, int optionType) {
		this.questionMaster = questionMaster;
		this.optionImage = optionImage;
		this.optionText = optionText;
		this.answer = answer;
		this.optionType = optionType;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_question_master_id", nullable = false)
	public QuestionMaster getQuestionMaster() {
		return this.questionMaster;
	}

	public void setQuestionMaster(QuestionMaster questionMaster) {
		this.questionMaster = questionMaster;
	}

	@Column(name = "option_image")
	public String getOptionImage() {
		return this.optionImage;
	}

	public void setOptionImage(String optionImage) {
		this.optionImage = optionImage;
	}

	@Column(name = "option_text", nullable = false)
	public String getOptionText() {
		return this.optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	@Column(name = "answer", nullable = false, length = 50)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "option_type", nullable = false)
	public int getOptionType() {
		return this.optionType;
	}

	public void setOptionType(int optionType) {
		this.optionType = optionType;
	}

}
