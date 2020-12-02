package com.example.dacfusion.model

import java.util.*

data class Produtos (
    
    var id:Int? = null ,
    var categoria_id:Int? = null,
    var nome:String? = null,
    var cor:String? = null,
    var quantidade:Int? = null,
    var preco:Double? = null,
    var modelo:String? = null,
    var descricao:String? = null,
    var sku:Int? = null ,
    var image:String? = null ,
    var created_at:Date? = null,
    var updated_at:Date? = null
)



//    "id": 1,
//"categoria_id": 1,
//"nome": "teste",
//"cor": "azul",
//"quantidade": 10,
//"preco": 20,
//"modelo": "sdas",
//"descricao": "ds",
//"sku": 5678,
//"image": "produtos/8Yw6LAHYT7PSTNZwPZv4ceF1XaNGDaOqiLn1S9rU.jpeg",
//"created_at": "2020-11-19T01:11:45.000000Z",
//"updated_at": "2020-11-19T01:11:45.000000Z"