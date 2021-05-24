use Find_UI;

# Add `USER` mock data
INSERT INTO user (name, status, created_date, last_modify_date,
                  nick_name, identity_verification_ymdt, sex_type,
                  location, birthday_ymdt, user_profile_img_seq)
VALUES ('name 01', 'ALIVE', NOW(), NOW(), 'nick_name 01', NOW(), 'MAN', 'location 01', NOW(), 01);


# Add `USER_DTL_INFO` mock data
INSERT INTO user_dtl_info (user_sequence,
                           introduction,
                           basic_info,
                           manner_score,
                           created_date, last_modify_date)
VALUES (1, 'introduction 01',
        '{"job":"basic_info 01","company":"IT Comapny","education":"University","mbti":"ENFP","height":"170 보다 커요","bodyType":"SLIM","smokingType":"NO","religion":"CHRISTIAN","marriedType":"MARRIED","drinkingType":"NO"}',
        10, NOW(), NOW());

# Add `USER_IMAGE` mock data
INSERT INTO user_image(img_url, user_image_status, user_sequence, created_date, last_modify_date)
VALUES ('https://bit.ly/2SiqKRl', 'USER_PROFILE', 1, NOW(), NOW());

INSERT INTO user_image(img_url, user_image_status, user_sequence, created_date, last_modify_date)
VALUES ('https://bit.ly/2TbufcG', 'USER_PROFILE', 1, NOW(), NOW());

INSERT INTO user_image(img_url, user_image_status, user_sequence, created_date, last_modify_date)
VALUES ('https://bit.ly/2TeiWQY', 'USER_PROFILE', 1, NOW(), NOW());
