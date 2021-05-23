package find_ui.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import find_ui.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user_dtl_info")
public class UserDetailInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_dtl_info_seq")
    private Long userDetailInfoSequence;

    private String introduction;

    @Column(name = "job")
    private String job;

    @Column(name = "company")
    private String company;

    @Column(name = "education")
    private String education;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "height")
    private String height;

    @Column(name = "bodyType")
    private String bodyType;

    @Column(name = "smoking")
    private String smoking;

    @Column(name = "religion")
    private String religion;

    @Column(name = "marriage")
    private String marriage;

    @Column(name = "drinking")
    private String drinking;

    @OneToOne
    @JoinColumn(name = "userSequence")
    private User user;

    @Column(name = "manner_score")
    private Long mannerScore;
}
