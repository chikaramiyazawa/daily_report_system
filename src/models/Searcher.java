package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "searcher")
@NamedQueries({
    @NamedQuery(
            name = "getAllSearcher",
            query = "SELECT s FROM Searcher AS s ORDER BY s.id DESC"
                ),
    @NamedQuery(
            name = "getSearcherCount",
            query ="SELECT COUNT(s) FROM Searcher AS s"
            ),
    @NamedQuery(
            name = "checkRegisteredSearch_id",
            query ="SELECT COUNT(s)FROM Searcher AS s WHERE s.search_id = :search_id"
            ),
    @NamedQuery(
            name = "checkSearch_id",
            query = "SELECT s FROM Searcher AS s WHERE   s.used  = 0 AND s.search_id = :search_id"
            ),
    @NamedQuery(
            name = "checkSearchUse",
            query = "SELECT s FROM Searcher AS s WHERE   s.used = 1 AND s.search_id = :search_id"
            ),



})
@Entity
public class Searcher {

@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column(name = "search_id" , nullable = false)
private String search_id;

@Column(name = "used" , nullable = false)
private Integer used;


@Column(name = "delete_flag" , nullable = false)
private Integer delete_flag;

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public String getSearch_id() {
    return search_id;
}

public void setSearch_id(String search_id) {
    this.search_id = search_id;
}
public Integer getDelete_flag() {
    return delete_flag;
}

public void setDelete_flag(Integer delete_flag) {
    this.delete_flag = delete_flag;
}

public Integer getUsed() {
    return used;
}

public void setUsed(Integer used) {
    this.used = used;
}

}