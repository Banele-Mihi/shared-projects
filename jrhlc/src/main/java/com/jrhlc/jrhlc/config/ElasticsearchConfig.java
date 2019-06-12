package com.jrhlc.jrhlc.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
	
		@Value("${spring.data.elasticsearch.properties.host}")
	    private String elasticsearchHost;
		
		@Value("${spring.data.elasticsearch.properties.host}")
		private String host;
		
		@Value("${spring.data.elasticsearch.properties.port}")
		private int port;

	    @Bean(destroyMethod = "close")
	    public RestHighLevelClient client() {
	        
	        RestHighLevelClient client = new RestHighLevelClient(
	                RestClient.builder(new HttpHost(host, port, "http"),
	                        		   new HttpHost(host, 9201, "http")));

	        return client;

	    }


}
