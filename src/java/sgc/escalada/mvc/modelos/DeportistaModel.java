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
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.entidades.DatDeportistaFederacion;
import sgc.escalada.mvc.entidades.DatDireccion;
import sgc.escalada.mvc.entidades.DatFaseBloque;
import sgc.escalada.mvc.entidades.DatFaseDificultad;
import sgc.escalada.mvc.entidades.DatFaseVelocidad;
import sgc.escalada.mvc.entidades.HisResultado;
import sgc.escalada.mvc.entidades.NomSexo;
import sgc.escalada.mvc.entidades.NomTipoSangre;
import sgc.escalada.mvc.modelos.exceptions.IllegalOrphanException;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
public class DeportistaModel implements Serializable {

    public DeportistaModel(EntityManagerFactory emf) {
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

    public void create(DatDeportista datDeportista) {
        if (datDeportista.getDatFaseBloqueCollection() == null) {
            datDeportista.setDatFaseBloqueCollection(new ArrayList<DatFaseBloque>());
        }
        if (datDeportista.getDatFaseDificultadCollection() == null) {
            datDeportista.setDatFaseDificultadCollection(new ArrayList<DatFaseDificultad>());
        }
        if (datDeportista.getDatAllroundCollection() == null) {
            datDeportista.setDatAllroundCollection(new ArrayList<DatAllround>());
        }
        if (datDeportista.getHisResultadoCollection() == null) {
            datDeportista.setHisResultadoCollection(new ArrayList<HisResultado>());
        }
        if (datDeportista.getDatFaseVelocidadCollection() == null) {
            datDeportista.setDatFaseVelocidadCollection(new ArrayList<DatFaseVelocidad>());
        }
        if (datDeportista.getDatDireccionCollection() == null) {
            datDeportista.setDatDireccionCollection(new ArrayList<DatDireccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NomTipoSangre idtiposangre = datDeportista.getIdtiposangre();
            if (idtiposangre != null) {
                idtiposangre = em.getReference(idtiposangre.getClass(), idtiposangre.getIdtiposangre());
                datDeportista.setIdtiposangre(idtiposangre);
            }
            NomSexo idsexo = datDeportista.getIdsexo();
            if (idsexo != null) {
                idsexo = em.getReference(idsexo.getClass(), idsexo.getIdsexo());
                datDeportista.setIdsexo(idsexo);
            }
            DatDeportistaFederacion datDeportistaFederacion = datDeportista.getDatDeportistaFederacion();
            if (datDeportistaFederacion != null) {
                datDeportistaFederacion = em.getReference(datDeportistaFederacion.getClass(), datDeportistaFederacion.getDatDeportistaFederacionPK());
                datDeportista.setDatDeportistaFederacion(datDeportistaFederacion);
            }
            Collection<DatFaseBloque> attachedDatFaseBloqueCollection = new ArrayList<DatFaseBloque>();
            for (DatFaseBloque datFaseBloqueCollectionDatFaseBloqueToAttach : datDeportista.getDatFaseBloqueCollection()) {
                datFaseBloqueCollectionDatFaseBloqueToAttach = em.getReference(datFaseBloqueCollectionDatFaseBloqueToAttach.getClass(), datFaseBloqueCollectionDatFaseBloqueToAttach.getDatFaseBloquePK());
                attachedDatFaseBloqueCollection.add(datFaseBloqueCollectionDatFaseBloqueToAttach);
            }
            datDeportista.setDatFaseBloqueCollection(attachedDatFaseBloqueCollection);
            Collection<DatFaseDificultad> attachedDatFaseDificultadCollection = new ArrayList<DatFaseDificultad>();
            for (DatFaseDificultad datFaseDificultadCollectionDatFaseDificultadToAttach : datDeportista.getDatFaseDificultadCollection()) {
                datFaseDificultadCollectionDatFaseDificultadToAttach = em.getReference(datFaseDificultadCollectionDatFaseDificultadToAttach.getClass(), datFaseDificultadCollectionDatFaseDificultadToAttach.getDatFaseDificultadPK());
                attachedDatFaseDificultadCollection.add(datFaseDificultadCollectionDatFaseDificultadToAttach);
            }
            datDeportista.setDatFaseDificultadCollection(attachedDatFaseDificultadCollection);
            Collection<DatAllround> attachedDatAllroundCollection = new ArrayList<DatAllround>();
            for (DatAllround datAllroundCollectionDatAllroundToAttach : datDeportista.getDatAllroundCollection()) {
                datAllroundCollectionDatAllroundToAttach = em.getReference(datAllroundCollectionDatAllroundToAttach.getClass(), datAllroundCollectionDatAllroundToAttach.getDatAllroundPK());
                attachedDatAllroundCollection.add(datAllroundCollectionDatAllroundToAttach);
            }
            datDeportista.setDatAllroundCollection(attachedDatAllroundCollection);
            Collection<HisResultado> attachedHisResultadoCollection = new ArrayList<HisResultado>();
            for (HisResultado hisResultadoCollectionHisResultadoToAttach : datDeportista.getHisResultadoCollection()) {
                hisResultadoCollectionHisResultadoToAttach = em.getReference(hisResultadoCollectionHisResultadoToAttach.getClass(), hisResultadoCollectionHisResultadoToAttach.getHisResultadoPK());
                attachedHisResultadoCollection.add(hisResultadoCollectionHisResultadoToAttach);
            }
            datDeportista.setHisResultadoCollection(attachedHisResultadoCollection);
            Collection<DatFaseVelocidad> attachedDatFaseVelocidadCollection = new ArrayList<DatFaseVelocidad>();
            for (DatFaseVelocidad datFaseVelocidadCollectionDatFaseVelocidadToAttach : datDeportista.getDatFaseVelocidadCollection()) {
                datFaseVelocidadCollectionDatFaseVelocidadToAttach = em.getReference(datFaseVelocidadCollectionDatFaseVelocidadToAttach.getClass(), datFaseVelocidadCollectionDatFaseVelocidadToAttach.getDatFaseVelocidadPK());
                attachedDatFaseVelocidadCollection.add(datFaseVelocidadCollectionDatFaseVelocidadToAttach);
            }
            datDeportista.setDatFaseVelocidadCollection(attachedDatFaseVelocidadCollection);
            Collection<DatDireccion> attachedDatDireccionCollection = new ArrayList<DatDireccion>();
            for (DatDireccion datDireccionCollectionDatDireccionToAttach : datDeportista.getDatDireccionCollection()) {
                datDireccionCollectionDatDireccionToAttach = em.getReference(datDireccionCollectionDatDireccionToAttach.getClass(), datDireccionCollectionDatDireccionToAttach.getDatDireccionPK());
                attachedDatDireccionCollection.add(datDireccionCollectionDatDireccionToAttach);
            }
            datDeportista.setDatDireccionCollection(attachedDatDireccionCollection);
            em.persist(datDeportista);
            if (idtiposangre != null) {
                idtiposangre.getDatDeportistaCollection().add(datDeportista);
                idtiposangre = em.merge(idtiposangre);
            }
            if (idsexo != null) {
                idsexo.getDatDeportistaCollection().add(datDeportista);
                idsexo = em.merge(idsexo);
            }
            if (datDeportistaFederacion != null) {
                DatDeportista oldDatDeportistaOfDatDeportistaFederacion = datDeportistaFederacion.getDatDeportista();
                if (oldDatDeportistaOfDatDeportistaFederacion != null) {
                    oldDatDeportistaOfDatDeportistaFederacion.setDatDeportistaFederacion(null);
                    oldDatDeportistaOfDatDeportistaFederacion = em.merge(oldDatDeportistaOfDatDeportistaFederacion);
                }
                datDeportistaFederacion.setDatDeportista(datDeportista);
                datDeportistaFederacion = em.merge(datDeportistaFederacion);
            }
            for (DatFaseBloque datFaseBloqueCollectionDatFaseBloque : datDeportista.getDatFaseBloqueCollection()) {
                DatDeportista oldDatDeportistaOfDatFaseBloqueCollectionDatFaseBloque = datFaseBloqueCollectionDatFaseBloque.getDatDeportista();
                datFaseBloqueCollectionDatFaseBloque.setDatDeportista(datDeportista);
                datFaseBloqueCollectionDatFaseBloque = em.merge(datFaseBloqueCollectionDatFaseBloque);
                if (oldDatDeportistaOfDatFaseBloqueCollectionDatFaseBloque != null) {
                    oldDatDeportistaOfDatFaseBloqueCollectionDatFaseBloque.getDatFaseBloqueCollection().remove(datFaseBloqueCollectionDatFaseBloque);
                    oldDatDeportistaOfDatFaseBloqueCollectionDatFaseBloque = em.merge(oldDatDeportistaOfDatFaseBloqueCollectionDatFaseBloque);
                }
            }
            for (DatFaseDificultad datFaseDificultadCollectionDatFaseDificultad : datDeportista.getDatFaseDificultadCollection()) {
                DatDeportista oldDatDeportistaOfDatFaseDificultadCollectionDatFaseDificultad = datFaseDificultadCollectionDatFaseDificultad.getDatDeportista();
                datFaseDificultadCollectionDatFaseDificultad.setDatDeportista(datDeportista);
                datFaseDificultadCollectionDatFaseDificultad = em.merge(datFaseDificultadCollectionDatFaseDificultad);
                if (oldDatDeportistaOfDatFaseDificultadCollectionDatFaseDificultad != null) {
                    oldDatDeportistaOfDatFaseDificultadCollectionDatFaseDificultad.getDatFaseDificultadCollection().remove(datFaseDificultadCollectionDatFaseDificultad);
                    oldDatDeportistaOfDatFaseDificultadCollectionDatFaseDificultad = em.merge(oldDatDeportistaOfDatFaseDificultadCollectionDatFaseDificultad);
                }
            }
            for (DatAllround datAllroundCollectionDatAllround : datDeportista.getDatAllroundCollection()) {
                DatDeportista oldDatDeportistaOfDatAllroundCollectionDatAllround = datAllroundCollectionDatAllround.getDatDeportista();
                datAllroundCollectionDatAllround.setDatDeportista(datDeportista);
                datAllroundCollectionDatAllround = em.merge(datAllroundCollectionDatAllround);
                if (oldDatDeportistaOfDatAllroundCollectionDatAllround != null) {
                    oldDatDeportistaOfDatAllroundCollectionDatAllround.getDatAllroundCollection().remove(datAllroundCollectionDatAllround);
                    oldDatDeportistaOfDatAllroundCollectionDatAllround = em.merge(oldDatDeportistaOfDatAllroundCollectionDatAllround);
                }
            }
            for (HisResultado hisResultadoCollectionHisResultado : datDeportista.getHisResultadoCollection()) {
                DatDeportista oldDatDeportistaOfHisResultadoCollectionHisResultado = hisResultadoCollectionHisResultado.getDatDeportista();
                hisResultadoCollectionHisResultado.setDatDeportista(datDeportista);
                hisResultadoCollectionHisResultado = em.merge(hisResultadoCollectionHisResultado);
                if (oldDatDeportistaOfHisResultadoCollectionHisResultado != null) {
                    oldDatDeportistaOfHisResultadoCollectionHisResultado.getHisResultadoCollection().remove(hisResultadoCollectionHisResultado);
                    oldDatDeportistaOfHisResultadoCollectionHisResultado = em.merge(oldDatDeportistaOfHisResultadoCollectionHisResultado);
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionDatFaseVelocidad : datDeportista.getDatFaseVelocidadCollection()) {
                DatDeportista oldDatDeportistaOfDatFaseVelocidadCollectionDatFaseVelocidad = datFaseVelocidadCollectionDatFaseVelocidad.getDatDeportista();
                datFaseVelocidadCollectionDatFaseVelocidad.setDatDeportista(datDeportista);
                datFaseVelocidadCollectionDatFaseVelocidad = em.merge(datFaseVelocidadCollectionDatFaseVelocidad);
                if (oldDatDeportistaOfDatFaseVelocidadCollectionDatFaseVelocidad != null) {
                    oldDatDeportistaOfDatFaseVelocidadCollectionDatFaseVelocidad.getDatFaseVelocidadCollection().remove(datFaseVelocidadCollectionDatFaseVelocidad);
                    oldDatDeportistaOfDatFaseVelocidadCollectionDatFaseVelocidad = em.merge(oldDatDeportistaOfDatFaseVelocidadCollectionDatFaseVelocidad);
                }
            }
            for (DatDireccion datDireccionCollectionDatDireccion : datDeportista.getDatDireccionCollection()) {
                DatDeportista oldDatDeportistaOfDatDireccionCollectionDatDireccion = datDireccionCollectionDatDireccion.getDatDeportista();
                datDireccionCollectionDatDireccion.setDatDeportista(datDeportista);
                datDireccionCollectionDatDireccion = em.merge(datDireccionCollectionDatDireccion);
                if (oldDatDeportistaOfDatDireccionCollectionDatDireccion != null) {
                    oldDatDeportistaOfDatDireccionCollectionDatDireccion.getDatDireccionCollection().remove(datDireccionCollectionDatDireccion);
                    oldDatDeportistaOfDatDireccionCollectionDatDireccion = em.merge(oldDatDeportistaOfDatDireccionCollectionDatDireccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatDeportista datDeportista) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatDeportista persistentDatDeportista = em.find(DatDeportista.class, datDeportista.getIddeportista());
            NomTipoSangre idtiposangreOld = persistentDatDeportista.getIdtiposangre();
            NomTipoSangre idtiposangreNew = datDeportista.getIdtiposangre();
            NomSexo idsexoOld = persistentDatDeportista.getIdsexo();
            NomSexo idsexoNew = datDeportista.getIdsexo();
            DatDeportistaFederacion datDeportistaFederacionOld = persistentDatDeportista.getDatDeportistaFederacion();
            DatDeportistaFederacion datDeportistaFederacionNew = datDeportista.getDatDeportistaFederacion();
            Collection<DatFaseBloque> datFaseBloqueCollectionOld = persistentDatDeportista.getDatFaseBloqueCollection();
            Collection<DatFaseBloque> datFaseBloqueCollectionNew = datDeportista.getDatFaseBloqueCollection();
            Collection<DatFaseDificultad> datFaseDificultadCollectionOld = persistentDatDeportista.getDatFaseDificultadCollection();
            Collection<DatFaseDificultad> datFaseDificultadCollectionNew = datDeportista.getDatFaseDificultadCollection();
            Collection<DatAllround> datAllroundCollectionOld = persistentDatDeportista.getDatAllroundCollection();
            Collection<DatAllround> datAllroundCollectionNew = datDeportista.getDatAllroundCollection();
            Collection<HisResultado> hisResultadoCollectionOld = persistentDatDeportista.getHisResultadoCollection();
            Collection<HisResultado> hisResultadoCollectionNew = datDeportista.getHisResultadoCollection();
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionOld = persistentDatDeportista.getDatFaseVelocidadCollection();
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionNew = datDeportista.getDatFaseVelocidadCollection();
            Collection<DatDireccion> datDireccionCollectionOld = persistentDatDeportista.getDatDireccionCollection();
            Collection<DatDireccion> datDireccionCollectionNew = datDeportista.getDatDireccionCollection();
            List<String> illegalOrphanMessages = null;
            if (datDeportistaFederacionOld != null && !datDeportistaFederacionOld.equals(datDeportistaFederacionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain DatDeportistaFederacion " + datDeportistaFederacionOld + " since its datDeportista field is not nullable.");
            }
            for (DatFaseBloque datFaseBloqueCollectionOldDatFaseBloque : datFaseBloqueCollectionOld) {
                if (!datFaseBloqueCollectionNew.contains(datFaseBloqueCollectionOldDatFaseBloque)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseBloque " + datFaseBloqueCollectionOldDatFaseBloque + " since its datDeportista field is not nullable.");
                }
            }
            for (DatFaseDificultad datFaseDificultadCollectionOldDatFaseDificultad : datFaseDificultadCollectionOld) {
                if (!datFaseDificultadCollectionNew.contains(datFaseDificultadCollectionOldDatFaseDificultad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseDificultad " + datFaseDificultadCollectionOldDatFaseDificultad + " since its datDeportista field is not nullable.");
                }
            }
            for (DatAllround datAllroundCollectionOldDatAllround : datAllroundCollectionOld) {
                if (!datAllroundCollectionNew.contains(datAllroundCollectionOldDatAllround)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatAllround " + datAllroundCollectionOldDatAllround + " since its datDeportista field is not nullable.");
                }
            }
            for (HisResultado hisResultadoCollectionOldHisResultado : hisResultadoCollectionOld) {
                if (!hisResultadoCollectionNew.contains(hisResultadoCollectionOldHisResultado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HisResultado " + hisResultadoCollectionOldHisResultado + " since its datDeportista field is not nullable.");
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionOldDatFaseVelocidad : datFaseVelocidadCollectionOld) {
                if (!datFaseVelocidadCollectionNew.contains(datFaseVelocidadCollectionOldDatFaseVelocidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatFaseVelocidad " + datFaseVelocidadCollectionOldDatFaseVelocidad + " since its datDeportista field is not nullable.");
                }
            }
            for (DatDireccion datDireccionCollectionOldDatDireccion : datDireccionCollectionOld) {
                if (!datDireccionCollectionNew.contains(datDireccionCollectionOldDatDireccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DatDireccion " + datDireccionCollectionOldDatDireccion + " since its datDeportista field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idtiposangreNew != null) {
                idtiposangreNew = em.getReference(idtiposangreNew.getClass(), idtiposangreNew.getIdtiposangre());
                datDeportista.setIdtiposangre(idtiposangreNew);
            }
            if (idsexoNew != null) {
                idsexoNew = em.getReference(idsexoNew.getClass(), idsexoNew.getIdsexo());
                datDeportista.setIdsexo(idsexoNew);
            }
            if (datDeportistaFederacionNew != null) {
                datDeportistaFederacionNew = em.getReference(datDeportistaFederacionNew.getClass(), datDeportistaFederacionNew.getDatDeportistaFederacionPK());
                datDeportista.setDatDeportistaFederacion(datDeportistaFederacionNew);
            }
            Collection<DatFaseBloque> attachedDatFaseBloqueCollectionNew = new ArrayList<DatFaseBloque>();
            for (DatFaseBloque datFaseBloqueCollectionNewDatFaseBloqueToAttach : datFaseBloqueCollectionNew) {
                datFaseBloqueCollectionNewDatFaseBloqueToAttach = em.getReference(datFaseBloqueCollectionNewDatFaseBloqueToAttach.getClass(), datFaseBloqueCollectionNewDatFaseBloqueToAttach.getDatFaseBloquePK());
                attachedDatFaseBloqueCollectionNew.add(datFaseBloqueCollectionNewDatFaseBloqueToAttach);
            }
            datFaseBloqueCollectionNew = attachedDatFaseBloqueCollectionNew;
            datDeportista.setDatFaseBloqueCollection(datFaseBloqueCollectionNew);
            Collection<DatFaseDificultad> attachedDatFaseDificultadCollectionNew = new ArrayList<DatFaseDificultad>();
            for (DatFaseDificultad datFaseDificultadCollectionNewDatFaseDificultadToAttach : datFaseDificultadCollectionNew) {
                datFaseDificultadCollectionNewDatFaseDificultadToAttach = em.getReference(datFaseDificultadCollectionNewDatFaseDificultadToAttach.getClass(), datFaseDificultadCollectionNewDatFaseDificultadToAttach.getDatFaseDificultadPK());
                attachedDatFaseDificultadCollectionNew.add(datFaseDificultadCollectionNewDatFaseDificultadToAttach);
            }
            datFaseDificultadCollectionNew = attachedDatFaseDificultadCollectionNew;
            datDeportista.setDatFaseDificultadCollection(datFaseDificultadCollectionNew);
            Collection<DatAllround> attachedDatAllroundCollectionNew = new ArrayList<DatAllround>();
            for (DatAllround datAllroundCollectionNewDatAllroundToAttach : datAllroundCollectionNew) {
                datAllroundCollectionNewDatAllroundToAttach = em.getReference(datAllroundCollectionNewDatAllroundToAttach.getClass(), datAllroundCollectionNewDatAllroundToAttach.getDatAllroundPK());
                attachedDatAllroundCollectionNew.add(datAllroundCollectionNewDatAllroundToAttach);
            }
            datAllroundCollectionNew = attachedDatAllroundCollectionNew;
            datDeportista.setDatAllroundCollection(datAllroundCollectionNew);
            Collection<HisResultado> attachedHisResultadoCollectionNew = new ArrayList<HisResultado>();
            for (HisResultado hisResultadoCollectionNewHisResultadoToAttach : hisResultadoCollectionNew) {
                hisResultadoCollectionNewHisResultadoToAttach = em.getReference(hisResultadoCollectionNewHisResultadoToAttach.getClass(), hisResultadoCollectionNewHisResultadoToAttach.getHisResultadoPK());
                attachedHisResultadoCollectionNew.add(hisResultadoCollectionNewHisResultadoToAttach);
            }
            hisResultadoCollectionNew = attachedHisResultadoCollectionNew;
            datDeportista.setHisResultadoCollection(hisResultadoCollectionNew);
            Collection<DatFaseVelocidad> attachedDatFaseVelocidadCollectionNew = new ArrayList<DatFaseVelocidad>();
            for (DatFaseVelocidad datFaseVelocidadCollectionNewDatFaseVelocidadToAttach : datFaseVelocidadCollectionNew) {
                datFaseVelocidadCollectionNewDatFaseVelocidadToAttach = em.getReference(datFaseVelocidadCollectionNewDatFaseVelocidadToAttach.getClass(), datFaseVelocidadCollectionNewDatFaseVelocidadToAttach.getDatFaseVelocidadPK());
                attachedDatFaseVelocidadCollectionNew.add(datFaseVelocidadCollectionNewDatFaseVelocidadToAttach);
            }
            datFaseVelocidadCollectionNew = attachedDatFaseVelocidadCollectionNew;
            datDeportista.setDatFaseVelocidadCollection(datFaseVelocidadCollectionNew);
            Collection<DatDireccion> attachedDatDireccionCollectionNew = new ArrayList<DatDireccion>();
            for (DatDireccion datDireccionCollectionNewDatDireccionToAttach : datDireccionCollectionNew) {
                datDireccionCollectionNewDatDireccionToAttach = em.getReference(datDireccionCollectionNewDatDireccionToAttach.getClass(), datDireccionCollectionNewDatDireccionToAttach.getDatDireccionPK());
                attachedDatDireccionCollectionNew.add(datDireccionCollectionNewDatDireccionToAttach);
            }
            datDireccionCollectionNew = attachedDatDireccionCollectionNew;
            datDeportista.setDatDireccionCollection(datDireccionCollectionNew);
            datDeportista = em.merge(datDeportista);
            if (idtiposangreOld != null && !idtiposangreOld.equals(idtiposangreNew)) {
                idtiposangreOld.getDatDeportistaCollection().remove(datDeportista);
                idtiposangreOld = em.merge(idtiposangreOld);
            }
            if (idtiposangreNew != null && !idtiposangreNew.equals(idtiposangreOld)) {
                idtiposangreNew.getDatDeportistaCollection().add(datDeportista);
                idtiposangreNew = em.merge(idtiposangreNew);
            }
            if (idsexoOld != null && !idsexoOld.equals(idsexoNew)) {
                idsexoOld.getDatDeportistaCollection().remove(datDeportista);
                idsexoOld = em.merge(idsexoOld);
            }
            if (idsexoNew != null && !idsexoNew.equals(idsexoOld)) {
                idsexoNew.getDatDeportistaCollection().add(datDeportista);
                idsexoNew = em.merge(idsexoNew);
            }
            if (datDeportistaFederacionNew != null && !datDeportistaFederacionNew.equals(datDeportistaFederacionOld)) {
                DatDeportista oldDatDeportistaOfDatDeportistaFederacion = datDeportistaFederacionNew.getDatDeportista();
                if (oldDatDeportistaOfDatDeportistaFederacion != null) {
                    oldDatDeportistaOfDatDeportistaFederacion.setDatDeportistaFederacion(null);
                    oldDatDeportistaOfDatDeportistaFederacion = em.merge(oldDatDeportistaOfDatDeportistaFederacion);
                }
                datDeportistaFederacionNew.setDatDeportista(datDeportista);
                datDeportistaFederacionNew = em.merge(datDeportistaFederacionNew);
            }
            for (DatFaseBloque datFaseBloqueCollectionNewDatFaseBloque : datFaseBloqueCollectionNew) {
                if (!datFaseBloqueCollectionOld.contains(datFaseBloqueCollectionNewDatFaseBloque)) {
                    DatDeportista oldDatDeportistaOfDatFaseBloqueCollectionNewDatFaseBloque = datFaseBloqueCollectionNewDatFaseBloque.getDatDeportista();
                    datFaseBloqueCollectionNewDatFaseBloque.setDatDeportista(datDeportista);
                    datFaseBloqueCollectionNewDatFaseBloque = em.merge(datFaseBloqueCollectionNewDatFaseBloque);
                    if (oldDatDeportistaOfDatFaseBloqueCollectionNewDatFaseBloque != null && !oldDatDeportistaOfDatFaseBloqueCollectionNewDatFaseBloque.equals(datDeportista)) {
                        oldDatDeportistaOfDatFaseBloqueCollectionNewDatFaseBloque.getDatFaseBloqueCollection().remove(datFaseBloqueCollectionNewDatFaseBloque);
                        oldDatDeportistaOfDatFaseBloqueCollectionNewDatFaseBloque = em.merge(oldDatDeportistaOfDatFaseBloqueCollectionNewDatFaseBloque);
                    }
                }
            }
            for (DatFaseDificultad datFaseDificultadCollectionNewDatFaseDificultad : datFaseDificultadCollectionNew) {
                if (!datFaseDificultadCollectionOld.contains(datFaseDificultadCollectionNewDatFaseDificultad)) {
                    DatDeportista oldDatDeportistaOfDatFaseDificultadCollectionNewDatFaseDificultad = datFaseDificultadCollectionNewDatFaseDificultad.getDatDeportista();
                    datFaseDificultadCollectionNewDatFaseDificultad.setDatDeportista(datDeportista);
                    datFaseDificultadCollectionNewDatFaseDificultad = em.merge(datFaseDificultadCollectionNewDatFaseDificultad);
                    if (oldDatDeportistaOfDatFaseDificultadCollectionNewDatFaseDificultad != null && !oldDatDeportistaOfDatFaseDificultadCollectionNewDatFaseDificultad.equals(datDeportista)) {
                        oldDatDeportistaOfDatFaseDificultadCollectionNewDatFaseDificultad.getDatFaseDificultadCollection().remove(datFaseDificultadCollectionNewDatFaseDificultad);
                        oldDatDeportistaOfDatFaseDificultadCollectionNewDatFaseDificultad = em.merge(oldDatDeportistaOfDatFaseDificultadCollectionNewDatFaseDificultad);
                    }
                }
            }
            for (DatAllround datAllroundCollectionNewDatAllround : datAllroundCollectionNew) {
                if (!datAllroundCollectionOld.contains(datAllroundCollectionNewDatAllround)) {
                    DatDeportista oldDatDeportistaOfDatAllroundCollectionNewDatAllround = datAllroundCollectionNewDatAllround.getDatDeportista();
                    datAllroundCollectionNewDatAllround.setDatDeportista(datDeportista);
                    datAllroundCollectionNewDatAllround = em.merge(datAllroundCollectionNewDatAllround);
                    if (oldDatDeportistaOfDatAllroundCollectionNewDatAllround != null && !oldDatDeportistaOfDatAllroundCollectionNewDatAllround.equals(datDeportista)) {
                        oldDatDeportistaOfDatAllroundCollectionNewDatAllround.getDatAllroundCollection().remove(datAllroundCollectionNewDatAllround);
                        oldDatDeportistaOfDatAllroundCollectionNewDatAllround = em.merge(oldDatDeportistaOfDatAllroundCollectionNewDatAllround);
                    }
                }
            }
            for (HisResultado hisResultadoCollectionNewHisResultado : hisResultadoCollectionNew) {
                if (!hisResultadoCollectionOld.contains(hisResultadoCollectionNewHisResultado)) {
                    DatDeportista oldDatDeportistaOfHisResultadoCollectionNewHisResultado = hisResultadoCollectionNewHisResultado.getDatDeportista();
                    hisResultadoCollectionNewHisResultado.setDatDeportista(datDeportista);
                    hisResultadoCollectionNewHisResultado = em.merge(hisResultadoCollectionNewHisResultado);
                    if (oldDatDeportistaOfHisResultadoCollectionNewHisResultado != null && !oldDatDeportistaOfHisResultadoCollectionNewHisResultado.equals(datDeportista)) {
                        oldDatDeportistaOfHisResultadoCollectionNewHisResultado.getHisResultadoCollection().remove(hisResultadoCollectionNewHisResultado);
                        oldDatDeportistaOfHisResultadoCollectionNewHisResultado = em.merge(oldDatDeportistaOfHisResultadoCollectionNewHisResultado);
                    }
                }
            }
            for (DatFaseVelocidad datFaseVelocidadCollectionNewDatFaseVelocidad : datFaseVelocidadCollectionNew) {
                if (!datFaseVelocidadCollectionOld.contains(datFaseVelocidadCollectionNewDatFaseVelocidad)) {
                    DatDeportista oldDatDeportistaOfDatFaseVelocidadCollectionNewDatFaseVelocidad = datFaseVelocidadCollectionNewDatFaseVelocidad.getDatDeportista();
                    datFaseVelocidadCollectionNewDatFaseVelocidad.setDatDeportista(datDeportista);
                    datFaseVelocidadCollectionNewDatFaseVelocidad = em.merge(datFaseVelocidadCollectionNewDatFaseVelocidad);
                    if (oldDatDeportistaOfDatFaseVelocidadCollectionNewDatFaseVelocidad != null && !oldDatDeportistaOfDatFaseVelocidadCollectionNewDatFaseVelocidad.equals(datDeportista)) {
                        oldDatDeportistaOfDatFaseVelocidadCollectionNewDatFaseVelocidad.getDatFaseVelocidadCollection().remove(datFaseVelocidadCollectionNewDatFaseVelocidad);
                        oldDatDeportistaOfDatFaseVelocidadCollectionNewDatFaseVelocidad = em.merge(oldDatDeportistaOfDatFaseVelocidadCollectionNewDatFaseVelocidad);
                    }
                }
            }
            for (DatDireccion datDireccionCollectionNewDatDireccion : datDireccionCollectionNew) {
                if (!datDireccionCollectionOld.contains(datDireccionCollectionNewDatDireccion)) {
                    DatDeportista oldDatDeportistaOfDatDireccionCollectionNewDatDireccion = datDireccionCollectionNewDatDireccion.getDatDeportista();
                    datDireccionCollectionNewDatDireccion.setDatDeportista(datDeportista);
                    datDireccionCollectionNewDatDireccion = em.merge(datDireccionCollectionNewDatDireccion);
                    if (oldDatDeportistaOfDatDireccionCollectionNewDatDireccion != null && !oldDatDeportistaOfDatDireccionCollectionNewDatDireccion.equals(datDeportista)) {
                        oldDatDeportistaOfDatDireccionCollectionNewDatDireccion.getDatDireccionCollection().remove(datDireccionCollectionNewDatDireccion);
                        oldDatDeportistaOfDatDireccionCollectionNewDatDireccion = em.merge(oldDatDeportistaOfDatDireccionCollectionNewDatDireccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datDeportista.getIddeportista();
                if (findDatDeportista(id) == null) {
                    throw new NonexistentEntityException("The datDeportista with id " + id + " no longer exists.");
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
            DatDeportista datDeportista;
            try {
                datDeportista = em.getReference(DatDeportista.class, id);
                datDeportista.getIddeportista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datDeportista with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            DatDeportistaFederacion datDeportistaFederacionOrphanCheck = datDeportista.getDatDeportistaFederacion();
            if (datDeportistaFederacionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatDeportista (" + datDeportista + ") cannot be destroyed since the DatDeportistaFederacion " + datDeportistaFederacionOrphanCheck + " in its datDeportistaFederacion field has a non-nullable datDeportista field.");
            }
            Collection<DatFaseBloque> datFaseBloqueCollectionOrphanCheck = datDeportista.getDatFaseBloqueCollection();
            for (DatFaseBloque datFaseBloqueCollectionOrphanCheckDatFaseBloque : datFaseBloqueCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatDeportista (" + datDeportista + ") cannot be destroyed since the DatFaseBloque " + datFaseBloqueCollectionOrphanCheckDatFaseBloque + " in its datFaseBloqueCollection field has a non-nullable datDeportista field.");
            }
            Collection<DatFaseDificultad> datFaseDificultadCollectionOrphanCheck = datDeportista.getDatFaseDificultadCollection();
            for (DatFaseDificultad datFaseDificultadCollectionOrphanCheckDatFaseDificultad : datFaseDificultadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatDeportista (" + datDeportista + ") cannot be destroyed since the DatFaseDificultad " + datFaseDificultadCollectionOrphanCheckDatFaseDificultad + " in its datFaseDificultadCollection field has a non-nullable datDeportista field.");
            }
            Collection<DatAllround> datAllroundCollectionOrphanCheck = datDeportista.getDatAllroundCollection();
            for (DatAllround datAllroundCollectionOrphanCheckDatAllround : datAllroundCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatDeportista (" + datDeportista + ") cannot be destroyed since the DatAllround " + datAllroundCollectionOrphanCheckDatAllround + " in its datAllroundCollection field has a non-nullable datDeportista field.");
            }
            Collection<HisResultado> hisResultadoCollectionOrphanCheck = datDeportista.getHisResultadoCollection();
            for (HisResultado hisResultadoCollectionOrphanCheckHisResultado : hisResultadoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatDeportista (" + datDeportista + ") cannot be destroyed since the HisResultado " + hisResultadoCollectionOrphanCheckHisResultado + " in its hisResultadoCollection field has a non-nullable datDeportista field.");
            }
            Collection<DatFaseVelocidad> datFaseVelocidadCollectionOrphanCheck = datDeportista.getDatFaseVelocidadCollection();
            for (DatFaseVelocidad datFaseVelocidadCollectionOrphanCheckDatFaseVelocidad : datFaseVelocidadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatDeportista (" + datDeportista + ") cannot be destroyed since the DatFaseVelocidad " + datFaseVelocidadCollectionOrphanCheckDatFaseVelocidad + " in its datFaseVelocidadCollection field has a non-nullable datDeportista field.");
            }
            Collection<DatDireccion> datDireccionCollectionOrphanCheck = datDeportista.getDatDireccionCollection();
            for (DatDireccion datDireccionCollectionOrphanCheckDatDireccion : datDireccionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DatDeportista (" + datDeportista + ") cannot be destroyed since the DatDireccion " + datDireccionCollectionOrphanCheckDatDireccion + " in its datDireccionCollection field has a non-nullable datDeportista field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            NomTipoSangre idtiposangre = datDeportista.getIdtiposangre();
            if (idtiposangre != null) {
                idtiposangre.getDatDeportistaCollection().remove(datDeportista);
                idtiposangre = em.merge(idtiposangre);
            }
            NomSexo idsexo = datDeportista.getIdsexo();
            if (idsexo != null) {
                idsexo.getDatDeportistaCollection().remove(datDeportista);
                idsexo = em.merge(idsexo);
            }
            em.remove(datDeportista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatDeportista> findDatDeportistaEntities() {
        return findDatDeportistaEntities(true, -1, -1);
    }

    public List<DatDeportista> findDatDeportistaEntities(int maxResults, int firstResult) {
        return findDatDeportistaEntities(false, maxResults, firstResult);
    }

    private List<DatDeportista> findDatDeportistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatDeportista.class));
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

    public DatDeportista findDatDeportista(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatDeportista.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatDeportistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatDeportista> rt = cq.from(DatDeportista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
