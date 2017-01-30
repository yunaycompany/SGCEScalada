package sgc.escalada.mvc.controladores;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sgc.escalada.mvc.entidades.DatGaleria;
import sgc.escalada.mvc.entidades.NomTipoMedia;
import sgc.escalada.mvc.modelos.GaleriaModel;

/**
 *
 * @author Ariam
 */
@Controller
public class SubirFicheroController {

    GaleriaModel galeriaModel = new GaleriaModel(null);

    @RequestMapping(value = "subirImagen.htm", method = RequestMethod.POST)
    public void subirFichero(HttpServletRequest request, HttpServletResponse res) throws IOException, FileUploadException, Exception {
        String ubicacionArchivo = request.getServletContext().getRealPath("/") + "img";

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024);
        factory.setRepository(new File(ubicacionArchivo));

        ServletFileUpload upload = new ServletFileUpload(factory);

        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");

        try {
            List<org.apache.tomcat.util.http.fileupload.FileItem> partes = upload.parseRequest(request);

            String name = "";
            for (org.apache.tomcat.util.http.fileupload.FileItem item : partes) {
                if (item.getFieldName().equals("file")) {
                    name = item.getName();
                    File file = new File(ubicacionArchivo, name);
                    item.write(file);
                }
            }

            DatGaleria galeria = new DatGaleria();
            galeria.setUrl(name);
            galeria.setIdtipomedia(new NomTipoMedia(1));

            galeriaModel.create(galeria);

            Map map = new HashMap();
            map.put("success", true);
            map.put("idmedia", galeria.getIdmedia());
            map.put("msg", "El archivo se ha subido correctamente.");
            res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception ex) {
            Map map = new HashMap();
            map.put("success", false);
            map.put("msg", "Ocurri&oacute; un error al intentar subir el archivo.");
            map.put("error", ex.getMessage());
            res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "subirVideo", method = RequestMethod.POST)
    public void subirVideo(HttpServletRequest request, HttpServletResponse res) throws IOException, FileUploadException, Exception {
        String ubicacionArchivo = request.getServletContext().getRealPath("/") + "img";

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024);
        factory.setRepository(new File(ubicacionArchivo));

        ServletFileUpload upload = new ServletFileUpload(factory);
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");

        try {
            List<org.apache.tomcat.util.http.fileupload.FileItem> partes = upload.parseRequest(request);

            for (org.apache.tomcat.util.http.fileupload.FileItem item : partes) {
                File file = new File(ubicacionArchivo, item.getName());
                item.write(file);
            }

            DatGaleria galeria = new DatGaleria();

            galeria.setUrl(partes.get(0).getName());
            galeria.setIdtipomedia(new NomTipoMedia(2));

            galeriaModel.create(galeria);

            Map map = new HashMap();
            map.put("success", true);
            map.put("msg", "El archivo se ha subido correctamente.");
            res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception ex) {
            Map map = new HashMap();
            map.put("success", false);
            map.put("msg", "Ocurri&oacute; un error al intentar subir el archivo.");
            map.put("error", ex.getMessage());
            res.getWriter().print(jsonObj.toJson(map));
        }
    }

    @RequestMapping(value = "listarImagenes", method = RequestMethod.GET)
    public void listarImagen(HttpServletRequest request, HttpServletResponse res) throws IOException, ParseException {
        //int idCompetencia = Integer.parseInt(request.getParameterValues("idCompetencia")[0]);
        List<DatGaleria> lista = galeriaModel.findDatGaleriaEntities();
        List<Map> listaMap = new LinkedList<>();

        for (DatGaleria imagen : lista) {
            if (imagen.getIdtipomedia().getIdtipomedia() == 1) {
                Map map = new HashMap();
                map.put("src", imagen.getUrl());
                Date fecha = imagen.getFecha();
                String nueva = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
                map.put("name", imagen.getNombre() + ": " + nueva);
                listaMap.add(map);
            }
        }
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(listaMap));

    }

    @RequestMapping(value = "listarVideos", method = RequestMethod.GET)
    public void listarVideos(HttpServletRequest request, HttpServletResponse res) throws IOException {
        //int idCompetencia = Integer.parseInt(request.getParameterValues("idCompetencia")[0]);
        List<DatGaleria> lista = galeriaModel.findDatGaleriaEntities();
        List<Map> listaMap = new LinkedList<>();

        for (DatGaleria imagen : lista) {
            if (imagen.getIdtipomedia().getIdtipomedia() == 2) {
                Map map = new HashMap();
                map.put("src", imagen.getUrl());

                listaMap.add(map);
            }
        }
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
        res.getWriter().print(jsonObj.toJson(listaMap));

    }

    @RequestMapping(value = "cambiarParamsImg.htm", method = RequestMethod.POST)
    public void cambiarParamsImg(HttpServletRequest req, HttpServletResponse res) throws IOException, sgc.escalada.mvc.modelos.exceptions.NonexistentEntityException, Exception {
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");

        try {
            String nombre = req.getParameterValues("nombre")[0];
            String fech = req.getParameterValues("fecha")[0];

            String[] split = fech.split("-");
            int dia = Integer.parseInt(split[2].substring(0, 2));
            int mes = Integer.parseInt(split[1]);
            int ano = Integer.parseInt(split[0]);
            Calendar calendario1 = Calendar.getInstance();
            calendario1.set(ano, mes, dia);
            Date fecha = calendario1.getTime();
            int idmedia = Integer.parseInt(req.getParameterValues("idmedia")[0]);

            DatGaleria dat = galeriaModel.findDatGaleria(idmedia);
            dat.setFecha(fecha);
            dat.setNombre(nombre);
            galeriaModel.edit(dat);
            Map map = new HashMap();
            map.put("success", true);
            map.put("msg", "El archivo se ha subido correctamente.");

            res.getWriter().print(jsonObj.toJson(map));
        } catch (Exception ex) {
            Map map = new HashMap();
            map.put("success", false);
            map.put("msg", "Ocurri&oacute; un error al intentar subir el archivo.");
            map.put("error", ex.getMessage());
            res.getWriter().print(jsonObj.toJson(map));
        }
    }

}
