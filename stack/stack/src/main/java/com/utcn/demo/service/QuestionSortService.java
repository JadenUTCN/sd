package com.utcn.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.utcn.demo.model.Question;
import com.utcn.demo.service.impl.question.QuestionSortType;

public interface QuestionSortService {

    Page<Question> sort(Pageable pageable);

    boolean isSuitableFor(QuestionSortType sortType);

}
