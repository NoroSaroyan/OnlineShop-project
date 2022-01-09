package ru.gb.onlineshop.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.gb.onlineshop.entity.User;
import ru.gb.service.UserService;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //Username and password can't me empty or contain whitespace
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.not_empty");

        // Username must have from 4 characters to 32
        if (user.getEmail().length() < 4) {
            errors.rejectValue("username", "register.error.username.less_4");
        }
        if(user.getEmail().length() > 32){
            errors.rejectValue("username","register.error.username.over_32");
        }
        //Username can't be duplicated
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("username", "register.error.duplicated.username");
        }
        //Email can't be duplicated
        if (userService.findByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "register.error.duplicated.email");
        }
        //Password must have at least 8 characters and max 32
        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "register.error.password.less_8");
        }
        if (user.getPassword().length() > 32){
            errors.rejectValue("password", "register.error.password.over_32");
        }
    }
}
