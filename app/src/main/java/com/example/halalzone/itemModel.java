package com.example.halalzone;

public class itemModel {
    private String name;
    private String newprice;
    private int image;

    public itemModel(String name, String newprice, int image) {
        this.name = name;
        this.newprice = newprice;
        this.image = image;
    }



    public void setName(String name) {
        this.name = name;
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
    public String getNewprice() {
        return newprice;
    }

    public String getName() {
        return name;
    }

}
