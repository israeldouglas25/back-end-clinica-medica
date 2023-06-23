# Projeto Back-End para uma clinica

💎 O objetivo principal é desenvolver o back-end (API Java) para uma clínica com cadastro de médico, paciente e consultas se seguindo as regras 
pré-estabelecidas no Trello documentação será utilizado o Swagger .

| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **API VollMed**
| :label: Tecnologias | Java 17, Spring Boot, MySQL, JWT Token (tecnologias utilizadas)
| :rocket: URL         | https://github.com/israeldouglas25/back-end-clinica-medica
| :fire: Desafio     | https://trello.com/b/SDBSgoLb/api-voll-med
<!-- Inserir imagem com a #vitrinedev ao final do link -->
![](https://via.placeholder.com/1200x500.png?text=imagem+lindona+do+meu+projeto#vitrinedev)

## Detalhes do projeto

Textos e imagens que descrevam seu projeto, suas conquistas, seus desafios, próximos passos, etc...

## 🛑 Pré-Requisitos / Dependências
✅ Java JDK 17 </br >
✅ IDE para desenvolvimento Java </br >
✅ Spring boot 3+ </br >
✅ Spring Security </br >
✅ Banco dados MySQL </br >
✅ Flyway </br >
✅ Lombok </br >
✅ JPA </br >
✅ Validation </br >
✅ JWT Token 4.2.1 </br >

## 👣 Passo-a-Passo
1. Neste projeto foram aplicadas técnicas de controle com camadas de API, CONTROLLER, DOMAIN, INFRA e 
SERVICE, para modelar aplicação.
2. A CONTROLLER foram criados os mapeamentos, por ser uma API usamos o padrão REST com as 
anotações específicas para recursos HTTPs;
3. Do pacote DOMAIN foram criadas subclasses ENTITIES e REPOSITORY. A ENTITIES foram criados os requisitos e regras de negócios, já na REPOSITORY foi criado a 
persistência, onde a classe estende a interface JpaRepository para utilização de recursos específicos como CRUD.
4. No pacote SERVICE foi criada a configuração para requisições, retornos e interface implementada para retorno da CONTROLLER.
5. A documentação foi criada com SWAGGER 3, onde foi aplicada apenas às dependências do SPRING DOC no pom.xml para descrever a API RESTFul usando JSON.

## 📦 Para executar o projeto
```shell
java -jar -DDATASOURCE_URL=? -DDATASOURCE_USERNAME=? -DDATASOURCE_PASSWORD=? .\api-clinica-medica-0.0.1.jar --spring.profiles.active=prod
```

### Para executar as requisições (end-point)
Entrar no banco de dados, na tabela usuarios, criar um login e senha, exemplo:
```shell
INSERT INTO `vollmed_api`.`usuarios` (`login`, `senha`) VALUES ('teste@email.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');
```
login: teste@email.com </br >
senha: 123456 = ($2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.) criptografada </br >

Gerar o Token, copiar e incluir no Authorize do Swagger </br >
O Token tem validade de 4 horas.

## 📝 Documentação

A documentação da API está disponível no Swagger:

http://localhost:8080/swagger-ui/index.html

### Localstack

## 👏 Conclusão
Este projeto foi desenvolvido para aprendizado do curso Spring Boot 3 da Alura, além dos ensinamentos também apliquei algumas técnicas e 
arquiteturas que adquirir ao longo de outros estudos, pude aprender ainda mais sobre esta linguagem e ferramenta que vem se desenvolvendo além mim qualificar para 
exercer a profissão de desenvolvedor de software com qualidade e eficiência. 
