package find_ui.service.values;

import java.util.Optional;

import org.springframework.stereotype.Service;

import find_ui.entity.user.User;
import find_ui.enums.ValuesViewType;
import find_ui.enums.response.ReturnCode;
import find_ui.exception.CustomException;
import find_ui.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValuesService {

    private final UserRepository userRepository;

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
}
