package br.com.easyrh.application.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.easyrh.infrastructure.repository.userRepository.IUserRepository;

@Service
public class Authorization implements UserDetailsService {
  @Autowired
  private IUserRepository _repository;

  @Override
  public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException
  {
    return _repository.findByEmail(userEmail);
  }
}
