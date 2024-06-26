package com.easyfolio.esf.member.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SecurityMemberVO implements UserDetails {
    private final MemberVO memberVO;
    private boolean accountNonExpired;
    private boolean accountLocked;

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

    public void setAccountNonExpired(boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public void setAccountLocked(boolean accountLocked){
        this.accountLocked = accountLocked;
    }
}
