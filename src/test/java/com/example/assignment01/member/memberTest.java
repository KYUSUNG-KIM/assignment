package com.example.assignment01.member;

import com.example.assignment01.member.dto.MemberDto;
import com.example.assignment01.member.entity.Member;
import com.example.assignment01.member.repository.MemberRepository;
import com.example.assignment01.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class memberTest {

    @Autowired
    private MemberService memberService;


    @Test
    public void saveMember() {

        // given
        MemberDto memberDto = new MemberDto();
        String name = "테스터";
        String phoneNumber = "01000000000";
        String email = "test@test.com";

        memberDto.setName(name);
        memberDto.setPhoneNumber(phoneNumber);
        memberDto.setEmail(email);


        // when
        memberService.save(memberDto);


        // then
        Optional<Member> member = memberService.getByEmail(email);

        assertThat(member).isNotNull();
        assertThat(member.get().getName()).isEqualTo(name);
        assertThat(member.get().getEmail()).isEqualTo(email);
        assertThat(member.get().getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(member.get().getIsValidYn()).isEqualTo("N");
    }

}
