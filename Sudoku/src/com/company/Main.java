    package com.company;

    import java.io.*;
    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) throws FileNotFoundException, IOException {
            // abre o arquivo entrada.txt
            // bufferiza a entrada do arquivo para ser possivel ler uma linha inteira no arquivo

            // le uma linha do arquivo de entrada


            // quebra a linha lida em funcao dos espacos encontrados na linha
            // e retorna um vetor de string

            //Função que chama todas as outras funcoes
            game();

        }

        //Função que cria o vetor em char do sudoku
        public static void game() throws IOException {
            Scanner ler = new Scanner(System.in);
            char tabuleiro[][] = new char[9][9];
            //tabuleiro inicial me da informações sobre quais são os numeros pre dispostos, aqueles qua eu não posso alterar
            char tabuleiroInicial [][] = new char [9] [9];
            char jogada;
            int condenadax, condenaday;


            initialize(tabuleiro);

            initialize(tabuleiroInicial);

            //conferindo se o player ganhou o sudoko se for true continua se for false o jogador venceu o desafio
            while(status(tabuleiro)) {

                print(tabuleiro);

                System.out.println("Digite a cordenada X");

                condenadax = ler.nextInt();


                System.out.println("Digite a cordenada Y");

                condenaday = ler.nextInt();

                System.out.println("Digite o Numero que deseja incerir");

                //Já inicio a jogada do player como uma variavel Char
                jogada = ler.next().charAt(0);


                //Chamando a função Step que irá avaliar a jogado do player
                int Ostep = step(tabuleiro, jogada, condenadax, condenaday,tabuleiroInicial);

                if (Ostep == 1){
                    System.out.println("Jogada efetuada com sucesso");
                    tabuleiro[condenaday - 1][condenadax - 1] = jogada;
                }
                if (Ostep == 0){
                    System.out.println("O valor já esta presnete na linha, tente outra jogada");
                }
                if (Ostep == -1){
                    System.out.println("Linha ou coluna inexistentes ou numero inerente do sudoko");
                }

                System.out.println("fim do programa.");
            }


            //printo por uma ultima vez para mostrar o sudoko completo fora do game loop
            print(tabuleiro);

            System.out.println("Meus Parabéns Você GANHOU !!!");
            System.out.println("Agora Vamos Tomar uma Breja pq Depois de Tudo Isso eu to Merecendo");


        }


        //Função que inicializa a matriz pegando o arquivo .txt dentro da matriz só há characters de numeros e '_'
        public static char[][] initialize(char tabuleiro[][]) throws FileNotFoundException, IOException {

            FileReader le = new FileReader("Sudoko.txt");
            BufferedReader leBuffer = new BufferedReader(le);

            for (int i = 0; i < 9; i++) {
                String linha = leBuffer.readLine();
                String vetorStringLinha[] = linha.split(" ");
                for (int j = 0; j < vetorStringLinha.length; j++) {
                    tabuleiro[i][j] = vetorStringLinha[j].charAt(0);
                    //System.out.print(tabuleiro[i][j]+" ");
                }
                //System.out.println();
            }

            return tabuleiro;

        }

        //Função que imprime o sudoku de um jeito bonito
        public static void print (char tabuleiro[] []){

            //Isso faaz com que os numeros apareção no inicio da tabela de sudoko
            System.out.print("   ");
            for (int k = 0; k < tabuleiro.length; k++){
                if (k == 3 || k == 6){
                    System.out.print("  ");
                }
                System.out.print(k+1+" ");
            }
            System.out.println();

            for (int i = 0; i < tabuleiro.length; i++) {
                //Esse pequeno System Print faz com que apareca os numeros do lado esquerdo da tabela sudoko
                System.out.print(i+1+"- ");

                //printa de u jeito bonitinho
                for (int j = 0; j < tabuleiro[0].length; j++) {
                    System.out.print( tabuleiro[i][j] + " ");
                    if (j == 2 || j == 5){
                        System.out.print("| ");
                    }
                }
                System.out.println();
                if(i==2 || i == 5){
                    System.out.println();
                }
            }
        }

        public static int step(char tabuleiro[][], char jogada, int cordenadax, int cordenaday, char tabuleiroInicial [] []){
            //Conferindo se o numero que o jogador quer alterar já não está presente dentro do arquivo txt e conferindo
            // a posição que o player deseja colocar uma jogada

            if(cordenadax-1>9||cordenaday-1>9||tabuleiroInicial[cordenaday - 1][cordenadax - 1] != '_'){
                return -1;
            }
            else{
                for (int j = 0; j < tabuleiro[0].length; j++) {
                    //regras do sudoko sendo implementadas verificando colunas linhas e quadrantes.
                    if (jogada == tabuleiro[j][cordenadax - 1] || jogada == tabuleiro[cordenaday - 1][j] || zonas(tabuleiro, cordenadax, cordenaday, jogada) ) {
                        if (jogada == '_') {
                            return 1;
                        }
                        return 0;
                    }
                }
            }
        return 1;
        }

        public static boolean zonas (char tabuleiro [] [], int cordenadax, int cordenaday, int jogador){

            boolean igual = false;

            int l = 2;
            for (int k=0; k<=6;k=k+3){
                int j=2;
                for(int i=0; i<=6;i=i+3){
                    //If responsavel por determinar em qual quadrante a jogada do jogador esta
                    if (k <= cordenaday-1 && cordenaday-1 <=l  && i<= cordenadax-1 && cordenadax-1 <=j){
                        for(int m = k; m<=l;m++){
                            for(int n=i; n<=j; n++) {
                                // Após reconhecer o quadrante esses dois "for" servem para conseguir comparar cada parte do quadrante
                               if(jogador == tabuleiro[m][n]) {
                                   igual = true;
                                }
                            }
                        }
                    }
                    j=j+3;
                }
                l=l+3;
            }

            return igual;

        }

        public static boolean status (char tabuleiro [][]){
            boolean jogo = false;

            for(int i=0;i<tabuleiro.length;i++){
                for(int j=0;j<tabuleiro[0].length;j++){
                    //caso o programa não passe por esse if o jogador venceu
                    if(tabuleiro[i][j]=='_'){
                        jogo = true;
                    }
                }
            }

            return jogo;
        }
    }

