package com.fbt.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

// Generated Mar 31, 2015 1:18:31 PM by Hibernate Tools 3.4.0.CR1


/**
 * ChapterCourseMapping generated by hbm2java
 */
@Entity
@Table(name = "chapter_course_mapping", catalog = "talentex_sme")
public class ChapterCourseMapping implements java.io.Serializable {

	private Integer id;
	private int chapterMasterId;
	private int courseMasterId;
	private double weightage;

	public ChapterCourseMapping() {
	}

	public ChapterCourseMapping(int chapterMasterId, int courseMasterId,
			double weightage) {
		this.chapterMasterId = chapterMasterId;
		this.courseMasterId = courseMasterId;
		this.weightage = weightage;
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

	@Column(name = "chapter_master_id", nullable = false)
	public int getChapterMasterId() {
		return this.chapterMasterId;
	}

	public void setChapterMasterId(int chapterMasterId) {
		this.chapterMasterId = chapterMasterId;
	}

	@Column(name = "course_master_id", nullable = false)
	public int getCourseMasterId() {
		return this.courseMasterId;
	}

	public void setCourseMasterId(int courseMasterId) {
		this.courseMasterId = courseMasterId;
	}

	@Column(name = "weightage", nullable = false, precision = 22, scale = 0)
	public double getWeightage() {
		return this.weightage;
	}

	public void setWeightage(double weightage) {
		this.weightage = weightage;
	}

}
