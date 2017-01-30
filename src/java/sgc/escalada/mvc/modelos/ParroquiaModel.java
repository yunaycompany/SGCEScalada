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
import sgc.escalada.mvc.entidades.DatDireccion;
import sgc.escalada.mvc.entidades.NomCiudad;
import sgc.escalada.mvc.entidades.NomLugar;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.entidades.NomParroquia_;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class ParroquiaModel implements Serializable {

    public ParroquiaModel(EntityManagerFactory emf) {
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

    public void create(NomParroquia nomParroquia) {
        if (nomParroquia.getNomLugarCollection() == null) {
            nomParroquia.setNomLugarCollection(new ArrayList<NomLugar>());
        }
        if (nomParroquia.getDatDireccionCollection() == null) {
            nomParroquia.setDatDireccionCollection(new ArrayList<DatDireccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomCiudad idciudad = nomParroquia.getIdciudad();
            if (idciudad != null) {
                idciudad = em.getReference(idciudad.getClass(), idciudad.getIdciudad());
                nomParroquia.setIdciudad(idciudad);
            }
            Collection<NomLugar> attachedNomLugarCollection = new ArrayList<NomLugar>();
            for (NomLugar nomLugarCollectionNomLugarToAttach : nomParroquia.getNomLugarCollection()) {
                nomLugarCollectionNomLugarToAttach = em.getReference(nomLugarCollectionNomLugarToAttach.getClass(), nomLugarCollectionNomLugarToAttach.getIdlugar());
                attachedNomLugarCollection.add(nomLugarCollectionNomLugarToAttach);
            }
            nomParroquia.setNomLugarCollection(attachedNomLugarCollection);
            Collection<DatDireccion> attachedDatDireccionCollection = new ArrayList<DatDireccion>();
            for (DatDireccion datDireccionCollectionDatDireccionToAttach : nomParroquia.getDatDireccionCollection()) {
                datDireccionCollectionDatDireccionToAttach = em.getReference(datDireccionCollectionDatDireccionToAttach.getClass(), datDireccionCollectionDatDireccionToAttach.getDatDireccionPK());
                attachedDatDireccionCollection.add(datDireccionCollectionDatDireccionToAttach);
            }
            nomParroquia.setDatDireccionCollection(attachedDatDireccionCollection);
            em.persist(nomParroquia);
            if (idciudad != null) {
                idciudad.getNomParroquiaCollection().add(nomParroquia);
                idciudad = em.merge(idciudad);
            }
            for (NomLugar nomLugarCollectionNomLugar : nomParroquia.getNomLugarCollection()) {
                NomParroquia oldIdparroquiaOfNomLugarCollectionNomLugar = nomLugarCollectionNomLugar.getIdparroquia();
                nomLugarCollectionNomLugar.setIdparroquia(nomParroquia);
                nomLugarCollectionNomLugar = em.merge(nomLugarCollectionNomLugar);
                if (oldIdparroquiaOfNomLugarCollectionNomLugar != null) {
                    oldIdparroquiaOfNomLugarCollectionNomLugar.getNomLugarCollection().remove(nomLugarCollectionNomLugar);
                    oldIdparroquiaOfNomLugarCollectionNomLugar = em.merge(oldIdparroquiaOfNomLugarCollectionNomLugar);
                }
            }
            for (DatDireccion datDireccionCollectionDatDireccion : nomParroquia.getDatDireccionCollection()) {
                NomParroquia oldNomParroquiaOfDatDireccionCollectionDatDireccion = datDireccionCollectionDatDireccion.getNomParroquia();
                datDireccionCollectionDatDireccion.setNomParroquia(nomParroquia);
                datDireccionCollectionDatDireccion = em.merge(datDireccionCollectionDatDireccion);
                if (oldNomParroquiaOfDatDireccionCollectionDatDireccion != null) {
                    oldNomParroquiaOfDatDireccionCollectionDatDireccion.getDatDireccionCollection().remove(datDireccionCollectionDatDireccion);
                    oldNomParroquiaOfDatDireccionCollectionDatDireccion = em.merge(oldNomParroquiaOfDatDireccionCollectionDatDireccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomParroquia nomParroquia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomParroquia persistentNomParroquia = em.find(NomParroquia.class, nomParroquia.getIdparroquia());
            NomCiudad idciudadOld = persistentNomParroquia.getIdciudad();
            NomCiudad idciudadNew = nomParroquia.getIdciudad();
            Collection<NomLugar> nomLugarCollectionOld = persistentNomParroquia.getNomLugarCollection();
            Collection<NomLugar> nomLugarCollectionNew = nomParroquia.getNomLugarCollection();
            Collection<DatDireccion> datDireccionCollectionOld = persistentNomParroquia.getDatDireccionCollection();
            Collection<DatDireccion> datDireccionCollectionNew = nomParroquia.getDatDireccionCollection();
            List<String> illegalOrphanMessages = null;
            for (NomLugar nomLugarCollectionOldNomLugar : nomLugarCollectionOld) {
                if (!nomLugarCollectionNew.contains(nomLugarCollectionOldNomLugar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NomLugar " + nomLugarCollectionOldNomLugar + " since its idparroquia field is not nullable.");
                }
            }
            for (DatDireccion datDireccionCollectionOldDatDireccion : datDireccionCollectionOld) {
                if (!datDireccionCollectionNew.contains(datDireccionCollectionOldDatDireccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatDireccion " + datDireccionCollectionOldDatDireccion + " since its nomParroquia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idciudadNew != null) {
                idciudadNew = em.getReference(idciudadNew.getClass(), idciudadNew.getIdciudad());
                nomParroquia.setIdciudad(idciudadNew);
            }
            Collection<NomLugar> attachedNomLugarCollectionNew = new ArrayList<NomLugar>();
            for (NomLugar nomLugarCollectionNewNomLugarToAttach : nomLugarCollectionNew) {
                nomLugarCollectionNewNomLugarToAttach = em.getReference(nomLugarCollectionNewNomLugarToAttach.getClass(), nomLugarCollectionNewNomLugarToAttach.getIdlugar());
                attachedNomLugarCollectionNew.add(nomLugarCollectionNewNomLugarToAttach);
            }
            nomLugarCollectionNew = attachedNomLugarCollectionNew;
            nomParroquia.setNomLugarCollection(nomLugarCollectionNew);
            Collection<DatDireccion> attachedDatDireccionCollectionNew = new ArrayList<DatDireccion>();
            for (DatDireccion datDireccionCollectionNewDatDireccionToAttach : datDireccionCollectionNew) {
                datDireccionCollectionNewDatDireccionToAttach = em.getReference(datDireccionCollectionNewDatDireccionToAttach.getClass(), datDireccionCollectionNewDatDireccionToAttach.getDatDireccionPK());
                attachedDatDireccionCollectionNew.add(datDireccionCollectionNewDatDireccionToAttach);
            }
            datDireccionCollectionNew = attachedDatDireccionCollectionNew;
            nomParroquia.setDatDireccionCollection(datDireccionCollectionNew);
            nomParroquia = em.merge(nomParroquia);
            if (idciudadOld != null && !idciudadOld.equals(idciudadNew)) {
                idciudadOld.getNomParroquiaCollection().remove(nomParroquia);
                idciudadOld = em.merge(idciudadOld);
            }
            if (idciudadNew != null && !idciudadNew.equals(idciudadOld)) {
                idciudadNew.getNomParroquiaCollection().add(nomParroquia);
                idciudadNew = em.merge(idciudadNew);
            }
            for (NomLugar nomLugarCollectionNewNomLugar : nomLugarCollectionNew) {
                if (!nomLugarCollectionOld.contains(nomLugarCollectionNewNomLugar)) {
                    NomParroquia oldIdparroquiaOfNomLugarCollectionNewNomLugar = nomLugarCollectionNewNomLugar.getIdparroquia();
                    nomLugarCollectionNewNomLugar.setIdparroquia(nomParroquia);
                    nomLugarCollectionNewNomLugar = em.merge(nomLugarCollectionNewNomLugar);
                    if (oldIdparroquiaOfNomLugarCollectionNewNomLugar != null && !oldIdparroquiaOfNomLugarCollectionNewNomLugar.equals(nomParroquia)) {
                        oldIdparroquiaOfNomLugarCollectionNewNomLugar.getNomLugarCollection().remove(nomLugarCollectionNewNomLugar);
                        oldIdparroquiaOfNomLugarCollectionNewNomLugar = em.merge(oldIdparroquiaOfNomLugarCollectionNewNomLugar);
                    }
                }
            }
            for (DatDireccion datDireccionCollectionNewDatDireccion : datDireccionCollectionNew) {
                if (!datDireccionCollectionOld.contains(datDireccionCollectionNewDatDireccion)) {
                    NomParroquia oldNomParroquiaOfDatDireccionCollectionNewDatDireccion = datDireccionCollectionNewDatDireccion.getNomParroquia();
                    datDireccionCollectionNewDatDireccion.setNomParroquia(nomParroquia);
                    datDireccionCollectionNewDatDireccion = em.merge(datDireccionCollectionNewDatDireccion);
                    if (oldNomParroquiaOfDatDireccionCollectionNewDatDireccion != null && !oldNomParroquiaOfDatDireccionCollectionNewDatDireccion.equals(nomParroquia)) {
                        oldNomParroquiaOfDatDireccionCollectionNewDatDireccion.getDatDireccionCollection().remove(datDireccionCollectionNewDatDireccion);
                        oldNomParroquiaOfDatDireccionCollectionNewDatDireccion = em.merge(oldNomParroquiaOfDatDireccionCollectionNewDatDireccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomParroquia.getIdparroquia();
                if (findNomParroquia(id) == null) {
                    throw new NonexistentEntityException("The nomParroquia with id " + id + " no longer exists.");
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
            NomParroquia nomParroquia;
            try {
                nomParroquia = em.getReference(NomParroquia.class, id);
                nomParroquia.getIdparroquia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomParroquia with id " + id + " no longer exists.", enfe);
            }
//            List<String> illegalOrphanMessages = null;
//            Collection<NomLugar> nomLugarCollectionOrphanCheck = nomParroquia.getNomLugarCollection();
//            for (NomLugar nomLugarCollectionOrphanCheckNomLugar : nomLugarCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomParroquia (" + nomParroquia + ") cannot be destroyed since the NomLugar " + nomLugarCollectionOrphanCheckNomLugar + " in its nomLugarCollection field has a non-nullable idparroquia field.");
//            }
//            Collection<DatDireccion> datDireccionCollectionOrphanCheck = nomParroquia.getDatDireccionCollection();
//            for (DatDireccion datDireccionCollectionOrphanCheckDatDireccion : datDireccionCollectionOrphanCheck) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("This NomParroquia (" + nomParroquia + ") cannot be destroyed since the DatDireccion " + datDireccionCollectionOrphanCheckDatDireccion + " in its datDireccionCollection field has a non-nullable nomParroquia field.");
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
            NomCiudad idciudad = nomParroquia.getIdciudad();
            if (idciudad != null) {
                idciudad.getNomParroquiaCollection().remove(nomParroquia);
                idciudad = em.merge(idciudad);
            }
            em.remove(nomParroquia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomParroquia> findNomParroquiaEntities() {
        return findNomParroquiaEntities(true, -1, -1);
    }

    public List<NomParroquia> findNomParroquiaEntities(int maxResults, int firstResult) {
        return findNomParroquiaEntities(false, maxResults, firstResult);
    }

    private List<NomParroquia> findNomParroquiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomParroquia.class));
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

    public NomParroquia findNomParroquia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomParroquia.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomParroquiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomParroquia> rt = cq.from(NomParroquia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<NomParroquia> findNomParroquiaEntitiesByIdCiudad(NomCiudad nomCiudad) {
        EntityManager em = getEntityManager();
        try{
            CriteriaBuilder cb = this.emf.getCriteriaBuilder();
            CriteriaQuery<NomParroquia> cq = cb.createQuery(NomParroquia.class);
            Root<NomParroquia> pet = cq.from(NomParroquia.class);
            cq.where(pet.get(NomParroquia_.idciudad).in(nomCiudad));
            Query q = em.createQuery(cq);
            
            return q.getResultList();
        }finally{
            em.close();
        }
    }
    
}
