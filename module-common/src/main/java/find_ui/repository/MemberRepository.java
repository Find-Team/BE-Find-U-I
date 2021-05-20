package find_ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import find_ui.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}