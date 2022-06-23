# ProjetoCep com painel React

* [Android](https://developer.android.com/studio)
* [Retrofit](https://square.github.io/retrofit/)
* [Jackson](https://github.com/FasterXML/jackson)
* 
Projeto Sendo desenvolvido para estudos para treinar uma consulta usando Retrofit,Jackson,Mysql,React,Express,JavaScript,CSS,Axios.
Material de estudos base https://blog.matheuscastiglioni.com.br/consumindo-web-service-no-android-com-retrofit/

A intenção do projeto seria ter as seguintes funções:

- [X] O aplicativo pesquisar o Cep na Api do ViaCep
- [X] O Aplicativo enviar estes dados para o Mysql
- [X] O Express receber estes dados
- [X] Axios fazer o tratamento por rotas enviando para o Mysql
- [X] Axios fazer o tratamento de dados e Listar para o React
- [X] Dentro do banco de dados guardar os dados
- [X] React receber os dados e exir na tela
- [X] React poder cadastrar um Cep pelo front
- [X] React poder deletar os dados pelo front
- [ ] O aplicativo gurdar o Cep pesquisado em historico local
- [ ] O aplicativo ter uma tela para exibir o historico de Cep pesquisado

A estrutura do projeto seria da seguinte forma:

<img src="https://raw.githubusercontent.com/MesopotamiaAlpha/ProjetoCep/Principal/Screenshot_10.png">

Para rodar é necessário no servidor ter o projeto server instalado e rodando para receber as requisições: [Link do servidor] (https://github.com/MesopotamiaAlpha/servidorprojetocep) no repositório do servidor tem detelhes de como deve ser instalado e usado para rodar o projeto.

Atualmente o Android foi desenvolvido usando SDK 32, Gradle versão 7.3.3.