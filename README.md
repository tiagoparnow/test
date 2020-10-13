# Processador Análise de Vendas
## Repositório para avaliação técnica

###### Descrição
Sistema de análise de dados de venda que irá importar lotes de arquivos e produzir um relatório baseado em informações presentes no mesmo.

###### Como funciona
O sistema irá ler arquivos no formato **TXT** no diretório de entrada padrão denominado *HOMEPATH/data/in*, e resultará em um relatório final também no formato **TXT** no diretório de saída padrão denominado *HOMEPATH/data/out*. Este relatório apresentará dados no mesmo formato presente nos arquivos de entrada, porém será adicionado ao nome o sufixo *_process*.</br>

Um ponto a ser destacado é que o sistema poderá identificar possíveis erros identificados ao realizar a leitura das linhas do arquivo, caso encontre será gerado um relatório final com o sufixo *_error*.</br>

**Exemplos**

Arquivo: teste.txt</br>
Diretório de Entrada: HOMEPATH/data/in/teste.txt</br>
Diretório de Saída: HOMEPATH/data/out/teste_process.txt</br>

Arquivo com erro: teste_fail.txt</br>
Diretório de Entrada: HOMEPATH/data/in/teste_fail.txt</br>
Diretório de Saída: HOMEPATH/data/out/teste_fail_process.txt</br>
Diretório de Saída: HOMEPATH/data/out/teste_fail_error.txt</br>

*Importante:*

- Os arquivos com o status de process (_process) serão ignorados na pasta de entrada, já que os mesmos já foram devidamente processados. Sendo assim o sistema irá aguardar novos arquivos para uma nova análise.
- Os diretórios serão criados de forma automática. O sistema identificará que a pasta não existe, então será criada através do HOMEPATH detectado do próprio sistema operacional ou aplicação que irá rodar o processador.
- O sistema irá identificar de forma automática os arquivos dentro da pasta de entrada. Com isto, basta criar novos arquivos dentro da pasta de entrada para que o sistema faça a devida análise.

## Bibliotecas utilizadas
- Maven
- Spring Boot
- JUnit
- Docker

## Maven Build

Insira o comando no diretório da aplicação

>$ mvn install

**Ao realizar o build com sucesso, agora a aplicação está apta a ser executada através do executável jar ou até via docker, veja a seguir.**

## Executando arquivo jar

**Pré-requisito: Maven Build**

Para iniciar a aplicação insira o seguinte comando

>$ java -jar <pasta_aplicacao>/target/processador-analise-vendas-1.0.0-SNAPSHOT.jar

## Instalação via Docker

**Pré-requisito: Maven Build** 

1. Acesse o diretório da aplicação
2. Crie imagem via Dockerfile
>$ docker build -t processador-analise-vendas .
3. Execute a imagem criada
>$ docker run -p 8080:8080 processador-analise-vendas

**Conteúdo do arquivo Dockerfile:** Além de possuir a criação da imagem, o arquivo também conta com a criação das pastas de entrada e saída e a cópia de arquivos de testes. Sendo assim o sistema irá inicializar executando os arquivos adicionados na pasta HOMEPATH/data/in através da configuração via dockerfile.

###### Adicionando arquivos no container
1. Acesse o prompt
2. Verifique o nome do container da imagem criada
>$ docker ps
O container está destacado pelo **NAME** da **IMAGE** processador-analise-vendas
3. Copie arquivos do diretório local para o container
>$ docker cp <diretorio_local>\teste8.txt <container_name>:/root/data/in

###### Docker Desktop
Através do Docker Desktop é possível verificar os arquivos tanto de entrada como de saída, acessando via prompt pode-se executar os seguintes comandos:

1. Listar arquivos de entrada
>$ cd /root/data/in</br>
>$ ls</br>

2. Listar arquivos de saída
>$ cd /root/data/out</br>
>$ ls</br>

3. Mostrar resultado do arquivo de saída
>$ cd /root/data/out</br>
>$ ls</br>
>$ cat teste1_process.txt</br>
