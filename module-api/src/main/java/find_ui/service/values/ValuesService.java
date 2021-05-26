package find_ui.service.values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import find_ui.controller.values.response.PickedValuesResult;
import find_ui.controller.values.response.PickedValuesResult.ValuesDto;
import find_ui.controller.values.response.QuestionAnswerResult;
import find_ui.controller.values.response.QuestionAnswerResult.QuestionAnswerVo;
import find_ui.entity.user.User;
import find_ui.entity.values.Values;
import find_ui.entity.values.ValuesAnswer;
import find_ui.entity.values.ValuesQuestion;
import find_ui.enums.ValuesCategoryType;
import find_ui.enums.ValuesViewType;
import find_ui.enums.response.ReturnCode;
import find_ui.exception.CustomException;
import find_ui.repository.UserRepository;
import find_ui.repository.ValuesAnswerRepository;
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
    private final ValuesAnswerRepository valuesAnswerRepository;

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

    public QuestionAnswerResult getValuesQuestion(Long userSequence) {
        Optional<User> userOptional = userRepository.findById(userSequence);
        if (!userOptional.isPresent()) {
            throw new CustomException(ReturnCode.UNKNOWN_ERROR);
        }
        User user = userOptional.get();
        List<ValuesAnswer> valuesAnswerList = user.getValuesAnswerList();

        Map<ValuesCategoryType, List<QuestionAnswerVo>> map = new HashMap<>();

        for (ValuesAnswer item : valuesAnswerList) {
            ValuesQuestion valuesQuestion = item.getValuesQuestion();
            ValuesCategoryType valuesCategoryType = valuesQuestion.getValuesCategory().getValuesCategoryType();

            if (!map.containsKey(valuesCategoryType)) {
                // Initialize for Key & Value
                map.computeIfAbsent(valuesCategoryType,
                                    key -> new ArrayList<>());
            }

            List<String> answerOptions = new ArrayList<>();
            valuesQuestion.getValuesAnswerOptions().stream()
                          .forEach(i -> answerOptions.add(i.getSelectableOption()));

            map.computeIfPresent(valuesCategoryType,
                                 (k, v) ->
                                 {
                                     v.add(QuestionAnswerVo.builder()
                                                           .categoryType(valuesCategoryType)
                                                           .question(valuesQuestion.getQuestion())
                                                           .answerOptions(answerOptions)
                                                           .build());
                                     return v;
                                 });

        }

        List<QuestionAnswerVo> questionAndOptions = new ArrayList<>();
        for (Map.Entry<ValuesCategoryType, List<QuestionAnswerVo>> entry : map.entrySet()) {
            ValuesCategoryType key = entry.getKey();
            List<QuestionAnswerVo> value = entry.getValue();
            for (QuestionAnswerVo item : value){
                questionAndOptions.add(item);

            }
        }
        return QuestionAnswerResult.of(questionAndOptions);
    }

    private ArrayList<String> addAnswerOptions(List<String> oldAnswerOptions, String answerOption) {
        ArrayList<String> newAnswerOptions = new ArrayList<>();
        newAnswerOptions.addAll(oldAnswerOptions);
        newAnswerOptions.add(answerOption);
        return newAnswerOptions;
    }
}
