package com.stepintoprofession.registration_service.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return list != null ? String.join(SPLIT_CHAR, list) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String str) {
        return str != null ? Arrays.asList(str.split(SPLIT_CHAR)) : Collections.emptyList();
    }
}
