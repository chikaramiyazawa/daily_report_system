package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Searcher;
import utils.DBUtil;

public class Search_idValidator {
    public static List<String> validate(Searcher s ,Boolean search_duplicate_check_flag){
        List<String>errors_id = new ArrayList<String>();

        String search_error = _validateSearch(s.getSearch_id(), search_duplicate_check_flag);
        if(!search_error.equals("")){
            errors_id.add(search_error);
        }

        return errors_id;
    }

    private static String _validateSearch(String searcher , Boolean search_duplicate_check_flag){
        if(searcher == null || searcher.equals("")){
            return "商談Idを入力してください。";
        }
        if(search_duplicate_check_flag){
            EntityManager em = DBUtil.createEntityManager();
            long searchs_count = (long)em.createNamedQuery("checkRegisteredSearch_id" , Long.class)
                                        .setParameter("search_id", searcher)
                                        .getSingleResult();
            em.close();
            if(searchs_count > 0){
                return "入力された商談Idはすでに存在しています";
            }
        }
        return "";
     }

}

