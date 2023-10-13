package ru.ponomarev.MySecondApp.service;

import org.springframework.stereotype.Service;
import ru.ponomarev.MySecondApp.model.Response;
public interface ModifyResponseService {
    Response modify(Response response);
}