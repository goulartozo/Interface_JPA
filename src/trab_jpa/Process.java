/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trab_jpa;

import java.util.List;
import javax.persistence.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author douglas
 */
public class Process {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trab_jpaPU");
    
    public boolean autenticacao(String nomeUsuario, String senha){
        EntityManager em = emf.createEntityManager();
        
        try {
            TypedQuery<Usuarios> query = em.createQuery(  "SELECT u FROM Usuarios u WHERE u.nomeUsuario = :nomeUsuario AND u.senha = :senha", Usuarios.class);
            query.setParameter("nomeUsuario", nomeUsuario);
            query.setParameter("senha", senha);
            
            Usuarios usuario = query.getSingleResult();
            
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }
    
    public void adicionar(String nome, int idade, String cidade){
        try {
            CadastrosJpaController cadCtrl = new CadastrosJpaController(emf);
            
            Cadastros cad = new Cadastros();
            cad.setNome(nome);
            cad.setIdade(idade);
            cad.setCidade(cidade);
            cadCtrl.create(cad);
            
            
        } catch (PersistenceException e) {
        }
    }
    
    public void atualizar(int id, String nome, int idade, String cidade){
        
        try {
            CadastrosJpaController cadCtrl = new CadastrosJpaController(emf);
            
            Cadastros cad = cadCtrl.findCadastros(id);
            
            cad.setNome(nome);
            cad.setIdade(idade);
            cad.setCidade(cidade);
            
            cadCtrl.edit(cad);
            
        } catch (Exception e) {
        }
        
    }
    
    public Cadastros buscando(int id){
        try {
            CadastrosJpaController cadCtrl = new CadastrosJpaController(emf);
            Cadastros cad = cadCtrl.findCadastros(id);
            return cad;
        } catch (Exception e) {
        }
        return null;
    }
    
    public void excluir(int id){
        try {
            
            CadastrosJpaController cadCtrl = new CadastrosJpaController(emf);
            cadCtrl.destroy(id);
  
        } catch (Exception e) {
        }
    }
    
    public List<Cadastros> listaCadastros(){
        try {
            CadastrosJpaController cadCtrl = new CadastrosJpaController(emf);
            List<Cadastros> cad = cadCtrl.findCadastrosEntities();
            return cad;
        } catch (Exception e) {
        }
        return null;
    }
    
    

}
