# Projeto Back-End para uma clinica

💎 O objetivo principal é desenvolver o back-end (API Java) para uma clínica com cadastro de médico, paciente e consultas se seguindo as regras 
pré-estabelecidas no Trello documentação será utilizado o Swagger .

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
BACK-END
1. Neste projeto foram aplicadas técnicas de controle com camadas de API, CONTROLLER, DOMAIN, INFRA e 
SERVICE, para modelar aplicação.
2. A CONTROLLER foram criados os mapeamentos, por ser uma API usamos o padrão REST com as 
anotações específicas para recursos HTTPs;
3. Do pacote DOMAIN foram criadas subclasses ENTITIES e REPOSITORY. A ENTITIES foram criados os requisitos e regras de negócios, já na REPOSITORY foi criado a 
persistência, onde a classe estende a interface JpaRepository para utilização de recursos específicos como CRUD.
4. No pacote SERVICE foi criada a configuração para requisições, retornos e interface implementada para retorno da CONTROLLER.
5. A documentação foi criada com SWAGGER 3, onde foi aplicada apenas às dependências do SWAGGER 3 no pom.xml para descrever a API RESTFul usando JSON.

## 📦 Para executar o projeto

## 📝 Documentação

A documentação da API está disponível no Swagger:

http://localhost:8080/swagger-ui/index.html

### Localstack

## 👏 Conclusão
Este projeto foi desenvolvido para aprendizado e certificação do curso Spring Boot 3 da Alura, além dos ensinamentos também apliquei algumas técnicas e 
arquiteturas que adquirir ao longo da profissão, com pode aprender ainda mais sobre esta ferramenta que vem se desenvolvendo cada vez mais e me qualificar para 
exercer a profissão de desenvolvedor de software com qualidade e eficiência. 
