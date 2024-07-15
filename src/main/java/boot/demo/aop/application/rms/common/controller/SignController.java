package boot.demo.aop.application.rms.common.controller;

import boot.demo.aop.application.rms.common.annotation.Loggable;
import boot.demo.aop.application.rms.common.model.dto.SignUpRequestDTO;
import boot.demo.aop.application.rms.common.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SignController {

    private final AuthenticationService authService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
        log.info("LOGIN ERROR MSG : {}", errorMessage);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }

        return "common/auth/login";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "common/auth/signUp";
    }

    @Loggable
    @PostMapping("/signUp")
    public String signUp(@Valid SignUpRequestDTO signUpRequestDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error("SIGN UP VALID ERROR: {}", error.getDefaultMessage()));
            model.addAttribute("errorMessage", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp";
        }
        try {
            authService.insertUserMst(signUpRequestDTO);
        } catch (RuntimeException e) {
            log.error("SIGN UP ERROR: {}", e.getMessage());
            bindingResult.rejectValue("signupFailed", e.getMessage());

            return "redirect:/signUp";
        }

        return "redirect:/";
    }
}
