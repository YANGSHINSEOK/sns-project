package com.fastcampus.sns.service;



import com.fastcampus.sns.exception.ErrorCode;
import com.fastcampus.sns.exception.SnsApplicationException;
import com.fastcampus.sns.model.User;
import com.fastcampus.sns.model.entity.UserEntity;
import com.fastcampus.sns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserEntityRepository userEntityRepository;
    public User join(String userName, String password) {

        System.out.println("UserService name ="+ userName);
        System.out.println("UserService password ="+password);
        userEntityRepository.findByUserName(userName).ifPresent(it -> {
            throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("userName is %s", userName));
        });

        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userName, password));

        return User.fromEntity(userEntity);
    }

    public String login(String userName, String password) {

        //회원여부 체크
        UserEntity userEntity
                = userEntityRepository.findByUserName(userName).orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND));
        //비밀번호 체크
        if(!userEntity.getPassword().equals(password)) {
            throw new SnsApplicationException(ErrorCode.INVALID_PASSWORD);
        }
        //토큰 생성

        return "";
    }
}
