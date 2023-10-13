package ru.ponomarev.MySecondApp.service;
import org.springframework.stereotype. Service;
import ru.ponomarev.MySecondApp.model.Positions;
    @Service
    public interface AnnualBonusService {
        double calculate(Positions positions, double salary, double bonus, int workDays);
        double calculateManagers(Positions positions, double salary, double bonus, int workDays);
}