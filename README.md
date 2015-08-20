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

A tecnologia REST foi adotada devido a sua simplicidade de uso, fácil integração com qualquer tipo de sistema pois o protocolo de comunicação é a base da web(HTTP)
Principais vantagens na utilização:
  - Integração
  - Protocolo leve(HTTP)
  - Caching Responses
  - Flexibilidade
  - Performance
  
O Algoritmo de caminho minimo utilizado foi o de E. Dijkstra que dado um ponto de partida, que cálculo o custo para cada uma dos possíveis pontos de chegada.
A implementação foi a mais simples possível dado o tempo curto e nao possui nenhuma otimização, como cache dos estimativas e melhorias de performance no algoritmo.
* nota: O algoritmo parte da premissa de que todos os pontos tem peso >= 0

Referências
------------------------
http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html
http://algs4.cs.princeton.edu/home/
https://docs.oracle.com/javaee/7/tutorial/

  
Instalação e deploy da aplicação
------------------------
'''sh
 > git clone https://github.com/dgSantos/syslog.git
 > cd syslog
 > mvn clean install
 > mvn wilfly:deploy
'''

*É necessario que o servidor Wilfly esteja sendo executado e configurado na porta padrão 8080*

Requisitos
------------------------
 - Wildfly 8.0.0 final (porta padrão 8080)
 - Oracle Java SDK 7
 - Maven 3.2.x


  

