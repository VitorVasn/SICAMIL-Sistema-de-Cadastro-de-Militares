package logica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import logica.Militar;
import util.JPAUtil;

import java.util.List;

public class militarDAO {

    // Método para salvar ou atualizar um militar no banco de dados
    public void salvar(Militar militar) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin(); // Inicia a transação
            em.merge(militar); // Salva ou atualiza o objeto
            em.getTransaction().commit(); // Confirma a transação
        } finally {
            em.close(); // Fecha o EntityManager
        }
    }

    // Método para buscar um militar pelo SARAM (chave primária)
    public Militar buscarPorSaram(String saram) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Militar.class, saram); // Busca pelo ID (SARAM)
        } finally {
            em.close();
        }
    }

    // Método para remover um militar pelo SARAM
    public void remover(String saram) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Militar militar = em.find(Militar.class, saram); // Busca o militar
            if (militar != null) {
                em.getTransaction().begin(); // Inicia a transação
                em.remove(militar); // Remove o objeto
                em.getTransaction().commit(); // Confirma a transação
            }
        } finally {
            em.close();
        }
    }

    // Método para listar todos os militares cadastrados
    public List<Militar> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Militar> query = em.createQuery("SELECT m FROM Militar m", Militar.class);
            return query.getResultList(); // Retorna a lista de militares
        } finally {
            em.close();
        }
    }
}
