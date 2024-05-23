package backend.sasonptumayense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.sasonptumayense.model.Question;
import backend.sasonptumayense.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question getQuestionById(Integer id) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question != null) {
            return question;
        }
        return null;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
