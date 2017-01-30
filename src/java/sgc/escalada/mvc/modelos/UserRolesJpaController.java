/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.modelos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sgc.escalada.mvc.entidades.UserRoles;
import sgc.escalada.mvc.entidades.Users;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Ariam
 */
public class UserRolesJpaController implements Serializable {

    public UserRolesJpaController(EntityManagerFactory emf) {
        if (emf == null) {
            this.emf = Persistence.createEntityManagerFactory("SGCEscaladaPU");
        } else {
            this.emf = emf;
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserRoles userRoles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users username = userRoles.getUsername();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                userRoles.setUsername(username);
            }
            em.persist(userRoles);
            if (username != null) {
                username.getUserRolesCollection().add(userRoles);
                username = em.merge(username);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserRoles userRoles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserRoles persistentUserRoles = em.find(UserRoles.class, userRoles.getUserRoleId());
            Users usernameOld = persistentUserRoles.getUsername();
            Users usernameNew = userRoles.getUsername();
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                userRoles.setUsername(usernameNew);
            }
            userRoles = em.merge(userRoles);
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getUserRolesCollection().remove(userRoles);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getUserRolesCollection().add(userRoles);
                usernameNew = em.merge(usernameNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userRoles.getUserRoleId();
                if (findUserRoles(id) == null) {
                    throw new NonexistentEntityException("The userRoles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserRoles userRoles;
            try {
                userRoles = em.getReference(UserRoles.class, id);
                userRoles.getUserRoleId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userRoles with id " + id + " no longer exists.", enfe);
            }
            Users username = userRoles.getUsername();
            if (username != null) {
                username.getUserRolesCollection().remove(userRoles);
                username = em.merge(username);
            }
            em.remove(userRoles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserRoles> findUserRolesEntities() {
        return findUserRolesEntities(true, -1, -1);
    }

    public List<UserRoles> findUserRolesEntities(int maxResults, int firstResult) {
        return findUserRolesEntities(false, maxResults, firstResult);
    }

    private List<UserRoles> findUserRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserRoles.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UserRoles findUserRoles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserRoles.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserRoles> rt = cq.from(UserRoles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
