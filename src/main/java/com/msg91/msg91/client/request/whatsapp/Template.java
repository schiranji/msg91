package com.msg91.msg91.client.request.whatsapp;

import lombok.Data;

import java.util.List;

@Data
public class Template {
    private String name;
    private Language language;
    private List<ToAndComponent> to_and_components;
}