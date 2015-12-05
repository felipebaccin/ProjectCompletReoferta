package br.univel.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import br.univel.entity.Cliente;
import br.univel.modelo.TransacaoVenda;

public class VendaDAO {

	public static void salvar(TransacaoVenda venda) {
        EntityManager em = Conexao.getEntityManager();
        try {
            em.getTransaction().begin();
            if (venda.getId() > 0){
            	em.merge(venda);
            } else {
            	em.persist(venda);	
            }           
            
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
	
	public static void excluir(int idTransacao) {
        EntityManager em = Conexao.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(TransacaoVenda.class, idTransacao));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
	
	public static Cliente localizar(int id) {
        EntityManager em = Conexao.getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
		return null;
    }
	
	public static List<TransacaoVenda> listaTransacoes(){
		EntityManager em = Conexao.getEntityManager();
        try {
            return em.createQuery( "from TransacaoVenda", TransacaoVenda.class ).getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
		return null;
	}
}
