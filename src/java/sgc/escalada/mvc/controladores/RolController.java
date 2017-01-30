/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sgc.escalada.mvc.controladores;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sgc.escalada.mvc.entidades.NomRol;
import sgc.escalada.mvc.modelos.RolModel;

/**
 *
 * @author Ariam
 */
@Controller
public class RolController {
    RolModel rol = new RolModel(null);
    
    /**
     *
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping(value="prueba", method = RequestMethod.GET)
    public void prueba(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        JsonObject jsonObj = new JsonObject();
        PrintWriter out = res.getWriter();
        jsonObj.addProperty("dat", "datos");
        
        NomRol r = new NomRol(null, "cuco"); 
        this.rol.create(r);
        jsonObj.addProperty("rol", r.getRol());
        jsonObj.addProperty("id", r.getIdrol());
        out.printf(jsonObj.toString());
    }
}
