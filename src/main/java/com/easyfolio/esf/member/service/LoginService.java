package com.easyfolio.esf.member.service;

import com.easyfolio.esf.member.vo.MemberVO;
import com.easyfolio.esf.member.vo.SecurityMemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberService memberService;
    public static Map<String,Integer> count;
    @Override
    public UserDetails loadUserByUsername(String username){

        MemberVO memberVO = memberService.findMemberById(username);
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
