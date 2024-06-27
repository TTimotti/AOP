package boot.demo.aop.controller;

import boot.demo.aop.aop.annotation.Loggable;
import boot.demo.aop.model.dto.SignUpRequestDTO;
import boot.demo.aop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/sign")
@RequiredArgsConstructor
public class SignController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
        if (errorMessage != null) {
            log.error("LOGIN ERROR: {}", errorMessage);
            model.addAttribute("errorMessage", errorMessage);
        }

        return "sign/login";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "sign/signUp";
    }

    @Loggable
    @PostMapping("/signUp")
    public String signUp(@Valid SignUpRequestDTO signUpRequestDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error("SIGN UP VALID ERROR: {}", error.getDefaultMessage()));
            model.addAttribute("errorMessage", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "sign/signUp";
        }
        try {
            userService.insertUserMst(signUpRequestDTO);
        } catch (RuntimeException e) {
            log.error("SIGN UP ERROR: {}", e.getMessage());
            bindingResult.rejectValue("signupFailed", e.getMessage());

            return "sign/signUp";
        }

        return "redirect:/";
    }
}
