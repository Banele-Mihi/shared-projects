package com.jrhlc.jrhlc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jrhlc.jrhlc.config.ElasticsearchConfig;



@SpringBootApplication
public class JrhlcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(JrhlcApplication.class, args);
	}

	@Autowired
	private ElasticsearchConfig elasticsearchConfig;
	
	@Override
	public void run(String... args) throws Exception {
		
		CreateIndexRequest createIndex = new CreateIndexRequest();
		
		File file = new File(getClass().getClassLoader().getResource("mappings.JSON").getFile());
		FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        String mappings = bufferedReader.readLine();
        
        bufferedReader.close();
		
        
		elasticsearchConfig.client().indices().create(createIndex.source(mappings, XContentType.JSON).index("test-index"), RequestOptions.DEFAULT);
		
		
		//elasticsearchConfig.client().indices().create(createIndex.source(indexMappings(), XContentType.JSON).index("test-index"), RequestOptions.DEFAULT);
	}
	
	
	public String indexMappings() {
	
		String mappings = "{ \"mappings\": { \"message\": { \"properties\": { \"messageType\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"morongwaId\": { \"type\": \"long\", \"copy_to\": \"all_field\" }, \"direction\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"trno\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"queueName\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"country\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"senderAddress\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"receiverAddress\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"businessEntity\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"hostId\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"systemDate\": { \"type\": \"date\", \"copy_to\": \"all_field\" }, \"relatedReference\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"system\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"username\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"messageKey\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"originalMessage\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"finFormat\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"amount\": { \"type\": \"double\", \"copy_to\": \"all_field\" }, \"jsonFormat\": { \"type\": \"text\", \"copy_to\": \"all_field\" }, \"all_field\": { \"type\": \"text\" } } } } }";
		
		return mappings;
	}

}
