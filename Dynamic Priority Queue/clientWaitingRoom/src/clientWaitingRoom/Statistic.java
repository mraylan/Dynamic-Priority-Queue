package clientWaitingRoom;

import clientWaitingRoom.HashTable.HashEntry;

public class Statistic {
	LinkedList listDay = null;
	ClientInfo client = null;
	
	int tam = 0;
	
	//constructor
	public Statistic (){	
		listDay = new LinkedList();
		client = null;
		//HashTable hashTable = new HashTable();// Coloca valores na hashTable (inicializa)
        
		// Adiciona em cada chave da hashTable um valor i
		for(int i=0; i < 16; i++) {
            final String key = String.valueOf(i);
            //hashTable.put(key, "", 0.0);
        }
		
	}
	
	// Classe para Lista Duplamente Encadeada
	// Armazena todos os clientes removidos da heap
	// A Classe também armazena uma Tabela Hash com os tipos de assunto
	// Cada chave da "hash" armazena um "tipo de assunto"
	// Para cada indice da "hash" temos armazenado:
	// 
	// Chave, Descrição do Assunto e , O PRINCIPAL, a soma dos tempos por tipo de assunto 
	public class LinkedList {
	    Node head = null;
	    HashTable hash;
	    int day;
	    
	    public LinkedList(){
	    	hash = new HashTable();
	    }
	    
	    class Node {	
	    	ClientInfo client;
	        Node prev;
	        Node next;
	        
	        Node(ClientInfo c) { 
	        	client = c; 
	        	//HashTable hashTable = new HashTable();
	        }
	    }
	    
	    public ClientInfo getItem(){
	    	return head.client;
	    }
	    
	    public void push(ClientInfo c, int day){
	        Node new_Node = new Node(c);
	        String key;
	        String description;
	        double duration;
	        
	        
	        new_Node.next = head;
	        new_Node.prev = null;
	     
	        //
	        if (head != null)
	            head.prev = new_Node;
	     
	        //
	        head = new_Node;
	        tam++;
	        
	        // Adicionando AS CHAVES com a LISTA DE ASSUNTOS do cliente "c"
	        
	        // Percorre a lista de assuntos do cliente e adiciona key, description, duration na HashTable
	        ///*
	        for(int j = 0; j < head.client.getSubjects().getSize(); j++) {
	        	key 		= String.valueOf( head.client.getSubjects().getType(j) );
	        	description = head.client.getSubjects().getDescription(j);
	        	duration 	= head.client.getSubjects().getTime(j);
	        
	        	hash.put(key, description, duration);
	        }
	        //*/
	    }
	    
	    // Calcula a média de tempo para o assunto do tipo "type" 
		public void getLastClients(int tam, int type){
			int count = 0;
			int qtd = 0;
			double value = 0;
			double average = 0;
			Node nodeAux = head;
			
			// Calculando a média de tempo para o Assunto com o tipo (type)
			if(head!=null) {
				
				System.out.printf("\n----------Clientes Removidos do HEAP----------\n\n");
				// Temos "tam" clientes para avaliar
				for(int i = 0; i < tam ; i++) {
					
					client = nodeAux.client;
					
					//Tempo total de serviço do cliente, ou seja, inclui TODOS os serviços
					value += client.getServiceTime();
					
					// Cada cliente possui uma quantidade de "x" de assuntos
					// x = client.getSubjects().getSize()
					for(int j = 0; j < client.getSubjects().getSize(); j++) {
						if(client.getSubjects().getType(j) == type) {
							// Caso o serviço seja do tipo "type" , então soma em "average" o tempo do serviço "j"
							average += client.getSubjects().getTime(j);
							qtd++;
						}
					}
					
					// Imprime as informações do cliente corrente
					client.print();
					
					//System.out.printf("Cliente " + client.getName() + " atendido p["  + client.getPriority() + "] " + "Tempo: " +client.getServiceTime() + "\n");
					nodeAux = nodeAux.next;
					if(nodeAux==head)
						i = tam;
				}
			}
			System.out.printf("\nMedia Geral: " + value/tam + "\nMedia de tempo do Serviço Tipo ["  + type + "]: " + average/qtd + "\n\n");
			
			//Lista a frequencia em todas as chaves UTILIZADAS/ATIVAS na hash 
			findType();
		}
		
		public void findType(){
			LinkedListStrings keys = hash.getUsedKeys();
			String key = keys.getNext();
            while( key != null ) {
            	System.out.printf("Media de tempo do Serviço Tipo "  + key + ": " + hash.getTime( String.valueOf( key ) ) + "\n");
            	key = keys.getNext();
            }
            hash.resetIT();
		}
	}
	
	//getter
	public void getClients(int type, int day){
		if( tam > 0 )
			listDay.getLastClients(tam, type);
	}
	
	//setter
	public void addClient(ClientInfo c, int day){
		listDay.push(c, day);
	}
	
	//methods
	public void addType(String key, String description, double duration, int day){
		//hashTable.put(key, description, duration);
	}
}
