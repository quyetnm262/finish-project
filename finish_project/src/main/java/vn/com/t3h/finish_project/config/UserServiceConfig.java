package vn.com.t3h.finish_project.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vn.com.t3h.finish_project.entity.UserEntity;
import vn.com.t3h.finish_project.repository.UserRepository;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class UserServiceConfig implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Could not find username");
        }
        return new UserSecurityDetails(user);

    }
}
