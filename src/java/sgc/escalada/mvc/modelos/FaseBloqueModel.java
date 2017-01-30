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
import sgc.escalada.mvc.entidades.DatFaseBloque;
import sgc.escalada.mvc.entidades.DatFaseBloquePK;
import sgc.escalada.mvc.entidades.DatFaseBloque_;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.entidades.NomEtapa;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;
import sgc.escalada.mvc.modelos.exceptions.PreexistingEntityException;

/**
 *
 * @author Ariam
 */
public class FaseBloqueModel implements Serializable {

    public FaseBloqueModel(EntityManagerFactory emf) {
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

    public void create(DatFaseBloque datFaseBloque) throws PreexistingEntityException, Exception {
        if (datFaseBloque.getDatFaseBloquePK() == null) {
            datFaseBloque.setDatFaseBloquePK(new DatFaseBloquePK());
        }
        datFaseBloque.getDatFaseBloquePK().setIdcompetencia(datFaseBloque.getDatCompetencia().getIdcompetencia());
        datFaseBloque.getDatFaseBloquePK().setIdcategoria(datFaseBloque.getNomCategoria().getIdcategoria());
        datFaseBloque.getDatFaseBloquePK().setIddeportista(datFaseBloque.getDatDeportista().getIddeportista());
        datFaseBloque.getDatFaseBloquePK().setIdetapa(datFaseBloque.getNomEtapa().getIdetapa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomEtapa nomEtapa = datFaseBloque.getNomEtapa();
            if (nomEtapa != null) {
                nomEtapa = em.getReference(nomEtapa.getClass(), nomEtapa.getIdetapa());
                datFaseBloque.setNomEtapa(nomEtapa);
            }
            NomCategoria nomCategoria = datFaseBloque.getNomCategoria();
            if (nomCategoria != null) {
                nomCategoria = em.getReference(nomCategoria.getClass(), nomCategoria.getIdcategoria());
                datFaseBloque.setNomCategoria(nomCategoria);
            }
            DatDeportista datDeportista = datFaseBloque.getDatDeportista();
            if (datDeportista != null) {
                datDeportista = em.getReference(datDeportista.getClass(), datDeportista.getIddeportista());
                datFaseBloque.setDatDeportista(datDeportista);
            }
            DatCompetencia datCompetencia = datFaseBloque.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia = em.getReference(datCompetencia.getClass(), datCompetencia.getIdcompetencia());
                datFaseBloque.setDatCompetencia(datCompetencia);
            }
            em.persist(datFaseBloque);
            if (nomEtapa != null) {
                nomEtapa.getDatFaseBloqueCollection().add(datFaseBloque);
                nomEtapa = em.merge(nomEtapa);
            }
            if (nomCategoria != null) {
                nomCategoria.getDatFaseBloqueCollection().add(datFaseBloque);
                nomCategoria = em.merge(nomCategoria);
            }
            if (datDeportista != null) {
                datDeportista.getDatFaseBloqueCollection().add(datFaseBloque);
                datDeportista = em.merge(datDeportista);
            }
            if (datCompetencia != null) {
                datCompetencia.getDatFaseBloqueCollection().add(datFaseBloque);
                datCompetencia = em.merge(datCompetencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatFaseBloque(datFaseBloque.getDatFaseBloquePK()) != null) {
                throw new PreexistingEntityException("DatFaseBloque " + datFaseBloque + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatFaseBloque datFaseBloque) throws NonexistentEntityException, Exception {
        datFaseBloque.getDatFaseBloquePK().setIdcompetencia(datFaseBloque.getDatCompetencia().getIdcompetencia());
        datFaseBloque.getDatFaseBloquePK().setIdcategoria(datFaseBloque.getNomCategoria().getIdcategoria());
        datFaseBloque.getDatFaseBloquePK().setIddeportista(datFaseBloque.getDatDeportista().getIddeportista());
        datFaseBloque.getDatFaseBloquePK().setIdetapa(datFaseBloque.getNomEtapa().getIdetapa());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatFaseBloque persistentDatFaseBloque = em.find(DatFaseBloque.class, datFaseBloque.getDatFaseBloquePK());
            NomEtapa nomEtapaOld = persistentDatFaseBloque.getNomEtapa();
            NomEtapa nomEtapaNew = datFaseBloque.getNomEtapa();
            NomCategoria nomCategoriaOld = persistentDatFaseBloque.getNomCategoria();
            NomCategoria nomCategoriaNew = datFaseBloque.getNomCategoria();
            DatDeportista datDeportistaOld = persistentDatFaseBloque.getDatDeportista();
            DatDeportista datDeportistaNew = datFaseBloque.getDatDeportista();
            DatCompetencia datCompetenciaOld = persistentDatFaseBloque.getDatCompetencia();
            DatCompetencia datCompetenciaNew = datFaseBloque.getDatCompetencia();
            if (nomEtapaNew != null) {
                nomEtapaNew = em.getReference(nomEtapaNew.getClass(), nomEtapaNew.getIdetapa());
                datFaseBloque.setNomEtapa(nomEtapaNew);
            }
            if (nomCategoriaNew != null) {
                nomCategoriaNew = em.getReference(nomCategoriaNew.getClass(), nomCategoriaNew.getIdcategoria());
                datFaseBloque.setNomCategoria(nomCategoriaNew);
            }
            if (datDeportistaNew != null) {
                datDeportistaNew = em.getReference(datDeportistaNew.getClass(), datDeportistaNew.getIddeportista());
                datFaseBloque.setDatDeportista(datDeportistaNew);
            }
            if (datCompetenciaNew != null) {
                datCompetenciaNew = em.getReference(datCompetenciaNew.getClass(), datCompetenciaNew.getIdcompetencia());
                datFaseBloque.setDatCompetencia(datCompetenciaNew);
            }
            datFaseBloque = em.merge(datFaseBloque);
            if (nomEtapaOld != null && !nomEtapaOld.equals(nomEtapaNew)) {
                nomEtapaOld.getDatFaseBloqueCollection().remove(datFaseBloque);
                nomEtapaOld = em.merge(nomEtapaOld);
            }
            if (nomEtapaNew != null && !nomEtapaNew.equals(nomEtapaOld)) {
                nomEtapaNew.getDatFaseBloqueCollection().add(datFaseBloque);
                nomEtapaNew = em.merge(nomEtapaNew);
            }
            if (nomCategoriaOld != null && !nomCategoriaOld.equals(nomCategoriaNew)) {
                nomCategoriaOld.getDatFaseBloqueCollection().remove(datFaseBloque);
                nomCategoriaOld = em.merge(nomCategoriaOld);
            }
            if (nomCategoriaNew != null && !nomCategoriaNew.equals(nomCategoriaOld)) {
                nomCategoriaNew.getDatFaseBloqueCollection().add(datFaseBloque);
                nomCategoriaNew = em.merge(nomCategoriaNew);
            }
            if (datDeportistaOld != null && !datDeportistaOld.equals(datDeportistaNew)) {
                datDeportistaOld.getDatFaseBloqueCollection().remove(datFaseBloque);
                datDeportistaOld = em.merge(datDeportistaOld);
            }
            if (datDeportistaNew != null && !datDeportistaNew.equals(datDeportistaOld)) {
                datDeportistaNew.getDatFaseBloqueCollection().add(datFaseBloque);
                datDeportistaNew = em.merge(datDeportistaNew);
            }
            if (datCompetenciaOld != null && !datCompetenciaOld.equals(datCompetenciaNew)) {
                datCompetenciaOld.getDatFaseBloqueCollection().remove(datFaseBloque);
                datCompetenciaOld = em.merge(datCompetenciaOld);
            }
            if (datCompetenciaNew != null && !datCompetenciaNew.equals(datCompetenciaOld)) {
                datCompetenciaNew.getDatFaseBloqueCollection().add(datFaseBloque);
                datCompetenciaNew = em.merge(datCompetenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DatFaseBloquePK id = datFaseBloque.getDatFaseBloquePK();
                if (findDatFaseBloque(id) == null) {
                    throw new NonexistentEntityException("The datFaseBloque with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DatFaseBloquePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatFaseBloque datFaseBloque;
            try {
                datFaseBloque = em.getReference(DatFaseBloque.class, id);
                datFaseBloque.getDatFaseBloquePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datFaseBloque with id " + id + " no longer exists.", enfe);
            }
            NomEtapa nomEtapa = datFaseBloque.getNomEtapa();
            if (nomEtapa != null) {
                nomEtapa.getDatFaseBloqueCollection().remove(datFaseBloque);
                nomEtapa = em.merge(nomEtapa);
            }
            NomCategoria nomCategoria = datFaseBloque.getNomCategoria();
            if (nomCategoria != null) {
                nomCategoria.getDatFaseBloqueCollection().remove(datFaseBloque);
                nomCategoria = em.merge(nomCategoria);
            }
            DatDeportista datDeportista = datFaseBloque.getDatDeportista();
            if (datDeportista != null) {
                datDeportista.getDatFaseBloqueCollection().remove(datFaseBloque);
                datDeportista = em.merge(datDeportista);
            }
            DatCompetencia datCompetencia = datFaseBloque.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia.getDatFaseBloqueCollection().remove(datFaseBloque);
                datCompetencia = em.merge(datCompetencia);
            }
            em.remove(datFaseBloque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatFaseBloque> findDatFaseBloqueEntities() {
        return findDatFaseBloqueEntities(true, -1, -1);
    }

    public List<DatFaseBloque> findDatFaseBloqueEntities(int maxResults, int firstResult) {
        return findDatFaseBloqueEntities(false, maxResults, firstResult);
    }

    private List<DatFaseBloque> findDatFaseBloqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatFaseBloque.class));
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

    public DatFaseBloque findDatFaseBloque(DatFaseBloquePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatFaseBloque.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatFaseBloqueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatFaseBloque> rt = cq.from(DatFaseBloque.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<DatFaseBloque> findDatFaseBloqueEntitiesByIdCompCategEtap(DatCompetencia competencia, 
              NomCategoria categoria,NomEtapa etapa) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<DatFaseBloque> cq = cb.createQuery(DatFaseBloque.class);
            Root<DatFaseBloque> pet = cq.from(DatFaseBloque.class);  
                    
            Predicate predicateComp = cb.equal(pet.get(DatFaseBloque_.datCompetencia), competencia);
            Predicate predicateCateg = cb.equal(pet.get(DatFaseBloque_.nomCategoria), categoria);
            Predicate predicateEtap = cb.equal(pet.get(DatFaseBloque_.nomEtapa), etapa);
            Predicate p = cb.and(predicateComp,predicateCateg,predicateEtap);
            cq.where(p);
            
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
