package find_ui.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicInfoUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static BasicInfo getBasicInfo(String basicInfoJson) {
        BasicInfo basicInfo = null;
        try {
            basicInfo = objectMapper.readValue(basicInfoJson, BasicInfo.class);
        } catch (JsonProcessingException e) {
            log.info("Parsing Error", e);
        }
        return basicInfo;
    }
}
