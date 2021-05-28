package find_ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import find_ui.entity.user.User;
import find_ui.entity.values.Values;

public interface ValuesRepository extends JpaRepository<Values, Long> {

    void deleteByUser(User user);
}
