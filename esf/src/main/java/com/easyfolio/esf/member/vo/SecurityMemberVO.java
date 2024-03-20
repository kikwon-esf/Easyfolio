package com.easyfolio.esf.member.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityMemberVO implements UserDetails {
    private final MemberVO memberVO;

    public SecurityMemberVO(MemberVO memberVO){
        this.memberVO = memberVO;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList();
        list.add(()->memberVO.getMemberRole());
        return list;
    }

    @Override
    public String getPassword() {
        return memberVO.getMemberPw();
    }

    @Override
    public String getUsername() {
        return memberVO.getMemberId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
