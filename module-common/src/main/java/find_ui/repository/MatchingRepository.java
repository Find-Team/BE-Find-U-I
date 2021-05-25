package find_ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import find_ui.entity.matching.Matching;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
}
