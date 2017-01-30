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
import sgc.escalada.mvc.entidades.DatGaleria;
import sgc.escalada.mvc.entidades.NomTipoMedia;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class TipoMediaModel implements Serializable {

    public TipoMediaModel(EntityManagerFactory emf) {
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

    public void create(NomTipoMedia nomTipoMedia) {
        if (nomTipoMedia.getDatGaleriaCollection() == null) {
            nomTipoMedia.setDatGaleriaCollection(new ArrayList<DatGaleria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DatGaleria> attachedDatGaleriaCollection = new ArrayList<DatGaleria>();
            for (DatGaleria datGaleriaCollectionDatGaleriaToAttach : nomTipoMedia.getDatGaleriaCollection()) {
                datGaleriaCollectionDatGaleriaToAttach = em.getReference(datGaleriaCollectionDatGaleriaToAttach.getClass(), datGaleriaCollectionDatGaleriaToAttach.getIdmedia());
                attachedDatGaleriaCollection.add(datGaleriaCollectionDatGaleriaToAttach);
            }
            nomTipoMedia.setDatGaleriaCollection(attachedDatGaleriaCollection);
            em.persist(nomTipoMedia);
            for (DatGaleria datGaleriaCollectionDatGaleria : nomTipoMedia.getDatGaleriaCollection()) {
                NomTipoMedia oldIdtipomediaOfDatGaleriaCollectionDatGaleria = datGaleriaCollectionDatGaleria.getIdtipomedia();
                datGaleriaCollectionDatGaleria.setIdtipomedia(nomTipoMedia);
                datGaleriaCollectionDatGaleria = em.merge(datGaleriaCollectionDatGaleria);
                if (oldIdtipomediaOfDatGaleriaCollectionDatGaleria != null) {
                    oldIdtipomediaOfDatGaleriaCollectionDatGaleria.getDatGaleriaCollection().remove(datGaleriaCollectionDatGaleria);
                    oldIdtipomediaOfDatGaleriaCollectionDatGaleria = em.merge(oldIdtipomediaOfDatGaleriaCollectionDatGaleria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomTipoMedia nomTipoMedia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomTipoMedia persistentNomTipoMedia = em.find(NomTipoMedia.class, nomTipoMedia.getIdtipomedia());
            Collection<DatGaleria> datGaleriaCollectionOld = persistentNomTipoMedia.getDatGaleriaCollection();
            Collection<DatGaleria> datGaleriaCollectionNew = nomTipoMedia.getDatGaleriaCollection();
            List<String> illegalOrphanMessages = null;
            for (DatGaleria datGaleriaCollectionOldDatGaleria : datGaleriaCollectionOld) {
                if (!datGaleriaCollectionNew.contains(datGaleriaCollectionOldDatGaleria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatGaleria " + datGaleriaCollectionOldDatGaleria + " since its idtipomedia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<DatGaleria> attachedDatGaleriaCollectionNew = new ArrayList<DatGaleria>();
            for (DatGaleria datGaleriaCollectionNewDatGaleriaToAttach : datGaleriaCollectionNew) {
                datGaleriaCollectionNewDatGaleriaToAttach = em.getReference(datGaleriaCollectionNewDatGaleriaToAttach.getClass(), datGaleriaCollectionNewDatGaleriaToAttach.getIdmedia());
                attachedDatGaleriaCollectionNew.add(datGaleriaCollectionNewDatGaleriaToAttach);
            }
            datGaleriaCollectionNew = attachedDatGaleriaCollectionNew;
            nomTipoMedia.setDatGaleriaCollection(datGaleriaCollectionNew);
            nomTipoMedia = em.merge(nomTipoMedia);
            for (DatGaleria datGaleriaCollectionNewDatGaleria : datGaleriaCollectionNew) {
                if (!datGaleriaCollectionOld.contains(datGaleriaCollectionNewDatGaleria)) {
                    NomTipoMedia oldIdtipomediaOfDatGaleriaCollectionNewDatGaleria = datGaleriaCollectionNewDatGaleria.getIdtipomedia();
                    datGaleriaCollectionNewDatGaleria.setIdtipomedia(nomTipoMedia);
                    datGaleriaCollectionNewDatGaleria = em.merge(datGaleriaCollectionNewDatGaleria);
                    if (oldIdtipomediaOfDatGaleriaCollectionNewDatGaleria != null && !oldIdtipomediaOfDatGaleriaCollectionNewDatGaleria.equals(nomTipoMedia)) {
                        oldIdtipomediaOfDatGaleriaCollectionNewDatGaleria.getDatGaleriaCollection().remove(datGaleriaCollectionNewDatGaleria);
                        oldIdtipomediaOfDatGaleriaCollectionNewDatGaleria = em.merge(oldIdtipomediaOfDatGaleriaCollectionNewDatGaleria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomTipoMedia.getIdtipomedia();
                if (findNomTipoMedia(id) == null) {
                    throw new NonexistentEntityException("The nomTipoMedia with id " + id + " no longer exists.");
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
            NomTipoMedia nomTipoMedia;
            try {
                nomTipoMedia = em.getReference(NomTipoMedia.class, id);
                nomTipoMedia.getIdtipomedia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomTipoMedia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DatGaleria> datGaleriaCollectionOrphanCheck = nomTipoMedia.getDatGaleriaCollection();
            for (DatGaleria datGaleriaCollectionOrphanCheckDatGaleria : datGaleriaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomTipoMedia (" + nomTipoMedia + ") cannot be destroyed since the DatGaleria " + datGaleriaCollectionOrphanCheckDatGaleria + " in its datGaleriaCollection field has a non-nullable idtipomedia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nomTipoMedia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomTipoMedia> findNomTipoMediaEntities() {
        return findNomTipoMediaEntities(true, -1, -1);
    }

    public List<NomTipoMedia> findNomTipoMediaEntities(int maxResults, int firstResult) {
        return findNomTipoMediaEntities(false, maxResults, firstResult);
    }

    private List<NomTipoMedia> findNomTipoMediaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomTipoMedia.class));
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

    public NomTipoMedia findNomTipoMedia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomTipoMedia.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomTipoMediaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomTipoMedia> rt = cq.from(NomTipoMedia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
