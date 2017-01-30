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
import sgc.escalada.mvc.entidades.DatDeportistaFederacion;
import sgc.escalada.mvc.entidades.NomFederacionProvincial;
import sgc.escalada.mvc.entidades.NomFederacionProvincial_;
import sgc.escalada.mvc.entidades.NomProvincia;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class FederacionProvincialModel implements Serializable {

    public FederacionProvincialModel(EntityManagerFactory emf) {
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

    public void create(NomFederacionProvincial nomFederacionProvincial) {
        if (nomFederacionProvincial.getDatDeportistaFederacionCollection() == null) {
            nomFederacionProvincial.setDatDeportistaFederacionCollection(new ArrayList<DatDeportistaFederacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomProvincia idprovincia = nomFederacionProvincial.getIdprovincia();
            if (idprovincia != null) {
                idprovincia = em.getReference(idprovincia.getClass(), idprovincia.getIdprovincia());
                nomFederacionProvincial.setIdprovincia(idprovincia);
            }
            Collection<DatDeportistaFederacion> attachedDatDeportistaFederacionCollection = new ArrayList<DatDeportistaFederacion>();
            for (DatDeportistaFederacion datDeportistaFederacionCollectionDatDeportistaFederacionToAttach : nomFederacionProvincial.getDatDeportistaFederacionCollection()) {
                datDeportistaFederacionCollectionDatDeportistaFederacionToAttach = em.getReference(datDeportistaFederacionCollectionDatDeportistaFederacionToAttach.getClass(), datDeportistaFederacionCollectionDatDeportistaFederacionToAttach.getDatDeportistaFederacionPK());
                attachedDatDeportistaFederacionCollection.add(datDeportistaFederacionCollectionDatDeportistaFederacionToAttach);
            }
            nomFederacionProvincial.setDatDeportistaFederacionCollection(attachedDatDeportistaFederacionCollection);
            em.persist(nomFederacionProvincial);
            if (idprovincia != null) {
                idprovincia.getNomFederacionProvincialCollection().add(nomFederacionProvincial);
                idprovincia = em.merge(idprovincia);
            }
            for (DatDeportistaFederacion datDeportistaFederacionCollectionDatDeportistaFederacion : nomFederacionProvincial.getDatDeportistaFederacionCollection()) {
                NomFederacionProvincial oldNomFederacionProvincialOfDatDeportistaFederacionCollectionDatDeportistaFederacion = datDeportistaFederacionCollectionDatDeportistaFederacion.getNomFederacionProvincial();
                datDeportistaFederacionCollectionDatDeportistaFederacion.setNomFederacionProvincial(nomFederacionProvincial);
                datDeportistaFederacionCollectionDatDeportistaFederacion = em.merge(datDeportistaFederacionCollectionDatDeportistaFederacion);
                if (oldNomFederacionProvincialOfDatDeportistaFederacionCollectionDatDeportistaFederacion != null) {
                    oldNomFederacionProvincialOfDatDeportistaFederacionCollectionDatDeportistaFederacion.getDatDeportistaFederacionCollection().remove(datDeportistaFederacionCollectionDatDeportistaFederacion);
                    oldNomFederacionProvincialOfDatDeportistaFederacionCollectionDatDeportistaFederacion = em.merge(oldNomFederacionProvincialOfDatDeportistaFederacionCollectionDatDeportistaFederacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomFederacionProvincial nomFederacionProvincial) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomFederacionProvincial persistentNomFederacionProvincial = em.find(NomFederacionProvincial.class, nomFederacionProvincial.getIdfederacion());
            NomProvincia idprovinciaOld = persistentNomFederacionProvincial.getIdprovincia();
            NomProvincia idprovinciaNew = nomFederacionProvincial.getIdprovincia();
            Collection<DatDeportistaFederacion> datDeportistaFederacionCollectionOld = persistentNomFederacionProvincial.getDatDeportistaFederacionCollection();
            Collection<DatDeportistaFederacion> datDeportistaFederacionCollectionNew = nomFederacionProvincial.getDatDeportistaFederacionCollection();
            List<String> illegalOrphanMessages = null;
            for (DatDeportistaFederacion datDeportistaFederacionCollectionOldDatDeportistaFederacion : datDeportistaFederacionCollectionOld) {
                if (!datDeportistaFederacionCollectionNew.contains(datDeportistaFederacionCollectionOldDatDeportistaFederacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatDeportistaFederacion " + datDeportistaFederacionCollectionOldDatDeportistaFederacion + " since its nomFederacionProvincial field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idprovinciaNew != null) {
                idprovinciaNew = em.getReference(idprovinciaNew.getClass(), idprovinciaNew.getIdprovincia());
                nomFederacionProvincial.setIdprovincia(idprovinciaNew);
            }
            Collection<DatDeportistaFederacion> attachedDatDeportistaFederacionCollectionNew = new ArrayList<DatDeportistaFederacion>();
            for (DatDeportistaFederacion datDeportistaFederacionCollectionNewDatDeportistaFederacionToAttach : datDeportistaFederacionCollectionNew) {
                datDeportistaFederacionCollectionNewDatDeportistaFederacionToAttach = em.getReference(datDeportistaFederacionCollectionNewDatDeportistaFederacionToAttach.getClass(), datDeportistaFederacionCollectionNewDatDeportistaFederacionToAttach.getDatDeportistaFederacionPK());
                attachedDatDeportistaFederacionCollectionNew.add(datDeportistaFederacionCollectionNewDatDeportistaFederacionToAttach);
            }
            datDeportistaFederacionCollectionNew = attachedDatDeportistaFederacionCollectionNew;
            nomFederacionProvincial.setDatDeportistaFederacionCollection(datDeportistaFederacionCollectionNew);
            nomFederacionProvincial = em.merge(nomFederacionProvincial);
            if (idprovinciaOld != null && !idprovinciaOld.equals(idprovinciaNew)) {
                idprovinciaOld.getNomFederacionProvincialCollection().remove(nomFederacionProvincial);
                idprovinciaOld = em.merge(idprovinciaOld);
            }
            if (idprovinciaNew != null && !idprovinciaNew.equals(idprovinciaOld)) {
                idprovinciaNew.getNomFederacionProvincialCollection().add(nomFederacionProvincial);
                idprovinciaNew = em.merge(idprovinciaNew);
            }
            for (DatDeportistaFederacion datDeportistaFederacionCollectionNewDatDeportistaFederacion : datDeportistaFederacionCollectionNew) {
                if (!datDeportistaFederacionCollectionOld.contains(datDeportistaFederacionCollectionNewDatDeportistaFederacion)) {
                    NomFederacionProvincial oldNomFederacionProvincialOfDatDeportistaFederacionCollectionNewDatDeportistaFederacion = datDeportistaFederacionCollectionNewDatDeportistaFederacion.getNomFederacionProvincial();
                    datDeportistaFederacionCollectionNewDatDeportistaFederacion.setNomFederacionProvincial(nomFederacionProvincial);
                    datDeportistaFederacionCollectionNewDatDeportistaFederacion = em.merge(datDeportistaFederacionCollectionNewDatDeportistaFederacion);
                    if (oldNomFederacionProvincialOfDatDeportistaFederacionCollectionNewDatDeportistaFederacion != null && !oldNomFederacionProvincialOfDatDeportistaFederacionCollectionNewDatDeportistaFederacion.equals(nomFederacionProvincial)) {
                        oldNomFederacionProvincialOfDatDeportistaFederacionCollectionNewDatDeportistaFederacion.getDatDeportistaFederacionCollection().remove(datDeportistaFederacionCollectionNewDatDeportistaFederacion);
                        oldNomFederacionProvincialOfDatDeportistaFederacionCollectionNewDatDeportistaFederacion = em.merge(oldNomFederacionProvincialOfDatDeportistaFederacionCollectionNewDatDeportistaFederacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomFederacionProvincial.getIdfederacion();
                if (findNomFederacionProvincial(id) == null) {
                    throw new NonexistentEntityException("The nomFederacionProvincial with id " + id + " no longer exists.");
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
            NomFederacionProvincial nomFederacionProvincial;
            try {
                nomFederacionProvincial = em.getReference(NomFederacionProvincial.class, id);
                nomFederacionProvincial.getIdfederacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomFederacionProvincial with id " + id + " no longer exists.", enfe);
            }
//            List<String> illegalOrphanMessages = null;
//            Collection<DatDeportistaFederacion> datDeportistaFederacionCollectionOrphanCheck = nomFederacionProvincial.getDatDeportistaFederacionCollection();
//            for (DatDeportistaFederacion datDeportistaFederacionCollectionOrphanCheckDatDeportistaFederacion : datDeportistaFederacionCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomFederacionProvincial (" + nomFederacionProvincial + ") cannot be destroyed since the DatDeportistaFederacion " + datDeportistaFederacionCollectionOrphanCheckDatDeportistaFederacion + " in its datDeportistaFederacionCollection field has a non-nullable nomFederacionProvincial field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
            NomProvincia idprovincia = nomFederacionProvincial.getIdprovincia();
            if (idprovincia != null) {
                idprovincia.getNomFederacionProvincialCollection().remove(nomFederacionProvincial);
                idprovincia = em.merge(idprovincia);
            }
            em.remove(nomFederacionProvincial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomFederacionProvincial> findNomFederacionProvincialEntities() {
        return findNomFederacionProvincialEntities(true, -1, -1);
    }

    public List<NomFederacionProvincial> findNomFederacionProvincialEntities(int maxResults, int firstResult) {
        return findNomFederacionProvincialEntities(false, maxResults, firstResult);
    }

    private List<NomFederacionProvincial> findNomFederacionProvincialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomFederacionProvincial.class));
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

    public NomFederacionProvincial findNomFederacionProvincial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomFederacionProvincial.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomFederacionProvincialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomFederacionProvincial> rt = cq.from(NomFederacionProvincial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<NomFederacionProvincial> findNomFederacionEntitiesByIdProvincia(NomProvincia nomProvincia) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<NomFederacionProvincial> cq = cb.createQuery(NomFederacionProvincial.class);
            Root<NomFederacionProvincial> pet = cq.from(NomFederacionProvincial.class);
            cq.where(pet.get(NomFederacionProvincial_.idprovincia).in(nomProvincia));
            Query q = em.createQuery(cq);

            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
