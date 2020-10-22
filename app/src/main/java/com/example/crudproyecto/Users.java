package com.example.crudproyecto;

public class Users {
     private String id,nombre,email,contacto;

    public Users() {
    }

    public Users(String id, String nombre, String email, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contacto = contacto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}
