package com.ramoncosta.cursomc.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public void uploadFile(String localFilePath) {		
		
		try {
			File file = new File(localFilePath);
			LOG.info("Iniciando Upload");
			s3Client.putObject(new PutObjectRequest(bucketName, "teste.png", file));
			LOG.info("Upload Finalizado");
		} catch (AmazonServiceException exception) {
			LOG.info("AmazonServiceException: " + exception.getMessage());
			LOG.info("Status Code: " + exception.getErrorCode());
		} catch (AmazonClientException exception) {
			LOG.info("AmazonClientException: " + exception.getMessage());
		}
	}
}
