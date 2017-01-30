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
import sgc.escalada.mvc.entidades.DatFaseVelocidad;
import sgc.escalada.mvc.entidades.DatFaseVelocidadPK;
import sgc.escalada.mvc.entidades.DatFaseVelocidad_;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.entidades.NomEtapa;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;
import sgc.escalada.mvc.modelos.exceptions.PreexistingEntityException;

/**
 *
 * @author Ariam
 */
public class FaseVelocidadModel implements Serializable {

    public FaseVelocidadModel(EntityManagerFactory emf) {
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

    public void create(DatFaseVelocidad datFaseVelocidad) throws PreexistingEntityException, Exception {
        if (datFaseVelocidad.getDatFaseVelocidadPK() == null) {
            datFaseVelocidad.setDatFaseVelocidadPK(new DatFaseVelocidadPK());
        }
        datFaseVelocidad.getDatFaseVelocidadPK().setIdetapa(datFaseVelocidad.getNomEtapa().getIdetapa());
        datFaseVelocidad.getDatFaseVelocidadPK().setIdcompetencia(datFaseVelocidad.getDatCompetencia().getIdcompetencia());
        datFaseVelocidad.getDatFaseVelocidadPK().setIddeportista(datFaseVelocidad.getDatDeportista().getIddeportista());
        datFaseVelocidad.getDatFaseVelocidadPK().setIdcategoria(datFaseVelocidad.getNomCategoria().getIdcategoria());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomEtapa nomEtapa = datFaseVelocidad.getNomEtapa();
            if (nomEtapa != null) {
                nomEtapa = em.getReference(nomEtapa.getClass(), nomEtapa.getIdetapa());
                datFaseVelocidad.setNomEtapa(nomEtapa);
            }
            NomCategoria nomCategoria = datFaseVelocidad.getNomCategoria();
            if (nomCategoria != null) {
                nomCategoria = em.getReference(nomCategoria.getClass(), nomCategoria.getIdcategoria());
                datFaseVelocidad.setNomCategoria(nomCategoria);
            }
            DatDeportista datDeportista = datFaseVelocidad.getDatDeportista();
            if (datDeportista != null) {
                datDeportista = em.getReference(datDeportista.getClass(), datDeportista.getIddeportista());
                datFaseVelocidad.setDatDeportista(datDeportista);
            }
            DatCompetencia datCompetencia = datFaseVelocidad.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia = em.getReference(datCompetencia.getClass(), datCompetencia.getIdcompetencia());
                datFaseVelocidad.setDatCompetencia(datCompetencia);
            }
            em.persist(datFaseVelocidad);
            if (nomEtapa != null) {
                nomEtapa.getDatFaseVelocidadCollection().add(datFaseVelocidad);
                nomEtapa = em.merge(nomEtapa);
            }
            if (nomCategoria != null) {
                nomCategoria.getDatFaseVelocidadCollection().add(datFaseVelocidad);
                nomCategoria = em.merge(nomCategoria);
            }
            if (datDeportista != null) {
                datDeportista.getDatFaseVelocidadCollection().add(datFaseVelocidad);
                datDeportista = em.merge(datDeportista);
            }
            if (datCompetencia != null) {
                datCompetencia.getDatFaseVelocidadCollection().add(datFaseVelocidad);
                datCompetencia = em.merge(datCompetencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatFaseVelocidad(datFaseVelocidad.getDatFaseVelocidadPK()) != null) {
                throw new PreexistingEntityException("DatFaseVelocidad " + datFaseVelocidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatFaseVelocidad datFaseVelocidad) throws NonexistentEntityException, Exception {
        datFaseVelocidad.getDatFaseVelocidadPK().setIdetapa(datFaseVelocidad.getNomEtapa().getIdetapa());
        datFaseVelocidad.getDatFaseVelocidadPK().setIdcompetencia(datFaseVelocidad.getDatCompetencia().getIdcompetencia());
        datFaseVelocidad.getDatFaseVelocidadPK().setIddeportista(datFaseVelocidad.getDatDeportista().getIddeportista());
        datFaseVelocidad.getDatFaseVelocidadPK().setIdcategoria(datFaseVelocidad.getNomCategoria().getIdcategoria());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatFaseVelocidad persistentDatFaseVelocidad = em.find(DatFaseVelocidad.class, datFaseVelocidad.getDatFaseVelocidadPK());
            NomEtapa nomEtapaOld = persistentDatFaseVelocidad.getNomEtapa();
            NomEtapa nomEtapaNew = datFaseVelocidad.getNomEtapa();
            NomCategoria nomCategoriaOld = persistentDatFaseVelocidad.getNomCategoria();
            NomCategoria nomCategoriaNew = datFaseVelocidad.getNomCategoria();
            DatDeportista datDeportistaOld = persistentDatFaseVelocidad.getDatDeportista();
            DatDeportista datDeportistaNew = datFaseVelocidad.getDatDeportista();
            DatCompetencia datCompetenciaOld = persistentDatFaseVelocidad.getDatCompetencia();
            DatCompetencia datCompetenciaNew = datFaseVelocidad.getDatCompetencia();
            if (nomEtapaNew != null) {
                nomEtapaNew = em.getReference(nomEtapaNew.getClass(), nomEtapaNew.getIdetapa());
                datFaseVelocidad.setNomEtapa(nomEtapaNew);
            }
            if (nomCategoriaNew != null) {
                nomCategoriaNew = em.getReference(nomCategoriaNew.getClass(), nomCategoriaNew.getIdcategoria());
                datFaseVelocidad.setNomCategoria(nomCategoriaNew);
            }
            if (datDeportistaNew != null) {
                datDeportistaNew = em.getReference(datDeportistaNew.getClass(), datDeportistaNew.getIddeportista());
                datFaseVelocidad.setDatDeportista(datDeportistaNew);
            }
            if (datCompetenciaNew != null) {
                datCompetenciaNew = em.getReference(datCompetenciaNew.getClass(), datCompetenciaNew.getIdcompetencia());
                datFaseVelocidad.setDatCompetencia(datCompetenciaNew);
            }
            datFaseVelocidad = em.merge(datFaseVelocidad);
            if (nomEtapaOld != null && !nomEtapaOld.equals(nomEtapaNew)) {
                nomEtapaOld.getDatFaseVelocidadCollection().remove(datFaseVelocidad);
                nomEtapaOld = em.merge(nomEtapaOld);
            }
            if (nomEtapaNew != null && !nomEtapaNew.equals(nomEtapaOld)) {
                nomEtapaNew.getDatFaseVelocidadCollection().add(datFaseVelocidad);
                nomEtapaNew = em.merge(nomEtapaNew);
            }
            if (nomCategoriaOld != null && !nomCategoriaOld.equals(nomCategoriaNew)) {
                nomCategoriaOld.getDatFaseVelocidadCollection().remove(datFaseVelocidad);
                nomCategoriaOld = em.merge(nomCategoriaOld);
            }
            if (nomCategoriaNew != null && !nomCategoriaNew.equals(nomCategoriaOld)) {
                nomCategoriaNew.getDatFaseVelocidadCollection().add(datFaseVelocidad);
                nomCategoriaNew = em.merge(nomCategoriaNew);
            }
            if (datDeportistaOld != null && !datDeportistaOld.equals(datDeportistaNew)) {
                datDeportistaOld.getDatFaseVelocidadCollection().remove(datFaseVelocidad);
                datDeportistaOld = em.merge(datDeportistaOld);
            }
            if (datDeportistaNew != null && !datDeportistaNew.equals(datDeportistaOld)) {
                datDeportistaNew.getDatFaseVelocidadCollection().add(datFaseVelocidad);
                datDeportistaNew = em.merge(datDeportistaNew);
            }
            if (datCompetenciaOld != null && !datCompetenciaOld.equals(datCompetenciaNew)) {
                datCompetenciaOld.getDatFaseVelocidadCollection().remove(datFaseVelocidad);
                datCompetenciaOld = em.merge(datCompetenciaOld);
            }
            if (datCompetenciaNew != null && !datCompetenciaNew.equals(datCompetenciaOld)) {
                datCompetenciaNew.getDatFaseVelocidadCollection().add(datFaseVelocidad);
                datCompetenciaNew = em.merge(datCompetenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DatFaseVelocidadPK id = datFaseVelocidad.getDatFaseVelocidadPK();
                if (findDatFaseVelocidad(id) == null) {
                    throw new NonexistentEntityException("The datFaseVelocidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DatFaseVelocidadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatFaseVelocidad datFaseVelocidad;
            try {
                datFaseVelocidad = em.getReference(DatFaseVelocidad.class, id);
                datFaseVelocidad.getDatFaseVelocidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datFaseVelocidad with id " + id + " no longer exists.", enfe);
            }
            NomEtapa nomEtapa = datFaseVelocidad.getNomEtapa();
            if (nomEtapa != null) {
                nomEtapa.getDatFaseVelocidadCollection().remove(datFaseVelocidad);
                nomEtapa = em.merge(nomEtapa);
            }
            NomCategoria nomCategoria = datFaseVelocidad.getNomCategoria();
            if (nomCategoria != null) {
                nomCategoria.getDatFaseVelocidadCollection().remove(datFaseVelocidad);
                nomCategoria = em.merge(nomCategoria);
            }
            DatDeportista datDeportista = datFaseVelocidad.getDatDeportista();
            if (datDeportista != null) {
                datDeportista.getDatFaseVelocidadCollection().remove(datFaseVelocidad);
                datDeportista = em.merge(datDeportista);
            }
            DatCompetencia datCompetencia = datFaseVelocidad.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia.getDatFaseVelocidadCollection().remove(datFaseVelocidad);
                datCompetencia = em.merge(datCompetencia);
            }
            em.remove(datFaseVelocidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatFaseVelocidad> findDatFaseVelocidadEntities() {
        return findDatFaseVelocidadEntities(true, -1, -1);
    }

    public List<DatFaseVelocidad> findDatFaseVelocidadEntities(int maxResults, int firstResult) {
        return findDatFaseVelocidadEntities(false, maxResults, firstResult);
    }

    private List<DatFaseVelocidad> findDatFaseVelocidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatFaseVelocidad.class));
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

    public DatFaseVelocidad findDatFaseVelocidad(DatFaseVelocidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatFaseVelocidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatFaseVelocidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatFaseVelocidad> rt = cq.from(DatFaseVelocidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<DatFaseVelocidad> findDatFaseVelocidadEntitiesByIdCompCategEtap(DatCompetencia competencia, 
              NomCategoria categoria,NomEtapa etapa) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<DatFaseVelocidad> cq = cb.createQuery(DatFaseVelocidad.class);
            Root<DatFaseVelocidad> pet = cq.from(DatFaseVelocidad.class);  
                    
            Predicate predicateComp = cb.equal(pet.get(DatFaseVelocidad_.datCompetencia), competencia);
            Predicate predicateCateg = cb.equal(pet.get(DatFaseVelocidad_.nomCategoria), categoria);
            Predicate predicateEtap = cb.equal(pet.get(DatFaseVelocidad_.nomEtapa), etapa);
            Predicate p = cb.and(predicateComp,predicateCateg,predicateEtap);
            cq.where(p);
            
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        } finally {
            em.close();
        }
    }
     
     public List<DatFaseVelocidad> findDatFaseVelocidadEntitiesByIdCompCateg(DatCompetencia competencia, 
              NomCategoria categoria) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<DatFaseVelocidad> cq = cb.createQuery(DatFaseVelocidad.class);
            Root<DatFaseVelocidad> pet = cq.from(DatFaseVelocidad.class);  
                    
            Predicate predicateComp = cb.equal(pet.get(DatFaseVelocidad_.datCompetencia), competencia);
            Predicate predicateCateg = cb.equal(pet.get(DatFaseVelocidad_.nomCategoria), categoria);
            Predicate p = cb.and(predicateComp,predicateCateg);
            cq.where(p);
            
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
