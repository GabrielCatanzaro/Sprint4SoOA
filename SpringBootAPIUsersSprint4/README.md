Entrega sprint4 Springboot CRUD registro e login

Integrantes:

Gabriel Gomes Catanzaro / RM93445
Lucas Gomes Alcântara / RM98766
Henrique Canali Cuzato / RM551326
Filipe Prado Menezes / RM98765
Cássio Eid Kobaysahi Yonetsuka / RM99678


Funcionamento: Utilizamos um banco de dados da oracle, onde foi criada uma tabela Users como tabela principal, para conectar usar as crendenciais para conectividade com o banco de dados e um app como postman/insomnia para realizar as requisições com o banco


Projeto CRUD API criando todas as requisições iniciais principalmente registros de login, posteriormente sendo adicionados outros processos que completam a aplicação, trazendo complementos atrelados aos usuários ja existem e tornando uma aplicação completa


## Atualizações realizadas sprint 4

- Estrutura em camadas (controller, service, repository).
- Introduzido IUserService + UserServiceImpl (injeção via Spring).
- Segurança stateless com JWT (JwtTokenProvider, JwtAuthenticationFilter).
- BCrypt para codificação de senhas.
- OpenAPI (SpringDoc) configurado.
- Teste unitário básico para UserServiceImpl.

### Como rodar

1. Ajuste a chave secreta em JwtTokenProvider (substitua o texto padrão por uma chave segura).
2. `mvn clean package` e `mvn spring-boot:run`.
3. Documentação Swagger disponível em `/swagger-ui.html` ou `/swagger-ui/index.html`.
