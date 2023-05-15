/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Conexion;
import model.Paciente;
import java.sql.PreparedStatement;
import utis.Mensajes;

/**
 *
 * @author Super
 */
public class PacienteDaoImpl implements PacienteDao {
    
    private Conexion conexion;
    private Statement st;
    private ResultSet rS;
    private PreparedStatement pSt;

    @Override
    public List<Paciente> findAll() {
        List<Paciente> pacientes = new ArrayList<>();
            try {
                /*
                String sql="
                SELECT f.*,t.nombre nombret,e.nombre nombre e 
                FROM pacientes f
                INNER JOIN tipos_identificacion t
                ON f.tipos_identificacion_id=t.id
                INNER JOIN estados_civiles e
                ON f.estados_civiles_id=e.id
                "
                */
                String sql = "SELECT * FROM pacientes";
                conexion=new Conexion();
                st=conexion.getCon().createStatement();
                rS=st.executeQuery(sql);
                while(rS.next()){
                    Paciente paciente= new Paciente();
                    paciente.setId(rS.getInt("id"));
                    paciente.setNumeroIdentificacion(rS.getString("numero_identificacion"));
                    paciente.setNombres(rS.getString("nombres"));
                    paciente.setApellidos(rS.getString("apellidos"));
                    paciente.setDireccion(rS.getString("direccion"));
                    paciente.setTelefono(rS.getString("telefono"));
                    paciente.setFechaNacimiento(LocalDate.parse(rS.getString("fecha_nacimiento")));
                    pacientes.add(paciente);
                }
                st.close();
                rS.close();
                conexion.getCon().close();
            } catch (SQLException ex) {
                Mensajes.mensajeError("Error de BBDD", ex.getMessage());
            }

            return pacientes;
    }

    @Override
    public Paciente findById(String documento) {
        Paciente paciente = new Paciente();
        String sql = "SELECT * FROM pacientes WHERE numero_identificacion=?";
        conexion=new Conexion();
        try {
            
            
            pSt=conexion.getCon().prepareStatement(sql);
            pSt.setString(1, documento);
            rS=pSt.executeQuery();
            if(rS.first()){
                paciente.setId(rS.getInt("id"));
                paciente.setNumeroIdentificacion(rS.getString("numero_identificacion"));
                paciente.setNombres(rS.getString("nombres"));
                paciente.setApellidos(rS.getString("apellidos"));
                paciente.setDireccion(rS.getString("direccion"));
                paciente.setTelefono(rS.getString("telefono"));
                paciente.setFechaNacimiento(LocalDate.parse(rS.getString("fecha_nacimiento"))); 
            }
            pSt.close();
            rS.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError("Error de BBDD", ex.getMessage());
        }
        
        return paciente;
    }

    @Override
    public int save(Paciente paciente) {
        int resultado=0;
        String sql = "INSERT INTO pacientes(" +
                "numero_identificacion," +
                "nombres," +
                "apellidos, " +
                "sexo," +
                "direccion," +
                "telefono," +
                "fecha_nacimiento," +
                "tipos_identificacion_id," +
                "estados_civiles_id" +
                ")" +
                "VALUES(?,?,?,?,?,?,?,?,?);";
        conexion=new Conexion();
        try{
            pSt=conexion.getCon().prepareStatement(sql);
            pSt.setString(1, paciente.getNumeroIdentificacion());
            pSt.setString(2, paciente.getNombres());
            pSt.setString(3, paciente.getApellidos());
            pSt.setString(4, String.valueOf(paciente.getSexo()));
            pSt.setString(5, paciente.getDireccion());
            pSt.setString(6, paciente.getTelefono());
            pSt.setString(7, paciente.getFechaNacimiento().toString());
            pSt.setInt(8, paciente.getTipoIdentificacion().getId());
            pSt.setInt(9, paciente.getEstadoCivil().getId());
            resultado=pSt.executeUpdate();
            pSt.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError("Error de BBDD", ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int update(Paciente paciente) {
        int resultado=0;
        int funcID = 0;
        List<Paciente> func=new ArrayList<>();
        func=findAll();
        for(Paciente f:func){
            if(f.getNumeroIdentificacion().equals(paciente.getNumeroIdentificacion())){
                funcID=f.getId();
                break;
            }
        }
        System.out.println(funcID);
        String sql = "UPDATE pacientes SET numero_identificacion=" +"'"+paciente.getNumeroIdentificacion()+"'"+
                ",nombres=" +"'"+ paciente.getNombres()+"'"+
                ",apellidos= " +"'"+ paciente.getApellidos()+"'"+ ",sexo=" +"'"+ paciente.getSexo()+"'"+
                ",direccion=" +"'"+paciente.getDireccion()+"'"+
                ",telefono=" +"'"+ paciente.getTelefono()+"'"+
                ",fecha_nacimiento=" +"'"+paciente.getFechaNacimiento()+"'"+
                "WHERE id="+funcID;
        conexion=new Conexion();
        try{
            pSt=conexion.getCon().prepareStatement(sql);
            /*pSt.setString(1, paciente.getNumeroIdentificacion());
            System.out.println(paciente.getNumeroIdentificacion());
            pSt.setString(2, paciente.getNombres());
            pSt.setString(3, paciente.getApellidos());
            pSt.setString(4, String.valueOf(paciente.getSexo()));
            pSt.setString(5, paciente.getDireccion());
            pSt.setString(6, paciente.getTelefono());
            pSt.setString(7, paciente.getFechaNacimiento().toString());
            pSt.setInt(8, paciente.getId());
            System.out.println(paciente.getId());*/
            System.out.println(sql);
            resultado=pSt.executeUpdate();
            pSt.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError("Error de BBDD", ex.getMessage());
        }
        return resultado;
    }

    @Override
    public void delete(String documento) {
        String sql="DELETE FROM pacientes WHERE numero_identificacion=?";
        conexion= new Conexion();
        try {
            pSt= conexion.getCon().prepareStatement(sql);
            pSt.setString(1, documento);
            pSt.executeUpdate();
            pSt.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError("Error de BBDD", ex.getMessage());
            
                  
        }
        
        
    }
    
}
