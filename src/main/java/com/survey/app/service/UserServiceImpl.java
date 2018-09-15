package com.survey.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.app.dto.UserData;
import com.survey.app.model.RoleName;
import com.survey.app.model.User;
import com.survey.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public List<UserData> getAllUsers() {
		List<User> users=userRepository.findAll();
		return users.stream().filter(user -> user.getRoles().iterator().next().getName().equals(RoleName.ROLE_USER))
				 			 .map(user -> new UserData(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.isActive()?"Active":"InActive"))
				 			 .collect(Collectors.toList());
	}

}
