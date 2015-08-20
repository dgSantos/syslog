syslog
========================
Author: Diego Santos<br/>
Since: 19/08/2015<br/>
Description: Solução para o problema de custo minimo.<br/>

Tecnologias utilizadas
------------------------
Java 7, JAX-RS, REST, JPA e CDI

Solução
------------------------
Sistema para calculo de custo minimo de malha logística desenvolvido na plataforma Java EE 7.

O sistema conta com duas interfaces REST:
  - Uma interface para input dos dados de malha logistica através do formato JSON.
  - Uma interface REST para o serviço de calculo do custo mímino de entrega.  
Para mais informações, veja a seção 'Interfaces'
  
O algoritmo de cálculo do menor caminho utilizado foi baseado na solução proposta por E. Dijkstra.

Motivação
------------------------
A tecnologia de desenvolvimento utilizada foi a Java EE 7 devido ao fato de ser uma plataforma madura, fortemente estabelecida no mercado de ambito mundial.
Principais vantagens na utilização:
  - Escalabilidade
  - Integração
  - Estabilidade
  - Multiplataforma
  - Estabilidade

A tecnologia REST foi adotada devido a sua simplicidade de uso, fácil integração com qualquer tipo de sistema pois o protocolo de comunicação é a base da web (HTTP)
Principais vantagens na utilização:
  - Integração
  - Protocolo leve (HTTP)
  - Caching Responses
  - Flexibilidade
  - Performance
  
O Algoritmo de caminho minimo utilizado foi o de E. Dijkstra que dado um ponto de partida, realiza um cálculo de custo para cada um dos possíveis pontos de chegada.
A implementação foi a mais simples possível, dado o tempo curto para o desenvolvimento do projeto e nao possui nenhuma otimização como cache das estimativas e melhorias de performance no algoritmo.
O algoritmo foi estruturado para facilitar a manutenção e possíveis otimizações.
* nota: O algoritmo parte da premissa de que todos os pontos tem peso >= 0

Para a camada de persistência foi utilizada a api JPA, oficial do Java EE(hibernate)
Para efeito de testes o banco da aplicação utiliza o H2, banco de dados in memory.
Para rodar em produção é necessário trocar a configuração do banco facilmente, através do arquivo syslog-ds.xml, para um banco relacional como SQL Server, Oracle ou DB2 para comportar a demanda e grandes quantidades de dados.

Referências
------------------------
http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html<br/>
http://algs4.cs.princeton.edu/home/<br/>
https://docs.oracle.com/javaee/7/tutorial/<br/>

  
Instalação e deploy da aplicação
------------------------
```sh
 > git clone https://github.com/dgSantos/syslog.git
 > cd syslog
 > mvn clean install
 > mvn wilfly:deploy
```

*É necessario que o servidor Wilfly esteja sendo executado e configurado na porta padrão 8080*

Interfaces
------------------------
O Sistema dispõe de suas interfaces, uma para cadastro e outra para obter o menor custo.

<h4>Interface de input da malha logística:</h4>

Utilizar o verbo HTTP POST<br/>
  - <host:8080>/syslog/malha<br/>
    Exemplo: http://localhost:8080/syslog/malha<br/>

Formato JSON aceito pelo serviço:

```javascript
{
  "nome":"<nome_do_mapa>",
  "malha":[
    ["<origem>", "<destino>", "<distancia>"],
    ...
]
}
```

Exemplo:

```javascript
{"nome":"SP",
  "malha":[
    ["A", "B", "10"],
    ["B", "D", "15"],
    ["A", "C", "20"],
    ["C", "D", "30"],
    ["B", "E", "50"],
    ["D", "E", "30"]
  ]
}
```

<h4>Interface de calculo do menor custo:</h4>

Utilizar o verbo HTTP GET<br/>
  - <host:8080>/syslog/custo?mapa=<nome_do_mapa>&origem=<origem>&destino=<destino>&autonomia=<autonomia_veiculo>&combustivel=<valor_combustivel><br/>
    Exemplo: http://localhost:8080/syslog/custo?mapa=SP&origem=A&destino=D&autonomia=10&combustivel=2.50<br/>

Formato JSON retornado pelo serviço:

```javascript
{
  "rota": [
      ...
  ],
  "custo": <valor>
}```

Exemplo:

```javascript
{
  "rota": [
      "A",
      "B",
      "D"
  ],
  "custo": 6.25
}
```

Requisitos
------------------------
 - Wildfly 8.0.0 final (porta padrão 8080)
 - Oracle Java SDK 7
 - Maven 3.2.x


  

