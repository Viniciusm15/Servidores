package com.vinicius.serversapi.utils;

import java.time.LocalDate;
import java.time.Period;

public class DateUtils {

    public static int calcularIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            return 0;
        }

        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
