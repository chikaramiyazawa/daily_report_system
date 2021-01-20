package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Opportunity;
import utils.DBUtil;

public class OpportunityValidator {
    public static List<String> validate(Opportunity o ,Boolean Op_id_duplicate_check_flag){
        List<String>errors = new ArrayList<String>();

        String faceid_error = _validateOp_id(o.getOp_id(), Op_id_duplicate_check_flag);
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

    private static String _validateOp_id(String op_id , Boolean faceid_duplicate_check_flag){
        if(op_id == null || op_id.equals("")){
            return "商談Idを入力してください。";
        }
        if(faceid_duplicate_check_flag){
            EntityManager em = DBUtil.createEntityManager();
            long opid_count = (long)em.createNamedQuery("getOp_idCount" , Long.class)
                                        .setParameter("op_id", op_id)
                                        .getSingleResult();
            em.close();
            if(opid_count > 0){
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


