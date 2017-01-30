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
import sgc.escalada.mvc.entidades.DatAllround;
import sgc.escalada.mvc.entidades.NomCanton;
import sgc.escalada.mvc.entidades.NomFederacionProvincial;
import sgc.escalada.mvc.entidades.NomProvincia;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Ariam
 */
public class ProvinciaModel implements Serializable {

    public ProvinciaModel(EntityManagerFactory emf) {
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

    public void create(NomProvincia nomProvincia) {
        if (nomProvincia.getDatAllroundCollection() == null) {
            nomProvincia.setDatAllroundCollection(new ArrayList<DatAllround>());
        }
        if (nomProvincia.getNomFederacionProvincialCollection() == null) {
            nomProvincia.setNomFederacionProvincialCollection(new ArrayList<NomFederacionProvincial>());
        }
        if (nomProvincia.getNomCantonCollection() == null) {
            nomProvincia.setNomCantonCollection(new ArrayList<NomCanton>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DatAllround> attachedDatAllroundCollection = new ArrayList<DatAllround>();
            for (DatAllround datAllroundCollectionDatAllroundToAttach : nomProvincia.getDatAllroundCollection()) {
                datAllroundCollectionDatAllroundToAttach = em.getReference(datAllroundCollectionDatAllroundToAttach.getClass(), datAllroundCollectionDatAllroundToAttach.getDatAllroundPK());
                attachedDatAllroundCollection.add(datAllroundCollectionDatAllroundToAttach);
            }
            nomProvincia.setDatAllroundCollection(attachedDatAllroundCollection);
            Collection<NomFederacionProvincial> attachedNomFederacionProvincialCollection = new ArrayList<NomFederacionProvincial>();
            for (NomFederacionProvincial nomFederacionProvincialCollectionNomFederacionProvincialToAttach : nomProvincia.getNomFederacionProvincialCollection()) {
                nomFederacionProvincialCollectionNomFederacionProvincialToAttach = em.getReference(nomFederacionProvincialCollectionNomFederacionProvincialToAttach.getClass(), nomFederacionProvincialCollectionNomFederacionProvincialToAttach.getIdfederacion());
                attachedNomFederacionProvincialCollection.add(nomFederacionProvincialCollectionNomFederacionProvincialToAttach);
            }
            nomProvincia.setNomFederacionProvincialCollection(attachedNomFederacionProvincialCollection);
            Collection<NomCanton> attachedNomCantonCollection = new ArrayList<NomCanton>();
            for (NomCanton nomCantonCollectionNomCantonToAttach : nomProvincia.getNomCantonCollection()) {
                nomCantonCollectionNomCantonToAttach = em.getReference(nomCantonCollectionNomCantonToAttach.getClass(), nomCantonCollectionNomCantonToAttach.getIdcanton());
                attachedNomCantonCollection.add(nomCantonCollectionNomCantonToAttach);
            }
            nomProvincia.setNomCantonCollection(attachedNomCantonCollection);
            em.persist(nomProvincia);
            for (DatAllround datAllroundCollectionDatAllround : nomProvincia.getDatAllroundCollection()) {
                NomProvincia oldIdprovinciaOfDatAllroundCollectionDatAllround = datAllroundCollectionDatAllround.getIdprovincia();
                datAllroundCollectionDatAllround.setIdprovincia(nomProvincia);
                datAllroundCollectionDatAllround = em.merge(datAllroundCollectionDatAllround);
                if (oldIdprovinciaOfDatAllroundCollectionDatAllround != null) {
                    oldIdprovinciaOfDatAllroundCollectionDatAllround.getDatAllroundCollection().remove(datAllroundCollectionDatAllround);
                    oldIdprovinciaOfDatAllroundCollectionDatAllround = em.merge(oldIdprovinciaOfDatAllroundCollectionDatAllround);
                }
            }
            for (NomFederacionProvincial nomFederacionProvincialCollectionNomFederacionProvincial : nomProvincia.getNomFederacionProvincialCollection()) {
                NomProvincia oldIdprovinciaOfNomFederacionProvincialCollectionNomFederacionProvincial = nomFederacionProvincialCollectionNomFederacionProvincial.getIdprovincia();
                nomFederacionProvincialCollectionNomFederacionProvincial.setIdprovincia(nomProvincia);
                nomFederacionProvincialCollectionNomFederacionProvincial = em.merge(nomFederacionProvincialCollectionNomFederacionProvincial);
                if (oldIdprovinciaOfNomFederacionProvincialCollectionNomFederacionProvincial != null) {
                    oldIdprovinciaOfNomFederacionProvincialCollectionNomFederacionProvincial.getNomFederacionProvincialCollection().remove(nomFederacionProvincialCollectionNomFederacionProvincial);
                    oldIdprovinciaOfNomFederacionProvincialCollectionNomFederacionProvincial = em.merge(oldIdprovinciaOfNomFederacionProvincialCollectionNomFederacionProvincial);
                }
            }
            for (NomCanton nomCantonCollectionNomCanton : nomProvincia.getNomCantonCollection()) {
                NomProvincia oldIdprovinciaOfNomCantonCollectionNomCanton = nomCantonCollectionNomCanton.getIdprovincia();
                nomCantonCollectionNomCanton.setIdprovincia(nomProvincia);
                nomCantonCollectionNomCanton = em.merge(nomCantonCollectionNomCanton);
                if (oldIdprovinciaOfNomCantonCollectionNomCanton != null) {
                    oldIdprovinciaOfNomCantonCollectionNomCanton.getNomCantonCollection().remove(nomCantonCollectionNomCanton);
                    oldIdprovinciaOfNomCantonCollectionNomCanton = em.merge(oldIdprovinciaOfNomCantonCollectionNomCanton);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomProvincia nomProvincia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomProvincia persistentNomProvincia = em.find(NomProvincia.class, nomProvincia.getIdprovincia());
            Collection<DatAllround> datAllroundCollectionOld = persistentNomProvincia.getDatAllroundCollection();
            Collection<DatAllround> datAllroundCollectionNew = nomProvincia.getDatAllroundCollection();
            Collection<NomFederacionProvincial> nomFederacionProvincialCollectionOld = persistentNomProvincia.getNomFederacionProvincialCollection();
            Collection<NomFederacionProvincial> nomFederacionProvincialCollectionNew = nomProvincia.getNomFederacionProvincialCollection();
            Collection<NomCanton> nomCantonCollectionOld = persistentNomProvincia.getNomCantonCollection();
            Collection<NomCanton> nomCantonCollectionNew = nomProvincia.getNomCantonCollection();
            List<String> illegalOrphanMessages = null;
            for (DatAllround datAllroundCollectionOldDatAllround : datAllroundCollectionOld) {
                if (!datAllroundCollectionNew.contains(datAllroundCollectionOldDatAllround)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatAllround " + datAllroundCollectionOldDatAllround + " since its idprovincia field is not nullable.");
                }
            }
            for (NomFederacionProvincial nomFederacionProvincialCollectionOldNomFederacionProvincial : nomFederacionProvincialCollectionOld) {
                if (!nomFederacionProvincialCollectionNew.contains(nomFederacionProvincialCollectionOldNomFederacionProvincial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NomFederacionProvincial " + nomFederacionProvincialCollectionOldNomFederacionProvincial + " since its idprovincia field is not nullable.");
                }
            }
            for (NomCanton nomCantonCollectionOldNomCanton : nomCantonCollectionOld) {
                if (!nomCantonCollectionNew.contains(nomCantonCollectionOldNomCanton)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NomCanton " + nomCantonCollectionOldNomCanton + " since its idprovincia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<DatAllround> attachedDatAllroundCollectionNew = new ArrayList<DatAllround>();
            for (DatAllround datAllroundCollectionNewDatAllroundToAttach : datAllroundCollectionNew) {
                datAllroundCollectionNewDatAllroundToAttach = em.getReference(datAllroundCollectionNewDatAllroundToAttach.getClass(), datAllroundCollectionNewDatAllroundToAttach.getDatAllroundPK());
                attachedDatAllroundCollectionNew.add(datAllroundCollectionNewDatAllroundToAttach);
            }
            datAllroundCollectionNew = attachedDatAllroundCollectionNew;
            nomProvincia.setDatAllroundCollection(datAllroundCollectionNew);
            Collection<NomFederacionProvincial> attachedNomFederacionProvincialCollectionNew = new ArrayList<NomFederacionProvincial>();
            for (NomFederacionProvincial nomFederacionProvincialCollectionNewNomFederacionProvincialToAttach : nomFederacionProvincialCollectionNew) {
                nomFederacionProvincialCollectionNewNomFederacionProvincialToAttach = em.getReference(nomFederacionProvincialCollectionNewNomFederacionProvincialToAttach.getClass(), nomFederacionProvincialCollectionNewNomFederacionProvincialToAttach.getIdfederacion());
                attachedNomFederacionProvincialCollectionNew.add(nomFederacionProvincialCollectionNewNomFederacionProvincialToAttach);
            }
            nomFederacionProvincialCollectionNew = attachedNomFederacionProvincialCollectionNew;
            nomProvincia.setNomFederacionProvincialCollection(nomFederacionProvincialCollectionNew);
            Collection<NomCanton> attachedNomCantonCollectionNew = new ArrayList<NomCanton>();
            for (NomCanton nomCantonCollectionNewNomCantonToAttach : nomCantonCollectionNew) {
                nomCantonCollectionNewNomCantonToAttach = em.getReference(nomCantonCollectionNewNomCantonToAttach.getClass(), nomCantonCollectionNewNomCantonToAttach.getIdcanton());
                attachedNomCantonCollectionNew.add(nomCantonCollectionNewNomCantonToAttach);
            }
            nomCantonCollectionNew = attachedNomCantonCollectionNew;
            nomProvincia.setNomCantonCollection(nomCantonCollectionNew);
            nomProvincia = em.merge(nomProvincia);
            for (DatAllround datAllroundCollectionNewDatAllround : datAllroundCollectionNew) {
                if (!datAllroundCollectionOld.contains(datAllroundCollectionNewDatAllround)) {
                    NomProvincia oldIdprovinciaOfDatAllroundCollectionNewDatAllround = datAllroundCollectionNewDatAllround.getIdprovincia();
                    datAllroundCollectionNewDatAllround.setIdprovincia(nomProvincia);
                    datAllroundCollectionNewDatAllround = em.merge(datAllroundCollectionNewDatAllround);
                    if (oldIdprovinciaOfDatAllroundCollectionNewDatAllround != null && !oldIdprovinciaOfDatAllroundCollectionNewDatAllround.equals(nomProvincia)) {
                        oldIdprovinciaOfDatAllroundCollectionNewDatAllround.getDatAllroundCollection().remove(datAllroundCollectionNewDatAllround);
                        oldIdprovinciaOfDatAllroundCollectionNewDatAllround = em.merge(oldIdprovinciaOfDatAllroundCollectionNewDatAllround);
                    }
                }
            }
            for (NomFederacionProvincial nomFederacionProvincialCollectionNewNomFederacionProvincial : nomFederacionProvincialCollectionNew) {
                if (!nomFederacionProvincialCollectionOld.contains(nomFederacionProvincialCollectionNewNomFederacionProvincial)) {
                    NomProvincia oldIdprovinciaOfNomFederacionProvincialCollectionNewNomFederacionProvincial = nomFederacionProvincialCollectionNewNomFederacionProvincial.getIdprovincia();
                    nomFederacionProvincialCollectionNewNomFederacionProvincial.setIdprovincia(nomProvincia);
                    nomFederacionProvincialCollectionNewNomFederacionProvincial = em.merge(nomFederacionProvincialCollectionNewNomFederacionProvincial);
                    if (oldIdprovinciaOfNomFederacionProvincialCollectionNewNomFederacionProvincial != null && !oldIdprovinciaOfNomFederacionProvincialCollectionNewNomFederacionProvincial.equals(nomProvincia)) {
                        oldIdprovinciaOfNomFederacionProvincialCollectionNewNomFederacionProvincial.getNomFederacionProvincialCollection().remove(nomFederacionProvincialCollectionNewNomFederacionProvincial);
                        oldIdprovinciaOfNomFederacionProvincialCollectionNewNomFederacionProvincial = em.merge(oldIdprovinciaOfNomFederacionProvincialCollectionNewNomFederacionProvincial);
                    }
                }
            }
            for (NomCanton nomCantonCollectionNewNomCanton : nomCantonCollectionNew) {
                if (!nomCantonCollectionOld.contains(nomCantonCollectionNewNomCanton)) {
                    NomProvincia oldIdprovinciaOfNomCantonCollectionNewNomCanton = nomCantonCollectionNewNomCanton.getIdprovincia();
                    nomCantonCollectionNewNomCanton.setIdprovincia(nomProvincia);
                    nomCantonCollectionNewNomCanton = em.merge(nomCantonCollectionNewNomCanton);
                    if (oldIdprovinciaOfNomCantonCollectionNewNomCanton != null && !oldIdprovinciaOfNomCantonCollectionNewNomCanton.equals(nomProvincia)) {
                        oldIdprovinciaOfNomCantonCollectionNewNomCanton.getNomCantonCollection().remove(nomCantonCollectionNewNomCanton);
                        oldIdprovinciaOfNomCantonCollectionNewNomCanton = em.merge(oldIdprovinciaOfNomCantonCollectionNewNomCanton);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomProvincia.getIdprovincia();
                if (findNomProvincia(id) == null) {
                    throw new NonexistentEntityException("The nomProvincia with id " + id + " no longer exists.");
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
            NomProvincia nomProvincia;
            try {
                nomProvincia = em.getReference(NomProvincia.class, id);
                nomProvincia.getIdprovincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomProvincia with id " + id + " no longer exists.", enfe);
            }
//            List<String> illegalOrphanMessages = null;
//            Collection<DatAllround> datAllroundCollectionOrphanCheck = nomProvincia.getDatAllroundCollection();
//            for (DatAllround datAllroundCollectionOrphanCheckDatAllround : datAllroundCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomProvincia (" + nomProvincia + ") cannot be destroyed since the DatAllround " + datAllroundCollectionOrphanCheckDatAllround + " in its datAllroundCollection field has a non-nullable idprovincia field.");
//            }
//            Collection<NomFederacionProvincial> nomFederacionProvincialCollectionOrphanCheck = nomProvincia.getNomFederacionProvincialCollection();
//            for (NomFederacionProvincial nomFederacionProvincialCollectionOrphanCheckNomFederacionProvincial : nomFederacionProvincialCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomProvincia (" + nomProvincia + ") cannot be destroyed since the NomFederacionProvincial " + nomFederacionProvincialCollectionOrphanCheckNomFederacionProvincial + " in its nomFederacionProvincialCollection field has a non-nullable idprovincia field.");
//            }
//            Collection<NomCanton> nomCantonCollectionOrphanCheck = nomProvincia.getNomCantonCollection();
//            for (NomCanton nomCantonCollectionOrphanCheckNomCanton : nomCantonCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomProvincia (" + nomProvincia + ") cannot be destroyed since the NomCanton " + nomCantonCollectionOrphanCheckNomCanton + " in its nomCantonCollection field has a non-nullable idprovincia field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
            em.remove(nomProvincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomProvincia> findNomProvinciaEntities() {
        return findNomProvinciaEntities(true, -1, -1);
    }

    public List<NomProvincia> findNomProvinciaEntities(int maxResults, int firstResult) {
        return findNomProvinciaEntities(false, maxResults, firstResult);
    }

    private List<NomProvincia> findNomProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomProvincia.class));
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

    public NomProvincia findNomProvincia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomProvincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomProvincia> rt = cq.from(NomProvincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
