package backend.sasonptumayense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
