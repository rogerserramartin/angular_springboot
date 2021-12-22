package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

// we will use this response in the frontend
@Data
// https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
@SuperBuilder// builder pattern
// @SuperBuilder lets you automatically produce the code required to have your class be instantiable with code such as:
//Person.builder().name("Adam Savage").city("San Francisco").job("Mythbusters").job("Unchained Reaction").build();
@JsonInclude(NON_NULL) // if the response is successful, the developermessage will be null,
// so it will not be included in the json
public class Response {

    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?,?> data;

}
