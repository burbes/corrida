
Algumas observações:
- Primeiramente, tentei implementar utilizar posição de linha para pegar os parametros, mas não consegui pois alguns lugares eram TAB, outros eram espaços, alguns casos ficaram confusos, o que dificultou saber a exata posição do parametro. Resultado, tive que refazer toda minha logica do zero. Depois de pensar um pouco, percebi que apesar do problema de espaçamento, seria possivel pegar por expressão regular, então montei um padrão para cada caso e consegui chegar nesta solução.
- Eu não soube se a velocidade seria em km/h ou m/s, então deduzi que eram Kmph.
- Reparei que o F.MASSA no tempo 23:52:17.003 estava com o nome F.MASS, o que dificultou na hora de posicionar a chave sendo o nome, então troquei a ordenação por Codigo do Corredor.
	
	
Para rodar a aplicação, basta buildar:
	
`mvn clean package`
	
e para executar:
	
`mvn exec:java`

o arquivo de log já está sendo passado por parametro nas configurações.
