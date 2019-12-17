package com.ramoncosta.cursomc.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String filename = multipartFile.getOriginalFilename();
			InputStream inputStream;
			inputStream = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(inputStream, filename, contentType);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}
	}

	public URI uploadFile(InputStream inputStream, String filename, String contentType) {
		try {
			ObjectMetadata objectMetaData = new ObjectMetadata();
			objectMetaData.setContentType(contentType);

			LOG.info("Iniciando Upload");
			s3Client.putObject(bucketName, filename, inputStream, objectMetaData);
			LOG.info("Upload Finalizado");
			return s3Client.getUrl(bucketName, filename).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}
	}
}
