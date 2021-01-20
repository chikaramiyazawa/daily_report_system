package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Client;
import utils.DBUtil;

public class ClientValidator {


    public static List<String>validate(Client c ,Boolean code_duplicate_check_flag ){
        List<String>errors = new ArrayList<String>();

        String code_error = _validateCode(c.getCompanycode(), code_duplicate_check_flag);
        if(!code_error.equals("")){
            errors.add(code_error);
        }

        String name_error = _validateName(c.getCompanyname());
        if(!name_error.equals("")){
            errors.add(name_error);
         }

    return errors;

}
private static String _validateCode(String companycode, Boolean code_duplicate_check_flag){
    if(companycode == null || companycode.equals("")){
        return "会社Codeを入力してください。";
    }

    if(code_duplicate_check_flag){
        EntityManager em = DBUtil.createEntityManager();
        long client_count = (long)em.createNamedQuery("checkCompanyCode" , Long.class)
                                    .setParameter("companycode", companycode)
                                    .getSingleResult();
        em.close();
        if(client_count > 0){
            return "入力された取引先の情報はすでに存在しています。";
        }
    }
    return "";
 }
private static String _validateName(String companyname){
    if(companyname == null || companyname.equals("")){
        return "会社名を入力してください。";
    }
    return "";
}


}


