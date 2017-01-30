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
import sgc.escalada.mvc.entidades.DatUsuario;
import sgc.escalada.mvc.entidades.NomRol;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Ariam
 */
public class RolModel implements Serializable {

    public RolModel(EntityManagerFactory emf) {
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

    public void create(NomRol nomRol) {
        if (nomRol.getDatUsuarioCollection() == null) {
            nomRol.setDatUsuarioCollection(new ArrayList<DatUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DatUsuario> attachedDatUsuarioCollection = new ArrayList<DatUsuario>();
            for (DatUsuario datUsuarioCollectionDatUsuarioToAttach : nomRol.getDatUsuarioCollection()) {
                datUsuarioCollectionDatUsuarioToAttach = em.getReference(datUsuarioCollectionDatUsuarioToAttach.getClass(), datUsuarioCollectionDatUsuarioToAttach.getIdusuario());
                attachedDatUsuarioCollection.add(datUsuarioCollectionDatUsuarioToAttach);
            }
            nomRol.setDatUsuarioCollection(attachedDatUsuarioCollection);
            em.persist(nomRol);
            for (DatUsuario datUsuarioCollectionDatUsuario : nomRol.getDatUsuarioCollection()) {
                NomRol oldIdrolOfDatUsuarioCollectionDatUsuario = datUsuarioCollectionDatUsuario.getIdrol();
                datUsuarioCollectionDatUsuario.setIdrol(nomRol);
                datUsuarioCollectionDatUsuario = em.merge(datUsuarioCollectionDatUsuario);
                if (oldIdrolOfDatUsuarioCollectionDatUsuario != null) {
                    oldIdrolOfDatUsuarioCollectionDatUsuario.getDatUsuarioCollection().remove(datUsuarioCollectionDatUsuario);
                    oldIdrolOfDatUsuarioCollectionDatUsuario = em.merge(oldIdrolOfDatUsuarioCollectionDatUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomRol nomRol) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomRol persistentNomRol = em.find(NomRol.class, nomRol.getIdrol());
            Collection<DatUsuario> datUsuarioCollectionOld = persistentNomRol.getDatUsuarioCollection();
            Collection<DatUsuario> datUsuarioCollectionNew = nomRol.getDatUsuarioCollection();
            List<String> illegalOrphanMessages = null;
            for (DatUsuario datUsuarioCollectionOldDatUsuario : datUsuarioCollectionOld) {
                if (!datUsuarioCollectionNew.contains(datUsuarioCollectionOldDatUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatUsuario " + datUsuarioCollectionOldDatUsuario + " since its idrol field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<DatUsuario> attachedDatUsuarioCollectionNew = new ArrayList<DatUsuario>();
            for (DatUsuario datUsuarioCollectionNewDatUsuarioToAttach : datUsuarioCollectionNew) {
                datUsuarioCollectionNewDatUsuarioToAttach = em.getReference(datUsuarioCollectionNewDatUsuarioToAttach.getClass(), datUsuarioCollectionNewDatUsuarioToAttach.getIdusuario());
                attachedDatUsuarioCollectionNew.add(datUsuarioCollectionNewDatUsuarioToAttach);
            }
            datUsuarioCollectionNew = attachedDatUsuarioCollectionNew;
            nomRol.setDatUsuarioCollection(datUsuarioCollectionNew);
            nomRol = em.merge(nomRol);
            for (DatUsuario datUsuarioCollectionNewDatUsuario : datUsuarioCollectionNew) {
                if (!datUsuarioCollectionOld.contains(datUsuarioCollectionNewDatUsuario)) {
                    NomRol oldIdrolOfDatUsuarioCollectionNewDatUsuario = datUsuarioCollectionNewDatUsuario.getIdrol();
                    datUsuarioCollectionNewDatUsuario.setIdrol(nomRol);
                    datUsuarioCollectionNewDatUsuario = em.merge(datUsuarioCollectionNewDatUsuario);
                    if (oldIdrolOfDatUsuarioCollectionNewDatUsuario != null && !oldIdrolOfDatUsuarioCollectionNewDatUsuario.equals(nomRol)) {
                        oldIdrolOfDatUsuarioCollectionNewDatUsuario.getDatUsuarioCollection().remove(datUsuarioCollectionNewDatUsuario);
                        oldIdrolOfDatUsuarioCollectionNewDatUsuario = em.merge(oldIdrolOfDatUsuarioCollectionNewDatUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomRol.getIdrol();
                if (findNomRol(id) == null) {
                    throw new NonexistentEntityException("The nomRol with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomRol nomRol;
            try {
                nomRol = em.getReference(NomRol.class, id);
                nomRol.getIdrol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomRol with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DatUsuario> datUsuarioCollectionOrphanCheck = nomRol.getDatUsuarioCollection();
            for (DatUsuario datUsuarioCollectionOrphanCheckDatUsuario : datUsuarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomRol (" + nomRol + ") cannot be destroyed since the DatUsuario " + datUsuarioCollectionOrphanCheckDatUsuario + " in its datUsuarioCollection field has a non-nullable idrol field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nomRol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomRol> findNomRolEntities() {
        return findNomRolEntities(true, -1, -1);
    }

    public List<NomRol> findNomRolEntities(int maxResults, int firstResult) {
        return findNomRolEntities(false, maxResults, firstResult);
    }

    private List<NomRol> findNomRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomRol.class));
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

    public NomRol findNomRol(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomRol.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomRol> rt = cq.from(NomRol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
