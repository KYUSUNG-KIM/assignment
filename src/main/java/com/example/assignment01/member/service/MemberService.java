package com.example.assignment01.member.service;

import com.example.assignment01.member.dto.MemberDto;
import com.example.assignment01.member.entity.Member;
import com.example.assignment01.member.repository.MemberRepository;
import com.example.assignment01.member.util.RandomTokenUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;



    /**
     * 임시 회원 생성 및 초대 토큰 생성
     * - 토큰 만료일 : 임시 회원 생성 후 3분
     *
     * @param dto
     */
    @Transactional
    public Member save(MemberDto dto) {

        Member member = modelMapper.map(dto, Member.class);

        // 초대 토큰 생성
        String inviteToken = RandomTokenUtil.generateRandomToken();

        // 토큰 만료 일시 설정 -> 3분 후에 만료로 설정
        LocalDateTime tokenExpireDate = LocalDateTime.now().plusMinutes(3);

        member.setInviteToken(inviteToken);
        member.setTokenExpireDate(tokenExpireDate);

        return memberRepository.save(member);
    }


    /**
     * 멤버 활성화
     *
     * @param inviteToken 초대 토큰
     */
    @Transactional
    public void activeMember(String inviteToken) {
        Member member = memberRepository.findByInviteToken(inviteToken).orElseThrow();

        LocalDateTime tokenExpireDate = member.getTokenExpireDate();    // 초대 토큰 만료 일시
        String isValidYn = member.getIsValidYn();                       // 멤버 활성화 여부

        boolean isExpireToken = tokenExpireDate.isAfter(LocalDateTime.now());   // 토큰 만료 여부
        boolean isAlreadyValidMember = StringUtils.equals("Y", isValidYn);      // 이미 활성화된 멤버

        if (isExpireToken && isAlreadyValidMember) {

            // 토큰이 만료됐거나 이미 활성화된 멤버인 경우
            throw new IllegalArgumentException("Token is expired");
        }
        else {
            member.setIsValidYn("Y");
            memberRepository.save(member);
        }
    }


    @Transactional(readOnly = true)
    public Optional<Member> getByEmail(String email) {

        return memberRepository.findByEmail(email);
    }

}
