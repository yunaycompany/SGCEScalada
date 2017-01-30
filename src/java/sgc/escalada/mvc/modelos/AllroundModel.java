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
import sgc.escalada.mvc.entidades.DatAllround;
import sgc.escalada.mvc.entidades.DatAllroundPK;
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.entidades.NomProvincia;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;
import sgc.escalada.mvc.modelos.exceptions.PreexistingEntityException;

/**
 *
 * @author Yosbel
 */
public class AllroundModel implements Serializable {

    public AllroundModel(EntityManagerFactory emf) {
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

    public void create(DatAllround datAllround) throws PreexistingEntityException, Exception {
        if (datAllround.getDatAllroundPK() == null) {
            datAllround.setDatAllroundPK(new DatAllroundPK());
        }
//        datAllround.getDatAllroundPK().setIddeportista(datAllround.getDatDeportista().getIddeportista());
//        datAllround.getDatAllroundPK().setIdcompetencia(datAllround.getDatCompetencia().getIdcompetencia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomProvincia idprovincia = datAllround.getIdprovincia();
            if (idprovincia != null) {
                idprovincia = em.getReference(idprovincia.getClass(), idprovincia.getIdprovincia());
                datAllround.setIdprovincia(idprovincia);
            }
            NomCategoria idcategoria = datAllround.getIdcategoria();
            if (idcategoria != null) {
                idcategoria = em.getReference(idcategoria.getClass(), idcategoria.getIdcategoria());
                datAllround.setIdcategoria(idcategoria);
            }
            DatDeportista datDeportista = datAllround.getDatDeportista();
            if (datDeportista != null) {
                datDeportista = em.getReference(datDeportista.getClass(), datDeportista.getIddeportista());
                datAllround.setDatDeportista(datDeportista);
            }
            DatCompetencia datCompetencia = datAllround.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia = em.getReference(datCompetencia.getClass(), datCompetencia.getIdcompetencia());
                datAllround.setDatCompetencia(datCompetencia);
            }
            em.persist(datAllround);
            if (idprovincia != null) {
                idprovincia.getDatAllroundCollection().add(datAllround);
                idprovincia = em.merge(idprovincia);
            }
            if (idcategoria != null) {
                idcategoria.getDatAllroundCollection().add(datAllround);
                idcategoria = em.merge(idcategoria);
            }
            if (datDeportista != null) {
                datDeportista.getDatAllroundCollection().add(datAllround);
                datDeportista = em.merge(datDeportista);
            }
            if (datCompetencia != null) {
                datCompetencia.getDatAllroundCollection().add(datAllround);
                datCompetencia = em.merge(datCompetencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatAllround(datAllround.getDatAllroundPK()) != null) {
                throw new PreexistingEntityException("DatAllround " + datAllround + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatAllround datAllround) throws NonexistentEntityException, Exception {
        datAllround.getDatAllroundPK().setIddeportista(datAllround.getDatDeportista().getIddeportista());
        datAllround.getDatAllroundPK().setIdcompetencia(datAllround.getDatCompetencia().getIdcompetencia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatAllround persistentDatAllround = em.find(DatAllround.class, datAllround.getDatAllroundPK());
            NomProvincia idprovinciaOld = persistentDatAllround.getIdprovincia();
            NomProvincia idprovinciaNew = datAllround.getIdprovincia();
            NomCategoria idcategoriaOld = persistentDatAllround.getIdcategoria();
            NomCategoria idcategoriaNew = datAllround.getIdcategoria();
            DatDeportista datDeportistaOld = persistentDatAllround.getDatDeportista();
            DatDeportista datDeportistaNew = datAllround.getDatDeportista();
            DatCompetencia datCompetenciaOld = persistentDatAllround.getDatCompetencia();
            DatCompetencia datCompetenciaNew = datAllround.getDatCompetencia();
            if (idprovinciaNew != null) {
                idprovinciaNew = em.getReference(idprovinciaNew.getClass(), idprovinciaNew.getIdprovincia());
                datAllround.setIdprovincia(idprovinciaNew);
            }
            if (idcategoriaNew != null) {
                idcategoriaNew = em.getReference(idcategoriaNew.getClass(), idcategoriaNew.getIdcategoria());
                datAllround.setIdcategoria(idcategoriaNew);
            }
            if (datDeportistaNew != null) {
                datDeportistaNew = em.getReference(datDeportistaNew.getClass(), datDeportistaNew.getIddeportista());
                datAllround.setDatDeportista(datDeportistaNew);
            }
            if (datCompetenciaNew != null) {
                datCompetenciaNew = em.getReference(datCompetenciaNew.getClass(), datCompetenciaNew.getIdcompetencia());
                datAllround.setDatCompetencia(datCompetenciaNew);
            }
            datAllround = em.merge(datAllround);
            if (idprovinciaOld != null && !idprovinciaOld.equals(idprovinciaNew)) {
                idprovinciaOld.getDatAllroundCollection().remove(datAllround);
                idprovinciaOld = em.merge(idprovinciaOld);
            }
            if (idprovinciaNew != null && !idprovinciaNew.equals(idprovinciaOld)) {
                idprovinciaNew.getDatAllroundCollection().add(datAllround);
                idprovinciaNew = em.merge(idprovinciaNew);
            }
            if (idcategoriaOld != null && !idcategoriaOld.equals(idcategoriaNew)) {
                idcategoriaOld.getDatAllroundCollection().remove(datAllround);
                idcategoriaOld = em.merge(idcategoriaOld);
            }
            if (idcategoriaNew != null && !idcategoriaNew.equals(idcategoriaOld)) {
                idcategoriaNew.getDatAllroundCollection().add(datAllround);
                idcategoriaNew = em.merge(idcategoriaNew);
            }
            if (datDeportistaOld != null && !datDeportistaOld.equals(datDeportistaNew)) {
                datDeportistaOld.getDatAllroundCollection().remove(datAllround);
                datDeportistaOld = em.merge(datDeportistaOld);
            }
            if (datDeportistaNew != null && !datDeportistaNew.equals(datDeportistaOld)) {
                datDeportistaNew.getDatAllroundCollection().add(datAllround);
                datDeportistaNew = em.merge(datDeportistaNew);
            }
            if (datCompetenciaOld != null && !datCompetenciaOld.equals(datCompetenciaNew)) {
                datCompetenciaOld.getDatAllroundCollection().remove(datAllround);
                datCompetenciaOld = em.merge(datCompetenciaOld);
            }
            if (datCompetenciaNew != null && !datCompetenciaNew.equals(datCompetenciaOld)) {
                datCompetenciaNew.getDatAllroundCollection().add(datAllround);
                datCompetenciaNew = em.merge(datCompetenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DatAllroundPK id = datAllround.getDatAllroundPK();
                if (findDatAllround(id) == null) {
                    throw new NonexistentEntityException("The datAllround with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DatAllroundPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatAllround datAllround;
            try {
                datAllround = em.getReference(DatAllround.class, id);
                datAllround.getDatAllroundPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datAllround with id " + id + " no longer exists.", enfe);
            }
            NomProvincia idprovincia = datAllround.getIdprovincia();
            if (idprovincia != null) {
                idprovincia.getDatAllroundCollection().remove(datAllround);
                idprovincia = em.merge(idprovincia);
            }
            NomCategoria idcategoria = datAllround.getIdcategoria();
            if (idcategoria != null) {
                idcategoria.getDatAllroundCollection().remove(datAllround);
                idcategoria = em.merge(idcategoria);
            }
            DatDeportista datDeportista = datAllround.getDatDeportista();
            if (datDeportista != null) {
                datDeportista.getDatAllroundCollection().remove(datAllround);
                datDeportista = em.merge(datDeportista);
            }
            DatCompetencia datCompetencia = datAllround.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia.getDatAllroundCollection().remove(datAllround);
                datCompetencia = em.merge(datCompetencia);
            }
            em.remove(datAllround);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatAllround> findDatAllroundEntities() {
        return findDatAllroundEntities(true, -1, -1);
    }

    public List<DatAllround> findDatAllroundEntities(int maxResults, int firstResult) {
        return findDatAllroundEntities(false, maxResults, firstResult);
    }

    private List<DatAllround> findDatAllroundEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatAllround.class));
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

    public DatAllround findDatAllround(DatAllroundPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatAllround.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatAllroundCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatAllround> rt = cq.from(DatAllround.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
