package com.hrdepartment.repo;

import com.hrdepartment.model.Users;
import com.hrdepartment.model.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    List<Users> findAllByOrderByRole();

    List<Users> findAllByRoleAndTertiary_MaritalAndTertiary_OriginAndTertiary_CitizenshipAndTertiary_RetireeAndTertiary_ConscriptedAndTertiary_Disability(Role role, Marital marital, Origin origin, Citizenship citizenship, YesNo retiree, YesNo conscripted, Disability disability);
    List<Users> findAllByRole(Role role);
    List<Users> findAllByRoleAndTertiary_Marital(Role role, Marital marital);
    List<Users> findAllByRoleAndTertiary_Origin(Role role, Origin origin);
    List<Users> findAllByRoleAndTertiary_Citizenship(Role role, Citizenship citizenship);
    List<Users> findAllByRoleAndTertiary_Retiree(Role role, YesNo yesNo);
    List<Users> findAllByRoleAndTertiary_Conscripted(Role role, YesNo yesNo);
    List<Users> findAllByRoleAndTertiary_Disability(Role role, Disability disability);
}
