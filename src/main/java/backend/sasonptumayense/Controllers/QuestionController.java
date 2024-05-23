package backend.sasonptumayense.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.Question;
import backend.sasonptumayense.service.QuestionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionController{
    private final QuestionService questionService;

    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    public Question getQuestionById(Integer id) {
        return questionService.getQuestionById(id);
    }
}
