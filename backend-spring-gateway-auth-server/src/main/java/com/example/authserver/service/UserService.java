package com.example.authserver.service;

import com.example.authserver.entity.JwtUser;
import com.example.authserver.entity.Role;
import com.example.authserver.entity.User;
import com.example.authserver.reposiroty.RoleRepository;
import com.example.authserver.reposiroty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    //这里的User是用来验证CurrentUser的,详见源码
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user=userRepository.findByUsername(username);
        if(user!=null){
            List<Role> roles=roleRepository.findByUser_id(user.getId());
            if(roles!=null && !roles.isEmpty()){
                user.setRoles(roles);
            }
            return new JwtUser(user);
        }else{
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
