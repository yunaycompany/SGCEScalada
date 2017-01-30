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
import sgc.escalada.mvc.entidades.DatAllround;
import sgc.escalada.mvc.entidades.DatFaseBloque;
import sgc.escalada.mvc.entidades.DatFaseDificultad;
import sgc.escalada.mvc.entidades.DatFaseVelocidad;
import sgc.escalada.mvc.entidades.HisResultado;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Ariam
 */
public class CategoriaModel implements Serializable {

    public CategoriaModel(EntityManagerFactory emf) {
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

    public void create(NomCategoria nomCategoria) {
        if (nomCategoria.getDatFaseBloqueCollection() == null) {
            nomCategoria.setDatFaseBloqueCollection(new ArrayList<DatFaseBloque>());
        }
        if (nomCategoria.getDatFaseDificultadCollection() == null) {
            nomCategoria.setDatFaseDificultadCollection(new ArrayList<DatFaseDificultad>());
        }
        if (nomCategoria.getDatAllroundCollection() == null) {
            nomCategoria.setDatAllroundCollection(new ArrayList<DatAllround>());
        }
        if (nomCategoria.getHisResultadoCollection() == null) {
            nomCategoria.setHisResultadoCollection(new ArrayList<HisResultado>());
        }
        if (nomCategoria.getDatFaseVelocidadCollection() == null) {
            nomCategoria.setDatFaseVelocidadCollection(new ArrayList<DatFaseVelocidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<DatFaseBloque> attachedDatFaseBloqueCollection = new ArrayList<DatFaseBloque>();
            for (DatFaseBloque datFaseBloqueCollectionDatFaseBloqueToAttach : nomCategoria.getDatFaseBloqueCollection()) {
                datFaseBloqueCollectionDatFaseBloqueToAttach = em.getReference(datFaseBloqueCollectionDatFaseBloqueToAttach.getClass(), datFaseBloqueCollectionDatFaseBloqueToAttach.getDatFaseBloquePK());
                attachedDatFaseBloqueCollection.add(datFaseBloqueCollectionDatFaseBloqueToAttach);
            }
            nomCategoria.setDatFaseBloqueCollection(attachedDatFaseBloqueCollection);
            Collection<DatFaseDificultad> attachedDatFaseDificultadCollection = new ArrayList<DatFaseDificultad>();
            for (DatFaseDificultad datFaseDificultadCollectionDatFaseDificultadToAttach : nomCategoria.getDatFaseDificultadCollection()) {
                datFaseDificultadCollectionDatFaseDificultadToAttach = em.getReference(datFaseDificultadCollectionDatFaseDificultadToAttach.getClass(), datFaseDificultadCollectionDatFaseDificultadToAttach.getDatFaseDificultadPK());
                attachedDatFaseDificultadCollection.add(datFaseDificultadCollectionDatFaseDificultadToAttach);
            }
            nomCategoria.setDatFaseDificultadCollection(attachedDatFaseDificultadCollection);
            Collection<DatAllround> attachedDatAllroundCollection = new ArrayList<DatAllround>();
            for (DatAllround datAllroundCollectionDatAllroundToAttach : nomCategoria.getDatAllroundCollection()) {
                datAllroundCollectionDatAllroundToAttach = em.getReference(datAllroundCollectionDatAllroundToAttach.getClass(), datAllroundCollectionDatAllroundToAttach.getDatAllroundPK());
                attachedDatAllroundCollection.add(datAllroundCollectionDatAllroundToAttach);
            }
            nomCategoria.setDatAllroundCollection(attachedDatAllroundCollection);
            Collection<HisResultado> attachedHisResultadoCollection = new ArrayList<HisResultado>();
            for (HisResultado hisResultadoCollectionHisResultadoToAttach : nomCategoria.getHisResultadoCollection()) {
                hisResultadoCollectionHisResultadoToAttach = em.getReference(hisResultadoCollectionHisResultadoToAttach.getClass(), hisResultadoCollectionHisResultadoToAttach.getHisResultadoPK());
                attachedHisResultadoCollection.add(hisResultadoCollectionHisResultadoToAttach);
            }
            nomCategoria.setHisResultadoCollection(attachedHisResultadoCollection);
            Collection<DatFaseVelocidad> attachedDatFaseVelocidadCollection = new ArrayList<DatFaseVelocidad>();
            for (DatFaseVelocidad datFaseVelocidadCollectionDatFaseVelocidadToAttach : nomCategoria.getDatFaseVelocidadCollection()) {
                datFaseVelocidadCollectionDatFaseVelocidadToAttach = em.getReference(datFaseVelocidadCollectionDatFaseVelocidadToAttach.getClass(), datFaseVelocidadCollectionDatFaseVelocidadToAttach.getDatFaseVelocidadPK());
                attachedDatFaseVelocidadCollection.add(datFaseVelocidadCollectionDatFaseVelocidadToAttach);
            }
            nomCategoria.setDatFaseVelocidadCollection(attachedDatFaseVelocidadCollection);
            em.persist(nomCategoria);
            for (DatFaseBloque datFaseBloqueCollectionDatFaseBloque : nomCategoria.getDatFaseBloqueCollection()) {
                NomCategoria oldNomCategoriaOfDatFaseBloqueCollectionDatFaseBloque = datFaseBloqueCollectionDatFaseBloque.getNomCategoria();
                datFaseBloqueCollectionDatFaseBloque.setNomCategoria(nomCategoria);
                datFaseBloqueCollectionDatFaseBloque = em.merge(datFaseBloqueCollectionDatFaseBloque);
                if (oldNomCategoriaOfDatFaseBloqueCollectionDatFaseBloque != null) {
                    oldNomCategoriaOfDatFaseBloqueCollectionDatFaseBloque.getDatFaseBloqueCollection().remove(datFaseBloqueCollectionDatFaseBloque);
                    oldNomCategoriaOfDatFaseBloqueCollectionDatFaseBloque = em.merge(oldNomCategoriaOfDatFaseBloqueCollectionDatFaseBloque);
                }
            }
            for (DatFaseDificultad datFaseDificultadCollectionDatFaseDificultad : nomCategoria.getDatFaseDificultadCollection()) {
                NomCategoria oldNomCategoriaOfDatFaseDificultadCollectionDatFaseDificultad = datFaseDificultadCollectionDatFaseDificultad.getNomCategoria();
                datFaseDificultadCollectionDatFaseDificultad.setNomCategoria(nomCategoria);
                datFaseDificultadCollectionDatFaseDificultad = em.merge(datFaseDificultadCollectionDatFaseDificultad);
                if (oldNomCategoriaOfDatFaseDificultadCollectionDatFaseDificultad != null) {
                    oldNomCategoriaOfDatFaseDificultadCollectionDatFaseDificultad.getDatFaseDificultadCollection().remove(datFaseDificultadCollectionDatFaseDificultad);
                    oldNomCategoriaOfDatFaseDificultadCollectionDatFaseDificultad = em.merge(oldNomCategoriaOfDatFaseDificultadCollectionDatFaseDificultad);
                }
            }
            for (DatAllround datAllroundCollectionDatAllround : nomCategoria.getDatAllroundCollection()) {
                NomCategoria oldIdcategoriaOfDatAllroundCollectionDatAllround = datAllroundCollectionDatAllround.getIdcategoria();
                datAllroundCollectionDatAllround.setIdcategoria(nomCategoria);
                datAllroundCollectionDatAllround = em.merge(datAllroundCollectionDatAllround);
                if (oldIdcategoriaOfDatAllroundCollectionDatAllround != null) {
                    oldIdcategoriaOfDatAllroundCollectionDatAllround.getDatAllroundCollection().remove(datAllroundCollectionDatAllround);
                    oldIdcategoriaOfDatAllroundCollectionDatAllround = em.merge(oldIdcategoriaOfDatAllroundCollectionDatAllround);
                }
            }
            for (HisResultado hisResultadoCollectionHisResultado : nomCategoria.getHisResultadoCollection()) {
                NomCategoria oldIdcategoriaOfHisResultadoCollectionHisResultado = hisResultadoCollectionHisResultado.getIdcategoria();
                hisResultadoCollectionHisResultado.setIdcategoria(nomCategoria);
                hisResultadoCollectionHisResultado = em.merge(hisResultadoCollectionHisResultado);
                if (oldIdcategoriaOfHisResultadoCollectionHisResultado != null) {
                    oldIdcategoriaOfHisResultadoCollectionHisResultado.getHisResultadoCollection().remove(hisResultadoCollectionHisResultado);
                    oldIdcategoriaOfHisResultadoCollectionHisResultado = em.merge(oldIdcategoriaOfHisResultadoCollectionHisResultado);
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionDatFaseVelocidad : nomCategoria.getDatFaseVelocidadCollection()) {
                NomCategoria oldNomCategoriaOfDatFaseVelocidadCollectionDatFaseVelocidad = datFaseVelocidadCollectionDatFaseVelocidad.getNomCategoria();
                datFaseVelocidadCollectionDatFaseVelocidad.setNomCategoria(nomCategoria);
                datFaseVelocidadCollectionDatFaseVelocidad = em.merge(datFaseVelocidadCollectionDatFaseVelocidad);
                if (oldNomCategoriaOfDatFaseVelocidadCollectionDatFaseVelocidad != null) {
                    oldNomCategoriaOfDatFaseVelocidadCollectionDatFaseVelocidad.getDatFaseVelocidadCollection().remove(datFaseVelocidadCollectionDatFaseVelocidad);
                    oldNomCategoriaOfDatFaseVelocidadCollectionDatFaseVelocidad = em.merge(oldNomCategoriaOfDatFaseVelocidadCollectionDatFaseVelocidad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NomCategoria nomCategoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomCategoria persistentNomCategoria = em.find(NomCategoria.class, nomCategoria.getIdcategoria());
            Collection<DatFaseBloque> datFaseBloqueCollectionOld = persistentNomCategoria.getDatFaseBloqueCollection();
            Collection<DatFaseBloque> datFaseBloqueCollectionNew = nomCategoria.getDatFaseBloqueCollection();
            Collection<DatFaseDificultad> datFaseDificultadCollectionOld = persistentNomCategoria.getDatFaseDificultadCollection();
            Collection<DatFaseDificultad> datFaseDificultadCollectionNew = nomCategoria.getDatFaseDificultadCollection();
            Collection<DatAllround> datAllroundCollectionOld = persistentNomCategoria.getDatAllroundCollection();
            Collection<DatAllround> datAllroundCollectionNew = nomCategoria.getDatAllroundCollection();
            Collection<HisResultado> hisResultadoCollectionOld = persistentNomCategoria.getHisResultadoCollection();
            Collection<HisResultado> hisResultadoCollectionNew = nomCategoria.getHisResultadoCollection();
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionOld = persistentNomCategoria.getDatFaseVelocidadCollection();
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionNew = nomCategoria.getDatFaseVelocidadCollection();
            List<String> illegalOrphanMessages = null;
            for (DatFaseBloque datFaseBloqueCollectionOldDatFaseBloque : datFaseBloqueCollectionOld) {
                if (!datFaseBloqueCollectionNew.contains(datFaseBloqueCollectionOldDatFaseBloque)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseBloque " + datFaseBloqueCollectionOldDatFaseBloque + " since its nomCategoria field is not nullable.");
                }
            }
            for (DatFaseDificultad datFaseDificultadCollectionOldDatFaseDificultad : datFaseDificultadCollectionOld) {
                if (!datFaseDificultadCollectionNew.contains(datFaseDificultadCollectionOldDatFaseDificultad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseDificultad " + datFaseDificultadCollectionOldDatFaseDificultad + " since its nomCategoria field is not nullable.");
                }
            }
            for (DatAllround datAllroundCollectionOldDatAllround : datAllroundCollectionOld) {
                if (!datAllroundCollectionNew.contains(datAllroundCollectionOldDatAllround)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatAllround " + datAllroundCollectionOldDatAllround + " since its idcategoria field is not nullable.");
                }
            }
            for (HisResultado hisResultadoCollectionOldHisResultado : hisResultadoCollectionOld) {
                if (!hisResultadoCollectionNew.contains(hisResultadoCollectionOldHisResultado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HisResultado " + hisResultadoCollectionOldHisResultado + " since its idcategoria field is not nullable.");
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionOldDatFaseVelocidad : datFaseVelocidadCollectionOld) {
                if (!datFaseVelocidadCollectionNew.contains(datFaseVelocidadCollectionOldDatFaseVelocidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseVelocidad " + datFaseVelocidadCollectionOldDatFaseVelocidad + " since its nomCategoria field is not nullable.");
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
            nomCategoria.setDatFaseBloqueCollection(datFaseBloqueCollectionNew);
            Collection<DatFaseDificultad> attachedDatFaseDificultadCollectionNew = new ArrayList<DatFaseDificultad>();
            for (DatFaseDificultad datFaseDificultadCollectionNewDatFaseDificultadToAttach : datFaseDificultadCollectionNew) {
                datFaseDificultadCollectionNewDatFaseDificultadToAttach = em.getReference(datFaseDificultadCollectionNewDatFaseDificultadToAttach.getClass(), datFaseDificultadCollectionNewDatFaseDificultadToAttach.getDatFaseDificultadPK());
                attachedDatFaseDificultadCollectionNew.add(datFaseDificultadCollectionNewDatFaseDificultadToAttach);
            }
            datFaseDificultadCollectionNew = attachedDatFaseDificultadCollectionNew;
            nomCategoria.setDatFaseDificultadCollection(datFaseDificultadCollectionNew);
            Collection<DatAllround> attachedDatAllroundCollectionNew = new ArrayList<DatAllround>();
            for (DatAllround datAllroundCollectionNewDatAllroundToAttach : datAllroundCollectionNew) {
                datAllroundCollectionNewDatAllroundToAttach = em.getReference(datAllroundCollectionNewDatAllroundToAttach.getClass(), datAllroundCollectionNewDatAllroundToAttach.getDatAllroundPK());
                attachedDatAllroundCollectionNew.add(datAllroundCollectionNewDatAllroundToAttach);
            }
            datAllroundCollectionNew = attachedDatAllroundCollectionNew;
            nomCategoria.setDatAllroundCollection(datAllroundCollectionNew);
            Collection<HisResultado> attachedHisResultadoCollectionNew = new ArrayList<HisResultado>();
            for (HisResultado hisResultadoCollectionNewHisResultadoToAttach : hisResultadoCollectionNew) {
                hisResultadoCollectionNewHisResultadoToAttach = em.getReference(hisResultadoCollectionNewHisResultadoToAttach.getClass(), hisResultadoCollectionNewHisResultadoToAttach.getHisResultadoPK());
                attachedHisResultadoCollectionNew.add(hisResultadoCollectionNewHisResultadoToAttach);
            }
            hisResultadoCollectionNew = attachedHisResultadoCollectionNew;
            nomCategoria.setHisResultadoCollection(hisResultadoCollectionNew);
            Collection<DatFaseVelocidad> attachedDatFaseVelocidadCollectionNew = new ArrayList<DatFaseVelocidad>();
            for (DatFaseVelocidad datFaseVelocidadCollectionNewDatFaseVelocidadToAttach : datFaseVelocidadCollectionNew) {
                datFaseVelocidadCollectionNewDatFaseVelocidadToAttach = em.getReference(datFaseVelocidadCollectionNewDatFaseVelocidadToAttach.getClass(), datFaseVelocidadCollectionNewDatFaseVelocidadToAttach.getDatFaseVelocidadPK());
                attachedDatFaseVelocidadCollectionNew.add(datFaseVelocidadCollectionNewDatFaseVelocidadToAttach);
            }
            datFaseVelocidadCollectionNew = attachedDatFaseVelocidadCollectionNew;
            nomCategoria.setDatFaseVelocidadCollection(datFaseVelocidadCollectionNew);
            nomCategoria = em.merge(nomCategoria);
            for (DatFaseBloque datFaseBloqueCollectionNewDatFaseBloque : datFaseBloqueCollectionNew) {
                if (!datFaseBloqueCollectionOld.contains(datFaseBloqueCollectionNewDatFaseBloque)) {
                    NomCategoria oldNomCategoriaOfDatFaseBloqueCollectionNewDatFaseBloque = datFaseBloqueCollectionNewDatFaseBloque.getNomCategoria();
                    datFaseBloqueCollectionNewDatFaseBloque.setNomCategoria(nomCategoria);
                    datFaseBloqueCollectionNewDatFaseBloque = em.merge(datFaseBloqueCollectionNewDatFaseBloque);
                    if (oldNomCategoriaOfDatFaseBloqueCollectionNewDatFaseBloque != null && !oldNomCategoriaOfDatFaseBloqueCollectionNewDatFaseBloque.equals(nomCategoria)) {
                        oldNomCategoriaOfDatFaseBloqueCollectionNewDatFaseBloque.getDatFaseBloqueCollection().remove(datFaseBloqueCollectionNewDatFaseBloque);
                        oldNomCategoriaOfDatFaseBloqueCollectionNewDatFaseBloque = em.merge(oldNomCategoriaOfDatFaseBloqueCollectionNewDatFaseBloque);
                    }
                }
            }
            for (DatFaseDificultad datFaseDificultadCollectionNewDatFaseDificultad : datFaseDificultadCollectionNew) {
                if (!datFaseDificultadCollectionOld.contains(datFaseDificultadCollectionNewDatFaseDificultad)) {
                    NomCategoria oldNomCategoriaOfDatFaseDificultadCollectionNewDatFaseDificultad = datFaseDificultadCollectionNewDatFaseDificultad.getNomCategoria();
                    datFaseDificultadCollectionNewDatFaseDificultad.setNomCategoria(nomCategoria);
                    datFaseDificultadCollectionNewDatFaseDificultad = em.merge(datFaseDificultadCollectionNewDatFaseDificultad);
                    if (oldNomCategoriaOfDatFaseDificultadCollectionNewDatFaseDificultad != null && !oldNomCategoriaOfDatFaseDificultadCollectionNewDatFaseDificultad.equals(nomCategoria)) {
                        oldNomCategoriaOfDatFaseDificultadCollectionNewDatFaseDificultad.getDatFaseDificultadCollection().remove(datFaseDificultadCollectionNewDatFaseDificultad);
                        oldNomCategoriaOfDatFaseDificultadCollectionNewDatFaseDificultad = em.merge(oldNomCategoriaOfDatFaseDificultadCollectionNewDatFaseDificultad);
                    }
                }
            }
            for (DatAllround datAllroundCollectionNewDatAllround : datAllroundCollectionNew) {
                if (!datAllroundCollectionOld.contains(datAllroundCollectionNewDatAllround)) {
                    NomCategoria oldIdcategoriaOfDatAllroundCollectionNewDatAllround = datAllroundCollectionNewDatAllround.getIdcategoria();
                    datAllroundCollectionNewDatAllround.setIdcategoria(nomCategoria);
                    datAllroundCollectionNewDatAllround = em.merge(datAllroundCollectionNewDatAllround);
                    if (oldIdcategoriaOfDatAllroundCollectionNewDatAllround != null && !oldIdcategoriaOfDatAllroundCollectionNewDatAllround.equals(nomCategoria)) {
                        oldIdcategoriaOfDatAllroundCollectionNewDatAllround.getDatAllroundCollection().remove(datAllroundCollectionNewDatAllround);
                        oldIdcategoriaOfDatAllroundCollectionNewDatAllround = em.merge(oldIdcategoriaOfDatAllroundCollectionNewDatAllround);
                    }
                }
            }
            for (HisResultado hisResultadoCollectionNewHisResultado : hisResultadoCollectionNew) {
                if (!hisResultadoCollectionOld.contains(hisResultadoCollectionNewHisResultado)) {
                    NomCategoria oldIdcategoriaOfHisResultadoCollectionNewHisResultado = hisResultadoCollectionNewHisResultado.getIdcategoria();
                    hisResultadoCollectionNewHisResultado.setIdcategoria(nomCategoria);
                    hisResultadoCollectionNewHisResultado = em.merge(hisResultadoCollectionNewHisResultado);
                    if (oldIdcategoriaOfHisResultadoCollectionNewHisResultado != null && !oldIdcategoriaOfHisResultadoCollectionNewHisResultado.equals(nomCategoria)) {
                        oldIdcategoriaOfHisResultadoCollectionNewHisResultado.getHisResultadoCollection().remove(hisResultadoCollectionNewHisResultado);
                        oldIdcategoriaOfHisResultadoCollectionNewHisResultado = em.merge(oldIdcategoriaOfHisResultadoCollectionNewHisResultado);
                    }
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionNewDatFaseVelocidad : datFaseVelocidadCollectionNew) {
                if (!datFaseVelocidadCollectionOld.contains(datFaseVelocidadCollectionNewDatFaseVelocidad)) {
                    NomCategoria oldNomCategoriaOfDatFaseVelocidadCollectionNewDatFaseVelocidad = datFaseVelocidadCollectionNewDatFaseVelocidad.getNomCategoria();
                    datFaseVelocidadCollectionNewDatFaseVelocidad.setNomCategoria(nomCategoria);
                    datFaseVelocidadCollectionNewDatFaseVelocidad = em.merge(datFaseVelocidadCollectionNewDatFaseVelocidad);
                    if (oldNomCategoriaOfDatFaseVelocidadCollectionNewDatFaseVelocidad != null && !oldNomCategoriaOfDatFaseVelocidadCollectionNewDatFaseVelocidad.equals(nomCategoria)) {
                        oldNomCategoriaOfDatFaseVelocidadCollectionNewDatFaseVelocidad.getDatFaseVelocidadCollection().remove(datFaseVelocidadCollectionNewDatFaseVelocidad);
                        oldNomCategoriaOfDatFaseVelocidadCollectionNewDatFaseVelocidad = em.merge(oldNomCategoriaOfDatFaseVelocidadCollectionNewDatFaseVelocidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomCategoria.getIdcategoria();
                if (findNomCategoria(id) == null) {
                    throw new NonexistentEntityException("The nomCategoria with id " + id + " no longer exists.");
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
            NomCategoria nomCategoria;
            try {
                nomCategoria = em.getReference(NomCategoria.class, id);
                nomCategoria.getIdcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomCategoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DatFaseBloque> datFaseBloqueCollectionOrphanCheck = nomCategoria.getDatFaseBloqueCollection();
            for (DatFaseBloque datFaseBloqueCollectionOrphanCheckDatFaseBloque : datFaseBloqueCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomCategoria (" + nomCategoria + ") cannot be destroyed since the DatFaseBloque " + datFaseBloqueCollectionOrphanCheckDatFaseBloque + " in its datFaseBloqueCollection field has a non-nullable nomCategoria field.");
            }
            Collection<DatFaseDificultad> datFaseDificultadCollectionOrphanCheck = nomCategoria.getDatFaseDificultadCollection();
            for (DatFaseDificultad datFaseDificultadCollectionOrphanCheckDatFaseDificultad : datFaseDificultadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomCategoria (" + nomCategoria + ") cannot be destroyed since the DatFaseDificultad " + datFaseDificultadCollectionOrphanCheckDatFaseDificultad + " in its datFaseDificultadCollection field has a non-nullable nomCategoria field.");
            }
            Collection<DatAllround> datAllroundCollectionOrphanCheck = nomCategoria.getDatAllroundCollection();
            for (DatAllround datAllroundCollectionOrphanCheckDatAllround : datAllroundCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomCategoria (" + nomCategoria + ") cannot be destroyed since the DatAllround " + datAllroundCollectionOrphanCheckDatAllround + " in its datAllroundCollection field has a non-nullable idcategoria field.");
            }
            Collection<HisResultado> hisResultadoCollectionOrphanCheck = nomCategoria.getHisResultadoCollection();
            for (HisResultado hisResultadoCollectionOrphanCheckHisResultado : hisResultadoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomCategoria (" + nomCategoria + ") cannot be destroyed since the HisResultado " + hisResultadoCollectionOrphanCheckHisResultado + " in its hisResultadoCollection field has a non-nullable idcategoria field.");
            }
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionOrphanCheck = nomCategoria.getDatFaseVelocidadCollection();
            for (DatFaseVelocidad datFaseVelocidadCollectionOrphanCheckDatFaseVelocidad : datFaseVelocidadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This NomCategoria (" + nomCategoria + ") cannot be destroyed since the DatFaseVelocidad " + datFaseVelocidadCollectionOrphanCheckDatFaseVelocidad + " in its datFaseVelocidadCollection field has a non-nullable nomCategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nomCategoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NomCategoria> findNomCategoriaEntities() {
        return findNomCategoriaEntities(true, -1, -1);
    }

    public List<NomCategoria> findNomCategoriaEntities(int maxResults, int firstResult) {
        return findNomCategoriaEntities(false, maxResults, firstResult);
    }

    private List<NomCategoria> findNomCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NomCategoria.class));
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

    public NomCategoria findNomCategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NomCategoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NomCategoria> rt = cq.from(NomCategoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
