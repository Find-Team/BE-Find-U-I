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
public class ValuesAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "values_answer_Seq")
    private Long valuesAnswerSequence;

    @JoinColumn(name = "user_seq")
    @OneToOne(mappedBy = "user_seq")
    private User userSequence;

    private String answer;

    @JoinColumn(name = "values_question_seq")
    @OneToOne(mappedBy = "values_question_seq")
    private ValuesQuestion valuesQuestionSequence;

}
