package mk.ukim.finki.wpproekt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class IngredientNotFoundException extends RuntimeException{

        public IngredientNotFoundException(Long id) {
            super(String.format("Ingredient with id: %d was not found", id));
        }
    }

