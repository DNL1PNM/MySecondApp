package ru.ponomarev.MySecondApp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ponomarev.MySecondApp.model.Request;
import ru.ponomarev.MySecondApp.util.DateTimeUtil;
import java.util.Date;
@Service
@Qualifier("ModifySystemTimeRequestService")
public class ModifySystemTimeRequestService
            implements ModifyRequestService {
        @Override
        public void modify(Request request) {
            request.setSystemTime(DateTimeUtil.getCustomFormat()
                    .format(new Date()));
            HttpEntity<Request> httpEntity = new HttpEntity<>(request);

            new RestTemplate().exchange("http://localhost:8084/feedback",
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<>() {
                    });
        }
}
