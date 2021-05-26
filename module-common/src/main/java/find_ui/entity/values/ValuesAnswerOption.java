package find_ui.entity.values;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import find_ui.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "values_answer_option")
public class ValuesAnswerOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "values_answer_option_seq")
    private Long valuesAnswerOptionSequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "values_question_seq")
    private ValuesQuestion valuesQuestion;

    private String selectableOption;
}
