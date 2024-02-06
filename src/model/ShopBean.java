package model;

public class ShopBean {
    public int shop_id;
    public String shop_name;
    public String shop_address;
    public String shop_contact;

    public int getShop_id() {
        return shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public String getShop_contact() {
        return shop_contact;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public void setShop_contact(String shop_contact) {
        this.shop_contact = shop_contact;
    }

    public ShopBean(int shop_id, String shop_name, String shop_address, String shop_contact) {
        super();
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_address = shop_address;
        this.shop_contact = shop_contact;
    }

    public ShopBean() {
        super();
    }

    @Override
    public String toString() {

        return "ShopBean{" +
                "shop_id=" + shop_id +
                ", shop_name='" + shop_name + '\'' +
                ", shop_address='" + shop_address + '\'' +
                ", shop_contact=" + shop_contact +
                '}';
    }
}
