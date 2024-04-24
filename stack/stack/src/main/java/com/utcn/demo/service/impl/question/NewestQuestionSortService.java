package com.utcn.demo.service.impl.question;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.utcn.demo.model.Question;
import com.utcn.demo.repository.QuestionRepository;
import com.utcn.demo.service.QuestionSortService;

import org.springframework.data.domain.Pageable;

@Service
@AllArgsConstructor
public class NewestQuestionSortService implements QuestionSortService {

    private final QuestionRepository questionRepository;

    @Override
    public Page<Question> sort(Pageable pageable) {
        Pageable unsortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return questionRepository.findAllSortByNewest(unsortedPageable);
    }

    @Override
    public boolean isSuitableFor(QuestionSortType sortType) {
        return  QuestionSortType.NEWEST.equals(sortType);
    }

}
