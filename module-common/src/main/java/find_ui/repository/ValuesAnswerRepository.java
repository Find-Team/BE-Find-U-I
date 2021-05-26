package find_ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import find_ui.entity.values.ValuesAnswer;

public interface ValuesAnswerRepository extends JpaRepository<ValuesAnswer, Long> {
}
