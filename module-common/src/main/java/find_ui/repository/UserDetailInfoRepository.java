package find_ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import find_ui.entity.user.UserDetailInfo;

public interface UserDetailInfoRepository extends JpaRepository<UserDetailInfo, Long> {
}
