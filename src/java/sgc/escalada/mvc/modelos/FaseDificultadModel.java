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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.entidades.DatFaseDificultad;
import sgc.escalada.mvc.entidades.DatFaseDificultadPK;
import sgc.escalada.mvc.entidades.DatFaseDificultad_;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;
import sgc.escalada.mvc.modelos.exceptions.PreexistingEntityException;

/**
 *
 * @author Yosbel
 */
public class FaseDificultadModel implements Serializable {

    public FaseDificultadModel(EntityManagerFactory emf) {
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

    public void create(DatFaseDificultad datFaseDificultad) throws PreexistingEntityException, Exception {
        if (datFaseDificultad.getDatFaseDificultadPK() == null) {
            datFaseDificultad.setDatFaseDificultadPK(new DatFaseDificultadPK());
        }
        datFaseDificultad.getDatFaseDificultadPK().setIdcompetencia(datFaseDificultad.getDatCompetencia().getIdcompetencia());
        datFaseDificultad.getDatFaseDificultadPK().setIddeportista(datFaseDificultad.getDatDeportista().getIddeportista());
        datFaseDificultad.getDatFaseDificultadPK().setIdcategoria(datFaseDificultad.getNomCategoria().getIdcategoria());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomCategoria nomCategoria = datFaseDificultad.getNomCategoria();
            if (nomCategoria != null) {
                nomCategoria = em.getReference(nomCategoria.getClass(), nomCategoria.getIdcategoria());
                datFaseDificultad.setNomCategoria(nomCategoria);
            }
            DatDeportista datDeportista = datFaseDificultad.getDatDeportista();
            if (datDeportista != null) {
                datDeportista = em.getReference(datDeportista.getClass(), datDeportista.getIddeportista());
                datFaseDificultad.setDatDeportista(datDeportista);
            }
            DatCompetencia datCompetencia = datFaseDificultad.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia = em.getReference(datCompetencia.getClass(), datCompetencia.getIdcompetencia());
                datFaseDificultad.setDatCompetencia(datCompetencia);
            }
            em.persist(datFaseDificultad);
            if (nomCategoria != null) {
                nomCategoria.getDatFaseDificultadCollection().add(datFaseDificultad);
                nomCategoria = em.merge(nomCategoria);
            }
            if (datDeportista != null) {
                datDeportista.getDatFaseDificultadCollection().add(datFaseDificultad);
                datDeportista = em.merge(datDeportista);
            }
            if (datCompetencia != null) {
                datCompetencia.getDatFaseDificultadCollection().add(datFaseDificultad);
                datCompetencia = em.merge(datCompetencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatFaseDificultad(datFaseDificultad.getDatFaseDificultadPK()) != null) {
                throw new PreexistingEntityException("DatFaseDificultad " + datFaseDificultad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatFaseDificultad datFaseDificultad) throws NonexistentEntityException, Exception {
        datFaseDificultad.getDatFaseDificultadPK().setIdcompetencia(datFaseDificultad.getDatCompetencia().getIdcompetencia());
        datFaseDificultad.getDatFaseDificultadPK().setIddeportista(datFaseDificultad.getDatDeportista().getIddeportista());
        datFaseDificultad.getDatFaseDificultadPK().setIdcategoria(datFaseDificultad.getNomCategoria().getIdcategoria());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatFaseDificultad persistentDatFaseDificultad = em.find(DatFaseDificultad.class, datFaseDificultad.getDatFaseDificultadPK());
            NomCategoria nomCategoriaOld = persistentDatFaseDificultad.getNomCategoria();
            NomCategoria nomCategoriaNew = datFaseDificultad.getNomCategoria();
            DatDeportista datDeportistaOld = persistentDatFaseDificultad.getDatDeportista();
            DatDeportista datDeportistaNew = datFaseDificultad.getDatDeportista();
            DatCompetencia datCompetenciaOld = persistentDatFaseDificultad.getDatCompetencia();
            DatCompetencia datCompetenciaNew = datFaseDificultad.getDatCompetencia();
            if (nomCategoriaNew != null) {
                nomCategoriaNew = em.getReference(nomCategoriaNew.getClass(), nomCategoriaNew.getIdcategoria());
                datFaseDificultad.setNomCategoria(nomCategoriaNew);
            }
            if (datDeportistaNew != null) {
                datDeportistaNew = em.getReference(datDeportistaNew.getClass(), datDeportistaNew.getIddeportista());
                datFaseDificultad.setDatDeportista(datDeportistaNew);
            }
            if (datCompetenciaNew != null) {
                datCompetenciaNew = em.getReference(datCompetenciaNew.getClass(), datCompetenciaNew.getIdcompetencia());
                datFaseDificultad.setDatCompetencia(datCompetenciaNew);
            }
            datFaseDificultad = em.merge(datFaseDificultad);
            if (nomCategoriaOld != null && !nomCategoriaOld.equals(nomCategoriaNew)) {
                nomCategoriaOld.getDatFaseDificultadCollection().remove(datFaseDificultad);
                nomCategoriaOld = em.merge(nomCategoriaOld);
            }
            if (nomCategoriaNew != null && !nomCategoriaNew.equals(nomCategoriaOld)) {
                nomCategoriaNew.getDatFaseDificultadCollection().add(datFaseDificultad);
                nomCategoriaNew = em.merge(nomCategoriaNew);
            }
            if (datDeportistaOld != null && !datDeportistaOld.equals(datDeportistaNew)) {
                datDeportistaOld.getDatFaseDificultadCollection().remove(datFaseDificultad);
                datDeportistaOld = em.merge(datDeportistaOld);
            }
            if (datDeportistaNew != null && !datDeportistaNew.equals(datDeportistaOld)) {
                datDeportistaNew.getDatFaseDificultadCollection().add(datFaseDificultad);
                datDeportistaNew = em.merge(datDeportistaNew);
            }
            if (datCompetenciaOld != null && !datCompetenciaOld.equals(datCompetenciaNew)) {
                datCompetenciaOld.getDatFaseDificultadCollection().remove(datFaseDificultad);
                datCompetenciaOld = em.merge(datCompetenciaOld);
            }
            if (datCompetenciaNew != null && !datCompetenciaNew.equals(datCompetenciaOld)) {
                datCompetenciaNew.getDatFaseDificultadCollection().add(datFaseDificultad);
                datCompetenciaNew = em.merge(datCompetenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DatFaseDificultadPK id = datFaseDificultad.getDatFaseDificultadPK();
                if (findDatFaseDificultad(id) == null) {
                    throw new NonexistentEntityException("The datFaseDificultad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DatFaseDificultadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatFaseDificultad datFaseDificultad;
            try {
                datFaseDificultad = em.getReference(DatFaseDificultad.class, id);
                datFaseDificultad.getDatFaseDificultadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datFaseDificultad with id " + id + " no longer exists.", enfe);
            }
            NomCategoria nomCategoria = datFaseDificultad.getNomCategoria();
            if (nomCategoria != null) {
                nomCategoria.getDatFaseDificultadCollection().remove(datFaseDificultad);
                nomCategoria = em.merge(nomCategoria);
            }
            DatDeportista datDeportista = datFaseDificultad.getDatDeportista();
            if (datDeportista != null) {
                datDeportista.getDatFaseDificultadCollection().remove(datFaseDificultad);
                datDeportista = em.merge(datDeportista);
            }
            DatCompetencia datCompetencia = datFaseDificultad.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia.getDatFaseDificultadCollection().remove(datFaseDificultad);
                datCompetencia = em.merge(datCompetencia);
            }
            em.remove(datFaseDificultad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatFaseDificultad> findDatFaseDificultadEntities() {
        return findDatFaseDificultadEntities(true, -1, -1);
    }

    public List<DatFaseDificultad> findDatFaseDificultadEntities(int maxResults, int firstResult) {
        return findDatFaseDificultadEntities(false, maxResults, firstResult);
    }

    private List<DatFaseDificultad> findDatFaseDificultadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatFaseDificultad.class));
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

    public DatFaseDificultad findDatFaseDificultad(DatFaseDificultadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatFaseDificultad.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatFaseDificultadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatFaseDificultad> rt = cq.from(DatFaseDificultad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<DatFaseDificultad> findDatFaseDificultadEntitiesByIdCompCateg(DatCompetencia competencia, NomCategoria categoria) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<DatFaseDificultad> cq = cb.createQuery(DatFaseDificultad.class);
            Root<DatFaseDificultad> pet = cq.from(DatFaseDificultad.class);   
                    
            Predicate predicateComp = cb.equal(pet.get(DatFaseDificultad_.datCompetencia), competencia);
            Predicate predicateCateg = cb.equal(pet.get(DatFaseDificultad_.nomCategoria), categoria);
            Predicate p = cb.and(predicateComp,predicateCateg);
            cq.where(p);
            
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
