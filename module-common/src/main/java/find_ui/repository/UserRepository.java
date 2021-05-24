package find_ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import find_ui.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
