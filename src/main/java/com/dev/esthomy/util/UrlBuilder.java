package com.dev.esthomy.util;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UrlBuilder {

    @Value("${s3.prefix}")
    private String preFix;

    public String toUrl(String key) {
        return preFix + key;

    }
    public List<String> returnList(List<S3ObjectSummary> objectSummaryList) {
       return objectSummaryList.stream().map(pr -> toUrl(pr.getKey())).toList();
    }
}
