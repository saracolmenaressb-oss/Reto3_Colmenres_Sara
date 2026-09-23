# Sistema de Inscripción a un Webinar Corporativo (JDBC + MVC)

---
## Nombre: Sara Colmenares
----

Aplicación de consola en Java desarrollada bajo la arquitectura **MVC (Modelo-Vista-Controlador)** que utiliza **JDBC**, **PreparedStatement** y **transacciones (Commit/Rollback)** para gestionar las inscripciones a un webinar corporativo, garantizando la integridad de los datos y evitando correos duplicados.

---

## 🛠️ Tecnologías y Requisitos
* **Lenguaje:** Java (JDK 25)
* **Entorno de Desarrollo:** Apache NetBeans / Consola
* **Gestor de Dependencias:** Maven
* **Base de Datos:** MySQL
* **Conector:** `mysql-connector-j`

---

## 🗄️ Script de la Base de Datos

Ejecuta el siguiente script SQL en tu gestor (MySQL Workbench / DBeaver) para crear la tabla requerida:

```sql
-- Ajusta el USE según tu entorno (my_db o campus)
USE my_db; 

CREATE TABLE IF NOT EXISTS participantes (
 idparticipante INT AUTO_INCREMENT PRIMARY KEY,
 nombre VARCHAR(100) NOT NULL,
 correo VARCHAR(120) NOT NULL UNIQUE,
 empresa VARCHAR(100) NOT NULL
);