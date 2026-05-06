package com.bank.credit.enums;

import java.util.HashMap;
import java.util.Map;

import com.bank.credit.exception.InvalidCreditStatusException;

public enum CreditStatus {
        EN_REVISION("En revisión"),
        ACTIVO("Activo"),
        CANCELADO("Cancelado"),
        MORA("En Mora"),
        COBRO_JURIDICO("En cobro jurídico");

        private final String label;

        CreditStatus(String label){
            this.label = label;
        }

        public String getLabel(){
            return label;
        }

        private static final Map<String, CreditStatus> BY_LABEL = new HashMap<>();
        
        static {
            for (CreditStatus type : values()) {
                BY_LABEL.put(type.label, type);
            }
    }

        public static CreditStatus fromLabel(String label){
            CreditStatus type = BY_LABEL.get(label);
            if(type==null) { throw new InvalidCreditStatusException(); }
            return type;
        }

}
