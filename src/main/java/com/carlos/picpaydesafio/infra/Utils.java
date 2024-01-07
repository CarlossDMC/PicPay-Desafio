package com.carlos.picpaydesafio.infra;

import org.springframework.stereotype.Component;

@Component
public class Utils {
    public boolean validarString(String value) {
        return value != null && !value.isEmpty();
    }
}
