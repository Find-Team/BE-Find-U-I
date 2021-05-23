package find_ui.entity.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import find_ui.entity.BaseEntity;
import find_ui.enums.Sex;
import find_ui.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userSequence")
    private Long userSequence;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(20) default 'SUSPEND'")
    private UserStatus status;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "identity_verification_ymdt")
    private LocalDateTime identityVerificationYmdt;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String location;

    @Column(name = "birthday_ymdt")
    private LocalDateTime birthDayYmdt;

    @Column(name = "user_profile_img_seq")
    private Long userProfileImageSequence;

}