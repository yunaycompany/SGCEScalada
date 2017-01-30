/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgc.escalada.mvc.controladores;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sgc.escalada.mvc.entidades.DatAllround;
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.entidades.DatDeportistaFederacion;
import sgc.escalada.mvc.entidades.DatDireccion;
import sgc.escalada.mvc.entidades.DatFaseBloque;
import sgc.escalada.mvc.entidades.DatFaseDificultad;
import sgc.escalada.mvc.entidades.DatFaseVelocidad;
import sgc.escalada.mvc.entidades.NomCanton;
import sgc.escalada.mvc.entidades.NomFederacionProvincial;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.entidades.NomProvincia;
import sgc.escalada.mvc.entidades.NomSexo;
import sgc.escalada.mvc.entidades.NomTipoSangre;
import sgc.escalada.mvc.modelos.AllroundModel;
import sgc.escalada.mvc.modelos.DeportistaFederacionModel;
import sgc.escalada.mvc.modelos.DeportistaModel;
import sgc.escalada.mvc.modelos.DireccionModel;
import sgc.escalada.mvc.modelos.FaseBloqueModel;
import sgc.escalada.mvc.modelos.FaseDificultadModel;
import sgc.escalada.mvc.modelos.FaseVelocidadModel;
import sgc.escalada.mvc.modelos.exceptions.PreexistingEntityException;

/**
 *
 * @author rolando
 */
@Controller
public class DeportistaController {

    DeportistaModel deportistaModelo = new DeportistaModel(null);
    DeportistaFederacionModel deportistaFederacionModelo = new DeportistaFederacionModel(null);
    AllroundModel allroundModel = new AllroundModel(null);
    FaseBloqueModel faseBloqueModel = new FaseBloqueModel(null);
    FaseDificultadModel faseDificultadModel = new FaseDificultadModel(null);
    FaseVelocidadModel faseVelocidadModel = new FaseVelocidadModel(null);
    DireccionModel direccionModel = new DireccionModel(null);

    @RequestMapping(value = "insertarDeportista", method = RequestMethod.GET)
    public void insertarDeportista(HttpServletRequest req, HttpServletResponse res)
            throws IOException, PreexistingEntityException, Exception {

        String cedula = req.getParameterValues("cedula")[0];
        Date fechaNacimiento = new Date(req.getParameterValues("fechaNacimiento")[0]);
        Date fechaIngreso = new Date(req.getParameterValues("fechaIngreso")[0]);
        String nombreDeportista = req.getParameterValues("nombreDeportista")[0];
        String pApellido = req.getParameterValues("pApellido")[0];
        String sApellido = req.getParameterValues("sApellido")[0];
        int sexo = Integer.parseInt(req.getParameterValues("sexo")[0]);
        int tipoSangre = Integer.parseInt(req.getParameterValues("tipoSangre")[0]);
        int idParroquia = Integer.parseInt(req.getParameterValues("idParroquia")[0]);
        int idFederacion = Integer.parseInt(req.getParameterValues("idFederacion")[0]);
        int numeroRregistro = Integer.parseInt(req.getParameterValues("numeroRregistro")[0]);

        Gson jsonObj = new Gson();
       res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        DatDeportista deportista = new DatDeportista();
        deportista.setCedula(cedula);
        deportista.setFechanacimiento(fechaNacimiento);
        deportista.setNombre(nombreDeportista);
        deportista.setPapellido(pApellido);
        deportista.setSapellido(sApellido);
        deportista.setIdsexo(new NomSexo(sexo));
        Map map = new HashMap();
        deportista.setIdtiposangre(new NomTipoSangre(tipoSangre));
        if (deportista.getEdad() < 8) {            
            map.put("success", false);
            map.put("msg", "El deportista no fue insertado porque es menor de 8 a&ntildeos.");
            res.getWriter().print(jsonObj.toJson(map));
            return;
        }

        
        try {
            this.deportistaModelo.create(deportista);
            deportista.getNomParroquiaCollection().add(new NomParroquia(idParroquia));

            DatDeportistaFederacion deportistaFederacion = new DatDeportistaFederacion();
            deportistaFederacion.setDatDeportista(deportista);
            deportistaFederacion.setNomFederacionProvincial(new NomFederacionProvincial(idFederacion));
            deportistaFederacion.setNumeroregistro(numeroRregistro);
            deportistaFederacion.setFechaingreso(fechaIngreso);

            try {
                this.deportistaFederacionModelo.create(deportistaFederacion);
                deportista.setDatDeportistaFederacion(deportistaFederacion);

                this.deportistaModelo.edit(deportista);
                map.put("success", true);
                map.put("msg", "Deportista insertado satisfactoriamente");
                 res.getWriter().print(jsonObj.toJson(map));
            } catch (Exception e) {
                deportistaModelo.destroy(deportista.getIddeportista());
                map.put("error", e.getMessage());
                map.put("success", false);
                map.put("msg", "Ya existe un deportista con ese n&uacute;mero de registro.");
                res.getWriter().print(jsonObj.toJson(map));
            }

        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ya existe el deportista.");
           res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "eliminarDeportistas", method = RequestMethod.GET)
    public void eliminarDeportista(HttpServletRequest req, HttpServletResponse res)
            throws IOException, PreexistingEntityException, Exception {

        String dato = req.getParameterValues("deportistas")[0];
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(dato);

        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        Map map = new HashMap();

        try {
            for (int i = 0; i < array.size(); i++) {
                JSONObject objeto = (JSONObject) array.get(i);
                int idDeportista = Integer.parseInt(objeto.get("idDeportista").toString());
                DatDeportista dep = deportistaModelo.findDatDeportista(idDeportista);
                deportistaFederacionModelo.destroy(dep.getDatDeportistaFederacion().getDatDeportistaFederacionPK());
                for (Object object : dep.getDatAllroundCollection().toArray()) {
                    allroundModel.destroy(((DatAllround)object).getDatAllroundPK());
                }                
                for (Object object : dep.getDatDireccionCollection().toArray()) {
                    direccionModel.destroy(((DatDireccion)object).getDatDireccionPK());
                } 
                for (Object object : dep.getDatFaseBloqueCollection().toArray()) {
                    faseBloqueModel.destroy(((DatFaseBloque)object).getDatFaseBloquePK());
                }
                for (Object object : dep.getDatFaseDificultadCollection().toArray()) {
                    faseDificultadModel.destroy(((DatFaseDificultad)object).getDatFaseDificultadPK());
                }
                for (Object object : dep.getDatFaseVelocidadCollection().toArray()) {
                    faseVelocidadModel.destroy(((DatFaseVelocidad)object).getDatFaseVelocidadPK());
                }       
                deportistaModelo.destroy(idDeportista);
            }

            map.put("success", true);
            map.put("msg", "Deportistas eliminados satisfactoriamente.");
            res.getWriter().print(jsonObj.toJson(map));

        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ocurri&oacute; un error al intentar eliminar los deportistas.");
            res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "listarDeportistas", method = RequestMethod.GET)
    public void listarDeportistasEnCompetencia(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<DatDeportista> listaDeportistas = this.deportistaModelo.findDatDeportistaEntities();
        List<Map> provinciaJson = new LinkedList<>();
        boolean preguntar = true;
        int idCompetencia = -1;
        try {
            idCompetencia = Integer.parseInt(req.getParameterValues("idCompetencia")[0]);
        } catch (Exception e) {
            preguntar = false;
        }

        for (DatDeportista deportista : listaDeportistas) {
            boolean bandera = true;
            if (preguntar) {
                Object[] velocidad = deportista.getDatFaseVelocidadCollection().toArray();
                for (int i = 0; i < velocidad.length; i++) {
                    if (((DatFaseVelocidad) (velocidad[i])).getDatCompetencia().getIdcompetencia() == idCompetencia) {
                        bandera = false;
                        break;
                    }
                }
            }
            if (bandera) {
                if (deportista.getCategoria() < 15) {
                    Map map = new HashMap();
                    map.put("iddeportista", deportista.getIddeportista());
                    map.put("nombre", deportista.getNombre());
                    map.put("idSexo", deportista.getIdsexo().getIdsexo());
                    map.put("sexo", deportista.getIdsexo().getSexo());
                    map.put("idTipoSangre", deportista.getIdtiposangre().getIdtiposangre());
                    map.put("tipoSangre", deportista.getIdtiposangre().getTiposangre());
                    map.put("pApellido", deportista.getPapellido());
                    map.put("sApellido", deportista.getSapellido());

                    NomParroquia parroquia = (NomParroquia) deportista.getNomParroquiaCollection().toArray()[0];
                    NomCanton canton = parroquia.getIdciudad().getIdcanton();
                    NomProvincia provincia = canton.getIdprovincia();

                    map.put("idParroquia", parroquia.getIdparroquia());
                    map.put("parroquia", parroquia.getParroquia());
                    map.put("idCanton", canton.getIdcanton());
                    map.put("canton", canton.getCanton());
                    map.put("idProvincia", provincia.getIdprovincia());
                    map.put("provincia", provincia.getProvincia());

                    provinciaJson.add(map);
                }
            }

        }

        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(provinciaJson));

        
    }

    @RequestMapping(value = "edad", method = RequestMethod.GET)
    public void edad(HttpServletRequest req, HttpServletResponse res) throws IOException {
        DatDeportista deportista = this.deportistaModelo.findDatDeportista(47);
        Map map = new HashMap();
        map.put("edad", deportista.getEdad());
        map.put("categoria", deportista.getCategoria());
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(map));

    }
}

//    @RequestMapping(value = "listarDeportistas.action", method = RequestMethod.GET)
//    public void listarDeportistas(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        List<DatDeportista> listaDeportistas = this.deportistaModelo.findDatDeportistaEntities();
//        List<Map> provinciaJson = new LinkedList<>();
//
//        for (DatDeportista deportista : listaDeportistas) {
//            Map map = new HashMap();
//            map.put("iddeportista", deportista.getIddeportista());
//            map.put("nombre", deportista.getNombre());
//            map.put("idSexo", deportista.getIdsexo().getIdsexo());
//            map.put("sexo", deportista.getIdsexo().getSexo());
//            map.put("idTipoSangre", deportista.getIdtiposangre().getIdtiposangre());
//            map.put("tipoSangre", deportista.getIdtiposangre().getTiposangre());
//            map.put("pApellido", deportista.getPapellido());
//            map.put("sApellido", deportista.getSapellido());
//
//            NomParroquia parroquia = ((NomParroquia) (deportista.getNomParroquiaCollection().toArray()[0]));
//            NomCanton canton = parroquia.getIdcanton();
//            NomProvincia provincia = canton.getIdprovincia();
//
//            map.put("idParroquia", parroquia.getIdparroquia());
//            map.put("parroquia", parroquia.getParroquia());
//            map.put("idCanton", canton.getIdcanton());
//            map.put("canton", canton.getCanton());
//            map.put("idProvincia", provincia.getIdprovincia());
//            map.put("provincia", provincia.getProvincia());
//
//            provinciaJson.add(map);
//        }
//
//        Gson jsonObj = new Gson();
//        PrintWriter out = res.getWriter();
//
//        out.printf(jsonObj.toJson(provinciaJson));
//    }
