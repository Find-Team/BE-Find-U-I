package find_ui.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import find_ui.controller.myprofile.response.BodyType;
import find_ui.controller.myprofile.response.DrinkingType;
import find_ui.controller.myprofile.response.MarriedType;
import find_ui.controller.myprofile.response.Mbti;
import find_ui.controller.myprofile.response.Religion;
import find_ui.controller.myprofile.response.SmokingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BasicInfo {
    private String job;
    private String company;
    private String education;
    private Mbti mbti;
    private String height;
    private BodyType bodyType;
    private SmokingType smokingType;
    private Religion religion;
    private MarriedType marriedType;
    private DrinkingType drinkingType;
}
