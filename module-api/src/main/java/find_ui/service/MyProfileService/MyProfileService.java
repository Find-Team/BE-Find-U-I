package find_ui.service.MyProfileService;

import static find_ui.entity.user.QUser.user;
import static find_ui.entity.user.QUserDetailInfo.userDetailInfo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import find_ui.controller.myprofile.response.MyProfileResult;
import find_ui.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyProfileService {

    private final JPAQueryFactory jpaQueryFactory;

    public MyProfileResult getMyProfile(Long userSequence) {
        log.info("userSequence : ", userSequence);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(user.eq(userDetailInfo.user));

        // use QueryDSL
        final List<User> myProfile = jpaQueryFactory.selectFrom(user)
                                                    .where(builder)
                                                    .fetch();

        return null;
    }
}

