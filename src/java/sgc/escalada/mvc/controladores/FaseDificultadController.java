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
import sgc.escalada.mvc.entidades.DatFaseDificultad;
import sgc.escalada.mvc.entidades.DatFaseDificultadPK;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.modelos.FaseDificultadModel;

/**
 *
 * @author rolando
 */
@Controller
public class FaseDificultadController {

    FaseDificultadModel faseDificultadModelo = new FaseDificultadModel(null);

    @RequestMapping(value = "listarFaseDificultad", method = RequestMethod.GET)
    public void listarFaseDificultad(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int idCompetencia = Integer.parseInt(req.getParameterValues("idCompetencia")[0]);
        int idCategoria = Integer.parseInt(req.getParameterValues("idCategoria")[0]);
        List<DatFaseDificultad> listaFases = this.faseDificultadModelo.
                findDatFaseDificultadEntitiesByIdCompCateg(new DatCompetencia(idCompetencia), new NomCategoria(idCategoria));
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaFases(listaFases)));

        
    }

    private List<Map> armarJsonListaFases(List<DatFaseDificultad> listaFases) {
        List<Map> listMap = new LinkedList<>();

        for (DatFaseDificultad fase : listaFases) {
            Map map = new HashMap();
            map.put("idCompetencia", fase.getDatCompetencia().getIdcompetencia());
            map.put("idDeportista", fase.getDatDeportista().getIddeportista());
            map.put("nombre", fase.getDatDeportista().getNombre());
            map.put("pApellido", fase.getDatDeportista().getPapellido());
            map.put("sApellido", fase.getDatDeportista().getSapellido());
            map.put("idCategoria", fase.getNomCategoria().getIdcategoria());
            map.put("categoria", fase.getNomCategoria().getCategoria());
            map.put("presa", fase.getPresa());
            map.put("agarre", fase.getAgarre());
            map.put("puntos", fase.getPuntos());
            map.put("finalizada", fase.getFinalizada());
            map.put("idProvincia", ((NomParroquia) fase.getDatDeportista().getNomParroquiaCollection().toArray()[0]).getIdciudad().getIdcanton().getIdprovincia().getIdprovincia());
            map.put("provincia", ((NomParroquia) fase.getDatDeportista().getNomParroquiaCollection().toArray()[0]).getIdciudad().getIdcanton().getIdprovincia().getProvincia());

            listMap.add(map);
        }

        return listMap;

    }

    @RequestMapping(value = "guardarFaseDificultad", method = RequestMethod.GET)
    public void guardarFaseDificultad(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
        String dato = req.getParameterValues("dificultad")[0];
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
            int idCategoria = Integer.parseInt(objeto.get("idCategoria").toString());
            int presa = Integer.parseInt(objeto.get("presa").toString());
            double puntos = Double.parseDouble(objeto.get("puntos").toString());
            boolean finalizada = Boolean.parseBoolean(objeto.get("finalizada").toString());
            String agarre = objeto.get("agarre").toString();

            DatFaseDificultad fase = this.faseDificultadModelo.findDatFaseDificultad(new DatFaseDificultadPK(idCompetencia, idDeportista, idCategoria));
            fase.setPresa(presa);
            fase.setPuntos(puntos);
            fase.setAgarre(agarre);
            try {
                if (!fase.getFinalizada()) {
                    fase.setFinalizada(true);
                    this.faseDificultadModelo.edit(fase);
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
