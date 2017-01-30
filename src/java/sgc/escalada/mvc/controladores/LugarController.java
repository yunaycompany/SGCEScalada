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
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.HisResultado;
import sgc.escalada.mvc.entidades.NomLugar;
import sgc.escalada.mvc.entidades.NomParroquia;
import sgc.escalada.mvc.modelos.CompetenciaModel;
import sgc.escalada.mvc.modelos.LugarModel;
import sgc.escalada.mvc.modelos.ResultadoModel;

/**
 *
 * @author rolando
 */
@Controller
public class LugarController {

    LugarModel lugarModelo = new LugarModel(null);
    CompetenciaModel competenciaModel = new CompetenciaModel(null);
    ResultadoModel resultadoModel = new ResultadoModel(null);

    @RequestMapping(value = "insertarLugar", method = RequestMethod.POST)
    public void insertarParroquia(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String nombre = req.getParameterValues("lugar")[0];
        int idParroquia = Integer.parseInt(req.getParameterValues("idParroquia")[0]);
        Gson jsonObj = new Gson();
          res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        NomLugar lugar = new NomLugar(null, nombre);
        lugar.setIdparroquia(new NomParroquia(idParroquia));
        Map map = new HashMap();
        try {
            this.lugarModelo.create(lugar);
            map.put("success", true);
            map.put("msg", "Lugar insertado satisfactoriamente");
            res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ya existe un lugar con ese nombre.");
            res.getWriter().print(jsonObj.toJson(map));
        }
    }
    
    @RequestMapping(value = "eliminarLugar", method = RequestMethod.POST)
    public void eliminarLugar(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        int idLugar = Integer.parseInt(req.getParameterValues("idLugar")[0]);
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        
        Map map = new HashMap();
        try {            
            NomLugar lugar = lugarModelo.findNomLugar(idLugar);            
            for (Object object : lugar.getDatCompetenciaCollection().toArray()) {
                competenciaModel.destroy(((DatCompetencia) object).getIdcompetencia());
            }            
            for (Object object : lugar.getHisResultadoCollection().toArray()) {
                resultadoModel.destroy(((HisResultado) object).getHisResultadoPK());
            }
            this.lugarModelo.destroy(idLugar);
            map.put("success", true);
            map.put("msg", "Lugar eliminado satisfactoriamente");
           res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "No existe un lugar con ese nombre.");
            res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "listarLugares", method = RequestMethod.GET)
    public void listarLugares(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<NomLugar> listaLugares = this.lugarModelo.findNomLugarEntities();
        Gson jsonObj = new Gson();
         res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaLugares(listaLugares)));

        
    }

    @RequestMapping(value = "listarLugaresPorIdParroquia", method = RequestMethod.GET)
    public void listarLugaresPorIdParroquia(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<NomLugar> listaLugares;
        try {
            int idParroquia = Integer.parseInt(req.getParameterValues("idParroquia")[0]);
            listaLugares = this.lugarModelo.findNomLugarEntitiesByIdParroquia(new NomParroquia(idParroquia));
        } catch (Exception e) {
            listaLugares = lugarModelo.findNomLugarEntities();
        }

        Gson jsonObj = new Gson();
         res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(armarJsonListaLugares(listaLugares)));

       
    }

    private List<Map> armarJsonListaLugares(List<NomLugar> listaLugares) {
        List<Map> listMap = new LinkedList<>();

        for (NomLugar lugar : listaLugares) {
            Map map = new HashMap();
            map.put("idLugar", lugar.getIdlugar());
            map.put("idParroquia", lugar.getIdparroquia().getIdparroquia());
            map.put("lugar", lugar.getLugar());
            listMap.add(map);
        }

        return listMap;
    }
}
