# Documentação da API

Esta é uma API de gerenciamento de pessoas e seguros.

## Endpoints

### 1. Cadastrar Pessoa

- **URL:** `/pessoa`
- **Método:** `POST`
- **Descrição:** Cria um novo registro de pessoa.
- **Parâmetros de entrada:**
    - `nome` (String, obrigatório): Nome da pessoa.
    - `cpfCnpj` (String, obrigatório): CPF ou CNPJ da pessoa.
    - `nascimento` (String): Data de nascimento da pessoa (opcional).
    - `seguros` (Lista de Strings): Lista de seguros associados à pessoa (opcional).
- **Exemplo de Requisição:**
  ```json
  {
    "nome": "João da Silva",
    "cpfCnpj": "123.456.789-00",
    "nascimento": "1990-01-01",
    "seguros": ["Seguro de Vida", "Seguro de Automóvel"]
  }

- **Exemplo de Requisição:**
  ```json
  {
    "nome": "João da Silva",
    "cpfCnpj": "123.456.789-00",
    "nascimento": "1990-01-01",
    "seguros": [null]
  }

Resposta de Sucesso: Retorna os detalhes da pessoa criada.

- **Exemplo de Response:**
  ```json
    {
        "id": "743f9e0b-16da-41c0-b70d-e3af3e71adfa",
        "nome": "Alessandra Silveira",
        "cpfCnpj": "59582552551",
        "nacimento": "1972-01-02",
        "seguros": [
            "vida",
            "empresarial",
            "residencial",
            "itens-pessoais"
        ]
    }  

- **Exemplo de Response:**
  ```json
    {
        "id": "743f9e0b-16da-41c0-b70d-e3af3e71adfa",
        "nome": "Alessandra Silveira",
        "cpfCnpj": "59582552551",
        "nacimento": "1972-01-02",
        "seguros": [null]
    } 

2. Consultar Pessoa por ID
   URL: /pessoa/{id}
   Método: GET
   Descrição: Consulta os detalhes de uma pessoa por ID.
   Parâmetros de entrada:
   id (String, obrigatório): ID da pessoa a ser consultada.
   Exemplo de Requisição:
   /pessoa/cafc5729-a332-448a-996b-4bea910ff7d1
   Resposta de Sucesso: Retorna os detalhes da pessoa com o ID especificado.
3. Buscar Pessoas por Termo
   URL: /pessoa
   Método: GET
   Descrição: Busca pessoas com base em um termo de pesquisa.
   Parâmetros de consulta:
   t (String, obrigatório): Termo de pesquisa.
   Exemplo de Requisição:
   /pessoa?t=João
   Resposta de Sucesso: Retorna uma lista de pessoas que correspondem ao termo de pesquisa.
   Configurações  


- A API utiliza um banco de dados MySQL e é implantada em containers Docker. As configurações do Docker Compose incluem contêineres para o MySQL, HAProxy (balanceador de carga). dois contêineres da aplicação API.


- MySQL:

Host: mysql   
Porta: 3306   
Usuário: root  
Senha: 1234567890  
Banco de dados: pessoa  

Aplicação:

Dois contêineres da aplicação expostos nas portas 8082 e 8083.
Conexão com o banco de dados MySQL configurada nas variáveis de ambiente.
Uso
Clone o repositório.
Execute o Docker Compose para iniciar os contêineres.
Acesse a API nos endpoints mencionados acima.
Lembre-se de verificar a documentação da API para obter mais detalhes sobre cada endpoint e os formatos de entrada/saída de dados.