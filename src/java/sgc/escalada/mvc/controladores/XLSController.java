/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgc.escalada.mvc.controladores;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sgc.escalada.mvc.entidades.DatCompetencia;
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.modelos.CategoriaModel;
import sgc.escalada.mvc.modelos.CompetenciaModel;

/**
 *
 * @author yosbel
 */
@Controller
public class XLSController {

    CompetenciaModel competenciaModel = new CompetenciaModel(null);
    CategoriaModel categoriaModel = new CategoriaModel(null);

    @RequestMapping(value = "exportarXls", method = RequestMethod.GET)
    public void exportarXls(HttpServletRequest request, HttpServletResponse res) throws IOException, ParseException {
        int idCompetencia = Integer.parseInt(request.getParameterValues("idCompetencia")[0]);
        int idCategoria = Integer.parseInt(request.getParameterValues("idCategoria")[0]);
        int idFase;
        try {
            idFase = Integer.parseInt(request.getParameterValues("idFase")[0]);
        } catch (Exception e) {
            idFase = 10;
        }

        String data = request.getParameterValues("data")[0];

        String nombreFichero = "FD" + new Date().toString().replace(" ", "").replace(":", "") + ".xls";
        String ubicacionArchivo = request.getServletContext().getRealPath("/") + "img\\" + nombreFichero;
        Map map = new HashMap();
        Gson jsonObj = new Gson();
        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");
       
        DatCompetencia competencia = competenciaModel.findDatCompetencia(idCompetencia);
        NomCategoria categoria = categoriaModel.findNomCategoria(idCategoria);
        try {
            switch (idFase) {
                case 1: {
                    xlsFaseDificultad(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 2: {
                    xlsFaseDificultadGeneral(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 3: {
                    xlsFaseVelocidadClasificatoria(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 4: {
                    xlsFaseVelocidadFinal(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 5: {
                    xlsFaseVelocidadGeneral(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 6: {
                    xlsFaseBloqueClasificatoria(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 7: {
                    xlsFaseBloqueSemiFinal(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 8: {
                    xlsFaseBloqueFinal(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 9: {
                    xlsFaseBloqueGeneral(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 10: {
                    xlsAllRound(ubicacionArchivo, data, competencia, categoria);
                    break;
                }

                default:
                    break;
            }
            map.put("success", true);
            map.put("src", nombreFichero);
            res.getWriter().print(jsonObj.toJson(map));
        } catch (FileNotFoundException ex) {
            map.put("success", false);
            map.put("msg", ex.getMessage());
             res.getWriter().print(jsonObj.toJson(map));
        }

    }

    private void xlsFaseDificultad(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, ParseException, IOException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("Fase Dificultad");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("Fase Dificultad");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());

        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("Presa");
        celda_actual = fila_actual.createCell(3);
        celda_actual.setCellValue("Agarre");
        celda_actual = fila_actual.createCell(4);
        celda_actual.setCellValue("Puntos");
        celda_actual = fila_actual.createCell(5);
        celda_actual.setCellValue("Ranking");

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 4);
            JSONObject objeto = (JSONObject) array.get(i);
            String presa = objeto.get("presa").toString();
            String agarre = objeto.get("agarre").toString();
            String ranking = objeto.get("ranking").toString();
            String nombre = objeto.get("nombre").toString();
            String puntos = objeto.get("puntos").toString();
            String provincia = objeto.get("provincia").toString();

            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);

            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);

            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(presa);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(3);
            celda_actual.setCellValue(agarre);
            hoja_actual.autoSizeColumn(i);

            celda_actual = fila_actual.createCell(4);
            celda_actual.setCellValue(puntos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(5);
            celda_actual.setCellValue(ranking);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

    private void xlsFaseDificultadGeneral(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, ParseException, IOException {
        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("Fase Dificultad General");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("Fase Dificultad General");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());
        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("Ranking");
        celda_actual = fila_actual.createCell(3);
        celda_actual.setCellValue("Puntos");
        celda_actual = fila_actual.createCell(4);
        celda_actual.setCellValue("Medalla");

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 3);
            JSONObject objeto = (JSONObject) array.get(i);
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String ranking = objeto.get("ranking").toString();
            String puntos = objeto.get("puntos").toString();
            String medalla = "";
            int r = Integer.parseInt(ranking);
            if (r == 1) {
                medalla = "Oro";
            } else if (r == 2) {
                medalla = "Plata";
            } else if (r == 3) {
                medalla = "Bronce";
            }

            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(ranking);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(3);
            celda_actual.setCellValue(puntos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(4);
            celda_actual.setCellValue(medalla);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

    private void xlsFaseVelocidadClasificatoria(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, ParseException, IOException {
        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("Fase Velocidad Clasificatoria");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("Fase Velocidad Clasificatoria");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());
        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Ruta 1");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Ruta 2");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(3);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(4);
        celda_actual.setCellValue("Tiempo 1");
        celda_actual = fila_actual.createCell(5);
        celda_actual.setCellValue("Tiempo 2");
        celda_actual = fila_actual.createCell(6);
        celda_actual.setCellValue("Mejor T.");
        celda_actual = fila_actual.createCell(7);
        celda_actual.setCellValue("Ranking");

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 3);
            JSONObject objeto = (JSONObject) array.get(i);
            String ruta1 = objeto.get("ruta1").toString();
            String ruta2 = objeto.get("ruta2").toString();
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String tiempo1 = objeto.get("tiempo1").toString();
            String tiempo2 = objeto.get("tiempo2").toString();
            String mejorTiempo = objeto.get("mejorTiempo").toString();
            String ranking = objeto.get("ranking").toString();

            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(ruta1);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(ruta2);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(3);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(4);
            celda_actual.setCellValue(tiempo1);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(5);
            celda_actual.setCellValue(tiempo2);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(6);
            celda_actual.setCellValue(mejorTiempo);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(7);
            celda_actual.setCellValue(ranking);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

    private void xlsFaseVelocidadFinal(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, ParseException, IOException {
        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("Fase Velocidad Final");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("Fase Velocidad Final");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());
        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("Tiempo");

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 3);
            JSONObject objeto = (JSONObject) array.get(i);
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String tiempoFinal = objeto.get("tiempoFinal").toString();

            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(tiempoFinal);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

    private void xlsFaseVelocidadGeneral(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, ParseException, IOException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("Fase Velocidad General");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("Fase Velocidad General");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());
        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Ruta 1");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Ruta 2");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(3);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(4);
        celda_actual.setCellValue("Ranking");
        celda_actual = fila_actual.createCell(5);
        celda_actual.setCellValue("Puntos");
        celda_actual = fila_actual.createCell(6);
        celda_actual.setCellValue("Medalla");

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 3);
            JSONObject objeto = (JSONObject) array.get(i);
            String ruta1 = objeto.get("ruta1").toString();
            String ruta2 = objeto.get("ruta2").toString();
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String ranking = objeto.get("ranking").toString();
            String puntos = objeto.get("punto").toString();
            String medalla = "";
            int r = Integer.parseInt(ranking);
            if (r == 1) {
                medalla = "Oro";
            } else if (r == 2) {
                medalla = "Plata";
            } else if (r == 3) {
                medalla = "Bronce";
            }

            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(ruta1);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(ruta2);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(3);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(4);
            celda_actual.setCellValue(ranking);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(5);
            celda_actual.setCellValue(puntos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(6);
            celda_actual.setCellValue(medalla);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

    private void xlsFaseBloqueClasificatoria(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException,  ParseException,   IOException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("Fase Bloque Clasificatoria");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("Fase Bloque Clasificatoria");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());
        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("T1");
        celda_actual = fila_actual.createCell(3);
        celda_actual.setCellValue("B1");
        celda_actual = fila_actual.createCell(4);
        celda_actual.setCellValue("T2");
        celda_actual = fila_actual.createCell(5);
        celda_actual.setCellValue("B2");
        celda_actual = fila_actual.createCell(6);
        celda_actual.setCellValue("T3");
        celda_actual = fila_actual.createCell(7);
        celda_actual.setCellValue("B3");
        celda_actual = fila_actual.createCell(8);
        celda_actual.setCellValue("T4");
        celda_actual = fila_actual.createCell(9);
        celda_actual.setCellValue("B4");
        celda_actual = fila_actual.createCell(10);
        celda_actual.setCellValue("T5");
        celda_actual = fila_actual.createCell(11);
        celda_actual.setCellValue("B5");
        celda_actual = fila_actual.createCell(12);
        celda_actual.setCellValue("T.TOP");
        celda_actual = fila_actual.createCell(13);
        celda_actual.setCellValue("I.TOP");
        celda_actual = fila_actual.createCell(14);
        celda_actual.setCellValue("T.Bonos");
        celda_actual = fila_actual.createCell(15);
        celda_actual.setCellValue("I.Bonos");
        celda_actual = fila_actual.createCell(16);
        celda_actual.setCellValue("Ranking");

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 3);
            JSONObject objeto = (JSONObject) array.get(i);
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String t1 = objeto.get("t1").toString();
            String t2 = objeto.get("t2").toString();
            String t3 = objeto.get("t3").toString();
            String t4 = objeto.get("t4").toString();
            String t5 = objeto.get("t5").toString();
            String b1 = objeto.get("b1").toString();
            String b2 = objeto.get("b2").toString();
            String b3 = objeto.get("b3").toString();
            String b4 = objeto.get("b4").toString();
            String b5 = objeto.get("b5").toString();
            String totalTop = objeto.get("totalTop").toString();
            String intentosTop = objeto.get("intentosTop").toString();
            String intentosBonos = objeto.get("intentosBonos").toString();
            String totalBonos = objeto.get("totalBonos").toString();
            String ranking = objeto.get("ranking").toString();

            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(t1);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(3);
            celda_actual.setCellValue(b1);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(4);
            celda_actual.setCellValue(t2);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(5);
            celda_actual.setCellValue(b2);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(6);
            celda_actual.setCellValue(t3);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(7);
            celda_actual.setCellValue(b3);
            hoja_actual.autoSizeColumn(i);

            celda_actual = fila_actual.createCell(8);
            celda_actual.setCellValue(t4);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(9);
            celda_actual.setCellValue(b4);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(10);
            celda_actual.setCellValue(t5);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(11);
            celda_actual.setCellValue(b5);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(12);
            celda_actual.setCellValue(totalTop);
            hoja_actual.autoSizeColumn(i);

            celda_actual = fila_actual.createCell(13);
            celda_actual.setCellValue(intentosTop);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(14);
            celda_actual.setCellValue(totalBonos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(15);
            celda_actual.setCellValue(intentosBonos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(16);
            celda_actual.setCellValue(ranking);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

    private void xlsFaseBloqueSemiFinal(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, ParseException, IOException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("Fase Bloque Semifinal");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("Fase Bloque Semifinal");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());
        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("T1");
        celda_actual = fila_actual.createCell(3);
        celda_actual.setCellValue("B1");
        celda_actual = fila_actual.createCell(4);
        celda_actual.setCellValue("T2");
        celda_actual = fila_actual.createCell(5);
        celda_actual.setCellValue("B2");
        celda_actual = fila_actual.createCell(6);
        celda_actual.setCellValue("T3");
        celda_actual = fila_actual.createCell(7);
        celda_actual.setCellValue("B3");
        celda_actual = fila_actual.createCell(8);
        celda_actual.setCellValue("T4");
        celda_actual = fila_actual.createCell(9);
        celda_actual.setCellValue("B4");
        celda_actual = fila_actual.createCell(10);
        celda_actual.setCellValue("T.TOP");
        celda_actual = fila_actual.createCell(11);
        celda_actual.setCellValue("I.TOP");
        celda_actual = fila_actual.createCell(12);
        celda_actual.setCellValue("T.Bonos");
        celda_actual = fila_actual.createCell(13);
        celda_actual.setCellValue("I.Bonos");
        celda_actual = fila_actual.createCell(14);
        celda_actual.setCellValue("Ranking");

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 3);
            JSONObject objeto = (JSONObject) array.get(i);
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String t1 = objeto.get("t1").toString();
            String t2 = objeto.get("t2").toString();
            String t3 = objeto.get("t3").toString();
            String t4 = objeto.get("t4").toString();
            String b1 = objeto.get("b1").toString();
            String b2 = objeto.get("b2").toString();
            String b3 = objeto.get("b3").toString();
            String b4 = objeto.get("b4").toString();
            String totalTop = objeto.get("totalTop").toString();
            String intentosTop = objeto.get("intentosTop").toString();
            String intentosBonos = objeto.get("intentosBonos").toString();
            String totalBonos = objeto.get("totalBonos").toString();
            String ranking = objeto.get("ranking").toString();

            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(t1);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(3);
            celda_actual.setCellValue(b1);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(4);
            celda_actual.setCellValue(t2);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(5);
            celda_actual.setCellValue(b2);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(6);
            celda_actual.setCellValue(t3);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(7);
            celda_actual.setCellValue(b3);
            hoja_actual.autoSizeColumn(i);

            celda_actual = fila_actual.createCell(8);
            celda_actual.setCellValue(t4);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(9);
            celda_actual.setCellValue(b4);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(10);
            celda_actual.setCellValue(totalTop);
            hoja_actual.autoSizeColumn(i);

            celda_actual = fila_actual.createCell(11);
            celda_actual.setCellValue(intentosTop);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(12);
            celda_actual.setCellValue(totalBonos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(13);
            celda_actual.setCellValue(intentosBonos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(14);
            celda_actual.setCellValue(ranking);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

    private void xlsFaseBloqueFinal(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, ParseException, IOException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("Fase Bloque Semifinal");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("Fase Bloque Semifinal");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());
        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("T1");
        celda_actual = fila_actual.createCell(3);
        celda_actual.setCellValue("B1");
        celda_actual = fila_actual.createCell(4);
        celda_actual.setCellValue("T2");
        celda_actual = fila_actual.createCell(5);
        celda_actual.setCellValue("B2");
        celda_actual = fila_actual.createCell(6);
        celda_actual.setCellValue("T3");
        celda_actual = fila_actual.createCell(7);
        celda_actual.setCellValue("B3");
        celda_actual = fila_actual.createCell(8);
        celda_actual.setCellValue("T4");
        celda_actual = fila_actual.createCell(9);
        celda_actual.setCellValue("B4");
        celda_actual = fila_actual.createCell(10);
        celda_actual.setCellValue("T.TOP");
        celda_actual = fila_actual.createCell(11);
        celda_actual.setCellValue("I.TOP");
        celda_actual = fila_actual.createCell(12);
        celda_actual.setCellValue("T.Bonos");
        celda_actual = fila_actual.createCell(13);
        celda_actual.setCellValue("I.Bonos");
        celda_actual = fila_actual.createCell(14);
        celda_actual.setCellValue("Ranking");

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 3);
            JSONObject objeto = (JSONObject) array.get(i);
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String t1 = objeto.get("t1").toString();
            String t2 = objeto.get("t2").toString();
            String t3 = objeto.get("t3").toString();
            String t4 = objeto.get("t4").toString();
            String b1 = objeto.get("b1").toString();
            String b2 = objeto.get("b2").toString();
            String b3 = objeto.get("b3").toString();
            String b4 = objeto.get("b4").toString();
            String totalTop = objeto.get("totalTop").toString();
            String intentosTop = objeto.get("intentosTop").toString();
            String intentosBonos = objeto.get("intentosBonos").toString();
            String totalBonos = objeto.get("totalBonos").toString();
            String ranking = objeto.get("ranking").toString();
            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(t1);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(3);
            celda_actual.setCellValue(b1);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(4);
            celda_actual.setCellValue(t2);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(5);
            celda_actual.setCellValue(b2);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(6);
            celda_actual.setCellValue(t3);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(7);
            celda_actual.setCellValue(b3);
            hoja_actual.autoSizeColumn(i);

            celda_actual = fila_actual.createCell(8);
            celda_actual.setCellValue(t4);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(9);
            celda_actual.setCellValue(b4);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(10);
            celda_actual.setCellValue(totalTop);
            hoja_actual.autoSizeColumn(i);

            celda_actual = fila_actual.createCell(11);
            celda_actual.setCellValue(intentosTop);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(12);
            celda_actual.setCellValue(totalBonos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(13);
            celda_actual.setCellValue(intentosBonos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(14);
            celda_actual.setCellValue(ranking);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

    private void xlsFaseBloqueGeneral(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, ParseException, IOException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("Fase Bloque General");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("Fase Bloque General");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());
        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("Ranking");
        celda_actual = fila_actual.createCell(3);
        celda_actual.setCellValue("Puntos");
        celda_actual = fila_actual.createCell(4);
        celda_actual.setCellValue("Medalla");

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 3);
            JSONObject objeto = (JSONObject) array.get(i);
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String ranking = objeto.get("ranking").toString();
            String puntos = objeto.get("punto").toString();
            String medalla = "";
            int r = Integer.parseInt(ranking);
            if (r == 1) {
                medalla = "Oro";
            } else if (r == 2) {
                medalla = "Plata";
            } else if (r == 3) {
                medalla = "Bronce";
            }

            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(ranking);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(3);
            celda_actual.setCellValue(puntos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(4);
            celda_actual.setCellValue(medalla);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

    private void xlsAllRound(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, ParseException, IOException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        HSSFWorkbook libroActual = new HSSFWorkbook();
        HSSFSheet hoja_actual = libroActual.createSheet("All Round");
        HSSFRow formato1 = hoja_actual.createRow((short) 0);
        HSSFCell fase = formato1.createCell(0);
        fase.setCellValue("All Round");

        formato1 = hoja_actual.createRow((short) 1);
        HSSFCell celda = formato1.createCell(0);
        celda.setCellValue("Competencia: " + competencia.getNombre());
        celda = formato1.createCell(1);
        celda.setCellValue("Categoría: " + categoria.getCategoria());
        HSSFRow fila_actual = hoja_actual.createRow((short) 3);
        fila_actual.createCell(0);
        HSSFCell celda_actual = fila_actual.createCell(0);
        celda_actual.setCellValue("Nombre");
        celda_actual = fila_actual.createCell(1);
        celda_actual.setCellValue("Provincia");
        celda_actual = fila_actual.createCell(2);
        celda_actual.setCellValue("R.V");
        celda_actual = fila_actual.createCell(3);
        celda_actual.setCellValue("P.V");
        celda_actual = fila_actual.createCell(4);
        celda_actual.setCellValue("R.D");
        celda_actual = fila_actual.createCell(5);
        celda_actual.setCellValue("P.D");
        celda_actual = fila_actual.createCell(6);
        celda_actual.setCellValue("R.B");
        celda_actual = fila_actual.createCell(7);
        celda_actual.setCellValue("P.B");
        celda_actual = fila_actual.createCell(8);
        celda_actual.setCellValue("T.P");        
        celda_actual = fila_actual.createCell(9);
        celda_actual.setCellValue("R.All");
        celda_actual = fila_actual.createCell(10);
        celda_actual.setCellValue("Puntos");
        celda_actual = fila_actual.createCell(11);
        celda_actual.setCellValue("Medalla");
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            fila_actual = hoja_actual.createRow((short) i + 3);
            JSONObject objeto = (JSONObject) array.get(i);
             String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();            
            String rankingV = objeto.get("rankingV").toString();
            String puntosV = objeto.get("puntosV").toString();
            String rankingD = objeto.get("rankingD").toString();
            String puntosD = objeto.get("puntosD").toString();            
            String rankingB = objeto.get("rankingB").toString();
            String puntosB = objeto.get("puntosB").toString();
            String totalPuntos = objeto.get("totalPuntos").toString();
            String ranking = objeto.get("ranking").toString();
            String puntos = objeto.get("punto").toString();          
            String medalla = "";
            int r = Integer.parseInt(ranking);
            if (r == 1) {
                medalla = "Oro";
            } else if (r == 2) {
                medalla = "Plata";
            } else if (r == 3) {
                medalla = "Bronce";
            }


            celda_actual = fila_actual.createCell(0);
            celda_actual.setCellValue(nombre);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(1);
            celda_actual.setCellValue(provincia);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(2);
            celda_actual.setCellValue(rankingV);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(3);
            celda_actual.setCellValue(puntosV);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(4);
            celda_actual.setCellValue(rankingD);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(5);
            celda_actual.setCellValue(puntosD);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(6);
            celda_actual.setCellValue(rankingB);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(7);
            celda_actual.setCellValue(puntosB);
            hoja_actual.autoSizeColumn(i);            
            celda_actual = fila_actual.createCell(8);
            celda_actual.setCellValue(totalPuntos);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(9);
            celda_actual.setCellValue(ranking);
            hoja_actual.autoSizeColumn(i);
            celda_actual = fila_actual.createCell(10);
            celda_actual.setCellValue(puntos);
            hoja_actual.autoSizeColumn(i);
             celda_actual = fila_actual.createCell(11);
            celda_actual.setCellValue(medalla);
            hoja_actual.autoSizeColumn(i);

        }
        HSSFRow formato5 = hoja_actual.createRow((short) array.size() + 5);
        HSSFCell celdaFuente = formato5.createCell(0);
        celdaFuente.setCellValue("Fecha: " + new Date().toString());
        libroActual.write(archivo);
        archivo.close();

    }

}
