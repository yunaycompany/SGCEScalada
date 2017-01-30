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
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.DatFaseBloque;
import sgc.escalada.mvc.entidades.DatFaseDificultad;
import sgc.escalada.mvc.entidades.DatFaseVelocidad;
import sgc.escalada.mvc.entidades.DatGaleria;
import sgc.escalada.mvc.entidades.HisResultado;
import sgc.escalada.mvc.entidades.NomLugar;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class CompetenciaModel implements Serializable {

    public CompetenciaModel(EntityManagerFactory emf) {
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

    public void create(DatCompetencia datCompetencia) {
        if (datCompetencia.getDatFaseBloqueCollection() == null) {
            datCompetencia.setDatFaseBloqueCollection(new ArrayList<DatFaseBloque>());
        }
        if (datCompetencia.getDatFaseDificultadCollection() == null) {
            datCompetencia.setDatFaseDificultadCollection(new ArrayList<DatFaseDificultad>());
        }
        if (datCompetencia.getDatAllroundCollection() == null) {
            datCompetencia.setDatAllroundCollection(new ArrayList<DatAllround>());
        }
        if (datCompetencia.getHisResultadoCollection() == null) {
            datCompetencia.setHisResultadoCollection(new ArrayList<HisResultado>());
        }
        if (datCompetencia.getDatFaseVelocidadCollection() == null) {
            datCompetencia.setDatFaseVelocidadCollection(new ArrayList<DatFaseVelocidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatGaleria datGaleria = datCompetencia.getDatGaleria();
            if (datGaleria != null) {
                datGaleria = em.getReference(datGaleria.getClass(), datGaleria.getIdmedia());
                datCompetencia.setDatGaleria(datGaleria);
            }
            NomLugar idlugar = datCompetencia.getIdlugar();
            if (idlugar != null) {
                idlugar = em.getReference(idlugar.getClass(), idlugar.getIdlugar());
                datCompetencia.setIdlugar(idlugar);
            }
            Collection<DatFaseBloque> attachedDatFaseBloqueCollection = new ArrayList<DatFaseBloque>();
            for (DatFaseBloque datFaseBloqueCollectionDatFaseBloqueToAttach : datCompetencia.getDatFaseBloqueCollection()) {
                datFaseBloqueCollectionDatFaseBloqueToAttach = em.getReference(datFaseBloqueCollectionDatFaseBloqueToAttach.getClass(), datFaseBloqueCollectionDatFaseBloqueToAttach.getDatFaseBloquePK());
                attachedDatFaseBloqueCollection.add(datFaseBloqueCollectionDatFaseBloqueToAttach);
            }
            datCompetencia.setDatFaseBloqueCollection(attachedDatFaseBloqueCollection);
            Collection<DatFaseDificultad> attachedDatFaseDificultadCollection = new ArrayList<DatFaseDificultad>();
            for (DatFaseDificultad datFaseDificultadCollectionDatFaseDificultadToAttach : datCompetencia.getDatFaseDificultadCollection()) {
                datFaseDificultadCollectionDatFaseDificultadToAttach = em.getReference(datFaseDificultadCollectionDatFaseDificultadToAttach.getClass(), datFaseDificultadCollectionDatFaseDificultadToAttach.getDatFaseDificultadPK());
                attachedDatFaseDificultadCollection.add(datFaseDificultadCollectionDatFaseDificultadToAttach);
            }
            datCompetencia.setDatFaseDificultadCollection(attachedDatFaseDificultadCollection);
            Collection<DatAllround> attachedDatAllroundCollection = new ArrayList<DatAllround>();
            for (DatAllround datAllroundCollectionDatAllroundToAttach : datCompetencia.getDatAllroundCollection()) {
                datAllroundCollectionDatAllroundToAttach = em.getReference(datAllroundCollectionDatAllroundToAttach.getClass(), datAllroundCollectionDatAllroundToAttach.getDatAllroundPK());
                attachedDatAllroundCollection.add(datAllroundCollectionDatAllroundToAttach);
            }
            datCompetencia.setDatAllroundCollection(attachedDatAllroundCollection);
            Collection<HisResultado> attachedHisResultadoCollection = new ArrayList<HisResultado>();
            for (HisResultado hisResultadoCollectionHisResultadoToAttach : datCompetencia.getHisResultadoCollection()) {
                hisResultadoCollectionHisResultadoToAttach = em.getReference(hisResultadoCollectionHisResultadoToAttach.getClass(), hisResultadoCollectionHisResultadoToAttach.getHisResultadoPK());
                attachedHisResultadoCollection.add(hisResultadoCollectionHisResultadoToAttach);
            }
            datCompetencia.setHisResultadoCollection(attachedHisResultadoCollection);
            Collection<DatFaseVelocidad> attachedDatFaseVelocidadCollection = new ArrayList<DatFaseVelocidad>();
            for (DatFaseVelocidad datFaseVelocidadCollectionDatFaseVelocidadToAttach : datCompetencia.getDatFaseVelocidadCollection()) {
                datFaseVelocidadCollectionDatFaseVelocidadToAttach = em.getReference(datFaseVelocidadCollectionDatFaseVelocidadToAttach.getClass(), datFaseVelocidadCollectionDatFaseVelocidadToAttach.getDatFaseVelocidadPK());
                attachedDatFaseVelocidadCollection.add(datFaseVelocidadCollectionDatFaseVelocidadToAttach);
            }
            datCompetencia.setDatFaseVelocidadCollection(attachedDatFaseVelocidadCollection);
            em.persist(datCompetencia);
            if (datGaleria != null) {
                DatCompetencia oldIdcompetenciaOfDatGaleria = datGaleria.getIdcompetencia();
                if (oldIdcompetenciaOfDatGaleria != null) {
                    oldIdcompetenciaOfDatGaleria.setDatGaleria(null);
                    oldIdcompetenciaOfDatGaleria = em.merge(oldIdcompetenciaOfDatGaleria);
                }
                datGaleria.setIdcompetencia(datCompetencia);
                datGaleria = em.merge(datGaleria);
            }
            if (idlugar != null) {
                idlugar.getDatCompetenciaCollection().add(datCompetencia);
                idlugar = em.merge(idlugar);
            }
            for (DatFaseBloque datFaseBloqueCollectionDatFaseBloque : datCompetencia.getDatFaseBloqueCollection()) {
                DatCompetencia oldDatCompetenciaOfDatFaseBloqueCollectionDatFaseBloque = datFaseBloqueCollectionDatFaseBloque.getDatCompetencia();
                datFaseBloqueCollectionDatFaseBloque.setDatCompetencia(datCompetencia);
                datFaseBloqueCollectionDatFaseBloque = em.merge(datFaseBloqueCollectionDatFaseBloque);
                if (oldDatCompetenciaOfDatFaseBloqueCollectionDatFaseBloque != null) {
                    oldDatCompetenciaOfDatFaseBloqueCollectionDatFaseBloque.getDatFaseBloqueCollection().remove(datFaseBloqueCollectionDatFaseBloque);
                    oldDatCompetenciaOfDatFaseBloqueCollectionDatFaseBloque = em.merge(oldDatCompetenciaOfDatFaseBloqueCollectionDatFaseBloque);
                }
            }
            for (DatFaseDificultad datFaseDificultadCollectionDatFaseDificultad : datCompetencia.getDatFaseDificultadCollection()) {
                DatCompetencia oldDatCompetenciaOfDatFaseDificultadCollectionDatFaseDificultad = datFaseDificultadCollectionDatFaseDificultad.getDatCompetencia();
                datFaseDificultadCollectionDatFaseDificultad.setDatCompetencia(datCompetencia);
                datFaseDificultadCollectionDatFaseDificultad = em.merge(datFaseDificultadCollectionDatFaseDificultad);
                if (oldDatCompetenciaOfDatFaseDificultadCollectionDatFaseDificultad != null) {
                    oldDatCompetenciaOfDatFaseDificultadCollectionDatFaseDificultad.getDatFaseDificultadCollection().remove(datFaseDificultadCollectionDatFaseDificultad);
                    oldDatCompetenciaOfDatFaseDificultadCollectionDatFaseDificultad = em.merge(oldDatCompetenciaOfDatFaseDificultadCollectionDatFaseDificultad);
                }
            }
            for (DatAllround datAllroundCollectionDatAllround : datCompetencia.getDatAllroundCollection()) {
                DatCompetencia oldDatCompetenciaOfDatAllroundCollectionDatAllround = datAllroundCollectionDatAllround.getDatCompetencia();
                datAllroundCollectionDatAllround.setDatCompetencia(datCompetencia);
                datAllroundCollectionDatAllround = em.merge(datAllroundCollectionDatAllround);
                if (oldDatCompetenciaOfDatAllroundCollectionDatAllround != null) {
                    oldDatCompetenciaOfDatAllroundCollectionDatAllround.getDatAllroundCollection().remove(datAllroundCollectionDatAllround);
                    oldDatCompetenciaOfDatAllroundCollectionDatAllround = em.merge(oldDatCompetenciaOfDatAllroundCollectionDatAllround);
                }
            }
            for (HisResultado hisResultadoCollectionHisResultado : datCompetencia.getHisResultadoCollection()) {
                DatCompetencia oldDatCompetenciaOfHisResultadoCollectionHisResultado = hisResultadoCollectionHisResultado.getDatCompetencia();
                hisResultadoCollectionHisResultado.setDatCompetencia(datCompetencia);
                hisResultadoCollectionHisResultado = em.merge(hisResultadoCollectionHisResultado);
                if (oldDatCompetenciaOfHisResultadoCollectionHisResultado != null) {
                    oldDatCompetenciaOfHisResultadoCollectionHisResultado.getHisResultadoCollection().remove(hisResultadoCollectionHisResultado);
                    oldDatCompetenciaOfHisResultadoCollectionHisResultado = em.merge(oldDatCompetenciaOfHisResultadoCollectionHisResultado);
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionDatFaseVelocidad : datCompetencia.getDatFaseVelocidadCollection()) {
                DatCompetencia oldDatCompetenciaOfDatFaseVelocidadCollectionDatFaseVelocidad = datFaseVelocidadCollectionDatFaseVelocidad.getDatCompetencia();
                datFaseVelocidadCollectionDatFaseVelocidad.setDatCompetencia(datCompetencia);
                datFaseVelocidadCollectionDatFaseVelocidad = em.merge(datFaseVelocidadCollectionDatFaseVelocidad);
                if (oldDatCompetenciaOfDatFaseVelocidadCollectionDatFaseVelocidad != null) {
                    oldDatCompetenciaOfDatFaseVelocidadCollectionDatFaseVelocidad.getDatFaseVelocidadCollection().remove(datFaseVelocidadCollectionDatFaseVelocidad);
                    oldDatCompetenciaOfDatFaseVelocidadCollectionDatFaseVelocidad = em.merge(oldDatCompetenciaOfDatFaseVelocidadCollectionDatFaseVelocidad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatCompetencia datCompetencia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatCompetencia persistentDatCompetencia = em.find(DatCompetencia.class, datCompetencia.getIdcompetencia());
            DatGaleria datGaleriaOld = persistentDatCompetencia.getDatGaleria();
            DatGaleria datGaleriaNew = datCompetencia.getDatGaleria();
            NomLugar idlugarOld = persistentDatCompetencia.getIdlugar();
            NomLugar idlugarNew = datCompetencia.getIdlugar();
            Collection<DatFaseBloque> datFaseBloqueCollectionOld = persistentDatCompetencia.getDatFaseBloqueCollection();
            Collection<DatFaseBloque> datFaseBloqueCollectionNew = datCompetencia.getDatFaseBloqueCollection();
            Collection<DatFaseDificultad> datFaseDificultadCollectionOld = persistentDatCompetencia.getDatFaseDificultadCollection();
            Collection<DatFaseDificultad> datFaseDificultadCollectionNew = datCompetencia.getDatFaseDificultadCollection();
            Collection<DatAllround> datAllroundCollectionOld = persistentDatCompetencia.getDatAllroundCollection();
            Collection<DatAllround> datAllroundCollectionNew = datCompetencia.getDatAllroundCollection();
            Collection<HisResultado> hisResultadoCollectionOld = persistentDatCompetencia.getHisResultadoCollection();
            Collection<HisResultado> hisResultadoCollectionNew = datCompetencia.getHisResultadoCollection();
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionOld = persistentDatCompetencia.getDatFaseVelocidadCollection();
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionNew = datCompetencia.getDatFaseVelocidadCollection();
            List<String> illegalOrphanMessages = null;
            if (datGaleriaOld != null && !datGaleriaOld.equals(datGaleriaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain DatGaleria " + datGaleriaOld + " since its idcompetencia field is not nullable.");
            }
            for (DatFaseBloque datFaseBloqueCollectionOldDatFaseBloque : datFaseBloqueCollectionOld) {
                if (!datFaseBloqueCollectionNew.contains(datFaseBloqueCollectionOldDatFaseBloque)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseBloque " + datFaseBloqueCollectionOldDatFaseBloque + " since its datCompetencia field is not nullable.");
                }
            }
            for (DatFaseDificultad datFaseDificultadCollectionOldDatFaseDificultad : datFaseDificultadCollectionOld) {
                if (!datFaseDificultadCollectionNew.contains(datFaseDificultadCollectionOldDatFaseDificultad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseDificultad " + datFaseDificultadCollectionOldDatFaseDificultad + " since its datCompetencia field is not nullable.");
                }
            }
            for (DatAllround datAllroundCollectionOldDatAllround : datAllroundCollectionOld) {
                if (!datAllroundCollectionNew.contains(datAllroundCollectionOldDatAllround)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatAllround " + datAllroundCollectionOldDatAllround + " since its datCompetencia field is not nullable.");
                }
            }
            for (HisResultado hisResultadoCollectionOldHisResultado : hisResultadoCollectionOld) {
                if (!hisResultadoCollectionNew.contains(hisResultadoCollectionOldHisResultado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HisResultado " + hisResultadoCollectionOldHisResultado + " since its datCompetencia field is not nullable.");
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionOldDatFaseVelocidad : datFaseVelocidadCollectionOld) {
                if (!datFaseVelocidadCollectionNew.contains(datFaseVelocidadCollectionOldDatFaseVelocidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseVelocidad " + datFaseVelocidadCollectionOldDatFaseVelocidad + " since its datCompetencia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (datGaleriaNew != null) {
                datGaleriaNew = em.getReference(datGaleriaNew.getClass(), datGaleriaNew.getIdmedia());
                datCompetencia.setDatGaleria(datGaleriaNew);
            }
            if (idlugarNew != null) {
                idlugarNew = em.getReference(idlugarNew.getClass(), idlugarNew.getIdlugar());
                datCompetencia.setIdlugar(idlugarNew);
            }
            Collection<DatFaseBloque> attachedDatFaseBloqueCollectionNew = new ArrayList<DatFaseBloque>();
            for (DatFaseBloque datFaseBloqueCollectionNewDatFaseBloqueToAttach : datFaseBloqueCollectionNew) {
                datFaseBloqueCollectionNewDatFaseBloqueToAttach = em.getReference(datFaseBloqueCollectionNewDatFaseBloqueToAttach.getClass(), datFaseBloqueCollectionNewDatFaseBloqueToAttach.getDatFaseBloquePK());
                attachedDatFaseBloqueCollectionNew.add(datFaseBloqueCollectionNewDatFaseBloqueToAttach);
            }
            datFaseBloqueCollectionNew = attachedDatFaseBloqueCollectionNew;
            datCompetencia.setDatFaseBloqueCollection(datFaseBloqueCollectionNew);
            Collection<DatFaseDificultad> attachedDatFaseDificultadCollectionNew = new ArrayList<DatFaseDificultad>();
            for (DatFaseDificultad datFaseDificultadCollectionNewDatFaseDificultadToAttach : datFaseDificultadCollectionNew) {
                datFaseDificultadCollectionNewDatFaseDificultadToAttach = em.getReference(datFaseDificultadCollectionNewDatFaseDificultadToAttach.getClass(), datFaseDificultadCollectionNewDatFaseDificultadToAttach.getDatFaseDificultadPK());
                attachedDatFaseDificultadCollectionNew.add(datFaseDificultadCollectionNewDatFaseDificultadToAttach);
            }
            datFaseDificultadCollectionNew = attachedDatFaseDificultadCollectionNew;
            datCompetencia.setDatFaseDificultadCollection(datFaseDificultadCollectionNew);
            Collection<DatAllround> attachedDatAllroundCollectionNew = new ArrayList<DatAllround>();
            for (DatAllround datAllroundCollectionNewDatAllroundToAttach : datAllroundCollectionNew) {
                datAllroundCollectionNewDatAllroundToAttach = em.getReference(datAllroundCollectionNewDatAllroundToAttach.getClass(), datAllroundCollectionNewDatAllroundToAttach.getDatAllroundPK());
                attachedDatAllroundCollectionNew.add(datAllroundCollectionNewDatAllroundToAttach);
            }
            datAllroundCollectionNew = attachedDatAllroundCollectionNew;
            datCompetencia.setDatAllroundCollection(datAllroundCollectionNew);
            Collection<HisResultado> attachedHisResultadoCollectionNew = new ArrayList<HisResultado>();
            for (HisResultado hisResultadoCollectionNewHisResultadoToAttach : hisResultadoCollectionNew) {
                hisResultadoCollectionNewHisResultadoToAttach = em.getReference(hisResultadoCollectionNewHisResultadoToAttach.getClass(), hisResultadoCollectionNewHisResultadoToAttach.getHisResultadoPK());
                attachedHisResultadoCollectionNew.add(hisResultadoCollectionNewHisResultadoToAttach);
            }
            hisResultadoCollectionNew = attachedHisResultadoCollectionNew;
            datCompetencia.setHisResultadoCollection(hisResultadoCollectionNew);
            Collection<DatFaseVelocidad> attachedDatFaseVelocidadCollectionNew = new ArrayList<DatFaseVelocidad>();
            for (DatFaseVelocidad datFaseVelocidadCollectionNewDatFaseVelocidadToAttach : datFaseVelocidadCollectionNew) {
                datFaseVelocidadCollectionNewDatFaseVelocidadToAttach = em.getReference(datFaseVelocidadCollectionNewDatFaseVelocidadToAttach.getClass(), datFaseVelocidadCollectionNewDatFaseVelocidadToAttach.getDatFaseVelocidadPK());
                attachedDatFaseVelocidadCollectionNew.add(datFaseVelocidadCollectionNewDatFaseVelocidadToAttach);
            }
            datFaseVelocidadCollectionNew = attachedDatFaseVelocidadCollectionNew;
            datCompetencia.setDatFaseVelocidadCollection(datFaseVelocidadCollectionNew);
            datCompetencia = em.merge(datCompetencia);
            if (datGaleriaNew != null && !datGaleriaNew.equals(datGaleriaOld)) {
                DatCompetencia oldIdcompetenciaOfDatGaleria = datGaleriaNew.getIdcompetencia();
                if (oldIdcompetenciaOfDatGaleria != null) {
                    oldIdcompetenciaOfDatGaleria.setDatGaleria(null);
                    oldIdcompetenciaOfDatGaleria = em.merge(oldIdcompetenciaOfDatGaleria);
                }
                datGaleriaNew.setIdcompetencia(datCompetencia);
                datGaleriaNew = em.merge(datGaleriaNew);
            }
            if (idlugarOld != null && !idlugarOld.equals(idlugarNew)) {
                idlugarOld.getDatCompetenciaCollection().remove(datCompetencia);
                idlugarOld = em.merge(idlugarOld);
            }
            if (idlugarNew != null && !idlugarNew.equals(idlugarOld)) {
                idlugarNew.getDatCompetenciaCollection().add(datCompetencia);
                idlugarNew = em.merge(idlugarNew);
            }
            for (DatFaseBloque datFaseBloqueCollectionNewDatFaseBloque : datFaseBloqueCollectionNew) {
                if (!datFaseBloqueCollectionOld.contains(datFaseBloqueCollectionNewDatFaseBloque)) {
                    DatCompetencia oldDatCompetenciaOfDatFaseBloqueCollectionNewDatFaseBloque = datFaseBloqueCollectionNewDatFaseBloque.getDatCompetencia();
                    datFaseBloqueCollectionNewDatFaseBloque.setDatCompetencia(datCompetencia);
                    datFaseBloqueCollectionNewDatFaseBloque = em.merge(datFaseBloqueCollectionNewDatFaseBloque);
                    if (oldDatCompetenciaOfDatFaseBloqueCollectionNewDatFaseBloque != null && !oldDatCompetenciaOfDatFaseBloqueCollectionNewDatFaseBloque.equals(datCompetencia)) {
                        oldDatCompetenciaOfDatFaseBloqueCollectionNewDatFaseBloque.getDatFaseBloqueCollection().remove(datFaseBloqueCollectionNewDatFaseBloque);
                        oldDatCompetenciaOfDatFaseBloqueCollectionNewDatFaseBloque = em.merge(oldDatCompetenciaOfDatFaseBloqueCollectionNewDatFaseBloque);
                    }
                }
            }
            for (DatFaseDificultad datFaseDificultadCollectionNewDatFaseDificultad : datFaseDificultadCollectionNew) {
                if (!datFaseDificultadCollectionOld.contains(datFaseDificultadCollectionNewDatFaseDificultad)) {
                    DatCompetencia oldDatCompetenciaOfDatFaseDificultadCollectionNewDatFaseDificultad = datFaseDificultadCollectionNewDatFaseDificultad.getDatCompetencia();
                    datFaseDificultadCollectionNewDatFaseDificultad.setDatCompetencia(datCompetencia);
                    datFaseDificultadCollectionNewDatFaseDificultad = em.merge(datFaseDificultadCollectionNewDatFaseDificultad);
                    if (oldDatCompetenciaOfDatFaseDificultadCollectionNewDatFaseDificultad != null && !oldDatCompetenciaOfDatFaseDificultadCollectionNewDatFaseDificultad.equals(datCompetencia)) {
                        oldDatCompetenciaOfDatFaseDificultadCollectionNewDatFaseDificultad.getDatFaseDificultadCollection().remove(datFaseDificultadCollectionNewDatFaseDificultad);
                        oldDatCompetenciaOfDatFaseDificultadCollectionNewDatFaseDificultad = em.merge(oldDatCompetenciaOfDatFaseDificultadCollectionNewDatFaseDificultad);
                    }
                }
            }
            for (DatAllround datAllroundCollectionNewDatAllround : datAllroundCollectionNew) {
                if (!datAllroundCollectionOld.contains(datAllroundCollectionNewDatAllround)) {
                    DatCompetencia oldDatCompetenciaOfDatAllroundCollectionNewDatAllround = datAllroundCollectionNewDatAllround.getDatCompetencia();
                    datAllroundCollectionNewDatAllround.setDatCompetencia(datCompetencia);
                    datAllroundCollectionNewDatAllround = em.merge(datAllroundCollectionNewDatAllround);
                    if (oldDatCompetenciaOfDatAllroundCollectionNewDatAllround != null && !oldDatCompetenciaOfDatAllroundCollectionNewDatAllround.equals(datCompetencia)) {
                        oldDatCompetenciaOfDatAllroundCollectionNewDatAllround.getDatAllroundCollection().remove(datAllroundCollectionNewDatAllround);
                        oldDatCompetenciaOfDatAllroundCollectionNewDatAllround = em.merge(oldDatCompetenciaOfDatAllroundCollectionNewDatAllround);
                    }
                }
            }
            for (HisResultado hisResultadoCollectionNewHisResultado : hisResultadoCollectionNew) {
                if (!hisResultadoCollectionOld.contains(hisResultadoCollectionNewHisResultado)) {
                    DatCompetencia oldDatCompetenciaOfHisResultadoCollectionNewHisResultado = hisResultadoCollectionNewHisResultado.getDatCompetencia();
                    hisResultadoCollectionNewHisResultado.setDatCompetencia(datCompetencia);
                    hisResultadoCollectionNewHisResultado = em.merge(hisResultadoCollectionNewHisResultado);
                    if (oldDatCompetenciaOfHisResultadoCollectionNewHisResultado != null && !oldDatCompetenciaOfHisResultadoCollectionNewHisResultado.equals(datCompetencia)) {
                        oldDatCompetenciaOfHisResultadoCollectionNewHisResultado.getHisResultadoCollection().remove(hisResultadoCollectionNewHisResultado);
                        oldDatCompetenciaOfHisResultadoCollectionNewHisResultado = em.merge(oldDatCompetenciaOfHisResultadoCollectionNewHisResultado);
                    }
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionNewDatFaseVelocidad : datFaseVelocidadCollectionNew) {
                if (!datFaseVelocidadCollectionOld.contains(datFaseVelocidadCollectionNewDatFaseVelocidad)) {
                    DatCompetencia oldDatCompetenciaOfDatFaseVelocidadCollectionNewDatFaseVelocidad = datFaseVelocidadCollectionNewDatFaseVelocidad.getDatCompetencia();
                    datFaseVelocidadCollectionNewDatFaseVelocidad.setDatCompetencia(datCompetencia);
                    datFaseVelocidadCollectionNewDatFaseVelocidad = em.merge(datFaseVelocidadCollectionNewDatFaseVelocidad);
                    if (oldDatCompetenciaOfDatFaseVelocidadCollectionNewDatFaseVelocidad != null && !oldDatCompetenciaOfDatFaseVelocidadCollectionNewDatFaseVelocidad.equals(datCompetencia)) {
                        oldDatCompetenciaOfDatFaseVelocidadCollectionNewDatFaseVelocidad.getDatFaseVelocidadCollection().remove(datFaseVelocidadCollectionNewDatFaseVelocidad);
                        oldDatCompetenciaOfDatFaseVelocidadCollectionNewDatFaseVelocidad = em.merge(oldDatCompetenciaOfDatFaseVelocidadCollectionNewDatFaseVelocidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datCompetencia.getIdcompetencia();
                if (findDatCompetencia(id) == null) {
                    throw new NonexistentEntityException("The datCompetencia with id " + id + " no longer exists.");
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
            DatCompetencia datCompetencia;
            try {
                datCompetencia = em.getReference(DatCompetencia.class, id);
                datCompetencia.getIdcompetencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datCompetencia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            DatGaleria datGaleriaOrphanCheck = datCompetencia.getDatGaleria();
            if (datGaleriaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatCompetencia (" + datCompetencia + ") cannot be destroyed since the DatGaleria " + datGaleriaOrphanCheck + " in its datGaleria field has a non-nullable idcompetencia field.");
            }
            Collection<DatFaseBloque> datFaseBloqueCollectionOrphanCheck = datCompetencia.getDatFaseBloqueCollection();
            for (DatFaseBloque datFaseBloqueCollectionOrphanCheckDatFaseBloque : datFaseBloqueCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatCompetencia (" + datCompetencia + ") cannot be destroyed since the DatFaseBloque " + datFaseBloqueCollectionOrphanCheckDatFaseBloque + " in its datFaseBloqueCollection field has a non-nullable datCompetencia field.");
            }
            Collection<DatFaseDificultad> datFaseDificultadCollectionOrphanCheck = datCompetencia.getDatFaseDificultadCollection();
            for (DatFaseDificultad datFaseDificultadCollectionOrphanCheckDatFaseDificultad : datFaseDificultadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatCompetencia (" + datCompetencia + ") cannot be destroyed since the DatFaseDificultad " + datFaseDificultadCollectionOrphanCheckDatFaseDificultad + " in its datFaseDificultadCollection field has a non-nullable datCompetencia field.");
            }
            Collection<DatAllround> datAllroundCollectionOrphanCheck = datCompetencia.getDatAllroundCollection();
            for (DatAllround datAllroundCollectionOrphanCheckDatAllround : datAllroundCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatCompetencia (" + datCompetencia + ") cannot be destroyed since the DatAllround " + datAllroundCollectionOrphanCheckDatAllround + " in its datAllroundCollection field has a non-nullable datCompetencia field.");
            }
            Collection<HisResultado> hisResultadoCollectionOrphanCheck = datCompetencia.getHisResultadoCollection();
            for (HisResultado hisResultadoCollectionOrphanCheckHisResultado : hisResultadoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatCompetencia (" + datCompetencia + ") cannot be destroyed since the HisResultado " + hisResultadoCollectionOrphanCheckHisResultado + " in its hisResultadoCollection field has a non-nullable datCompetencia field.");
            }
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionOrphanCheck = datCompetencia.getDatFaseVelocidadCollection();
            for (DatFaseVelocidad datFaseVelocidadCollectionOrphanCheckDatFaseVelocidad : datFaseVelocidadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatCompetencia (" + datCompetencia + ") cannot be destroyed since the DatFaseVelocidad " + datFaseVelocidadCollectionOrphanCheckDatFaseVelocidad + " in its datFaseVelocidadCollection field has a non-nullable datCompetencia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            NomLugar idlugar = datCompetencia.getIdlugar();
            if (idlugar != null) {
                idlugar.getDatCompetenciaCollection().remove(datCompetencia);
                idlugar = em.merge(idlugar);
            }
            em.remove(datCompetencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatCompetencia> findDatCompetenciaEntities() {
        return findDatCompetenciaEntities(true, -1, -1);
    }

    public List<DatCompetencia> findDatCompetenciaEntities(int maxResults, int firstResult) {
        return findDatCompetenciaEntities(false, maxResults, firstResult);
    }

    private List<DatCompetencia> findDatCompetenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatCompetencia.class));
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

    public DatCompetencia findDatCompetencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatCompetencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatCompetenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatCompetencia> rt = cq.from(DatCompetencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
