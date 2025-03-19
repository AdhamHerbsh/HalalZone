package com.example.halalzone;

public class Business {
    private int id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String status;
    private byte[] image;
    private String type;

    public Business(int id, String email, String password, String name, String phone, String status, byte[] image, String type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.image = image;
        this.type = type;
    }

    // Getters
    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getStatus() { return status; }
    public byte[] getImage() { return image; }
    public String getType() { return type; }
}
