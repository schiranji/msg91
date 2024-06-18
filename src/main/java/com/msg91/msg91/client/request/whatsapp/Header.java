package com.msg91.msg91.client.request.whatsapp;

import lombok.Data;

@Data
public class Header {
    private String type = "component-type";
    private String value = "component-url-link";
}
