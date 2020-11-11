package br.dh.barbearia.java.commun;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.dh.barbearia.java.entity.Agenda;

public class GeneratorPDF {


    public static String geraPDFagendamentoOK (Agenda dadosCliente){
           // criação do documento
          Document document = new Document();
          PdfPTable table = new PdfPTable(1);
          String filename = "PDF_BarbeariaAgendamento.pdf";
          String arquivoPDF = System.getProperty("user.dir") + "/recibo/" + filename;
          try {
              PdfWriter.getInstance(document, new FileOutputStream(arquivoPDF));
              document.open();

              // adicionando um parágrafo no documento
              document.add(new Paragraph("Agendamento Realizado com Sucesso \n \n \n"));
              PdfPCell dados = new PdfPCell(new Paragraph("DADOS:"));
              PdfPCell nome = new PdfPCell(new Paragraph("Nome do cliente: " + dadosCliente.getNome() ));
              PdfPCell cpf = new PdfPCell(new Paragraph("CPF: " + dadosCliente.getCpf() ));
              PdfPCell data = new PdfPCell(new Paragraph("Data: " + dadosCliente.getDataAgendamento()));
              PdfPCell hora = new PdfPCell(new Paragraph("Hora: " + dadosCliente.getHoraAgendamento()));
              PdfPCell chave = new PdfPCell(new Paragraph("Chave de Cancelamento: " + dadosCliente.getChaveDeCancelamento()));
              PdfPCell notifica = new PdfPCell(new Paragraph("\n \n** A chave de cancelamento serve para cancelar sua marcação, bastando apenas acessar a página AGENDA e selecionar a opção CANCELAMENTO."));
             
              table.addCell(dados);
              table.addCell(nome);
              table.addCell(cpf);
              table.addCell(data);
              table.addCell(hora);
              table.addCell(chave);   
              table.addCell(notifica);  
              
	          document.add(table);
	          document.close();
	          return filename;
          
          }
          catch(DocumentException de) {
              System.err.println(de.getMessage());
          }
          catch(IOException ioe) {
              System.err.println(ioe.getMessage());
          }
          document.close();
          return "";
      }
    

    }