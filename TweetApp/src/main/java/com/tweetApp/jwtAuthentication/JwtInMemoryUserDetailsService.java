package com.tweetApp.jwtAuthentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
	  PasswordEncoder encoder = new BCryptPasswordEncoder();
	  String pwd = encoder.encode("dummy");
	  //$2a$10$iYhom1toje9/c9f97LT04uoPAnMR1JKLrKtpU7RiFMiI53DRorlta
    inMemoryUserList.add(new JwtUserDetails(1L, "tweetUser","$2a$10$iYhom1toje9/c9f97LT04uoPAnMR1JKLrKtpU7RiFMiI53DRorlta", "ROLE_USER_2"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


