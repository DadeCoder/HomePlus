package com.dade.security;

import com.dade.user.hunter.HunterUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dade on 2017/1/7.
 */
public interface HunterUserRepository extends CrudRepository<HunterUser, String> {

    HunterUser findByPhoneNumber(String phoneNumber);

}
