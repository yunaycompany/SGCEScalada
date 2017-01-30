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
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.entidades.DatDireccion;
import sgc.escalada.mvc.entidades.DatDireccionPK;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;
import sgc.escalada.mvc.modelos.exceptions.PreexistingEntityException;

/**
 *
 * @author Ariam
 */
public class DireccionModel implements Serializable {

    public DireccionModel(EntityManagerFactory emf) {
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

    public void create(DatDireccion datDireccion) throws PreexistingEntityException, Exception {
        if (datDireccion.getDatDireccionPK() == null) {
            datDireccion.setDatDireccionPK(new DatDireccionPK());
        }
        datDireccion.getDatDireccionPK().setIdparroquia(datDireccion.getNomParroquia().getIdparroquia());
        datDireccion.getDatDireccionPK().setIddeportista(datDireccion.getDatDeportista().getIddeportista());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomParroquia nomParroquia = datDireccion.getNomParroquia();
            if (nomParroquia != null) {
                nomParroquia = em.getReference(nomParroquia.getClass(), nomParroquia.getIdparroquia());
                datDireccion.setNomParroquia(nomParroquia);
            }
            DatDeportista datDeportista = datDireccion.getDatDeportista();
            if (datDeportista != null) {
                datDeportista = em.getReference(datDeportista.getClass(), datDeportista.getIddeportista());
                datDireccion.setDatDeportista(datDeportista);
            }
            em.persist(datDireccion);
            if (nomParroquia != null) {
                nomParroquia.getDatDireccionCollection().add(datDireccion);
                nomParroquia = em.merge(nomParroquia);
            }
            if (datDeportista != null) {
                datDeportista.getDatDireccionCollection().add(datDireccion);
                datDeportista = em.merge(datDeportista);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatDireccion(datDireccion.getDatDireccionPK()) != null) {
                throw new PreexistingEntityException("DatDireccion " + datDireccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatDireccion datDireccion) throws NonexistentEntityException, Exception {
        datDireccion.getDatDireccionPK().setIdparroquia(datDireccion.getNomParroquia().getIdparroquia());
        datDireccion.getDatDireccionPK().setIddeportista(datDireccion.getDatDeportista().getIddeportista());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatDireccion persistentDatDireccion = em.find(DatDireccion.class, datDireccion.getDatDireccionPK());
            NomParroquia nomParroquiaOld = persistentDatDireccion.getNomParroquia();
            NomParroquia nomParroquiaNew = datDireccion.getNomParroquia();
            DatDeportista datDeportistaOld = persistentDatDireccion.getDatDeportista();
            DatDeportista datDeportistaNew = datDireccion.getDatDeportista();
            if (nomParroquiaNew != null) {
                nomParroquiaNew = em.getReference(nomParroquiaNew.getClass(), nomParroquiaNew.getIdparroquia());
                datDireccion.setNomParroquia(nomParroquiaNew);
            }
            if (datDeportistaNew != null) {
                datDeportistaNew = em.getReference(datDeportistaNew.getClass(), datDeportistaNew.getIddeportista());
                datDireccion.setDatDeportista(datDeportistaNew);
            }
            datDireccion = em.merge(datDireccion);
            if (nomParroquiaOld != null && !nomParroquiaOld.equals(nomParroquiaNew)) {
                nomParroquiaOld.getDatDireccionCollection().remove(datDireccion);
                nomParroquiaOld = em.merge(nomParroquiaOld);
            }
            if (nomParroquiaNew != null && !nomParroquiaNew.equals(nomParroquiaOld)) {
                nomParroquiaNew.getDatDireccionCollection().add(datDireccion);
                nomParroquiaNew = em.merge(nomParroquiaNew);
            }
            if (datDeportistaOld != null && !datDeportistaOld.equals(datDeportistaNew)) {
                datDeportistaOld.getDatDireccionCollection().remove(datDireccion);
                datDeportistaOld = em.merge(datDeportistaOld);
            }
            if (datDeportistaNew != null && !datDeportistaNew.equals(datDeportistaOld)) {
                datDeportistaNew.getDatDireccionCollection().add(datDireccion);
                datDeportistaNew = em.merge(datDeportistaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DatDireccionPK id = datDireccion.getDatDireccionPK();
                if (findDatDireccion(id) == null) {
                    throw new NonexistentEntityException("The datDireccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DatDireccionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatDireccion datDireccion;
            try {
                datDireccion = em.getReference(DatDireccion.class, id);
                datDireccion.getDatDireccionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datDireccion with id " + id + " no longer exists.", enfe);
            }
            NomParroquia nomParroquia = datDireccion.getNomParroquia();
            if (nomParroquia != null) {
                nomParroquia.getDatDireccionCollection().remove(datDireccion);
                nomParroquia = em.merge(nomParroquia);
            }
            DatDeportista datDeportista = datDireccion.getDatDeportista();
            if (datDeportista != null) {
                datDeportista.getDatDireccionCollection().remove(datDireccion);
                datDeportista = em.merge(datDeportista);
            }
            em.remove(datDireccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatDireccion> findDatDireccionEntities() {
        return findDatDireccionEntities(true, -1, -1);
    }

    public List<DatDireccion> findDatDireccionEntities(int maxResults, int firstResult) {
        return findDatDireccionEntities(false, maxResults, firstResult);
    }

    private List<DatDireccion> findDatDireccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatDireccion.class));
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

    public DatDireccion findDatDireccion(DatDireccionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatDireccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatDireccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatDireccion> rt = cq.from(DatDireccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
