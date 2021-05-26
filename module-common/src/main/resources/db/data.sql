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

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('관계와 관련된 질문 !!!', 1, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('관계와 관련된 질문 !!!', 1, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('관계와 관련된 질문 !!!', 1, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('관계와 관련된 질문 !!!', 1, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('관계와 관련된 질문 !!!', 1, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('가족과 관련된 질문 !!!', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('가족과 관련된 질문 !!!', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('가족과 관련된 질문 !!!', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('가족과 관련된 질문 !!!', 2, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date)VALUES ('가족과 관련된 질문 !!!', 2, NOW(), NOW());

INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date) VALUES ('직장과 관련된 질문 !!!', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date) VALUES ('직장과 관련된 질문 !!!', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date) VALUES ('직장과 관련된 질문 !!!', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date) VALUES ('직장과 관련된 질문 !!!', 3, NOW(), NOW());
INSERT INTO values_question(question, values_category_seq, created_date, last_modify_date) VALUES ('직장과 관련된 질문 !!!', 3, NOW(), NOW());

select *
from values_question;

INSERT INTO values_answer(answer, user_seq, values_question_seq,created_date, last_modify_date) VALUES('answer1', 1, 1, NOW(),NOW());
INSERT INTO values_answer(answer, user_seq, values_question_seq,created_date, last_modify_date) VALUES('answer2', 1, 2, NOW(),NOW());
INSERT INTO values_answer(answer, user_seq, values_question_seq,created_date, last_modify_date) VALUES('answer3', 1, 3, NOW(),NOW());
INSERT INTO values_answer(answer, user_seq, values_question_seq,created_date, last_modify_date) VALUES('answer4', 1, 4, NOW(),NOW());
INSERT INTO values_answer(answer, user_seq, values_question_seq,created_date, last_modify_date) VALUES('answer5', 1, 5, NOW(),NOW());

select *
from values_answer;


INSERT INTO valuation(priority, pick_values_question_seq, user_seq, created_date, last_modify_date) VALUES (1, 1, 1, NOW(), NOW());
INSERT INTO valuation(priority, pick_values_question_seq, user_seq, created_date, last_modify_date) VALUES (1, 6, 1, NOW(), NOW());
INSERT INTO valuation(priority, pick_values_question_seq, user_seq, created_date, last_modify_date) VALUES (1, 11, 1, NOW(), NOW());
select *
from valuation;


INSERT INTO values_answer_option(selectable_option, values_question_seq, created_date, last_modify_date) VALUES ('selectable option 1-1', 1, NOW(), NOW());
INSERT INTO values_answer_option(selectable_option, values_question_seq, created_date, last_modify_date) VALUES ('selectable option 1-2', 1, NOW(), NOW());
INSERT INTO values_answer_option(selectable_option, values_question_seq, created_date, last_modify_date) VALUES ('selectable option 2-1', 2, NOW(), NOW());
INSERT INTO values_answer_option(selectable_option, values_question_seq, created_date, last_modify_date) VALUES ('selectable option 2-2', 2, NOW(), NOW());

select *
from values_answer_option;

