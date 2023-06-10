package de.blog.config.security;

import de.blog.data.tables.daos.UserEntityDao;
import de.blog.data.tables.pojos.Role;
import de.blog.data.tables.pojos.UserEntity;
import de.blog.user.UserFinder;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityUserService implements UserDetailsService {

    private final UserEntityDao userEntityDao;
    private final UserFinder userFinder;


    public SecurityUserService(final UserEntityDao userEntityDao, final UserFinder userFinder) {
        this.userEntityDao = userEntityDao;
        this.userFinder = userFinder;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Optional<UserEntity> userEntityOptional = userEntityDao.fetchOptionalByUsername(username);

        if (userEntityOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        UserEntity userEntity = userEntityOptional.get();
        List<Role> roles = userFinder.findRolesByUserId(userEntity.getId());

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(roles.stream().map(Role::getName).toArray(String[]::new))
                .build();
    }
}
