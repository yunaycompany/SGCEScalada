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
import sgc.escalada.mvc.entidades.DatUsuario;
import sgc.escalada.mvc.entidades.NomRol;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class UsuarioModel implements Serializable {

    public UsuarioModel(EntityManagerFactory emf) {
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

    public void create(DatUsuario datUsuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomRol idrol = datUsuario.getIdrol();
            if (idrol != null) {
                idrol = em.getReference(idrol.getClass(), idrol.getIdrol());
                datUsuario.setIdrol(idrol);
            }
            em.persist(datUsuario);
            if (idrol != null) {
                idrol.getDatUsuarioCollection().add(datUsuario);
                idrol = em.merge(idrol);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatUsuario datUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatUsuario persistentDatUsuario = em.find(DatUsuario.class, datUsuario.getIdusuario());
            NomRol idrolOld = persistentDatUsuario.getIdrol();
            NomRol idrolNew = datUsuario.getIdrol();
            if (idrolNew != null) {
                idrolNew = em.getReference(idrolNew.getClass(), idrolNew.getIdrol());
                datUsuario.setIdrol(idrolNew);
            }
            datUsuario = em.merge(datUsuario);
            if (idrolOld != null && !idrolOld.equals(idrolNew)) {
                idrolOld.getDatUsuarioCollection().remove(datUsuario);
                idrolOld = em.merge(idrolOld);
            }
            if (idrolNew != null && !idrolNew.equals(idrolOld)) {
                idrolNew.getDatUsuarioCollection().add(datUsuario);
                idrolNew = em.merge(idrolNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datUsuario.getIdusuario();
                if (findDatUsuario(id) == null) {
                    throw new NonexistentEntityException("The datUsuario with id " + id + " no longer exists.");
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
            DatUsuario datUsuario;
            try {
                datUsuario = em.getReference(DatUsuario.class, id);
                datUsuario.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datUsuario with id " + id + " no longer exists.", enfe);
            }
            NomRol idrol = datUsuario.getIdrol();
            if (idrol != null) {
                idrol.getDatUsuarioCollection().remove(datUsuario);
                idrol = em.merge(idrol);
            }
            em.remove(datUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatUsuario> findDatUsuarioEntities() {
        return findDatUsuarioEntities(true, -1, -1);
    }

    public List<DatUsuario> findDatUsuarioEntities(int maxResults, int firstResult) {
        return findDatUsuarioEntities(false, maxResults, firstResult);
    }

    private List<DatUsuario> findDatUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatUsuario.class));
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

    public DatUsuario findDatUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatUsuario> rt = cq.from(DatUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
