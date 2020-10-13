package com.analise.vendas.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.analise.vendas.exception.ProcessingApplicationException;
import com.analise.vendas.utils.ProcessingApplicationUtils;

public abstract class FileService extends AbstractService {

	private static final Logger log = LoggerFactory.getLogger(FileService.class);
	
	/**
	 * Metodo responsavel em inicializar o processamento dos arquivos
	 */
	public static void run() {
		final File diretorioInput = getPathDiretorioInput();
		validarPathDiretorioOutput();
		
		final List<File> arquivos = diretorioInput.listFiles() != null
									? Arrays
										.asList(diretorioInput.listFiles(ProcessingApplicationUtils.getfileFilter()))
										.stream()
										.collect(Collectors.toList())
									: null;
		
	    if (CollectionUtils.isNotEmpty(arquivos)) {
	    	final List<File> filesNotProcess = filterFilesNotProcess(arquivos);
	    	ProcessService.create().process(filesNotProcess);
	    } else {
	    	log.info(new StringBuilder().append("Não existem arquivos no diretório para processamento.").toString());
	    }
	    
	}
	
	/**
	 * Retorna o diretorio de entrada
	 * 
	 * @return File
	 */
	private static File getPathDiretorioInput() {
		try {
			return ProcessingApplicationUtils.getDiretorioIn();

		} catch (final Exception e) {
			throw new ProcessingApplicationException(new StringBuilder().append("Erro no diretório de entrada: ")
																		.append(e.getMessage())
																		.toString());
		}
	}
	
	/**
	 * Validar o diretorio de saida
	 */
	private static void validarPathDiretorioOutput() {
		try {
			ProcessingApplicationUtils.getDiretorioOut();

		} catch (final Exception e) {
			throw new ProcessingApplicationException(new StringBuilder().append("Erro no diretório de saída: ")
																		.append(e.getMessage())
																		.toString());
		}
	}
	
	/**
	 * Filtra os arquivos nao processados atraves da pasta de entrada.
	 * Exemplo: o arquivo data/in/arquivo.txt nao pode estar
	 * como processado em data/out/arquivo_process.txt
	 * 
	 * @param files
	 * @return List<File>
	 */
	private static List<File> filterFilesNotProcess(final List<File> files) {
		return files.stream()
					.filter(f -> !ProcessingApplicationUtils.getArquivosProcessados().contains(f.getName().toLowerCase()))
					.collect(Collectors.toList());
	}
}
