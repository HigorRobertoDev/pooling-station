# API para votação de pauta e apuração dos resultados

Para iniciar a votação, é preciso realizar os seguintas passos:

1. Criação da Pauta
2. Criação da Sessão com o tempo de duração
3. Criação dos associados (caso não existir)
4. Votação
5. Apuração dos votos totais

#### Criação da Pauta
**POST:** http://localhost:8085/polling-station/voting-agenda
Request:
```json
{
    "description": "PAUTA 1"
}
```
Response:
```json
{
    "codVotingAgenda": 1,
    "description": "PAUTA 1",
    "registrationDate": "2023-04-10T11:30:34.958"
}
```

#### Listas das pautas
**GET:** http://localhost:8085/polling-station/voting-agenda
Response:
```json
[
    {
        "codVotingAgenda": 1,
        "description": "PAUTA 1",
        "registrationDate": "2023-04-10T10:40:51.108"
    }
]
```
#### Criação da Sessão com o tempo de duração
###### Detalhes:
- codVotingAgenda:  É gerado quando é criado uma pauta
- sessionOpeningTimeInMinutes: Tempo para a votação

**POST:** http://localhost:8085/polling-station/voting-session
Request:
```json
{
    "codVotingAgenda": 1,
    "sessionName": "SESSÃO 1",
    "sessionOpeningTimeInMinutes": 5
}
```
Response:
```json
{
    "codVotingSession": 2,
    "sessionName": "SESSÃO 1",
    "sessionOpeningTimeInMinutes": 5,
    "registrationDate": "2023-04-10T11:30:45.93"
}
```
#### Listas das Sessões
**GET:** http://localhost:8085/polling-station/voting-session
Response:
```json
[
    {
        "codVotingSession": 1,
        "sessionName": "SESSÃO 1",
        "sessionOpeningTimeInMinutes": 5,
        "registrationDate": "2023-04-10T10:41:07.588"
    }
]
```

#### Criação de Associados
**POST:** http://localhost:8085/associate
Request:
```json
{
    "associateName": "Higão de Guararema",
    "cpf": "79699062061"
}
```
Response:
```json
{
    "codAssociate": 1,
    "associateName": "Higão de Guararema",
    "cpf": "79699062061"
}
```
#### Lista dos Associados
**GET:** http://localhost:8085/associate
Response:
```json
[
    {
        "codAssociate": 1,
        "associateName": "Higão de Guararema",
        "cpf": "79699062061"
    }
]
```

#### Votação
###### Detalhes:
- codVotingAgenda:  É gerado quando uma pauta é criada
- codAssociate: É gerado quando um associado é criado
- Caso o cpf do associado não seja válido, a votação não será concluída
- valueVote: "S" para sim e "N" para não

**POST:** http://localhost:8085/polling-station/vote-agenda
Request:
```json
{
    "codVotingAgenda": 1,
    "codAssociate": 1,
    "valueVote": "N"
}
```
**OBS:** Response caso o cpf do associado não seja válido
```json
{
    "statusCode": 400,
    "message": "This Associate is unable to vote",
    "timeStamp": 1688739865414,
    "code": 2
}
```

#### Apuração dos votos totais
###### Detalhes:
- codVotingAgenda:  É gerado quando cria-se uma pauta

**GET:** http://localhost:8085/polling-station/vote-agenda-result/2
Response:
```json
[
    {
        "valueVote": "S",
        "result": 0
    },
    {
        "valueVote": "N",
        "result": 1
    }
]
```

**INTEGRAÇÕES:** Ao realizar uma votação, a aplicação faz uma integração com uma outra aplicação spring para validar se o cpf é válido.

**URL API:** http://app.higorroberto.dev.br/validador-cpf/{cpf}

Response quando o cpf for válido:
```json
{
  "status": "ABLE_TO_VOTE"
}
```

Response quando o cpf não for válido:
```json
{
  "status": "UNABLE_TO_VOTE"
}
```

URL do projeto do validador: https://github.com/HigorRobertoDev/validador-api

**BANCO DE DADOS DO PROJETO**

O banco de dados do projeto é um Mysql 5.6, ele está rodando em um container Docker junto com a imagem do projeto de validar o cep.

Eles estão hospedados em um VPS que uso para projetos pessoais.

Para configurar a roda do projeto spring, utilizei o nginx com a seguinte configuração:

```json
server {
  listen 80;

  server_name app.higorroberto.dev.br;

  location / {

      proxy_set_header X-Real-IP $remote_addr;
      proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
      proxy_set_header Host $host;
      proxy_set_header X-NginX-Proxy true;
      proxy_pass http://localhost:8082/;
      proxy_redirect http://localhost:8082/ http://$server_name/;
  }
}

```