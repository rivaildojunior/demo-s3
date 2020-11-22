package com.example.controller;

import java.io.File;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.example.service.AWSS3Service;

@RestController
@RequestMapping("/storages")
public class BucketController {

	
	private AWSS3Service awsService;

	public BucketController(AWSS3Service awsService) {
		this.awsService = awsService;
	}

	@PostMapping("/{bucket}")
	public void create(@PathVariable String bucket) {
		awsService.createBucket(bucket);
	}

	@DeleteMapping("/{bucket}")
	public void delete(@PathVariable String bucket) {
		awsService.deleteBucket(bucket);
	}
	
	@GetMapping
	public List<Bucket> listar() {
		return awsService.listBuckets();
	}
	
	@PostMapping("/{bucket}/file")
	public void uploadFile(@PathVariable String bucket) {
		awsService.putObject(bucket, "hello.txt", new File("C://s3//hello.txt"));
	}

	@DeleteMapping("/{bucket}/file")
	public void deleteFile(@PathVariable String bucket) {
		awsService.deleteObject(bucket, "hello.txt");
	}
	
	@GetMapping("/{bucket}/file")
	public ObjectListing listarFile(@PathVariable String bucket) {
		return awsService.listObjects(bucket);
	}

}
