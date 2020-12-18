package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Opportunity;

public class OpportunityValidator {
    public static List<String> validate(Opportunity o){
        List<String>errors = new ArrayList<String>();

        String opportunity_error = _validateOpportunity(o.getOpportunity());
        if(!opportunity_error.equals("")){
            errors.add(opportunity_error);
        }

        String status_error = _validateStatus(o.getStatus());
        if(!status_error.equals("")){
            errors.add(status_error);
        }
        return errors;
    }

    private static String _validateOpportunity(String opportunity){
        if(opportunity == null || opportunity.equals("")){
            return "商談を入力してください。";
        }
        return "";
    }

    private static String _validateStatus(String status){
        if(status == null || status.equals("")){
            return "商談状況を入力してください。";
        }

        return "";
    }

}


