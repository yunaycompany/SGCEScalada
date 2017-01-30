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
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.DatFaseVelocidad;
import sgc.escalada.mvc.entidades.DatFaseVelocidadPK;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.entidades.NomEtapa;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.modelos.FaseVelocidadModel;

/**
 *
 * @author rolando
 */
@Controller
public class FaseVelocidadController {

    FaseVelocidadModel faseVelocidadModelo = new FaseVelocidadModel(null);

    @RequestMapping(value = "listarFaseVelocidad", method = RequestMethod.GET)
    public void listarFaseVelocidad(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int idCompetencia = Integer.parseInt(req.getParameterValues("idCompetencia")[0]);
        int idCategoria = Integer.parseInt(req.getParameterValues("idCategoria")[0]);
        int idEtapa = -1;
        try {
            idEtapa = Integer.parseInt(req.getParameterValues("idEtapa")[0]);
        } catch (Exception e) {
            
        }
        List<DatFaseVelocidad> listaFases;
        if (idEtapa != -1) {
            listaFases = this.faseVelocidadModelo.findDatFaseVelocidadEntitiesByIdCompCategEtap(new DatCompetencia(idCompetencia), new NomCategoria(idCategoria), new NomEtapa(idEtapa));
        } else {
            listaFases = this.faseVelocidadModelo.findDatFaseVelocidadEntitiesByIdCompCateg(new DatCompetencia(idCompetencia), new NomCategoria(idCategoria));
        }
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaFases(listaFases)));
        
    }

    private List<Map> armarJsonListaFases(List<DatFaseVelocidad> listaFases) {
        List<Map> listMap = new LinkedList<>();

        for (DatFaseVelocidad fase : listaFases) {
            Map map = new HashMap();
            map.put("idCompetencia", fase.getDatCompetencia().getIdcompetencia());
            map.put("idDeportista", fase.getDatDeportista().getIddeportista());
            map.put("nombre", fase.getDatDeportista().getNombre());
            map.put("pApellido", fase.getDatDeportista().getPapellido());
            map.put("sApellido", fase.getDatDeportista().getSapellido());
            map.put("idCategoria", fase.getNomCategoria().getIdcategoria());
            map.put("categoria", fase.getNomCategoria().getCategoria());
            map.put("tiempo1", fase.getTiempo1());
            map.put("tiempo2", fase.getTiempo2());
            map.put("tiempoFinal", fase.getTiempofinal());
            map.put("finalizada", fase.getFinalizada());
            map.put("idProvincia", ((NomParroquia) fase.getDatDeportista().getNomParroquiaCollection().toArray()[0]).getIdciudad().getIdcanton().getIdprovincia().getIdprovincia());
            map.put("provincia", ((NomParroquia) fase.getDatDeportista().getNomParroquiaCollection().toArray()[0]).getIdciudad().getIdcanton().getIdprovincia().getProvincia());

            listMap.add(map);
        }

        return listMap;
    }

    @RequestMapping(value = "guardarFaseVelocidad", method = RequestMethod.GET)
    public void guardarFaseDificultad(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
        String dato = req.getParameterValues("velocidad")[0];
        int idEtapa = Integer.parseInt(req.getParameterValues("idEtapa")[0]);
        int idCategoria = Integer.parseInt(req.getParameterValues("idCategoria")[0]);
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
            DatFaseVelocidad fase = null;
            if (idEtapa == 1) {
                float tiempo1 = Float.parseFloat(objeto.get("tiempo1").toString());
                float tiempo2 = Float.parseFloat(objeto.get("tiempo2").toString());

                fase = this.faseVelocidadModelo.findDatFaseVelocidad(new DatFaseVelocidadPK(idCompetencia, idDeportista, idCategoria, 1));
                fase.setTiempo1(tiempo1);
                fase.setTiempo2(tiempo2);
            } else {
                float tiempoFinal = Float.parseFloat(objeto.get("tiempoFinal").toString());
                fase = this.faseVelocidadModelo.findDatFaseVelocidad(new DatFaseVelocidadPK(idCompetencia, idDeportista, idCategoria, 1));
                fase.setTiempofinal(tiempoFinal);
            }

            try {
                if (idEtapa == 3 && !fase.getFinalizada()) {
                    fase.setFinalizada(true);
                    this.faseVelocidadModelo.edit(fase);
                } else if (idEtapa == 1) {
                    this.faseVelocidadModelo.edit(fase);
                }

            } catch (Exception ex) {
                map.put("success", false);
                map.put("msg", "Error al actualizar fase.");
                map.put("error", ex.getMessage());
                res.getWriter().print(jsonObj.toJson(map));
                return;
            }
        }

        map.put("success", true);
        map.put("msg", "Fase actualizada satisfactoriamente.");
         res.getWriter().print(jsonObj.toJson(map));
    }
}
