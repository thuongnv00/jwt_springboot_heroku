package com.example.springbootjwtmaven.security.userprincal;

import com.example.springbootjwtmaven.model.User;
import com.example.springbootjwtmaven.repository.IUserRepository;
import com.example.springbootjwtmaven.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Security;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    UserServiceImpl userService;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found -> Username or password " +username));
        return UserPrinciple.build(user);
    }
    // Lay user hien tai de thao tac voi db
    public User getCurrentUser() {
        Optional<User> user;
        String username;
        //lay 1 object principal trong securitycontextholder
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // so sanh obj voi userdetails
        if (principal instanceof UserDetails ) {
            username = ((UserDetails) principal).getUsername();
        } else {
            //neu khong phai user hien tai thi
            username = principal.toString();
        }
        // ktra neu username ton tai trong db thi gan user = ham tim kiem trong db theo username
        if(userRepository.existsByUsername(username)) {
            user = userService.findByUsername(username);
        }
        else {
            // neu chua ton tai trong db thi tra ve 1 the hien cua lop user qua optional.of
            user = Optional.of(new User());
            // set cho no 1 cai ten user an danh. day la 1 truong hop tuong tac giong nhu dang nhap bang fb hay gg
            user.get().setUsername("Anonymous");

        }
        return user.get();
    }
}
