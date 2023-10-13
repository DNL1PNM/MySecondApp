package ru.ponomarev.MySecondApp.service;

import org.springframework.stereotype.Service;
import ru.ponomarev.MySecondApp.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
