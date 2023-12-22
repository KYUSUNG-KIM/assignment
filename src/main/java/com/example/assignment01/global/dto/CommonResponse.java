package com.example.assignment01.global.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse {

    private String result = "SUCCESS";
    private int status = HttpStatus.OK.value();
    private Integer errorCode;
    private String message;
    private Object data;

    public CommonResponse(Object data) {
        setData(data);
    }

    public void setError(HttpStatus httpStatus, String message) {
        setResult("ERROR");
        setStatus(httpStatus.value());
        setMessage(message);
    }

}
