package find_ui.entity.values;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    private ValuesCategory valuesCategory;

    private String question;

    @OneToMany(mappedBy = "valuesQuestion")
    private List<ValuesSelectableAnswer> valuesSelectableAnswers = new ArrayList<>();


}
