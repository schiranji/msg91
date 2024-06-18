package com.msg91.msg91.client.request.whatsapp;

import lombok.Data;

import java.util.List;

@Data
public class ToAndComponent {
    private List<String> to;
    private Components components;
}
