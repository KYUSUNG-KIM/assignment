package com.example.assignment01.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDto {

    @NotNull
    private String name;                // 이름

    @NotNull
    private String phoneNumber;         // 전화 번호

    @NotNull
    private String email;               // 이메일
}
