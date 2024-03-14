package com.easyfolio.esf.member.service;

import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.member.vo.SecurityMemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username){
        System.err.println(username);
        MemberVO memberVO = memberService.findMemberById(username);
        System.err.println(memberVO);

        if(memberVO == null){
            throw new UsernameNotFoundException(username);
        }

        UserDetails securityUser = new SecurityMemberVO(memberVO);

        return User.builder()
                .username(securityUser.getUsername())
                .password(securityUser.getPassword())
                .roles(securityUser.getAuthorities().toString())
                .build();
    }
}
