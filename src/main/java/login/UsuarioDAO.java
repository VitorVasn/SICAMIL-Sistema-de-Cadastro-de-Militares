package login;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import util.JPAUtil;

public class UsuarioDAO {

    // Método para salvar um novo usuário no banco de dados
    public void salvar(Usuario usuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Método para buscar um usuário pelo login e senha (usado no login)
    public Usuario buscarPorLoginSenha(String login, String senha) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class);
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna null se o login/senha estiver incorreto
        } finally {
            em.close();
        }
    }

    // Método para verificar se o login já existe no banco de dados
    public boolean existeLogin(String login) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(u) FROM Usuario u WHERE u.login = :login", Long.class);
            query.setParameter("login", login);
            Long quantidade = query.getSingleResult();
            return quantidade > 0;
        } finally {
            em.close();
        }
    }
}
