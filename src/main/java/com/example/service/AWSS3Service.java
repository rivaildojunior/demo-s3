package com.example.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class AWSS3Service {
	
	private final AmazonS3 s3client;

	public AWSS3Service() {
		AWSCredentials credentials = new BasicAWSCredentials("<inserir>",
				"<inserir>");
		this.s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2).build();
	}

	// is bucket exist?
	public boolean doesBucketExist(String bucketName) {
		return s3client.doesBucketExist(bucketName);
	}

	// create a bucket
	public Bucket createBucket(String bucketName) {
		return s3client.createBucket(bucketName);
	}

	// list all buckets
	public List<Bucket> listBuckets() {
		return s3client.listBuckets();
	}

	// delete a bucket
	public void deleteBucket(String bucketName) {
		s3client.deleteBucket(bucketName);
	}

	// uploading object
	public PutObjectResult putObject(String bucketName, String key, File file) {
		return s3client.putObject(bucketName, key, file);
	}

	// listing objects
	public ObjectListing listObjects(String bucketName) {
		return s3client.listObjects(bucketName);
	}

	// get an object
	public S3Object getObject(String bucketName, String objectKey) {
		return s3client.getObject(bucketName, objectKey);
	}

	// copying an object
	public CopyObjectResult copyObject(String sourceBucketName, String sourceKey, String destinationBucketName,
			String destinationKey) {
		return s3client.copyObject(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
	}

	// deleting an object
	public void deleteObject(String bucketName, String objectKey) {
		s3client.deleteObject(bucketName, objectKey);
	}

	// deleting multiple Objects
	public DeleteObjectsResult deleteObjects(DeleteObjectsRequest delObjReq) {
		return s3client.deleteObjects(delObjReq);
	}


}
