# news-monitor-api
API Restful with Java 8, Spring Boot v2.7.2, postgreSQL, Eclipse e Spring Tools
## 1. Download images the postgreSQL and pgadmin4

docker pull postgres
docker pull dpage/pgadmin4

## 2. Create network in bridge to containers

docker network create -d bridge postgres-network

## 3. Create container to instance the postgreSQL

docker run --name newsmonitor-postgres --network=postgres-network -e "POSTGRES_PASSWORD=skfjr23!" -p 5432:5432 -v C:\Users\Alan\Documents\Treinamento\Postgres\PostgreSQL:/var/lib/postgresql/data -d postgres

Host: newsmonitor-postgres or ip
Port: 5432
User: postgres
Password: skfjr23!

## 4. Create container to execute pgadmin4

docker run --name newsmonitor-pgadmin --network=postgres-network -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=alanlages@gmail.com" -e "PGADMIN_DEFAULT_PASSWORD=skfjr23!" -d dpage/pgadmin4

http://127.0.0.1:15432

## 5. Create database with name "newsmonitor"

## 6. To test use Talend API Tester or Postman

http://127.0.0.1:8080/article

{
	"title": "",
	"date"
	"content": ""
}
