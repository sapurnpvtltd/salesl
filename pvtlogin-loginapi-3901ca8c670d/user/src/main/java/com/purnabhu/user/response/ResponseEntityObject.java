package com.purnabhu.user.response;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntityObject {
    private int responseCode;
    private String responseMessage;
    private Map<String,Object> dataMap;

}
