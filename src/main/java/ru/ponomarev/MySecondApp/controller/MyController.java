package ru.ponomarev.MySecondApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ponomarev.MySecondApp.exception.UnsupportedCodeException;
import ru.ponomarev.MySecondApp.exception.ValidationFailedException;
import ru.ponomarev.MySecondApp.model.*;
import ru.ponomarev.MySecondApp.service.ModifyRequestService;
import ru.ponomarev.MySecondApp.service.ModifyResponseService;
import ru.ponomarev.MySecondApp.service.ValidationService;
import ru.ponomarev.MySecondApp.util.DateTimeUtil;
import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController
    public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService")ModifyResponseService modifyResponseService,
                        ModifyRequestService modifyRequestService) {

        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("Received request: {}", request);

        Response response = buildInitialResponse(request);

        try {
            handleSpecialCases(request, response, bindingResult);
            validationService.isValid(bindingResult);
        } catch (UnsupportedCodeException e) {
            handleException(response, bindingResult, "Unsupported UID: 123", HttpStatus.BAD_REQUEST);
        } catch (ValidationFailedException e) {
            handleException(response, bindingResult, "Validation failed", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            handleException(response, bindingResult, "Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
        modifyRequestService.modify(request);

        log.info("Modified response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Response buildInitialResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    private void handleSpecialCases(Request request, Response response, BindingResult bindingResult) {
        if ("123".equals(request.getUid())) {
            throw new UnsupportedCodeException("Unsupported UID: 123");
        }
    }

    private void handleException(Response response, BindingResult bindingResult, String errorMessage, HttpStatus httpStatus) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
        response.setErrorMessage(ErrorMessages.UNKNOWN);
        log.error("Exception occurred, response: {}", response);
        bindingResult.addError(new ObjectError("Request", errorMessage));
    }
}
//BindingResult - это часть процесса контроля целостности данных в Spring.
//Без BindingResult сервер продолжит работу после неудачной валидации формы, что может привести к непредсказуемым результатам и ошибкам на стороне пользователя.

