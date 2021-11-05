# creditsTc
- Primero se debe ejecutar el servidor Eureka que se encuentra en el repositorio https://github.com/ajulcala/eurekaTc
- Segundo se debe ejecutar el servidor Config Server que se encuentra en el repositorio https://github.com/ajulcala/configServerTc
- Tercero registrar CUSTOMER https://github.com/ajulcala/customerTc

CREDIT ACCOUNT PERSONAL

Para registrar un CREDIT ACCOUNT PERSONAL en enviaremos por la ruta el dni del CUSTOMER  a traves de un POST:
http://localhost:8092/rest/creditpersonal/
Estructura para el registro:

{
    "dni": "8565855",
    "name": "Jose",
    "apep": "Peres",
    "apem": "Peres",
    "birth_date": "2000-03-03",
    "address": "Los Girasoles",
    "naccount": "155-562-536-55",
    "currency": "SOLES",
    "limit": 5000.0,
    "amount": 5000.0 ,
    "status": 1,
    "card": {        
        "number": "111-635-89563-88",
        "password": "1234"
    }
}

Si el usuario existe en el microservicio de CREDIT ACCOUNT PERSONAL no dejara crear otra cuenta si existe una cuenta con el mismo dni.

Buscar por CARD para consultar saldo: http://localhost:8092/rest/creditpersonal/card a traves de una peticion POST

{        
    "number": "111-635-89563-88",
    "password": "1234"
}

para buscar un CREDIT ACCOUNT PERSONAL por DNI hacemos una peticion GET:

http://localhost:8092/rest/creditpersonal/dni/8565855

Para Modificar un CREDIT ACCOUNT PERSONAL por ID hacemos una peticion PUT con la estructura antes mencionada:

http://localhost:8092/api/creditpersonal/61855fd5c040b66aa9c526d2

y finalmente para eliminar un CREDIT ACCOUNT PERSONAL por ID hacemos una peticion DELETE:

http://localhost:8092/api/creditpersonal/61855fd5c040b66aa9c526d2


CREDIT ACCOUNT EMPRESARIAL

Para registrar un CREDIT ACCOUNT PERSONAL en enviaremos por la ruta el dni del CUSTOMER  a traves de un POST:
http://localhost:8092/rest/creditbusiness/
Estructura para el registro:

{
    "naccount": "124-562-536-99-36",
    "currency": "SOLES",
    "ruc": "235689569999",
    "business_name": "INVERSIONES GOMEX", 
    "address":"Calle los Rabanos 235",
    "limit": 30000.00,
    "amount": 30000.00,
    "card": {
        "number": "222-635-565-03",
        "password": "1234"
    },
"owners":[
        {
        "dni": "56897788",
        "description": "TITULAR PRINCIPAL"
        },
        {
        "dni": "56844600",
        "description": "TITULAR SECUNADRIOS"
        }
    ],
"signatories":[
        {
        "dni": "56897788",
        "description": "TITULAR PRINCIPAL"
        },
        {
        "dni": "56844600",
        "description": "TITULAR SECUNADRIOS"
        }
    ]
}

Buscar por CARD para consultar saldo: http://localhost:8092/rest/creditbusiness/card a traves de una peticion POST

{        
    "number": "111-635-89563-88",
    "password": "1234"
}

para buscar un CREDIT ACCOUNT PERSONAL por DNI hacemos una peticion GET:

http://localhost:8092/rest/creditbusiness/ruc/8565855

Para Modificar un CREDIT ACCOUNT PERSONAL por ID hacemos una peticion PUT con la estructura antes mencionada:

http://localhost:8092/api/creditbusiness/61855fd5c040b66aa9c526d2

y finalmente para eliminar un CREDIT ACCOUNT PERSONAL por ID hacemos una peticion DELETE:

http://localhost:8092/api/creditbusiness/61855fd5c040b66aa9c526d2

