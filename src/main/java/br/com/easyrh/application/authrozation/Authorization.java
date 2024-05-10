package br.com.easyrh.application.authrozation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.easyrh.infrastructure.repository.personRepository.IPersonRepository;

@Service
public class Authorization implements UserDetailsService{

    @Autowired
    private IPersonRepository _repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return _repository.findByEmail(email);
    }

}
