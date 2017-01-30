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
import sgc.escalada.mvc.entidades.DatFaseBloque;
import sgc.escalada.mvc.entidades.DatFaseVelocidad;
import sgc.escalada.mvc.entidades.NomEtapa;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class EtapaModel implements Serializable {

    public EtapaModel(EntityManagerFactory emf) {
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

    public void create(NomEtapa nomEtapa) {
        if (nomEtapa.getDatFaseBloqueCollection() == null) {
            nomEtapa.setDatFaseBloqueCollection(new ArrayList<DatFaseBloque>());
        }
        if (nomEtapa.getDatFaseVelocidadCollection() == null) {
            nomEtapa.setDatFaseVelocidadCollection(new ArrayList<DatFaseVelocidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DatFaseBloque> attachedDatFaseBloqueCollection = new ArrayList<DatFaseBloque>();
            for (DatFaseBloque datFaseBloqueCollectionDatFaseBloqueToAttach : nomEtapa.getDatFaseBloqueCollection()) {
                datFaseBloqueCollectionDatFaseBloqueToAttach = em.getReference(datFaseBloqueCollectionDatFaseBloqueToAttach.getClass(), datFaseBloqueCollectionDatFaseBloqueToAttach.getDatFaseBloquePK());
                attachedDatFaseBloqueCollection.add(datFaseBloqueCollectionDatFaseBloqueToAttach);
            }
            nomEtapa.setDatFaseBloqueCollection(attachedDatFaseBloqueCollection);
            Collection<DatFaseVelocidad> attachedDatFaseVelocidadCollection = new ArrayList<DatFaseVelocidad>();
            for (DatFaseVelocidad datFaseVelocidadCollectionDatFaseVelocidadToAttach : nomEtapa.getDatFaseVelocidadCollection()) {
                datFaseVelocidadCollectionDatFaseVelocidadToAttach = em.getReference(datFaseVelocidadCollectionDatFaseVelocidadToAttach.getClass(), datFaseVelocidadCollectionDatFaseVelocidadToAttach.getDatFaseVelocidadPK());
                attachedDatFaseVelocidadCollection.add(datFaseVelocidadCollectionDatFaseVelocidadToAttach);
            }
            nomEtapa.setDatFaseVelocidadCollection(attachedDatFaseVelocidadCollection);
            em.persist(nomEtapa);
            for (DatFaseBloque datFaseBloqueCollectionDatFaseBloque : nomEtapa.getDatFaseBloqueCollection()) {
                NomEtapa oldNomEtapaOfDatFaseBloqueCollectionDatFaseBloque = datFaseBloqueCollectionDatFaseBloque.getNomEtapa();
                datFaseBloqueCollectionDatFaseBloque.setNomEtapa(nomEtapa);
                datFaseBloqueCollectionDatFaseBloque = em.merge(datFaseBloqueCollectionDatFaseBloque);
                if (oldNomEtapaOfDatFaseBloqueCollectionDatFaseBloque != null) {
                    oldNomEtapaOfDatFaseBloqueCollectionDatFaseBloque.getDatFaseBloqueCollection().remove(datFaseBloqueCollectionDatFaseBloque);
                    oldNomEtapaOfDatFaseBloqueCollectionDatFaseBloque = em.merge(oldNomEtapaOfDatFaseBloqueCollectionDatFaseBloque);
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionDatFaseVelocidad : nomEtapa.getDatFaseVelocidadCollection()) {
                NomEtapa oldNomEtapaOfDatFaseVelocidadCollectionDatFaseVelocidad = datFaseVelocidadCollectionDatFaseVelocidad.getNomEtapa();
                datFaseVelocidadCollectionDatFaseVelocidad.setNomEtapa(nomEtapa);
                datFaseVelocidadCollectionDatFaseVelocidad = em.merge(datFaseVelocidadCollectionDatFaseVelocidad);
                if (oldNomEtapaOfDatFaseVelocidadCollectionDatFaseVelocidad != null) {
                    oldNomEtapaOfDatFaseVelocidadCollectionDatFaseVelocidad.getDatFaseVelocidadCollection().remove(datFaseVelocidadCollectionDatFaseVelocidad);
                    oldNomEtapaOfDatFaseVelocidadCollectionDatFaseVelocidad = em.merge(oldNomEtapaOfDatFaseVelocidadCollectionDatFaseVelocidad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomEtapa nomEtapa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomEtapa persistentNomEtapa = em.find(NomEtapa.class, nomEtapa.getIdetapa());
            Collection<DatFaseBloque> datFaseBloqueCollectionOld = persistentNomEtapa.getDatFaseBloqueCollection();
            Collection<DatFaseBloque> datFaseBloqueCollectionNew = nomEtapa.getDatFaseBloqueCollection();
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionOld = persistentNomEtapa.getDatFaseVelocidadCollection();
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionNew = nomEtapa.getDatFaseVelocidadCollection();
            List<String> illegalOrphanMessages = null;
            for (DatFaseBloque datFaseBloqueCollectionOldDatFaseBloque : datFaseBloqueCollectionOld) {
                if (!datFaseBloqueCollectionNew.contains(datFaseBloqueCollectionOldDatFaseBloque)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseBloque " + datFaseBloqueCollectionOldDatFaseBloque + " since its nomEtapa field is not nullable.");
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionOldDatFaseVelocidad : datFaseVelocidadCollectionOld) {
                if (!datFaseVelocidadCollectionNew.contains(datFaseVelocidadCollectionOldDatFaseVelocidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseVelocidad " + datFaseVelocidadCollectionOldDatFaseVelocidad + " since its nomEtapa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<DatFaseBloque> attachedDatFaseBloqueCollectionNew = new ArrayList<DatFaseBloque>();
            for (DatFaseBloque datFaseBloqueCollectionNewDatFaseBloqueToAttach : datFaseBloqueCollectionNew) {
                datFaseBloqueCollectionNewDatFaseBloqueToAttach = em.getReference(datFaseBloqueCollectionNewDatFaseBloqueToAttach.getClass(), datFaseBloqueCollectionNewDatFaseBloqueToAttach.getDatFaseBloquePK());
                attachedDatFaseBloqueCollectionNew.add(datFaseBloqueCollectionNewDatFaseBloqueToAttach);
            }
            datFaseBloqueCollectionNew = attachedDatFaseBloqueCollectionNew;
            nomEtapa.setDatFaseBloqueCollection(datFaseBloqueCollectionNew);
            Collection<DatFaseVelocidad> attachedDatFaseVelocidadCollectionNew = new ArrayList<DatFaseVelocidad>();
            for (DatFaseVelocidad datFaseVelocidadCollectionNewDatFaseVelocidadToAttach : datFaseVelocidadCollectionNew) {
                datFaseVelocidadCollectionNewDatFaseVelocidadToAttach = em.getReference(datFaseVelocidadCollectionNewDatFaseVelocidadToAttach.getClass(), datFaseVelocidadCollectionNewDatFaseVelocidadToAttach.getDatFaseVelocidadPK());
                attachedDatFaseVelocidadCollectionNew.add(datFaseVelocidadCollectionNewDatFaseVelocidadToAttach);
            }
            datFaseVelocidadCollectionNew = attachedDatFaseVelocidadCollectionNew;
            nomEtapa.setDatFaseVelocidadCollection(datFaseVelocidadCollectionNew);
            nomEtapa = em.merge(nomEtapa);
            for (DatFaseBloque datFaseBloqueCollectionNewDatFaseBloque : datFaseBloqueCollectionNew) {
                if (!datFaseBloqueCollectionOld.contains(datFaseBloqueCollectionNewDatFaseBloque)) {
                    NomEtapa oldNomEtapaOfDatFaseBloqueCollectionNewDatFaseBloque = datFaseBloqueCollectionNewDatFaseBloque.getNomEtapa();
                    datFaseBloqueCollectionNewDatFaseBloque.setNomEtapa(nomEtapa);
                    datFaseBloqueCollectionNewDatFaseBloque = em.merge(datFaseBloqueCollectionNewDatFaseBloque);
                    if (oldNomEtapaOfDatFaseBloqueCollectionNewDatFaseBloque != null && !oldNomEtapaOfDatFaseBloqueCollectionNewDatFaseBloque.equals(nomEtapa)) {
                        oldNomEtapaOfDatFaseBloqueCollectionNewDatFaseBloque.getDatFaseBloqueCollection().remove(datFaseBloqueCollectionNewDatFaseBloque);
                        oldNomEtapaOfDatFaseBloqueCollectionNewDatFaseBloque = em.merge(oldNomEtapaOfDatFaseBloqueCollectionNewDatFaseBloque);
                    }
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionNewDatFaseVelocidad : datFaseVelocidadCollectionNew) {
                if (!datFaseVelocidadCollectionOld.contains(datFaseVelocidadCollectionNewDatFaseVelocidad)) {
                    NomEtapa oldNomEtapaOfDatFaseVelocidadCollectionNewDatFaseVelocidad = datFaseVelocidadCollectionNewDatFaseVelocidad.getNomEtapa();
                    datFaseVelocidadCollectionNewDatFaseVelocidad.setNomEtapa(nomEtapa);
                    datFaseVelocidadCollectionNewDatFaseVelocidad = em.merge(datFaseVelocidadCollectionNewDatFaseVelocidad);
                    if (oldNomEtapaOfDatFaseVelocidadCollectionNewDatFaseVelocidad != null && !oldNomEtapaOfDatFaseVelocidadCollectionNewDatFaseVelocidad.equals(nomEtapa)) {
                        oldNomEtapaOfDatFaseVelocidadCollectionNewDatFaseVelocidad.getDatFaseVelocidadCollection().remove(datFaseVelocidadCollectionNewDatFaseVelocidad);
                        oldNomEtapaOfDatFaseVelocidadCollectionNewDatFaseVelocidad = em.merge(oldNomEtapaOfDatFaseVelocidadCollectionNewDatFaseVelocidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomEtapa.getIdetapa();
                if (findNomEtapa(id) == null) {
                    throw new NonexistentEntityException("The nomEtapa with id " + id + " no longer exists.");
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
            NomEtapa nomEtapa;
            try {
                nomEtapa = em.getReference(NomEtapa.class, id);
                nomEtapa.getIdetapa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomEtapa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DatFaseBloque> datFaseBloqueCollectionOrphanCheck = nomEtapa.getDatFaseBloqueCollection();
            for (DatFaseBloque datFaseBloqueCollectionOrphanCheckDatFaseBloque : datFaseBloqueCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomEtapa (" + nomEtapa + ") cannot be destroyed since the DatFaseBloque " + datFaseBloqueCollectionOrphanCheckDatFaseBloque + " in its datFaseBloqueCollection field has a non-nullable nomEtapa field.");
            }
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionOrphanCheck = nomEtapa.getDatFaseVelocidadCollection();
            for (DatFaseVelocidad datFaseVelocidadCollectionOrphanCheckDatFaseVelocidad : datFaseVelocidadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomEtapa (" + nomEtapa + ") cannot be destroyed since the DatFaseVelocidad " + datFaseVelocidadCollectionOrphanCheckDatFaseVelocidad + " in its datFaseVelocidadCollection field has a non-nullable nomEtapa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nomEtapa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomEtapa> findNomEtapaEntities() {
        return findNomEtapaEntities(true, -1, -1);
    }

    public List<NomEtapa> findNomEtapaEntities(int maxResults, int firstResult) {
        return findNomEtapaEntities(false, maxResults, firstResult);
    }

    private List<NomEtapa> findNomEtapaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomEtapa.class));
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

    public NomEtapa findNomEtapa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomEtapa.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomEtapaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomEtapa> rt = cq.from(NomEtapa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
