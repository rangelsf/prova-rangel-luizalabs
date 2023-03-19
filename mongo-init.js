print('Initiating DB....');

db = db.getSiblingDB('prova');

db.createCollection('wishlist');

db.wishlist.insertMany([
    {     _id:"w1",
          "name": "Full List",
          "clientId": "c1",
          "productIdList": ["p1", "p2",
          				 "p3", "p4",
          				 "p5", "p6",
          				 "p7", "p8",
          				 "p8","p10",
                         "p11","p12",
                         "p13","p14",
                         "p15","p16",
                         "p17","1p8",
                         "p19","p20"
          ]
        },
        {
        _id:"w2",
        "name": "Empty List",
        "clientId": "c1",
        "productIdList": []}
]);

print('Wish Lists Inserted');

db.createCollection('client');

db.client.insertMany([
    {_id:"c1", "name": "Client A" },
    {_id:"c2", "name": "Client B" },
    {_id:"c3", "name": "Client C" },
    {_id:"c4", "name": "Client D" }
]);

print('Clients Inserted');

db.createCollection('product');

db.product.insertMany([
    {_id:"p1"  , "name": "Product 1" },
    {_id:"p2"  , "name": "Product 2" },
    {_id:"p3"  , "name": "Product 3" },
    {_id:"p4"  , "name": "Product 4" },
    {_id:"p5"  , "name": "Product 5" },
    {_id:"p6"  , "name": "Product 6" },
    {_id:"p7"  , "name": "Product 7" },
    {_id:"p8"  , "name": "Product 8" },
    {_id:"p9"  , "name": "Product 9" },
    {_id:"p10" , "name": "Product 10" },
    {_id:"p11" , "name": "Product 11" },
    {_id:"p12" , "name": "Product 12" },
    {_id:"p13" , "name": "Product 13" },
    {_id:"p14" , "name": "Product 14" },
    {_id:"p15" , "name": "Product 15" },
    {_id:"p16" , "name": "Product 16" },
    {_id:"p17" , "name": "Product 17" },
    {_id:"p18" , "name": "Product 18" },
    {_id:"p19" , "name": "Product 19" },
    {_id:"p20" , "name": "Product 20" },
    {_id:"p21" , "name": "Product 21" },
    {_id:"p22" , "name": "Product 22" }
]);


print('Products Inserted');
