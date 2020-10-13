package com.analise.vendas.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.analise.vendas.commons.ErrorDTO;
import com.analise.vendas.commons.ResultDTO;
import com.analise.vendas.exception.ProcessingApplicationException;
import com.analise.vendas.utils.ProcessingApplicationUtils;

public class ProcessService extends FileService {

	private static final Logger log = LoggerFactory.getLogger(ProcessService.class);

	public static ProcessService create() {
		return new ProcessService();
	}

	public void process(List<File> arquivos) {
		if (CollectionUtils.isNotEmpty(arquivos)) {
			arquivos.stream().forEach(arquivo -> {
				processResult(arquivo);
			});
		}
	}
	
	private void processResult(File arquivo) {
		try {
			log.info(new StringBuilder().append("Processando arquivo: ")
										.append(arquivo.getName())
										.toString());
			List<String> lines = Files.readAllLines(arquivo.toPath());
			
			ResultDTO result = ResultService.create().buildResult(lines);
			
			this.createResultFile(result, arquivo);
			
			if (CollectionUtils.isNotEmpty(result.getErrors())) {
				this.createResultFileError(result, arquivo);
			}
			
			log.info(new StringBuilder().append("Processamento do arquivo: ")
										.append(arquivo.getName())
										.append(" concluído")
										.toString());
		} catch (IOException e) {
			throw new ProcessingApplicationException(new StringBuilder().append("Erro na leitura do arquivo: ")
																		.append(e.getMessage())
																		.toString());
		}
	}
	
	private void createResultFile(ResultDTO result, File arquivo) {
		try (BufferedWriter writer = Files.newBufferedWriter(ProcessingApplicationUtils.getResultPathProcess(arquivo))) {
			writer.write(this.getResultMessage(result));
        } catch (IOException e) {
        	throw new ProcessingApplicationException(new StringBuilder().append("Erro ao criar arquivo de saída")
																		.append(e.getMessage())
																		.toString());
		}
	}
	
	private void createResultFileError(ResultDTO result, File arquivo) {
		try (BufferedWriter writer = Files.newBufferedWriter(ProcessingApplicationUtils.getResultPathError(arquivo))) {
            for (ErrorDTO error : result.getErrors()) {
            	writer.write(error.toString());
            	writer.newLine();
			}
        } catch (IOException e) {
        	throw new ProcessingApplicationException(new StringBuilder().append("Erro ao criar arquivo de erro")
																		.append(e.getMessage())
																		.toString());
		}
	}
	
	private String getResultMessage(ResultDTO result) {
		if (result.getMostExpensiveSaleId() == null
				|| result.getQuantityClients() == null
				|| result.getQuantitySalesman() == null
				|| StringUtils.isBlank(result.getWorstSalesman())) {
			return "Não foi possível analisar as vendas, verifique se todos os dados foram informados.";
		}
		return result.toString();
	}
}
