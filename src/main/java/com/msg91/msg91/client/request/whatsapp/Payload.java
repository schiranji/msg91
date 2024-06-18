package com.msg91.msg91.client.request.whatsapp;

import lombok.Data;

@Data
public class Payload{
    private String type="template";
    private Template template;
    private String messaging_product="whatsapp";
}