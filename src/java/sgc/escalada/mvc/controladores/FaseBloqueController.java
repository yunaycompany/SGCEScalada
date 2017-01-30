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
import sgc.escalada.mvc.entidades.DatFaseBloque;
import sgc.escalada.mvc.entidades.DatFaseBloquePK;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.entidades.NomEtapa;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.modelos.FaseBloqueModel;

/**
 *
 * @author rolando
 */
@Controller
public class FaseBloqueController {

    FaseBloqueModel faseBloqueModelo = new FaseBloqueModel(null);

    @RequestMapping(value = "listarFaseBloque", method = RequestMethod.GET)
    public void listarFaseBloque(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int idCompetencia = Integer.parseInt(req.getParameterValues("idCompetencia")[0]);
        int idCategoria = Integer.parseInt(req.getParameterValues("idCategoria")[0]);
        int idEtapa = Integer.parseInt(req.getParameterValues("idEtapa")[0]);
        List<DatFaseBloque> listaFases = this.faseBloqueModelo.
                findDatFaseBloqueEntitiesByIdCompCategEtap(new DatCompetencia(idCompetencia),
                        new NomCategoria(idCategoria), new NomEtapa(idEtapa));
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaFaseBloque(listaFases)));

        
    }

    private List<Map> armarJsonListaFaseBloque(List<DatFaseBloque> listaFases) {
        List<Map> listMap = new LinkedList<>();

        for (DatFaseBloque fase : listaFases) {
            Map map = new HashMap();
            map.put("idCompetencia", fase.getDatCompetencia().getIdcompetencia());
            map.put("idDeportista", fase.getDatDeportista().getIddeportista());
            map.put("nombre", fase.getDatDeportista().getNombre());
            map.put("pApellido", fase.getDatDeportista().getPapellido());
            map.put("sApellido", fase.getDatDeportista().getSapellido());
            map.put("idCategoria", fase.getNomCategoria().getIdcategoria());
            map.put("categoria", fase.getNomCategoria().getCategoria());
            map.put("idEtapa", fase.getNomEtapa().getIdetapa());
            map.put("etapa", fase.getNomEtapa().getEtapa());
            map.put("t1", fase.getT1());
            map.put("b1", fase.getB1());
            map.put("t2", fase.getT2());
            map.put("b2", fase.getB2());
            map.put("t3", fase.getT3());
            map.put("b3", fase.getB3());
            map.put("t4", fase.getT4());
            map.put("b4", fase.getB4());
            map.put("t5", fase.getT5());
            map.put("b5", fase.getB5());
            map.put("finalizada", fase.getFinalizada());
            map.put("idProvincia", ((NomParroquia) fase.getDatDeportista().getNomParroquiaCollection().toArray()[0]).getIdciudad().getIdcanton().getIdprovincia().getIdprovincia());
            map.put("provincia", ((NomParroquia) fase.getDatDeportista().getNomParroquiaCollection().toArray()[0]).getIdciudad().getIdcanton().getIdprovincia().getProvincia());

            listMap.add(map);
        }

        return listMap;
    }

    @RequestMapping(value = "guardarFaseBloque", method = RequestMethod.GET)
    public void guardarFaseBloque(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
        String dato = req.getParameterValues("bloque")[0];
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
            int t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0;
            DatFaseBloque fase = this.faseBloqueModelo.findDatFaseBloque(new DatFaseBloquePK(idCompetencia, idDeportista, idCategoria, idEtapa));

            t1 = Integer.parseInt(objeto.get("t1").toString());
            t2 = Integer.parseInt(objeto.get("t2").toString());
            t3 = Integer.parseInt(objeto.get("t3").toString());
            t4 = Integer.parseInt(objeto.get("t4").toString());
            b1 = Integer.parseInt(objeto.get("b1").toString());
            b2 = Integer.parseInt(objeto.get("b2").toString());
            b3 = Integer.parseInt(objeto.get("b3").toString());
            b4 = Integer.parseInt(objeto.get("b4").toString());
            fase.setB1(b1);
            fase.setB2(b2);
            fase.setB3(b3);
            fase.setB4(b4);
            fase.setT1(t1);
            fase.setT2(t2);
            fase.setT3(t3);
            fase.setT4(t4);
            if (idEtapa == 1) {
                t5 = Integer.parseInt(objeto.get("t5").toString());
                b5 = Integer.parseInt(objeto.get("b5").toString());
                fase.setT5(t5);
                fase.setB5(b5);
            }

            try {
                if (!fase.getFinalizada()) {
                    fase.setFinalizada(true);
                    this.faseBloqueModelo.edit(fase);
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
