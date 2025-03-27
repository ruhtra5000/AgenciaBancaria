package agencia.agencia.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.AreaBreakType;

import agencia.agencia.service.ServicePDF;

/*
 * Controlador para geração de PDF
 * da consulta 4
 */

@Controller
@RequestMapping("/")
public class ControllerPDF {

    @Autowired
    ServicePDF servicePDF = new ServicePDF();

    @GetMapping("/consulta4/pdf")
    public ResponseEntity<byte[]> gerarPdf() throws IOException {

        // Gera os gráficos antes de criar o PDF
        servicePDF.gerarGraficos();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.setMargins(20, 20, 20, 20);

        // Adiciona os gráficos ao PDF
        for (int mes = 1; mes <= 12; mes++) {

            String saveDir = "agencia/src/main/resources/imgs/graficos/";
            File file = new File(saveDir + "grafico_mes_" + mes + ".png");

            if (file.exists()) {
                Image img = new Image(ImageDataFactory.create(file.getAbsolutePath()));
                document.add(img);
                
                if(mes != 12)
                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

            }
        }

        document.close();

        byte[] pdfBytes = out.toByteArray();

        // Definir headers corretamente
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio_transacoes.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
