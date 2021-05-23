package find_ui.entity.matching;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import find_ui.entity.BaseEntity;
import find_ui.entity.user.User;
import find_ui.enums.MatchingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Matching extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_seq")
    private Long matchingSequence;

    @Enumerated(EnumType.STRING)
    private MatchingStatus matchingStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_user_seq")
    private User fromUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_user_seq")
    private User toUser;

}
