package Controle;
import java.util.Scanner;

public class Prefeitura {

	public static Scanner e = new Scanner(System.in);
	public static int tam = 20;
	public static  Pessoa vPessoa[] = new  Pessoa[tam];
	public static int ind = 0;		 	
	
	public static void main(String[] args) {
		while (true) {
			switch (menu()) {
			case '1':	cadastra();			break;					
			case '2':	relatorio();
						    reinicia();			break;	
			case '3':	sair();					break;	
			}			
		}
	}//--------------------------------
	
	public static char menu() {		
		char a = ' ';
		System.out.println("\n\nMENU"+
		  "\n------------------------------------------------------------"+
		  "\n1 - Digitar dados"+
		  "\n2 - Relatório"+
		  "\n3 - Sair");
		do {
			System.out.print("\ndigite a opção do MENU: ");
			a = e.next().toLowerCase().charAt(0);			
			if (a != '1' && a != '2' && a != '3') 
				System.out.println("ERRO! - Digite uma opção válida!");				
		} while (a!= '1' && a != '2' && a != '3');
		return a;
	}//--------------------------------
	
	public static String digita(String m){		
		System.out.print(m);		
		return e.next();
	}//--------------------------------
	
	public static void sair() {	
		System.out.println("Fim da aplicação!");
		System.exit(0);		
	}//--------------------------------
	
	public static void cadastra() {	
		boolean f = false;
		if (ind<tam) {
			vPessoa[ind] = new Pessoa();
			vPessoa[ind].setNome(digita("Digite o nome: "));
			do {
				try {
					vPessoa[ind].setIdade(Integer.parseInt(digita("Digite a idade: ")));
					f = false;
				} catch (NumberFormatException e) {
				    System.out.println("Por favor, digite apenas números!");
				    f = true;
				}	
			} while (f);				
		do {
			vPessoa[ind].setSexo(digita("Digite o sexo (m/f): ").toLowerCase().charAt(0));
			if (vPessoa[ind].getSexo() == 'm' || vPessoa[ind].getSexo() == 'f') {
				break;
			}			
		} while (true);
			vPessoa[ind++].setSalario(Double.parseDouble(digita("Digite o Salário: ")));
		}else
			System.out.println("Cadastro cheio!");			
	}//--------------------------------
	
	public static void mostrarHabitantes(Pessoa vet[], String m, Pessoa vet1[], String m1, Pessoa vet2[], String m2, Pessoa vet3[], String m3) {
		System.out.println("\n==== Habitantes Pesquisados ====");		
		for (int i = 0; i < ind; i++) {
			System.out.print(m+": "+vet[i].getNome()+" | "+m1+": "+vet1[i].getIdade()+" | "+m2+": "+vet2[i].getSexo()+" | "+m3+": "+vet3[i].getSalario()+" | \n");
		}
	}//--------------------------------
	
	public static void relatorio(){	
		if (ind > 0) {
			mostrarHabitantes(vPessoa, "Nome", vPessoa, "Idade", vPessoa, "Sexo", vPessoa, "Salario R$");
			System.out.println("\n\na) Média de salário da população: R$"+mediaSal()+
							           "\nb) Na pesquisa realizada com "+ind+" pessoas, "+homens()+" são homens e "+mulheres()+" são mulheres,"+
							           "\nsendo que a média de idade dos homens é "+mediaIdades('m', homens())+" anos e das mulheres "+mediaIdades('f', mulheres())+" anos."+
						        	   "\nc) Percentual de Homens e Mulheres entre a faixa salarial: ");
			perSalario('m', "homens", homens());
			perSalario('f', "mulheres", mulheres());
		}else
			System.out.println("Não há ninguém cadastrado!");
		
		
	}//--------------------------------
	
	public static double mediaSal(){
		double media = 0;
		for (int i = 0; i < ind; i++) {
			media += vPessoa[i].getSalario();
		}
		return media/ind;		
	}//--------------------------------
	
	public static int homens(){
		int homens = 0;
		for (int i = 0; i < ind; i++) {
			if (vPessoa[i].getSexo() == 'm') {
				homens ++;
			}		
		}
		return homens;	
	}//--------------------------------
	
	public static int mulheres(){
		int mulheres = 0;
		for (int i = 0; i < ind; i++) {
			if (vPessoa[i].getSexo() == 'f') {
				mulheres ++;
			}		
		}
		return mulheres;	
	}//--------------------------------
	
	public static void perSalario(char sexo, String mens, int pessoa){
		int percent499 = 0;
		int percent1499 = 0;
		int percentAcima = 0;
		for (int i = 0; i < ind; i++) {
			if (vPessoa[i].getSexo() == sexo) {
				if (vPessoa[i].getSalario() >= 0 && vPessoa[i].getSalario() < 500) {
					percent499++;
				}else if(vPessoa[i].getSalario() >= 500 && vPessoa[i].getSalario() < 1400) {
					percent1499++;
				}else if(vPessoa[i].getSalario() >= 1400) {
					percentAcima++;
				}
			}
		}		
		if (pessoa !=0)	mostraPercentSal(percent499, percent1499, percentAcima, mens, pessoa);		
		else System.out.println("Sem salario p este sexo.");						
	}//-------------------------------- 
	
	public static void mostraPercentSal(int num, int num2, int num3, String m, int pessoa){		
		System.out.println("Percentual de "+m+" com salário [0,00 – 499,99]: "+((num*100)/pessoa)+"%"+
						           "\nPercentual de "+m+" com salário [500,00 – 1.399,99]: "+((num2*100)/pessoa)+"%"+
						           "\nPercentual de "+m+" com salário [1.400,00 – acima]: "+((num3*100)/pessoa)+"%");		
	}//-------------------------------- 
	
	public static double mediaIdades(char sexo, int num){
		double media = 0;
		for (int i = 0; i < ind; i++) {
			if (vPessoa[i].getSexo() == sexo)
			media += vPessoa[i].getIdade();
		}
		try {
			media = media/num;
		} catch (ArithmeticException e) {
			
		}		
		return media;			
	}//-------------------------------- 
	
	public static void reinicia(){
		if (ind != 0)		
			ind = 0;			
	}//-------------------------------- 
}//-------------------------------- final classe
