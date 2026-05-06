package com.bank.credit.enums;

import java.util.HashMap;
import java.util.Map;

import com.bank.credit.exception.InvalidDocumentTypeException;

public enum DocumentType {
        CEDULA_CIUDADANIA("Cédula de ciudadanía"),
        PASAPORTE("Pasaporte"),
        CEDULA_EXTRANJERIA("Cédula de extranjería");

        private final String label;

        DocumentType(String label){
            this.label = label;
        }

        public String getLabel(){
            return label;
        }

        private static final Map<String, DocumentType> BY_LABEL = new HashMap<>();
        
        static {
            for (DocumentType type : values()) {
                BY_LABEL.put(type.label, type);
            }
    }

        public static DocumentType fromLabel(String label){
            DocumentType type = BY_LABEL.get(label);
            if(type==null) { throw new InvalidDocumentTypeException(); }
            return type;
        }
    }
