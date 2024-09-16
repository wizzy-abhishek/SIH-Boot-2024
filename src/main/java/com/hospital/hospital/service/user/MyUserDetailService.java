/*
package com.hospital.hospital.service.user;

import com.hospital.hospital.model.user.UserDb;
import com.hospital.hospital.model.user.UserPrincipal;
import com.hospital.hospital.repo.user.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailsRepo userRepo ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDb user = userRepo.findById(username).orElse(null);

        if(user == null){
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }

        return new UserPrincipal(user) ;

    }
}
*/
