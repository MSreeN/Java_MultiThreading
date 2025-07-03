package org.example.Clone;


public class Address implements Cloneable {

    String city;

    Address(String city){
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }

    @Override
    public Address clone() {
        try {
            Address clone = (Address) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Address cloneSec() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}
