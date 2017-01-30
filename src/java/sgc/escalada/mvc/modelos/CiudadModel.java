/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.modelos;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sgc.escalada.mvc.entidades.NomCanton;
import sgc.escalada.mvc.entidades.NomParroquia;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import sgc.escalada.mvc.entidades.NomCanton_;
import sgc.escalada.mvc.entidades.NomCiudad;
import sgc.escalada.mvc.entidades.NomCiudad_;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class CiudadModel implements Serializable {

    public CiudadModel(EntityManagerFactory emf) {
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

    public void create(NomCiudad nomCiudad) {
        if (nomCiudad.getNomParroquiaCollection() == null) {
            nomCiudad.setNomParroquiaCollection(new ArrayList<NomParroquia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomCanton idcanton = nomCiudad.getIdcanton();
            if (idcanton != null) {
                idcanton = em.getReference(idcanton.getClass(), idcanton.getIdcanton());
                nomCiudad.setIdcanton(idcanton);
            }
            Collection<NomParroquia> attachedNomParroquiaCollection = new ArrayList<NomParroquia>();
            for (NomParroquia nomParroquiaCollectionNomParroquiaToAttach : nomCiudad.getNomParroquiaCollection()) {
                nomParroquiaCollectionNomParroquiaToAttach = em.getReference(nomParroquiaCollectionNomParroquiaToAttach.getClass(), nomParroquiaCollectionNomParroquiaToAttach.getIdparroquia());
                attachedNomParroquiaCollection.add(nomParroquiaCollectionNomParroquiaToAttach);
            }
            nomCiudad.setNomParroquiaCollection(attachedNomParroquiaCollection);
            em.persist(nomCiudad);
            if (idcanton != null) {
                idcanton.getNomCiudadCollection().add(nomCiudad);
                idcanton = em.merge(idcanton);
            }
            for (NomParroquia nomParroquiaCollectionNomParroquia : nomCiudad.getNomParroquiaCollection()) {
                NomCiudad oldIdciudadOfNomParroquiaCollectionNomParroquia = nomParroquiaCollectionNomParroquia.getIdciudad();
                nomParroquiaCollectionNomParroquia.setIdciudad(nomCiudad);
                nomParroquiaCollectionNomParroquia = em.merge(nomParroquiaCollectionNomParroquia);
                if (oldIdciudadOfNomParroquiaCollectionNomParroquia != null) {
                    oldIdciudadOfNomParroquiaCollectionNomParroquia.getNomParroquiaCollection().remove(nomParroquiaCollectionNomParroquia);
                    oldIdciudadOfNomParroquiaCollectionNomParroquia = em.merge(oldIdciudadOfNomParroquiaCollectionNomParroquia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomCiudad nomCiudad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomCiudad persistentNomCiudad = em.find(NomCiudad.class, nomCiudad.getIdciudad());
            NomCanton idcantonOld = persistentNomCiudad.getIdcanton();
            NomCanton idcantonNew = nomCiudad.getIdcanton();
            Collection<NomParroquia> nomParroquiaCollectionOld = persistentNomCiudad.getNomParroquiaCollection();
            Collection<NomParroquia> nomParroquiaCollectionNew = nomCiudad.getNomParroquiaCollection();
            List<String> illegalOrphanMessages = null;
            for (NomParroquia nomParroquiaCollectionOldNomParroquia : nomParroquiaCollectionOld) {
                if (!nomParroquiaCollectionNew.contains(nomParroquiaCollectionOldNomParroquia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NomParroquia " + nomParroquiaCollectionOldNomParroquia + " since its idciudad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcantonNew != null) {
                idcantonNew = em.getReference(idcantonNew.getClass(), idcantonNew.getIdcanton());
                nomCiudad.setIdcanton(idcantonNew);
            }
            Collection<NomParroquia> attachedNomParroquiaCollectionNew = new ArrayList<NomParroquia>();
            for (NomParroquia nomParroquiaCollectionNewNomParroquiaToAttach : nomParroquiaCollectionNew) {
                nomParroquiaCollectionNewNomParroquiaToAttach = em.getReference(nomParroquiaCollectionNewNomParroquiaToAttach.getClass(), nomParroquiaCollectionNewNomParroquiaToAttach.getIdparroquia());
                attachedNomParroquiaCollectionNew.add(nomParroquiaCollectionNewNomParroquiaToAttach);
            }
            nomParroquiaCollectionNew = attachedNomParroquiaCollectionNew;
            nomCiudad.setNomParroquiaCollection(nomParroquiaCollectionNew);
            nomCiudad = em.merge(nomCiudad);
            if (idcantonOld != null && !idcantonOld.equals(idcantonNew)) {
                idcantonOld.getNomCiudadCollection().remove(nomCiudad);
                idcantonOld = em.merge(idcantonOld);
            }
            if (idcantonNew != null && !idcantonNew.equals(idcantonOld)) {
                idcantonNew.getNomCiudadCollection().add(nomCiudad);
                idcantonNew = em.merge(idcantonNew);
            }
            for (NomParroquia nomParroquiaCollectionNewNomParroquia : nomParroquiaCollectionNew) {
                if (!nomParroquiaCollectionOld.contains(nomParroquiaCollectionNewNomParroquia)) {
                    NomCiudad oldIdciudadOfNomParroquiaCollectionNewNomParroquia = nomParroquiaCollectionNewNomParroquia.getIdciudad();
                    nomParroquiaCollectionNewNomParroquia.setIdciudad(nomCiudad);
                    nomParroquiaCollectionNewNomParroquia = em.merge(nomParroquiaCollectionNewNomParroquia);
                    if (oldIdciudadOfNomParroquiaCollectionNewNomParroquia != null && !oldIdciudadOfNomParroquiaCollectionNewNomParroquia.equals(nomCiudad)) {
                        oldIdciudadOfNomParroquiaCollectionNewNomParroquia.getNomParroquiaCollection().remove(nomParroquiaCollectionNewNomParroquia);
                        oldIdciudadOfNomParroquiaCollectionNewNomParroquia = em.merge(oldIdciudadOfNomParroquiaCollectionNewNomParroquia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomCiudad.getIdciudad();
                if (findNomCiudad(id) == null) {
                    throw new NonexistentEntityException("The nomCiudad with id " + id + " no longer exists.");
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
            NomCiudad nomCiudad;
            try {
                nomCiudad = em.getReference(NomCiudad.class, id);
                nomCiudad.getIdciudad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomCiudad with id " + id + " no longer exists.", enfe);
            }
//            List<String> illegalOrphanMessages = null;
//            Collection<NomParroquia> nomParroquiaCollectionOrphanCheck = nomCiudad.getNomParroquiaCollection();
//            for (NomParroquia nomParroquiaCollectionOrphanCheckNomParroquia : nomParroquiaCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomCiudad (" + nomCiudad + ") cannot be destroyed since the NomParroquia " + nomParroquiaCollectionOrphanCheckNomParroquia + " in its nomParroquiaCollection field has a non-nullable idciudad field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
            NomCanton idcanton = nomCiudad.getIdcanton();
            if (idcanton != null) {
                idcanton.getNomCiudadCollection().remove(nomCiudad);
                idcanton = em.merge(idcanton);
            }
            em.remove(nomCiudad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomCiudad> findNomCiudadEntities() {
        return findNomCiudadEntities(true, -1, -1);
    }

    public List<NomCiudad> findNomCiudadEntities(int maxResults, int firstResult) {
        return findNomCiudadEntities(false, maxResults, firstResult);
    }

    private List<NomCiudad> findNomCiudadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomCiudad.class));
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

    public NomCiudad findNomCiudad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomCiudad.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomCiudadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomCiudad> rt = cq.from(NomCiudad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<NomCiudad> findNomCiudadEntitiesByIdCanton(NomCanton nomCanton) {
        EntityManager em = getEntityManager();
        try{
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<NomCiudad> cq = cb.createQuery(NomCiudad.class);
            Root<NomCiudad> pet = cq.from(NomCiudad.class);
            cq.where(pet.get(NomCiudad_.idcanton).in(nomCanton));
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        }finally{
            em.close();
        }
    }
    
}
