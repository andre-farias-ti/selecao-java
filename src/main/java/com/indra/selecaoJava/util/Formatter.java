package com.indra.selecaoJava.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formatter {

    public static LocalDate toDate(String strData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(strData, formatter);
        return data;
    }
}