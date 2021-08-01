package com.test.develop.common.handler;

import com.test.develop.common.domain.AuthConstants;
import com.test.develop.common.domain.UserDetailsVO;
import com.test.develop.common.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request
            , HttpServletResponse response
            , Authentication authentication) throws IOException {
        UserDetailsVO userDetailsVO = (UserDetailsVO) authentication.getPrincipal();
        String token = TokenUtils.generateJwtToken(userDetailsVO);
        response.addHeader(
                AuthConstants.AUTH_HEADER
                , AuthConstants.TOKEN_TYPE + " " + token);
        response.addHeader("name", userDetailsVO.getNickname());
    }
}
