## Desafio backend

##### Esse repositório contém o código desenvolvido para resolver um desafio de backend. O desenvolvimento do código foi inspirado por esse vídeo: https://www.youtube.com/watch?v=9SL7XT3NX7U

### Desafios a serem implementados

Inicialmente, deve ser feito a implementação do cálculo de um dígito único de um número inteiro respeitando as seguintes regras:
1. Se X tem apenas um dígito, então o seu dígito único é X
2. Caso contrário, o dígito único de X é igual ao dígito único da soma dos dígitos de X.

A implementação desse problema está presente no arquivo *CalculateUniqueDigitServiceImpl.java*

Deverá ser feito um CRUD de usuários, que possuirão id, nome, email e uma lista de resultados dos *digitosUnicos* já calculados. Essa lista deverá conter os parâmetos de entrada de cálculo e qual o resultado. 

Além disso, um sistema de **CACHE** deve ser desenvolvido, sem utilizar nenhum framework. Basicamente, deverá ser feito um cache em memória para guardar os últimos 10 cálculos realizados dos digitosUnicos, sendo independente do usuário, de modo que se o usuário A realizar o cálculo dígito X e o usuário B requisitar o cálculo para o mesmo dígito X, não será necessário recalcular o resultado do digitoUnico. 

### Estrutura do projeto

Ao longo do desenvolvimento do projeto, foram utilizados testes unitários para tornar a aplicação mais robusta e eles estão presentes na pasta *test*.
A pasta *main* contém a aplicação em si, organizados da seguinte maneira:
1. Subdiretório *controller*:
    Contém a camada de acesso as rotas da API, com os arquivos *UserController.java* e *CalculateUniqueDigitController.java* realizando o gerenciamento delas e o subdiretório *dto* contendo os *records* utilizados para melhor organização da tipagem.
2. Subdiretório *domain*:
    Possui os arquivos *User.java* e *UniqueDigit.java* que representam as entidades representadas no banco de dados, de acordo com os requisitos do projeto.
3. Subdiretório *repository*: possui o arquivo *UserRepository.java*, responsável pela comunicação direta com o banco de dados.
4. Subdiretório *services*: representa a camada de implementação da lógica do projeto, sendo organizado entre arquivos de interface, como *UserService.java* e arquivos que implementam essa interface, como *UserServiceImpl.java*. Além disso, possui os diretórios de *dto*, para organização dos *records* e o diretório *exceptions* que lida com possíveis erros que possam a surgir por uso incorreto da API. 
