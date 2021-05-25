package find_ui.controller.matching.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import find_ui.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class MatchingResult {
    private List<CommonInfo> connected;
    private List<CommonInfo> receivedFeeling;
    private List<CommonInfo> sendFeeling;
    private List<CommonInfo> receivedDibs;
    private List<CommonInfo> sendDibs;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommonInfo {
        private Long userSequence;
        private String profileImageUrl;
        private String nickName;
        private int age;
        private String job;
        private String location;
        private String directMessage;

        public static CommonInfo of(User user, String profileImageUrl,
                                    int age, String job, String directMessage) {
            return builder()
                    .userSequence(user.getUserSequence())
                    .profileImageUrl(profileImageUrl)
                    .nickName(user.getNickName())
                    .age(age)
                    .job(job)
                    .location(user.getLocation())
                    .directMessage(directMessage)
                    .build();

        }

    }
}
