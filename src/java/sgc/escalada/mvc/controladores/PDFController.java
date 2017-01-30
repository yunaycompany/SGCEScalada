/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgc.escalada.mvc.controladores;

import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
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
import sgc.escalada.mvc.entidades.NomCategoria;
import sgc.escalada.mvc.modelos.CategoriaModel;
import sgc.escalada.mvc.modelos.CompetenciaModel;

/**
 *
 * @author rolando
 */
@Controller
public class PDFController {

    CompetenciaModel competenciaModel = new CompetenciaModel(null);
    CategoriaModel categoriaModel = new CategoriaModel(null);
    /*
     {idFase: 1, fase: 'Fase dificultad'},
     {idFase: 2, fase: 'Fase dificultad general'},
     {idFase: 3, fase: 'Fase velocidad clasificatoria'},
     {idFase: 4, fase: 'Fase velocidad final'},
     {idFase: 5, fase: 'Fase velocidad general'},
     {idFase: 6, fase: 'Fase bloque clasificatoria'},
     {idFase: 7, fase: 'Fase bloque semifinal'},
     {idFase: 8, fase: 'Fase bloque final'},
     {idFase: 9, fase: 'Fase bloque general'},
     {idFase: 'all', fase: 'Fase ALLROUND'}
     */

    @RequestMapping(value = "exportarPdf", method = RequestMethod.GET)
    public void exportarPdf(HttpServletRequest request, HttpServletResponse res) throws IOException, ParseException {
        int idCompetencia = Integer.parseInt(request.getParameterValues("idCompetencia")[0]);
        int idCategoria = Integer.parseInt(request.getParameterValues("idCategoria")[0]);
        int idFase;
        try {
            idFase = Integer.parseInt(request.getParameterValues("idFase")[0]);
        } catch (Exception e) {
            idFase = 10;
        }
        
        String data = request.getParameterValues("data")[0];

        String nombreFichero = "FD" + new Date().toString().replace(" ", "").replace(":", "") + ".pdf";
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
                    pdfFaseDificultad(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 2: {
                    pdfFaseDificultadGeneral(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 3: {
                    pdfFaseVelocidadClasificatoria(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 4: {
                    pdfFaseVelocidadFinal(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 5: {
                    pdfFaseVelocidadGeneral(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 6: {
                    pdfFaseBloqueClasificatoria(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 7: {
                    pdfFaseBloqueSemiFinal(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 8: {
                    pdfFaseBloqueFinal(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 9: {
                    pdfFaseBloqueGeneral(ubicacionArchivo, data, competencia, categoria);
                    break;
                }
                case 10: {
                    pdfAllRound(ubicacionArchivo, data, competencia, categoria);
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
        } catch (DocumentException ex) {
            map.put("success", false);
            map.put("msg", ex.getMessage());
             res.getWriter().print(jsonObj.toJson(map));
        }

    }

    private void pdfFaseDificultad(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 6;
        PdfPTable table = new PdfPTable(cantColumnas);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("Fase Dificultad"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("Presa"));
        table.addCell(new Paragraph("Agarre"));
        table.addCell(new Paragraph("Puntos"));
        table.addCell(new Paragraph("Ranking"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            JSONObject objeto = (JSONObject) array.get(i);
            String presa = objeto.get("presa").toString();
            String agarre = objeto.get("agarre").toString();
            String pApellido = objeto.get("pApellido").toString();
            String sApellido = objeto.get("sApellido").toString();
            String ranking = objeto.get("ranking").toString();
            String nombre = objeto.get("nombre").toString();
            String puntos = objeto.get("puntos").toString();
            String provincia = objeto.get("provincia").toString();

            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(presa);
            table.addCell(agarre);
            table.addCell(puntos);
            table.addCell(ranking);
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }

    private void pdfFaseDificultadGeneral(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 5;
        PdfPTable table = new PdfPTable(cantColumnas);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("Fase Dificultad General"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("Ranking"));
        table.addCell(new Paragraph("Puntos"));
        table.addCell(new Paragraph("Medalla"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
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
            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(ranking);
            table.addCell(puntos);
            table.addCell(medalla);
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }
    
    private void pdfFaseVelocidadClasificatoria(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 8;
        PdfPTable table = new PdfPTable(cantColumnas);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("Fase Velocidad Clasificatoria"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        table.addCell(new Paragraph("Ruta 1"));
        table.addCell(new Paragraph("Ruta 2"));
        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("Tiempo 1"));
        table.addCell(new Paragraph("Tiempo 2"));
        table.addCell(new Paragraph("Mejor T."));
        table.addCell(new Paragraph("Ranking"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            JSONObject objeto = (JSONObject) array.get(i);
            String ruta1 = objeto.get("ruta1").toString();
            String ruta2 = objeto.get("ruta2").toString();
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String tiempo1 = objeto.get("tiempo1").toString();
            String tiempo2 = objeto.get("tiempo2").toString();
            String mejorTiempo = objeto.get("mejorTiempo").toString();
            String ranking = objeto.get("ranking").toString();

            table.addCell(ruta1);
            table.addCell(ruta2);
            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(tiempo1);
            table.addCell(tiempo2);
            table.addCell(mejorTiempo);
            table.addCell(ranking);
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }
    
    private void pdfFaseVelocidadFinal(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 3;
        PdfPTable table = new PdfPTable(cantColumnas);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("Fase Velocidad Final"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        
        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("Tiempo"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
            JSONObject objeto = (JSONObject) array.get(i);
            
            String nombre = objeto.get("nombre").toString();
            String provincia = objeto.get("provincia").toString();
            String tiempoFinal = objeto.get("tiempoFinal").toString();
           
            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(tiempoFinal);
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }

    private void pdfFaseVelocidadGeneral(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 7;
        PdfPTable table = new PdfPTable(cantColumnas);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("Fase Velocidad General"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        table.addCell(new Paragraph("Ruta 1"));
        table.addCell(new Paragraph("Ruta 2"));
        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("Ranking"));
        table.addCell(new Paragraph("Puntos"));
        table.addCell(new Paragraph("Medalla"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
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
            
            table.addCell(ruta1);
            table.addCell(ruta2);
            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(ranking);
            table.addCell(puntos);
            table.addCell(medalla);
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }
    
    private void pdfFaseBloqueClasificatoria(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 17;
        PdfPTable table = new PdfPTable(cantColumnas);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("Fase Bloque Clasificatoria"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        
        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("T1"));
        table.addCell(new Paragraph("B1"));
        table.addCell(new Paragraph("T2"));
        table.addCell(new Paragraph("B2"));
        table.addCell(new Paragraph("T3"));
        table.addCell(new Paragraph("B3"));
        table.addCell(new Paragraph("T4"));
        table.addCell(new Paragraph("B4"));
        table.addCell(new Paragraph("T5"));
        table.addCell(new Paragraph("B5"));
        table.addCell(new Paragraph("T.TOP"));
        table.addCell(new Paragraph("I.TOP"));
        table.addCell(new Paragraph("T.Bonos"));
        table.addCell(new Paragraph("I.Bonos"));
        table.addCell(new Paragraph("Ranking"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
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

            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(t1);
            table.addCell(t2);
            table.addCell(t3);
            table.addCell(t4);
            table.addCell(t5);
            table.addCell(b1);
            table.addCell(b2);
            table.addCell(b3);
            table.addCell(b4);
            table.addCell(b5);
            table.addCell(totalTop);
            table.addCell(intentosTop);
            table.addCell(totalBonos);
            table.addCell(intentosBonos);
            table.addCell(ranking);
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }
    
    private void pdfFaseBloqueSemiFinal(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 15;
        PdfPTable table = new PdfPTable(cantColumnas);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("Fase Bloque Semifinal"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        
        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("T1"));
        table.addCell(new Paragraph("B1"));
        table.addCell(new Paragraph("T2"));
        table.addCell(new Paragraph("B2"));
        table.addCell(new Paragraph("T3"));
        table.addCell(new Paragraph("B3"));
        table.addCell(new Paragraph("T4"));
        table.addCell(new Paragraph("B4"));
        table.addCell(new Paragraph("T.TOP"));
        table.addCell(new Paragraph("I.TOP"));
        table.addCell(new Paragraph("T.Bonos"));
        table.addCell(new Paragraph("I.Bonos"));
        table.addCell(new Paragraph("Ranking"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
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

            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(t1);
            table.addCell(t2);
            table.addCell(t3);
            table.addCell(t4);
            table.addCell(b1);
            table.addCell(b2);
            table.addCell(b3);
            table.addCell(b4);
            table.addCell(totalTop);
            table.addCell(intentosTop);
            table.addCell(totalBonos);
            table.addCell(intentosBonos);
            table.addCell(ranking);
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }
    
    private void pdfFaseBloqueFinal(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 15;
        PdfPTable table = new PdfPTable(cantColumnas);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("Fase Bloque Final"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        
        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("T1"));
        table.addCell(new Paragraph("B1"));
        table.addCell(new Paragraph("T2"));
        table.addCell(new Paragraph("B2"));
        table.addCell(new Paragraph("T3"));
        table.addCell(new Paragraph("B3"));
        table.addCell(new Paragraph("T4"));
        table.addCell(new Paragraph("B4"));
        table.addCell(new Paragraph("T.TOP"));
        table.addCell(new Paragraph("I.TOP"));
        table.addCell(new Paragraph("T.Bonos"));
        table.addCell(new Paragraph("I.Bonos"));
        table.addCell(new Paragraph("Ranking"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
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

            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(t1);
            table.addCell(t2);
            table.addCell(t3);
            table.addCell(t4);
            table.addCell(b1);
            table.addCell(b2);
            table.addCell(b3);
            table.addCell(b4);
            table.addCell(totalTop);
            table.addCell(intentosTop);
            table.addCell(totalBonos);
            table.addCell(intentosBonos);
            table.addCell(ranking);
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }
    
    private void pdfFaseBloqueGeneral(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 5;
        PdfPTable table = new PdfPTable(cantColumnas);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("Fase Bloque General"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("Ranking"));
        table.addCell(new Paragraph("Puntos"));
        table.addCell(new Paragraph("Medalla"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
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
            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(ranking);
            table.addCell(puntos);
            table.addCell(medalla);
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }
    
    private void pdfAllRound(String ubicacionArchivo, String data, DatCompetencia competencia, NomCategoria categoria) throws FileNotFoundException, DocumentException, ParseException {

        FileOutputStream archivo;
        archivo = new FileOutputStream(ubicacionArchivo);
        Document documento = new Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) documento, archivo);
        documento.open();
        int cantColumnas = 12;
        PdfPTable table = new PdfPTable(cantColumnas);
        table.setSplitRows(true);
        PdfPCell celdaInicial = new PdfPCell(new Paragraph("All Round"));
        celdaInicial.setColspan(cantColumnas);
        table.addCell(celdaInicial);

        PdfPCell celdaComp = new PdfPCell(new Paragraph("Competencia: " + competencia.getNombre()));
        celdaComp.setColspan(cantColumnas);
        table.addCell(celdaComp);

        PdfPCell celdaCateg = new PdfPCell(new Paragraph("Categoría: " + categoria.getCategoria()));
        celdaCateg.setColspan(cantColumnas);
        table.addCell(celdaCateg);

        
        table.addCell(new Paragraph("Nombre"));
        table.addCell(new Paragraph("Provincia"));
        table.addCell(new Paragraph("R.V"));
        table.addCell(new Paragraph("P.V"));
        table.addCell(new Paragraph("R.D"));
        table.addCell(new Paragraph("P.D"));
        table.addCell(new Paragraph("R.B"));
        table.addCell(new Paragraph("P.B"));
        table.addCell(new Paragraph("T.P"));
        table.addCell(new Paragraph("R.All"));
        table.addCell(new Paragraph("Puntos"));
        table.addCell(new Paragraph("Medalla"));

        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(data);
        for (int i = 0; i < array.size(); i++) {
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

            table.addCell(nombre);
            table.addCell(provincia);
            table.addCell(rankingV);
            table.addCell(puntosV);
            table.addCell(rankingD);
            table.addCell(puntosD);
            table.addCell(rankingB);
            table.addCell(puntosB);
            table.addCell(totalPuntos);
            table.addCell(ranking);
            table.addCell(puntos);
            table.addCell(medalla);
            
        }

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Fecha: " + new Date().toString()));
        // Indicamos cuantas columnas ocupa la celda
        celdaFinal.setColspan(cantColumnas);
        table.addCell(celdaFinal);
        documento.add(table);
        documento.close();

    }
    
}
