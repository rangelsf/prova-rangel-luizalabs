print('Initiating DB....');

db = db.getSiblingDB('wishlist');

db.createCollection('wishlist');

db.wishlist.insertMany([
    {     _id:"1",
          "nome": "Full List",
          "idCliente": "1",
          "idProdutos": ["1","2","3","4","5","6","7","8","9","10",
                         "11","12","13","14","15","16","17","18","19","20"
          ]
        },
        {
        _id:"2",
        "nome": "Empty List",
        "idCliente": "1",
        "idProdutos": []}
]);

print('Wish Lists Inserted');

db.createCollection('client');

db.cliente.insertMany([
    {_id:"1", "nome": "Client 1" },
    {_id:"2", "nome": "Client 2" },
    {_id:"3", "nome": "Client 3" },
    {_id:"4", "nome": "Client 4" }
]);

print('Clients Inserted');

db.createCollection('product');

db.produto.insertMany([
    {_id:"1"  , "nome": "Product 1" },
    {_id:"2"  , "nome": "Product 2" },
    {_id:"3"  , "nome": "Product 3" },
    {_id:"4"  , "nome": "Product 4" },
    {_id:"5"  , "nome": "Product 5" },
    {_id:"6"  , "nome": "Product 6" },
    {_id:"7"  , "nome": "Product 7" },
    {_id:"8"  , "nome": "Product 8" },
    {_id:"9"  , "nome": "Product 9" },
    {_id:"10" , "nome": "Product 10" },
    {_id:"11" , "nome": "Product 11" },
    {_id:"12" , "nome": "Product 12" },
    {_id:"13" , "nome": "Product 13" },
    {_id:"14" , "nome": "Product 14" },
    {_id:"15" , "nome": "Product 15" },
    {_id:"16" , "nome": "Product 16" },
    {_id:"17" , "nome": "Product 17" },
    {_id:"18" , "nome": "Product 18" },
    {_id:"19" , "nome": "Product 19" },
    {_id:"20" , "nome": "Product 20" },
    {_id:"21" , "nome": "Product 21" },
    {_id:"22" , "nome": "Product 22" }
]);


print('Products Inserted');
