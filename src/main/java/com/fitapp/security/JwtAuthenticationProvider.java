//package com.fitapp.security;
//
//
//import com.fitapp.model.AppUser;
//import com.fitapp.model.JwtAuthenticationToken;
//import com.fitapp.model.JwtUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
//
//    @Autowired
//    private JwtValidator validator;
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
//
//    }
//
//    @Override
//    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
//
//        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
//        String token = jwtAuthenticationToken.getToken();
//
//        AppUser jwtUser = validator.validate(token);
//
//        if (jwtUser == null) {
//            throw new RuntimeException("JWT Token is incorrect");
//        }
//
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList(jwtUser.getRole());
//        return new JwtUserDetails(jwtUser.getName(), jwtUser.getPassword(), jwtUser.getId(),
//                token,
//                grantedAuthorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
//    }
//}