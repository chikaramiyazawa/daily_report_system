package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "opportunity")
@NamedQueries({
    @NamedQuery(
        name = "getAllOpportunity",
        query = "SELECT o FROM Opportunity AS o ORDER BY o.id DESC"
    ),
    @NamedQuery(
        name = "getOpportunityCount",
        query = "SELECT COUNT(o) FROM Opportunity AS o"
    ),
    @NamedQuery(
            name = "getMyClientOpportunity",
            query = "SELECT o FROM Opportunity AS o WHERE o.client = :client ORDER BY o.id DESC"
            ),
    @NamedQuery(
            name = "getMyClientOpportunityCount",
            query = "SELECT COUNT(o) FROM Opportunity AS o WHERE o.client = :client "
            ),

    @NamedQuery(
            name = "getOp_id",
            query = "SELECT o FROM Opportunity AS o WHERE o.op_id = :op_id ORDER BY o.id DESC"
            ),
    @NamedQuery(
            name = "getOp_idCount",
            query = "SELECT COUNT(o) FROM Opportunity AS o WHERE o.op_id = :op_id"
            ),
    @NamedQuery(
            name = "getPersonAndCode",
            query = "SELECT o FROM Opportunity AS o WHERE o.person like :person AND o.op_code = :op_code ORDER BY o.id DESC"
            ),
    @NamedQuery(
            name = "getPersonAndCodeCount",
            query = "SELECT COUNT(o) FROM Opportunity AS  o WHERE o.person = :person AND o.op_code = :op_code"
            ),
    @NamedQuery(
            name = "getOpportunity_date",
            query = "SELECT o FROM Opportunity AS o WHERE o.opportunity_date = :opportunity_date ORDER BY o.id DESC"
            ),
    @NamedQuery(
            name = "getOpportunity_dateCount",
            query = "SELECT COUNT(o) FROM Opportunity AS o WHERE o.opportunity_date = :opportunity_date"

            ),
    @NamedQuery(
            name = "getBeforeDate",
            query = "SELECT o FROM Opportunity AS o WHERE o.opportunity_date < :opportunity_date  ORDER BY o.id DESC"
            ),
    @NamedQuery(
            name = "getBeforeDateCount",
            query = "SELECT COUNT(o) FROM Opportunity AS o WHERE o.opportunity_date < :opportunity_date"
            ),
    @NamedQuery(
            name = "getAfterDate",
            query = "SELECT o FROM Opportunity AS o WHERE o.opportunity_date > :opportunity_date  ORDER BY o.id DESC"
            ),
    @NamedQuery(
            name = "getAfterDateCount",
            query = "SELECT COUNT(o) FROM Opportunity AS o WHERE o.opportunity_date > :opportunity_date "
               )
})
@Entity
public class Opportunity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "companycode", nullable = false)
    private Client companycode;

    @Column(name = "op_code" , nullable = false)
    private String op_code;

    @Column(name = "op_id" , nullable = false)
    private String op_id;

    @Column(name = "op_com" , nullable = false)
    private String op_com;


    @Column(name = "person" , nullable = false)
    private String person;

    @Column(name="changer",nullable = false)
    private String changer;

    @Column(name = "location" , nullable = false)
    private String location;

    @Column(name = "opportunity_date", nullable = false)
    private Date opportunity_date;

    @Column(name = "opportunity", length = 255, nullable = false)
    private String opportunity;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getOpportunity_date() {
        return opportunity_date;
    }

    public void setOpportunity_date(Date opportunity_date) {
        this.opportunity_date = opportunity_date;
    }

    public String getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(String opportunity) {
        this.opportunity = opportunity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    public Client getCompanycode() {
        return companycode;
    }

    public void setCompanycode(Client companycode) {
        this.companycode = companycode;
    }
    public String getPerson(){
        return person;
    }
    public void setPerson(String person){
        this.person = person;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getChanger() {
        return changer;
    }


    public void setChanger(String changer) {
        this.changer = changer;
    }

    public String getOp_id() {
        return op_id;
    }

    public void setOp_id(String op_id) {
        this.op_id = op_id;
    }

    public String getOp_code() {
        return op_code;
    }

    public void setOp_code(String op_code) {
        this.op_code = op_code;
    }
    public String getOp_com() {
        return op_com;
    }

    public void setOp_com(String op_com) {
        this.op_com = op_com;
    }



}