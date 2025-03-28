package agencia.agencia.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Service para geração de gráficos em PDF
 * da consulta 4
 */

@Service
public class ServicePDF {

    @Autowired
    ServiceGeral serviceGeral = new ServiceGeral();

    public void gerarGraficos() {
        String[] meses = {
                "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        List<Object[]> resultados = serviceGeral.realizarConsulta4();

        for (int mes = 1; mes <= 12; mes++) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            for (Object[] row : resultados) {
                int ano = (int) row[0];
                int mesTransacao = (int) row[1];
                int totalTransacoes = (int) row[2];

                if (mesTransacao == mes)
                    dataset.addValue(totalTransacoes, meses[mes - 1] + "/" + ano, String.valueOf(ano));
            }

            JFreeChart chart = ChartFactory.createBarChart(
                    "Transações em " + meses[mes - 1], // Pegando o nome do mês
                    "Ano",
                    "Quantidade",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false);

            NumberAxis yAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
            yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

            try {
                String saveDir = "agencia/src/main/resources/imgs/graficos/";

                File file = new File(saveDir + "grafico_mes_" + mes + ".png");
                file.getParentFile().mkdirs(); // Criar diretório se não existir
                ImageIO.write(chart.createBufferedImage(800, 600), "png", file);
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
