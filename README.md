# prova-rangel-luizalabs
 Prova técnica para desenvolvedor pleno
 
***Apresentação***
 
Olá pessoal!
 
Esse repositório existe pois me foi dada uma oportunidade de integrar o time de desenvolvedores no Luizalabs da Magalu.
 
Foi dado como desafio para mim o desenvolvimento de uma API REST de Lista de Desejos.
 
O objetivo foi desenvolver um serviço resolvendo a funcionalidade de Wishlist do cliente com os seguintes requisitos:
	- Adicionar um produto na Wishlist do cliente;
	- Remover um produto da Wishlist do cliente;
	- Consultar todos os produtos da Wishlist do cliente;
	- Consultar se um determinado produto está na Wishlist do cliente;
	- A Wishlist deve ter o limite máximo de 20 produtos;
	- O projeto deve ter testes unitários e integrados;
	- Usar preferencialmente o modelo REST;
	- Banco de dados deve ser NoSQL;
	- Linguagem Java 11 ou +
	- Framework Spring Boot;
	- Utilizar Clean Architecture;
	
 
***Como eu desenvolvi***

Utilizei a Clean Architecture dividindo em 2 grandes pacotes a aplicação:

1. **Domain** pacote onde ficou concentrado as regras de negócio, juntamente com os casos de uso.
2. **Infraestructure** pacote responsável por guardar as classes de exposição da API, pessoalmente escolhi controllers, juntamente com as classes de banco de dados.


Coloquei como desafio para mim mesmo usar a menor quantidade possível de dependências e frameworks.
Logo frameworks mais comuns como Lombok, Mapstruct, Hibernate não foram utilizados.

|   | O que foi utilizado |
| ------------- | ------------- |
| Linguagem  | Java 11  |
| Framework | Spring Boot 2.7.9  |
| Gerenciador de dependência | Maven 3.8 |
| Banco de Dados | MongoDB latest |
| Testes | Mockito |
| Documentação | Springdoc 1.6.15 com Swagger 2 |


 
 
***Como rodar***

Para você conseguir visualizar o projeto será necessário buildar e subir os containers.
Basta executar os seguintes comandos dentro da pasta raiz do projeto


Para buildar
`mvn clean package`


Para subir o container Docker 
`docker-compose up --build`
Nesse docker-compose existe as configurações para o .jar e uma imagem MongoDB 



Após a subida dos conteiners a documentação da API vai ficar disponível em
http://localhost:8080/swagger-ui.html


