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
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.entidades.HisResultado;
import sgc.escalada.mvc.entidades.HisResultadoPK;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.entidades.NomLugar;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;
import sgc.escalada.mvc.modelos.exceptions.PreexistingEntityException;

/**
 *
 * @author Ariam
 */
public class ResultadoModel implements Serializable {

    public ResultadoModel(EntityManagerFactory emf) {
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

    public void create(HisResultado hisResultado) throws PreexistingEntityException, Exception {
        if (hisResultado.getHisResultadoPK() == null) {
            hisResultado.setHisResultadoPK(new HisResultadoPK());
        }
        hisResultado.getHisResultadoPK().setIddeportista(hisResultado.getDatDeportista().getIddeportista());
        hisResultado.getHisResultadoPK().setIdcompetencia(hisResultado.getDatCompetencia().getIdcompetencia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomLugar idlugar = hisResultado.getIdlugar();
            if (idlugar != null) {
                idlugar = em.getReference(idlugar.getClass(), idlugar.getIdlugar());
                hisResultado.setIdlugar(idlugar);
            }
            NomCategoria idcategoria = hisResultado.getIdcategoria();
            if (idcategoria != null) {
                idcategoria = em.getReference(idcategoria.getClass(), idcategoria.getIdcategoria());
                hisResultado.setIdcategoria(idcategoria);
            }
            DatDeportista datDeportista = hisResultado.getDatDeportista();
            if (datDeportista != null) {
                datDeportista = em.getReference(datDeportista.getClass(), datDeportista.getIddeportista());
                hisResultado.setDatDeportista(datDeportista);
            }
            DatCompetencia datCompetencia = hisResultado.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia = em.getReference(datCompetencia.getClass(), datCompetencia.getIdcompetencia());
                hisResultado.setDatCompetencia(datCompetencia);
            }
            em.persist(hisResultado);
            if (idlugar != null) {
                idlugar.getHisResultadoCollection().add(hisResultado);
                idlugar = em.merge(idlugar);
            }
            if (idcategoria != null) {
                idcategoria.getHisResultadoCollection().add(hisResultado);
                idcategoria = em.merge(idcategoria);
            }
            if (datDeportista != null) {
                datDeportista.getHisResultadoCollection().add(hisResultado);
                datDeportista = em.merge(datDeportista);
            }
            if (datCompetencia != null) {
                datCompetencia.getHisResultadoCollection().add(hisResultado);
                datCompetencia = em.merge(datCompetencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHisResultado(hisResultado.getHisResultadoPK()) != null) {
                throw new PreexistingEntityException("HisResultado " + hisResultado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HisResultado hisResultado) throws NonexistentEntityException, Exception {
        hisResultado.getHisResultadoPK().setIddeportista(hisResultado.getDatDeportista().getIddeportista());
        hisResultado.getHisResultadoPK().setIdcompetencia(hisResultado.getDatCompetencia().getIdcompetencia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HisResultado persistentHisResultado = em.find(HisResultado.class, hisResultado.getHisResultadoPK());
            NomLugar idlugarOld = persistentHisResultado.getIdlugar();
            NomLugar idlugarNew = hisResultado.getIdlugar();
            NomCategoria idcategoriaOld = persistentHisResultado.getIdcategoria();
            NomCategoria idcategoriaNew = hisResultado.getIdcategoria();
            DatDeportista datDeportistaOld = persistentHisResultado.getDatDeportista();
            DatDeportista datDeportistaNew = hisResultado.getDatDeportista();
            DatCompetencia datCompetenciaOld = persistentHisResultado.getDatCompetencia();
            DatCompetencia datCompetenciaNew = hisResultado.getDatCompetencia();
            if (idlugarNew != null) {
                idlugarNew = em.getReference(idlugarNew.getClass(), idlugarNew.getIdlugar());
                hisResultado.setIdlugar(idlugarNew);
            }
            if (idcategoriaNew != null) {
                idcategoriaNew = em.getReference(idcategoriaNew.getClass(), idcategoriaNew.getIdcategoria());
                hisResultado.setIdcategoria(idcategoriaNew);
            }
            if (datDeportistaNew != null) {
                datDeportistaNew = em.getReference(datDeportistaNew.getClass(), datDeportistaNew.getIddeportista());
                hisResultado.setDatDeportista(datDeportistaNew);
            }
            if (datCompetenciaNew != null) {
                datCompetenciaNew = em.getReference(datCompetenciaNew.getClass(), datCompetenciaNew.getIdcompetencia());
                hisResultado.setDatCompetencia(datCompetenciaNew);
            }
            hisResultado = em.merge(hisResultado);
            if (idlugarOld != null && !idlugarOld.equals(idlugarNew)) {
                idlugarOld.getHisResultadoCollection().remove(hisResultado);
                idlugarOld = em.merge(idlugarOld);
            }
            if (idlugarNew != null && !idlugarNew.equals(idlugarOld)) {
                idlugarNew.getHisResultadoCollection().add(hisResultado);
                idlugarNew = em.merge(idlugarNew);
            }
            if (idcategoriaOld != null && !idcategoriaOld.equals(idcategoriaNew)) {
                idcategoriaOld.getHisResultadoCollection().remove(hisResultado);
                idcategoriaOld = em.merge(idcategoriaOld);
            }
            if (idcategoriaNew != null && !idcategoriaNew.equals(idcategoriaOld)) {
                idcategoriaNew.getHisResultadoCollection().add(hisResultado);
                idcategoriaNew = em.merge(idcategoriaNew);
            }
            if (datDeportistaOld != null && !datDeportistaOld.equals(datDeportistaNew)) {
                datDeportistaOld.getHisResultadoCollection().remove(hisResultado);
                datDeportistaOld = em.merge(datDeportistaOld);
            }
            if (datDeportistaNew != null && !datDeportistaNew.equals(datDeportistaOld)) {
                datDeportistaNew.getHisResultadoCollection().add(hisResultado);
                datDeportistaNew = em.merge(datDeportistaNew);
            }
            if (datCompetenciaOld != null && !datCompetenciaOld.equals(datCompetenciaNew)) {
                datCompetenciaOld.getHisResultadoCollection().remove(hisResultado);
                datCompetenciaOld = em.merge(datCompetenciaOld);
            }
            if (datCompetenciaNew != null && !datCompetenciaNew.equals(datCompetenciaOld)) {
                datCompetenciaNew.getHisResultadoCollection().add(hisResultado);
                datCompetenciaNew = em.merge(datCompetenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HisResultadoPK id = hisResultado.getHisResultadoPK();
                if (findHisResultado(id) == null) {
                    throw new NonexistentEntityException("The hisResultado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HisResultadoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HisResultado hisResultado;
            try {
                hisResultado = em.getReference(HisResultado.class, id);
                hisResultado.getHisResultadoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hisResultado with id " + id + " no longer exists.", enfe);
            }
            NomLugar idlugar = hisResultado.getIdlugar();
            if (idlugar != null) {
                idlugar.getHisResultadoCollection().remove(hisResultado);
                idlugar = em.merge(idlugar);
            }
            NomCategoria idcategoria = hisResultado.getIdcategoria();
            if (idcategoria != null) {
                idcategoria.getHisResultadoCollection().remove(hisResultado);
                idcategoria = em.merge(idcategoria);
            }
            DatDeportista datDeportista = hisResultado.getDatDeportista();
            if (datDeportista != null) {
                datDeportista.getHisResultadoCollection().remove(hisResultado);
                datDeportista = em.merge(datDeportista);
            }
            DatCompetencia datCompetencia = hisResultado.getDatCompetencia();
            if (datCompetencia != null) {
                datCompetencia.getHisResultadoCollection().remove(hisResultado);
                datCompetencia = em.merge(datCompetencia);
            }
            em.remove(hisResultado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HisResultado> findHisResultadoEntities() {
        return findHisResultadoEntities(true, -1, -1);
    }

    public List<HisResultado> findHisResultadoEntities(int maxResults, int firstResult) {
        return findHisResultadoEntities(false, maxResults, firstResult);
    }

    private List<HisResultado> findHisResultadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HisResultado.class));
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

    public HisResultado findHisResultado(HisResultadoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HisResultado.class, id);
        } finally {
            em.close();
        }
    }

    public int getHisResultadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HisResultado> rt = cq.from(HisResultado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
