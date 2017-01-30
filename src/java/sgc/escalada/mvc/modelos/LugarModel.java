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
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.HisResultado;
import sgc.escalada.mvc.entidades.NomLugar;
import sgc.escalada.mvc.entidades.NomLugar_;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Ariam
 */
public class LugarModel implements Serializable {

    public LugarModel(EntityManagerFactory emf) {
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

    public void create(NomLugar nomLugar) {
        if (nomLugar.getDatCompetenciaCollection() == null) {
            nomLugar.setDatCompetenciaCollection(new ArrayList<DatCompetencia>());
        }
        if (nomLugar.getHisResultadoCollection() == null) {
            nomLugar.setHisResultadoCollection(new ArrayList<HisResultado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomParroquia idparroquia = nomLugar.getIdparroquia();
            if (idparroquia != null) {
                idparroquia = em.getReference(idparroquia.getClass(), idparroquia.getIdparroquia());
                nomLugar.setIdparroquia(idparroquia);
            }
            Collection<DatCompetencia> attachedDatCompetenciaCollection = new ArrayList<DatCompetencia>();
            for (DatCompetencia datCompetenciaCollectionDatCompetenciaToAttach : nomLugar.getDatCompetenciaCollection()) {
                datCompetenciaCollectionDatCompetenciaToAttach = em.getReference(datCompetenciaCollectionDatCompetenciaToAttach.getClass(), datCompetenciaCollectionDatCompetenciaToAttach.getIdcompetencia());
                attachedDatCompetenciaCollection.add(datCompetenciaCollectionDatCompetenciaToAttach);
            }
            nomLugar.setDatCompetenciaCollection(attachedDatCompetenciaCollection);
            Collection<HisResultado> attachedHisResultadoCollection = new ArrayList<HisResultado>();
            for (HisResultado hisResultadoCollectionHisResultadoToAttach : nomLugar.getHisResultadoCollection()) {
                hisResultadoCollectionHisResultadoToAttach = em.getReference(hisResultadoCollectionHisResultadoToAttach.getClass(), hisResultadoCollectionHisResultadoToAttach.getHisResultadoPK());
                attachedHisResultadoCollection.add(hisResultadoCollectionHisResultadoToAttach);
            }
            nomLugar.setHisResultadoCollection(attachedHisResultadoCollection);
            em.persist(nomLugar);
            if (idparroquia != null) {
                idparroquia.getNomLugarCollection().add(nomLugar);
                idparroquia = em.merge(idparroquia);
            }
            for (DatCompetencia datCompetenciaCollectionDatCompetencia : nomLugar.getDatCompetenciaCollection()) {
                NomLugar oldIdlugarOfDatCompetenciaCollectionDatCompetencia = datCompetenciaCollectionDatCompetencia.getIdlugar();
                datCompetenciaCollectionDatCompetencia.setIdlugar(nomLugar);
                datCompetenciaCollectionDatCompetencia = em.merge(datCompetenciaCollectionDatCompetencia);
                if (oldIdlugarOfDatCompetenciaCollectionDatCompetencia != null) {
                    oldIdlugarOfDatCompetenciaCollectionDatCompetencia.getDatCompetenciaCollection().remove(datCompetenciaCollectionDatCompetencia);
                    oldIdlugarOfDatCompetenciaCollectionDatCompetencia = em.merge(oldIdlugarOfDatCompetenciaCollectionDatCompetencia);
                }
            }
            for (HisResultado hisResultadoCollectionHisResultado : nomLugar.getHisResultadoCollection()) {
                NomLugar oldIdlugarOfHisResultadoCollectionHisResultado = hisResultadoCollectionHisResultado.getIdlugar();
                hisResultadoCollectionHisResultado.setIdlugar(nomLugar);
                hisResultadoCollectionHisResultado = em.merge(hisResultadoCollectionHisResultado);
                if (oldIdlugarOfHisResultadoCollectionHisResultado != null) {
                    oldIdlugarOfHisResultadoCollectionHisResultado.getHisResultadoCollection().remove(hisResultadoCollectionHisResultado);
                    oldIdlugarOfHisResultadoCollectionHisResultado = em.merge(oldIdlugarOfHisResultadoCollectionHisResultado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomLugar nomLugar) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomLugar persistentNomLugar = em.find(NomLugar.class, nomLugar.getIdlugar());
            NomParroquia idparroquiaOld = persistentNomLugar.getIdparroquia();
            NomParroquia idparroquiaNew = nomLugar.getIdparroquia();
            Collection<DatCompetencia> datCompetenciaCollectionOld = persistentNomLugar.getDatCompetenciaCollection();
            Collection<DatCompetencia> datCompetenciaCollectionNew = nomLugar.getDatCompetenciaCollection();
            Collection<HisResultado> hisResultadoCollectionOld = persistentNomLugar.getHisResultadoCollection();
            Collection<HisResultado> hisResultadoCollectionNew = nomLugar.getHisResultadoCollection();
            List<String> illegalOrphanMessages = null;
            for (DatCompetencia datCompetenciaCollectionOldDatCompetencia : datCompetenciaCollectionOld) {
                if (!datCompetenciaCollectionNew.contains(datCompetenciaCollectionOldDatCompetencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatCompetencia " + datCompetenciaCollectionOldDatCompetencia + " since its idlugar field is not nullable.");
                }
            }
            for (HisResultado hisResultadoCollectionOldHisResultado : hisResultadoCollectionOld) {
                if (!hisResultadoCollectionNew.contains(hisResultadoCollectionOldHisResultado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HisResultado " + hisResultadoCollectionOldHisResultado + " since its idlugar field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idparroquiaNew != null) {
                idparroquiaNew = em.getReference(idparroquiaNew.getClass(), idparroquiaNew.getIdparroquia());
                nomLugar.setIdparroquia(idparroquiaNew);
            }
            Collection<DatCompetencia> attachedDatCompetenciaCollectionNew = new ArrayList<DatCompetencia>();
            for (DatCompetencia datCompetenciaCollectionNewDatCompetenciaToAttach : datCompetenciaCollectionNew) {
                datCompetenciaCollectionNewDatCompetenciaToAttach = em.getReference(datCompetenciaCollectionNewDatCompetenciaToAttach.getClass(), datCompetenciaCollectionNewDatCompetenciaToAttach.getIdcompetencia());
                attachedDatCompetenciaCollectionNew.add(datCompetenciaCollectionNewDatCompetenciaToAttach);
            }
            datCompetenciaCollectionNew = attachedDatCompetenciaCollectionNew;
            nomLugar.setDatCompetenciaCollection(datCompetenciaCollectionNew);
            Collection<HisResultado> attachedHisResultadoCollectionNew = new ArrayList<HisResultado>();
            for (HisResultado hisResultadoCollectionNewHisResultadoToAttach : hisResultadoCollectionNew) {
                hisResultadoCollectionNewHisResultadoToAttach = em.getReference(hisResultadoCollectionNewHisResultadoToAttach.getClass(), hisResultadoCollectionNewHisResultadoToAttach.getHisResultadoPK());
                attachedHisResultadoCollectionNew.add(hisResultadoCollectionNewHisResultadoToAttach);
            }
            hisResultadoCollectionNew = attachedHisResultadoCollectionNew;
            nomLugar.setHisResultadoCollection(hisResultadoCollectionNew);
            nomLugar = em.merge(nomLugar);
            if (idparroquiaOld != null && !idparroquiaOld.equals(idparroquiaNew)) {
                idparroquiaOld.getNomLugarCollection().remove(nomLugar);
                idparroquiaOld = em.merge(idparroquiaOld);
            }
            if (idparroquiaNew != null && !idparroquiaNew.equals(idparroquiaOld)) {
                idparroquiaNew.getNomLugarCollection().add(nomLugar);
                idparroquiaNew = em.merge(idparroquiaNew);
            }
            for (DatCompetencia datCompetenciaCollectionNewDatCompetencia : datCompetenciaCollectionNew) {
                if (!datCompetenciaCollectionOld.contains(datCompetenciaCollectionNewDatCompetencia)) {
                    NomLugar oldIdlugarOfDatCompetenciaCollectionNewDatCompetencia = datCompetenciaCollectionNewDatCompetencia.getIdlugar();
                    datCompetenciaCollectionNewDatCompetencia.setIdlugar(nomLugar);
                    datCompetenciaCollectionNewDatCompetencia = em.merge(datCompetenciaCollectionNewDatCompetencia);
                    if (oldIdlugarOfDatCompetenciaCollectionNewDatCompetencia != null && !oldIdlugarOfDatCompetenciaCollectionNewDatCompetencia.equals(nomLugar)) {
                        oldIdlugarOfDatCompetenciaCollectionNewDatCompetencia.getDatCompetenciaCollection().remove(datCompetenciaCollectionNewDatCompetencia);
                        oldIdlugarOfDatCompetenciaCollectionNewDatCompetencia = em.merge(oldIdlugarOfDatCompetenciaCollectionNewDatCompetencia);
                    }
                }
            }
            for (HisResultado hisResultadoCollectionNewHisResultado : hisResultadoCollectionNew) {
                if (!hisResultadoCollectionOld.contains(hisResultadoCollectionNewHisResultado)) {
                    NomLugar oldIdlugarOfHisResultadoCollectionNewHisResultado = hisResultadoCollectionNewHisResultado.getIdlugar();
                    hisResultadoCollectionNewHisResultado.setIdlugar(nomLugar);
                    hisResultadoCollectionNewHisResultado = em.merge(hisResultadoCollectionNewHisResultado);
                    if (oldIdlugarOfHisResultadoCollectionNewHisResultado != null && !oldIdlugarOfHisResultadoCollectionNewHisResultado.equals(nomLugar)) {
                        oldIdlugarOfHisResultadoCollectionNewHisResultado.getHisResultadoCollection().remove(hisResultadoCollectionNewHisResultado);
                        oldIdlugarOfHisResultadoCollectionNewHisResultado = em.merge(oldIdlugarOfHisResultadoCollectionNewHisResultado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomLugar.getIdlugar();
                if (findNomLugar(id) == null) {
                    throw new NonexistentEntityException("The nomLugar with id " + id + " no longer exists.");
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
            NomLugar nomLugar;
            try {
                nomLugar = em.getReference(NomLugar.class, id);
                nomLugar.getIdlugar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomLugar with id " + id + " no longer exists.", enfe);
            }
//            List<String> illegalOrphanMessages = null;
//            Collection<DatCompetencia> datCompetenciaCollectionOrphanCheck = nomLugar.getDatCompetenciaCollection();
//            for (DatCompetencia datCompetenciaCollectionOrphanCheckDatCompetencia : datCompetenciaCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomLugar (" + nomLugar + ") cannot be destroyed since the DatCompetencia " + datCompetenciaCollectionOrphanCheckDatCompetencia + " in its datCompetenciaCollection field has a non-nullable idlugar field.");
//            }
//            Collection<HisResultado> hisResultadoCollectionOrphanCheck = nomLugar.getHisResultadoCollection();
//            for (HisResultado hisResultadoCollectionOrphanCheckHisResultado : hisResultadoCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomLugar (" + nomLugar + ") cannot be destroyed since the HisResultado " + hisResultadoCollectionOrphanCheckHisResultado + " in its hisResultadoCollection field has a non-nullable idlugar field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
            NomParroquia idparroquia = nomLugar.getIdparroquia();
            if (idparroquia != null) {
                idparroquia.getNomLugarCollection().remove(nomLugar);
                idparroquia = em.merge(idparroquia);
            }
            em.remove(nomLugar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomLugar> findNomLugarEntities() {
        return findNomLugarEntities(true, -1, -1);
    }

    public List<NomLugar> findNomLugarEntities(int maxResults, int firstResult) {
        return findNomLugarEntities(false, maxResults, firstResult);
    }

    private List<NomLugar> findNomLugarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomLugar.class));
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

    public NomLugar findNomLugar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomLugar.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomLugarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomLugar> rt = cq.from(NomLugar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param parroquia
     * @return
     */
    public List<NomLugar> findNomLugarEntitiesByIdParroquia(NomParroquia parroquia) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<NomLugar> cq = cb.createQuery(NomLugar.class);
            Root<NomLugar> pet = cq.from(NomLugar.class);
            cq.where(pet.get(NomLugar_.idparroquia).in(parroquia));
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        } finally {
            em.close();
        }
    }    
}
