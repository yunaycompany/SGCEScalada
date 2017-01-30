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
import sgc.escalada.mvc.entidades.DatDireccion;

import sgc.escalada.mvc.entidades.NomCiudad;
import sgc.escalada.mvc.entidades.NomLugar;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.modelos.DireccionModel;
import sgc.escalada.mvc.modelos.LugarModel;
import sgc.escalada.mvc.modelos.ParroquiaModel;

/**
 *
 * @author Yosbel
 */
@Controller
public class ParroquiaController {

    ParroquiaModel parroquiaModelo = new ParroquiaModel(null);
    DireccionModel direccionModel = new DireccionModel(null);
    LugarModel lugarModel = new LugarModel(null);

    @RequestMapping(value = "insertarParroquia", method = RequestMethod.POST)
    public void insertarParroquia(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
//        String usser = req.getParameterValues("Usuario")[0];

        String nombre = req.getParameterValues("nombreParroquia")[0];
        int idCiudad = Integer.parseInt(req.getParameterValues("idCiudad")[0]);
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        NomParroquia parroquia = new NomParroquia(null, nombre);
        parroquia.setIdciudad(new NomCiudad(idCiudad));
                
        Map map = new HashMap();
        try {
            this.parroquiaModelo.create(parroquia);
            map.put("success", true);
            map.put("msg", "Parroquia insertada satisfactoriamente");
             res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ya existe una parroquia con ese nombre.");
             res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "eliminarParroquia", method = RequestMethod.POST)
    public void eliminarParroquia(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int idParroquia = Integer.parseInt(req.getParameterValues("idParroquia")[0]);
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        
        Map map = new HashMap();
        try {
            NomParroquia parroquia = parroquiaModelo.findNomParroquia(idParroquia);
            for (Object object : parroquia.getDatDireccionCollection().toArray()) {
                direccionModel.destroy(((DatDireccion) object).getDatDireccionPK());
            }
            for (Object object : parroquia.getNomLugarCollection().toArray()) {
                lugarModel.destroy(((NomLugar) object).getIdlugar());
            }
            this.parroquiaModelo.destroy(idParroquia);
            map.put("success", true);
            map.put("msg", "Parroquia eliminada satisfactoriamente.");
           res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "No existe una parroquia con ese nombre.");
           res.getWriter().print(jsonObj.toJson(map));
        }
    }

//    @RequestMapping(value = "listarParroquias.action", method = RequestMethod.GET)
//    public void listarParroquias(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        List<NomParroquia> listaParroquias = this.parroquiaModelo.findNomParroquiaEntities(); 
//        Gson jsonObj = new Gson();
//        PrintWriter out = res.getWriter();
//
//        out.printf(jsonObj.toJson(armarJsonListaParroquias(listaParroquias)));
//    }
    @RequestMapping(value = "listarParroquias", method = RequestMethod.GET)
    public void listarParroquiasPorIdCiudad(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<NomParroquia> listaParroquias;
        try {
            int idCiudad = Integer.parseInt(req.getParameterValues("idCiudad")[0]);
            listaParroquias = this.parroquiaModelo.findNomParroquiaEntitiesByIdCiudad(new NomCiudad(idCiudad));
        } catch (Exception e) {
            listaParroquias = parroquiaModelo.findNomParroquiaEntities();
        }

        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaParroquias(listaParroquias)));
        
    }

    private List<Map> armarJsonListaParroquias(List<NomParroquia> listaParroquias) {
        List<Map> listMap = new LinkedList<>();

        for (NomParroquia parroquia : listaParroquias) {
            Map map = new HashMap();
            map.put("idParroquia", parroquia.getIdparroquia());
            map.put("idCiudad", parroquia.getIdciudad().getIdciudad());
            map.put("parroquia", parroquia.getParroquia());
            listMap.add(map);
        }

        return listMap;
    }

}
