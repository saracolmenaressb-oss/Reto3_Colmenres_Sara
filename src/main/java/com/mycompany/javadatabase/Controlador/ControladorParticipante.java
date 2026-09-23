/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javadatabase.Controlador;

import com.mycompany.javadatabase.Modelo.Clases.Participante;
import com.mycompany.javadatabase.Modelo.Persistencia.ConexionDB;
import com.mycompany.javadatabase.Modelo.Persistencia.Operaciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class ControladorParticipante {

    // 1. INSCRIBIR PARTICIPANTE 
    public static boolean registrarParticipante(String nombre, String correo, String empresa) throws SQLException {
        // Validar campos vacíos
        if (nombre == null || nombre.trim().isEmpty() || 
            correo == null || correo.trim().isEmpty() || 
            empresa == null || empresa.trim().isEmpty()) {
            System.out.println("Error: Todos los campos (nombre, correo, empresa) son obligatorios.");
            return false;
        }
        
        // Validar correo duplicado
        if (existeCorreo(correo)) {
            System.out.println("Error de validación: Ya existe una inscripción con este correo.");
            return false; 
        }
        
        Participante par = new Participante(nombre, correo, empresa);
        Operaciones.setConnection(ConexionDB.MysConnection());
        
        // Sentencia SQL corregida apuntando a la tabla participantes
        String sentencia = "INSERT INTO participantes (nombre, correo, empresa) VALUES (?, ?, ?);";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ps.setString(1, par.getNombre());
        ps.setString(2, par.getCorreo());
        ps.setString(3, par.getEmpresa());
        
        // Transacción
        if (Operaciones.setAutoCommitBD(false)) {
            if (Operaciones.insertar_actualizar_borrar_BD(ps) > 0) {
                Operaciones.commitBD();
                Operaciones.cerrarConexion();
                System.out.println("¡Participante inscrito exitosamente!");
                return true;
            } else {
                Operaciones.rollbackBD();
                Operaciones.cerrarConexion();
                return false;
            }
        } else {
            Operaciones.cerrarConexion();
            return false;
        }
    }

    // Método auxiliar privado para verificar correo duplicado
    private static boolean existeCorreo(String correo) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sql = "SELECT * FROM participantes WHERE correo = ?";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sql);
        ps.setString(1, correo);
        ResultSet rs = Operaciones.consultar_BD(ps);
        boolean encontrado = rs != null && rs.next(); 
        Operaciones.cerrarConexion();
        return encontrado;
    }

    // 2. LISTAR TODOS LOS PARTICIPANTES
    public static List<Participante> listarParticipantes() throws SQLException {
        List<Participante> lista = new ArrayList<>();
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "SELECT * FROM participantes;";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ResultSet rs = Operaciones.consultar_BD(ps);
        
        while (rs != null && rs.next()) {
            int id = rs.getInt("idparticipante");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            String empresa = rs.getString("empresa");
            
            Participante p = new Participante(id, nombre, correo, empresa);
            lista.add(p);
        }
        Operaciones.cerrarConexion();
        return lista;
    }

    // 3. BUSCAR PARTICIPANTES POR EMPRESA
    public static List<Participante> buscarPorEmpresa(String empresaBuscada) throws SQLException {
        List<Participante> lista = new ArrayList<>();
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "SELECT * FROM participantes WHERE empresa = ?;";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ps.setString(1, empresaBuscada);
        ResultSet rs = Operaciones.consultar_BD(ps);
        
        while (rs != null && rs.next()) {
            int id = rs.getInt("idparticipante");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            String empresa = rs.getString("empresa");
            
            Participante p = new Participante(id, nombre, correo, empresa);
            lista.add(p);
        }
        Operaciones.cerrarConexion();
        return lista;
    }

    // 4. CONTAR TOTAL DE PARTICIPANTES
    public static int contarParticipantes() throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "SELECT COUNT(*) AS total FROM participantes;";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ResultSet rs = Operaciones.consultar_BD(ps);
        
        int total = 0;
        if (rs != null && rs.next()) {
            total = rs.getInt("total");
        }
        Operaciones.cerrarConexion();
        return total;
    }

    // 5. ELIMINAR PARTICIPANTE POR ID
    public static boolean eliminarParticipante(int id) throws SQLException {
        Operaciones.setConnection(ConexionDB.MysConnection());
        String sentencia = "DELETE FROM participantes WHERE idparticipante = ?;";
        PreparedStatement ps = Operaciones.getConnection().prepareStatement(sentencia);
        ps.setInt(1, id);
        
        if (Operaciones.setAutoCommitBD(false)) {
            int filasAfectadas = Operaciones.insertar_actualizar_borrar_BD(ps);
            if (filasAfectadas > 0) {
                Operaciones.commitBD();
                Operaciones.cerrarConexion();
                System.out.println("Participante con ID " + id + " eliminado correctamente.");
                return true;
            } else {
                Operaciones.rollbackBD();
                Operaciones.cerrarConexion();
                System.out.println("Aviso: No se encontró ningún participante con el ID " + id + ".");
                return false;
            }
        } else {
            Operaciones.cerrarConexion();
            return false;
        }
    }
}