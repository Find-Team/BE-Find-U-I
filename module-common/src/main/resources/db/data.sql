use Find_UI;

############### Add `USER` mock data
INSERT INTO user (name, status, created_date, last_modify_date,
                  nick_name, identity_verification_ymdt, sex_type,
                  location, birthday_ymdt, user_profile_img_seq)
VALUES ('name 01', 'ALIVE', NOW(), NOW(), 'nick_name 01', NOW(), 'MAN', 'location 01', NOW(), 01);

INSERT INTO user (name, status, created_date, last_modify_date,
                  nick_name, identity_verification_ymdt, sex_type,
                  location, birthday_ymdt, user_profile_img_seq)
VALUES ('name 02', 'ALIVE', NOW(), NOW(), 'nick_name 02', NOW(), 'WOMAN', 'location 02', NOW(), 03);

INSERT INTO user (name, status, created_date, last_modify_date,
                  nick_name, identity_verification_ymdt, sex_type,
                  location, birthday_ymdt, user_profile_img_seq)
VALUES ('name 03', 'ALIVE', NOW(), NOW(), 'nick_name 03', NOW(), 'WOMAN', 'location 03', NOW(), 03);


############### Add `USER_DTL_INFO` mock data
INSERT INTO user_dtl_info (user_sequence,
                           introduction,
                           basic_info,
                           manner_score,
                           created_date, last_modify_date)
VALUES (1, 'introduction 01',
        '{"job":"basic_info 01","company":"IT Comapny","education":"University","mbti":"ENFP","height":"170 보다 커요","bodyType":"SLIM","smokingType":"NO","religion":"CHRISTIAN","marriedType":"MARRIED","drinkingType":"NO"}',
        10, NOW(), NOW());

INSERT INTO user_dtl_info (user_sequence,
                           introduction,
                           basic_info,
                           manner_score,
                           created_date, last_modify_date)
VALUES (2, 'introduction 02',
        '{"job":"basic_info 02","company":"IT Comapny","education":"University","mbti":"ENFP","height":"200 보다 커요","bodyType":"SLIM","smokingType":"NO","religion":"CHRISTIAN","marriedType":"MARRIED","drinkingType":"NO"}',
        5, NOW(), NOW());

INSERT INTO user_dtl_info (user_sequence,
                           introduction,
                           basic_info,
                           manner_score,
                           created_date, last_modify_date)
VALUES (3, 'introduction 03',
        '{"job":"basic_info 03","company":"IT Comapny","education":"University","mbti":"ENFP","height":"123 보다 커요","bodyType":"SLIM","smokingType":"NO","religion":"CHRISTIAN","marriedType":"MARRIED","drinkingType":"NO"}',
        3, NOW(), NOW());

############### Add `USER_IMAGE` mock data
INSERT INTO user_image(img_url, image_type, user_sequence, created_date, last_modify_date)
VALUES ('https://bit.ly/2SiqKRl', 'USER_PROFILE', 1, NOW(), NOW());

INSERT INTO user_image(img_url, image_type, user_sequence, created_date, last_modify_date)
VALUES ('https://bit.ly/2TbufcG', 'USER_PROFILE', 1, NOW(), NOW());

INSERT INTO user_image(img_url, image_type, user_sequence, created_date, last_modify_date)
VALUES ('https://bit.ly/2TeiWQY', 'USER_PROFILE', 2, NOW(), NOW());

INSERT INTO user_image(img_url, image_type, user_sequence, created_date, last_modify_date)
VALUES ('https://bit.ly/3uhGvoC', 'USER_PROFILE', 2, NOW(), NOW());

select *
from user_image;

############### Add `USER_IMAGE` mock data

INSERT INTO matching(matching_status, from_user_seq, to_user_seq, created_date, last_modify_date) values ('FEELING', 1, 2, NOW(), NOW());
INSERT INTO matching(matching_status, from_user_seq, to_user_seq, created_date, last_modify_date) values ('DIBS', 2, 1, NOW(), NOW());
INSERT INTO matching(matching_status, from_user_seq, to_user_seq, created_date, last_modify_date) values ('FEELING', 1, 3, NOW(), NOW());
INSERT INTO matching(matching_status, from_user_seq, to_user_seq, created_date, last_modify_date) values ('FEELING', 3, 1, NOW(), NOW());

select *
from matching;


# VALUES CATEGORY
INSERT INTO values_category(category, created_date, last_modify_date)
VALUES ('RELATION_SHIP', NOW(), NOW());

INSERT INTO values_category(category, created_date, last_modify_date)
VALUES ('FAMILY', NOW(), NOW());

INSERT INTO values_category(category, created_date, last_modify_date)
VALUES ('CAREER', NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('연인관계를 주변에 알리고 공개하는 것', 1, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('연인과의 사생활 공유', 1, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('이상적인 연애비용분담', 1, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('애인이 다른 이성친구와 단둘이 만난다면', 1, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('연인과 얼마나 자주 연락하는게 좋나요?', 1, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('연인과의 갈등', 1, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('사귈 때 스킨십에 대한 생각', 1, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('SNS에 연인과의 사진 포스팅', 1, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('장거리 연애, 주말 부부', 1, NOW(), NOW());


INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('시간 약속', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('주말에 필요한 개인적인 시간', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('직업 선택시 중요한 요소', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('자기계발', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('행복한 삶을 위해 필요한 경제력', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('평소 생활리듬', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('20~30대 소비습관', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('평소 운동', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('종교의 중요성', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('정치성향', 2, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('사랑하는 사람이 생긴다면 결혼은?', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('나중에 아이가 있었으면 하나요?', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('미래에 부모님을 모실 수 있나요?', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('결혼 전 경제력', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('결혼 후 자산관리', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('맞벌이 부부의 가사분담', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('반려동물 ', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('배우자의 종교생활', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('사회생활 중 유흥업소 출입', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)
VALUES ('사랑하는 이의 부채', 3, NOW(), NOW());

select *
from values_question;

INSERT INTO values_answer(user_seq, values_question_seq, values_selectable_answer_seq, created_date, last_modify_date) VALUES (1, 1, 1, NOW(), NOW());
INSERT INTO values_answer(user_seq, values_question_seq, values_selectable_answer_seq, created_date, last_modify_date) VALUES (1, 2, 4, NOW(), NOW());
INSERT INTO values_answer(user_seq, values_question_seq, values_selectable_answer_seq, created_date, last_modify_date) VALUES (1, 3, 7, NOW(), NOW());
INSERT INTO values_answer(user_seq, values_question_seq, values_selectable_answer_seq, created_date, last_modify_date) VALUES (1, 4, 11, NOW(), NOW());
select *
from values_answer;

## 1번
INSERT INTO valuation(priority, pick_values_question_seq, user_seq, created_date, last_modify_date) VALUES (1, 1, 1, NOW(), NOW());
INSERT INTO valuation(priority, pick_values_question_seq, user_seq, created_date, last_modify_date) VALUES (1, 6, 1, NOW(), NOW());
INSERT INTO valuation(priority, pick_values_question_seq, user_seq, created_date, last_modify_date) VALUES (1, 11, 1, NOW(), NOW());
select *
from valuation;


INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('관계가 깊어지기 전까지는 금물', 1, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('가까운 사람에게만', 1, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('당당하게 행동', 1, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('모든걸 공유', 2, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('합의한 부분들에 대해서만 공유', 2, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('사생활이나 비밀을 지켜주기', 2, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('칼 더치페이 (5:5)', 3, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('적당히 번갈아가며 (6:4)', 3, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('여유 있는쪽이 더 부담 (7:3)', 3, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('친한 친구라면 술, 영화까지 가능', 4, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('식사, 커피 외에는 불가', 4, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('왜 단둘이 만나?', 4, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('빠쁘더라도 최대한 자주', 5, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('시간 여유가 있고 생각이 날 때 연락', 5, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('오해가 없게 바로바로 풀기', 6, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('시간을 좀 갖고 진정된 후 풀기', 6, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('스킨십도 연애의 중요한 요소', 7, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('결혼 전 관계는 원치 않음', 7, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('공개적으로 올리는건 부담', 8, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('당당하게 올리는걸 선호', 8, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('사랑한다면 가능', 9, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('장기적으로는 분명 어려움', 9, NOW(), NOW());

## 2번째 질문
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('기본이므로 철저히 지켜야함', 10, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('간혹 약속을 지키지 못해도 이해가능', 10, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('딱히 생각해본적 없어요', 11, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('가능하면 연인과 함께', 11, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('하루 정도는 나만의 시간필요', 11, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('행복할 수 있는 일', 12, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('안정적인 생활이 먼저', 12, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('일이 바빠 아직은 힘듬', 13, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('일 외에도 적당히 하는 중', 13, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('다양하게 매우 열심', 13, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('안정적인 삶이 가능한 정도면 충분', 14, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('보통수준 이상의 경제력이 필수', 14, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('아침형', 15, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('올빼미형', 15, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('부족하더라도 미래를 위해 절약', 16, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('젊을 때만 할 수 있는 것들을 위해 충분히 투자', 16, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('규칙적으로 함', 17, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('규칙적으로 못함', 17, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('중요하지 않아요', 18, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('정도를 지킨다면 괜찮아요', 18, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('매우 중요해요', 18, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('보수에 가까워요', 19, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('진보에 가까워요', 19, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('무관심한 편이에요', 19, NOW(), NOW());



### 3번
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('1~2 년 이내라도 고려', 20, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('아직 결혼생각은 이름', 20, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('비혼주의', 20, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('지금은 원치 않아요', 21, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('1~2 명의 아이', 21, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('아니요', 21, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('상황에 따라 가능', 22, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('모시기는 힘들고 다른 방법을 강구', 22, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('충분히 뒷받침돼야만 함', 23, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('함께 살림 차릴 정도면 가능', 23, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('부족해도 같이 만들어가면 됨', 23, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('부부 각자 분리해서 관리', 24, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('부부 공동으로 관리', 24, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('남편이 꼭 도울 필요는 없음', 25, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('남편도 적극적으로 해야만함', 25, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('정해진 남녀 역할구분이 없다고봄', 25, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('키우고 싶어요', 26, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('키우기는 어려워요', 26, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('배우자와 협의', 26, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('배우자에 맞춰 줄 수 있음', 27, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('각자의 종교활동은 존중 해야함', 27, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('이해할 수 없음', 27, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('사회생활이라 불가피, 이해가능', 28, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('어떠한 이유에서도 가지 않는게 맞음', 28, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('결혼전의 부채는 스스로', 29, NOW(), NOW());
INSERT INTO values_selectable_answer(selectable_answer, values_question_seq, created_date, last_modify_date)
VALUES ('불가피한 상황이라면 함께 부담', 29, NOW(), NOW());


select *
from values_selectable_answer;



