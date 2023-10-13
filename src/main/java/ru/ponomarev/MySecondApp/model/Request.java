package ru.ponomarev.MySecondApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Request {
        // Идентификатор шаблона
        private String templateId;
        // Код SMS
        private String smsCode ;
        // Уникальный идентификатор пользователя
        @NotBlank
        @Max(value = 32)
        private String uid;
        // Уникальный идентификатор операции
        @NotBlank
        @Max(value = 32)
        private String operationUid;
        // Название системы
        private String systemName;
        // Время в системе
        @NotBlank
        private String systemTime;
        // Источник запроса
        private String source;
        // Позиция сотрудника
        private Positions position;
        // Зарплата сотрудника
        private Double salary;
        // Бонус сотрудника
        private Double bonus;
        // Количество рабочих дней
        private Integer workDays;
        // Код продукта
        private Integer productCode;

        // Идентификатор коммуникации
        @Min(value = 1)
        @Max(value = 100000)
        private int communicationId;

        @Override
        public String toString() {
                return "{" +
                        "uid='" + uid + '\'' +
                        ", operationUid='" + operationUid +
                        ", systemName='" + systemName +
                        ", systemTime='" + systemTime +
                        ", source='" + source +
                        ", position" + position +
                        ", salary" + salary +
                        ", bonus" + bonus +
                        ", workDays" + workDays +
                        ", communicationId=" + communicationId +
                        ", templateId" + templateId +
                        ", productCode=" +
                        "smsCode=" + smsCode +
                        '}';
        }
}