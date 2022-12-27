package pl.limescode.gksecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.limescode.gksecurity.entity.Privilege;
import pl.limescode.gksecurity.entity.Role;
import pl.limescode.gksecurity.entity.User;
import pl.limescode.gksecurity.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Optional<User> findByUserName(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        Collection<? extends GrantedAuthority> roles = mapRolesToAuthorities(user.getRoles());
        Collection<? extends GrantedAuthority> privileges = mapPrivilegesToAuthorities(user.getPrivileges());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.size() + privileges.size());
        authorities.addAll(roles);
        authorities.addAll(privileges);
        var userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.unmodifiableList(authorities));
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    private Collection<? extends GrantedAuthority> mapPrivilegesToAuthorities(Collection<Privilege> privileges) {
        return privileges.stream().map(privilege -> new SimpleGrantedAuthority((privilege.getName()))).collect(Collectors.toList());
    }
}
