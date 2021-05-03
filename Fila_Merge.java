class Main
{
    public static void main(String[] args) throws Exception
    {
        Fila arnoldSwhasnegger = new Fila(5);
        arnoldSwhasnegger.insere(1);
        arnoldSwhasnegger.insere(3);
        arnoldSwhasnegger.insere(5);
        arnoldSwhasnegger.imprime();
        Fila albertEinstein = new Fila(5);
        albertEinstein.insere(2);
        albertEinstein.insere(4);
        Merge casamento = new Merge();
        Fila albertSwhasnegger = casamento.OperacaoDeMerge(arnoldSwhasnegger,albertEinstein);
        albertSwhasnegger.imprime();
    }
}

class Fila {
    //indice do primeiro elemento
    private int primeiro;
    //indice do ultimo elemento
    private int ultimo;
    //armazenamento de dados
    private final int[] dados; 

    Fila(int tamanhoDaFila) {
        primeiro = 0;
        ultimo = -1;
        dados = new int[tamanhoDaFila];
    }

    public int tamanhoDaFila(){
        return ultimo - primeiro +1;
    }

    public void filaResetar(){
        primeiro = primeiro - dados.length*2;
        ultimo -= dados.length*2;
    }

    public boolean filaCheia(){ //Verifica se a fila esta cheia
        return  ultimo - primeiro == dados.length -1;
    }

    public boolean filaVazia(){ //Verifica se a fila esta vazia

        return primeiro -1 == ultimo;
    }

    public void insere(int elemento) throws Exception{ //Insere elemento na fila
        if (filaCheia()){
            throw new Exception("A fila se encontra cheia");
        }
        else{
            ultimo ++;
            dados[ultimo % dados.length] = elemento;
        }
    }

    public int remove() throws Exception{ //Remove elementos da fila
        if (filaVazia()){
            throw new Exception("A fila se encontra vazia");
        }
        else{
            int removido = dados[primeiro % dados.length];
            primeiro++;
            if(primeiro >= dados.length*2)
                filaResetar();
            return removido;
        }
    }

    public void imprime() throws Exception{
        if (filaVazia()){
            throw new Exception("Nao foi possivel retornar um elemento da Fila, por que ela se encontra vaiza");
        }
        else{
            for(int i = primeiro; i <= ultimo; i++){
                System.out.print("  " + dados[i % dados.length]);
                if (i == ultimo)
                    System.out.println("  ");
            }
        }
    }

}

class Merge{
    public Fila OperacaoDeMerge(Fila FilaA, Fila FilaB) throws Exception{

        if (FilaA.filaVazia())
            return FilaB;
        if (FilaB.filaVazia())
            return FilaA;
        Fila FilaFinal = new Fila(FilaA.tamanhoDaFila() + FilaB.tamanhoDaFila());
        int elementoA = FilaA.remove();
        int elementoB = FilaB.remove();

        // Fila FilaFinal = new Fila(FilaA.TamanhoDaFila() + FilaB.TamanhoDaFila());
        // aqui nao funciona, pq os elementoA e elementoB estao retirariam os elementos
        // da FilaFinal

        while(true){
            System.out.println(elementoA+"  "+elementoB);

            if(elementoA <= elementoB){
                FilaFinal.insere(elementoA);
                if(FilaA.filaVazia()){
                    FilaFinal.insere(elementoB);
                    return acrescentar(FilaFinal, FilaB);
                }
                elementoA = FilaA.remove();
            }
            else{
                FilaFinal.insere(elementoB);
                if(FilaB.filaVazia()){
                    FilaFinal.insere(elementoA);
                    return acrescentar(FilaFinal, FilaA);
                }
                elementoB = FilaB.remove();
            }
            FilaFinal.imprime();
        }
    }
    private Fila acrescentar(Fila FilaFinal, Fila FilaAux) throws Exception{
        while (true){
            if(FilaAux.filaVazia())
                return FilaFinal;

            var removido = FilaAux.remove();
            FilaFinal.insere(removido);
        }
    }
}

