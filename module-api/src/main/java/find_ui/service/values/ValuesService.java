package find_ui.service.values;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import find_ui.controller.values.request.SaveAnswerForm;
import find_ui.controller.values.request.SaveAnswerForm.QuestionAndSelectableAnswerVo;
import find_ui.controller.values.response.PickedValuesResult;
import find_ui.controller.values.response.PickedValuesResult.ValuesDto;
import find_ui.controller.values.response.QuestionAnswerResult;
import find_ui.controller.values.response.QuestionAnswerResult.AnswerVo;
import find_ui.controller.values.response.QuestionAnswerResult.QuestionAndAnswer;
import find_ui.controller.values.response.QuestionAnswerResult.QuestionAndAnswerVo;
import find_ui.entity.matching.Matching;
import find_ui.entity.matching.QMatching;
import find_ui.entity.user.User;
import find_ui.entity.values.QValuesAnswer;
import find_ui.entity.values.Values;
import find_ui.entity.values.ValuesAnswer;
import find_ui.entity.values.ValuesQuestion;
import find_ui.entity.values.ValuesSelectableAnswer;
import find_ui.enums.AnswerType;
import find_ui.enums.ValuesCategoryType;
import find_ui.enums.ValuesViewType;
import find_ui.enums.response.ReturnCode;
import find_ui.exception.CustomException;
import find_ui.repository.UserRepository;
import find_ui.repository.ValuesAnswerRepository;
import find_ui.repository.ValuesQuestionRepository;
import find_ui.repository.ValuesSelectableAnswerRepository;
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
    private final ValuesSelectableAnswerRepository valuesSelectableAnswerRepository;
    private final JPAQueryFactory jpaQueryFactory;

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

        Map<Long, QuestionAndAnswerVo> allQuestionAndSelectableAnswerMap = new HashMap<>();
        List<ValuesQuestion> valuesQuestionList = valuesQuestionRepository.findAll();
        for (ValuesQuestion item : valuesQuestionList) {
            List<AnswerVo> answerVoList = item.getValuesSelectableAnswers().stream()
                                              .map(i -> AnswerVo.builder()
                                                                .selectableAnswerSequence(
                                                                        i.getValuesSelectableAnswerSequence())
                                                                .answerText(i.getSelectableAnswer())
                                                                .build())
                                              .collect(Collectors.toList());

            QuestionAndAnswerVo questionAndAnswerVo =
                    QuestionAndAnswerVo.builder()
                                       .question(item.getQuestion())
                                       .questionSequence(item.getValuesQuestionSequence())
                                       .selectableAnswerList(answerVoList)
                                       .categoryType(item.getValuesCategory().getValuesCategoryType())
                                       .build();
            allQuestionAndSelectableAnswerMap.put(item.getValuesQuestionSequence(), questionAndAnswerVo);
        }

        // Process user picked answer list
        List<ValuesAnswer> valuesAnswerList = user.getValuesAnswerList();
        for (ValuesAnswer item : valuesAnswerList) {
            // 유저가 대답한 질문에 해당하는 Question 갖고오기
            QuestionAndAnswerVo questionAndAnswerVo =
                    allQuestionAndSelectableAnswerMap.get(item.getValuesQuestion().getValuesQuestionSequence());
            List<AnswerVo> selectableAnswerList = questionAndAnswerVo.getSelectableAnswerList();

            // 유저가 해당 질문에 어떤 답을 했는지 체크
            List<AnswerVo> newSelectableAnswerList = new ArrayList<>();
            for (AnswerVo answerVoItem : selectableAnswerList) {
                if (answerVoItem.getSelectableAnswerSequence() ==
                    item.getValuesSelectableAnswer().getValuesSelectableAnswerSequence()) {
                    answerVoItem = answerVoItem.toBuilder().isChoiced(true).build();
                }
                newSelectableAnswerList.add(answerVoItem);
            }

            // 유저가 택한 답을 갖고 있는 newSelectableAnswerList로 교체
            questionAndAnswerVo = questionAndAnswerVo.toBuilder()
                                                     .selectableAnswerList(newSelectableAnswerList)
                                                     .build();

            allQuestionAndSelectableAnswerMap.put(item.getValuesQuestion().getValuesQuestionSequence(),
                                                  questionAndAnswerVo);
        }

        Map<ValuesCategoryType, List<QuestionAndAnswerVo>> groupBygCategoryMap = new HashMap<>();

        // Initialize by Category
        Arrays.stream(ValuesCategoryType.values()).forEach(i -> groupBygCategoryMap.put(i, new ArrayList<>()));

        allQuestionAndSelectableAnswerMap.values()
                                         .stream()
                                         .forEach(i -> {
                                             List<QuestionAndAnswerVo> questionAndAnswerVoList = groupBygCategoryMap.get(i.getCategoryType());

                                             questionAndAnswerVoList.add(QuestionAndAnswerVo.builder()
                                                                                            .questionSequence(i.getQuestionSequence())
                                                                                            .question(i.getQuestion())
                                                                                            .selectableAnswerList(i.getSelectableAnswerList())
                                                                                            .build());
                                         });

        // Make Data for QuestionAnswerResult
        List<QuestionAndAnswer> questionAndAnswerList = new ArrayList<>();
        for (Map.Entry<ValuesCategoryType, List<QuestionAndAnswerVo>> entry : groupBygCategoryMap.entrySet()) {
            ValuesCategoryType key = entry.getKey();
            List<QuestionAndAnswerVo> value = entry.getValue();

            List<QuestionAndAnswerVo> questionAndAnswerVoList = new ArrayList<>();
            for (QuestionAndAnswerVo item : value) {
                questionAndAnswerVoList.add(item);
            }

            questionAndAnswerList.add(QuestionAndAnswer.builder()
                                                       .categoryType(key)
                                                       .questionAndAnswerInfoList(questionAndAnswerVoList)
                                                       .build());
        }

        return QuestionAnswerResult.of(questionAndAnswerList);
    }

    public void saveAnswer(SaveAnswerForm saveAnswerForm) {
        Long userSequence = saveAnswerForm.getUserSequence();
        Optional<User> userOptional = userRepository.findById(userSequence);
        if (!userOptional.isPresent()) {
            throw new CustomException(ReturnCode.UNKNOWN_ERROR);
        }
        User user = userOptional.get();

        List<QuestionAndSelectableAnswerVo> questionAndSelectableAnswerList =
                saveAnswerForm.getQuestionAndSelectableAnswerList();

        for (QuestionAndSelectableAnswerVo item : questionAndSelectableAnswerList) {
            ValuesQuestion valuesQuestion = valuesQuestionRepository.findById(item.getQuestionSequence()).get();
            ValuesSelectableAnswer valuesSelectableAnswer =
                    valuesSelectableAnswerRepository.findById(item.getSelectableAnswerSequence()).get();

            BooleanBuilder builder = new BooleanBuilder();
            builder.and(QValuesAnswer.valuesAnswer.user.eq(user));
            builder.and(QValuesAnswer.valuesAnswer.valuesQuestion.eq(valuesQuestion));
            ValuesAnswer valuesAnswer = jpaQueryFactory.selectFrom(QValuesAnswer.valuesAnswer)
                                                       .where(builder)
                                                       .fetchOne();

            if (item.getAnswerType() == AnswerType.CANCEL_ANSWER) {
                valuesAnswerRepository.delete(valuesAnswer);
            } else if (item.getAnswerType() == AnswerType.ANSWER) {
                // Prevent Logic Code
                valuesAnswerRepository.delete(valuesAnswer);
                valuesAnswer = ValuesAnswer.builder()
                                           .user(user)
                                           .valuesQuestion(valuesQuestion)
                                           .valuesSelectableAnswer(valuesSelectableAnswer)
                                           .build();
                valuesAnswerRepository.save(valuesAnswer);
            }
        }
    }
}