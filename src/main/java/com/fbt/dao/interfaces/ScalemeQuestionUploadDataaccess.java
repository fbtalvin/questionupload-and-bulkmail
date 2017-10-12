package com.fbt.dao.interfaces;

import java.util.List;
import java.util.Set;

import com.fbt.dao.entities.Options;
import com.fbt.dao.entities.Question;

public interface ScalemeQuestionUploadDataaccess {
	
	public void QuestionandAnswerUpload(Question question, Set<Options> option);
}
