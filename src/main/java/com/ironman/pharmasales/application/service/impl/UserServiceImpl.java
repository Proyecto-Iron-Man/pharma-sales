package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.dto.user.AuthDto;
import com.ironman.pharmasales.application.dto.user.UserCreateDto;
import com.ironman.pharmasales.application.dto.user.UserDto;
import com.ironman.pharmasales.application.dto.user.UserSecurityDto;
import com.ironman.pharmasales.application.dto.user.mapper.UserMapper;
import com.ironman.pharmasales.application.service.UserService;
import com.ironman.pharmasales.persistence.entity.UserEntity;
import com.ironman.pharmasales.persistence.repository.UserRepository;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.security.JwtHelper;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    @Override
    public UserDto create(UserCreateDto userCreateDto) throws DataNotFoundException {
        UserEntity userSave = userMapper.toUserEntity(userCreateDto);

        userSave.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        userSave.setState(State.ACTIVE.getValue());
        userSave.setCreatedAt(LocalDateTime.now());

        UserEntity user = userRepository.save(userSave);

        return userMapper.toUserDto(user);
    }

    @Override
    public UserSecurityDto login(AuthDto authDto) throws DataNotFoundException {
        UserEntity user = userRepository.findByEmail(authDto.getEmail())
                .orElseThrow(()-> new DataNotFoundException("Usuario " + authDto.getEmail() + " no se encuenta en el registro"));

        if(!passwordEncoder.matches(authDto.getPassword(), user.getPassword()))
            throw new DataNotFoundException("Usuario/Password no son correctos");

        if(user.getState().equalsIgnoreCase(State.DISABLE.getValue()))
            throw new DataNotFoundException("Usuario esta deshabilitado. Comuniquese con el administrador del sistema");

        UserSecurityDto userSecurity = userMapper.toUserSecurityDto(user);

//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getProfileId().toString());
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("user");
        UserDetails userDetails = new User(user.getEmail(), user.getPassword(), List.of(grantedAuthority));

        userSecurity.setSecurity(jwtHelper.getSecurity(userDetails));

        return userSecurity;
    }
}
