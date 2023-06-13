package de.blog.config;

import de.blog.data.tables.daos.UserEntityDao;
import de.blog.data.tables.pojos.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthenticationController {

  private final UserEntityDao userEntityDao;
  private final DaoAuthenticationProvider daoAuthenticationProvider;

  public AuthenticationController(final UserEntityDao userEntityDao, final DaoAuthenticationProvider daoAuthenticationProvider) {
    this.userEntityDao = userEntityDao;
      this.daoAuthenticationProvider = daoAuthenticationProvider;
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String authenticate(
      HttpServletRequest request,
      HttpServletResponse response,
      RedirectAttributes redirectAttributes) {

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    Optional<UserEntity> userEntityOptional = userEntityDao.fetchOptionalByUsername(username);

    if (userEntityOptional.isEmpty()) {
      redirectAttributes.addAttribute("error", "Invalid username and password");
      return "redirect:/login";
    }

    UsernamePasswordAuthenticationToken authenticationToken =
        UsernamePasswordAuthenticationToken.unauthenticated(
            username, userEntityOptional.get().getSalt() + password);

      Authentication authentication = daoAuthenticationProvider.authenticate(authenticationToken);
      SecurityContext context = SecurityContextHolder.createEmptyContext();

      context.setAuthentication(authentication);
      new HttpSessionSecurityContextRepository().saveContext(context, request, response);

      redirectAttributes.addAttribute("success", "You have been logged in successfully");
      return "redirect:/";
  }
}
