# Global Logic - Ejercicio

## Ejercicio Evaluación Java

### Ejecución

Ejecución con graddle wrapper

`gradlew bootRun`

### Endpoints
#### Crear usuario - No Authorization
(POST) http://localhost:8080/usuario

Se toma exactamente el Body del enunciado del ejercicio, con la diferencia del password, ya que no cae en las reglas de validación

##### Request Body
```json
{"name" : "Juan Rodriguez",
"email" : "juan@rodriguez.org",
"password" : "A12aaa",
"phones" : [
    {"number": "1234567",
    "citycode": "1",
    "contrycode": "57"
    }
]
}
```
#### Autorización - No Authorization
(POST) http://localhost:8080/authme

##### Request Body
```json
{"username":"juan@rodriguez.org","password":"A12aaa"}
```

#### Obtener usuario - Authorized
(GET) http://localhost:8080/usuario/{id}

El {id} es obtenido como respuesta de servicio de creación de usuario

##### Authorization header (Token Bearer)
pegar toker entregado como respuesta de servicio de autorización (if)

