package find_ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import find_ui.entity.values.ValuesQuestion;

public interface ValuesQuestionRepository extends JpaRepository<ValuesQuestion, Long> {
}
