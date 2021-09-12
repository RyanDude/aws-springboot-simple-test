package com.example.authserver.Controllers;

import com.example.authserver.annotations.Log;
import com.example.authserver.entity.Preference;
import com.example.authserver.entity.User;
import com.example.authserver.reposiroty.PreferenceRepository;
import com.example.authserver.reposiroty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    PreferenceRepository preferenceRepository;
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/redis")
    public String test(){
        stringRedisTemplate.opsForValue().set("test-key","test-value");
        return stringRedisTemplate.opsForValue().get("test-key");
    }
    @RequestMapping("/annotation")
    @Log(value = "annotation simple test")
    public String annotations(){
        return "annotation test";
    }
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    @RequestMapping("/batchinsert")
    public String insert(){
        User user = userRepository.findByUsername("Guo");
        if(user!=null){
            List<Preference> P=new ArrayList<>();
            for(int i=0 ;i<50; i++){
                Preference preference = new Preference(user);
                String s= (char)('a'+i) + "prefer";
                preference.setName(s);
                P.add(preference);
            }
            preferenceRepository.saveAll(P);
        }
        return "insert data";
    }
    @RequestMapping("/pageable")
    public List<Preference> page_find(){
        // frontend can send 'page' and 'size'. this is just a sample
        Pageable pageable= PageRequest.of(0,10, Sort.by("id"));
        Page<Preference> page = preferenceRepository.findByUser_id(1,pageable);
        System.err.println(page.getTotalPages());
        List<Preference> ret = page.getContent();
        return ret;
    }
}
