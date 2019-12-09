/*
Instituto Politécnico Nacional
Escuela Superior de Cómputo
Teoria Computacional
Grupo: 2CV1
Alumno: García Tello Axel
Profesor: Benjamin Luna Benoso
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
Lector de MT

Fecha: 24 de noviembre de 2019
*/

public class Analizador {
    private String estado;
    private String []cinta;
    private int ubicacion;
    
    public Analizador(){
        estado="0";
        ubicacion=3;
    }
    
    public void imprimirCinta(){
        int tamanio=cinta.length;
        for(int x=0;x<tamanio;x++){
            if(x==ubicacion){
                System.out.print("[q"+estado+"]");
            }
	    System.out.print(" ");
	    System.out.print(" ");
        }
        System.out.println("");
	System.out.print(" ");
        for(int x=0;x<tamanio;x++){
            if(x==ubicacion){
                System.out.print("| ");
            }
	    System.out.print(" ");
	    System.out.print(" ");
        }
        System.out.println("");
	System.out.print(" ");
        for(int x=0;x<tamanio;x++){
            if(x==ubicacion){
                System.out.print("v ");
            }
	    System.out.print(" ");
	    System.out.print(" ");
        }
        System.out.println("");
        System.out.print("|");
        for(int x=0;x<tamanio;x++){
            System.out.print(cinta[x]);
            System.out.print("|");
        }
        System.out.println("\n\n");
    }
    
    public void analizarCelda(Tabla tabla){
        int fila=0,
            columna=0;
        try{
            while(!(estado.equals(tabla.mostrarDato(fila,0)))){
                fila++;
            }
        }
        catch(ArrayIndexOutOfBoundsException aioobe){
            System.out.println("No se encontro una rama en "
                    + "el estado actual de la maquina de "
                    + "Turing\nLa maquina de Turing actual "
                    + "no acepta esta cadena.");
            System.exit(0);
        }
        switch(cinta[ubicacion]){
            case "0":
                columna=1;
            break;
            case "1":
                columna=4;
            break;
            case "b":
                columna=7;
            break;
            case "x":
                columna=10;
            break;
            case "y":
                columna=13;
            break;
        }
        estado=tabla.mostrarDato(fila, columna);
        cinta[ubicacion]=tabla.mostrarDato(fila, columna+1);
        if(tabla.mostrarDato(fila, columna+2).equals("d")){
            ubicacion++;
        }
        else{
            ubicacion--;
        }
    }
    
    public void analizarCadena(Tabla tabla, String cadena){
        int tamanio=cadena.length(),
            contador=0;
        tamanio+=6;
        cinta=new String[tamanio];
        cinta[0]="b";
        cinta[1]="b";
        cinta[2]="b";
        cinta[tamanio-1]="b";
        cinta[tamanio-2]="b";
        cinta[tamanio-3]="b";
        for(int x=3;x<(tamanio-3);x++){
            cinta[x]=cadena.charAt(contador)+"";
            contador++;
        }
        imprimirCinta();
        while(ubicacion<(tamanio-1)){
            analizarCelda(tabla);
            imprimirCinta();
            tabla.verificarEstado(estado);
        }
    }
    
}
