package com.mytorch.sweety.fragment_json;

/**
 * Created by Sweety on 04-04-2016.
 */
// STEP 4.1
    // CREATE SEPERTAE CLASS WITH REQUIRED VARIABLE THAT WE WANT TO STORE DATA DEPENDING UPON JSON S
public class Contacts {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String name,email,phone;
}
