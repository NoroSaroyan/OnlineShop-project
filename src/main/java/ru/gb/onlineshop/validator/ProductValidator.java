package ru.gb.onlineshop.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.gb.onlineshop.entity.Product;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title","error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.not_empty");

        // Name must have from 2 characters to 32
        if (product.getTitle().length() <= 1) {
            errors.rejectValue("title", "product.error.name.less_2");
        }
        if (product.getTitle().length() > 64) {
            errors.rejectValue("title", "product.error.name.over_64");
        }
    }
}
