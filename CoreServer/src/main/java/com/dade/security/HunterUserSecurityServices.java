package com.dade.security;

import com.dade.commons.utils.LogUtil;
import com.dade.user.hunter.HunterUser;
import com.dade.user.hunter.HunterUserDao;
import com.dade.user.hunter.HunterUserService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dade on 2017/1/7.
 */
public class HunterUserSecurityServices implements UserDetailsService {

    private final HunterUserRepository hunterUserRepository;

    public HunterUserSecurityServices(HunterUserRepository hunterUserRepository){
        this.hunterUserRepository = hunterUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        LogUtil.info(phoneNumber);

        HunterUser hunterUser = hunterUserRepository.findByPhoneNumber(phoneNumber);

        LogUtil.info(hunterUser.toString());

        if (hunterUser != null){
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("HUNTER_USER"));
            return new User(hunterUser.getPhoneNumber(), hunterUser.getPassword(), authorities);
        }

        throw new UsernameNotFoundException("User " + phoneNumber + "Not Found!");

    }

}
