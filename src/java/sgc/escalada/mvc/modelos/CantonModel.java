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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sgc.escalada.mvc.entidades.NomCanton;
import sgc.escalada.mvc.entidades.NomCanton_;
import sgc.escalada.mvc.entidades.NomCiudad;

import sgc.escalada.mvc.entidades.NomProvincia;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Ariam
 */
public class CantonModel implements Serializable {

    public CantonModel(EntityManagerFactory emf) {
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

    public void create(NomCanton nomCanton) {
        if (nomCanton.getNomCiudadCollection() == null) {
            nomCanton.setNomCiudadCollection(new ArrayList<NomCiudad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomProvincia idprovincia = nomCanton.getIdprovincia();
            if (idprovincia != null) {
                idprovincia = em.getReference(idprovincia.getClass(), idprovincia.getIdprovincia());
                nomCanton.setIdprovincia(idprovincia);
            }
            Collection<NomCiudad> attachedNomCiudadCollection = new ArrayList<NomCiudad>();
            for (NomCiudad nomCiudadCollectionNomCiudadToAttach : nomCanton.getNomCiudadCollection()) {
                nomCiudadCollectionNomCiudadToAttach = em.getReference(nomCiudadCollectionNomCiudadToAttach.getClass(), nomCiudadCollectionNomCiudadToAttach.getIdciudad());
                attachedNomCiudadCollection.add(nomCiudadCollectionNomCiudadToAttach);
            }
            nomCanton.setNomCiudadCollection(attachedNomCiudadCollection);
            em.persist(nomCanton);
            if (idprovincia != null) {
                idprovincia.getNomCantonCollection().add(nomCanton);
                idprovincia = em.merge(idprovincia);
            }
            for (NomCiudad nomCiudadCollectionNomCiudad : nomCanton.getNomCiudadCollection()) {
                NomCanton oldIdcantonOfNomCiudadCollectionNomCiudad = nomCiudadCollectionNomCiudad.getIdcanton();
                nomCiudadCollectionNomCiudad.setIdcanton(nomCanton);
                nomCiudadCollectionNomCiudad = em.merge(nomCiudadCollectionNomCiudad);
                if (oldIdcantonOfNomCiudadCollectionNomCiudad != null) {
                    oldIdcantonOfNomCiudadCollectionNomCiudad.getNomCiudadCollection().remove(nomCiudadCollectionNomCiudad);
                    oldIdcantonOfNomCiudadCollectionNomCiudad = em.merge(oldIdcantonOfNomCiudadCollectionNomCiudad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomCanton nomCanton) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomCanton persistentNomCanton = em.find(NomCanton.class, nomCanton.getIdcanton());
            NomProvincia idprovinciaOld = persistentNomCanton.getIdprovincia();
            NomProvincia idprovinciaNew = nomCanton.getIdprovincia();
            Collection<NomCiudad> nomCiudadCollectionOld = persistentNomCanton.getNomCiudadCollection();
            Collection<NomCiudad> nomCiudadCollectionNew = nomCanton.getNomCiudadCollection();
            List<String> illegalOrphanMessages = null;
            for (NomCiudad nomCiudadCollectionOldNomCiudad : nomCiudadCollectionOld) {
                if (!nomCiudadCollectionNew.contains(nomCiudadCollectionOldNomCiudad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NomCiudad " + nomCiudadCollectionOldNomCiudad + " since its idcanton field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idprovinciaNew != null) {
                idprovinciaNew = em.getReference(idprovinciaNew.getClass(), idprovinciaNew.getIdprovincia());
                nomCanton.setIdprovincia(idprovinciaNew);
            }
            Collection<NomCiudad> attachedNomCiudadCollectionNew = new ArrayList<NomCiudad>();
            for (NomCiudad nomCiudadCollectionNewNomCiudadToAttach : nomCiudadCollectionNew) {
                nomCiudadCollectionNewNomCiudadToAttach = em.getReference(nomCiudadCollectionNewNomCiudadToAttach.getClass(), nomCiudadCollectionNewNomCiudadToAttach.getIdciudad());
                attachedNomCiudadCollectionNew.add(nomCiudadCollectionNewNomCiudadToAttach);
            }
            nomCiudadCollectionNew = attachedNomCiudadCollectionNew;
            nomCanton.setNomCiudadCollection(nomCiudadCollectionNew);
            nomCanton = em.merge(nomCanton);
            if (idprovinciaOld != null && !idprovinciaOld.equals(idprovinciaNew)) {
                idprovinciaOld.getNomCantonCollection().remove(nomCanton);
                idprovinciaOld = em.merge(idprovinciaOld);
            }
            if (idprovinciaNew != null && !idprovinciaNew.equals(idprovinciaOld)) {
                idprovinciaNew.getNomCantonCollection().add(nomCanton);
                idprovinciaNew = em.merge(idprovinciaNew);
            }
            for (NomCiudad nomCiudadCollectionNewNomCiudad : nomCiudadCollectionNew) {
                if (!nomCiudadCollectionOld.contains(nomCiudadCollectionNewNomCiudad)) {
                    NomCanton oldIdcantonOfNomCiudadCollectionNewNomCiudad = nomCiudadCollectionNewNomCiudad.getIdcanton();
                    nomCiudadCollectionNewNomCiudad.setIdcanton(nomCanton);
                    nomCiudadCollectionNewNomCiudad = em.merge(nomCiudadCollectionNewNomCiudad);
                    if (oldIdcantonOfNomCiudadCollectionNewNomCiudad != null && !oldIdcantonOfNomCiudadCollectionNewNomCiudad.equals(nomCanton)) {
                        oldIdcantonOfNomCiudadCollectionNewNomCiudad.getNomCiudadCollection().remove(nomCiudadCollectionNewNomCiudad);
                        oldIdcantonOfNomCiudadCollectionNewNomCiudad = em.merge(oldIdcantonOfNomCiudadCollectionNewNomCiudad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomCanton.getIdcanton();
                if (findNomCanton(id) == null) {
                    throw new NonexistentEntityException("The nomCanton with id " + id + " no longer exists.");
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
            NomCanton nomCanton;
            try {
                nomCanton = em.getReference(NomCanton.class, id);
                nomCanton.getIdcanton();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomCanton with id " + id + " no longer exists.", enfe);
            }
//            List<String> illegalOrphanMessages = null;
//            Collection<NomCiudad> nomCiudadCollectionOrphanCheck = nomCanton.getNomCiudadCollection();
//            for (NomCiudad nomCiudadCollectionOrphanCheckNomCiudad : nomCiudadCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomCanton (" + nomCanton + ") cannot be destroyed since the NomCiudad " + nomCiudadCollectionOrphanCheckNomCiudad + " in its nomCiudadCollection field has a non-nullable idcanton field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
            NomProvincia idprovincia = nomCanton.getIdprovincia();
            if (idprovincia != null) {
                idprovincia.getNomCantonCollection().remove(nomCanton);
                idprovincia = em.merge(idprovincia);
            }
            em.remove(nomCanton);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomCanton> findNomCantonEntities() {
        return findNomCantonEntities(true, -1, -1);
    }

    public List<NomCanton> findNomCantonEntities(int maxResults, int firstResult) {
        return findNomCantonEntities(false, maxResults, firstResult);
    }

    private List<NomCanton> findNomCantonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomCanton.class));
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

    public NomCanton findNomCanton(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomCanton.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomCantonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomCanton> rt = cq.from(NomCanton.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<NomCanton> findNomCantonEntitiesByIdProvincia(NomProvincia provincia) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<NomCanton> cq = cb.createQuery(NomCanton.class);
            Root<NomCanton> pet = cq.from(NomCanton.class);
            cq.where(pet.get(NomCanton_.idprovincia).in(provincia));
            Query q = em.createQuery(cq);

            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
