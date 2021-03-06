package com.fbt.dao.entities;

// Generated Mar 31, 2015 1:18:31 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ChapterMaster generated by hbm2java
 */
@Entity
@Table(name = "chapter_master", catalog = "talentex_sme")
public class ChapterMaster implements java.io.Serializable {

	private Integer id;
	private String chapterCode;
	private String chapterName;
	private Set<QuestionMaster> questionMasters = new HashSet<QuestionMaster>();

	public ChapterMaster() {
	}

	public ChapterMaster(String chapterCode, String chapterName) {
		this.chapterCode = chapterCode;
		this.chapterName = chapterName;
	}

	public ChapterMaster(String chapterCode, String chapterName,
			Set<QuestionMaster> questionMasters) {
		this.chapterCode = chapterCode;
		this.chapterName = chapterName;
		this.questionMasters = questionMasters;
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

	@Column(name = "chapter_code", nullable = false, length = 10)
	public String getChapterCode() {
		return this.chapterCode;
	}

	public void setChapterCode(String chapterCode) {
		this.chapterCode = chapterCode;
	}

	@Column(name = "chapter_name", nullable = false, length = 150)
	public String getChapterName() {
		return this.chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chapterMaster")
	public Set<QuestionMaster> getQuestionMasters() {
		return this.questionMasters;
	}

	public void setQuestionMasters(Set<QuestionMaster> questionMasters) {
		this.questionMasters = questionMasters;
	}

}
