package find_ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import find_ui.entity.user.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
}
