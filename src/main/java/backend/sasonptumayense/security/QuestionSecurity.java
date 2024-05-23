package backend.sasonptumayense.security;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.QuestionController;
import backend.sasonptumayense.config.GlobalMappingConfig;
import backend.sasonptumayense.model.Question;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionSecurity extends GlobalMappingConfig {
	private final QuestionController questionController;
	
	@GetMapping(value = "questions")
	public List<Question> getQuestions() {
		return questionController.getAllQuestions();
	}
}
