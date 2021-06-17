#	requisitos:
		Execute o comando: docker run --name car-wash -p 3306:3306 -e MYSQL_DATABASE=lavajato -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=12345678 -d mysql:5.6 , para criação do banco de dados.
		Verifique a execução do Docker execute o comando: docker ps
		Acople devidamente o projeto ao tomcat para execução.