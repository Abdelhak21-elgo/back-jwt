package com.learnjwt.jwt_learn.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnjwt.jwt_learn.Dao.RoleDao;
import com.learnjwt.jwt_learn.Dao.UserDao;
import com.learnjwt.jwt_learn.Entity.Role;
import com.learnjwt.jwt_learn.Entity.User;

@Service
public class UserService {

    @Autowired
    private UserDao userdao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        user.setVerificationToken(generateVerificationToken());
        User savedao = userdao.save(user);
        sendVerificationEmail(user.getUserEmail(), user.getVerificationToken());
        if (user.getEnabled()) {
            return savedao;
        } else {
            return null;
        }
    }

    // verification Token for Email
    private String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }

    // send Email to the new users
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    private void sendVerificationEmail(String email, String token) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("andelhakabdelhak200@gmail.com");
        msg.setTo(email);
        msg.setSubject("Account Verification");
        msg.setText("\nPlease click the link below to verify your account:"
                + "\n http://localhost:8080/verify?token="+token);
        javaMailSender.send(msg);
    }

    public void initRoleandUser() {

        // Admin Role
        Role adminRole = new Role();
        adminRole.setRolename("Admin");
        adminRole.setRoleDescription("Admin fro administration");
        roleDao.save(adminRole);

        // User Role
        Role userRole = new Role();
        userRole.setRolename("User");
        userRole.setRoleDescription("default role for new User");
        roleDao.save(userRole);

        // admin registration
        User adminUser = new User();
        adminUser.setUsrFirstName("Admin");
        adminUser.setUserLastName("Admin");
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserEmail("admin@admin.com");
        adminUser.setEnable(true);
        adminUser.setVerificationToken(generateVerificationToken());
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userdao.save(adminUser);

        // user regestration
        User user = new User();
        user.setUsrFirstName("User");
        user.setUserLastName("User");
        user.setUserName("user123");
        user.setUserPassword(getEncodedPassword("user@pass"));
        user.setUserEmail("user@user.com");
        user.setEnable(true);
        user.setVerificationToken(generateVerificationToken());
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userdao.save(user);

    }

    public String getEncodedPassword(String Password) {
        return passwordEncoder.encode(Password);
    }
}
