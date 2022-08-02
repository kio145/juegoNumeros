//La clase que nos permitirá manejar colores es la clase Color, en java. awt. 
import java.awt.Color;
//La clase Font nos permite cambiar las fuentes y estilo de letras
import java.awt.Font;
//La clase GridBagConstraints especifica restricciones para los componentes que se distribuyen mediante la clase 
import java.awt.GridBagConstraints;
//La clase GridLayout representa un administrador de diseño con un número específico de filas y columnas en una cuadrícula rectangular
import java.awt.GridBagLayout;
// Un evento ActionEvent se produce: al pulsar un botón. al hacer doble clic en un elemento de lista.
import java.awt.event.ActionEvent;
//Los Listeners se encargan de controlar los eventos, esperan a que el evento se produzca y realiza una serie de acciones.
import java.awt.event.ActionListener;
import java.io.Serializable;
/*Para mostrar una imagen como icono podemos acudir a la clase ImageIcon del paquete javax.swing,
 * con ello solo le debemos pasar la ruta de la imagen y el nombre del archivo.
 */
import javax.swing.ImageIcon;
//La clase JButton proporciona un botón visible que se puede añadir a cualquier interfaz de usuario ajustando la apariencia y comportamiento.
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//JOptionPane es una clase que nos provee una conjunto de ventanas de dialogo que es ideal, para mostrar mensajes al usuario
import javax.swing.JOptionPane;
/*Jpanel es una herramienta util que tiene objetos contenedores que a su vez agrupan otros objetos 
 * tales como botones,etiquetas,selectores,campos para texto,etc.
 */import javax.swing.JPanel;

public class tabla extends JPanel implements Serializable{
    
private int tamanioMatriz[][];
private GridBagConstraints gbc = new GridBagConstraints();
private JButton[][] numeros;
private JButton[] botones;
private ImageIcon iconoPremio = new ImageIcon(Menu.class.getResource("/trofeo.png"));
private ImageIcon iconoGameOver = new ImageIcon(Menu.class.getResource("/game-over.png"));
private int columnas;
private JFrame juego;
private int limite;
Opciones aux;

public tabla(int c, JFrame v,int l){
    super();
    juego=v;
    limite=l;
    this.tamanioMatriz = new int[c][c];
    this.setLayout(new GridBagLayout());
    this.numeros= new JButton[c][c];
    this.botones= new JButton[c];
    columnas=c;
    aux=new Opciones(0,(int) Math.pow(2, columnas+7),columnas,juego,0,limite);
    inicializaComponentes();
        aux.setVisible(true);
        aux.cambiarTitulo(c,c);
        aux.cambiarPuntuacion(ControladorDatos.getController().getMemoria().getPuntuacion());
}

private int generarNumero(){ 
    int potencia = (int) (Math.random() * 6) + 1;
    return (int) Math.pow(2, potencia);
    
}

/** private int generarNumero(int n){ 
        int num=n;
        int aux=n;
    
        suma=n+aux;
        return suma;
    } 
 **/

private Color colorFondo(int valor){
switch(valor){
case 2: 
    return Color.RED;
case 4: 
    return Color.BLUE;
case 8: 
    return Color.GREEN;
case 16: 
    return Color.GRAY;
case 32: 
    return Color.BLACK;
case 64: 
    return Color.YELLOW;
case 128: 
    return Color.ORANGE;
case 256: 
    return Color.MAGENTA;
case 512: 
    return Color.CYAN;
case 1024: 
    return Color.LIGHT_GRAY;
case 2048: 
    return Color.PINK;
case 4096:
    return Color.DARK_GRAY;
case 8192:
    return Color.BLACK;
default:return Color.WHITE;
}
}
/*
 * Metodo que verifica el valor que se desea alcanzar
 */
private boolean valor_encontrado() {
boolean valor_encontrado = false;
boolean salir=false;
for (int i = 0; i < tamanioMatriz.length && salir; i++) {
for (int j = 0; j < tamanioMatriz[0].length && salir; j++) {
if (tamanioMatriz[i][j] >= (int) Math.pow(2,columnas+7)){
valor_encontrado = true;
salir=true;
}
}
}
return valor_encontrado;
}


private boolean tablaLlena(){
boolean lleno=true;
boolean salir=false;
for (int i=0; i<tamanioMatriz.length && !salir;i++){
for (int j=0; j<tamanioMatriz[0].length && !salir; j++){
if (tamanioMatriz[i][j]== 0){
lleno=false;
salir=true;
}
}
}
return lleno;
}

private void inicializaComponentes(){
    
for (int i=0;i<tamanioMatriz[0].length;i++){
for(int j=0;j<tamanioMatriz[0].length;j++) {
numeros[i][j]=new JButton((tamanioMatriz[i][j])!=0?Integer.toString(tamanioMatriz[i][j]):"");
gbc.gridx=j;
gbc.gridy=i;
gbc.gridwidth=1;
gbc.gridheight=1;
gbc.weightx=1.0;
gbc.weighty=1.0;
gbc.fill=GridBagConstraints.BOTH;
this.add(numeros[i][j],gbc);
}
}
int numero=generarNumero();
System.out.println("Ha salido el numero "+ numero);

for (int i=0; i<tamanioMatriz[0].length;i++){
botones[i]=new JButton("");
botones[i].addActionListener(listener);
gbc.gridx=i;
gbc.gridy=tamanioMatriz[0].length+2;
gbc.gridwidth=1;
gbc.gridheight=1;
gbc.weightx=1.0;
gbc.weighty=1.0;
gbc.fill=GridBagConstraints.BOTH;
gbc.anchor = GridBagConstraints.PAGE_END;
this.add(botones[i],gbc);
}
botones[(tamanioMatriz[0].length-1)/2].setFont(new Font("Arial", Font.BOLD, 25));
botones[(tamanioMatriz[0].length-1)/2].setForeground(Color.WHITE);
botones[(tamanioMatriz[0].length-1)/2].setBackground(colorFondo(numero));
botones[(tamanioMatriz[0].length-1)/2].setText(Integer.toString(numero));
}

private void actualizarNumeros(){
for (int i=0;i<tamanioMatriz[0].length;i++){
for(int j=0;j<tamanioMatriz[0].length;j++) {
numeros[i][j].setFont(new Font("Arial", Font.BOLD, 25));
numeros[i][j].setForeground(Color.WHITE);
numeros[i][j].setBackground(colorFondo(tamanioMatriz[i][j]));
numeros[i][j].setText((this.tamanioMatriz[i][j])!=0?Integer.toString(this.tamanioMatriz[i][j]):"");
}
}
}

private void actualizarBotones(){
int numero=generarNumero();
System.out.println("Ha salido el numero "+ numero);

for (int i=0; i<tamanioMatriz[0].length;i++){
botones[i].setEnabled(true);
}
botones[(tamanioMatriz[0].length-1)/2].setBackground(colorFondo(numero));
botones[(tamanioMatriz[0].length-1)/2].setText(Integer.toString(numero));
}

private ActionListener listener= new ActionListener() {
@Override
public void actionPerformed(ActionEvent ae) {
int i=0;
boolean salir=false;
if (valor_encontrado()==false && tablaLlena()==false){
for (i=0;i<tamanioMatriz.length&&(salir==false);i++){
if(ae.getSource().equals(botones[i]))
salir=true;
}
System.out.println("Vas a colocarlo en la columna "+ (i));
for (int j=0; j<tamanioMatriz[0].length;j++){
botones[j].setEnabled(false);
}
realizarMovimiento(i-1);
}else{
if(valor_encontrado()){
    if(ControladorDatos.getController().getMemoria().getNivel()<10){
        ControladorDatos.getController().getMemoria().setPuntuacion(aux.getPuntucion());
        ControladorDatos.getController().getMemoria().setNivel(ControladorDatos.getController().getMemoria().getNivel()+1);
        ControladorDatos.getController().guardarObjeto();
        int columnas=ControladorDatos.getController().getMemoria().getNivel();
        JFrame app=new JFrame("Mi juego de Numeros");
        tabla t= new tabla(columnas, app,limite);
        t.imprimeTodo();
       
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(892, 520);
        app.setLocationRelativeTo(null);
        app.add(t);
        app.setVisible(true);
        aux.setVisible(false);
        juego.setVisible(false);
    }
}
else{
    game_over fin=new game_over(columnas,columnas,(int) Math.pow(2, columnas+7),0,0,limite);
    fin.setVisible(true);
    ControladorDatos.getController().getMemoria().setRanking(aux.getPuntucion());
    ControladorDatos.getController().guardarObjeto();
    aux.setVisible(false);
    juego.setVisible(false);
}
}

}
};



private void imprimeEncabezado() {
for (int i = 0; i < tamanioMatriz[0].length; i++) {
System.out.printf(" %4d ", i);
}
System.out.println();
System.out.print("---------");
for (int i = 0; i < tamanioMatriz[0].length; i++) {
System.out.print("-----");
}
System.out.println();
}

private void imprimetabla() {
for (int i=0; i<tamanioMatriz.length; i++) {
for (int j=0; j < tamanioMatriz[i].length; j++)
if (tamanioMatriz[i][j] == 0)
System.out.print(" ");
else
System.out.printf(" %4d ",tamanioMatriz[i][j]);
System.out.println();
}
}

public void imprimeTodo() {
imprimeEncabezado();
imprimetabla();
}


private boolean columnaLlena(int c) {
boolean columnaLlena = true;
boolean salir=false;
for (int i = 0; i < this.tamanioMatriz.length && !salir; i++) {
if (this.tamanioMatriz[i][c] == 0)
{
columnaLlena = false;
salir=true;
}
}
return columnaLlena;
}

private void realizarMovimiento(int c) {
if(aux.getPuntucion()>= (int) Math.pow(2, columnas +7) ){
    if(ControladorDatos.getController().getMemoria().getNivel()<10){
        ControladorDatos.getController().getMemoria().setPuntuacion(aux.getPuntucion());
        ControladorDatos.getController().getMemoria().setNivel(ControladorDatos.getController().getMemoria().getNivel()+1);
        ControladorDatos.getController().guardarObjeto();
        int columnas=ControladorDatos.getController().getMemoria().getNivel();
        JFrame app=new JFrame("Mi juego de Numeros 6");
        tabla t= new tabla(columnas, app,limite);
        t.imprimeTodo();
       
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(892, 520);
        //app.setLocationRelativeTo(null);
        app.setLocation(10,100);
        app.add(t);
        app.setVisible(true);
        aux.setVisible(false);
        juego.setVisible(false);
    }
}
if (columnaLlena(c)==false){
int i = 0;
while (tamanioMatriz[i][c] != 0) {
i++;
}
tamanioMatriz[i][c] = Integer.parseInt(botones[(tamanioMatriz[0].length-1)/2].getText());
imprimeTodo();
actualizarNumeros();
sumarValores(i, c);
actualizarBotones();
}else{
    game_over fin=new game_over(columnas,columnas,(int) Math.pow(2, columnas+7),0,0,limite);
    fin.setVisible(true);
        ControladorDatos.getController().getMemoria().setRanking(aux.getPuntucion());
    ControladorDatos.getController().guardarObjeto();
    aux.setVisible(false);
    juego.setVisible(false);
}
}


private void sumarValores(int f, int c) {
//fusion total
if ((c!=0)&&(c<tamanioMatriz[0].length-1)&&(f!=0)&&(tamanioMatriz[f][c]==tamanioMatriz[f-1][c])&&((tamanioMatriz[f][c]==tamanioMatriz[f][c-1])&&((tamanioMatriz[f][c]==tamanioMatriz[f][c+1])))) {
tamanioMatriz[f-1][c]=tamanioMatriz[f][c]*8;
aux.cambiarPuntuacion(tamanioMatriz[f][c]*8);
for (int i=f; i< tamanioMatriz.length-1; i++){
tamanioMatriz[i][c-1]= tamanioMatriz[i+1][c-1];
tamanioMatriz[i][c]= tamanioMatriz[i+1][c];
tamanioMatriz[i][c+1]= tamanioMatriz[i+1][c+1];
}
tamanioMatriz[tamanioMatriz.length-1][c-1]=0;
tamanioMatriz[tamanioMatriz.length-1][c]=0;
tamanioMatriz[tamanioMatriz.length-1][c+1]=0;
imprimeTodo();
actualizarNumeros();
sumarValores(f-1,c);
if (tamanioMatriz[f][c-1]!=0)
sumarValores(f,c-1);
if (tamanioMatriz[f][c]!=0)
sumarValores(f,c);
if (tamanioMatriz[f][c+1]!=0)
sumarValores(f,c+1);
}
//fusion tres numeros uno arriba y izd
else if ((c!=0)&&(f!=0)&&(tamanioMatriz[f][c]==tamanioMatriz[f-1][c])&&((tamanioMatriz[f][c]==tamanioMatriz[f][c-1]))) {
tamanioMatriz[f-1][c]=tamanioMatriz[f][c]*4;
aux.cambiarPuntuacion(tamanioMatriz[f][c]*4);
for (int i=f; i< tamanioMatriz.length-1; i++){
tamanioMatriz[i][c-1]= tamanioMatriz[i+1][c-1];
tamanioMatriz[i][c]= tamanioMatriz[i+1][c];
}
tamanioMatriz[tamanioMatriz.length-1][c-1]=0;
tamanioMatriz[tamanioMatriz.length-1][c]=0;
imprimeTodo();
actualizarNumeros();
sumarValores(f-1,c);
if (tamanioMatriz[f][c-1]!=0)
sumarValores(f,c-1);
if (tamanioMatriz[f][c]!=0)
sumarValores(f,c);
}
//fusion tres numeros arriba derecha
else if((c<tamanioMatriz[0].length-1)&&(f!=0)&&(tamanioMatriz[f][c]==tamanioMatriz[f-1][c])&&((tamanioMatriz[f][c]==tamanioMatriz[f][c+1]))) {
tamanioMatriz[f-1][c]=tamanioMatriz[f][c]*4;
aux.cambiarPuntuacion(tamanioMatriz[f][c]*4);
for (int i=f; i< tamanioMatriz.length-1; i++){
tamanioMatriz[i][c]= tamanioMatriz[i+1][c];
tamanioMatriz[i][c+1]= tamanioMatriz[i+1][c+1];
}
tamanioMatriz[tamanioMatriz.length-1][c]=0;
tamanioMatriz[tamanioMatriz.length-1][c+1]=0;
imprimeTodo();
actualizarNumeros();
sumarValores(f-1,c);
if (tamanioMatriz[f][c]!=0)
sumarValores(f,c);
if (tamanioMatriz[f][c+1]!=0)
sumarValores(f,c+1);
}
//fusion tres numeros misma fila
else if((c!=0)&&(c<tamanioMatriz[0].length-1)&&((tamanioMatriz[f][c]==tamanioMatriz[f][c-1])&&((tamanioMatriz[f][c]==tamanioMatriz[f][c+1])))) {
tamanioMatriz[f][c]=tamanioMatriz[f][c]*4;
aux.cambiarPuntuacion(tamanioMatriz[f][c]*4);
for (int i=f; i< tamanioMatriz.length-1; i++){
tamanioMatriz[i][c-1]= tamanioMatriz[i+1][c-1];
tamanioMatriz[i][c+1]= tamanioMatriz[i+1][c+1];
}
tamanioMatriz[tamanioMatriz.length-1][c-1]=0;
tamanioMatriz[tamanioMatriz.length-1][c+1]=0;
imprimeTodo();
actualizarNumeros();
if (tamanioMatriz[f][c-1]!=0)
sumarValores(f,c-1);
if (tamanioMatriz[f][c]!=0)
sumarValores(f,c);
if (tamanioMatriz[f][c+1]!=0)
sumarValores(f,c+1);
}
//fusion izd dos numeros
else if((c!=0)&&(tamanioMatriz[f][c]==tamanioMatriz[f][c-1])){
tamanioMatriz[f][c]=tamanioMatriz[f][c]*2;
aux.cambiarPuntuacion(tamanioMatriz[f][c]*2);
for (int i=f; i< tamanioMatriz.length-1; i++){
tamanioMatriz[i][c-1]= tamanioMatriz[i+1][c-1];
}
tamanioMatriz[tamanioMatriz.length-1][c-1]=0;
imprimeTodo();
actualizarNumeros();
if (tamanioMatriz[f][c-1]!=0)
sumarValores(f,c-1);
if (tamanioMatriz[f][c]!=0)
sumarValores(f,c);
}
//fusion derecha dos numeros
else if((c<tamanioMatriz[0].length-1)&&(tamanioMatriz[f][c]==tamanioMatriz[f][c+1])){
tamanioMatriz[f][c]=tamanioMatriz[f][c]*2;
aux.cambiarPuntuacion(tamanioMatriz[f][c]*2);
for (int i=f; i< tamanioMatriz.length-1; i++){
tamanioMatriz[i][c+1]= tamanioMatriz[i+1][c+1];
}
tamanioMatriz[tamanioMatriz.length-1][c+1]=0;
imprimeTodo();
actualizarNumeros();
sumarValores(f,c);
if (tamanioMatriz[f][c+1]!=0)
sumarValores(f,c+1);
}
//fusion arriba dos numeros
else if((f!=0)&&(tamanioMatriz[f][c]==tamanioMatriz[f-1][c])) {
tamanioMatriz[f-1][c]=tamanioMatriz[f][c]*2;
aux.cambiarPuntuacion(tamanioMatriz[f][c]*2);
for (int i=f; i< tamanioMatriz.length-1; i++){
tamanioMatriz[i][c]= tamanioMatriz[i+1][c];
}
tamanioMatriz[tamanioMatriz.length-1][c]=0;
imprimeTodo();
actualizarNumeros();
sumarValores(f-1,c);
if (tamanioMatriz[f][c]!=0)
sumarValores(f,c);
}
}
}
