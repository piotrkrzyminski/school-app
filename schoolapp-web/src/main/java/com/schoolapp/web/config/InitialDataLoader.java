package com.schoolapp.web.config;

import com.schoolapp.core.repository.user.UserRepository;
import com.schoolapp.model.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@ComponentScan(basePackages = {"com.schoolapp"})
@EnableJpaRepositories(basePackages = "com.schoolapp.core.repository.user")
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(!alreadySetup) {

            UserModel adminUser = new UserModel();
            adminUser.setFirstName("Tom");
            adminUser.setLastName("Smith");
            adminUser.setEmail("tom.smith@test.com");
            adminUser.setEnabled(true);
            adminUser.setPassword(passwordEncoder.encode("qwerty"));

            userRepository.save(adminUser);

            alreadySetup = true;
        }
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
