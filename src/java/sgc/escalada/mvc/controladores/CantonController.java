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
import sgc.escalada.mvc.entidades.NomProvincia;
import sgc.escalada.mvc.modelos.CantonModel;
import sgc.escalada.mvc.modelos.CiudadModel;


/**
 *
 * @author Yosbel
 */
@Controller
public class CantonController {

    CantonModel cantonModelo = new CantonModel(null);
    CiudadModel ciudadModel = new CiudadModel(null);

    @RequestMapping(value = "insertarCanton", method = RequestMethod.POST)
    public void insertarCanton(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
//        String usser = req.getParameterValues("Usuario")[0];

        String nombre = req.getParameterValues("nombreCanton")[0];
        int idProvincia = Integer.parseInt(req.getParameterValues("idProvincia")[0]);
        Gson jsonObj = new Gson();
         res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        NomCanton canton = new NomCanton(null, nombre);
        canton.setIdprovincia(new NomProvincia(idProvincia));
        Map map = new HashMap();
        try {
            this.cantonModelo.create(canton);
            map.put("success", true);
            map.put("msg", "Cant&oacute;n insertado satisfactoriamente");
           res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ya existe un cant&oacute;n con ese nombre.");
             res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "listarCantones", method = RequestMethod.GET)
    public void listarCantones(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<NomCanton> listaCantones = this.cantonModelo.findNomCantonEntities();
        Gson jsonObj = new Gson();
       res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaCantones(listaCantones)));

      
    }

    @RequestMapping(value = "listarCantonesPorIdProvincia", method = RequestMethod.GET)
    public void listarCantonPorIdProvincia(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<NomCanton> listaCantones;
        try {
            int idProvincia = Integer.parseInt(req.getParameterValues("idProvincia")[0]);
            listaCantones = this.cantonModelo.findNomCantonEntitiesByIdProvincia(new NomProvincia(idProvincia));
        } catch (Exception e) {
            listaCantones = this.cantonModelo.findNomCantonEntities();
        }

        Gson jsonObj = new Gson();
       res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaCantones(listaCantones)));

       
    }

    @RequestMapping(value = "eliminarCanton", method = RequestMethod.POST)
    public void eliminarCanton(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int idCanton = Integer.parseInt(req.getParameterValues("idCanton")[0]);
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        
        Map map = new HashMap();
        try {
            NomCanton canton = cantonModelo.findNomCanton(idCanton);
            for (Object object : canton.getNomCiudadCollection().toArray()) {
                ciudadModel.destroy(((NomCiudad) object).getIdciudad());
            }
            this.cantonModelo.destroy(idCanton);
            map.put("success", true);
            map.put("msg", "Cant&oacute;n eliminado satisfactoriamente");
            res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "No existe un cant&oacute;n con ese nombre.");
            res.getWriter().print(jsonObj.toJson(map));
        }

       
    }

    private List<Map> armarJsonListaCantones(List<NomCanton> listaCantones) {
        List<Map> listaMap = new LinkedList<>();

        for (NomCanton canton : listaCantones) {
            Map map = new HashMap();
            map.put("idCanton", canton.getIdcanton());
            map.put("idProvincia", canton.getIdprovincia().getIdprovincia());
            map.put("canton", canton.getCanton());
            listaMap.add(map);
        }

        return listaMap;
    }
}
