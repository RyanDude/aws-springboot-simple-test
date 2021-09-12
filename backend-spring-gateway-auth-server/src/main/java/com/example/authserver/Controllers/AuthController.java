package com.example.authserver.Controllers;

import com.example.authserver.entity.LoginUser;
import com.example.authserver.entity.Role;
import com.example.authserver.entity.User;
import com.example.authserver.reposiroty.RoleRepository;
import com.example.authserver.reposiroty.UserRepository;
import com.example.authserver.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    MailService mailService;
    @RequestMapping("/auth/register")
    public String register(@RequestBody LoginUser loginUser){
        User user = new User();
        user.setUsername(loginUser.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(loginUser.getPassword()));
        user.setEnabled(true);
        Role role=new Role(user);
        role.setRole("USER");

        userRepository.save(user);
        roleRepository.save(role);
        return "register successfully";
    }
    /**
     * TEST: send email to user
     * */
    @RequestMapping("/mail/code")
    public void sendEmail(@RequestParam(value = "to")String to){
        mailService.sendSimpleMail(to,"JAVA EMAIL TEST","JUST A TEST");
    }
}
