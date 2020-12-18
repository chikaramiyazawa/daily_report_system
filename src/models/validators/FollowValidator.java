package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Follow;


public class FollowValidator {
    public static List<String> validate(Follow f){
        List<String>errors = new ArrayList<String>();

        String review_error = _validateReview(f.getReview());
        if(!review_error.equals("")){
            errors.add(review_error);
        }
        return errors;
    }

    private static String _validateReview(String review){
        if(review == null || review.equals("")){
            return "評価の欄が空白です。";
        }
        return "";
    }



}

