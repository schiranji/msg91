package com.msg91.msg91.client.request.whatsapp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Msg91WhatsappRequest {
    private String integrated_number;
    private String content_type="template";
    private Payload payload;

    public Msg91WhatsappRequest(String integratedNumber, String templateName, List<String> to, List<String> paramValues) {
        this.integrated_number = integratedNumber;
        Payload payload = new Payload();
        Template template = new Template();
        List<ToAndComponent> toAndComponents = new ArrayList<>();
        ToAndComponent toAndComponent = new ToAndComponent();
        toAndComponent.setTo(to);
        Components components = new Components();
        int i = 1;
        for(String paramValue: paramValues) {
            Body body = new Body();
            body.setValue(paramValue);
            body.setType("text");
            switch (i) {
                case 1: components.setBody_1(body);break;
                case 2: components.setBody_2(body);break;
                case 3: components.setBody_3(body);break;
                case 4: components.setBody_4(body);break;
                case 5: components.setBody_5(body);break;
                case 6: components.setBody_6(body);break;
                case 7: components.setBody_7(body);break;
                case 8: components.setBody_8(body);break;
                case 9: components.setBody_9(body);break;
                case 10: components.setBody_10(body);break;
                case 11: components.setBody_11(body);break;
                default: throw new RuntimeException("All variables are not set to send whatsapp message.");
            }
            i++;
        }
        toAndComponents.add(toAndComponent);
        toAndComponent.setComponents(components);
        template.setName(templateName);
        template.setTo_and_components(toAndComponents);
        template.setLanguage(new Language());
        payload.setTemplate(template);
        this.setPayload(payload);
    }
}
