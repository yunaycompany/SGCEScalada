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
import sgc.escalada.mvc.entidades.NomTipoSangre;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class TipoSangreModel implements Serializable {

    public TipoSangreModel(EntityManagerFactory emf) {
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

    public void create(NomTipoSangre nomTipoSangre) {
        if (nomTipoSangre.getDatDeportistaCollection() == null) {
            nomTipoSangre.setDatDeportistaCollection(new ArrayList<DatDeportista>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DatDeportista> attachedDatDeportistaCollection = new ArrayList<DatDeportista>();
            for (DatDeportista datDeportistaCollectionDatDeportistaToAttach : nomTipoSangre.getDatDeportistaCollection()) {
                datDeportistaCollectionDatDeportistaToAttach = em.getReference(datDeportistaCollectionDatDeportistaToAttach.getClass(), datDeportistaCollectionDatDeportistaToAttach.getIddeportista());
                attachedDatDeportistaCollection.add(datDeportistaCollectionDatDeportistaToAttach);
            }
            nomTipoSangre.setDatDeportistaCollection(attachedDatDeportistaCollection);
            em.persist(nomTipoSangre);
            for (DatDeportista datDeportistaCollectionDatDeportista : nomTipoSangre.getDatDeportistaCollection()) {
                NomTipoSangre oldIdtiposangreOfDatDeportistaCollectionDatDeportista = datDeportistaCollectionDatDeportista.getIdtiposangre();
                datDeportistaCollectionDatDeportista.setIdtiposangre(nomTipoSangre);
                datDeportistaCollectionDatDeportista = em.merge(datDeportistaCollectionDatDeportista);
                if (oldIdtiposangreOfDatDeportistaCollectionDatDeportista != null) {
                    oldIdtiposangreOfDatDeportistaCollectionDatDeportista.getDatDeportistaCollection().remove(datDeportistaCollectionDatDeportista);
                    oldIdtiposangreOfDatDeportistaCollectionDatDeportista = em.merge(oldIdtiposangreOfDatDeportistaCollectionDatDeportista);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomTipoSangre nomTipoSangre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomTipoSangre persistentNomTipoSangre = em.find(NomTipoSangre.class, nomTipoSangre.getIdtiposangre());
            Collection<DatDeportista> datDeportistaCollectionOld = persistentNomTipoSangre.getDatDeportistaCollection();
            Collection<DatDeportista> datDeportistaCollectionNew = nomTipoSangre.getDatDeportistaCollection();
            List<String> illegalOrphanMessages = null;
            for (DatDeportista datDeportistaCollectionOldDatDeportista : datDeportistaCollectionOld) {
                if (!datDeportistaCollectionNew.contains(datDeportistaCollectionOldDatDeportista)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatDeportista " + datDeportistaCollectionOldDatDeportista + " since its idtiposangre field is not nullable.");
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
            nomTipoSangre.setDatDeportistaCollection(datDeportistaCollectionNew);
            nomTipoSangre = em.merge(nomTipoSangre);
            for (DatDeportista datDeportistaCollectionNewDatDeportista : datDeportistaCollectionNew) {
                if (!datDeportistaCollectionOld.contains(datDeportistaCollectionNewDatDeportista)) {
                    NomTipoSangre oldIdtiposangreOfDatDeportistaCollectionNewDatDeportista = datDeportistaCollectionNewDatDeportista.getIdtiposangre();
                    datDeportistaCollectionNewDatDeportista.setIdtiposangre(nomTipoSangre);
                    datDeportistaCollectionNewDatDeportista = em.merge(datDeportistaCollectionNewDatDeportista);
                    if (oldIdtiposangreOfDatDeportistaCollectionNewDatDeportista != null && !oldIdtiposangreOfDatDeportistaCollectionNewDatDeportista.equals(nomTipoSangre)) {
                        oldIdtiposangreOfDatDeportistaCollectionNewDatDeportista.getDatDeportistaCollection().remove(datDeportistaCollectionNewDatDeportista);
                        oldIdtiposangreOfDatDeportistaCollectionNewDatDeportista = em.merge(oldIdtiposangreOfDatDeportistaCollectionNewDatDeportista);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomTipoSangre.getIdtiposangre();
                if (findNomTipoSangre(id) == null) {
                    throw new NonexistentEntityException("The nomTipoSangre with id " + id + " no longer exists.");
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
            NomTipoSangre nomTipoSangre;
            try {
                nomTipoSangre = em.getReference(NomTipoSangre.class, id);
                nomTipoSangre.getIdtiposangre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomTipoSangre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DatDeportista> datDeportistaCollectionOrphanCheck = nomTipoSangre.getDatDeportistaCollection();
            for (DatDeportista datDeportistaCollectionOrphanCheckDatDeportista : datDeportistaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomTipoSangre (" + nomTipoSangre + ") cannot be destroyed since the DatDeportista " + datDeportistaCollectionOrphanCheckDatDeportista + " in its datDeportistaCollection field has a non-nullable idtiposangre field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nomTipoSangre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomTipoSangre> findNomTipoSangreEntities() {
        return findNomTipoSangreEntities(true, -1, -1);
    }

    public List<NomTipoSangre> findNomTipoSangreEntities(int maxResults, int firstResult) {
        return findNomTipoSangreEntities(false, maxResults, firstResult);
    }

    private List<NomTipoSangre> findNomTipoSangreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomTipoSangre.class));
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

    public NomTipoSangre findNomTipoSangre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomTipoSangre.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomTipoSangreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomTipoSangre> rt = cq.from(NomTipoSangre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
