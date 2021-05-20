package find_ui.entity.values;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import find_ui.entity.BaseEntity;
import find_ui.enums.MatchingStatus;
import find_ui.enums.ValuesCategoryType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ValuesCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "values_category_seq")
    private Long valuesCategorySequence;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ValuesCategoryType valuesCategoryType;

}
