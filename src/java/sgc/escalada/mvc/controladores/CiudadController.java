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
import sgc.escalada.mvc.entidades.NomCanton;
import sgc.escalada.mvc.entidades.NomCiudad;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.modelos.CiudadModel;
import sgc.escalada.mvc.modelos.ParroquiaModel;

/**
 *
 * @author Ariam
 */
@Controller
public class CiudadController {

    CiudadModel ciudadModelo = new CiudadModel(null);    
    ParroquiaModel parroquiaModel = new ParroquiaModel(null);

    @RequestMapping(value = "insertarCiudad", method = RequestMethod.POST)
    public void insertarCiudad(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
//        String usser = req.getParameterValues("Usuario")[0];

        String nombre = req.getParameterValues("nombreCiudad")[0];
        int idCanton = Integer.parseInt(req.getParameterValues("idCanton")[0]);
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        NomCiudad ciudad = new NomCiudad(null, nombre);
        ciudad.setIdcanton(new NomCanton(idCanton));
        Map map = new HashMap();
        try {
            this.ciudadModelo.create(ciudad);
            map.put("success", true);
            map.put("msg", "Ciudad insertada satisfactoriamente");
           res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ya existe una ciudad con ese nombre.");
             res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "eliminarCiudad", method = RequestMethod.POST)
    public void eliminarCiudad(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int idCiudad = Integer.parseInt(req.getParameterValues("idCiudad")[0]);
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
     
        Map map = new HashMap();
        try {
            NomCiudad parroquia = ciudadModelo.findNomCiudad(idCiudad);           
            for (Object object : parroquia.getNomParroquiaCollection().toArray()) {
                parroquiaModel.destroy(((NomParroquia) object).getIdparroquia());
            }
            this.ciudadModelo.destroy(idCiudad);
            map.put("success", true);
            map.put("msg", "Ciudad eliminada satisfactoriamente.");
         res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "No existe una ciudad con ese nombre.");
              res.getWriter().print(jsonObj.toJson(map));
        }
    }

//    @RequestMapping(value = "listarCiudads.action", method = RequestMethod.GET)
//    public void listarCiudads(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        List<NomCiudad> listaCiudads = this.parroquiaModelo.findNomCiudadEntities(); 
//        Gson jsonObj = new Gson();
//        PrintWriter out = res.getWriter();
//
//        out.printf(jsonObj.toJson(armarJsonListaCiudads(listaCiudads)));
//    }
    @RequestMapping(value = "listarCiudades", method = RequestMethod.GET)
    public void listarCiudadesPorIdCanton(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<NomCiudad> listaCiudades;
        try {
            int idCanton = Integer.parseInt(req.getParameterValues("idCanton")[0]);
            listaCiudades = this.ciudadModelo.findNomCiudadEntitiesByIdCanton(new NomCanton(idCanton));                   
        } catch (Exception e) {
            listaCiudades = ciudadModelo.findNomCiudadEntities();
        }

        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaCiudads(listaCiudades)));

       
    }

    private List<Map> armarJsonListaCiudads(List<NomCiudad> listaCiudads) {
        List<Map> listMap = new LinkedList<>();

        for (NomCiudad parroquia : listaCiudads) {
            Map map = new HashMap();
            map.put("idCiudad", parroquia.getIdciudad());
            map.put("idCanton", parroquia.getIdcanton().getIdcanton());
            map.put("ciudad", parroquia.getCiudad());
            listMap.add(map);
        }

        return listMap;
    }

}
