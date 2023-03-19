package com.prova.rangel.luizalabs.prova;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractContainerBaseTest {

	protected final String URL_BASE = "http://localhost:8080";

    static MongoDBContainer mongoDBContainer;

    static {
        Map<String, String> env = new HashMap<>();
        env.put("MONGO_REPLICA_SET_NAME", "rs0");
        mongoDBContainer = new MongoDBContainer("mongo:4.2.7")
                .withEnv(env);
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    public static void startWishlist(MongoTemplate mongoTemplate){
        MongoCollection<Document> wishlistCollection = mongoTemplate.getCollection("wishlist");
        wishlistCollection.insertMany(Arrays.asList(
                Document.parse("{_id: \"w1\",\n" +
                        "\"name\": \"Full List\",\n" +
                        "\"clientId\": \"c1\",\n" +
                        "\"productIdList\": [\"p1\",\"p2\",\"p3\",\"p4\",\"p5\",\"p6\",\"p7\",\"p8\",\"p9\",\"p10\",\n" +
                        "\"p11\",\"p12\",\"p13\",\"p14\",\"p15\",\"p16\",\"p17\",\"p18\",\"p19\",\"p20\"]}"),
                Document.parse("{_id: \"w2\",\n" +
                        "\"name\": \"Empty List\",\n" +
                        "\"clientId\": \"c1\",\n" +
                        "\"productIdList\": []}")
                )
        );
    }
    
    public static void dropWishlist(MongoTemplate mongoTemplate){
        MongoCollection<Document> collection = mongoTemplate.getCollection("wishlist");
        collection.drop();
    }

    public static void startProduct(MongoTemplate mongoTemplate){
        MongoCollection<Document> productCollection = mongoTemplate.getCollection("product");
        productCollection.insertMany(Arrays.asList(
                Document.parse("{_id:\"p1\"  ,\"name\": \"Product 1\" }"),
                Document.parse("{_id:\"p2\"  ,\"name\": \"Product 2\" }"),
                Document.parse("{_id:\"p3\"  ,\"name\": \"Product 3\" }"),
                Document.parse("{_id:\"p4\"  ,\"name\": \"Product 4\" }"),
                Document.parse("{_id:\"p5\"  ,\"name\": \"Product 5\" }"),
                Document.parse("{_id:\"p6\"  ,\"name\": \"Product 6\" }"),
                Document.parse("{_id:\"p7\"  ,\"name\": \"Product 7\" }"),
                Document.parse("{_id:\"p8\"  ,\"name\": \"Product 8\" }"),
                Document.parse("{_id:\"p9\"  ,\"name\": \"Product 9\" }"),
                Document.parse("{_id:\"p10\" ,\"name\": \"Product 10\" }"),
                Document.parse("{_id:\"p11\" ,\"name\": \"Product 11\" }"),
                Document.parse("{_id:\"p12\" ,\"name\": \"Product 12\" }"),
                Document.parse("{_id:\"p13\" ,\"name\": \"Product 13\" }"),
                Document.parse("{_id:\"p14\" ,\"name\": \"Product 14\" }"),
                Document.parse("{_id:\"p15\" ,\"name\": \"Product 15\" }"),
                Document.parse("{_id:\"p16\" ,\"name\": \"Product 16\" }"),
                Document.parse("{_id:\"p17\" ,\"name\": \"Product 17\" }"),
                Document.parse("{_id:\"p18\" ,\"name\": \"Product 18\" }"),
                Document.parse("{_id:\"p19\" ,\"name\": \"Product 19\" }"),
                Document.parse("{_id:\"p20\" ,\"name\": \"Product 20\" }"),
                Document.parse("{_id:\"p21\" ,\"name\": \"Product 21\" }"),
                Document.parse("{_id:\"p22\" ,\"name\": \"Product 22\" }")
        ));
    }
    
    public static void dropProduct(MongoTemplate mongoTemplate){
        MongoCollection<Document> collection = mongoTemplate.getCollection("product");
        collection.drop();
    }

    public static void startClient(MongoTemplate mongoTemplate){
        MongoCollection<Document> clientCollection = mongoTemplate.getCollection("client");
        clientCollection.insertMany(Arrays.asList(
                Document.parse("{_id:\"c1\", \"name\": \"Client 1\" }"),
                Document.parse("{_id:\"c2\", \"name\": \"Client 2\" }"),
                Document.parse("{_id:\"c3\", \"name\": \"Client 3\" }"),
                Document.parse("{_id:\"c4\", \"name\": \"Client 4\" }")
        ));
    }
    
    public static void dropClient(MongoTemplate mongoTemplate){
        MongoCollection<Document> clienteCollection = mongoTemplate.getCollection("client");
        clienteCollection.drop();
    }
}
