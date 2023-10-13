package ru.ponomarev.MySecondApp.model;
import lombok.Getter;
    @Getter
    public enum Positions {

        DEV(2.2, false),
        HR(1.2, false),
        TLC(2.6, false),
        MNGR(2.0, true),
        ENGR(2.4, false),
        DRCT(1.5, true);
        private final double positionCoefficient;
        private final boolean isManager;
        Positions(double positionCoefficient, boolean isManager){
            this.positionCoefficient =positionCoefficient;
            this.isManager = isManager;
        }
        public boolean isManager() {
            return isManager;
        }
    }
