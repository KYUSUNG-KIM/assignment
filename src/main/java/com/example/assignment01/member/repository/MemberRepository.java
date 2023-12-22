package com.example.assignment01.member.repository;

import com.example.assignment01.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByInviteToken(String inviteToken);

    Optional<Member> findByEmail(String email);
}
