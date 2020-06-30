package com.internet.cinema.dao;

import com.internet.cinema.model.Role;

public interface RoleDao {

    Role add(Role role);

    Role getRoleByName(String roleName);
}
