Baixe o projeto.
Execute-o em uma IDE de sua escolha.
Abra seu navegador e navegue para a seguinte URL:
http://localhost:8080/swagger-ui/index.html
A API está totalmente documentada via Swagger.

Segurança da API
A API é protegida pelo Spring Security (token JWT). Aqui estão os passos para obter acesso:

Registro de Usuário: Registre um usuário no banco de dados (você pode fazer isso via Postman ou Swagger).
Login: Com o usuário registrado, faça o login.
Obtenção do Token: Recupere o token retornado após o login.
Autorizar Swagger: Use o token para autorizar o Swagger. Uma vez autorizado, todos os métodos estarão acessíveis.
Sobre a API:

Esta API REST foi projetada para gerenciar medicamentos em uma farmácia hospitalar. Ela suporta as seguintes funcionalidades:

- Registrar Medicamentos: Adicionar novos medicamentos ao sistema.

- Registrar Usuários do Sistema: Adicionar novos usuários ao sistema.

- Gerenciar Quantidades de Medicamentos: Adicionar ou remover quantidades de medicamentos.

- Deleção: Realizar deleção direta ou deleção lógica baseada em regras de negócios.

- Controle de Segurança: As requisições são permitidas apenas após o login e inclusão do token para autenticação.

- Criptografia de Senha: As senhas são criptografadas usando Bcrypt para garantir a segurança da instituição e suas funções.

Tecnologias Utilizadas:

- Java
- Spring Boot
- Token JWT
- MySQL
- Swagger
- Spring Security
- Bcrypt
