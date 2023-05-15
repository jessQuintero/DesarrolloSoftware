/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.Paciente;

/**
 *
 * @author Super
 */
public interface PacienteDao {
    List<Paciente> findAll();
    
    Paciente findById(String documento);
    
    int save(Paciente paciente);
    
    int update(Paciente paciente);
    
    void delete(String documento);
}
