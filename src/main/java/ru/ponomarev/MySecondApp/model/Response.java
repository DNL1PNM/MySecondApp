package ru.ponomarev.MySecondApp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Response {
        // Уникальный идентификатор пользователя
        private String uid;
        // Уникальный идентификатор операции
        private String operationUid;
        // Время в системе
        private String systemTime;
        // Код ответа
        private Codes code;
        // Годовой бонус
        private Double annualBonus;
        // Код ошибки
        private ErrorCodes errorCode;
        // Сообщение об ошибке
        private ErrorMessages errorMessage;
}
