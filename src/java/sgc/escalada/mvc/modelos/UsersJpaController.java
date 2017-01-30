/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import sgc.escalada.mvc.modelos.exceptions.PreexistingEntityException;

/**
 *
 * @author Ariam
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
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

    public void create(Users users) throws PreexistingEntityException, Exception {
        if (users.getUserRolesCollection() == null) {
            users.setUserRolesCollection(new ArrayList<UserRoles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<UserRoles> attachedUserRolesCollection = new ArrayList<UserRoles>();
            for (UserRoles userRolesCollectionUserRolesToAttach : users.getUserRolesCollection()) {
                userRolesCollectionUserRolesToAttach = em.getReference(userRolesCollectionUserRolesToAttach.getClass(), userRolesCollectionUserRolesToAttach.getUserRoleId());
                attachedUserRolesCollection.add(userRolesCollectionUserRolesToAttach);
            }
            users.setUserRolesCollection(attachedUserRolesCollection);
            em.persist(users);
            for (UserRoles userRolesCollectionUserRoles : users.getUserRolesCollection()) {
                Users oldUsernameOfUserRolesCollectionUserRoles = userRolesCollectionUserRoles.getUsername();
                userRolesCollectionUserRoles.setUsername(users);
                userRolesCollectionUserRoles = em.merge(userRolesCollectionUserRoles);
                if (oldUsernameOfUserRolesCollectionUserRoles != null) {
                    oldUsernameOfUserRolesCollectionUserRoles.getUserRolesCollection().remove(userRolesCollectionUserRoles);
                    oldUsernameOfUserRolesCollectionUserRoles = em.merge(oldUsernameOfUserRolesCollectionUserRoles);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsers(users.getUsername()) != null) {
                throw new PreexistingEntityException("Users " + users + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getUsername());
            Collection<UserRoles> userRolesCollectionOld = persistentUsers.getUserRolesCollection();
            Collection<UserRoles> userRolesCollectionNew = users.getUserRolesCollection();
            Collection<UserRoles> attachedUserRolesCollectionNew = new ArrayList<UserRoles>();
            for (UserRoles userRolesCollectionNewUserRolesToAttach : userRolesCollectionNew) {
                userRolesCollectionNewUserRolesToAttach = em.getReference(userRolesCollectionNewUserRolesToAttach.getClass(), userRolesCollectionNewUserRolesToAttach.getUserRoleId());
                attachedUserRolesCollectionNew.add(userRolesCollectionNewUserRolesToAttach);
            }
            userRolesCollectionNew = attachedUserRolesCollectionNew;
            users.setUserRolesCollection(userRolesCollectionNew);
            users = em.merge(users);
            for (UserRoles userRolesCollectionOldUserRoles : userRolesCollectionOld) {
                if (!userRolesCollectionNew.contains(userRolesCollectionOldUserRoles)) {
                    userRolesCollectionOldUserRoles.setUsername(null);
                    userRolesCollectionOldUserRoles = em.merge(userRolesCollectionOldUserRoles);
                }
            }
            for (UserRoles userRolesCollectionNewUserRoles : userRolesCollectionNew) {
                if (!userRolesCollectionOld.contains(userRolesCollectionNewUserRoles)) {
                    Users oldUsernameOfUserRolesCollectionNewUserRoles = userRolesCollectionNewUserRoles.getUsername();
                    userRolesCollectionNewUserRoles.setUsername(users);
                    userRolesCollectionNewUserRoles = em.merge(userRolesCollectionNewUserRoles);
                    if (oldUsernameOfUserRolesCollectionNewUserRoles != null && !oldUsernameOfUserRolesCollectionNewUserRoles.equals(users)) {
                        oldUsernameOfUserRolesCollectionNewUserRoles.getUserRolesCollection().remove(userRolesCollectionNewUserRoles);
                        oldUsernameOfUserRolesCollectionNewUserRoles = em.merge(oldUsernameOfUserRolesCollectionNewUserRoles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = users.getUsername();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            Collection<UserRoles> userRolesCollection = users.getUserRolesCollection();
            for (UserRoles userRolesCollectionUserRoles : userRolesCollection) {
                userRolesCollectionUserRoles.setUsername(null);
                userRolesCollectionUserRoles = em.merge(userRolesCollectionUserRoles);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
