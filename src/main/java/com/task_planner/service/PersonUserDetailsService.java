package com.task_planner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task_planner.model.Person;
import com.task_planner.repository.PersonRepository;


@Service
public class PersonUserDetailsService implements UserDetailsService{

	@Autowired
	private PersonRepository customerRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<Person> opt= customerRepository.findByEmail(username);

		if(opt.isPresent()) {
			
			Person person = opt.get();
			
			List<GrantedAuthority> authorities= new ArrayList<>();
			SimpleGrantedAuthority sga= new SimpleGrantedAuthority(person.getRole());
			authorities.add(sga);
			
			
			return new User(person.getEmail(), person.getPassword(), authorities);
			
			
			
			
		}else
			throw new BadCredentialsException("User Details not found with this username: "+username);
		
		
		
		
		
	}

}
