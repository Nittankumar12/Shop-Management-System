package model;

import org.w3c.dom.ls.LSOutput;

public class CustomerBean {
    public int cust_id;
    public int cust_spendings;
    public String cust_name;
    public String cust_contact;

    public CustomerBean() {
        super();
    }

    public int getCust_id() {
        return cust_id;
    }

    public int getCust_spendings() {
        return cust_spendings;
    }

    public String getCust_name() {
        return cust_name;
    }

    public String getCust_contact() {
        return cust_contact;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public void setCust_spendings(int cust_spendings) {
        this.cust_spendings = cust_spendings;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    @Override
    public String toString() {
        return "CustomerBean{" +
                "cust_id=" + cust_id +
                ", cust_spendings=" + cust_spendings +
                ", cust_name='" + cust_name + '\'' +
                ", cust_contact='" + cust_contact + '\'' +
                '}';
    }

    public void setCust_contact(String cust_contact) {
        this.cust_contact = cust_contact;
    }

    public CustomerBean(int cust_id, int cust_spendings, String cust_name, String cust_contact) {
        super();
        this.cust_id = cust_id;
        this.cust_spendings = cust_spendings;
        this.cust_name = cust_name;
        this.cust_contact = cust_contact;
    }
}

