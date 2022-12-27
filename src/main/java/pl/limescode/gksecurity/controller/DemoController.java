package pl.limescode.gksecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.limescode.gksecurity.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/unsecured")
    public String unsecuredPage() {
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/content")
    public String contentPage() {
        return "content with write_privilege";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    public String daoTestPage(Principal principal){
        return null;
    }


}
