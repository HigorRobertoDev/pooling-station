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
    "associateName": "Higão de Guararema"
}
```
Response:
```json
{
    "codAssociate": 1,
    "associateName": "Higão de Guararema"
}
```
#### Lista dos Associados
**GET:** http://localhost:8085/associate
Response:
```json
[
    {
        "codAssociate": 1,
        "associateName": "Higão de Guararema"
    }
]
```

#### Votação
###### Detalhes:
- codVotingAgenda:  É gerado quando uma pauta é criada
- codAssociate: É gerado quando um associado é criado
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