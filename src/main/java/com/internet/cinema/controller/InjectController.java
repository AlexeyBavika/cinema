package com.internet.cinema.controller;

import com.internet.cinema.model.Role;
import com.internet.cinema.model.RoleName;
import com.internet.cinema.model.User;
import com.internet.cinema.service.RoleService;
import com.internet.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InjectController(RoleService roleService,
                            UserService userService,
                            PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void injectData() {
        injectRoles();
        injectUsers();
    }

    private void injectRoles() {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        roleService.add(userRole);
    }

    private void injectUsers() {
        User adminUser = new User();
        adminUser.setEmail("admin@gmail.com");
        adminUser.setPassword(passwordEncoder.encode("1"));
        adminUser.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(adminUser);
        User user = new User();
        user.setEmail("second@gmail.com");
        user.setPassword(passwordEncoder.encode("2"));
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.add(user);
    }
}
