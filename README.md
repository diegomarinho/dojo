PROBLEMA
========
Dado o seguinte log de um jogo de tiro em primeira pessoa:

23/04/2013 15:34:22 - New match 11348965 has started
23/04/2013 15:36:04 - Roman killed Nick using M16
23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN
23/04/2013 15:39:22 - Match 11348965 has ended

Resultado esperado
-----------------------
* A partir de um input de um arquivo de log do formato acima, montar o ranking de cada partida, com a quantidade assassinatos e a quantidade de mortes de cada jogador;

Observa��es
-----------------------
* Assassinatos realizados pelo player <WORLD> devem ser desconsiderados.

B�nus
-----------------------
N�o obrigat�rio. Fa�a apenas caso se identifique com o problema ou se achar que h� algo interessante a ser mostrado na solu��o

* Descobrir a arma preferida (a que mais matou) do vencedor;
* Identificar a maior sequ�ncia de assassinatos efetuadas por um jogador (streak) sem morrer, dentro da partida;
* Jogadores que vencerem uma partida sem morrerem devem ganhar um "award";
* Jogadores que matarem 5 vezes em 1 minuto devem ganhar um "award".

SOLU��O
========
* Seja criativo;
* Explore ao m�ximo a orienta��o a objetos;
* Crie testes unit�rios e tente usar TDD;
* N�o � necess�rio utilizar nenhum framework, mas fica ao seu crit�rio;
* Caso voc� tenha baixado o arquivo zip, o projeto j� cont�m um reposit�rio git inicializado. Se voc� nunca usou git desconsidere isso.

ENTREGA
========
* Caso tenha feito um fork do nosso reposit�rio, nos envie a url do seu reposit�rio com a solu��o;
* Caso tenha baixado o arquivo zip, nos envie este mesmo projeto em formato compactado, com os seus fontes;
* Se voc� usar o controlador de vers�es git, enviar a pasta .git junto com o projeto compactado;
* Utilize o arquivo SUAS-INSTRU��ES.txt para adicionar algum coment�rio/observa��o que achar importante.