package com.test.develop.common.handler;

import com.test.develop.common.domain.UserDetailsServiceImpl;
import com.test.develop.common.domain.UserDetailsVO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "userDetailsService")
    UserDetailsServiceImpl userDetailService;

    @NonNull
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("CustomAuthenticationProvider.authenticate");
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String username = token.getName();
        String password = (String) token.getCredentials();

        if (ObjectUtils.isEmpty(password)) {
            password = "";
        }

        UserDetailsVO userDetailsVO = (UserDetailsVO) userDetailService.loadUserByUsername(username);

        System.out.println("======================================================");
        System.out.println(userDetailsVO.toString());
        System.out.println("======================================================");

        if (!bCryptPasswordEncoder.matches(password, String.valueOf(userDetailsVO.getPassword()))) {
            throw new BadCredentialsException(userDetailsVO.getUsername() + "Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetailsVO
                , password
                , userDetailsVO.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        log.info("CustomAuthenticationProvider.supports");
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
