/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Paciente;
import dao.PacienteDao;

/**
 *
 * @author Super
 */
public class PacienteControlller {
    private PacienteDao pacienteDao;

    public PacienteControlller(PacienteDao pacienteDao) {
        this.pacienteDao = pacienteDao;
    }
    
    public DefaultTableModel llenarTabla(){
        DefaultTableModel model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        model.addColumn("Id");
        model.addColumn("Número Doc.");
        model.addColumn("Nombre");
        model.addColumn("Apellido");  
         model.addColumn("Fecha Nacimiento.");
        //model.addColumn("Estado Civil");
       // model.addColumn("Sexo");
        model.addColumn("Direccion");
        model.addColumn("Telefono");
       
        List<Paciente> pacientes=pacienteDao.findAll();
        String [] registros= new String [7];
        for(Paciente f:pacientes){
            registros[0]=String.valueOf(f.getId());
            registros[1]=f.getNumeroIdentificacion();
            registros[2]=f.getNombres();
            registros[3]=f.getApellidos();
            registros[4]=f.getFechaNacimiento().toString();
            //registros[4]=f.getEstadoCivil().toString();
          // registros[4]= String.valueOf(f.getEstadoCivil());
          //  registros[5]= Character.toString(f.getSexo());
            registros[5]=f.getDireccion();
            registros[6]=f.getTelefono();
            
            model.addRow(registros);
        }
        return model;
    }
    
    public Paciente listarPorDocumento(String documento){
        return pacienteDao.findById(documento);
    }
    
    public int guardar(Paciente paciente){
        return pacienteDao.save(paciente);
    }
    
    public int actualizar(Paciente paciente){
        return pacienteDao.update(paciente);
    }
    
    public void borrar(String documento){
        pacienteDao.delete(documento);
    }
}
