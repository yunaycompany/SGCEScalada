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
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sgc.escalada.mvc.entidades.DatAllround;
import sgc.escalada.mvc.entidades.DatAllroundPK;
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.entidades.DatFaseBloque;
import sgc.escalada.mvc.entidades.DatFaseDificultad;
import sgc.escalada.mvc.entidades.DatFaseVelocidad;
import sgc.escalada.mvc.entidades.HisResultado;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.entidades.NomEtapa;
import sgc.escalada.mvc.entidades.NomLugar;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.modelos.AllroundModel;
import sgc.escalada.mvc.modelos.CompetenciaModel;
import sgc.escalada.mvc.modelos.DeportistaModel;
import sgc.escalada.mvc.modelos.FaseBloqueModel;
import sgc.escalada.mvc.modelos.FaseDificultadModel;
import sgc.escalada.mvc.modelos.FaseVelocidadModel;
import sgc.escalada.mvc.modelos.ResultadoModel;

/**
 *
 * @author rolando
 */
@Controller
public class CompetenciaController {

    CompetenciaModel competenciaModelo = new CompetenciaModel(null);
    FaseDificultadModel faseDificultadModelo = new FaseDificultadModel(null);
    FaseBloqueModel faseBloqueoModelo = new FaseBloqueModel(null);
    FaseVelocidadModel faseVelocidadModelo = new FaseVelocidadModel(null);
    AllroundModel allRoundModelo = new AllroundModel(null);
    DeportistaModel deportistaModelo = new DeportistaModel(null);
    ResultadoModel resultadoModel = new ResultadoModel(null);

    @RequestMapping(value = "insertarCompetencia", method = RequestMethod.GET)
    public void insertarCompetencia(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        String fecha = req.getParameterValues("fecha")[0];
        int idLugar = Integer.parseInt(req.getParameterValues("idLugar")[0]);
        String nombreCompetencia = req.getParameterValues("nombreCompetencia")[0];
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        DatCompetencia competencia = new DatCompetencia();
        competencia.setFecha(new Date());
        competencia.setIdlugar(new NomLugar(idLugar));
        competencia.setNombre(nombreCompetencia);
        competencia.setFinalizada(false);

        Map map = new HashMap();
        try {
            this.competenciaModelo.create(competencia);
            map.put("success", true);
            map.put("msg", "Competencia insertada satisfactoriamente");
            res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ya existe la competencia.");
             res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "eliminarCompetencia", method = RequestMethod.GET)
    public void eliminarCompetencia(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int idCompetencia = Integer.parseInt(req.getParameterValues("idCompetencia")[0]);
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        Map map = new HashMap();
        try {
            DatCompetencia competencia = competenciaModelo.findDatCompetencia(idCompetencia);
            for (Object object : competencia.getDatAllroundCollection().toArray()) {
                allRoundModelo.destroy(((DatAllround) object).getDatAllroundPK());
            }
            for (Object object : competencia.getDatFaseBloqueCollection().toArray()) {
                faseBloqueoModelo.destroy(((DatFaseBloque) object).getDatFaseBloquePK());
            }
            for (Object object : competencia.getDatFaseDificultadCollection().toArray()) {
                faseDificultadModelo.destroy(((DatFaseDificultad) object).getDatFaseDificultadPK());
            }
            for (Object object : competencia.getDatFaseVelocidadCollection().toArray()) {
                faseVelocidadModelo.destroy(((DatFaseVelocidad) object).getDatFaseVelocidadPK());
            }
            for (Object object : competencia.getHisResultadoCollection().toArray()) {
                resultadoModel.destroy(((HisResultado) object).getHisResultadoPK());
            }

            this.competenciaModelo.destroy(idCompetencia);
            map.put("success", true);
            map.put("msg", "Competencia eliminada satisfactoriamente");
            res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "No existe la competencia.");
             res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "listarCompetencias", method = RequestMethod.GET)
    public void listarCompetencias(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<DatCompetencia> listaCompetencias = this.competenciaModelo.findDatCompetenciaEntities();
        List<Map> competenciaJson = new LinkedList<>();

        for (DatCompetencia competencia : listaCompetencias) {
            Map map = new HashMap();
            map.put("idCompetencia", competencia.getIdcompetencia());
            map.put("idLugar", competencia.getIdlugar().getIdlugar());
            map.put("lugar", competencia.getIdlugar().getLugar());
            map.put("fecha", competencia.getFecha());
            map.put("nombre", competencia.getNombre());

            competenciaJson.add(map);
        }

        Gson jsonObj = new Gson();
       res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(competenciaJson));

      
    }

    @RequestMapping(value = "listarCompetenciasNoFinalizadas", method = RequestMethod.GET)
    public void listarCompetenciasNoFinalizadas(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<DatCompetencia> listaCompetencias = this.competenciaModelo.findDatCompetenciaEntities();
        List<Map> competenciaJson = new LinkedList<>();

        for (DatCompetencia competencia : listaCompetencias) {
            if (!competencia.isFinalizada()) {
                Map map = new HashMap();
                map.put("idCompetencia", competencia.getIdcompetencia());
                map.put("idLugar", competencia.getIdlugar().getIdlugar());
                map.put("lugar", competencia.getIdlugar().getLugar());
                map.put("fecha", competencia.getFecha());
                map.put("nombre", competencia.getNombre());

                competenciaJson.add(map);
            }
        }

        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(competenciaJson));

        
    }

    @RequestMapping(value = "inscribirDeportistasEnFases", method = RequestMethod.GET)
    public void inscribirDeportistasEnFases(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException, Exception {

        String dato = req.getParameterValues("deportistas")[0];

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(dato);
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       
        Map map = new HashMap();
        for (int i = 0; i < array.size(); i++) {
            try {
                JSONObject objeto = (JSONObject) array.get(i);
                int idCompetencia = Integer.parseInt(objeto.get("idCompetencia").toString());
                int idDeportista = Integer.parseInt(objeto.get("idDeportista").toString());
                int idCategoria = this.deportistaModelo.findDatDeportista(idDeportista).getCategoria();

                inscribirDeportistaEnFaseBloque(idDeportista, idCompetencia, idCategoria);
                inscribirDeportistaEnFaseDificultad(idDeportista, idCompetencia, idCategoria);
                inscribirDeportistaEnFaseVelocidad(idDeportista, idCompetencia, idCategoria);
                inscribirDeportistaAllRound(idDeportista, idCompetencia, idCategoria);

            } catch (Exception ex) {
                map.put("success", false);
                map.put("msg", "Error al inscribir deportistas.");
                map.put("error", ex.getMessage());
                 res.getWriter().print(jsonObj.toJson(map));
                return;
            }
        }

        map.put("success", true);
        map.put("msg", "Deportistas inscritos satisfactoriamente.");
        res.getWriter().print(jsonObj.toJson(map));

    }

    public boolean inscribirDeportistaEnTodasFases(int idDeportista, int idCompetencia, int idCategoria) throws Exception {
        return inscribirDeportistaEnFaseDificultad(idDeportista, idCompetencia, idCategoria)
                && inscribirDeportistaEnFaseBloque(idDeportista, idCompetencia, idCategoria)
                && inscribirDeportistaEnFaseVelocidad(idDeportista, idCompetencia, idCategoria)
                && inscribirDeportistaAllRound(idDeportista, idCompetencia, idCategoria);
    }

    public boolean inscribirDeportistaEnFaseDificultad(int idDeportista, int idCompetencia, int idCategoria) throws Exception {
        DatFaseDificultad faseDificultad = new DatFaseDificultad();
        faseDificultad.setDatCompetencia(new DatCompetencia(idCompetencia));
        faseDificultad.setDatDeportista(new DatDeportista(idDeportista));
        faseDificultad.setNomCategoria(new NomCategoria(idCategoria));

        faseDificultadModelo.create(faseDificultad);
        return true;

    }

    //tiene tres etapas Clasificatoria, Semifinal y Final  1 2 3
    public boolean inscribirDeportistaEnFaseBloque(int idDeportista, int idCompetencia, int idCategoria) throws Exception {
        DatFaseBloque datFaseBloque = new DatFaseBloque();
        datFaseBloque.setDatCompetencia(new DatCompetencia(idCompetencia));
        datFaseBloque.setDatDeportista(new DatDeportista(idDeportista));
        datFaseBloque.setNomCategoria(new NomCategoria(idCategoria));

        datFaseBloque.setNomEtapa(new NomEtapa(1));
        faseBloqueoModelo.create(datFaseBloque);
        datFaseBloque.setNomEtapa(new NomEtapa(2));
        faseBloqueoModelo.create(datFaseBloque);
        datFaseBloque.setNomEtapa(new NomEtapa(3));
        faseBloqueoModelo.create(datFaseBloque);
        return true;
    }

    //tiene dos etapas Clasificatoria y Final  1 3
    public boolean inscribirDeportistaEnFaseVelocidad(int idDeportista, int idCompetencia, int idCategoria) throws Exception {
        DatFaseVelocidad datFaseVelocidad = new DatFaseVelocidad();
        datFaseVelocidad.setDatCompetencia(new DatCompetencia(idCompetencia));
        datFaseVelocidad.setDatDeportista(new DatDeportista(idDeportista));
        datFaseVelocidad.setNomCategoria(new NomCategoria(idCategoria));
        datFaseVelocidad.setNomEtapa(new NomEtapa(1));

        faseVelocidadModelo.create(datFaseVelocidad);
        return true;
    }

    public boolean inscribirDeportistaAllRound(int idDeportista, int idCompetencia, int idCategoria) throws Exception {
        DatAllround allRound = new DatAllround(new DatAllroundPK());
        allRound.getDatAllroundPK().setIdcompetencia(idCompetencia);
        allRound.getDatAllroundPK().setIddeportista(idDeportista);
        allRound.setIdprovincia(((NomParroquia) deportistaModelo.findDatDeportista(idDeportista).getNomParroquiaCollection().toArray()[0]).getIdciudad().getIdcanton().getIdprovincia());
        allRound.setIdcategoria(new NomCategoria(idCategoria));

        allRoundModelo.create(allRound);
        return true;
    }

}
