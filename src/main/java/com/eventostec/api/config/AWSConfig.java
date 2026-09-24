package com.eventostec.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig {
    @Value("${aws.region}")
    private String awsRegion;

    /*Tenho que configurar o aplicativo IAM da aws no pc
     * Novamente, por conta da diferença de versões, tive que pesquisar um pouco para descobrir os novos metodos chamados
     * para seguir a semantica e funcionamento da Kipper, nada demais. Mudou pouquissimas coisas sobre o AWS S3.
     * */
    @Bean
    public S3Client createS3Instance() {
        return S3Client.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(ProfileCredentialsProvider.create("local_access"))
                .build();
    }
}
