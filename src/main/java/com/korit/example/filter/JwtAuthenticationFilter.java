package com.korit.example.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.korit.example.entity.UserEntity;
import com.korit.example.provider.JwtProvider;
import com.korit.example.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// filter:
// - 서버 로직(톰캣의 로직)과 서블릿(컨트롤러부터의 내용) 사이에서 Http Request에 대한 사전 처리를 수행하는 영역
// - filter에서 걸러진 Request는 서블릿까지 도달하지 못하고 중간에서 reject된다.

@Component
@RequiredArgsConstructor
// OncePerRequestFilter 추상클래스를 확장 구현하여 filter 클래스로 생성 가능하다.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    
    try {

      // 1. request 객체에서 token 가져오기
      String token = getToken(request);
      
      // null 도 가질수 있기에 거르기
      if(token == null) {
        filterChain.doFilter(request, response);
        return;
      }

      // 2. token 검증
      String subject = jwtProvider.validate(token);
      if(subject == null){
        filterChain.doFilter(request, response);
        return;
      }

      // 3. 데이터베이스에 존재하는 유저인지 확인(선택 사항)
      UserEntity userEntity = userRepository.findByUserId(subject);
      if (userEntity == null){
        filterChain.doFilter(request, response);
        return;
      }

      // 4. 접근주체의 권한(리스트) 지정
      List<GrantedAuthority> roles = AuthorityUtils.NO_AUTHORITIES;
    //   String role = userEntity.getRole();
    //   List<GrantedAuthority> roles = new ArrayList<>();
    //   roles.add(new SimpleGrantedAuthority(role));

      // 5. principle 에 대한 정보를 context에 저장
      // 5-1. principle에 대한 정보를 인증된 사용자 객체로 생성
      // UsernamePasswordAuthenticationToken(사용자정보, 비밀번호, 권한);
      AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(subject, null, roles);  

      // 5-2. 인증 정보에 request 등록
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      // 5-3. 등록할 빈 security contest 생성
      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

      // 5-4. 생성한 security context에 
      securityContext.setAuthentication(authenticationToken);

      // 5-5. 생성ㅇ한 security context를 등록
      SecurityContextHolder.setContext(securityContext);

    } catch(Exception exception) {
      exception.printStackTrace();

    }

    // 6. 다음 필터에 request와 response 객체를 전달
    filterChain.doFilter(request, response);

  }
  
  // 1. request 객체 header에서 'Authoriztion' 필드의 값을 가져온다.
  // 2. Authorization 필드 값이 'Bearer ' 로 시작하는지 확인
  // 3. Authorization 필드 값에서 토큰 추출
  private String getToken(HttpServletRequest request) {

    // 1. request 객체 header에서 'Authoriztion' 필드의 값을 가져온다.
    String authorization = request.getHeader("Authorization");

    // Authorization 필드값이 존재하는지 확인
    // null 이나 "" 처럼 빈문자열이거나 공백으로 이루어진 문자열인지 확인
    boolean hasAuthorization = StringUtils.hasText(authorization);
    if(!hasAuthorization) return null;

    // 2. Authorization 필드 값이 'Bearer ' 로 시작하는지 확인
    boolean isBearer = authorization.startsWith("Bearer ");
    if(!isBearer) return null;

    // 3. Authorization 필드 값에서 토큰 추출
    // Bearer eyJhbGciOiJIUzI1NiJ9.ey...
    // Bearer 을 제외한 토큰 값만 추출
    String token = authorization.substring(7);
    return token;
  }

}