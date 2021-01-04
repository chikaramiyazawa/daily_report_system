package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Opportunity;
import utils.DBUtil;

public class OpportunityValidator {
    public static List<String> validate(Opportunity o ,Boolean faceid_duplicate_check_flag){
        List<String>errors = new ArrayList<String>();

        String faceid_error = _validateFaceid(o.getFaceid(), faceid_duplicate_check_flag);
        if(!faceid_error.equals("")){
            errors.add(faceid_error);
        }



        String opportunity_error = _validateOpportunity(o.getOpportunity());
        if(!opportunity_error.equals("")){
            errors.add(opportunity_error);
        }


        String status_error = _validateStatus(o.getStatus());
        if(!status_error.equals("")){
            errors.add(status_error);
        }

        String location_error = _validateLocation(o.getLocation());
        if(!location_error.equals("")){
            errors.add(location_error);
        }
        return errors;
    }

    private static String _validateFaceid(String faceid , Boolean faceid_duplicate_check_flag){
        if(faceid == null || faceid.equals("")){
            return "商談Idを入力してください。";
        }
        if(faceid_duplicate_check_flag){
            EntityManager em = DBUtil.createEntityManager();
            long faceid_count = (long)em.createNamedQuery("checkRegisteredfaceid" , Long.class)
                                        .setParameter("faceid", faceid)
                                        .getSingleResult();
            em.close();
            if(faceid_count > 0){
                return "入力された商談Idはすでに存在しています";
            }
        }
            return "";

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
    private static String _validateLocation(String location){
        if(location == null || location.equals("")){
            return "現場は必ず書き記しましょう。";
        }

        return "";
    }


}


