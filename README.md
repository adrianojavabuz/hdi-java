# Teste HDI
## API Usuário e Contatos

Esta API retorna um CRUD de usuários e contatos esta desenvolvida utilizando JAVA como linguagem de desenvolvimento.


## Tecnologias utilizadas

- SpringBoot
- Banco de dados H2
- Gradle

## Instalação
Entrar na pasta do projeto e executar o comando :
gradle bootRun [To run](https://docs.gradle.org/current/userguide/userguide.html).

Ao iniciar o projeto é criado uma massa de teste
usuario:
{
"id": 1,
"nome": "User1",
"documento": 465789834,
"contatos": [
{
"id": 2,
"email": "cebolinha@gmail.com",
"telefone": 11945467348,
"flag": 1
}
]
}
contato:
{
"id": 2,
"email": "cebolinha@gmail.com",
"telefone": 11945467348,
"flag": 1
}
A API esta iniciando na porta 8088.

Acesso ao banco de dados H2:
http://localhost:8088/h2-console

## POSTMAN
Chamadas do POSTMAN CRUD:
http://localhost:8088/api/usuario
http://localhost:8088/api/contato