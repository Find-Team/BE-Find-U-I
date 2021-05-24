package find_ui.entity.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

import find_ui.entity.BaseEntity;
import find_ui.enums.UserImageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_img_seq")
    private Long userImageSequence;

    @Enumerated(EnumType.STRING)
    private UserImageStatus userImageStatus;

    @Lob
    @Column(name = "img_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSequence")
    private User user;

}
