package find_ui.entity.values;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import find_ui.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ValuesQuestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "values_question_seq")
    private Long valuesQuestionSequence;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "values_category_seq")
    private ValuesCategory valuesCategorySequence;

    private String question;

}
