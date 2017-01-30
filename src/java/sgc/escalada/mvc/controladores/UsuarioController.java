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
import sgc.escalada.mvc.entidades.UserRoles;
import sgc.escalada.mvc.entidades.Users;
import sgc.escalada.mvc.modelos.UserRolesModelo;
import sgc.escalada.mvc.modelos.UsersModelo;
import sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException;

/**
 *
 * @author Yosbel
 */
@Controller
public class UsuarioController {
    UsersModelo usersModelo = new UsersModelo(null);
    UserRolesModelo userRolesModelo = new UserRolesModelo(null);

    @RequestMapping(value = "insertarUsuario.htm", method = RequestMethod.POST)
    public void insertarUsuario(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String usser = req.getParameterValues("Usuario")[0];
        String passw = req.getParameterValues("Contrasenna")[0];
        int idRol = Integer.parseInt(req.getParameterValues("roll")[0]);
        String roll = "";
        if (idRol == 1) {
            roll = "Administrador";
        } else if (idRol == 2) {
            roll = "Jurado";
        } else {
            roll = "Cliente";
        }

        Gson jsonObj = new Gson();
       res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       
        Users user = new Users(usser, passw);
        user.setEnabled(Boolean.TRUE);

        Map map = new HashMap();
        try {
            this.usersModelo.create(user);
            UserRoles role = new UserRoles();
            role.setRole(roll);
            role.setUsername(user);
            this.userRolesModelo.create(role);

            map.put("success", true);
            map.put("msg", "Usuario insertado satisfactoriamente");
          res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("error", e.getMessage());
            map.put("success", false);
            map.put("msg", "Ya existe un usuario con ese nombre.");
             res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "listarUsuarios.htm", method = RequestMethod.GET)
    public void listarUsuarios(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<Users> listaUsers = this.usersModelo.findUsersEntities();
        List<Map> usuariosJson = new LinkedList<>();

        for (Users usuario : listaUsers) {
            Map map = new HashMap();
            map.put("usuario", usuario.getUsername());
            usuariosJson.add(map);
        }

        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(usuariosJson));

        
    }

    @RequestMapping(value = "eliminarUsuarios.htm", method = RequestMethod.POST)
    public void eliminarUsuarios(HttpServletRequest req, HttpServletResponse res) throws IOException, NonexistentEntityException, sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException {
        String idUsuario = req.getParameterValues("idUsuario")[0];
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        
        this.usersModelo.destroy(idUsuario);
        Map map = new HashMap();
        map.put("success", true);
        map.put("msg", "Usuario eliminado satisfactoriamente.");

      res.getWriter().print(jsonObj.toJson(map));
    }

    @RequestMapping(value = "modificarUsuario.htm", method = RequestMethod.POST)
    public void modificarUsuario(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        String usser = req.getParameterValues("Usuario")[0];
        String passw = req.getParameterValues("Contrasenna")[0];
        String idRol = req.getParameterValues("roll")[0];
        String idUsuario = req.getParameterValues("idUsuario")[0];
        String roll = "";
        if (idRol == "1") {
            roll = "Administrador";
        } 
        else if (idRol == "2") {
            roll = "Jurado";
        } 
        else {
            roll = "Cliente";
        }

        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       

        Users user = new Users(usser, passw);

        Map map = new HashMap();
        try {
            this.usersModelo.edit(user);
            map.put("success", true);
            map.put("msg", "Usuario modificado satisfactoriamente");
            res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "Error al tratar de modificar el usuario " + usser + ".");
            res.getWriter().print(jsonObj.toJson(map));
        }
    }
}
