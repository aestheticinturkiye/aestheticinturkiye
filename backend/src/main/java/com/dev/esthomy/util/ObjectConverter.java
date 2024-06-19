package com.dev.esthomy.util;

import com.dev.esthomy.dto.request.CreateFindPartnerRequest;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectConverter implements Converter<String, CreateFindPartnerRequest> {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public CreateFindPartnerRequest convert(final String source) {
        return objectMapper.readValue(source, CreateFindPartnerRequest.class);
    }

    @Override
    public JavaType getInputType(final TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(final TypeFactory typeFactory) {
        return null;
    }
}
