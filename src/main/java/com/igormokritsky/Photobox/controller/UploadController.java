package com.igormokritsky.Photobox.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


@Controller
public class UploadController {

    private static final String bucketName = "sandbox-photo-app/data";

    private AmazonS3 s3Client;

    @Value("${app.aws.iam.accesskey}")
    private String accessKey;

    @Value("${app.aws.iam.secretkey}")
    private String secretKey;

    @Value("${app.aws.s3.clientregion}")
    private String clientRegion;


    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(clientRegion)
                .build();

        boolean isBucketExist =s3Client.doesBucketExistV2(bucketName);
        if(!isBucketExist) {
            s3Client.createBucket(bucketName);
        }

        try {
            File fileObj = convertMultiPartToFile(file);
            String key = file.getOriginalFilename();
            uploadPhoto(key, fileObj);
        } catch (IOException e) {
            e.printStackTrace();
        }


        redirectAttributes.addFlashAttribute("message", "Successfully Uploaded");
        return "redirect:/uploadStatus";
    }

    public void uploadPhoto(String key, File file) {
        this.s3Client.putObject(bucketName, key, file);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(ModelMap m) {
        return "upload";
    }

    @GetMapping("/upload")
    public String displayHomePageForAlarm() {
        return "upload";
    }

}
