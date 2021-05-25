package find_ui.service.values;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import find_ui.controller.values.response.PickedValuesResult;
import find_ui.controller.values.response.PickedValuesResult.ValuesDto;
import find_ui.entity.user.User;
import find_ui.entity.values.Values;
import find_ui.entity.values.ValuesQuestion;
import find_ui.enums.ValuesViewType;
import find_ui.enums.response.ReturnCode;
import find_ui.exception.CustomException;
import find_ui.repository.UserRepository;
import find_ui.repository.ValuesQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValuesService {
    private static final String PREFIX_QUESTION_TEXT = "Q. ";

    private final UserRepository userRepository;
    private final ValuesQuestionRepository valuesQuestionRepository;

    public ValuesViewType getUserValuesViewType(Long userSequence) {
        Optional<User> userOptional = userRepository.findById(userSequence);
        if (!userOptional.isPresent()) {
            throw new CustomException(ReturnCode.UNKNOWN_ERROR);
        }
        User user = userOptional.get();

        ValuesViewType valuesViewType = null;

        if (!user.getValuesList().isEmpty()
            && user.getValuesList().size() == 5) {
            valuesViewType = ValuesViewType.NEED_GET_PICKED_VALUES;
        } else if (user.getValuesList().isEmpty()
                   && !user.getValuesAnswerList().isEmpty()) {
            valuesViewType = ValuesViewType.NEED_PICK_VALUES;
        } else {
            valuesViewType = ValuesViewType.NEED_QUESTIONS_AND_ANSWERS;
        }
        return valuesViewType;
    }

    public PickedValuesResult getUserPickedValues(Long userSequence) {
        Optional<User> userOptional = userRepository.findById(userSequence);
        if (!userOptional.isPresent()) {
            throw new CustomException(ReturnCode.UNKNOWN_ERROR);
        }
        User user = userOptional.get();

        List<Values> valuesList = user.getValuesList();
        List<ValuesDto> valuesDtoList = new ArrayList<>();

        for (Values values : valuesList) {
            Long pickedQuestionSequence = values.getPickValuesQuestionSequence().getValuesQuestionSequence();
            ValuesQuestion question = valuesQuestionRepository.findById(pickedQuestionSequence).get();
            valuesDtoList.add(ValuesDto.builder()
                                       .valuesSequence(question.getValuesQuestionSequence())
                                       .question(PREFIX_QUESTION_TEXT + question.getQuestion())
                                       .build());
        }
        return PickedValuesResult.of(valuesDtoList);
    }
}
