package find_ui.entity.values;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import find_ui.entity.BaseEntity;
import find_ui.entity.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Values extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "values_seq")
    private Long valuesSequence;

    @JoinColumn(name = "user_seq")
    @OneToOne(mappedBy = "user_seq")
    private User userSequence;

    @JoinColumn(name = "pick_values_question_seq")
    @OneToOne(mappedBy = "valuesQuestionSequence")
    private ValuesQuestion pickValuesQuestionSequence;

    private Long priority;

}
