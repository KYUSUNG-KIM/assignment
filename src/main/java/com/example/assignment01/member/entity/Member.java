package com.example.assignment01.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @Column(nullable = false)
    private String name;                // 이름

    @Column(nullable = false)
    private String phoneNumber;         // 전화 번호

    @Column(nullable = false, unique = true)
    private String email;               // 이메일

    @Column(nullable = false, unique = true)
    private String inviteToken;         // 초대 토큰

    @NotNull
    private LocalDateTime tokenExpireDate;      // 토크 만료일

    @NotNull
    @ColumnDefault("'N'")
    @Column(length = 1)
    private String isValidYn = "N";             // 멤버 활성화 여부
}
