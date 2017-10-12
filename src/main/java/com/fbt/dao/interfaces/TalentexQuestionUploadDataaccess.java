package com.fbt.dao.interfaces;

import java.util.Set;

import com.fbt.dao.entities.QuestionMaster;
import com.fbt.dao.entities.QuestionOptionMapping;

public interface TalentexQuestionUploadDataaccess {
	
	public void QuestionandAnswerUpload(QuestionMaster questionMaster, Set<QuestionOptionMapping> questionOptionMappings);

}
