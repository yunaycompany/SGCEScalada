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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.DatGaleria;
import sgc.escalada.mvc.entidades.NomCanton;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.entidades.NomParroquia_;
import sgc.escalada.mvc.entidades.NomTipoMedia;
import sgc.escalada.mvc.entidades.NomTipoMedia_;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class GaleriaModel implements Serializable {

    public GaleriaModel(EntityManagerFactory emf) {
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

    public void create(DatGaleria datGaleria) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        DatCompetencia idcompetenciaOrphanCheck = datGaleria.getIdcompetencia();
        if (idcompetenciaOrphanCheck != null) {
            DatGaleria oldDatGaleriaOfIdcompetencia = idcompetenciaOrphanCheck.getDatGaleria();
            if (oldDatGaleriaOfIdcompetencia != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The DatCompetencia " + idcompetenciaOrphanCheck + " already has an item of type DatGaleria whose idcompetencia column cannot be null. Please make another selection for the idcompetencia field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomTipoMedia idtipomedia = datGaleria.getIdtipomedia();
            if (idtipomedia != null) {
                idtipomedia = em.getReference(idtipomedia.getClass(), idtipomedia.getIdtipomedia());
                datGaleria.setIdtipomedia(idtipomedia);
            }
            DatCompetencia idcompetencia = datGaleria.getIdcompetencia();
            if (idcompetencia != null) {
                idcompetencia = em.getReference(idcompetencia.getClass(), idcompetencia.getIdcompetencia());
                datGaleria.setIdcompetencia(idcompetencia);
            }
            em.persist(datGaleria);
            if (idtipomedia != null) {
                idtipomedia.getDatGaleriaCollection().add(datGaleria);
                idtipomedia = em.merge(idtipomedia);
            }
            if (idcompetencia != null) {
                idcompetencia.setDatGaleria(datGaleria);
                idcompetencia = em.merge(idcompetencia);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatGaleria datGaleria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatGaleria persistentDatGaleria = em.find(DatGaleria.class, datGaleria.getIdmedia());
            NomTipoMedia idtipomediaOld = persistentDatGaleria.getIdtipomedia();
            NomTipoMedia idtipomediaNew = datGaleria.getIdtipomedia();
            DatCompetencia idcompetenciaOld = persistentDatGaleria.getIdcompetencia();
            DatCompetencia idcompetenciaNew = datGaleria.getIdcompetencia();
            List<String> illegalOrphanMessages = null;
            if (idcompetenciaNew != null && !idcompetenciaNew.equals(idcompetenciaOld)) {
                DatGaleria oldDatGaleriaOfIdcompetencia = idcompetenciaNew.getDatGaleria();
                if (oldDatGaleriaOfIdcompetencia != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The DatCompetencia " + idcompetenciaNew + " already has an item of type DatGaleria whose idcompetencia column cannot be null. Please make another selection for the idcompetencia field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idtipomediaNew != null) {
                idtipomediaNew = em.getReference(idtipomediaNew.getClass(), idtipomediaNew.getIdtipomedia());
                datGaleria.setIdtipomedia(idtipomediaNew);
            }
            if (idcompetenciaNew != null) {
                idcompetenciaNew = em.getReference(idcompetenciaNew.getClass(), idcompetenciaNew.getIdcompetencia());
                datGaleria.setIdcompetencia(idcompetenciaNew);
            }
            datGaleria = em.merge(datGaleria);
            if (idtipomediaOld != null && !idtipomediaOld.equals(idtipomediaNew)) {
                idtipomediaOld.getDatGaleriaCollection().remove(datGaleria);
                idtipomediaOld = em.merge(idtipomediaOld);
            }
            if (idtipomediaNew != null && !idtipomediaNew.equals(idtipomediaOld)) {
                idtipomediaNew.getDatGaleriaCollection().add(datGaleria);
                idtipomediaNew = em.merge(idtipomediaNew);
            }
            if (idcompetenciaOld != null && !idcompetenciaOld.equals(idcompetenciaNew)) {
                idcompetenciaOld.setDatGaleria(null);
                idcompetenciaOld = em.merge(idcompetenciaOld);
            }
            if (idcompetenciaNew != null && !idcompetenciaNew.equals(idcompetenciaOld)) {
                idcompetenciaNew.setDatGaleria(datGaleria);
                idcompetenciaNew = em.merge(idcompetenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datGaleria.getIdmedia();
                if (findDatGaleria(id) == null) {
                    throw new NonexistentEntityException("The datGaleria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatGaleria datGaleria;
            try {
                datGaleria = em.getReference(DatGaleria.class, id);
                datGaleria.getIdmedia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datGaleria with id " + id + " no longer exists.", enfe);
            }
            NomTipoMedia idtipomedia = datGaleria.getIdtipomedia();
            if (idtipomedia != null) {
                idtipomedia.getDatGaleriaCollection().remove(datGaleria);
                idtipomedia = em.merge(idtipomedia);
            }
            DatCompetencia idcompetencia = datGaleria.getIdcompetencia();
            if (idcompetencia != null) {
                idcompetencia.setDatGaleria(null);
                idcompetencia = em.merge(idcompetencia);
            }
            em.remove(datGaleria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatGaleria> findDatGaleriaEntities() {
        return findDatGaleriaEntities(true, -1, -1);
    }

    public List<DatGaleria> findDatGaleriaEntities(int maxResults, int firstResult) {
        return findDatGaleriaEntities(false, maxResults, firstResult);
    }

    private List<DatGaleria> findDatGaleriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatGaleria.class));
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

    public DatGaleria findDatGaleria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatGaleria.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatGaleriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatGaleria> rt = cq.from(DatGaleria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<DatGaleria> findGalerEntitiesByTipo(NomTipoMedia tipo) {
        EntityManager em = getEntityManager();
        try{
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<NomTipoMedia> cq = cb.createQuery(NomTipoMedia.class);
            Root<NomTipoMedia> pet = cq.from(NomTipoMedia.class);
            cq.where(pet.get(NomTipoMedia_.idtipomedia).in(tipo));
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        }finally{
            em.close();
        }
    }
    
}
