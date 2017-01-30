/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgc.escalada.mvc.controladores;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sgc.escalada.mvc.entidades.NomFederacionProvincial;
import sgc.escalada.mvc.entidades.NomProvincia;
import sgc.escalada.mvc.modelos.FederacionProvincialModel;

/**
 *
 * @author rolando
 */
@Controller
public class FederacionProvincialController {

    FederacionProvincialModel federacionModelo = new FederacionProvincialModel(null);

    @RequestMapping(value = "insertarFederacionProvincial", method = RequestMethod.POST)
    public void insertarFederacionProvincial(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        int idProvincia = Integer.parseInt(req.getParameterValues("idProvincia")[0]);
        String nombre = req.getParameterValues("nombreFederacion")[0];
        Gson jsonObj = new Gson();
          res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        

        NomFederacionProvincial federacion = new NomFederacionProvincial(null);
        federacion.setFederacion(nombre);
        federacion.setIdprovincia(new NomProvincia(idProvincia));

        Map map = new HashMap();
        try {
            this.federacionModelo.create(federacion);
            map.put("success", true);
            map.put("msg", "Federaci&oacute;n insertada satisfactoriamente");
           res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ya existe una federaci&oacute;n con ese nombre.");
            res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "eliminarFederacion", method = RequestMethod.POST)
    public void eliminarFederacion(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int idFederacion = Integer.parseInt(req.getParameterValues("idFederacion")[0]);
        Gson jsonObj = new Gson();
         res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       
        Map map = new HashMap();
        try {
            this.federacionModelo.destroy(idFederacion);
            map.put("success", true);
            map.put("msg", "Federaci&oacute;n eliminada satisfactoriamente");
            res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "No existe una federaci&oacute;n con ese nombre.");
             res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "listarFederaciones", method = RequestMethod.GET)
    public void listarFederaciones(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<NomFederacionProvincial> listaFederaciones;
        try {
            int idProvincia = Integer.parseInt(req.getParameterValues("idProvincia")[0]);
            listaFederaciones = this.federacionModelo.findNomFederacionEntitiesByIdProvincia(new NomProvincia(idProvincia));
        } catch (Exception e) {
            listaFederaciones = this.federacionModelo.findNomFederacionProvincialEntities();
        }
        Gson jsonObj = new Gson();
         res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaCantones(listaFederaciones)));

       
    }

    private List<Map> armarJsonListaCantones(List<NomFederacionProvincial> listaFederaciones) {
        List<Map> listaMap = new LinkedList<>();

        for (NomFederacionProvincial federacion : listaFederaciones) {
            Map map = new HashMap();
            map.put("idFederacion", federacion.getIdfederacion());
            map.put("idProvincia", federacion.getIdprovincia().getIdprovincia());
            map.put("federacion", federacion.getFederacion());
            listaMap.add(map);
        }

        return listaMap;
    }
}
