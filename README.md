# REST API com Spring Boot e PostgreSQL

###  API de cadastramento de imóveis

Sendo construída com o framework [Spring Boot](https://spring.io/projects/spring-boot)  e baseado em um banco de dados [PostgreSQL](https://www.postgresql.org/).

<br>

## Diagrama do banco de dados

<p align="center">
 <img width="500" src="https://github.com/Johnson49/api-rest-com-spring-boot-e-postgreSQL/blob/main/uml.drawio.png"> 
</p>

<br>

## EndPoints 

> As rotas são compostas pelo endereço base (http://localhost:8080) mais o recurso que você deseja acessa.

### Imoveis

|Método|Rota| Funcionalidade| Acesso |
|:-------:|:-----:|:------:|:------:|
|GET | /imoveis/ | Consulta todos os imoveis existentes.| Público |
|GET |  /imoveis?id= | Consulta um imovel pelo seu id| Público |
|POST | /imoveis | Registrar um novo imovel. | Público |
| PUT | /imoveis?id= | Atualiza os dados de um imovel.| Público |
| DELETE | /imoveis?id= | Excluir um imovel. | Público |

<br>

### Proprietário 

|Método|Rota| Funcionalidade| Acesso |
|:-------:|:-----:|:------:|:------:|
|GET | /proprietario/ | Consulta todos os proprietários  existentes.| Público |
|GET |  /proprietario?id= | Consulta um proprietário  pelo seu id| Público |
|POST | /proprietario | Registrar um novo proprietário . | Público |
| PUT | /proprietario?id= | Atualiza os dados de um proprietário .| Público |
| DELETE | /proprietario?id= | Excluir um proprietário . | Público |
