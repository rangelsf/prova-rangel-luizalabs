print('Initiating DB....');

db = db.getSiblingDB('wishlist');

db.createCollection('wishlist');

db.wishlist.insertMany([
    {     _id:"AAAAA",
          "nome": "Full List",
          "idCliente": "AAAAA",
          "idProdutos": ["AAAA", "AAAAB",
          				 "AAAAC", "AAAAD",
          				 "AAAAE", "AAAAF",
          				 "AAAAG", "AAAAH",
          				 "AAAAI","AAAAJ",
                         "AAAAK","AAAAL",
                         "AAAAM","AAAAN",
                         "AAAAO","AAAAP",
                         "AAAAQ","AAAAR",
                         "AAAAS","AAAAT"
          ]
        },
        {
        _id:"AAAAB",
        "nome": "Empty List",
        "idCliente": "AAAAA",
        "idProdutos": []}
]);

print('Wish Lists Inserted');

db.createCollection('client');

db.cliente.insertMany([
    {_id:"AAAAA", "nome": "Client A" },
    {_id:"AAAAB", "nome": "Client B" },
    {_id:"AAAAC", "nome": "Client C" },
    {_id:"AAAAD", "nome": "Client D" }
]);

print('Clients Inserted');

db.createCollection('product');

db.produto.insertMany([
    {_id:"AAAAA"  , "nome": "Product A" },
    {_id:"AAAAB"  , "nome": "Product B" },
    {_id:"AAAAC"  , "nome": "Product C" },
    {_id:"AAAAD"  , "nome": "Product D" },
    {_id:"AAAAE"  , "nome": "Product E" },
    {_id:"AAAAF"  , "nome": "Product F" },
    {_id:"AAAAG"  , "nome": "Product G" },
    {_id:"AAAAH"  , "nome": "Product H" },
    {_id:"AAAAI"  , "nome": "Product I" },
    {_id:"AAAAJ" , "nome": "Product J" },
    {_id:"AAAAK" , "nome": "Product K" },
    {_id:"AAAAL" , "nome": "Product L" },
    {_id:"AAAAM" , "nome": "Product M" },
    {_id:"AAAAN" , "nome": "Product N" },
    {_id:"AAAAO" , "nome": "Product O" },
    {_id:"AAAAP" , "nome": "Product P" },
    {_id:"AAAAQ" , "nome": "Product Q" },
    {_id:"AAAAR" , "nome": "Product R" },
    {_id:"AAAAS" , "nome": "Product S" },
    {_id:"AAAAT" , "nome": "Product T" },
    {_id:"AAAAU" , "nome": "Product U" },
    {_id:"AAAAV" , "nome": "Product V" }
]);


print('Products Inserted');
