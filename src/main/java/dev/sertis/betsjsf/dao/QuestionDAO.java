package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.domain.Event;
import dev.sertis.betsjsf.domain.Question;

import java.time.LocalDate;
import java.util.List;

public interface QuestionDAO {

    void save(Question question);

    Question update(Question question);

    void delete(Question question);

    Question getQuestionById(Long questionId);
    
}
