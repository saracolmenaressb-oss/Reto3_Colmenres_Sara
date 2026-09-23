/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.javadatabase.Vista;

import com.mycompany.javadatabase.Controlador.ControladorParticipante;
import com.mycompany.javadatabase.Modelo.Clases.Participante;
import com.mycompany.javadatabase.Modelo.Persistencia.ConexionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class JavaDatabase {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA DE INSCRIPCIÓN - WEBINAR     ");
            System.out.println("==========================================");
            System.out.println("1. Inscribir un participante");
            System.out.println("2. Listar todos los participantes");
            System.out.println("3. Buscar participantes por empresa");
            System.out.println("4. Contar total de participantes");
            System.out.println("5. Eliminar inscripción por ID");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción (1-6): ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- REGISTRAR NUEVO PARTICIPANTE ---");
                        System.out.print("Ingrese el nombre completo: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese el correo electrónico: ");
                        String correo = scanner.nextLine();
                        System.out.print("Ingrese la empresa: ");
                        String empresa = scanner.nextLine();

                        // Llamada al Controlador
                        ControladorParticipante.registrarParticipante(nombre, correo, empresa);
                        break;

                    case 2:
                        System.out.println("\n--- LISTA DE PARTICIPANTES INSCRITOS ---");
                        List<Participante> lista = ControladorParticipante.listarParticipantes();
                        if (lista.isEmpty()) {
                            System.out.println("No hay participantes inscritos todavía.");
                        } else {
                            for (Participante p : lista) {
                                System.out.println(p); // Usa el toString() de la clase Participante
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n--- BUSCAR POR EMPRESA ---");
                        System.out.print("Ingrese el nombre de la empresa a buscar: ");
                        String empresaBuscada = scanner.nextLine();
                        
                        List<Participante> listaEmpresa = ControladorParticipante.buscarPorEmpresa(empresaBuscada);
                        if (listaEmpresa.isEmpty()) {
                            System.out.println("No se encontraron participantes para la empresa: " + empresaBuscada);
                        } else {
                            for (Participante p : listaEmpresa) {
                                System.out.println(p);
                            }
                        }
                        break;

                    case 4:
                        System.out.println("\n--- TOTAL DE INSCRITOS ---");
                        int total = ControladorParticipante.contarParticipantes();
                        System.out.println("Cantidad total de participantes: " + total);
                        break;

                    case 5:
                        System.out.println("\n--- ELIMINAR PARTICIPANTE ---");
                        System.out.print("Ingrese el ID del participante que desea eliminar: ");
                        int idEliminar = Integer.parseInt(scanner.nextLine());
                        
                        ControladorParticipante.eliminarParticipante(idEliminar);
                        break;

                    case 6:
                        System.out.println("\n¡Gracias por usar el sistema! Saliendo...");
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, elija un número entre 1 y 6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido en las opciones o IDs.");
            } catch (SQLException e) {
                System.out.println("Error de base de datos: " + e.getMessage());
            }

        } while (opcion != 6);

        scanner.close();
        
    }
}
