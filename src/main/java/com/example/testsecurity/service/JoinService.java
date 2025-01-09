package com.example.testsecurity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public void joinProcess(JoinDTO joinDTO) {
		// db에 이미 동일한 username을 가진 회원이 존재하는지?
		boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
		if (isUser) {
			return;
		}

		UserEntity data = new UserEntity();

		data.setUsername(joinDTO.getUsername());
		data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
		data.setRole("ROLE_ADMIN");

		userRepository.save(data);
	}
}
