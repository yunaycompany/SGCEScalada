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
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.entidades.NomSexo;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class SexoModel implements Serializable {

    public SexoModel(EntityManagerFactory emf) {
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

    public void create(NomSexo nomSexo) {
        if (nomSexo.getDatDeportistaCollection() == null) {
            nomSexo.setDatDeportistaCollection(new ArrayList<DatDeportista>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DatDeportista> attachedDatDeportistaCollection = new ArrayList<DatDeportista>();
            for (DatDeportista datDeportistaCollectionDatDeportistaToAttach : nomSexo.getDatDeportistaCollection()) {
                datDeportistaCollectionDatDeportistaToAttach = em.getReference(datDeportistaCollectionDatDeportistaToAttach.getClass(), datDeportistaCollectionDatDeportistaToAttach.getIddeportista());
                attachedDatDeportistaCollection.add(datDeportistaCollectionDatDeportistaToAttach);
            }
            nomSexo.setDatDeportistaCollection(attachedDatDeportistaCollection);
            em.persist(nomSexo);
            for (DatDeportista datDeportistaCollectionDatDeportista : nomSexo.getDatDeportistaCollection()) {
                NomSexo oldIdsexoOfDatDeportistaCollectionDatDeportista = datDeportistaCollectionDatDeportista.getIdsexo();
                datDeportistaCollectionDatDeportista.setIdsexo(nomSexo);
                datDeportistaCollectionDatDeportista = em.merge(datDeportistaCollectionDatDeportista);
                if (oldIdsexoOfDatDeportistaCollectionDatDeportista != null) {
                    oldIdsexoOfDatDeportistaCollectionDatDeportista.getDatDeportistaCollection().remove(datDeportistaCollectionDatDeportista);
                    oldIdsexoOfDatDeportistaCollectionDatDeportista = em.merge(oldIdsexoOfDatDeportistaCollectionDatDeportista);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomSexo nomSexo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomSexo persistentNomSexo = em.find(NomSexo.class, nomSexo.getIdsexo());
            Collection<DatDeportista> datDeportistaCollectionOld = persistentNomSexo.getDatDeportistaCollection();
            Collection<DatDeportista> datDeportistaCollectionNew = nomSexo.getDatDeportistaCollection();
            List<String> illegalOrphanMessages = null;
            for (DatDeportista datDeportistaCollectionOldDatDeportista : datDeportistaCollectionOld) {
                if (!datDeportistaCollectionNew.contains(datDeportistaCollectionOldDatDeportista)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatDeportista " + datDeportistaCollectionOldDatDeportista + " since its idsexo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<DatDeportista> attachedDatDeportistaCollectionNew = new ArrayList<DatDeportista>();
            for (DatDeportista datDeportistaCollectionNewDatDeportistaToAttach : datDeportistaCollectionNew) {
                datDeportistaCollectionNewDatDeportistaToAttach = em.getReference(datDeportistaCollectionNewDatDeportistaToAttach.getClass(), datDeportistaCollectionNewDatDeportistaToAttach.getIddeportista());
                attachedDatDeportistaCollectionNew.add(datDeportistaCollectionNewDatDeportistaToAttach);
            }
            datDeportistaCollectionNew = attachedDatDeportistaCollectionNew;
            nomSexo.setDatDeportistaCollection(datDeportistaCollectionNew);
            nomSexo = em.merge(nomSexo);
            for (DatDeportista datDeportistaCollectionNewDatDeportista : datDeportistaCollectionNew) {
                if (!datDeportistaCollectionOld.contains(datDeportistaCollectionNewDatDeportista)) {
                    NomSexo oldIdsexoOfDatDeportistaCollectionNewDatDeportista = datDeportistaCollectionNewDatDeportista.getIdsexo();
                    datDeportistaCollectionNewDatDeportista.setIdsexo(nomSexo);
                    datDeportistaCollectionNewDatDeportista = em.merge(datDeportistaCollectionNewDatDeportista);
                    if (oldIdsexoOfDatDeportistaCollectionNewDatDeportista != null && !oldIdsexoOfDatDeportistaCollectionNewDatDeportista.equals(nomSexo)) {
                        oldIdsexoOfDatDeportistaCollectionNewDatDeportista.getDatDeportistaCollection().remove(datDeportistaCollectionNewDatDeportista);
                        oldIdsexoOfDatDeportistaCollectionNewDatDeportista = em.merge(oldIdsexoOfDatDeportistaCollectionNewDatDeportista);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomSexo.getIdsexo();
                if (findNomSexo(id) == null) {
                    throw new NonexistentEntityException("The nomSexo with id " + id + " no longer exists.");
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
            NomSexo nomSexo;
            try {
                nomSexo = em.getReference(NomSexo.class, id);
                nomSexo.getIdsexo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomSexo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DatDeportista> datDeportistaCollectionOrphanCheck = nomSexo.getDatDeportistaCollection();
            for (DatDeportista datDeportistaCollectionOrphanCheckDatDeportista : datDeportistaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomSexo (" + nomSexo + ") cannot be destroyed since the DatDeportista " + datDeportistaCollectionOrphanCheckDatDeportista + " in its datDeportistaCollection field has a non-nullable idsexo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nomSexo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomSexo> findNomSexoEntities() {
        return findNomSexoEntities(true, -1, -1);
    }

    public List<NomSexo> findNomSexoEntities(int maxResults, int firstResult) {
        return findNomSexoEntities(false, maxResults, firstResult);
    }

    private List<NomSexo> findNomSexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomSexo.class));
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

    public NomSexo findNomSexo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomSexo.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomSexoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomSexo> rt = cq.from(NomSexo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
