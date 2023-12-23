package com.example.assignment01.member.controller;

import com.example.assignment01.global.dto.CommonResponse;
import com.example.assignment01.member.dto.InviteUrlDto;
import com.example.assignment01.member.dto.MemberDto;
import com.example.assignment01.member.entity.Member;
import com.example.assignment01.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    // 멤버 초대
    @PostMapping("/member/invite")
    public CommonResponse inviteMember(@RequestBody @Valid MemberDto dto) {

        try {
            Member member = memberService.save(dto);

            String inviteUrl = "/member/invite/" + member.getInviteToken();

            InviteUrlDto result = new InviteUrlDto();
            result.setUrl(inviteUrl);

            return new CommonResponse(result);
        }
        catch (Exception e) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

            log.error("/member/invite error occur", e);

            return commonResponse;
        }
    }


    // 초대 링크 접속 -> 임시 회원 활성화
    @PostMapping("/member/invite/{inviteToken}")
    public CommonResponse activeMember(@PathVariable(value="inviteToken") String inviteToken) {

        System.out.println(inviteToken);

        try {
            memberService.activeMember(inviteToken);

            return new CommonResponse("ok");
        }
        catch (IllegalArgumentException ise) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setError(HttpStatus.BAD_REQUEST, ise.getMessage());

            return commonResponse;
        }
        catch (Exception e) {
            CommonResponse commonResponse = new CommonResponse();
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

            log.error("/member/invite/{inviteToken} error occur", e);

            return commonResponse;
        }
    }

}
