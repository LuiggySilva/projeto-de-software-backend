
# H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2
#indicarï¿½ o path para vocï¿½ acessar a interface do h2, em geral, vï¿½ ao browser e coloque localhost:8080/h2 com 8080 ou sua porta

#deixa que hibernate gerencia a criaï¿½ï¿½o das bases de dados - permite atualizaï¿½ï¿½es nas bases, mas nunca apaga tabelas ou colunas que nï¿½o estejam em uso pela aplicaï¿½ï¿½o - existem outras configuraï¿½ï¿½es - essa ï¿½ sï¿½ simples e segura na fase de desenvolvimento!
spring.jpa.hibernate.ddl-auto=update
hibernate.dialect=org.hibernate.dialect.H2Dialect


# Datasource
spring.datasource.url=jdbc:h2:file:./ajuda
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver

server.servlet.context-path=/v1
#diz ao spring que coloque /api antes de qualquer url, ou seja, se voce quiser utilizar as rotas /products, precisarï¿½ adicionar /api =>  /v1/api/products e assim por diante
