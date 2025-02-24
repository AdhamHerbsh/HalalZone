package com.example.halalzone;

public class OfferModel {
    private String name;
    private String business;
    private String oldprice;
    private String newprice;
    private int image;

    public OfferModel(String name,String business,String oldprice,String newprice, int image) {
        this.name = name;
        this.business = business;
        this.oldprice = oldprice;
        this.newprice = newprice;
        this.image = image;
    }


    public void setBusiness(String business) {
        this.business = business;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public void setNewprice(String newprice) {
        this.newprice = newprice;
    }



    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }
    public String getOldprice() {
        return oldprice;
    }
    public String getNewprice() {
        return newprice;
    }
    public String getBusiness() {
        return business;
    }
    public String getName() {
        return name;
    }

}
