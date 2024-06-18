package com.msg91.msg91.client.request.msg;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Request {
    private String template_id;
    private String shortUrl;
    private List<Recipient> recipients;
    private Map<String, String> headers;
}
