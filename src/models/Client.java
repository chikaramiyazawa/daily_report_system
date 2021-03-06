package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "client")
@NamedQueries({
    @NamedQuery(
            name = "getAllClient",
            query = "SELECT c FROM Client AS c ORDER BY c.id DESC"
                ),
    @NamedQuery(
            name = "getClientsCount",
            query ="SELECT COUNT(c) FROM Client AS c"
            ),
    @NamedQuery(
            name = "checkCompanyCode",
            query ="SELECT COUNT(c)FROM Client AS c WHERE c.companycode = :companycode"
            ),
    @NamedQuery(
            name = "checkAuthentizationCode",
            query = "SELECT c FROM Client AS c WHERE c.delete_flag = 0 AND c.companycode = :companycode "
            )
})
@Entity
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "companycode" ,  nullable = false)
    private String companycode;

    @Column(name = "companyname", nullable = false, unique = true)
    private String companyname;

    @Column(name = "created_at" , nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at" , nullable = false )
    private Timestamp updated_at;

    @Column(name = "delete_flag" , nullable = false)
    private Integer delete_flag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }



}
