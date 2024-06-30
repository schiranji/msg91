package com.msg91.msg91.client;

import com.msg91.msg91.client.request.msg.Recipient;
import com.msg91.msg91.client.request.msg.Request;
import com.msg91.msg91.client.request.whatsapp.Msg91WhatsappRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class Msg91MesssageClient {
    private static final String URL = "https://control.msg91.com/api/v5/flow/";
    private static final String TEMPLATE_ID1 = "663f42eed6fc0537e05aa472";
    public static final String WHATSAPP_URL_WITH_TEMPLATE = "https://api.msg91.com/api/v5/whatsapp/whatsapp-outbound-message/bulk/";
    private static final String AUTH_KEY = "<Auth Key Here>";
    private static final String INTEGRATED_NUMBER = "<Integrated Phone Number here>";
    private static final String TO_PHONE_NUMBER = "<Send Whatsapp message to this phone>";
    private static final String WHATSAPP_TEMPLATE = "<Whatsapp registered template here>";

    public static void main(String[] args) throws MalformedURLException {
        Msg91MesssageClient msg99 = new Msg91MesssageClient();
        /*Map<String, String> params = new HashMap<>();
        params.put("var", "VAR1");
        params.put("var1", "VAR2");
        Response res = msg99.sendMessage(, TEMPLATE_ID1, params);
        System.out.println("Response " + res);*/

        List<String> paramList = new ArrayList<>();
        paramList.add("A1");
        paramList.add("B2");
        paramList.add("C3");
        paramList.add("D4");
        paramList.add("E5");
        paramList.add("F6");
        paramList.add("G7");
        paramList.add("H8");
        paramList.add("I9");
        paramList.add("J11");
        final StringBuilder sb = new StringBuilder();
        Map map = msg99.sendWhatsappMessageUsingTemplate(TO_PHONE_NUMBER, WHATSAPP_TEMPLATE, paramList);
        map.keySet().forEach(key -> sb.append(key+" : "+ map.get(key) + "\n "));
        System.out.println(sb.toString());
    }

    public Response sendMessage(String phoneNumber, String template, Map<String, String> params) {
        Request request = new Request();
        Recipient recipient = new Recipient();
        List<Recipient> recipients = new ArrayList<>();
        recipient.setMobiles(phoneNumber);
        recipient.setVar(params.get("var"));
        recipient.setVar1(params.get("var1"));
        recipients.add(recipient);
        request.setTemplate_id(template);
        request.setRecipients(recipients);
        request.setShortUrl("0");
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<Request> entity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Response> response = restTemplate.postForEntity(URL, entity, Response.class);
        return response.getBody();
    }
    
    public Map sendWhatsappMessageUsingTemplate(String phoneNumber, String template, List<String> paramsList) throws JsonProcessingException {
        System.out.println("Whatsapp Template " + template);
        System.out.println("URL " + WHATSAPP_URL_WITH_TEMPLATE);
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumber);
        Msg91WhatsappRequest request = new Msg91WhatsappRequest(INTEGRATED_NUMBER, template, phoneNumbers, paramsList);
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(getMapper().writeValueAsString(request), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(WHATSAPP_URL_WITH_TEMPLATE, entity, Map.class);
        return response.getBody();
    }

    private static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
    

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("authkey", AUTH_KEY);
        headers.set("accept", "application/json");
        headers.set("content-type", "application/json");
        return headers;
    }


}
