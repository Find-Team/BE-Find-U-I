package find_ui.service;

import static find_ui.domain.QMember.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import find_ui.domain.Member;
import find_ui.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    @Value("${profile-name}")
    private String name;

    private final MemberRepository memberRepository;

    private final JPAQueryFactory jpaQueryFactory;

    public String checkDB() {
        log.info(name);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.id.eq(1L));
        builder.or(member.id.eq(2L));

        // use QueryDSL
        List<Member> memberList = jpaQueryFactory.selectFrom(member)
                                                 .where(builder)
                                                 .fetch();

        log.info("memberList.size() : {}", memberList.size());

        // use CrudRepository interface
        memberRepository.save(Member.builder()
                                    .name("goodGid")
                                    .build());
        return memberRepository.findAll().size() + "";
    }
}