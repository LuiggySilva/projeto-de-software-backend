# AJuDE: AquiJUntosDoandoEsperança

> Repositório do backend do projeto final da disciplina de Projeto de Software na UFCG.

> O AJuDE é uma ferramenta que permite a organização de doações para projetos/campanhas.

> Link para apĺicação: http://ajude-crowdfunding-app.herokuapp.com/#campaigns

> [Vídeo de demonstração](https://youtu.be/j-9l-KAQGpI)

## O projeto utilizou das seguintes ferramentas:

- Java
- PostgreSQL
- Spring Boot
- Spring Data
- Heroku (Deploy)

## Diretórios usados:

- No caminho (`.src/main/java/com/ajude`):
  - `./DAO` Diretório onde encontra-se as classes de **Persistência**.
    - `CampanhaDAO.java` Persistência de campanhas
    - `ComentarioDAO.java` Persistência de comentarios
    - `DoacaoDAO.java` Persistência de doações
    - `LikeDAO.java` Persistência de likes
    - `UsuarioDAO.java` Persistência de usuários 
  - `./controller` Diretório onde encontra-se as classes **Controladoras**.
    - `CampanhaController.java` Controlador de campanha
    - `LoginController.java` Controlador de login
    - `UsuarioController.java` Controlador usuário
  - `./model` Diretório onde encontra-se as classes de **Modelo**.
    - `Campanha.java` Classe que representa uma campanha completa
    - `CampanhaDTO.java` Classe que representa uma campanha simples
    - `Comentario.java` Classe que representa um comentário
    - `Doacao.java` Classe que representa uma doação
    - `Like.java` Classe que representa um like
    - `Usuario.java` Classe que representa um usuário completo
    - `UsuarioDTO.java` Classe que representa um usuário simples
  - `./service` Diretório onde encontra-se as classes de **Serviço**.
    - `CampanhaService.java` Classe com os serviços oferecidos pela campanha
    - `JWTService.java` Classe com os serviços oferecidos pelo JsonWebToken
    - `UsuarioService.java` Classe com os serviços oferecidos pelo usuário
  - `./util` Diretório onde encontra-se as classes **Auxiliares**.
    - `Filtro.java` Classe com funcionalidade de filtrar as rotas que precisam de autorização
    - `JavaMailApp.java` Classe com funcionalidade de enviar emails para novos usuários cadastrados
    - `OrdenaCampanhaDataCriacao.java` Classe com funcionalidade de ordenar uma lista de campanhas pela data de criação
    - `OrdenaCampanhaDeadLine.java` Classe com funcionalidade de ordenar uma lista de campanhas pelo deadline
    - `OrdenaCampanhaLike.java` Classe com funcionalidade de ordenar uma lista de campanhas pela quantidade de likes
    - `OrdenaCampanhaMeta.java`Classe com funcionalidade de ordenar uma lista de campanhas pela meta
    - `StatusCampanha.java` Classe enum com todos status que uma campanha pode ter
  - `./AjudeApplication.java` Classe main que inicia a aplicação

## Desenvolvedores:
  - [Caio Maxximus](https://github.com/CaioMaxximus)
  - [Luiggy Silva](https://github.com/LuiggySilva)
