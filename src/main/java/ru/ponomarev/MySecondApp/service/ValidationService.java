package ru.ponomarev.MySecondApp.service;
import org.springframework.stereotype. Service;
import org.springframework. validation. BindingResult;
import ru.ponomarev.MySecondApp.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
