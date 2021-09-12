package org.kalum.core.db;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

public class Conexion {
    private Connection conexion;
    private Statement enunciado;
    private static Conexion instancia;

    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/kalum2021_dev?user=root&password=root&serverTimezone=UTC");
            enunciado = conexion.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        return this.conexion;
    }

    public ResultSet hacerConsulta(String consulta) {
        ResultSet resultado = null;
        try {
            resultado = enunciado.executeQuery(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}