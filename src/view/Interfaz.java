package view;

import java.io.IOException;
import java.util.Scanner;

import model.logic.LectorArchivo;
import Controller.Controller;


public class Interfaz {


	public static void main (String[] args) {



		Scanner sc = new Scanner(System.in);
		boolean fin=false;

		while(!fin) {
			printMenu();

			int option = sc.nextInt();
			switch(option)
			{

			case 1:
				try {
					Controller.leerArchivo("/Users/juanestebanmendez/Documents/Los Andes/Lenguajes y Maquinas/workspace/Proyecto 0/test.bib");
					boolean segundoFin = false;
					while(!segundoFin) {
						printSubMenu();

						int secondOption = sc.nextInt();

						switch (secondOption) {
						case 1:
							Controller.mostrarTiposBibliografiasValidas();
							break;
						case 2:
							Controller.mostrarAnalisisCamposReqYOpc();
							break;
						case 3:
							Controller.mostrarErrores();
							break;
						case 4:
							segundoFin= true;
							//sc.close();
							break;
						}
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2: //salir
				fin=true;
				sc.close();
				break;

			}
		}

	}



	private static void printMenu() {
		// TODO Auto-generated method stub
		System.out.println("---------------LENGUAJES Y MAQUINAS-----------------");
		System.out.println("----------------Lector Archivos Bib-----------------");
		System.out.println("---------------------Project 0----------------------");
		System.out.println();
		System.out.println("1. Leer archivo.");
		System.out.println("2. Salir.");

	}

	private static void printSubMenu() {
		System.out.println("1. Mostrar tipos de bibliografias validas.");
		System.out.println("2. Análisis de campos requeridos y campos opcionales.");
		System.out.println("3. Mostrar errores de formato.");
		System.out.println("4. Volver.");
	}
}
