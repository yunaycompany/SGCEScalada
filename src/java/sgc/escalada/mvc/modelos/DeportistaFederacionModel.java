/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.entidades.DatDeportistaFederacion;
import sgc.escalada.mvc.entidades.DatDeportistaFederacionPK;
import sgc.escalada.mvc.entidades.NomFederacionProvincial;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;
import sgc.escalada.mvc.modelos.exceptions.PreexistingEntityException;

/**
 *
 * @author Yosbel
 */
public class DeportistaFederacionModel implements Serializable {

    public DeportistaFederacionModel(EntityManagerFactory emf) {
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

    public void create(DatDeportistaFederacion datDeportistaFederacion) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (datDeportistaFederacion.getDatDeportistaFederacionPK() == null) {
            datDeportistaFederacion.setDatDeportistaFederacionPK(new DatDeportistaFederacionPK());
        }
        datDeportistaFederacion.getDatDeportistaFederacionPK().setIdfederacion(datDeportistaFederacion.getNomFederacionProvincial().getIdfederacion());
        datDeportistaFederacion.getDatDeportistaFederacionPK().setIddeportista(datDeportistaFederacion.getDatDeportista().getIddeportista());
        List<String> illegalOrphanMessages = null;
        DatDeportista datDeportistaOrphanCheck = datDeportistaFederacion.getDatDeportista();
        if (datDeportistaOrphanCheck != null) {
            DatDeportistaFederacion oldDatDeportistaFederacionOfDatDeportista = datDeportistaOrphanCheck.getDatDeportistaFederacion();
            if (oldDatDeportistaFederacionOfDatDeportista != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The DatDeportista " + datDeportistaOrphanCheck + " already has an item of type DatDeportistaFederacion whose datDeportista column cannot be null. Please make another selection for the datDeportista field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomFederacionProvincial nomFederacionProvincial = datDeportistaFederacion.getNomFederacionProvincial();
            if (nomFederacionProvincial != null) {
                nomFederacionProvincial = em.getReference(nomFederacionProvincial.getClass(), nomFederacionProvincial.getIdfederacion());
                datDeportistaFederacion.setNomFederacionProvincial(nomFederacionProvincial);
            }
            DatDeportista datDeportista = datDeportistaFederacion.getDatDeportista();
            if (datDeportista != null) {
                datDeportista = em.getReference(datDeportista.getClass(), datDeportista.getIddeportista());
                datDeportistaFederacion.setDatDeportista(datDeportista);
            }
            em.persist(datDeportistaFederacion);
            if (nomFederacionProvincial != null) {
                nomFederacionProvincial.getDatDeportistaFederacionCollection().add(datDeportistaFederacion);
                nomFederacionProvincial = em.merge(nomFederacionProvincial);
            }
            if (datDeportista != null) {
                datDeportista.setDatDeportistaFederacion(datDeportistaFederacion);
                datDeportista = em.merge(datDeportista);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatDeportistaFederacion(datDeportistaFederacion.getDatDeportistaFederacionPK()) != null) {
                throw new PreexistingEntityException("DatDeportistaFederacion " + datDeportistaFederacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatDeportistaFederacion datDeportistaFederacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        datDeportistaFederacion.getDatDeportistaFederacionPK().setIdfederacion(datDeportistaFederacion.getNomFederacionProvincial().getIdfederacion());
        datDeportistaFederacion.getDatDeportistaFederacionPK().setIddeportista(datDeportistaFederacion.getDatDeportista().getIddeportista());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatDeportistaFederacion persistentDatDeportistaFederacion = em.find(DatDeportistaFederacion.class, datDeportistaFederacion.getDatDeportistaFederacionPK());
            NomFederacionProvincial nomFederacionProvincialOld = persistentDatDeportistaFederacion.getNomFederacionProvincial();
            NomFederacionProvincial nomFederacionProvincialNew = datDeportistaFederacion.getNomFederacionProvincial();
            DatDeportista datDeportistaOld = persistentDatDeportistaFederacion.getDatDeportista();
            DatDeportista datDeportistaNew = datDeportistaFederacion.getDatDeportista();
            List<String> illegalOrphanMessages = null;
            if (datDeportistaNew != null && !datDeportistaNew.equals(datDeportistaOld)) {
                DatDeportistaFederacion oldDatDeportistaFederacionOfDatDeportista = datDeportistaNew.getDatDeportistaFederacion();
                if (oldDatDeportistaFederacionOfDatDeportista != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The DatDeportista " + datDeportistaNew + " already has an item of type DatDeportistaFederacion whose datDeportista column cannot be null. Please make another selection for the datDeportista field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nomFederacionProvincialNew != null) {
                nomFederacionProvincialNew = em.getReference(nomFederacionProvincialNew.getClass(), nomFederacionProvincialNew.getIdfederacion());
                datDeportistaFederacion.setNomFederacionProvincial(nomFederacionProvincialNew);
            }
            if (datDeportistaNew != null) {
                datDeportistaNew = em.getReference(datDeportistaNew.getClass(), datDeportistaNew.getIddeportista());
                datDeportistaFederacion.setDatDeportista(datDeportistaNew);
            }
            datDeportistaFederacion = em.merge(datDeportistaFederacion);
            if (nomFederacionProvincialOld != null && !nomFederacionProvincialOld.equals(nomFederacionProvincialNew)) {
                nomFederacionProvincialOld.getDatDeportistaFederacionCollection().remove(datDeportistaFederacion);
                nomFederacionProvincialOld = em.merge(nomFederacionProvincialOld);
            }
            if (nomFederacionProvincialNew != null && !nomFederacionProvincialNew.equals(nomFederacionProvincialOld)) {
                nomFederacionProvincialNew.getDatDeportistaFederacionCollection().add(datDeportistaFederacion);
                nomFederacionProvincialNew = em.merge(nomFederacionProvincialNew);
            }
            if (datDeportistaOld != null && !datDeportistaOld.equals(datDeportistaNew)) {
                datDeportistaOld.setDatDeportistaFederacion(null);
                datDeportistaOld = em.merge(datDeportistaOld);
            }
            if (datDeportistaNew != null && !datDeportistaNew.equals(datDeportistaOld)) {
                datDeportistaNew.setDatDeportistaFederacion(datDeportistaFederacion);
                datDeportistaNew = em.merge(datDeportistaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DatDeportistaFederacionPK id = datDeportistaFederacion.getDatDeportistaFederacionPK();
                if (findDatDeportistaFederacion(id) == null) {
                    throw new NonexistentEntityException("The datDeportistaFederacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DatDeportistaFederacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatDeportistaFederacion datDeportistaFederacion;
            try {
                datDeportistaFederacion = em.getReference(DatDeportistaFederacion.class, id);
                datDeportistaFederacion.getDatDeportistaFederacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datDeportistaFederacion with id " + id + " no longer exists.", enfe);
            }
            NomFederacionProvincial nomFederacionProvincial = datDeportistaFederacion.getNomFederacionProvincial();
            if (nomFederacionProvincial != null) {
                nomFederacionProvincial.getDatDeportistaFederacionCollection().remove(datDeportistaFederacion);
                nomFederacionProvincial = em.merge(nomFederacionProvincial);
            }
            DatDeportista datDeportista = datDeportistaFederacion.getDatDeportista();
            if (datDeportista != null) {
                datDeportista.setDatDeportistaFederacion(null);
                datDeportista = em.merge(datDeportista);
            }
            em.remove(datDeportistaFederacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatDeportistaFederacion> findDatDeportistaFederacionEntities() {
        return findDatDeportistaFederacionEntities(true, -1, -1);
    }

    public List<DatDeportistaFederacion> findDatDeportistaFederacionEntities(int maxResults, int firstResult) {
        return findDatDeportistaFederacionEntities(false, maxResults, firstResult);
    }

    private List<DatDeportistaFederacion> findDatDeportistaFederacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatDeportistaFederacion.class));
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

    public DatDeportistaFederacion findDatDeportistaFederacion(DatDeportistaFederacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatDeportistaFederacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatDeportistaFederacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatDeportistaFederacion> rt = cq.from(DatDeportistaFederacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
