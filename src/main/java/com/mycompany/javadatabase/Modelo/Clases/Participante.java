/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javadatabase.Modelo.Clases;

/**
 *
 * @author USUARIO
 */


/**
 * @author Sara
 */
public class Participante {
    private int idparticipante;
    private String nombre;
    private String correo;
    private String empresa;

    // Constructor con ID (para cuando se consulta de la BD)
    public Participante(int idparticipante, String nombre, String correo, String empresa) {
        this.idparticipante = idparticipante;
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
    }

    // Constructor sin ID (para cuando se va a insertar un nuevo registro)
    public Participante(String nombre, String correo, String empresa) {
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
    }

    public int getIdparticipante() {
        return idparticipante;
    }

    public void setIdparticipante(int idparticipante) {
        this.idparticipante = idparticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "ID: " + idparticipante + " | Nombre: " + nombre + " | Correo: " + correo + " | Empresa: " + empresa;
    }
}
