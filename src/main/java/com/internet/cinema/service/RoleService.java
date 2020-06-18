package com.internet.cinema.service;

import com.internet.cinema.model.Role;

public interface RoleService {

    Role add(Role role);

    Role getRoleByName(String roleName);
}
