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
import sgc.escalada.mvc.entidades.DatAllround;
import sgc.escalada.mvc.entidades.NomCanton;
import sgc.escalada.mvc.entidades.NomFederacionProvincial;
import sgc.escalada.mvc.entidades.NomProvincia;
import sgc.escalada.mvc.modelos.AllroundModel;
import sgc.escalada.mvc.modelos.CantonModel;
import sgc.escalada.mvc.modelos.FederacionProvincialModel;
import sgc.escalada.mvc.modelos.ProvinciaModel;

/**
 *
 * @author Yosbel
 */
@Controller
public class ProvinciaController {

    ProvinciaModel provinciaModelo = new ProvinciaModel(null);
    CantonModel cantonModel = new CantonModel(null);
    FederacionProvincialModel federacionProvincialModel = new FederacionProvincialModel(null);
    AllroundModel allroundModel = new AllroundModel(null);

    /**
     *
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping(value = "insertarProvincia", method = RequestMethod.POST)
    public void insertarProvincia(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String nombre = req.getParameterValues("nombreProvincia")[0];
        Gson jsonObj = new Gson();
      res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        NomProvincia provincia = new NomProvincia(null);
        provincia.setProvincia(nombre);
        Map map = new HashMap();
        try {
            this.provinciaModelo.create(provincia);
            map.put("success", true);
            map.put("msg", "Provincia insertada satisfactoriamente");
           res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ya existe una provincia con ese nombre.");
            res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "eliminarProvincia", method = RequestMethod.POST)
    public void eliminarProvincia(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        int idProvincia = Integer.parseInt(req.getParameterValues("idProvincia")[0]);
        Gson jsonObj = new Gson();
       res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        
        Map map = new HashMap();
        try {
            NomProvincia provincia = provinciaModelo.findNomProvincia(idProvincia);
            for (Object object : provincia.getDatAllroundCollection().toArray()) {
                allroundModel.destroy(((DatAllround) object).getDatAllroundPK());
            }
            for (Object object : provincia.getNomCantonCollection().toArray()) {
                cantonModel.destroy(((NomCanton) object).getIdcanton());
            }
            for (Object object : provincia.getNomFederacionProvincialCollection().toArray()) {
                federacionProvincialModel.destroy(((NomFederacionProvincial) object).getIdfederacion());
            }
            this.provinciaModelo.destroy(idProvincia);
            map.put("success", true);
            map.put("msg", "Provincia eliminada satisfactoriamente");
           res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "No existe una provincia con ese nombre.");
           res.getWriter().print(jsonObj.toJson(map));
        }
    }

    /**
     *
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping(value = "listarProvincias", method = RequestMethod.GET)
    public void listarProvincias(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<NomProvincia> listaProvincias = this.provinciaModelo.findNomProvinciaEntities();
        List<Map> provinciaJson = new LinkedList<>();

        for (NomProvincia provincia : listaProvincias) {
            Map map = new HashMap();
            map.put("idProvincia", provincia.getIdprovincia());
            map.put("provincia", provincia.getProvincia());

            provinciaJson.add(map);
        }

        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(provinciaJson));
              
//        PrintWriter out = res.getWriter();
//
//        out.printf(jsonObj.toJson(provinciaJson));
    }

}
