/*
    Diferente de como a Kipper faz na vídeo dela, eu tive que pesquisar um pouco pois não estava executando a query via
    migrations. Tive que mudar a dependencia que estava sendo importada no maven, quando a Kipper estava fazendo a aplicação,
    ainda utilizava-se o Spring 3, que só de ter o flyway-core no classpath era o suficiente para a execução das migrations.
    Hoje, com o Spring 4, é necessário o starter-flyway para que seja corretamente executado, além da necessidade de explicitar
    também, qual SGBD irá utilizar, no meu caso, PostegreSQL(org.flywaydb:flyway-database-postgresql).
*/
CREATE TABLE event(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(24) NOT NULL,
    description VARCHAR(200) NOT NULL,
    img_url VARCHAR(100) NOT NULL,
    event_url VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    remote BOOLEAN NOT NULL,
    uf VARCHAR(2) NOT NULL,
    city VARCHAR(100) NOT NULL
);