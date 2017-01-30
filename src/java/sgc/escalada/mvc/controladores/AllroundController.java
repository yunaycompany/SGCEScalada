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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sgc.escalada.mvc.entidades.DatAllround;
import sgc.escalada.mvc.entidades.DatAllroundPK;
import sgc.escalada.mvc.entidades.DatDeportista;
import sgc.escalada.mvc.modelos.AllroundModel;
import sgc.escalada.mvc.modelos.DeportistaModel;

/**
 *
 * @author Yosbel
 */
@Controller
public class AllroundController {
    
    AllroundModel allRoundModelo = new AllroundModel(null);
    DeportistaModel deportistaModelo = new DeportistaModel(null);
    
    @RequestMapping(value = "guardarAllRoundDG", method = RequestMethod.GET)
    public void guardarAllRoundDG(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
        String dato = req.getParameterValues("generalDG")[0];
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(dato);
        Gson jsonObj = new Gson();
//        PrintWriter out = res.getWriter();
        Map map = new HashMap();
        for (int i = 0; i < array.size(); i++) {
            JSONObject objeto = (JSONObject) array.get(i);
            int idCompetencia = Integer.parseInt(objeto.get("idCompetencia").toString());
            int idDeportista = Integer.parseInt(objeto.get("idDeportista").toString());
            int puntosv = Integer.parseInt(objeto.get("puntosD").toString());
            int rankingv = Integer.parseInt(objeto.get("rankingD").toString());
            
            DatAllround fase = this.allRoundModelo.findDatAllround(new DatAllroundPK(idDeportista, idCompetencia));
            fase.setPuntosd(puntosv);
            fase.setRankingd(rankingv);
            try {
                this.allRoundModelo.edit(fase);
            } catch (Exception ex) {
                map.put("success", false);
                map.put("msg", "Error al actualizar all round.");
                map.put("error", ex.getMessage());
                res.getWriter().print(jsonObj.toJson(map));
                return;
            }
        }
        
        map.put("success", true);
        map.put("msg", "All round actualizado satisfactoriamente.");
         res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(map));
        
    }
    
    @RequestMapping(value = "guardarAllRoundVG", method = RequestMethod.GET)
    public void guardarAllRoundVG(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
        String dato = req.getParameterValues("generalVG")[0];
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(dato);
        Gson jsonObj = new Gson();
          res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       
        Map map = new HashMap();
        for (int i = 0; i < array.size(); i++) {
            JSONObject objeto = (JSONObject) array.get(i);
            int idCompetencia = Integer.parseInt(objeto.get("idCompetencia").toString());
            int idDeportista = Integer.parseInt(objeto.get("idDeportista").toString());
            int puntosv = Integer.parseInt(objeto.get("puntosV").toString());
            int rankingv = Integer.parseInt(objeto.get("rankingV").toString());
            
            DatAllround fase = this.allRoundModelo.findDatAllround(new DatAllroundPK(idDeportista, idCompetencia));
            fase.setPuntosv(puntosv);
            fase.setRankingv(rankingv);
            try {
                this.allRoundModelo.edit(fase);
            } catch (Exception ex) {
                map.put("success", false);
                map.put("msg", "Error al actualizar all round.");
                map.put("error", ex.getMessage());
               res.getWriter().print(jsonObj.toJson(map));
                return;
            }
        }
        
        map.put("success", true);
        map.put("msg", "All round actualizado satisfactoriamente.");
         res.getWriter().print(jsonObj.toJson(map));
    }
    
    @RequestMapping(value = "guardarAllRoundBG", method = RequestMethod.GET)
    public void guardarAllRoundBG(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
        String dato = req.getParameterValues("generalBG")[0];
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(dato);
        Gson jsonObj = new Gson();
          res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       
        Map map = new HashMap();
        for (int i = 0; i < array.size(); i++) {
            JSONObject objeto = (JSONObject) array.get(i);
            int idCompetencia = Integer.parseInt(objeto.get("idCompetencia").toString());
            int idDeportista = Integer.parseInt(objeto.get("idDeportista").toString());
            int puntosv = Integer.parseInt(objeto.get("puntosB").toString());
            int rankingv = Integer.parseInt(objeto.get("rankingB").toString());
            
            DatAllround fase = this.allRoundModelo.findDatAllround(new DatAllroundPK(idDeportista, idCompetencia));
            fase.setPuntosb(puntosv);
            fase.setRankingb(rankingv);
            try {
                this.allRoundModelo.edit(fase);
            } catch (Exception ex) {
                map.put("success", false);
                map.put("msg", "Error al actualizar all round.");
                map.put("error", ex.getMessage());
             res.getWriter().print(jsonObj.toJson(map));
                return;
            }
        }
        
        map.put("success", true);
        map.put("msg", "All round actualizado satisfactoriamente.");
         res.getWriter().print(jsonObj.toJson(map));
    }
    
    @RequestMapping(value = "guardarAllRoundG", method = RequestMethod.GET)
    public void guardarAllRoundG(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
        String dato = req.getParameterValues("generalARG")[0];
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(dato);
        Gson jsonObj = new Gson();
         res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       
        Map map = new HashMap();
        for (int i = 0; i < array.size(); i++) {
            JSONObject objeto = (JSONObject) array.get(i);
            int idCompetencia = Integer.parseInt(objeto.get("idCompetencia").toString());
            int idDeportista = Integer.parseInt(objeto.get("idDeportista").toString());
            int totalPuntos = Integer.parseInt(objeto.get("totalPuntos").toString());
            int ranking = Integer.parseInt(objeto.get("ranking").toString());
            int puntos = Integer.parseInt(objeto.get("puntos").toString());
            
            DatAllround fase = this.allRoundModelo.findDatAllround(new DatAllroundPK(idDeportista, idCompetencia));
            fase.setTotalpuntos(totalPuntos);
            fase.setRanking(ranking);
            fase.setPuntos(puntos);
            try {
                this.allRoundModelo.edit(fase);
            } catch (Exception ex) {
                map.put("success", false);
                map.put("msg", "Error al actulizar all round.");
                map.put("error", ex.getMessage());
                 res.getWriter().print(jsonObj.toJson(map));
                return;
            }
        }
        
        map.put("success", true);
        map.put("msg", "All round actualizado satisfactoriamente.");
        res.getWriter().print(jsonObj.toJson(map));
    }
    
    @RequestMapping(value = "listarAllRound", method = RequestMethod.GET)
    public void listarFaseBloque(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int idCompetencia = Integer.parseInt(req.getParameterValues("idCompetencia")[0]);
        int idCategoria = Integer.parseInt(req.getParameterValues("idCategoria")[0]);
        List<DatAllround> listaFases = this.allRoundModelo.findDatAllroundEntities();
        for (int i = 0; i < listaFases.size(); i++) {
            DatAllround fase = listaFases.get(i);
            if (fase.getIdcategoria().getIdcategoria() != idCategoria || fase.getDatAllroundPK().getIdcompetencia() != idCompetencia) {
                listaFases.remove(i);
                i--;
            }
        }
        Gson jsonObj = new Gson();
          res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaFaseBloque(listaFases)));
        
       
    }
    
    private List<Map> armarJsonListaFaseBloque(List<DatAllround> listaFases) {
        List<Map> listMap = new LinkedList<>();
        
        for (DatAllround fase : listaFases) {
            Map map = new HashMap();
            map.put("idCompetencia", fase.getDatAllroundPK().getIdcompetencia());
            map.put("idDeportista", fase.getDatAllroundPK().getIddeportista());
            DatDeportista deportista = deportistaModelo.findDatDeportista(fase.getDatAllroundPK().getIddeportista());
            map.put("nombre", deportista.getNombre());
            map.put("pApellido", deportista.getPapellido());
            map.put("sApellido", deportista.getSapellido());
            map.put("idCategoria", fase.getIdcategoria().getIdcategoria());
            map.put("categoria", fase.getIdcategoria().getCategoria());
            map.put("idProvincia", fase.getIdprovincia().getIdprovincia());
            map.put("provincia", fase.getIdprovincia().getProvincia());
            map.put("rankingV", fase.getRankingv());
            map.put("rankingD", fase.getRankingd());
            map.put("rankingB", fase.getRankingb());
            map.put("puntosV", fase.getPuntosv());
            map.put("puntosD", fase.getPuntosd());
            map.put("puntosB", fase.getPuntosb());
            map.put("totalPuntos", fase.getTotalpuntos());
            map.put("ranking", fase.getRanking());
            map.put("punto", fase.getPuntos());
            
            listMap.add(map);
        }
        return listMap;
    }
}
